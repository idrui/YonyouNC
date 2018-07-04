/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-2 下午04:17:24
 */
package nc.pubimpl.pu.m21.arap.mf3.handler;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.pu.m21.writeback.arap.OrderWriteBackForF3BP;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除付款单，采购订单响应事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-2 下午04:17:24
 */
public class OrderWriteBackForF3DeleteHandler implements IBusinessListener {

  /**
   * 父类方法重写
   * 
   * @see nc.bs.businessevent.IBusinessListener#doAction(nc.bs.businessevent.IBusinessEvent)
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_DELETE_AFTER.equals(event.getEventType())) {
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

        this.writebackForF3(payBillItems);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 方法功能描述：回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param payBillItems
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午04:19:58
   */
  private void writebackForF3(PayBillItemVO[] payBillItems) {
    IOrderWriteBackParaForF3[] paras =
        new IOrderWriteBackParaForF3[payBillItems.length];
    for (int i = 0; i < payBillItems.length; ++i) {
      final PayBillItemVO payBillItem = payBillItems[i];
      paras[i] = new IOrderWriteBackParaForF3() {

        @Override
        public String getBID() {
          return payBillItem.getSrc_itemid();
        }

        @Override
        public UFDate getBilldate() {
          return payBillItem.getBilldate();
        }

        @Override
        public String getCurrency() {
          return payBillItem.getPk_currtype();
        }

        @Override
        public UFDouble getDiffNum() {
          return null;
        }

        @Override
        public UFDouble getDiffPaymny() {
          return MathTool.oppose(payBillItem.getLocal_money_de());
        }

        @Override
        public UFDouble getDiffPayorgmny() {
          return MathTool.oppose(payBillItem.getMoney_de());
        }

        @Override
        public String getHID() {
          return payBillItem.getSrc_billid();
        }
      };
    }

    new OrderWriteBackForF3BP().writeback(paras);
  }

}
