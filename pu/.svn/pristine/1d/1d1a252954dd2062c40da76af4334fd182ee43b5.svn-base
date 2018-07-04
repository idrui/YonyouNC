package nc.ui.pu.m23.editor.card.beforeedit.body;

import java.util.Map;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.pub.Constructor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于控制某些字段不允许编辑，也是为了防止不应该被编辑的字段，用户在模板上设置可以编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-26 下午03:11:59
 */
public class NeverEditBodyItem implements ICardBodyBeforeEditEventListener {

  /**
   * 添加某个类的全部字段设为不可编辑
   * 
   * @param clazz
   * @param listenerMap
   */
  public void addNeverEditItem(Class<? extends ISuperVO> clazz,
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    ISuperVO vo = Constructor.construct(clazz);
    IVOMeta voMeta = vo.getMetaData();
    IAttributeMeta[] attributes = voMeta.getAttributes();
    for (IAttributeMeta attribute : attributes) {
      listenerMap.put(attribute.getName(), this);
    }
  }

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    e.setReturnValue(Boolean.FALSE);
  }
}
