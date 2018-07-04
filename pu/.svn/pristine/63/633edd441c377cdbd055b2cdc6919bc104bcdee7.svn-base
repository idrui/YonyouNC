/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:42:49
 */
package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m422x.IStoreReqAppClose;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
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
 * <li>行关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:42:49
 */
public class RowCloseAction extends NCAction {

  private static final long serialVersionUID = 8936240317633605531L;

  private BillForm billForm;

  private BillListView list;

  private int[] selectIndex;

  public RowCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINECLOSE);
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

    StoreReqAppVO vo = this.getSelectedVO();

    if (null == vo) {
      return;
    }

    StoreReqAppVO oldVO = this.getOldVO();

    // 差异VO处理
    ClientBillToServer<StoreReqAppVO> tool =
        new ClientBillToServer<StoreReqAppVO>();
    StoreReqAppVO[] lightVOs = tool.construct(new StoreReqAppVO[] {
      vo
    }, new StoreReqAppVO[] {
      vo
    });

    // 执行远程调用，进行行关闭
    IStoreReqAppClose orderCloseService =
        NCLocator.getInstance().lookup(IStoreReqAppClose.class);
    StoreReqAppVO[] returnVos = orderCloseService.rowClose(lightVOs);

    new ClientBillCombinServer<StoreReqAppVO>().combine(new StoreReqAppVO[] {
      oldVO
    }, returnVos);
    
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0163", null,
					new String[] {this.selectIndex.length + ""}), /*
                     											   * @res
                     											   * "行关闭操作成功，共关闭（）行！"
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
   * @time 2010-7-28 下午08:14:00
   */
  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0003")/*
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
   * @time 2010-7-28 下午08:13:27
   */
  private AbstractAppModel getAppModel() {
    return this.billForm.getModel();
  }

  /**
   * 方法功能描述：如果是卡片界面，从卡片获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 下午08:12:46
   */
  private StoreReqAppVO getCloseVOFromBillForm() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据VO
    StoreReqAppVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    StoreReqAppVO vo = (StoreReqAppVO) oldVO.clone();
    // Map<String, StoreReqAppItemVO> itemMap =
    // AggVOUtil.createItemMap(new StoreReqAppVO[] {
    // vo
    // });

    BillIndex index = new BillIndex(new StoreReqAppVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(StoreReqAppItemVO.class);
    StoreReqAppItemVO[] itemVOs =
        new StoreReqAppItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.billForm.getBillCardPanel().getBodyValueAt(
              this.selectIndex[i], StoreReqAppItemVO.PK_STOREREQ_B);
      // StoreReqAppItemVO itemVO = itemMap.get(pk_order_b);
      StoreReqAppItemVO itemVO =
          (StoreReqAppItemVO) index.get(meta, pk_order_b);
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
   * @time 2010-7-28 下午08:13:01
   */
  private StoreReqAppVO getCloseVOFromListView() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据的VO
    StoreReqAppVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    StoreReqAppVO vo = (StoreReqAppVO) oldVO.clone();
    // Map<String, StoreReqAppItemVO> itemMap =
    // AggVOUtil.createItemMap(new StoreReqAppVO[] {
    // vo
    // });

    BillIndex index = new BillIndex(new StoreReqAppVO[] {
      vo
    });
    StoreReqAppItemVO[] itemVOs =
        new StoreReqAppItemVO[this.selectIndex.length];
    IVOMeta meta = vo.getMetaData().getVOMeta(StoreReqAppItemVO.class);
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.list.getBillListPanel().getBodyBillModel()
              .getValueAt(this.selectIndex[i], StoreReqAppItemVO.PK_STOREREQ_B);
      // StoreReqAppItemVO itemVO = itemMap.get(pk_order_b);
      StoreReqAppItemVO itemVO =
          (StoreReqAppItemVO) index.get(meta, pk_order_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;
  }

  /**
   * 方法功能描述：得到model中的旧VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 下午08:13:46
   */
  private StoreReqAppVO getOldVO() {
    StoreReqAppVO vo = (StoreReqAppVO) this.getAppModel().getSelectedData();
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
   * @time 2010-7-28 下午08:13:14
   */
  private UIPanel getPanel() {
    UIPanel panel = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      panel = this.billForm.getBillCardPanel();
    }
    else {
      // 列表界面
      panel = this.list.getBillListPanel();
    }
    return panel;
  }

  /**
   * 方法功能描述：得到当前界面上选择的数据VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 下午08:12:28
   */
  private StoreReqAppVO getSelectedVO() {
    StoreReqAppVO vo = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getCloseVOFromBillForm();
    }
    else {
      // 列表界面
      vo = this.getCloseVOFromListView();
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
   * @time 2010-7-28 下午08:13:36
   */
  private void getSelectIndex() {
    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {
      // 列表界面
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
    this.getSelectIndex();

    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return false;
    }

    StoreReqAppVO vo = this.getSelectedVO();
    if (null == vo
        || EnumBillStatus.CLOSE.value().equals(vo.getHVO().getFbillstatus())
        || !POEnumBillStatus.APPROVE.value().equals(
            vo.getHVO().getFbillstatus())) {
      return false;
    }

    StoreReqAppItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return false;
    }

    if (itemVOs.length > 1) {
      return true;
    }

    return itemVOs[0] != null && !itemVOs[0].getBclose().booleanValue();
  }

}
