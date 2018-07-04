/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午06:19:42
 */
package nc.ui.pu.m21.action.orderclose.rowclose;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pu.m21.action.orderclose.AfterOrderCloseAction;
import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货行关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 下午06:19:42
 */
public class ArriveRowCloseAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = -8514791381881222548L;

  private OrderCloseManageModel model = null;

  public ArriveRowCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_ARRIVECLOSE);
    // this.setBtnName("到货关闭");
    // this.setCode("arriveRowClose");

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doBeforeAction();

    OrderVO[] vos = OrderCloseVO.getOrderVO(this.model.getSelectedCloseVO());

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // 执行远程调用,进行到货行关闭
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.ARRIVE_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // 到货关闭可能导致整单关闭,更新整单所有行
    Map<Integer, OrderCloseVO> mapAllClose = this.model.getAllCloseRow();

    this.model
        .directlyUpdate(mapAllClose.values().toArray(new OrderCloseVO[0]));

  }

  public OrderCloseManageModel getModel() {
    return this.model;
  }

  public void setModel(OrderCloseManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.model.getSelectedOperaDatas())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0012")/*
                                                                   * @res
                                                                   * "请先选择到货关闭行"
                                                                   */);
    }
  }

  @Override
  // 按钮是否可以操作
  protected boolean isActionEnable() {

    // 是否有选中的行
    OrderCloseVO[] orderVOs = this.model.getSelectedCloseVO();
    if (ArrayUtils.isEmpty(orderVOs)) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (!vo.getBarriveclose().booleanValue()) {
        return true;
      }
    }
    return false;
  }

}
