package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;

import nc.ui.ic.general.view.SetPartDlg;
import nc.ui.ic.material.query.InvInfoUIQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> liuchx 暂时先用ic的Dig，如何编译有问题，再修改
 * <ul>
 * <li>联查单据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-4 下午02:27:22
 */
public class LinkSetPartUIAction extends NCAction {

  private static final long serialVersionUID = -8876691494402335938L;

  /** 卡片视图 **/
  private ShowUpableBillForm billForm;

  private BillManageModel model;

  public LinkSetPartUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SETPARTINFO);
    // String name = "成套件信息";
    // this.setBtnName(name);
    // this.putValue(INCAction.CODE, name);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.ALT_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, name);

  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel panel = this.billForm.getBillCardPanel();
    // 选中行
    int selectrow = panel.getBillTable().getSelectedRow();

    String pk_material =
        (String) panel.getBodyValueAt(selectrow, ArriveItemVO.PK_MATERIAL);

    if (pk_material == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0024")/*
                                                                   * @res
                                                                   * "没有选择物料"
                                                                   */);
      return;
    }

    String pk_corp_v =
        (String) panel.getBodyValueAt(selectrow, ArriveItemVO.PK_ORG_V);

    InvBasVO invbasevo =

    InvInfoUIQuery.getInstance().getInvInfoQuery().getInvBasVO(pk_material);

    if (invbasevo == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0025")/*
                                                                   * @res
                                                                   * "物料不存在"
                                                                   */);
      return;

    }

    if (invbasevo.getSetpartsflag() == null

    || !invbasevo.getSetpartsflag().booleanValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0026")/*
                                                                   * @res
                                                                   * "物料不是成套件"
                                                                   */);
      return;

    }

    SetPartDlg dlg = new SetPartDlg(this.model.getContext().getEntranceUI());

    dlg.initUI(pk_material, pk_corp_v);
    dlg.setResizable(true);
    dlg.setReset(true);

    dlg.showModal();

  }

  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
