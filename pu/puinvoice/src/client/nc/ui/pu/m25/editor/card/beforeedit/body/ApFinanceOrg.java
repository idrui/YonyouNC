/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 ����04:52:38
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ӧ��������֯�༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-31 ����04:52:38
 */
public class ApFinanceOrg implements ICardBodyBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    /*
     * �ǿա����ղɹ��������߲ɹ���ⵥ���ɲɹ���Ʊʱ���Բɹ��������߲ɹ���ⵥ�����Ӧ����֯��Ϊ�ɹ���Ʊ�ı���Ӧ����֯��
     * ���Ҳ��ɱ༭(����Դʱ�Ͳ��ɱ༭)�� ���Ʋɹ���Ʊʱ��Ӧ����֯���ݱ�ͷ������֯����Ĭ��ֵ���ɱ༭��
     * ���ռ��ŷ�Χ����֯����Ϊ������֯����֯��Ԫ¼�롣
     */
    int row = event.getRow();
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    String source = helper.getBodyStringValue(row, InvoiceItemVO.CSOURCEBID);
    if (StringUtil.isEmptyWithTrim(source)) {
      event.setReturnValue(Boolean.TRUE);
    }
    else {
      event.setReturnValue(Boolean.FALSE);
    }

  }

}
