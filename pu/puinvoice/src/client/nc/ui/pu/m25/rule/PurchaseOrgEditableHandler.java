/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-7 ����11:16:53
 */
package nc.ui.pu.m25.rule;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ�Ĳɹ���֯�ɱ༭�Դ�����<br>
 * �������ɲɹ���Ʊʱ���ݲɹ��������߲ɹ���ⵥ��ͷ�ɹ���֯���룬 ����ǿգ��򲻿ɱ༭�����Ϊ��ʱ����¼�룬������֯����Ϊ�ɹ���֯����֯��Ԫ¼�롣
 * �ǿա�Ĭ��ֵ���������Ʊ���������֯��ѡ�˲ɹ���֯���ԣ���ȡ����֯��Ϊ�ɹ���֯���ֹ�¼�롣
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-7 ����11:16:53
 */
public class PurchaseOrgEditableHandler {
  private BillCardPanel bcp;

  /**
   * PurchaseOrgEditableHandler �Ĺ�����
   * 
   * @param bcp
   */
  public PurchaseOrgEditableHandler(BillCardPanel bcp) {
    this.bcp = bcp;
  }

  public void setEditable() {
    CardEditorHelper helper = new CardEditorHelper(this.bcp);
    // BillItem item = this.bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG);
    BillItem item = this.bcp.getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG_V);
    if (!item.isEnabled()) {
      return;// ģ�����ò��ɱ༭��ֱ�ӷ���
    }
    item.setEnabled(this.isEditable(helper));
  }

  private boolean isEditable(CardEditorHelper helper) {
    int rowcount = helper.getItemCount();
    if (0 == rowcount) {
      return true;
    }
    String pk_purorg =
        helper.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    if (StringUtil.isEmptyWithTrim(pk_purorg)) {
      return true;
    }
    for (int i = 0; i < rowcount; ++i) {
      // ����Դ����ֵʱ���ɱ༭
      if (!StringUtil.isEmptyWithTrim(helper.getBodyStringValue(i,
          InvoiceItemVO.CSOURCEBID))) {
        return false;
      }
    }
    return true;// ����ʱ�ɱ༭
  }

}
