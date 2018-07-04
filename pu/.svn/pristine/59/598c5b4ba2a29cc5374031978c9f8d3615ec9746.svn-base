package nc.impl.pu.m21.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pu.m21.action.rule.payplan.PayReqChkRule;
import nc.impl.pu.m21.action.rule.payplan.WriteBackPayPlanRule;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.event.IPOEventType;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

/**
 * 取消付款申请，查询
 * 
 * @since 6.0
 * @version 2011-1-24 下午01:33:44
 * @author wuxla
 */

public class PayPlanCancleToPayReqAction {

  public PayPlanViewVO[] canclePayReq(PayPlanViewVO[] views) {
    ViewTransferTool<PayPlanViewVO> tool =
        new ViewTransferTool<PayPlanViewVO>(views);
    PayPlanViewVO[] originViews = tool.getOriginViews();

    AroundProcesser<PayPlanViewVO> processer =
        new AroundProcesser<PayPlanViewVO>(
            OrderPluginPoint.PAYPLAN_CANCELPAYREQ);
    this.addRule(processer);

    processer.before(originViews);

    // 触发事件
    AggPayPlanVO[] vos = this.getVOs(originViews);
    try {
      String sourceid = PUMDValue.Order.value();
      EventDispatcher.fireEvent(new BusinessEvent(sourceid,
          IPOEventType.TYPE_CANCEL_PAYAPP_AFTER, vos));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    processer.after(originViews);
    return originViews;
  }

  private void addRule(AroundProcesser<PayPlanViewVO> processer) {
    // 基于合同、订单、入库、暂估应付、确认应付做的付款申请，禁止重复申请
    processer.addBeforeRule(new PayReqChkRule());
    processer.addAfterRule(new WriteBackPayPlanRule(UFBoolean.FALSE));
  }

  private AggPayPlanVO[] getVOs(PayPlanViewVO[] views) {
    List<OrderHeaderVO> headList = new ArrayList<OrderHeaderVO>();
    List<PayPlanVO> itemList = new ArrayList<PayPlanVO>();
    for (PayPlanViewVO view : views) {
      headList.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
      itemList.add((PayPlanVO) view.getVO(PayPlanVO.class));
    }

    BillComposite<AggPayPlanVO> bc =
        new BillComposite<AggPayPlanVO>(AggPayPlanVO.class);
    AggPayPlanVO tempVO = new AggPayPlanVO();
    bc.append(tempVO.getMetaData().getParent(),
        headList.toArray(new OrderHeaderVO[headList.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(PayPlanVO.class),
        itemList.toArray(new PayPlanVO[itemList.size()]));
    AggPayPlanVO[] vos = bc.composite();
    return vos;
  }

}
