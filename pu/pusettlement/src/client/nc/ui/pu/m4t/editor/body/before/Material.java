package nc.ui.pu.m4t.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2011-5-31 上午10:00:08
 * @author wuxla
 */

public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object pk_org =
        event.getBillCardPanel().getHeadItem(InitialEstHeaderVO.PK_ORG)
            .getValueObject();
    if (ObjectUtil.isEmptyWithTrim(pk_org)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    // 根据”是否费用“过滤物料
    BillItem materialItem =
        event.getBillCardPanel().getBodyItem(InitialEstItemVO.PK_MATERIAL);
    FilterMaterialRefUtils filterMaterialRefUtils =
        new FilterMaterialRefUtils((UIRefPane) materialItem.getComponent());

    // 过滤掉费用和折扣物料
    filterMaterialRefUtils.filterRefByDiscountflag(UFBoolean.FALSE);
    filterMaterialRefUtils.filterRefByFeeflag(UFBoolean.FALSE);
    filterMaterialRefUtils.filterItemRefByOrg(pk_org.toString());

    materialItem =
        event.getBillCardPanel().getBodyItem(InitialEstItemVO.PK_SRCMATERIAL);
    new FilterMaterialRefUtils((UIRefPane) materialItem.getComponent())
        .filterItemRefByOrg(pk_org.toString());
  }

}
