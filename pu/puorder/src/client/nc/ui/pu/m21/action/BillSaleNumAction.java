/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 上午08:39:17
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pu.m21.view.AvgSaleQueryUI;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pu.m21.entity.AvgSaleQueryVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销量查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-21 上午08:39:17
 */
public class BillSaleNumAction extends NCAction {

  private static final long serialVersionUID = 2257920813242019145L;

  private ShowUpableBillForm billForm;

  private ShowUpableBillForm billForm_Add;

  private BillListView list;

  private BillManageModel model;

  public BillSaleNumAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SALEINFOQUERY);
    // String str = "销量查询";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.SHORT_DESCRIPTION, str);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    ShowUpableBillForm showBillForm = null;
    if (this.billForm != null && this.billForm.isComponentVisible()) {
      showBillForm = this.billForm;
    }
    else if (this.billForm_Add != null
        && this.billForm_Add.isComponentVisible()) {
      showBillForm = this.billForm_Add;
    }
    if (null == showBillForm) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0032")/*
                                                                   * @res
                                                                   * "只在卡片下可用"
                                                                   */);
    }
    if (showBillForm.isComponentVisible()) {
      this.openByForm();
    }
    else {
      this.openByList();
    }
  }

  /**
   * @return billForm
   */
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public ShowUpableBillForm getBillForm_Add() {
    return this.billForm_Add;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  public void setBillForm_Add(ShowUpableBillForm billForm_Add) {
    this.billForm_Add = billForm_Add;
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  /**
   * 方法功能描述：是否可以销量查询
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-23 上午08:28:27
   */
  private boolean checkData() {
    BillCardPanel panel = (BillCardPanel) this.getBillForm().getBillCardPanel();
    if (0 == panel.getRowCount()) {
      return false;
    }

    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    boolean isSaleOrg = true;
    try {
      isSaleOrg = OrgUnitPubService.isTypeOf(pk_org, IOrgConst.SALEORGTYPE);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    if (!isSaleOrg) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0034")/*
                                                                   * @res
                                                                   * "组织不是销售组织，不能销量查询"
                                                                   */);
      return false;
    }

    int row = panel.getBillTable().getSelectedRow();
    if (-1 == row) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0035")/*
                                                                   * @res
                                                                   * "请选中有物料的行"
                                                                   */);
      return false;
    }

    Object pk_material = panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
    if (ObjectUtil.isEmptyWithTrim(pk_material)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0035")/*
                                                                   * @res
                                                                   * "请选中有物料的行"
                                                                   */);
      return false;
    }

    return true;
  }

  private boolean checkDataByList() {
    if (this.list.getModel().getSelectedData() == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return false;
    }
    BillListPanel panel = this.list.getBillListPanel();
    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    boolean isSaleOrg = true;
    try {
      isSaleOrg = OrgUnitPubService.isTypeOf(pk_org, IOrgConst.SALEORGTYPE);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    if (!isSaleOrg) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0034")/*
                                                                   * @res
                                                                   * "组织不是销售组织，不能销量查询"
                                                                   */);
      return false;
    }

    int row = panel.getBodyTable().getSelectedRow();
    if (-1 == row) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0035")/*
                                                                   * @res
                                                                   * "请选中有物料的行"
                                                                   */);
      return false;
    }
    Object pk_material =
        panel.getBodyBillModel().getValueAt(row,
            OrderItemVO.PK_MATERIAL + IBillItem.ID_SUFFIX);
    if (ObjectUtil.isEmptyWithTrim(pk_material)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0035")/*
                                                                   * @res
                                                                   * "请选中有物料的行"
                                                                   */);
      return false;
    }

    return true;
  }

  private void openByForm() {
    boolean canSale = this.checkData();
    if (canSale) {
      BillCardPanel panel =
          (BillCardPanel) this.getBillForm().getBillCardPanel();
      int row = panel.getBillTable().getSelectedRow();

      AvgSaleQueryVO vo = new AvgSaleQueryVO();
      String pk_org =
          (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
      vo.setPk_org(pk_org);
      vo.setDquerydate(ClientContext.getInstance().getBusiDate());
      vo.setPk_material((String) panel.getBodyValueAt(row,
          OrderItemVO.PK_MATERIAL));
      vo.setPk_srcmaterial((String) panel.getBodyValueAt(row,
          OrderItemVO.PK_SRCMATERIAL));
      vo.setCunitid((String) panel.getBodyValueAt(row, OrderItemVO.CUNITID));
      AvgSaleQueryUI dlgSaleNum = new AvgSaleQueryUI(this.getBillForm(),true);
      dlgSaleNum.setTxtDayText(Integer.valueOf(7));
      dlgSaleNum.initData(new AvgSaleQueryVO[] {
        vo
      });
      dlgSaleNum.setResizable(true);
      dlgSaleNum.showModal();
    }
  }

  private void openByList() {
    boolean canSale = this.checkDataByList();
    if (canSale) {
      BillListPanel panel = this.list.getBillListPanel();
      int row = panel.getBodyTable().getSelectedRow();
      AvgSaleQueryVO vo = new AvgSaleQueryVO();
      String pk_org =
          (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
      vo.setPk_org(pk_org);
      vo.setDquerydate(ClientContext.getInstance().getBusiDate());
      vo.setPk_material((String) panel.getBodyBillModel().getValueAt(row,
          OrderItemVO.PK_MATERIAL + IBillItem.ID_SUFFIX));
      vo.setPk_srcmaterial((String) panel.getBodyBillModel().getValueAt(row,
          OrderItemVO.PK_SRCMATERIAL + IBillItem.ID_SUFFIX));
      vo.setCunitid((String) panel.getBodyBillModel().getValueAt(row,
          OrderItemVO.CUNITID + IBillItem.ID_SUFFIX));
      AvgSaleQueryUI dlgSaleNum = new AvgSaleQueryUI(this.getBillForm());
      dlgSaleNum.setTxtDayText(Integer.valueOf(7));
      dlgSaleNum.initData(new AvgSaleQueryVO[] {
        vo
      });
      dlgSaleNum.showModal();
    }
  }

  @Override
  protected boolean isActionEnable() {
    return true;
  }

}
