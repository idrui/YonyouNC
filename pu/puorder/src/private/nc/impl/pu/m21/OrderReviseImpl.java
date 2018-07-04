package nc.impl.pu.m21;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.pub.VOQryUtil;
import nc.impl.pu.m21.action.OrderReviseAction;
import nc.itf.pu.m21.IOrderRevise;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-15 上午10:52:30
 */
public class OrderReviseImpl implements IOrderRevise {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderRevise#reviseSave(nc.vo.pu.m21.entity.OrderVO[],
   *      nc.vo.pu.m21.context.OrderContext[])
   */
  @Override
  public OrderVO[] reviseSave(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException {
    OrderContext ctx = ArrayUtils.isEmpty(ctxs) ? new OrderContext() : ctxs[0];
    Set<String> set = new HashSet<String>();
    for(OrderVO ordervo : orderVos){
    	set.add(ordervo.getPrimaryKey());
    }
    String[] ids = set.toArray(new String[0]);
    VOQryUtil<ISuperVO> voqry = new VOQryUtil<ISuperVO>(OrderPaymentVO.class);
    OrderPaymentVO[] paymentvo = (OrderPaymentVO[]) voqry.qryBySpecField(OrderPaymentVO.PK_ORDER,ids);
    for(OrderVO ordervo : orderVos){
    	ordervo.setTableVO("pk_payment", paymentvo);
    }
    try {
      return new OrderReviseAction().revise(orderVos, ctx);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
