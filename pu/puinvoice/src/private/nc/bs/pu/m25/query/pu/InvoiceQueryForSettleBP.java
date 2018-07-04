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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票为结算查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 下午05:04:21
 */
public class InvoiceQueryForSettleBP {

  /**
   * 方法功能描述：获得查询SQL语句<br>
   * c) 未结算完毕的采购发票。<br>
   * d) 采购发票必须为审核状态并且为未冻结状态（也就是说采购发票必须审核后才可以参与结算）。<br>
   * e) 采购发票交易类型属性中“是否消耗采购”为是的采购发票不允许进行采购结算；只有为否时才可以参与采购结算。<br>
   * f) 非来源消耗汇总单的采购发票。<br>
   * g) 对于劳务类发票，则对应劳务类物料必须在采购成本要素中定义了分摊规则(些规则在查询后过滤实现了)<br>
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
    sql.append(balias + "." + InvoiceItemVO.FROWTYPE);// 行类型
    sql.append("," + malias + "." + InvoiceHeaderVO.PK_INVOICE);
    sql.append("," + malias + "." + InvoiceHeaderVO.PK_PARENTINVOICE);
    sql.append("," + malias + "." + InvoiceHeaderVO.FINVOICETYPE);
    sql.append(" ");
    SqlBuilder partWhr = new SqlBuilder();
    // 得到可结算的发票where条件
    this.getAppendWhere(queryScheme, malias, balias, partWhr);
    qsPrc.appendWhere(partWhr.toString());
    qsPrc.appendCurrentGroup();
    sql.append(qsPrc.getFinalFromWhere());
    // 区分进口发票还是采购发票
    sql.append(this.getPUorITWhere(malias + "." + InvoiceHeaderVO.FINVOICETYPE));
    // 拼接查询关联费用发票的SQL
    InvoiceQueryForSettleBPExt bpext = new InvoiceQueryForSettleBPExt(this);
    bpext.appendRelaFeeQuerySQL(queryScheme, sql);
    return sql.toString();
  }

  /**
   * 根据查询条件得到符合结算的发票。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 下午06:25:56
   */
  public InvoiceQueryResultFor27 queryByWhereSql(IQueryScheme queryScheme) {
    Set<String> goodsbids = new HashSet<String>();
    Set<String> feebids = new HashSet<String>();
    Set<String> discountbids = new HashSet<String>();
    Set<String> adjbids = new HashSet<String>();
    // 查询符合条件的发票BID
    this.getQueryInvoiceBID(queryScheme, goodsbids, feebids, discountbids,
        adjbids);
    // 检查返回记录行数
    int maxRow = Variable.getMaxQueryCount();
    if (goodsbids.size() + feebids.size() + discountbids.size() > maxRow) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("common", "04004000-0000", null, new String[] {
            String.valueOf(maxRow)
          })/* 查询结果返回记录数超过{0}条，请修改查询条件减少查询记录数！ */);
    }
    // 货物发票、 劳务发票、折扣发票
    InvoiceSettleVO[] goodsInvoices = null;
    FeeDiscountSettleVO[] feeInvoices = null;
    FeeDiscountSettleVO[] discountInvoices = null;
    // 调整货物发票
    FeeDiscountSettleVO[] adjInvoices = null;
    goodsInvoices = this.queryGoodsInvoice(goodsbids);
    ViewQuery<FeeDiscountSettleVO> query =
        new ViewQuery<FeeDiscountSettleVO>(FeeDiscountSettleVO.class);
    feeInvoices = this.queryFeeInvoice(feebids, query);
    discountInvoices = this.queryDiscountInvoice(discountbids, query);
    adjInvoices = this.queryaAdjbidsInvoice(adjbids, query);

    // 构造返回结构
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
    // 成本要素
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
    // 设置成本要素、分摊方式
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
        // 过滤掉未在成本要素中定义的费用类发票
        continue;
      }
      CostfactorViewVO factor = mrl_factorvo_map.get(feemrl);
      fee.setPk_costfactor(factor.getPk_costfactor());// 要素主键
      fee.setFapportionmode(factor.getFapportionmode());// 分摊方式
      feeLst.add(fee);
    }
    return feeLst;
  }

  private void getAppendWhere(IQueryScheme queryScheme, String malias,
      String balias, SqlBuilder partWhr) {
    // 过滤来源于加工费结算单的发票 mengjian
    partWhr.appendNot(" and " + balias + "." + InvoiceItemVO.CSOURCETYPECODE,
        new String[] {
          "55E6"
        });
    partWhr.append(" and " + balias + ".dr", 0);
    // 赵玉行 根据需求我认为负发票，如果手工冻结了，也不能参与结算？--答案为：是
    partWhr.append(" and isnull(po_invoice.bfrozen, 'N')", UFBoolean.FALSE);
    // 采购发票必须为审核状态
    partWhr.append(" and " + malias + "." + InvoiceHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInteger());
    // 未结算完毕的采购发票
    partWhr.append(" and abs(isnull(" + balias + "."
        + InvoiceItemVO.NACCUMSETTMNY + ", 0)) < ");
    // partWhr.append("abs(" + balias + "." + InvoiceItemVO.NMNY + ")");
    // V61 wuxla
    // 发票金额、本次结算金额等由取发票的本币无税金额调整为取发票的计成本金额，
    // 所以通过计成本金额判断，不再根据发票的本币无税金额判断
    partWhr.append("abs(" + balias + "." + InvoiceItemVO.NCALCOSTMNY + ")");
    // 采购发票交易类型属性中“是否消耗采购”为是的采购发票不允许进行采购结算；只有为否时才可以参与采购结算
    partWhr.append(this.getWhrByTrantype(malias));
    String qryType = (String) queryScheme.get(IInvoiceSettleQuery.QRY_TYPE_KEY);
    // 根据采购结算还是消耗汇总结算走不同的查询
    partWhr.append(this.getWhrBySettleQryType(qryType, balias));
    // 参照交易类型属性为供应商寄存的采购订单生成的采购发票，要过滤掉
    partWhr.append(this.getWhrByOrderTrantype(qryType, balias));
    // 处理“是否费用发票”查询条件
    partWhr.append(this.getWhrByIncludeFee(queryScheme, balias));
    // 处理“是否按已经输入的入库单过滤物料”查询条件
    partWhr.append(this.getWhrByIncludeStockMar(queryScheme, balias));
  }

  private void getQueryInvoiceBID(IQueryScheme queryScheme,
      Set<String> goodsbids, Set<String> feebids, Set<String> discountbids,
      Set<String> adjbids) {
    DataAccessUtils dataAccessUtils = new DataAccessUtils();
    // 根据查询条件得到子表ID数组
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
    // 只包含费用类发票
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
    // 采购结算，不能来源于消耗汇总
    if (IInvoiceSettleQuery.QRY_TYPE_PO.equals(type)) {
      whr.appendNot(srctypeName, srcTypes);
    }
    else {
      // 来源消耗汇总单的采购发票
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
    // 消耗汇总不支持合理损耗（V60根据V5X规则，先不支持，需求wangyf确认）
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
      // 填补成本要素主键，并过滤
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
      this.processForVMIMatch(result);// 为消耗汇总查询做特殊处理
    }
    return result;
  }

  protected Object getPUorITWhere(String field) {
    String where = "";
    // 采购结算查询发票
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
