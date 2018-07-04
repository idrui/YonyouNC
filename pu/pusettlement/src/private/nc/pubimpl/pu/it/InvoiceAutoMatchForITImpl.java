package nc.pubimpl.pu.it;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m25.query.pu.it.InvoiceQueryForITSettleBP;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.pubimpl.pu.it.bp.SettleBillBPForIT;
import nc.pubimpl.pu.it.merge.InvoiceAutoMatchWithStockMergeForIT;
import nc.pubimpl.pu.it.merge.WithoutInoiceMatchMergeForIT;
import nc.pubitf.pu.it.IInvoiceAutoMatchForIT;
import nc.pubitf.pu.m25.it.InvoiceQueryResultForIT;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ViewConcurrentUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.31
 * @version 2013-11-25 ����09:47:35
 * @author mengjian
 */
public class InvoiceAutoMatchForITImpl implements IInvoiceAutoMatchForIT {

  @Override
  public void invoiceAutoMatch4IT(InvoiceVO[] voaPara) throws BusinessException {

    if (voaPara == null) {
      return;
    }

    try {
      for (InvoiceVO voReal : voaPara) {
        this.settle(voReal);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private QueryScheme createQueryScheme(String invoiceHId, String queryType)
      throws BusinessException {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M25_H_TAB + " " + PUEntity.M25_H_TAB);
    SqlBuilder where = new SqlBuilder();
    where.append(PUEntity.M25_H_TAB + "." + InvoiceHeaderVO.PK_INVOICE,
        invoiceHId);
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M25_H_TAB);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String invcbeanid =
        MetaUtils.getBusinessEntityByBillType(POBillType.Invoice.getCode())
            .getID();
    String[] incRelaFee = {
      UFBoolean.TRUE.toString()
    };
    QueryCondition qc =
        new QueryCondition(InvoiceQryFieldCode.brelafeeinclude.code(), "=",
            incRelaFee);
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qcMap.put(qc.getFieldCode(), qc);
    QueryScheme qs = new QueryScheme();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, invcbeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    qs.put(IInvoiceSettleQuery.QRY_TYPE_KEY, queryType);
    return qs;
  }

  private InvoiceQueryResultForIT queryGoodsAndRelaFeeByHID(String invoiceHId,
      String queryType) throws BusinessException {
    try {
      // ģ�ⴴ��һ��ǰ̨��ѯ����
      QueryScheme qs = this.createQueryScheme(invoiceHId, queryType);
      return (InvoiceQueryResultForIT) new InvoiceQueryForITSettleBP()
          .queryByWhereSql(qs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  /**
   * ��ѯ������Ŀ�浥��
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice
   * @param sSrcBillType
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 ����03:17:58
   */
  private StockSettleVO[] queryStocks(InvoiceVO voInvoice)
      throws BusinessException {
    StockSettleVO[] voaStockMerge = null;
    // ��浥�������ǽ�����
    IStockFinanceQuery itfStockSettleQuery =
        NCLocator.getInstance().lookup(IStockFinanceQuery.class);
    // ��ϸ����ID
    Set<String> orderBids =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.PK_ORDER_B);
    voaStockMerge =
        itfStockSettleQuery.queryPurchaseInByOrderBID(orderBids
            .toArray(new String[orderBids.size()]));
    return voaStockMerge;
  }

  /**
   * ������Ʊ�Ľ��㡣
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice ��Ʊ
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-1 ����02:01:56
   */
  private void settle(InvoiceVO voInvoice) throws BusinessException {

    // ------1���
    if (voInvoice == null) {
      return;
    }
    // ����Ƿ��÷�Ʊ�������Զ�����
    if (UFBoolean.TRUE.equals(voInvoice.getParentVO().getBfee())) {
      return;
    }

    UFBoolean virtual = voInvoice.getParentVO().getBvirtual();
    // ���ⷢƱ���޷�Ʊ����
    if (UFBoolean.TRUE.equals(virtual)) {
      this.settleForVirtualInvoice(voInvoice);
    }
    // �����ⷢƱ���Զ�����
    else {
      this.settleForCommonInvoice(voInvoice);
    }
  }

  /**
   * ����������������ͨ��Ʊ�������Զ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice ��ͨ��Ʊ
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-9 ����09:29:48
   */
  private void settleForCommonInvoice(InvoiceVO voInvoice)
      throws BusinessException {
    // ���ݷ�Ʊ�ҵ���Դ���ݣ����û����Դ��Ҳ���ý���
    HashSet<String> hsetSrcBillType =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.CSOURCETYPECODE);
    if (hsetSrcBillType == null || hsetSrcBillType.size() == 0) {
      return;
    }

    // ------ 2��֯��Ʊ
    // �ӷ�Ʊ�з����������ۿ�
    // �������ȫ�޻��Ҳ���ý���
    InvoiceQueryResultForIT allMrgInvc =
        this.queryGoodsAndRelaFeeByHID(voInvoice.getParentVO().getPk_invoice(),
            InvoiceSettleVOUtil.getQueryType(voInvoice,
                IInvoiceSettleQuery.QRY_TYPE_PO));
    if (null == allMrgInvc || ArrayUtils.isEmpty(allMrgInvc.getGoodsInvoices())) {
      return;
    }

    // ------ 3��ѯ��浥��
    StockSettleVO[] voaStockMerge = null;
    voaStockMerge = this.queryStocks(voInvoice);
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4��ʼ�����㻷��
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.IT_INVOICE_AUTO);

    // ------ 5��桢��ƱVO�ϲ�
    FeeDiscountSettleVO[] vosFee = allMrgInvc.getFeeInvoices();
    FeeDiscountSettleVO[] vosdiscount = allMrgInvc.getDiscountInvoices();
    InvoiceSettleVO[] goodsInvcs = allMrgInvc.getGoodsInvoices();
    FeeDiscountSettleVO[] vosAdjustInvc = allMrgInvc.getAdjInvoices();
    SettleBillVO[] voaBill = null;
    // ���Ʋ�������ȡ���ݹ�
    ViewConcurrentUtil.getInstance().concurrentCheck(voaStockMerge);
    voaBill =
        new InvoiceAutoMatchWithStockMergeForIT(goodsInvcs, voaStockMerge,
            vosFee, vosdiscount, vosAdjustInvc, settleEnv).merge();

    // ------ 6������㵥
    new SettleBillBPForIT().saveSettleBills(voaBill, settleEnv);
  }

  /**
   * ���������������޷�Ʊ�������ɵ����ⷢƱ�������Զ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice ���ⷢƱ
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-9 ����09:30:55
   */
  private void settleForVirtualInvoice(InvoiceVO voInvoice)
      throws BusinessException {
    // ------ 2��֯��Ʊ
    // �ӷ�Ʊ�з����������ۿ�
    // �������ȫ�޻��Ҳ���ý���
    // ʵ���ϴ���ⵥ���ɵ����ⷢƱ��������������ۿ�������
    InvoiceSettleVO[] voaInvoiceMerge =
        InvoiceSettleVOUtil.convertFromInvoice(voInvoice);
    if (ArrayUtils.isEmpty(voaInvoiceMerge)) {
      return;
    }

    // ------ 3��ѯ��浥��
    StockSettleVO[] voaStockMerge = this.queryStocks(voInvoice);
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4��ʼ�����㻷��
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.IT_WITHOUT_INVOICE);

    // ------ 5��桢��ƱVO�ϲ�
    SettleBillVO[] voaBill =
        new WithoutInoiceMatchMergeForIT(voaInvoiceMerge, voaStockMerge, null,
            null, null, settleEnv).merge();

    // ------ 6������㵥
    new SettleBillBPForIT().saveSettleBills(voaBill, settleEnv);
  }
}
