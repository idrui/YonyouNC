/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-12 ����11:44:09
 */
package nc.ui.pu.m25.editor.list;

import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�б��ͷ�иı��¼���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-12 ����11:44:09
 */
public class HeadRowChangeHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // ���ȴ���
    this.setScale(e);

  }

  /**
   * ���������������б�ľ��ȴ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param e
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-12 ����11:53:22
   */
  private void setScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    BillListPanel blp = e.getBillListPanel();
    Object oldOrg =
        blp.getHeadBillModel().getValueAt(oldrow,
            InvoiceHeaderVO.PK_ORG + IBillItem.ID_SUFFIX);
    Object newOrg =
        blp.getHeadBillModel().getValueAt(row,
            InvoiceHeaderVO.PK_ORG + IBillItem.ID_SUFFIX);
    if (null == newOrg) {
      return;
    }
    if (newOrg.equals(oldOrg)) {
      return;
    }
    new InvoiceUIScaleProcessor().setListScale(blp, e.getContext()
        .getPk_group());
  }

}
