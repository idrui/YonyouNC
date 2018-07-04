/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午05:02:55
 */
package nc.ui.pu.m4t.editor.head;

import nc.ui.pu.m4t.rule.InitialEstScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

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
 * @author wuxla
 * @time 2010-9-7 下午05:02:55
 */
public class ListHeadRowChangedEventHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
   */
  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // 设置列表界面精度
    this.setListScale(e);
  }

  /**
   * 方法功能描述：设置列表界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午04:02:40
   */
  private void setListScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    String pk_group = e.getContext().getPk_group();
    BillListPanel blp = e.getBillListPanel();
    Object oldOrg =
        blp.getHeadBillModel().getValueAt(oldrow,
            InitialEstHeaderVO.PK_ORG + "_ID");
    Object newOrg =
        blp.getHeadBillModel().getValueAt(row,
            InitialEstHeaderVO.PK_ORG + "_ID");
    if ((newOrg == null) || newOrg.equals(oldOrg)) {
      return;
    }
    new InitialEstScaleSetter(pk_group).setListScale(blp);
  }
}
