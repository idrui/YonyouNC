package nc.pubimpl.pu.m27;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m27.bp.SettleBillBP;
import nc.impl.pu.m27.merge.InvoiceAutoMatchDTransNoStockMerge;
import nc.impl.pu.m27.merge.InvoiceAutoMatchWithStockMerge;
import nc.impl.pu.m27.merge.WithoutInoiceMatchMerge;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.itf.uap.pf.IPFConfig;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.pubitf.pu.m27.IInvoiceAutoMatch;
import nc.pubitf.pu.m4t.pu.settle.IInitialEstSettleQuery;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillbusinessVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ViewConcurrentUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票自动结算的实现类
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since V6.0
 * @author wangyf
 * @time 2009-7-6 上午09:46:09
 */
public class InvoiceAutoMatchImpl implements IInvoiceAutoMatch {

  @Override
  public void invoiceAutoMatch(InvoiceVO[] voaPara) throws BusinessException {

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
  private StockSettleVO[] queryStocks(InvoiceVO voInvoice, String sSrcBillType)
      throws BusinessException {
    HashSet<String> hsetSrcBid =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.CSOURCEBID);
    StockSettleVO[] voaStockMerge = null;

    // -------------委外订单、采购订单自动结算
    if (POBillType.Order.getCode().equals(sSrcBillType)
        && InvoiceVOUtil.isAutoSettleFromOrder(voInvoice)) {// 必须参数PO46设置了自动结算
      // 库存单据类型是采购入
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      Set<String> orderBids =
          CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
              InvoiceItemVO.PK_ORDER_B);
      StockSettleVO[] voaPurchseIn =
          itfStockSettleQuery.queryPurchaseInByOrderBID(orderBids
              .toArray(new String[orderBids.size()]));
      IInitialEstSettleQuery itfInitial =
          NCLocator.getInstance().lookup(IInitialEstSettleQuery.class);
      StockSettleVO[] voaInitial =
          itfInitial.queryByOrderBID(hsetSrcBid.toArray(new String[hsetSrcBid
              .size()]));
      voaStockMerge = ArrayUtil.combinArrays(voaPurchseIn, voaInitial);
    }
    if (SCBillType.Order.getCode().equals(sSrcBillType)
        && InvoiceVOUtil.isAutoSettleFromOrder(voInvoice)) {// 必须参数PO46设置了自动结算
      // 库存单据类型是委外入
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.querySubcontractByOrderBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    // -------------库存自动结算
    else if (ICBillType.PurchaseIn.getCode().equals(sSrcBillType)) {
      // 库存单据类型是采购入
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.queryPurchaseInByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    else if (POBillType.InitEstimate.getCode().equals(sSrcBillType)) {
      // 期初暂估入
      IInitialEstSettleQuery itfInitial =
          NCLocator.getInstance().lookup(IInitialEstSettleQuery.class);
      voaStockMerge =
          itfInitial
              .queryByBID(hsetSrcBid.toArray(new String[hsetSrcBid.size()]));
    }
    else if (ICBillType.SubContinIn.getCode().equals(sSrcBillType)) {
      // 库存单据类型是委外入
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.querySubcontractByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    else if (ICBillType.VmiSum.getCode().equals(sSrcBillType)) {
      // 库存单据类型是VMI
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.queryVoiconsumeByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }

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
    IInvoiceSettleQuery iInvoiceQuery =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    InvoiceQueryResultFor27 allMrgInvc =
        iInvoiceQuery.queryGoodsAndRelaFeeByHID(voInvoice.getParentVO()
            .getPk_invoice(), InvoiceSettleVOUtil.getQueryType(voInvoice,
            IInvoiceSettleQuery.QRY_TYPE_PO));
    if (null == allMrgInvc || ArrayUtils.isEmpty(allMrgInvc.getGoodsInvoices())) {
      return;
    }

    // ------ 3查询库存单据
    StockSettleVO[] voaStockMerge = null;
    boolean bDTrans = false;
    boolean bStockInFlow = true;
    {
      // 查询是否直运、直运流程中有否库存单据
      {
        // 得到订单的交易类型
        HashSet<String> hsetOrderType =
            CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
                InvoiceItemVO.VORDERTRANTYPE);
        String sOrderType =
            hsetOrderType == null || hsetOrderType.size() == 0 ? null
                : hsetOrderType.iterator().next();

        // 只查采购订单
        if (!PubAppTool.isNull(sOrderType)
            && POBillType.Order.getCode().equals(
                PfUtilBaseTools.getRealBilltypeByPK(sOrderType))) {
          IPoTransTypeQuery queryPoType =
              NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
          Map<String, PoTransTypeVO> hmapPoType =
              queryPoType.queryAttrByIDs(new String[]{sOrderType});
          bDTrans = hmapPoType.get(sOrderType).getBdirect().booleanValue();

          // 如果是直运，看流程有无库存单据
          if (bDTrans) {
            // 是否直运、有无采购入库、来源单据类型、
            IPFConfig pfConfig =
                NCLocator.getInstance().lookup(IPFConfig.class);

            BillbusinessVO[] vosBusi =
                pfConfig.findBillbusinessVOs(voInvoice.getParentVO()
                    .getPk_busitype(), voInvoice.getParentVO().getPk_group());
            bStockInFlow =
                BillbusinessVOUtil.isExistBillType(vosBusi,
                    ICBillType.PurchaseIn.getCode());
          }
        }
      }
      // 直运且流程中无入库单时，不用查库存单据；其他必须有库存单据
      if (!(bDTrans && !bStockInFlow)) {
        String srcBilltype = "";
        Iterator<String> iterator = hsetSrcBillType.iterator();
        while (iterator.hasNext()) {
          srcBilltype = iterator.next();
          // 这里取的是入库类型，来源订单的不考虑
          if (!POBillType.Order.getCode().equals(srcBilltype)) {
            break;
          }
        }
        voaStockMerge = this.queryStocks(voInvoice, srcBilltype);
        if (voaStockMerge == null) {
          return;
        }
      }
    }

    // ------ 4初始化结算环境
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.INVOICE_AUTO);

    // ------ 5库存、发票VO合并
    FeeDiscountSettleVO[] vosFee = allMrgInvc.getFeeInvoices();
    FeeDiscountSettleVO[] vosdiscount = allMrgInvc.getDiscountInvoices();
    InvoiceSettleVO[] goodsInvcs = allMrgInvc.getGoodsInvoices();
    SettleBillVO[] voaBill = null;
    // 有入库单、直运无入库单要区分开
    if (bDTrans && !bStockInFlow) {
      voaBill =
          new InvoiceAutoMatchDTransNoStockMerge(goodsInvcs, null, vosFee,
              vosdiscount, settleEnv).merge();
    }
    else {
      // 控制并发，与取消暂估
      ViewConcurrentUtil.getInstance().concurrentCheck(voaStockMerge);
      voaBill =
          new InvoiceAutoMatchWithStockMerge(goodsInvcs, voaStockMerge, vosFee,
              vosdiscount, settleEnv).merge();
    }

    // ------ 6保存结算单
    new SettleBillBP().saveSettleBills(voaBill, settleEnv);
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
    StockSettleVO[] voaStockMerge =
        this.queryStocks(voInvoice,
            voInvoice.getChildrenVO()[0].getCsourcetypecode());
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4初始化结算环境
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.WITHOUT_INVOICE);

    // ------ 5库存、发票VO合并
    SettleBillVO[] voaBill =
        new WithoutInoiceMatchMerge(voaInvoiceMerge, voaStockMerge, null, null,
            settleEnv).merge();

    // ------ 6保存结算单
    new SettleBillBP().saveSettleBills(voaBill, settleEnv);
  }

}
