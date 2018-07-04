package nc.pubimpl.pu.m21.ec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFBusiAction;
import nc.pubitf.pu.m21.ec.IOrderPushSaveForEC38;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 补货申请推式保存采购订单实现类
 * 
 * @since 6.0
 * @version 2012-11-12 下午06:26:24
 * @author lixyp
 */
public class OrderPushSaveForEC38Impl implements IOrderPushSaveForEC38 {

  @Override
  public OrderVO[] pushSave(OrderVO[] orderVOs) throws BusinessException {
    try {
      StringBuilder builder = new StringBuilder();
      builder.append("debug init size:" + orderVOs.length);
      for (OrderVO vo : orderVOs) {
        if (vo.getBVO() != null) {
          builder.append("item length:" + vo.getBVO().length);
        }
      }
      Log.error("debug init:" + builder.toString());

      MapList<String, OrderVO> mapList = new MapList<String, OrderVO>();
      for (OrderVO orderVO : orderVOs) {
        orderVO.getHVO().setStatus(VOStatus.NEW);
        String vtrantypecode = orderVO.getHVO().getVtrantypecode();
        if (vtrantypecode == null) {
          vtrantypecode = POBillType.Order.getCode();
        }
        mapList.put(vtrantypecode, orderVO);
      }

      IPFBusiAction busiAction =
          NCLocator.getInstance().lookup(IPFBusiAction.class);

      List<OrderVO> orderList = new ArrayList<OrderVO>();
      for (Entry<String, List<OrderVO>> entry : mapList.entrySet()) {
        String vtrantypecode = entry.getKey();
        List<OrderVO> value = entry.getValue();
        OrderVO[] newVOs = value.toArray(new OrderVO[value.size()]);
        try {
          OrderVO[] retVos =
              (OrderVO[]) busiAction.processBatch("SAVEBASE", vtrantypecode,
                  newVOs, null, null, null);
          orderList.addAll(Arrays.asList(retVos));
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }

      OrderVO[] retVos = orderList.toArray(new OrderVO[orderList.size()]);

      StringBuilder info = new StringBuilder();
      for (OrderVO retVo : retVos) {
        info.append("vbillcode:" + retVo.getHVO().getVbillcode());
        info.append(",pk_order:" + retVo.getHVO().getPk_order());
      }
      Log.error("debug push size:" + retVos.length + " info:" + info.toString());
      return retVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
