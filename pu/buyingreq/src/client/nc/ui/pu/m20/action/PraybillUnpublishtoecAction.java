package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillPublishToEC;
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
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.data.ValueUtils;
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
 * <li>取消发布到电子商务Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-19 下午03:27:25
 */
public class PraybillUnpublishtoecAction extends NCAction {

  private static final long serialVersionUID = -4860975333777589069L;

  private BillForm billForm;

  private BillListView list;

  private AbstractAppModel model;

  private int[] selectIndex = null;

  public PraybillUnpublishtoecAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_CANCELPUBLISHTOEC);
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

    // 执行远程调用，进行行打开
    IPraybillPublishToEC PraybillCloseService =
        NCLocator.getInstance().lookup(IPraybillPublishToEC.class);
    PraybillVO[] returnVos = PraybillCloseService.unPublishToEC(lightVOs);
    String msg = returnVos[0].getMsg();
    new ClientBillCombinServer<PraybillVO>().combine(new PraybillVO[] {
      oldVO
    }, returnVos);

    this.getAppModel().directlyUpdate(oldVO);
    if (StringUtil.isEmptyWithTrim(msg)) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0018")/*
                                                                   * @res
                                                                   * "取消发布到电子商务成功。"
                                                                   */, this
          .getAppModel().getContext());
    }
    else {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0019")/*
                                                                   * @res
                                                                   * "取消发布到电子商务成功,其中"
                                                                   */+ msg);
    }
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
   * 方法功能描述：判断有没有选择行等
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
   * 方法功能描述：得到选择数据的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 下午01:49:33
   */
  private PraybillVO getSelectedVO() {
    PraybillVO vo = null;

    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getVOFromBillForm();
    }
    else {// 列表界面
      vo = this.getVOFromListView();
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
   * 方法功能描述：如果是卡片界面，从卡片获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 下午12:24:04
   */
  private PraybillVO getVOFromBillForm() {
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
          (String) this.billForm.getBillCardPanel().getBillModel()
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
   * 方法功能描述：如果是列表界面，从列表获取表体数据
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 下午03:42:55
   */
  private PraybillVO getVOFromListView() {
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

    if (null == vo) {
      return false;
    }

    // 非审批态，返回false
    if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getFbillstatus()
        .intValue()) {
      return false;
    }

    // 如果选择的行有一个且未发布到电子商务，则返回false，不允许取消发布到电子商务
    int len = vo.getBVO().length;
    PraybillItemVO[] items = vo.getBVO();
    if (len == 1) {
      if (!ValueUtils.getBoolean(items[0].getBpublishtoec())) {
        return false;
      }
      if (ValueUtils.getBoolean(items[0].getBrowclose())) {
        return false;
      }
    }

    return true;
  }

}
