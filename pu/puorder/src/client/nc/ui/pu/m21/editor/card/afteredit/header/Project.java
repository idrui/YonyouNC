package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.rule.ProjectSetter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>项目的编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 上午10:39:15
 */
public class Project implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());

    // 同步表体的项目
    ProjectSetter setter = new ProjectSetter(helper);
    setter.setBodyProject();
  }

}
