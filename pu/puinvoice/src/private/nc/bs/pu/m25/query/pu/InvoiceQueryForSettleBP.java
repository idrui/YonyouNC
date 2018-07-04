package nc.bs.pu.m25.query.pu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.pubitf.pu.m21.pu.m25.IOrderQueryFor25;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ƱΪ�����ѯBP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 ����05:04:21
 */
public class InvoiceQueryForSettleBP {

  /**
   * ����������������ò�ѯSQL���<br>
   * c) δ������ϵĲɹ���Ʊ��<br>
   * d) �ɹ���Ʊ����Ϊ���״̬����Ϊδ����״̬��Ҳ����˵�ɹ���Ʊ������˺�ſ��Բ�����㣩��<br>
   * e) �ɹ���Ʊ�������������С��Ƿ����Ĳɹ���Ϊ�ǵĲɹ���Ʊ��������вɹ����㣻ֻ��Ϊ��ʱ�ſ��Բ���ɹ����㡣<br>
   * f) ����Դ���Ļ��ܵ��Ĳɹ���Ʊ��<br>
   * g) ���������෢Ʊ�����Ӧ���������ϱ����ڲɹ��ɱ�Ҫ���ж����˷�̯����(Щ�����ڲ�ѯ�����ʵ����)<br>
   * 
   * @param queryScheme
   * @return
   */
  public String getSQL(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsPrc = new QuerySchemeProcessor(queryScheme);
    String malias = qsPrc.getMainTableAlias();
    String balias =
        qsPrc.getTableAliasOfAttribute(InvoiceQryFieldCode.invoicebid.code());
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + balias + ".pk_invoice_b, ");
    sql.append(balias + "." + InvoiceItemVO.FROWTYPE);// ������
    sql.append("," + malias + "." + InvoiceHeaderVO.PK_INVOICE);
    sql.append("," + malias + "." + InvoiceHeaderVO.PK_PARENTINVOICE);
    sql.append("," + malias + "." + InvoiceHeaderVO.FINVOICETYPE);
    sql.append(" ");
    SqlBuilder partWhr = new SqlBuilder();
    // �õ��ɽ���ķ�Ʊwhere����
    this.getAppendWhere(queryScheme, malias, balias, partWhr);
    qsPrc.appendWhere(partWhr.toString());
    qsPrc.appendCurrentGroup();
    sql.append(qsPrc.getFinalFromWhere());
    // ���ֽ��ڷ�Ʊ���ǲɹ���Ʊ
    sql.append(this.getPUorITWhere(malias + "." + InvoiceHeaderVO.FINVOICETYPE));
    // ƴ�Ӳ�ѯ�������÷�Ʊ��SQL
    InvoiceQueryForSettleBPExt bpext = new InvoiceQueryForSettleBPExt(this);
    bpext.appendRelaFeeQuerySQL(queryScheme, sql);
    return sql.toString();
  }

  /**
   * ���ݲ�ѯ�����õ����Ͻ���ķ�Ʊ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 ����06:25:56
   */
  public InvoiceQueryResultFor27 queryByWhereSql(IQueryScheme queryScheme) {
    Set<String> goodsbids = new HashSet<String>();
    Set<String> feebids = new HashSet<String>();
    Set<String> discountbids = new HashSet<String>();
    Set<String> adjbids = new HashSet<String>();
    // ��ѯ���������ķ�ƱBID
    this.getQueryInvoiceBID(queryScheme, goodsbids, feebids, discountbids,
        adjbids);
    // ��鷵�ؼ�¼����
    int maxRow = Variable.getMaxQueryCount();
    if (goodsbids.size() + feebids.size() + discountbids.size() > maxRow) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("common", "04004000-0000", null, new String[] {
            String.valueOf(maxRow)
          })/* ��ѯ������ؼ�¼������{0}�������޸Ĳ�ѯ�������ٲ�ѯ��¼���� */);
    }
    // ���﷢Ʊ�� ����Ʊ���ۿ۷�Ʊ
    InvoiceSettleVO[] goodsInvoices = null;
    FeeDiscountSettleVO[] feeInvoices = null;
    FeeDiscountSettleVO[] discountInvoices = null;
    // �������﷢Ʊ
    FeeDiscountSettleVO[] adjInvoices = null;
    goodsInvoices = this.queryGoodsInvoice(goodsbids);
    ViewQuery<FeeDiscountSettleVO> query =
        new ViewQuery<FeeDiscountSettleVO>(FeeDiscountSettleVO.class);
    feeInvoices = this.queryFeeInvoice(feebids, query);
    discountInvoices = this.queryDiscountInvoice(discountbids, query);
    adjInvoices = this.queryaAdjbidsInvoice(adjbids, query);

    // ���췵�ؽṹ
    InvoiceQueryResultFor27 result =
        this.constructResult(queryScheme, goodsInvoices, feeInvoices,
            discountInvoices, adjInvoices);
    return result;
  }

  private void clearReasonWasteNum(InvoiceSettleVO[] vos) {
    if (null == vos) {
      return;
    }
    for (InvoiceSettleVO vo : vos) {
      vo.setNreasonwastenum(null);
    }
  }

  private List<FeeDiscountSettleVO> fillupCostFactor(
      FeeDiscountSettleVO[] feeInvoices) {
    // �ɱ�Ҫ��
    String pk_fiorg = feeInvoices[0].getPk_org();
    String[] mrlsrcpks =
        CirVOUtil.getDistinctFieldSet(feeInvoices,
            InvoiceSettleVO.PK_SRCMATERIAL).toArray(new String[0]);
    CostfactorViewVO[] factors = null;
    try {
      IFactorOrdByOrgMaterial cfSrv =
          NCLocator.getInstance().lookup(IFactorOrdByOrgMaterial.class);
      factors = cfSrv.queryFactorViewVOByOrgMaterial(pk_fiorg, mrlsrcpks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == factors || ArrayUtils.isEmpty(factors)) {
      return null;
    }
    // ���óɱ�Ҫ�ء���̯��ʽ
    Map<String, CostfactorViewVO> mrl_factorvo_map =
        new HashMap<String, CostfactorViewVO>();
    for (CostfactorViewVO factor : factors) {
      String mrl = factor.getPk_srcmaterial();
      mrl_factorvo_map.put(mrl, factor);
    }
    List<FeeDiscountSettleVO> feeLst = new ArrayList<FeeDiscountSettleVO>();
    for (FeeDiscountSettleVO fee : feeInvoices) {
      String feemrl = fee.getPk_srcmaterial();
      if (mrl_factorvo_map.get(feemrl) == null) {
        // ���˵�δ�ڳɱ�Ҫ���ж���ķ����෢Ʊ
        continue;
      }
      CostfactorViewVO factor = mrl_factorvo_map.get(feemrl);
      fee.setPk_costfactor(factor.getPk_costfactor());// Ҫ������
      fee.setFapportionmode(factor.getFapportionmode());// ��̯��ʽ
      feeLst.add(fee);
    }
    return feeLst;
  }

  private void getAppendWhere(IQueryScheme queryScheme, String malias,
      String balias, SqlBuilder partWhr) {
    // ������Դ�ڼӹ��ѽ��㵥�ķ�Ʊ mengjian
    partWhr.appendNot(" and " + balias + "." + InvoiceItemVO.CSOURCETYPECODE,
        new String[] {
          "55E6"
        });
    partWhr.append(" and " + balias + ".dr", 0);
    // ������ ������������Ϊ����Ʊ������ֹ������ˣ�Ҳ���ܲ�����㣿--��Ϊ����
    partWhr.append(" and isnull(po_invoice.bfrozen, 'N')", UFBoolean.FALSE);
    // �ɹ���Ʊ����Ϊ���״̬
    partWhr.append(" and " + malias + "." + InvoiceHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInteger());
    // δ������ϵĲɹ���Ʊ
    partWhr.append(" and abs(isnull(" + balias + "."
        + InvoiceItemVO.NACCUMSETTMNY + ", 0)) < ");
    // partWhr.append("abs(" + balias + "." + InvoiceItemVO.NMNY + ")");
    // V61 wuxla
    // ��Ʊ�����ν��������ȡ��Ʊ�ı�����˰������Ϊȡ��Ʊ�ļƳɱ���
    // ����ͨ���Ƴɱ�����жϣ����ٸ��ݷ�Ʊ�ı�����˰����ж�
    partWhr.append("abs(" + balias + "." + InvoiceItemVO.NCALCOSTMNY + ")");
    // �ɹ���Ʊ�������������С��Ƿ����Ĳɹ���Ϊ�ǵĲɹ���Ʊ��������вɹ����㣻ֻ��Ϊ��ʱ�ſ��Բ���ɹ�����
    partWhr.append(this.getWhrByTrantype(malias));
    String qryType = (String) queryScheme.get(IInvoiceSettleQuery.QRY_TYPE_KEY);
    // ���ݲɹ����㻹�����Ļ��ܽ����߲�ͬ�Ĳ�ѯ
    partWhr.append(this.getWhrBySettleQryType(qryType, balias));
    // ���ս�����������Ϊ��Ӧ�̼Ĵ�Ĳɹ��������ɵĲɹ���Ʊ��Ҫ���˵�
    partWhr.append(this.getWhrByOrderTrantype(qryType, balias));
    // �����Ƿ���÷�Ʊ����ѯ����
    partWhr.append(this.getWhrByIncludeFee(queryScheme, balias));
    // �����Ƿ��Ѿ��������ⵥ�������ϡ���ѯ����
    partWhr.append(this.getWhrByIncludeStockMar(queryScheme, balias));
  }

  private void getQueryInvoiceBID(IQueryScheme queryScheme,
      Set<String> goodsbids, Set<String> feebids, Set<String> discountbids,
      Set<String> adjbids) {
    DataAccessUtils dataAccessUtils = new DataAccessUtils();
    // ���ݲ�ѯ�����õ��ӱ�ID����
    IRowSet rowset = dataAccessUtils.query(this.getSQL(queryScheme));

    while (rowset.next()) {
      String bid = rowset.getString(0);
      int type = rowset.getInteger(1).intValue();
      Integer finvoicetype = rowset.getInteger(4);
      if (InvoiceRowType.GOODS_ROW == type
          && InvoicePuImportEnum.ADJUSTINVOICE.toInteger().equals(finvoicetype)) {
        adjbids.add(bid);
      }
      else if (InvoiceRowType.GOODS_ROW == type) {
        goodsbids.add(bid);
      }
      else if (InvoiceRowType.DISCOUNT_ROW == type) {
        discountbids.add(bid);
      }
      else if (InvoiceRowType.FEE_ROW == type) {
        feebids.add(bid);
      }
    }
  }

  private SqlBuilder getWhrByIncludeFee(IQueryScheme qs, String balias) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(qs);
    QueryCondition qc =
        qsp.getQueryCondition(InvoiceQryFieldCode.bfeeinvoice.code());
    UFBoolean bincfee = null;
    String cv = null != qc ? (String) qc.getValues()[0] : null;
    bincfee = null == cv ? null : UFBoolean.valueOf(cv);
    SqlBuilder sql = new SqlBuilder();
    if (null == bincfee) {
      return sql;
    }
    // ֻ���������෢Ʊ
    if (bincfee.booleanValue()) {
      sql.append(" and " + balias + "." + InvoiceItemVO.FROWTYPE, new int[] {
        InvoiceRowType.DISCOUNT_ROW, InvoiceRowType.FEE_ROW
      });
      return sql;
    }
    sql.append(" and " + balias + "." + InvoiceItemVO.FROWTYPE, new int[] {
      InvoiceRowType.GOODS_ROW
    });
    return sql;
  }

  private SqlBuilder getWhrByIncludeStockMar(IQueryScheme qs, String balias) {
    SqlBuilder sql = new SqlBuilder();
    Set<?> stockMarIDSet =
        (Set<?>) qs.get(IInvoiceSettleQuery.INCLUDE_MAR__KEY);
    if (CollectionUtils.isEmpty(stockMarIDSet)) {
      return sql;
    }
    String qryName = balias + "." + InvoiceItemVO.PK_MATERIAL;
    String marInWhr =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_06.name()).buildSQL(qryName,
            stockMarIDSet.toArray(new String[stockMarIDSet.size()]));
    sql.append(" and ");
    sql.append(marInWhr);
    return sql;
  }

  private SqlBuilder getWhrBySettleQryType(String type, String balias) {
    String srctypeName = balias + "." + InvoiceItemVO.CSOURCETYPECODE;
    String[] srcTypes = {
      ICBillType.VmiSum.getCode()
    };
    SqlBuilder whr = new SqlBuilder();
    whr.append(" and (");
    // �ɹ����㣬������Դ�����Ļ���
    if (IInvoiceSettleQuery.QRY_TYPE_PO.equals(type)) {
      whr.appendNot(srctypeName, srcTypes);
    }
    else {
      // ��Դ���Ļ��ܵ��Ĳɹ���Ʊ
      whr.append(srctypeName, srcTypes);
    }
    whr.appendIDIsNull(" or " + balias + "." + InvoiceItemVO.CSOURCEBID);
    whr.append(")");
    return whr;
  }

  private SqlBuilder getWhrByTrantype(String mainAlias) {
    SqlBuilder whr = new SqlBuilder();
    whr.append(" and " + mainAlias + "." + InvoiceHeaderVO.VTRANTYPECODE);
    whr.append(" in (select " + InvcTranTypeVO.VTRANTYPECODE);
    whr.append(" from " + PUEntity.INVC_TRANTYPE_TAB + " where isnull(");
    whr.append(InvcTranTypeVO.BCONSUMEPUR + ", 'N') ", UFBoolean.FALSE);
    whr.append("and dr=0 and " + InvcTranTypeVO.PK_GROUP);
    whr.append("='" + BSContext.getInstance().getGroupID() + "')");
    return whr;
  }

  private void processForVMIMatch(InvoiceQueryResultFor27 reslt) {
    // ���Ļ��ܲ�֧�ֺ�����ģ�V60����V5X�����Ȳ�֧�֣�����wangyfȷ�ϣ�
    this.clearReasonWasteNum(reslt.getGoodsInvoices());
    this.clearReasonWasteNum(reslt.getDiscountInvoices());
    this.clearReasonWasteNum(reslt.getFeeInvoices());
  }

  private FeeDiscountSettleVO[] queryaAdjbidsInvoice(Set<String> adjbids,
      ViewQuery<FeeDiscountSettleVO> query) {
    FeeDiscountSettleVO[] adjInvoices = null;
    if (adjbids.size() > 0) {
      adjInvoices = query.query(adjbids.toArray(new String[adjbids.size()]));
      InvoiceSettleVOUtil.calInvoiceCanSettleValue(adjInvoices);
    }
    return adjInvoices;
  }

  private FeeDiscountSettleVO[] queryDiscountInvoice(Set<String> discountbids,
      ViewQuery<FeeDiscountSettleVO> query) {
    FeeDiscountSettleVO[] discountInvoices = null;
    if (discountbids.size() > 0) {
      discountInvoices =
          query.query(discountbids.toArray(new String[discountbids.size()]));
      InvoiceSettleVOUtil.calInvoiceCanSettleValue(discountInvoices);
    }
    return discountInvoices;
  }

  private FeeDiscountSettleVO[] queryFeeInvoice(Set<String> feebids,
      ViewQuery<FeeDiscountSettleVO> query) {
    FeeDiscountSettleVO[] feeInvoices = null;
    if (feebids.size() > 0) {
      feeInvoices = query.query(feebids.toArray(new String[feebids.size()]));
      // ��ɱ�Ҫ��������������
      List<FeeDiscountSettleVO> feeLst = this.fillupCostFactor(feeInvoices);
      if (CollectionUtils.isEmpty(feeLst)) {
        feeInvoices = null;
      }
      else {
        feeInvoices = feeLst.toArray(new FeeDiscountSettleVO[feeLst.size()]);
        InvoiceSettleVOUtil.calInvoiceCanSettleValue(feeInvoices);
      }
    }
    return feeInvoices;
  }

  private InvoiceSettleVO[] queryGoodsInvoice(Set<String> goodsbids) {
    if (goodsbids.size() > 0) {
      ViewQuery<InvoiceSettleVO> query =
          new ViewQuery<InvoiceSettleVO>(InvoiceSettleVO.class);
      InvoiceSettleVO[] goodsInvoices =
          query.query(goodsbids.toArray(new String[goodsbids.size()]));
      InvoiceSettleVOUtil.calInvoiceCanSettleValue(goodsInvoices);
      return goodsInvoices;
    }
    return null;
  }

  protected InvoiceQueryResultFor27 constructResult(IQueryScheme queryScheme,
      InvoiceSettleVO[] goodsInvoices, FeeDiscountSettleVO[] feeInvoices,
      FeeDiscountSettleVO[] discountInvoices, FeeDiscountSettleVO[] adjInvoices) {
    InvoiceQueryResultFor27 result = new InvoiceQueryResultFor27();
    result.setGoodsInvoices(goodsInvoices);
    result.setFeeInvoices(feeInvoices);
    result.setDiscountInvoices(discountInvoices);
    String qryType = (String) queryScheme.get(IInvoiceSettleQuery.QRY_TYPE_KEY);
    if (IInvoiceSettleQuery.QRY_TYPE_VMI.equals(qryType)) {
      this.processForVMIMatch(result);// Ϊ���Ļ��ܲ�ѯ�����⴦��
    }
    return result;
  }

  protected Object getPUorITWhere(String field) {
    String where = "";
    // �ɹ������ѯ��Ʊ
    where =
        " isnull(" + field + ", " + InvoicePuImportEnum.PUINVOICE.toInt()
            + ") = " + InvoicePuImportEnum.PUINVOICE.toInt();
    return " and (" + where + ")";
  }

  protected SqlBuilder getWhrByOrderTrantype(String type, String balias) {
    if (!IInvoiceSettleQuery.QRY_TYPE_PO.equals(type)) {
      return new SqlBuilder();
    }
    try {
      IQueryScheme otqs =
          NCLocator.getInstance().lookup(IOrderQueryFor25.class)
              .getNonVMITrantypeQS(BSContext.getInstance().getGroupID());
      SqlBuilder sql = new SqlBuilder();
      sql.append(" and ");
      sql.startParentheses();
      sql.append(balias + "." + InvoiceItemVO.VORDERTRANTYPE + " in ");
      sql.startParentheses();
      sql.append("select " + PoTransTypeVO.CTRANTYPEID + " ");
      sql.append(otqs.getTableJoinFromWhereSQL().toString());
      sql.endParentheses();
      sql.appendIDIsNull(" or " + balias + "." + InvoiceItemVO.PK_ORDER_B);
      sql.append(" or " + balias + "." + InvoiceItemVO.CSOURCETYPECODE,
          SCBillType.Order.getCode());
      sql.append(" or " + balias + "." + InvoiceItemVO.CFIRSTTYPECODE,
          SCBillType.Order.getCode());
      sql.endParentheses();
      return sql;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
