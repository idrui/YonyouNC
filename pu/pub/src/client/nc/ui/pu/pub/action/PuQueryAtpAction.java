package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.atp.ATPForOneMaterialMulOrgUI;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pu.atp.ATPParamVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购 存量查询Action父类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午04:06:01
 */
public abstract class PuQueryAtpAction extends NCAction {

  private static final long serialVersionUID = -5771455814680977918L;

  /** 卡片视图 **/
  private ShowUpableBillForm billForm;

  /** 列表界面 **/
  private BillListView list;

  private BillManageModel model;

  /** 存量面板 **/
  private ATPForOneMaterialMulOrgUI onAtppane;

  /** 默认构造子 **/
  public PuQueryAtpAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDQUERY);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
    // this.putValue(INCAction.CODE, "存量查询");
    // this.setBtnName("存量查询");
    // this.putValue(Action.SHORT_DESCRIPTION, "存量查询");
  }

  /**
   * 存量查询
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    ATPParamVO[] params = this.getParamVO();

    if (params == null || params.length == 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0030")/*
                                                                   * @res
                                                                   * "库存组织、物料、计划日期信息不完整,不能查询存量。"
                                                                   */, this
          .getAppModel().getContext());
      return;
    }

    this.getOnAtppane().initData(params);

    this.getOnAtppane().showModal();

    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0031")/* @res "存量查询完成" */, this.getAppModel()
            .getContext());
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
  public BillManageModel getAppModel() {
    return this.model;
  }

  /** 取得卡片视图 **/
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  /** 取得存量面板 **/
  public ATPForOneMaterialMulOrgUI getOnAtppane() {
    if (null == this.onAtppane) {
      this.onAtppane =
          new ATPForOneMaterialMulOrgUI(this.model.getContext().getEntranceUI());
    }
    return this.onAtppane;
  }

  /**
   * 方法功能描述：卡片下取得参数，需要取得物料信息和计划日期信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          卡片
   * @param selectRow
   *          表体选择行
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-28 下午04:13:01
   */
  public abstract ATPParamVO[] getParamByCardRow(BillCardPanel panel,
      int[] selectRows);

  /**
   * 方法功能描述：列表下取得参数，需要取得物料信息和计划日期信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          列表
   * @param selectRow
   *          表体选择行
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-28 下午04:13:06
   */
  public abstract ATPParamVO[] getParamByListRow(BillListPanel panel,
      int[] selectRows);

  /**
   * 方法功能描述：取得查询VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 下午04:10:24
   */
  public ATPParamVO[] getParamVO() {
    // 卡片界面
    if (this.billForm.isComponentVisible()) {
      return this.getParamByCard();
    }

    // 列表界面
    return this.getParamByList();

  }

  /** 设置卡片视图 **/
  public void setBillForm(ShowUpableBillForm billForm) {
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
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private boolean checkParam(ATPParamVO param) {
    if (null == param) {
      return false;
    }

    if (param.getPk_material() == null || param.getEnd_date() == null
        || param.getPk_stockorgs() == null
        || param.getPk_stockorgs()[0] == null) {
      return false;
    }

    return true;
  }

  private ATPParamVO[] getParamByCard() {
    BillCardPanel panel = this.billForm.getBillCardPanel();
    int[] selectRows = panel.getBillTable().getSelectedRows();
    if (selectRows == null || selectRows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "请先选择行"
                                                                   */);
    }
    return this.getParamByCardRow(panel, selectRows);
  }

  private ATPParamVO[] getParamByList() {
    BillListPanel panel = this.list.getBillListPanel();
    int[] selectRows = panel.getBodyTable().getSelectedRows();
    if (selectRows == null || selectRows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "请先选择行"
                                                                   */);
    }
    return this.getParamByListRow(panel, selectRows);
  }

}
