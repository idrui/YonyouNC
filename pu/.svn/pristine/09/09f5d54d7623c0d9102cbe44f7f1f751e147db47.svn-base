/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:27:50
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购组织编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:27:50
 */
public class Purchaseorg implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object pk_material =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_MATERIAL);
    if ((null == pk_material) || (0 == pk_material.toString().trim().length())) {
      // 物料未设置，不能编辑
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    Object bcanpurchaseorgedit =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.BCANPURCHASEORGEDIT);
    if ((null != bcanpurchaseorgedit)
        && !((UFBoolean) bcanpurchaseorgedit).booleanValue()) {
      // 如果该默认采购组织不等于请购单表头公司，则认为此物料当前公司无权采购，因此不可修改

      event.setReturnValue(Boolean.FALSE);
    }
    else {
      event.setReturnValue(Boolean.TRUE);
    }
  }
}
