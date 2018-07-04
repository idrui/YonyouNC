/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 上午09:43:53
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>行打开
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-1 上午09:43:53
 */
public class RowOpenAction extends NCAction {

  private static final long serialVersionUID = 8482334456022240082L;

  private BillForm billForm;

  private BillListView list;

  private int[] selectIndex = null;


  public RowOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINEOPEN);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getSelectIndex();

    this.doBeforeAction();

    OrderVO vo = this.getSelectedVO();

    if (null == vo) {
      return;
    }

    OrderVO oldVO = this.getOldVO();

    // 差异VO处理
    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(new OrderVO[] {
      vo
    }, new OrderVO[] {
      vo
    });

    // 执行远程调用，进行行打开
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.open(lightVOs,
            ((Integer) EnumCloseFlag.ROW_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(new OrderVO[] {
      oldVO
    }, returnVos);


    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0162", null,
					new String[] {this.selectIndex.length + ""}), /*
					   											   * @res
					   											   * "行打开操作成功，共打开（）行！"
					   											   */
             this.billForm.getModel().getContext());
    
    this.getAppModel().directlyUpdate(oldVO);
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  /**
   * 方法功能描述：判断有没有选择行
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 上午10:31:29
   */
  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0065")/*
                                                                   * @res
                                                                   * "请先选择行"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：得到model
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 下午04:16:31
   */
  private AbstractAppModel getAppModel() {
    return this.billForm.getModel();
  }

  /**
   * 方法功能描述：得到model中的旧VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 下午04:28:59
   */
  private OrderVO getOldVO() {
    OrderVO vo = (OrderVO) this.getAppModel().getSelectedData();
    return vo;
  }

  /**
   * 方法功能描述：如果是卡片界面，从卡片获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 下午12:24:04
   */
  private OrderVO getOpenVOFromBillForm() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据VO
    OrderVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    OrderVO vo = (OrderVO) oldVO.clone();
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] itemVOs = new OrderItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.billForm.getBillCardPanel().getBillModel()
              .getValueAt(this.selectIndex[i], OrderItemVO.PK_ORDER_B);
      // OrderItemVO itemVO = itemMap.get(pk_order_b);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pk_order_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;

  }

  /**
   * 方法功能描述：如果是列表界面，从列表获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 下午03:42:55
   */
  private OrderVO getOpenVOFromListView() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据的VO
    OrderVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    OrderVO vo = (OrderVO) oldVO.clone();
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] itemVOs = new OrderItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.list.getBillListPanel().getBodyBillModel()
              .getValueAt(this.selectIndex[i], OrderItemVO.PK_ORDER_B);
      // OrderItemVO itemVO = itemMap.get(pk_order_b);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pk_order_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;
  }

  /**
   * 方法功能描述：得到Panel
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 上午10:21:05
   */
  private UIPanel getPanel() {
    UIPanel panel = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      panel = this.billForm.getBillCardPanel();
    }
    else {// 列表界面
      panel = this.list.getBillListPanel();
    }
    return panel;
  }

  /**
   * 方法功能描述：得到选择数据的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 下午01:49:33
   */
  private OrderVO getSelectedVO() {
    OrderVO vo = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getOpenVOFromBillForm();
    }
    else {// 列表界面
      vo = this.getOpenVOFromListView();
    }

    return vo;
  }

  /**
   * 方法功能描述：得到选择的行
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 上午10:23:23
   */
  private void getSelectIndex() {
    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {// 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      this.selectIndex = panel.getBodyTable().getSelectedRows();
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.billForm.getModel().getUiState()
        || UIState.EDIT == this.billForm.getModel().getUiState()) {
      return false;
    }
    this.getSelectIndex();

    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return false;
    }

    OrderVO vo = this.getSelectedVO();

    if (null == vo) {
      return false;
    }
    if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
        .intValue()) {
      return false;
    }
    // 如果选择的行有一个为激活状态，则返回false，不允许打开
    for (OrderItemVO itemVO : vo.getBVO()) {
      if (itemVO != null
          && itemVO.getFisactive().equals(EnumActive.ACTIVE.value())) {
        return false;
      }
    }

    return true;
  }

}
