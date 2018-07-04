package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.ReferenceFilterByMaterial;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务单位的编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-31 下午01:20:52
 */
public class AssistUnit implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 物料
    Object pk_material =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.PK_MATERIAL);
    if (ObjectUtil.isEmptyWithTrim(pk_material)) {
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      // 过滤跟物料相关的参照
      new ReferenceFilterByMaterial(event.getBillCardPanel()).filter(
          event.getRow(), event.getKey());
      // 设置返回值
      event.setReturnValue(Boolean.TRUE);
    }
  }

}
