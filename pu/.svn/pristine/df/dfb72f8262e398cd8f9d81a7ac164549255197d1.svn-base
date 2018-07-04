/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午06:17:53
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
import nc.ui.uif2.model.AbstractAppModel;
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
 * <li>最终关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 下午06:17:53
 */
public class FinalAllCloseAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = -5214849177013546292L;

  private AbstractAppModel model = null;

  public FinalAllCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FINALCLOSE);
    // this.setBtnName("最终关闭");
    // this.setCode("finalAllClose");

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
        OrderCloseVO.getOrderVO(((OrderCloseManageModel) this.model)
            .getAllCloseRow().values().toArray(new OrderCloseVO[0]));

    OrderVO[] vostoclose = new OrderVO[vos.length];

    // 清除已经整单关闭的单据
    int nCount = 0;
    for (int n = 0; n < vos.length; n++) {
      if (!vos[n].getHVO().getBfinalclose().booleanValue()) {
        vostoclose[nCount++] = vos[n];
      }
    }

    OrderVO[] vos0 = new OrderVO[nCount];

    System.arraycopy(vostoclose, 0, vos0, 0, nCount);

    if (vos0.length == 0) {
      return;
    }

    PowerCheckUtils.checkHasPermission(vos0, POBillType.Order.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), OrderHeaderVO.VBILLCODE);

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos0, vos0);

    // 执行远程调用，进行最终关闭
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.FINAL_CLOSE.value()).intValue(), true);

    new ClientBillCombinServer<OrderVO>().combine(vos0, returnVos);

    // 更新关闭行
    Map<Integer, OrderCloseVO> mapAllClose =
        ((OrderCloseManageModel) this.model).getAllCloseRow();

    ((OrderCloseManageModel) this.model).directlyUpdate(mapAllClose.values()
        .toArray(new OrderCloseVO[0]));

  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(((OrderCloseManageModel) this.model)
        .getSelectedOperaDatas())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0002")/*
                                                                   * @res
                                                                   * "请先选择要整单最终关闭行"
                                                                   */);
    }
  }

  @Override
  // 按钮是否可以操作
  protected boolean isActionEnable() {

    // 是否有选中的行
    OrderCloseVO[] orderVOs =
        ((OrderCloseManageModel) this.model).getSelectedCloseVO();
    if (ArrayUtils.isEmpty(orderVOs)) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (!vo.getBfinalclose().booleanValue()) {
        return true;
      }
    }
    return false;
  }

}
