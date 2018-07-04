/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 上午08:54:49
 */
package nc.pubimpl.pu.m21.arap.mf3.handler;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>增加前校验：存在付款关闭的行，不能付款！ 该类已经没有用了，回写时检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-13 上午08:54:49
 */
public class OrderWriteBackForF3InsertBeforeHandler implements
    IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {

    try {
      // 删除该类，回写时检查
      boolean isPOEnabled = SysInitGroupQuery.isPOEnabled();
      if (!isPOEnabled) {
        return;
      }

      if (IEventType.TYPE_INSERT_BEFORE.equals(event.getEventType())) {
        BusinessEvent e = (BusinessEvent) event;
        Object value = e.getObject();
        if (null == value) {
          return;
        }

        OrderWriteBackForF3Util util = OrderWriteBackForF3Util.getInstance();
        PayBillItemVO[] payBillItems = util.getPayBillItems(value);
        if (ArrayUtils.isEmpty(payBillItems)) {
          return;
        }

        OrderItemVO[] itemVOs = util.getOrderItemVOs(payBillItems);

        if (ArrayUtils.isEmpty(itemVOs)) {
          return;
        }

        for (OrderItemVO itemVO : itemVOs) {
          if (itemVO.getBpayclose().booleanValue()) {
            ExceptionUtils.marsh(new BusinessException(
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004030_0", "04004030-0172")/* @res "存在付款关闭的行，不能付款！" */));
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
