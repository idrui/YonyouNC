/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 ����06:27:58
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.rule.CalculateBodyDays;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ڱ༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-11 ����06:27:58
 */
public class ReqDate implements ICardBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		this.checkReqDate(event);
	}

	private void checkReqDate(CardBodyAfterEditEvent event) {
		// UFDate billDate = (UFDate) event.getBillCardPanel().getHeadItem(
		// PraybillHeaderVO.DBILLDATE).getValueObject();
		// UFDate reqDate = (UFDate) event.getValue();
		//
		// if (new BillDateAndReqDate().isBillDateAfterReqDate(billDate,
		// reqDate)) {
		// MessageDialog.showWarningDlg(null, "����", "�������ڲ���С���빺����");
		// }

		CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
		// �������ںͽ��鶩�����ڴ���
		boolean isExcelImprting = event.isExcelImprting();
		// ����ܲ 2018-03-28 �޸ģ��������excel ����Ļ��Ͳ�����������ֵ��
		if (isExcelImprting) {
			return;
		}
		new CalculateBodyDays().setAdvDays(kv, new int[] { event.getRow() });
	}
}