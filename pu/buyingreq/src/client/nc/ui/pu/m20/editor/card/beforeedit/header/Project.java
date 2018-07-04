/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 ����04:14:37
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ��Ŀ�༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-9 ����04:14:37
 */
public class Project implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // ���ձ�ͷ��������֯�ɼ�����Ŀ����¼��
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterProjectRefUtils filter =
          new FilterProjectRefUtils(event.getBillCardPanel().getHeadItem(
              PraybillHeaderVO.CHPROJECTID));

      filter.filterItemRefByOrg(org);
    }
  }

}
