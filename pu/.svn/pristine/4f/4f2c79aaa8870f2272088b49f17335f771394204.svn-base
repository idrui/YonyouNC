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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 上午8:47:25
 */
public class PrayarrangeBodyAfterEditHandler extends
		AbstractCardBodyAfterEditEventHandler {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#getCalculateListener()
	 */
	@Override
	public AbstractRelationCalculateListener getCalculateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
	 */
	@Override
	public void registerEventListener(
			Map<String, ICardBodyAfterEditEventListener> listenerMap) {
		//采购员
		listenerMap.put(PraybillItemVO.PK_EMPLOYEE, new Employee());
		//采购组织
		listenerMap.put(PraybillItemVO.PK_PURCHASEORG, new PurchaseOrg());
		//建议供应商
		listenerMap.put(PraybillItemVO.PK_SUGGESTSUPPLIER, new SuggestSupplier());
	}

}
