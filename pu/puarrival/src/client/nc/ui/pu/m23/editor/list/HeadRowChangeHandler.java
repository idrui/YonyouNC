package nc.ui.pu.m23.editor.list;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

/**
 * �б��ͷ�иı��¼���������
 * 
 * @since 6.0
 * @version 2011-5-7 ����12:54:44
 * @author zhaoyha
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
   * @param e <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-12 ����11:53:22
   */
  private void setScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils lpu = new ListPanelValueUtils(blp);
    Object oldOrg = lpu.getHeadStringValueAt(oldrow, ArriveHeaderVO.PK_ORG);
    Object newOrg = lpu.getHeadStringValueAt(row, StoreReqAppHeaderVO.PK_ORG);
    if (null == newOrg || newOrg.equals(oldOrg)) {
      return;
    }
    new ArriveUIScaleRule(ClientContext.getInstance().getPk_group())
        .setListScale(blp);
  }

}
