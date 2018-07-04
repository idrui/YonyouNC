/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午02:36:21
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主单位s编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 下午02:36:21
 */
public class Unitid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String pk_material =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_MATERIAL);
    if (StringUtil.isEmptyWithTrim(pk_material)) {
      // 物料未设置，不能编辑
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      event.setReturnValue(Boolean.TRUE);
    }
  }

}
