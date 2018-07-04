/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午06:58:49
 */
package nc.ui.pu.m21.action.orderclose.allclose;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pu.m21.action.orderclose.AfterOrderCloseAction;
import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库整单关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 下午06:58:49
 */
public class StoreAllCloseAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = -7012499258444905688L;

  private OrderCloseManageModel model = null;

  public StoreAllCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_STORECLOSE);
    // this.setBtnName("入库关闭");
    // this.setCode("inAllClose");

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 检查有没有选择数据行
    this.doBeforeAction();

    OrderVO[] vos =
        OrderCloseVO.getOrderVO(this.model.getAllCloseRow().values()
            .toArray(new OrderCloseVO[0]));

    PowerCheckUtils.checkHasPermission(vos, POBillType.Order.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), OrderHeaderVO.VBILLCODE);

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // 执行远程调用，进行入库关闭
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.STOCK_CLOSE.value()).intValue(), true);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // 更新关闭行
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
          .getNCLangRes().getStrByID("4004030_0", "04004030-0005")/*
                                                                   * @res
                                                                   * "请先选择要整单入库关闭行"
                                                                   */);
    }
  }

  @Override
  // 按钮是否可以操作
  protected boolean isActionEnable() {

    // 是否有选中的行
    Map<Integer, OrderCloseVO> orderCloseMap = this.model.getAllCloseRow();

    OrderCloseVO[] orderVOs = null;

    if (orderCloseMap != null) {
      orderVOs = orderCloseMap.values().toArray(new OrderCloseVO[0]);
    }

    if (null == orderVOs || 0 == orderVOs.length) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (!vo.getBstockclose().booleanValue()) {
        return true;
      }
    }
    return false;
  }

}
