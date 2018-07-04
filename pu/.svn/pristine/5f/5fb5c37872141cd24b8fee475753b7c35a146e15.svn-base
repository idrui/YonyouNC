package nc.ui.pu.m20.view;

import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * 请购单卡片界面
 * 处理批次号档案 参照
 * 处理可以支持批改的字段
 * 
 * @since 6.0
 * @version 2011-7-8 上午09:37:34
 * @author liuchx
 */
public class PraybillBillForm extends PUBillForm {

  private static final long serialVersionUID = 8187553530293704364L;

  @Override
  public void initUI() {
    super.initUI();
    this.setBatchCodeRef();
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    nc.ui.ic.batchcode.ref.BatchRefPane pane =
        new nc.ui.ic.batchcode.ref.BatchRefPane();
    card.getBodyItem(PraybillItemVO.VBATCHCODE).setComponent(pane);
  }

  @Override
  protected void enableFillableItems() {
    super.enableFillableItems();
    // 需求日期、建议订货日期；
    // 订单类型；
    // 项目、需求部门；
    // 备注、自定义项
    String[] enableItems =
        new String[] {
          PraybillItemVO.DREQDATE, PraybillItemVO.DSUGGESTDATE,
          PraybillItemVO.CORDERTRANTYPECODE, PraybillItemVO.CPROJECTID,
          PraybillItemVO.PK_REQDEPT_V, PraybillItemVO.VBMEMO,
          PraybillItemVO.VBDEF1, PraybillItemVO.VBDEF2, PraybillItemVO.VBDEF3,
          PraybillItemVO.VBDEF4, PraybillItemVO.VBDEF5, PraybillItemVO.VBDEF6,
          PraybillItemVO.VBDEF7, PraybillItemVO.VBDEF8, PraybillItemVO.VBDEF9,
          PraybillItemVO.VBDEF10, PraybillItemVO.VBDEF11,
          PraybillItemVO.VBDEF12, PraybillItemVO.VBDEF13,
          PraybillItemVO.VBDEF14, PraybillItemVO.VBDEF15,
          PraybillItemVO.VBDEF16, PraybillItemVO.VBDEF17,
          PraybillItemVO.VBDEF18, PraybillItemVO.VBDEF19,
          PraybillItemVO.VBDEF20,PraybillItemVO.PK_EMPLOYEE,
          //v65需求，需求仓库支持批改
          PraybillItemVO.PK_REQSTOR
        };
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // 放开字段的批改
    cardUtils.enableItemsFill(enableItems);
  }
}
