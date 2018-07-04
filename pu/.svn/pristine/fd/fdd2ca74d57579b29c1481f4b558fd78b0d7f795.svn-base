package nc.ui.pu.m21.editor.list;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表界面下行切换事件处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-17 下午03:41:14
 */
public class ListHeadRowChangedEventHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // 设置列表界面精度
    this.setListScale(e);

  }

  private void setListScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    String pk_group = e.getContext().getPk_group();
    BillListPanel blp = e.getBillListPanel();
    Object oldOrg =
        blp.getHeadBillModel().getValueAt(oldrow, OrderHeaderVO.PK_ORG + "_ID");
    Object newOrg =
        blp.getHeadBillModel().getValueAt(row, OrderHeaderVO.PK_ORG + "_ID");
    if (newOrg == null || newOrg.equals(oldOrg)) {
      return;
    }
    new OrderScaleSetter(pk_group).setListScale(blp);
  }

}
