package nc.impl.pu.m21.action;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.ApprovingEditCheckRule;
import nc.vo.pu.pub.rule.ApprovingEditSendMsgRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.TimeUtils;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.bs.pu.m21.plugin.OrderPluginPoint;

import nc.impl.pu.m21.action.rule.approve.FilterOrderByStatusRule;
import nc.impl.pu.m21.action.rule.approve.OrderPriceMaintainRule;
import nc.impl.pu.m21.action.rule.revise.BrefwhenreturnCheckRule;
import nc.impl.pu.m21.action.rule.revise.ItemChangeCheckRule;
import nc.impl.pu.m21.action.rule.revise.ItemRule;
import nc.impl.pu.m21.action.rule.revise.ModifyStatusOnWayRule;
import nc.impl.pu.m21.action.rule.revise.ModifySupplyRule;
import nc.impl.pu.m21.action.rule.revise.OneContractCheckRule;
import nc.impl.pu.m21.action.rule.revise.ReviseBudgetCtrlRule;
import nc.impl.pu.m21.action.rule.revise.ReviseNumCheckRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������޶�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-16 ����09:44:47
 */
public class OrderReviseAction {

  /**
   * ���������������ɹ������޶�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-16 ����09:50:28
   */
  public OrderVO[] revise(OrderVO[] orderVos, OrderContext ctx) {
    if (ArrayUtils.isEmpty(orderVos)) {
      return null;
    }

    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] voSaved = tool.getClientFullInfoBill();
    OrderVO[] voOrginal = tool.getOriginBills();

    // ִ�����°�Ĳɹ���������
    OrderVO[] voRevised = this.saveNewVO(voSaved, voOrginal, ctx);

    // ������ʷ��¼
    this.setOrignialVO(voOrginal, voSaved);

    // ִ����ʷ��������
    this.saveHistoryVO(voOrginal);

    return tool.getBillForToClient(voRevised);

  }

  /**
   * �����������������þ�VO��ֵ��ʹ������ʷ��¼��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param sReviseUserId
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-17 ����07:02:21
   */
  public void setOrignialVO(OrderVO[] voOrginal, OrderVO[] voSaved) {
    if (ArrayUtils.isEmpty(voOrginal)) {
      return;
    }

    UFDate correctdate = TimeUtils.getsrvBaseDate();
    for (int i = 0; i < voOrginal.length; ++i) {
      // ͷ
      OrderHeaderVO voHead = voOrginal[i].getHVO();
      voHead.setStatus(VOStatus.NEW);
      voHead.setPk_order(null);
      voHead.setBislatest(UFBoolean.FALSE);
      voHead.setTs(null);

      // ��
      OrderItemVO[] voItems = voOrginal[i].getBVO();
      for (int j = 0; j < voItems.length; ++j) {
        voItems[j].setStatus(VOStatus.NEW);
        voItems[j].setPk_srcorder_b(voItems[j].getPk_order_b());
        voItems[j].setPk_order_b(null);
        voItems[j].setPk_order(null);
        voItems[j].setDcorrectdate(correctdate);
        voItems[j].setChandler(voSaved[i].getHVO().getCrevisepsn());
        voItems[j].setFisactive((Integer) EnumActive.REVISEHISTORY.value());
        voItems[j].setTs(null);
      }
      /*
       * add by wandl ��ո���Э���������޶����и���Э��Ķ����ᱨΥ��ΨһԼ����
       */
      OrderPaymentVO[] paymentItem = voOrginal[i].getPaymentVO();
      for (OrderPaymentVO payment : paymentItem) {
        payment.setPk_payment(null);
      }
    }
  }

  /**
   * ����������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 ����06:41:30
   */
  private void addAfterRule(CompareAroundProcesser<OrderVO> processer,
      OrderContext ctx) {
    // �޶������Ϸ��Լ�飬�޶����������ԭ����ͬ�������Ҳ���С�ں�������
    processer.addAfterRule(new ReviseNumCheckRule(ctx
        .getReviseToleranceConfirm().booleanValue()));
    // ֻ��������������ݣ����������޶�����δ����ͨ�������ݹ�����
    processer.addAfterRule(new FilterOrderByStatusRule(POEnumBillStatus.APPROVE
        .toInt()));
    processer.addAfterRule(new ModifySupplyRule());
    processer.addAfterRule(new ReviseBudgetCtrlRule());
    // �޸���;״̬��������
    processer.addAfterRule(new ModifyStatusOnWayRule());
    // ���¼۸��
    processer.addAfterRule(new OrderPriceMaintainRule());
  }

  /**
   * ������������������ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 ����06:41:24
   */
  private void addBeforeRule(CompareAroundProcesser<OrderVO> processer) {

    // �����Ϸ��Լ��
    processer.addBeforeRule(new ItemRule());

    // ��ͷ�˻�(��)����ԭ����������飬�к������ݲ������޸��˻�/�����ԭ���������ֶ�
    processer.addBeforeRule(new BrefwhenreturnCheckRule());

    // ���岻�ܸı������к������ݣ��������κš������š���Ŀ���ջ��ֿ⡢�ջ���ַ����Ʒ�����޸�
    processer.addBeforeRule(new ItemChangeCheckRule());

    // ��ͬ��飬�޶�ǰ��������ͬһ����ͬ�����ܽ�������Ϊ���Զ����ͬ�Ķ���
    processer.addBeforeRule(new OneContractCheckRule());

    // ֧���������޸ģ��޶����ĵ��ݣ�����Ƿ�Ӧ���ɵ�ǰ�������޸ģ��޶���
    processer.addBeforeFinalRule(new ApprovingEditCheckRule<OrderVO>(
        POBillType.Order));

    // ֧���������޸ģ��޶����ĵ��ݣ���̨�������Ѿ����������������˷�����Ϣ
    processer.addBeforeFinalRule(new ApprovingEditSendMsgRule<OrderVO>());

  }

  /**
   * ����������������ʷ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 ����06:43:11
   */
  private void saveHistoryVO(OrderVO[] voOrginal) {
    if (!ArrayUtils.isEmpty(voOrginal)) {
      for (OrderVO vo : voOrginal) {
        vo.setChildren(PayPlanVO.class, null);
      }
      new BillInsert<OrderVO>().insert(voOrginal);
    }
  }

  /**
   * �����������������°�Ĳɹ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param voSaved
   * @param voOrginal
   * @param ctx
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 ����06:45:12
   */
  private OrderVO[] saveNewVO(OrderVO[] voSaved, OrderVO[] voOrginal,
      OrderContext ctx) {
    if (ArrayUtils.isEmpty(voSaved)) {
      return null;
    }
    // ���⴦��
    // ǰ̨���촦��ʱ����Ϊ����û�и���ƻ������Խ�������Ϊɾ��
    // ���óɲ���
    this.setPayPlanStatus(voSaved);
    // ����
    CompareAroundProcesser<OrderVO> processer =
        new CompareAroundProcesser<OrderVO>(OrderPluginPoint.REVISE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, ctx);

    // ִ�����°�Ĳɹ���������
    processer.before(voSaved, voOrginal);
    OrderVO[] retVOs = new OrderSaveBP(ctx).save(voSaved, voOrginal);

    processer.after(retVOs, voOrginal);

    return retVOs;
  }

  private void setPayPlanStatus(OrderVO[] voSaved) {

    for (OrderVO vo : voSaved) {
      ISuperVO[] vos = vo.getChildren(PayPlanVO.class);
      if (ArrayUtils.isEmpty(vos)) {
        continue;
      }
      PayPlanVO[] payplanVOs = ArrayUtil.convertArrayType(vos);
      for (PayPlanVO payplanVO : payplanVOs) {
        payplanVO.setStatus(VOStatus.UNCHANGED);
      }
    }
  }

}
