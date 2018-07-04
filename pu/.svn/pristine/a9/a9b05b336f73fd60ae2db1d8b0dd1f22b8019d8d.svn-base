package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.List;
import java.util.Map;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.cmp.m36d1.OrderPayPlanWriteBackParaFor36D1;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单付款计划给资金付款申请单提供的删除后事件回写监听处理类
 * 
 * @since 6.3.1
 * @version 2013-10-10 上午10:51:26
 * @author fanly3
 */
public class OrderPayPlanWriteBackFor36D1DeleteHandler implements
    IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (!SysInitGroupQuery.isPOEnabled()) {
      return;
    }
    // 删除后
    if (IEventType.TYPE_DELETE_AFTER.equals(event.getEventType())) {
      BusinessEvent e = (BusinessEvent) event;
      Object value = e.getObject();
      if (null == value) {
        return;
      }

      // 调用回写工具回写
      OrderPayPlanWriteBackFor36D1Util util =
          OrderPayPlanWriteBackFor36D1Util.getInstance();
      AggApplyVO[] aggVos = util.convertObjectToAggVos(value);
      AggApplyVO[] afterFilterAggVos = util.getFilterAggVos(aggVos);

      if (ArrayUtils.isEmpty(afterFilterAggVos)) {
        return;
      }
      // 构造删除付款申请单时的回写参数
      Map<String, List<RewritePara>> rwParaMap =
          util.getRewritePara(null, afterFilterAggVos);
      if (rwParaMap == null) {
        return;
      }
      List<RewritePara> paraList = rwParaMap.get(POBillType.Order.getCode());
      if (paraList == null || paraList.size() == 0) {
        return;
      }
      OrderPayPlanWriteBackParaFor36D1[] backVOs =
          util.getWritebackVO(paraList);
      util.writeBack(backVOs);
    }
  }

}
