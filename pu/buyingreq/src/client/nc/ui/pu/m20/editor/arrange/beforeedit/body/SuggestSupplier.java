/**
 * 
 */
package nc.ui.pu.m20.editor.arrange.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 ����9:08:55
 */
public class SuggestSupplier implements ICardBodyBeforeEditEventListener {

	/* 
	 * ���෽����д
	 *
	 * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
	 */
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
