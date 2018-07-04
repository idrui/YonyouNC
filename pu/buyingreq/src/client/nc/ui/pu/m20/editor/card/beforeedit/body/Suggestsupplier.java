/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 ����06:25:08
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���鹩Ӧ�̱༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 ����06:25:08
 */
public class Suggestsupplier implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    int row = event.getRow();
    Object purchaseorg =
        event.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.PK_PURCHASEORG);
    if ((null == purchaseorg) || (purchaseorg.toString().trim().length() == 0)) {
      // �ɹ���֯δ¼�룬���ɱ༭
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      // ���յķ�ΧΪ����ɹ���֯�ɼ��Ĺ�Ӧ�̡�����ɹ���֯Ϊ��ʱ����ղ����κι�Ӧ�̵�����
      FilterSupplierRefUtils filter =
          new FilterSupplierRefUtils(event.getBillCardPanel().getBodyItem(
              PraybillItemVO.PK_SUGGESTSUPPLIER));

      filter.filterItemRefByOrg(purchaseorg.toString());
      event.setReturnValue(Boolean.TRUE);
    }

  }
}
