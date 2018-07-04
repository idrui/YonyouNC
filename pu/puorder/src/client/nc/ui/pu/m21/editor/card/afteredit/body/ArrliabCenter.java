package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货利润中心的编辑后事件处理类
 * <li>判断是否清空收货仓库
 * <li>..01
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.35
 * @since 6.35
 * @author guoyk
 * @time 2014年9月26日
 */
public class ArrliabCenter implements ICardBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
		//获取编辑的行
		int row = event.getRow();
		String stordoc = card.getBodyStringValue(row, OrderItemVO.PK_RECVSTORDOC);
		// 如果仓库为空，直接返回
		if(stordoc == null){
		  return ;
		}
		StordocVO[] stordocvo = StordocPubService.queryStordocByPks(new String[]{stordoc},
		    new String[]{StordocVO.PROFITCENTRE});
		String arrliabCenter = stordocvo[0].getProfitcentre();
		if(arrliabCenter == null || arrliabCenter.equals(event.getValue())){
		  return;
		}
		//编辑收货利润中心后，清空收货仓库.
	  card.setBodyValue(row, OrderItemVO.PK_RECVSTORDOC, null);
	}

}
