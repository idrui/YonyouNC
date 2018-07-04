package nc.ui.pu.m27.settlebill.editor.list;

import nc.ui.pu.m27.settlebill.editor.SettleBillScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�б���������л��¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-17 ����03:41:14
 */
public class ListHeadRowChangedEventHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // �����б���澫��
    this.setListScale(e);
  }

  private void setListScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    String pk_group = e.getContext().getPk_group();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils blpUtil = new ListPanelValueUtils(blp);
    Object oldOrg =
        blpUtil.getHeadStringValueAt(oldrow, SettleBillHeaderVO.PK_ORG);
    Object newOrg =
        blpUtil.getHeadStringValueAt(row, SettleBillHeaderVO.PK_ORG);
    if (newOrg == null || newOrg.equals(oldOrg)) {
      return;
    }
    new SettleBillScaleSetter(pk_group).setListScale(blp);
  }

}
