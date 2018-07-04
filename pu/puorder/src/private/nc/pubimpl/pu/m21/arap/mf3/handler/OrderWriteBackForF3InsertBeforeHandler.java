/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����08:54:49
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ǰУ�飺���ڸ���رյ��У����ܸ�� �����Ѿ�û�����ˣ���дʱ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-13 ����08:54:49
 */
public class OrderWriteBackForF3InsertBeforeHandler implements
    IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {

    try {
      // ɾ�����࣬��дʱ���
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
                    "4004030_0", "04004030-0172")/* @res "���ڸ���رյ��У����ܸ��" */));
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
