/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午07:36:40
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
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
 * <li>行关闭Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午07:36:40
 */
public class PraybillCloseLineAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  private BillForm billForm;

  private BillListView list;

  private AbstractAppModel model;

  private int[] selectIndex = null;

  public PraybillCloseLineAction() {
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

    PraybillVO vo = this.getSelectedVO();

    PraybillVO[] vos = new PraybillVO[] {
      vo
    };
    PowerCheckUtils.checkHasPermission(vos, POBillType.PrayBill.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), PraybillHeaderVO.VBILLCODE);

    if (null == vo) {
      return;
    }

    PraybillVO oldVO = this.getOldVO();

    // 差异VO处理
    ClientBillToServer<PraybillVO> tool = new ClientBillToServer<PraybillVO>();
    PraybillVO[] lightVOs = tool.construct(new PraybillVO[] {
      vo
    }, new PraybillVO[] {
      vo
    });

    // 执行远程调用，进行行关闭
    IPraybillMaintain PraybillCloseService =
        NCLocator.getInstance().lookup(IPraybillMaintain.class);
    PraybillVO[] returnVos = PraybillCloseService.closeBillRow(lightVOs);

    new ClientBillCombinServer<PraybillVO>().combine(new PraybillVO[] {
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
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BillListView list) {
    this.list = list;
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
   * 方法功能描述：判断有没有选择行
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-10 上午10:31:29
   */
  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0007")/*
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
   * @author GGR
   * @time 2010-4-6 下午04:16:31
   */
  private AbstractAppModel getAppModel() {
    return this.model;
  }

  /**
   * 方法功能描述：如果是卡片界面，从卡片获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 上午11:34:18
   */
  private PraybillVO getCloseVOFromBillForm() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据VO
    PraybillVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    PraybillVO vo = (PraybillVO) oldVO.clone();
    // Map<String, PraybillItemVO> itemMap =
    // AggVOUtil.createItemMap(new PraybillVO[] {
    // vo
    // });
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    PraybillItemVO[] itemVOs = new PraybillItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_Praybill_b =
          (String) this.billForm.getBillCardPanel().getBodyValueAt(
              this.selectIndex[i], PraybillItemVO.PK_PRAYBILL_B);
      // PraybillItemVO itemVO = itemMap.get(pk_Praybill_b);
      PraybillItemVO itemVO = (PraybillItemVO) index.get(meta, pk_Praybill_b);
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
   * @author GGR
   * @time 2010-4-4 下午12:13:40
   */
  private PraybillVO getCloseVOFromListView() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // 获得旧VO，然后将表体设为选择的VO，得到选择数据的VO
    PraybillVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    PraybillVO vo = (PraybillVO) oldVO.clone();
    // Map<String, PraybillItemVO> itemMap =
    // AggVOUtil.createItemMap(new PraybillVO[] {
    // vo
    // });
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    PraybillItemVO[] itemVOs = new PraybillItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_Praybill_b =
          (String) this.list.getBillListPanel().getBodyBillModel()
              .getValueAt(this.selectIndex[i], PraybillItemVO.PK_PRAYBILL_B);
      // PraybillItemVO itemVO = itemMap.get(pk_Praybill_b);
      PraybillItemVO itemVO = (PraybillItemVO) index.get(meta, pk_Praybill_b);
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
   * @author GGR
   * @time 2010-4-6 下午04:28:59
   */
  private PraybillVO getOldVO() {
    PraybillVO vo = (PraybillVO) this.getAppModel().getSelectedData();
    return vo;
  }

  /**
   * 方法功能描述：得到Panel
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
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
   * 方法功能描述：得到当前界面上选择的数据VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 下午01:55:55
   */
  private PraybillVO getSelectedVO() {
    PraybillVO vo = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getCloseVOFromBillForm();
    }
    else {// 列表界面
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
   * @author GGR
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
    this.getSelectIndex();

    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return false;
    }

    PraybillVO vo = this.getSelectedVO();

    // 非审批态的不能行关闭
    if (null != vo
        && !(POEnumBillStatus.APPROVE.toInt() == vo.getHVO().getFbillstatus()
            .intValue())) {
      return false;
    }

    // 如果最终关闭，则行一定关闭
    if (null == vo
        || EnumBillStatue.CLOSE.toInt() == vo.getHVO().getFbillstatus()
            .intValue()) {
      return false;
    }

    // 如果选择的行有一个为关闭状态，则返回false，不允许关闭
    for (PraybillItemVO itemVO : vo.getBVO()) {
      if (itemVO != null && UFBoolean.TRUE.equals(itemVO.getBrowclose())) {
        return false;
      }
    }

    return true;
  }
}
