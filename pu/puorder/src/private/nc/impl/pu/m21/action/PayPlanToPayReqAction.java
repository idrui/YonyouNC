package nc.impl.pu.m21.action;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pu.m21.action.rule.payplan.PayPlanStatusChkRule;
import nc.impl.pu.m21.action.rule.payplan.WriteBackPayPlanRule;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.scmf.payplan.rule.PayAppEndDateRule;
import nc.impl.scmf.payplan.rule.PayAppFollowUpBillChkRule;
import nc.itf.scmpub.reference.ps.PsServicesUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.pub.IApplyConst;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.rule.OrderPayChkRule;
import nc.vo.pu.m21.rule.OrderPayCloseChkRule;
import nc.vo.pu.m21.rule.OrderPayFreezeChkRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ���ɸ�������
 * 
 * @since 6.0
 * @version 2011-1-20 ����08:18:41
 * @author wuxla
 */

public class PayPlanToPayReqAction {

  public PayPlanViewVO[] toPayReq(PayPlanViewVO[] views) {
    ViewTransferTool<PayPlanViewVO> tool =
        new ViewTransferTool<PayPlanViewVO>(views);
    PayPlanViewVO[] originViews = tool.getOriginViews();

    AroundProcesser<PayPlanViewVO> processer =
        new AroundProcesser<PayPlanViewVO>(OrderPluginPoint.PAYPLAN_PAYREQ);
    this.addRule(processer);

    processer.before(originViews);
    this.toPayreq(originViews);
    originViews = processer.after(originViews);
    return originViews;
  }

  private void addRule(AroundProcesser<PayPlanViewVO> processer) {
    // ���ں�ͬ����������⡢�ݹ�Ӧ����ȷ��Ӧ�����ĸ������룬��ֹ�ظ�����
    processer.addBeforeRule(new PayPlanStatusChkRule());
    processer.addBeforeRule(new PayAppFollowUpBillChkRule<PayPlanViewVO>());
    processer.addBeforeRule(new OrderPayCloseChkRule());
    processer.addBeforeRule(new OrderPayFreezeChkRule());
    processer.addBeforeRule(new PayAppEndDateRule<PayPlanViewVO>());
    processer.addBeforeRule(new OrderPayChkRule());// ��������Ӧ�����Ƿ���������򸶿�����
    processer.addAfterRule(new WriteBackPayPlanRule(UFBoolean.TRUE));
  }

  private AggPayPlanVO[] getVOs(PayPlanViewVO[] views) {
    // List<OrderHeaderVO> headList = new ArrayList<OrderHeaderVO>();
    // List<PayPlanVO> itemList = new ArrayList<PayPlanVO>();
    // for (PayPlanViewVO view : views) {
    // headList.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
    // itemList.add((PayPlanVO) view.getVO(PayPlanVO.class));
    // }
    //
    // BillComposite<AggPayPlanVO> bc =
    // new BillComposite<AggPayPlanVO>(AggPayPlanVO.class);
    // AggPayPlanVO tempVO = new AggPayPlanVO();
    // bc.append(tempVO.getMetaData().getParent(),
    // headList.toArray(new OrderHeaderVO[headList.size()]));
    // bc.append(tempVO.getMetaData().getVOMeta(PayPlanVO.class),
    // itemList.toArray(new PayPlanVO[itemList.size()]));
    AggPayPlanVO[] vos = new AggPayPlanVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      vos[i] = new AggPayPlanVO();
      vos[i].setHVO((OrderHeaderVO) views[i].getVO(OrderHeaderVO.class));
      vos[i].setPayPlanVO(new PayPlanVO[] {
        (PayPlanVO) views[i].getVO(PayPlanVO.class)
      });
    }
    return vos;
  }

  private void toPayreq(PayPlanViewVO[] views) {
    AggPayPlanVO[] originVOs = this.getVOs(views);
    AggApplyVO[] appVOs =
        PfServiceScmUtil.exeVOChangeByBillItfDef(POBillType.Order.getCode(),
            IApplyConst.CMP_36D1, originVOs);

    appVOs = PsServicesUtil.saveFromSCM(appVOs);
    if (null == appVOs) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0168")/*
                                                                   * @res
                                                                   * "���ɸ�������ʧ��"
                                                                   */);
    }
  }
}
