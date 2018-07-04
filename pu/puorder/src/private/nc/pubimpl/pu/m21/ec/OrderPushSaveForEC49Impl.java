package nc.pubimpl.pu.m21.ec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFBusiAction;
import nc.pubitf.pu.m21.ec.IOrderPushSaveForEC49;
import nc.pubitf.pu.m21.ec.PushSaveVO;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 排程结果到采购订单的推式保存接口
 * 
 * @since 6.0
 * @version 2012-11-6 下午04:28:45
 * @author lixyp
 */
public class OrderPushSaveForEC49Impl implements IOrderPushSaveForEC49 {

  @Override
  public PushSaveVO pushSave(PushSaveVO pushSaveVO) throws BusinessException {
    OrderVO[] orderVOs = pushSaveVO.getVos();
    OrderContext[] contexts = pushSaveVO.getContexts();

    try {
      this.logStart(orderVOs);

      // 按交易类型分组。
      MapList<String, OrderVO> orderMapList = new MapList<String, OrderVO>();
      MapList<String, OrderContext> contextMapList =
          new MapList<String, OrderContext>();
      OrderVO orderVO = null;
      for (int i = 0; i < orderVOs.length; i++) {
        orderVO = orderVOs[i];

        orderVO.getHVO().setStatus(VOStatus.NEW);
        String vtrantypecode = orderVO.getHVO().getVtrantypecode();
        if (vtrantypecode == null) {
          vtrantypecode = POBillType.Order.getCode();
        }
        orderMapList.put(vtrantypecode, orderVO);
        contextMapList.put(vtrantypecode, contexts[i]);
      }

      // 调用流程平台保存。
      IPFBusiAction busiAction =
          NCLocator.getInstance().lookup(IPFBusiAction.class);

      // 返回的订单VO集合。
      List<OrderVO> retOrders = new ArrayList<OrderVO>();
      // 返回的订单上下文集合。
      List<OrderContext> retContexts = new ArrayList<OrderContext>();
      // 输入的订单上下文集合与数组
      List<OrderContext> contextList = null;
      OrderContext[] contextArray = null;
      PfUserObject[] pfUserObjects = null;
      for (Entry<String, List<OrderVO>> entry : orderMapList.entrySet()) {
        List<OrderVO> value = entry.getValue();
        OrderVO[] newVOs = value.toArray(new OrderVO[value.size()]);
        contextList = contextMapList.get(entry.getKey());
        contextArray =
            contextList.toArray(new OrderContext[contextList.size()]);
        pfUserObjects = new PfUserObject[contextArray.length];
        for (int i = 0; i < contextArray.length; i++) {
          pfUserObjects[i] = new PfUserObject();
          pfUserObjects[i].setUserObject(contextArray[i]);
        }

        // 调用批量保存接口并收集返回结果。
        try {
          OrderVO[] retVos =
              (OrderVO[]) busiAction.processBatch("SAVEBASE", entry.getKey(),
                  newVOs, pfUserObjects, null, null);
          retOrders.addAll(Arrays.asList(retVos));
          retContexts.addAll(Arrays.asList(contextArray));
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }

      OrderVO[] retVos = retOrders.toArray(new OrderVO[retOrders.size()]);

      this.logEnd(retVos);
      return new PushSaveVO(retVos,
          retContexts.toArray(new OrderContext[retContexts.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 结束处日志
   * 
   * @param retVos 返回的订单VO数组
   */
  private void logEnd(OrderVO[] retVos) {
    StringBuilder info = new StringBuilder();
    for (OrderVO retVo : retVos) {
      info.append("vbillcode:" + retVo.getHVO().getVbillcode());
      info.append(",pk_order:" + retVo.getHVO().getPk_order());
    }
    Log.error("debug push size:" + retVos.length + " info:" + info.toString());
  }

  /**
   * 开始处日志
   * 
   * @param orderVOs 传入的订单VO数组
   */
  private void logStart(OrderVO[] orderVOs) {
    StringBuilder builder = new StringBuilder();
    builder.append("debug init size:" + orderVOs.length);
    for (OrderVO vo : orderVOs) {
      if (vo.getBVO() != null) {
        builder.append("item length:" + vo.getBVO().length);
      }
    }
    Log.debug("debug init:" + builder.toString());
  }
}
