package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务单位编辑前处理,设置单位的参照范围
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-30 上午10:04:44
 */
public class AstUnit implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 物料主键
    String pk_material =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_MATERIAL);

    // 设置单位的参照范围
    BillItem astUnit = card.getBodyItem(ArriveItemVO.CASTUNITID);
    new FilterMeasdocRefUtils(astUnit).setMaterialPk(pk_material);

    e.setReturnValue(Boolean.TRUE);
  }
}
