/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 下午04:14:37
 */
package nc.ui.pu.m20.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头项目编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-9 下午04:14:37
 */
public class Project implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 根据表头项目设置表体项目
    this.setBodyProject(event);
  }

  /**
   * 将表头项目设置在表体项目上。
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 下午04:26:34
   */
  private void setBodyProject(CardHeadTailAfterEditEvent event) {
    Object hproject =
        event.getBillCardPanel().getHeadItem(PraybillHeaderVO.CHPROJECTID)
            .getValueObject();

    int row = event.getBillCardPanel().getRowCount();

    for (int i = 0; i < row; i++) {
      event.getBillCardPanel().setBodyValueAt(hproject, i,
          PraybillItemVO.CPROJECTID);
    }

  }
}
