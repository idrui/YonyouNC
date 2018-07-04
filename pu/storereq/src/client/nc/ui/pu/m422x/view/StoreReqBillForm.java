package nc.ui.pu.m422x.view;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

public class StoreReqBillForm extends PUBillForm {

  private static final long serialVersionUID = -6511542565134146961L;

  @Override
  public void initUI() {
    super.initUI();
    this.setBatchCodeRef();
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    BatchRefPane pane = new BatchRefPane();
    card.getBodyItem(StoreReqAppItemVO.VBATCHCODE).setComponent(pane);
  }

  @Override
  protected void enableFillableItems() {
    super.enableFillableItems();
    // 需求日期；
    // 申请部门、申请人。
    // 项目；
    // 备注、自定义项
    String[] enableItems =
        new String[] {
          StoreReqAppItemVO.DREQDATE, StoreReqAppItemVO.PK_APPDEPT_V,
          StoreReqAppItemVO.PK_APPPSN, StoreReqAppItemVO.CPROJECTID,
          StoreReqAppItemVO.VBMEMO, StoreReqAppItemVO.VBDEF1,
          StoreReqAppItemVO.VBDEF2, StoreReqAppItemVO.VBDEF3,
          StoreReqAppItemVO.VBDEF4, StoreReqAppItemVO.VBDEF5,
          StoreReqAppItemVO.VBDEF6, StoreReqAppItemVO.VBDEF7,
          StoreReqAppItemVO.VBDEF8, StoreReqAppItemVO.VBDEF9,
          StoreReqAppItemVO.VBDEF10, StoreReqAppItemVO.VBDEF11,
          StoreReqAppItemVO.VBDEF12, StoreReqAppItemVO.VBDEF13,
          StoreReqAppItemVO.VBDEF14, StoreReqAppItemVO.VBDEF15,
          StoreReqAppItemVO.VBDEF16, StoreReqAppItemVO.VBDEF17,
          StoreReqAppItemVO.VBDEF18, StoreReqAppItemVO.VBDEF19,
          StoreReqAppItemVO.VBDEF20
        };
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // 放开字段的批改
    cardUtils.enableItemsFill(enableItems);
  }
}
