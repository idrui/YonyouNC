package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.0
 * @version 2011-6-13 上午10:06:24
 * @author wuxla
 */

public abstract class LinkQueryMppAction extends NCAction {
  private static final long serialVersionUID = 3505250143536019807L;

  private ShowUpableBillForm billForm;

  private BillListView list;

  private BillManageModel model;

  private int selectIndex = -1;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isMPPEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0028")/*
                                                                   * @res
                                                                   * "采购计划未启用"
                                                                   */);
    }
    this.initSelectIndex();
    if (!this.linkBefore()) {
      return;
    }

    this.linkQuery();
    //
    // IAccessableBusiVO[] busivo = this.getBusiVO();
    // NtbParamVO[] vos = BudgetServices.getLinkDatas(busivo);
    // NtbParamVOChooser chooser =
    // new NtbParamVOChooser(this.getModel().getContext().getEntranceUI(),
    // "预算执行情况");
    // if (null == vos || vos.length == 0) {
    // throw new BusinessException("当前计划没有设置对应的控制方案，无法联查预算执行情况！ ");
    // }
    // chooser.setParamVOs(vos);
    // chooser.showModal();
  }

  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public int getSelectIndex() {
    return this.selectIndex;
  }

  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setSelectIndex(int selectIndex) {
    this.selectIndex = selectIndex;
  }

  private void initSelectIndex() {
    // 卡片界面
    if (this.billForm.isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRow();
    }
    else {// 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      this.selectIndex = panel.getBodyTable().getSelectedRow();
    }
  }

  @Override
  protected boolean isActionEnable() {
    if (null == this.getModel().getSelectedData()) {
      return false;
    }
    return true;
  }

  protected boolean linkBefore() {
    if (-1 == this.selectIndex) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "请先选择行"
                                                                   */);
      return false;
    }
    return true;
  }

  protected abstract void linkQuery() throws BusinessException;
}
