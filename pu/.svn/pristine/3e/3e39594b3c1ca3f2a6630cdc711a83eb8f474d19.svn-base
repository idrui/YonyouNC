package nc.ui.pu.m23.view;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.m23.refmodel.MaterialRefModel;
import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.uif2.UIState;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 到货单的卡片显示所使用的ShowUpableBillForm类
 * 
 * @since 6.0
 * @version 2010-11-10 下午02:32:48
 * @author hanbin
 */
public class ArriveCardForm extends PUBillForm {

  private static final long serialVersionUID = 6251861701753657625L;

  @Override
  public void initUI() {
    super.initUI();
    // 设置物料的参照
    this.setMaterialRefModel();
    // 设置批次档案参照
    this.setBatchCodeRef();
    // 设置精度
    this.setScale();
  }

  @Override
  public void setValue(Object object) {
    super.setValue(object);
    // 到货单上维护采购订单的到货关闭状态
    if (this.isShowClosePOItem((ArriveVO) object)) {
      ArriveClientUtil.showCloseOrderItem(this.getBillCardPanel());
    }
    else {
      ArriveClientUtil.hideCloseOrderItem(this.getBillCardPanel());
    }
  }

  private boolean isShowClosePOItem(ArriveVO vo) {
    // 只有新增时（到货单只有拉单），非退货时才进行采购订单的关闭，委外订单也不处理
    if (null == vo || UIState.ADD != this.getModel().getUiState()
        || UFBoolean.TRUE.equals(vo.getHVO().getBisback())
        || StringUtils.isNotBlank(vo.getHVO().getPk_arriveorder())) {
      return false;
    }
    for (ArriveItemVO item : vo.getBVO()) {
      if (POBillType.Order.getCode().equals(item.getCsourcetypecode())) {
        return true;
      }
    }
    return false;
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    BatchRefPane pane = new BatchRefPane();
    card.getBodyItem(ArriveItemVO.VBATCHCODE).setComponent(pane);
  }

  private void setMaterialRefModel() {
    BillCardPanel card = this.getBillCardPanel();

    UIRefPane mrlRefPane =
        (UIRefPane) card.getBodyItem(ArriveItemVO.PK_MATERIAL).getComponent();
    mrlRefPane.setRefModel(new MaterialRefModel());
  }

  private void setScale() {
    String pkGroup = this.getModel().getContext().getPk_group();
    new ArriveUIScaleRule(pkGroup).setCardScale(this.getBillCardPanel());
  }

  @Override
  protected void enableFillableItems() {
    super.enableFillableItems();
    String[] enableItems =
        new String[] {
    			ArriveItemVO.PK_RACK,
          ArriveItemVO.PK_RECEIVESTORE, ArriveItemVO.CPROJECTID,
          ArriveItemVO.VMEMOB, ArriveItemVO.VBDEF1, ArriveItemVO.VBDEF2,
          ArriveItemVO.VBDEF3, ArriveItemVO.VBDEF4, ArriveItemVO.VBDEF5,
          ArriveItemVO.VBDEF6, ArriveItemVO.VBDEF7, ArriveItemVO.VBDEF8,
          ArriveItemVO.VBDEF9, ArriveItemVO.VBDEF10, ArriveItemVO.VBDEF11,
          ArriveItemVO.VBDEF12, ArriveItemVO.VBDEF13, ArriveItemVO.VBDEF14,
          ArriveItemVO.VBDEF15, ArriveItemVO.VBDEF16, ArriveItemVO.VBDEF17,
          ArriveItemVO.VBDEF18, ArriveItemVO.VBDEF19, ArriveItemVO.VBDEF20
        };
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // 放开字段的批改
    cardUtils.enableItemsFill(enableItems);
  }
}
