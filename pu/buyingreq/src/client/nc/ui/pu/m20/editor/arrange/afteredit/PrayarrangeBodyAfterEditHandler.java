/**
 * 
 */
package nc.ui.pu.m20.editor.arrange.afteredit;

import java.util.Map;

import nc.ui.pu.m20.editor.arrange.afteredit.body.Employee;
import nc.ui.pu.m20.editor.arrange.afteredit.body.PurchaseOrg;
import nc.ui.pu.m20.editor.arrange.afteredit.body.SuggestSupplier;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
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
 * @time 2014-7-17 ����8:47:25
 */
public class PrayarrangeBodyAfterEditHandler extends
		AbstractCardBodyAfterEditEventHandler {

	/* 
	 * ���෽����д
	 *
	 * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#getCalculateListener()
	 */
	@Override
	public AbstractRelationCalculateListener getCalculateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * ���෽����д
	 *
	 * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
	 */
	@Override
	public void registerEventListener(
			Map<String, ICardBodyAfterEditEventListener> listenerMap) {
		//�ɹ�Ա
		listenerMap.put(PraybillItemVO.PK_EMPLOYEE, new Employee());
		//�ɹ���֯
		listenerMap.put(PraybillItemVO.PK_PURCHASEORG, new PurchaseOrg());
		//���鹩Ӧ��
		listenerMap.put(PraybillItemVO.PK_SUGGESTSUPPLIER, new SuggestSupplier());
	}

}
