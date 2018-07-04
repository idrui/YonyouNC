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
 * @version 2013-11-25 上午09:47:35
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
      // 模拟创建一个前台查询条件
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
   * 查询待结算的库存单据
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   * @param sSrcBillType
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 下午03:17:58
   */
  private StockSettleVO[] queryStocks(InvoiceVO voInvoice)
      throws BusinessException {
    StockSettleVO[] voaStockMerge = null;
    // 库存单据类型是进口入
    IStockFinanceQuery itfStockSettleQuery =
        NCLocator.getInstance().lookup(IStockFinanceQuery.class);
    // 明细单行ID
    Set<String> orderBids =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.PK_ORDER_B);
    voaStockMerge =
        itfStockSettleQuery.queryPurchaseInByOrderBID(orderBids
            .toArray(new String[orderBids.size()]));
    return voaStockMerge;
  }

  /**
   * 各个发票的结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice 发票
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-1 下午02:01:56
   */
  private void settle(InvoiceVO voInvoice) throws BusinessException {

    // ------1检查
    if (voInvoice == null) {
      return;
    }
    // 如果是费用发票，则不用自动结算
    if (UFBoolean.TRUE.equals(voInvoice.getParentVO().getBfee())) {
      return;
    }

    UFBoolean virtual = voInvoice.getParentVO().getBvirtual();
    // 虚拟发票走无发票结算
    if (UFBoolean.TRUE.equals(virtual)) {
      this.settleForVirtualInvoice(voInvoice);
    }
    // 非虚拟发票走自动结算
    else {
      this.settleForCommonInvoice(voInvoice);
    }
  }

  /**
   * 方法功能描述：普通发票的审批自动结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice 普通发票
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-9 上午09:29:48
   */
  private void settleForCommonInvoice(InvoiceVO voInvoice)
      throws BusinessException {
    // 根据发票找到来源单据，如果没有来源，也不用结算
    HashSet<String> hsetSrcBillType =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.CSOURCETYPECODE);
    if (hsetSrcBillType == null || hsetSrcBillType.size() == 0) {
      return;
    }

    // ------ 2组织发票
    // 从发票中分离货物、劳务、折扣
    // 如果表体全无货物，也不用结算
    InvoiceQueryResultForIT allMrgInvc =
        this.queryGoodsAndRelaFeeByHID(voInvoice.getParentVO().getPk_invoice(),
            InvoiceSettleVOUtil.getQueryType(voInvoice,
                IInvoiceSettleQuery.QRY_TYPE_PO));
    if (null == allMrgInvc || ArrayUtils.isEmpty(allMrgInvc.getGoodsInvoices())) {
      return;
    }

    // ------ 3查询库存单据
    StockSettleVO[] voaStockMerge = null;
    voaStockMerge = this.queryStocks(voInvoice);
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4初始化结算环境
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.IT_INVOICE_AUTO);

    // ------ 5库存、发票VO合并
    FeeDiscountSettleVO[] vosFee = allMrgInvc.getFeeInvoices();
    FeeDiscountSettleVO[] vosdiscount = allMrgInvc.getDiscountInvoices();
    InvoiceSettleVO[] goodsInvcs = allMrgInvc.getGoodsInvoices();
    FeeDiscountSettleVO[] vosAdjustInvc = allMrgInvc.getAdjInvoices();
    SettleBillVO[] voaBill = null;
    // 控制并发，与取消暂估
    ViewConcurrentUtil.getInstance().concurrentCheck(voaStockMerge);
    voaBill =
        new InvoiceAutoMatchWithStockMergeForIT(goodsInvcs, voaStockMerge,
            vosFee, vosdiscount, vosAdjustInvc, settleEnv).merge();

    // ------ 6保存结算单
    new SettleBillBPForIT().saveSettleBills(voaBill, settleEnv);
  }

  /**
   * 方法功能描述：无发票结算生成的虚拟发票的审批自动结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice 虚拟发票
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-9 上午09:30:55
   */
  private void settleForVirtualInvoice(InvoiceVO voInvoice)
      throws BusinessException {
    // ------ 2组织发票
    // 从发票中分离货物、劳务、折扣
    // 如果表体全无货物，也不用结算
    // 实际上从入库单生成的虚拟发票不可能有劳务和折扣类物料
    InvoiceSettleVO[] voaInvoiceMerge =
        InvoiceSettleVOUtil.convertFromInvoice(voInvoice);
    if (ArrayUtils.isEmpty(voaInvoiceMerge)) {
      return;
    }

    // ------ 3查询库存单据
    StockSettleVO[] voaStockMerge = this.queryStocks(voInvoice);
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4初始化结算环境
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.IT_WITHOUT_INVOICE);

    // ------ 5库存、发票VO合并
    SettleBillVO[] voaBill =
        new WithoutInoiceMatchMergeForIT(voaInvoiceMerge, voaStockMerge, null,
            null, null, settleEnv).merge();

    // ------ 6保存结算单
    new SettleBillBPForIT().saveSettleBills(voaBill, settleEnv);
  }
}
