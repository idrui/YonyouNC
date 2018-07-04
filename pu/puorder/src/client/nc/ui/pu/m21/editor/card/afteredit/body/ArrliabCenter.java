package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ջ��������ĵı༭���¼�������
 * <li>�ж��Ƿ�����ջ��ֿ�
 * <li>..01
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.35
 * @since 6.35
 * @author guoyk
 * @time 2014��9��26��
 */
public class ArrliabCenter implements ICardBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
		//��ȡ�༭����
		int row = event.getRow();
		String stordoc = card.getBodyStringValue(row, OrderItemVO.PK_RECVSTORDOC);
		// ����ֿ�Ϊ�գ�ֱ�ӷ���
		if(stordoc == null){
		  return ;
		}
		StordocVO[] stordocvo = StordocPubService.queryStordocByPks(new String[]{stordoc},
		    new String[]{StordocVO.PROFITCENTRE});
		String arrliabCenter = stordocvo[0].getProfitcentre();
		if(arrliabCenter == null || arrliabCenter.equals(event.getValue())){
		  return;
		}
		//�༭�ջ��������ĺ�����ջ��ֿ�.
	  card.setBodyValue(row, OrderItemVO.PK_RECVSTORDOC, null);
	}

}
