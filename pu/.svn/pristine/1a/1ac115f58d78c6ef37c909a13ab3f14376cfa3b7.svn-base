package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2011-7-1 上午08:41:30
 * @author wuxla
 */

public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel billCardPanel = event.getBillCardPanel();
    String csourceTypeCode =
        (String) billCardPanel.getBodyValueAt(event.getRow(),
            StoreReqAppItemVO.CSOURCETYPECODE);
    // 来源于物质及服务需求单以及维修计划的物资需求申请单行，物料不让编辑
    if (ForeignForPUConstant.MATERPLAN.equals(csourceTypeCode)
        || PuRefBillTypeIdEnum.M4B32.getBillTypeId().equals(csourceTypeCode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane panel =
        (UIRefPane) billCardPanel.getBodyItem(StoreReqAppItemVO.PK_MATERIAL)
            .getComponent();
    panel.setMultiSelectedEnabled(true);

    // 参照的范围为请购单表头库存组织可见的物料
    String org =
        (String) billCardPanel.getHeadItem(StoreReqAppHeaderVO.PK_ORG)
            .getValueObject();
    if (null != org) {
      FilterMaterialRefUtils filter =
          new FilterMaterialRefUtils((UIRefPane) billCardPanel.getBodyItem(
              StoreReqAppItemVO.PK_MATERIAL).getComponent());

      filter.filterItemRefByOrg(org);
      filter.filterIsSealedShow(UFBoolean.FALSE);

      filter.filterRefByFeeflag(UFBoolean.FALSE);
      filter.filterRefByDiscountflag(UFBoolean.FALSE);
    }
    event.setReturnValue(Boolean.TRUE);
  }

}
