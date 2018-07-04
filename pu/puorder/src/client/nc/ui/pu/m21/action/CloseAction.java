/**
  * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 上午08:54:50
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
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
 * @author wuxla
 * @time 2010-4-1 上午08:54:50
 */
public class CloseAction extends NCAction {

  private static final long serialVersionUID = -7899940627753032910L;

  private AbstractAppModel model;

  public CloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLCLOSE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    OrderVO[] vos = this.getSelectedVO();
    PowerCheckUtils.checkHasPermission(vos, POBillType.Order.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), OrderHeaderVO.VBILLCODE);

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // 执行远程调用，进行最终关闭
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.FINAL_CLOSE.value()).intValue(), true);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0160", null,
					new String[] {returnVos.length+""}),/*
					   									 * @res
					   									 * "整单关闭操作成功，共关闭（）行！"
					   									 */ 
             this.model.getContext());
    this.model.directlyUpdate(vos);
  }

/**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * 方法功能描述：得到选择的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 下午01:39:34
   */
  private OrderVO[] getSelectedVO() {
    OrderVO[] vos = null;
    Object[] tempData = ((BillManageModel) this.model).getSelectedOperaDatas();
    if (!ArrayUtils.isEmpty(tempData)) {
      vos = new OrderVO[tempData.length];
      System.arraycopy(tempData, 0, vos, 0, tempData.length);
    }
    return vos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.model.getUiState()
        || UIState.EDIT == this.model.getUiState()) {
      return false;
    }
    OrderVO[] vos = this.getSelectedVO();
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }
    // 选择的订单中有一个为最终关闭，则返回false，不允许关闭
    for (OrderVO vo : vos) {
      if (null == vo || vo.getHVO().getBfinalclose().booleanValue()) {
        return false;
      }
      if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
          .intValue()) {
        return false;
      }
    }

    return true;
  }

}
