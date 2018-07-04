/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-24 下午04:03:29
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体单位编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-24 下午04:03:29
 */
public class InvoiceAstUnit implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 单位
    BillItem astUnitItem =
        event.getBillCardPanel().getBodyItem(InvoiceItemVO.CASTUNITID);
    // 物料
    Object pk_material =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            InvoiceItemVO.PK_MATERIAL);
    if (ObjectUtil.isEmptyWithTrim(pk_material)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    event.setReturnValue(Boolean.TRUE);
    // 根据物料限制单位
    new FilterMeasdocRefUtils(astUnitItem)
        .setMaterialPk(pk_material.toString());
  }

}
