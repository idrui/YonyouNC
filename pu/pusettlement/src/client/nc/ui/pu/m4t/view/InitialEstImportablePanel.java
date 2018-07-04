package nc.ui.pu.m4t.view;

import java.util.List;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.trade.excelimport.Uif2BodyOnlyImportablePanel;
import nc.ui.uif2.UIState;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.POBillType;

public class InitialEstImportablePanel extends Uif2BodyOnlyImportablePanel {

  public InitialEstImportablePanel() {
    super(null, null, null);
  }

  public InitialEstImportablePanel(String title, String funCode,
      String configFilePath) {
    super(null, null, null);
  }

  @Override
  public void addNew() {
    this.getAppModel().initModel(null);
    this.getAppModel().setUiState(UIState.ADD);
  }

  @Override
  public List<InputItem> getInputItems() {
    BillItem[] items = this.getBillData().getBillItemsByPos(IBillItem.HEAD);
    for (BillItem item : items) {
      if (InitialEstHeaderVO.PK_ORG.equals(item.getKey())) {
        item.setShow(true);
        item.setEdit(true);
      }
    }
    // items = this.getBillData().getBillItemsByPos(IBillItem.BODY);
    // for (BillItem item : items) {
    // }
    return super.getInputItems();
  }

  @Override
  protected String getAddActionBeanName() {
    return null;
  }

  @Override
  protected String getAppModelBeanName() {
    return null;
  }

  @Override
  protected String getBillCardEditorBeanName() {
    return null;
  }

  @Override
  protected String getCancelActionBeanName() {
    return null;
  }

  @Override
  protected String getSaveActionBeanName() {
    return null;
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
    String pk_group = AppContext.getInstance().getPkGroup();
    this.getBillData().setHeadItem(InitialEstHeaderVO.PK_GROUP, pk_group);

    // 解析字段并设置到billdata上
    // 先解析采购组织，
    String pk_purchaseorg_v =
        (String) eavo.getParentVO().getAttributeValue(
            InitialEstHeaderVO.PK_PURCHASEORG_V);

    UIRefPane ref =
        (UIRefPane) this.getBillData()
            .getHeadItem(InitialEstHeaderVO.PK_PURCHASEORG_V).getComponent();
    if (pk_purchaseorg_v != null) {
      ref.getRefModel().clearData();
      ref.setBlurValue(pk_purchaseorg_v);
      ref.setValueObj(ref.getRefPK());
    }
    else {
      ref.setPK(null);
    }
    pk_purchaseorg_v = ref.getRefPK();
    String pk_purchaseorg = OrgUnitPubService.getOrgIDByVID(pk_purchaseorg_v);

    // 设置参照
    ref =
        (UIRefPane) this.getBillData()
            .getHeadItem(InitialEstHeaderVO.PK_BIZPSN).getComponent();
    FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(ref)
        .filterItemRefByOrg(pk_purchaseorg);

    ref =
        (UIRefPane) this.getBillData().getHeadItem(InitialEstHeaderVO.PK_DEPT)
            .getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(ref).filterItemRefByOrg(
        pk_purchaseorg);

    ref =
        (UIRefPane) this.getBillData()
            .getHeadItem(InitialEstHeaderVO.PK_DEPT_V).getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(ref).filterItemRefByOrg(
        pk_purchaseorg);

    new FilterTransTypeRefUtils(this.getBillData().getHeadItem(
        InitialEstHeaderVO.CTRANTYPEID)).filterTranType(new String[] {
      POBillType.InitEstimate.getCode()
    });

    this.getBillData().setImportBillValueVO(eavo);
  }

}
