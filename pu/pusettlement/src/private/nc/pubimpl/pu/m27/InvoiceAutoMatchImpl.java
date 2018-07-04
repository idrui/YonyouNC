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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ�Զ������ʵ����
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since V6.0
 * @author wangyf
 * @time 2009-7-6 ����09:46:09
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
  private StockSettleVO[] queryStocks(InvoiceVO voInvoice, String sSrcBillType)
      throws BusinessException {
    HashSet<String> hsetSrcBid =
        CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
            InvoiceItemVO.CSOURCEBID);
    StockSettleVO[] voaStockMerge = null;

    // -------------ί�ⶩ�����ɹ������Զ�����
    if (POBillType.Order.getCode().equals(sSrcBillType)
        && InvoiceVOUtil.isAutoSettleFromOrder(voInvoice)) {// �������PO46�������Զ�����
      // ��浥�������ǲɹ���
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
        && InvoiceVOUtil.isAutoSettleFromOrder(voInvoice)) {// �������PO46�������Զ�����
      // ��浥��������ί����
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.querySubcontractByOrderBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    // -------------����Զ�����
    else if (ICBillType.PurchaseIn.getCode().equals(sSrcBillType)) {
      // ��浥�������ǲɹ���
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.queryPurchaseInByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    else if (POBillType.InitEstimate.getCode().equals(sSrcBillType)) {
      // �ڳ��ݹ���
      IInitialEstSettleQuery itfInitial =
          NCLocator.getInstance().lookup(IInitialEstSettleQuery.class);
      voaStockMerge =
          itfInitial
              .queryByBID(hsetSrcBid.toArray(new String[hsetSrcBid.size()]));
    }
    else if (ICBillType.SubContinIn.getCode().equals(sSrcBillType)) {
      // ��浥��������ί����
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.querySubcontractByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }
    else if (ICBillType.VmiSum.getCode().equals(sSrcBillType)) {
      // ��浥��������VMI
      IStockFinanceQuery itfStockSettleQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      voaStockMerge =
          itfStockSettleQuery.queryVoiconsumeByBID(hsetSrcBid
              .toArray(new String[hsetSrcBid.size()]));
    }

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
    IInvoiceSettleQuery iInvoiceQuery =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    InvoiceQueryResultFor27 allMrgInvc =
        iInvoiceQuery.queryGoodsAndRelaFeeByHID(voInvoice.getParentVO()
            .getPk_invoice(), InvoiceSettleVOUtil.getQueryType(voInvoice,
            IInvoiceSettleQuery.QRY_TYPE_PO));
    if (null == allMrgInvc || ArrayUtils.isEmpty(allMrgInvc.getGoodsInvoices())) {
      return;
    }

    // ------ 3��ѯ��浥��
    StockSettleVO[] voaStockMerge = null;
    boolean bDTrans = false;
    boolean bStockInFlow = true;
    {
      // ��ѯ�Ƿ�ֱ�ˡ�ֱ���������з��浥��
      {
        // �õ������Ľ�������
        HashSet<String> hsetOrderType =
            CirVOUtil.getDistinctFieldSet(voInvoice.getChildrenVO(),
                InvoiceItemVO.VORDERTRANTYPE);
        String sOrderType =
            hsetOrderType == null || hsetOrderType.size() == 0 ? null
                : hsetOrderType.iterator().next();

        // ֻ��ɹ�����
        if (!PubAppTool.isNull(sOrderType)
            && POBillType.Order.getCode().equals(
                PfUtilBaseTools.getRealBilltypeByPK(sOrderType))) {
          IPoTransTypeQuery queryPoType =
              NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
          Map<String, PoTransTypeVO> hmapPoType =
              queryPoType.queryAttrByIDs(new String[]{sOrderType});
          bDTrans = hmapPoType.get(sOrderType).getBdirect().booleanValue();

          // �����ֱ�ˣ����������޿�浥��
          if (bDTrans) {
            // �Ƿ�ֱ�ˡ����޲ɹ���⡢��Դ�������͡�
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
      // ֱ��������������ⵥʱ�����ò��浥�ݣ����������п�浥��
      if (!(bDTrans && !bStockInFlow)) {
        String srcBilltype = "";
        Iterator<String> iterator = hsetSrcBillType.iterator();
        while (iterator.hasNext()) {
          srcBilltype = iterator.next();
          // ����ȡ����������ͣ���Դ�����Ĳ�����
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

    // ------ 4��ʼ�����㻷��
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.INVOICE_AUTO);

    // ------ 5��桢��ƱVO�ϲ�
    FeeDiscountSettleVO[] vosFee = allMrgInvc.getFeeInvoices();
    FeeDiscountSettleVO[] vosdiscount = allMrgInvc.getDiscountInvoices();
    InvoiceSettleVO[] goodsInvcs = allMrgInvc.getGoodsInvoices();
    SettleBillVO[] voaBill = null;
    // ����ⵥ��ֱ������ⵥҪ���ֿ�
    if (bDTrans && !bStockInFlow) {
      voaBill =
          new InvoiceAutoMatchDTransNoStockMerge(goodsInvcs, null, vosFee,
              vosdiscount, settleEnv).merge();
    }
    else {
      // ���Ʋ�������ȡ���ݹ�
      ViewConcurrentUtil.getInstance().concurrentCheck(voaStockMerge);
      voaBill =
          new InvoiceAutoMatchWithStockMerge(goodsInvcs, voaStockMerge, vosFee,
              vosdiscount, settleEnv).merge();
    }

    // ------ 6������㵥
    new SettleBillBP().saveSettleBills(voaBill, settleEnv);
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
    StockSettleVO[] voaStockMerge =
        this.queryStocks(voInvoice,
            voInvoice.getChildrenVO()[0].getCsourcetypecode());
    if (voaStockMerge == null) {
      return;
    }

    // ------ 4��ʼ�����㻷��
    String pk_org = voInvoice.getParentVO().getPk_org();
    SettleEnvironment settleEnv =
        new SettleEnvironment(pk_org, EnumSettleType.WITHOUT_INVOICE);

    // ------ 5��桢��ƱVO�ϲ�
    SettleBillVO[] voaBill =
        new WithoutInoiceMatchMerge(voaInvoiceMerge, voaStockMerge, null, null,
            settleEnv).merge();

    // ------ 6������㵥
    new SettleBillBP().saveSettleBills(voaBill, settleEnv);
  }

}
