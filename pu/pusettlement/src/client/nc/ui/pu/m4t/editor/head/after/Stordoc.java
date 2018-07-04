/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 ����05:06:01
 */
package nc.ui.pu.m4t.editor.head.after;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.rule.CostregionSetter;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ֿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 ����05:06:01
 */
public class Stordoc implements ICardHeadTailAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    new CostregionSetter(editor).setCostregion();
    // ��������������ֵ�߼��Ǹ���ҵ��ί�й�ϵ����ͬ�ɹ�������������������ģ�����Ҫ�༭���¼�
    // new PCCostregionSetter(editor).setPK_apliabcenter();
  }

}
