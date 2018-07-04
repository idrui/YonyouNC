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
 * @version 2011-6-13 ����10:06:24
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
                                                                   * "�ɹ��ƻ�δ����"
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
    // "Ԥ��ִ�����");
    // if (null == vos || vos.length == 0) {
    // throw new BusinessException("��ǰ�ƻ�û�����ö�Ӧ�Ŀ��Ʒ������޷�����Ԥ��ִ������� ");
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
    // ��Ƭ����
    if (this.billForm.isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRow();
    }
    else {// �б����
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
                                                                   * "����ѡ����"
                                                                   */);
      return false;
    }
    return true;
  }

  protected abstract void linkQuery() throws BusinessException;
}
