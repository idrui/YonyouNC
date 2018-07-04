package nc.bs.pu.m21.state;

import nc.impl.pubapp.env.BSContext;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.lang.UFBoolean;

public class OrderCloseStateUtil {

  private static OrderCloseStateUtil instance = new OrderCloseStateUtil();

  // 即时关闭
  // private boolean bInstanceClose = false;

  private OrderCloseStateUtil() {
    // 私有
  }

  public static OrderCloseStateUtil getInstance() {
    return OrderCloseStateUtil.instance;
  }

  /**
   * @return bInstanceClose
   */
  public boolean isbInstanceClose() {
    UFBoolean binstance =
        (UFBoolean) BSContext.getInstance().getSession(
            OrderCloseStateUtil.class.getName());
    return binstance != null && binstance.booleanValue();
    // return this.bInstanceClose;
  }

  public boolean isFinalClosable(OrderVO vo) {
    // 如果不是即时关闭，即定时关闭，不需要整单关闭
    if (!this.isbInstanceClose()) {
      return false;
    }
    // 如果是即时关闭，判断所有表体的状态，如果全部关闭，则整单关闭
    for (OrderItemVO item : vo.getBVO()) {
      if (!item.getFisactive().equals(EnumActive.CLOSE.value())) {
        return false;
      }
    }
    return true;
  }

  public boolean isOpenable(OrderVO vo) {
    if (null == vo) {
      return false;
    }
    return true;
  }

  public boolean isRowClosable(OrderCloseVO vo) {
    if (vo.getBfinalclose().booleanValue()) {
      return true;
    }
    return vo.getBarriveclose().booleanValue()
        && vo.getBstockclose().booleanValue()
        && vo.getBinvoiceclose().booleanValue()
        && vo.getBpayclose().booleanValue();
  }

  public boolean isRowOpenable(OrderCloseVO vo) {
    if (null == vo) {
      return false;
    }
    return true;
  }

  /**
   * @param bInstanceClose
   *          要设置的 bInstanceClose
   */
  public void setbInstanceClose(boolean bInstanceClose) {
    BSContext.getInstance().setSession(OrderCloseStateUtil.class.getName(),
        UFBoolean.valueOf(bInstanceClose));
    // this.bInstanceClose = bInstanceClose;
  }
}
