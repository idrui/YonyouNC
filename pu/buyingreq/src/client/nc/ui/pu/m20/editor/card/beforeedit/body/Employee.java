/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 ����06:38:29
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�Ա�༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 ����06:38:29
 */
public class Employee implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object pk_purchaseorg =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_PURCHASEORG);
    if (null == pk_purchaseorg
        || pk_purchaseorg.toString().trim().length() == 0) {
      // �ɹ���֯δ¼�룬�ɹ�Ա���ɱ༭
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // ���ձ���ɹ���֯����Ա����¼��
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU((UIRefPane) event
            .getBillCardPanel().getBodyItem(PraybillItemVO.PK_EMPLOYEE)
            .getComponent());
    filter.filterItemRefByOrg(pk_purchaseorg.toString());
    event.setReturnValue(Boolean.TRUE);
  }
}
