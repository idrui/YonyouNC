package nc.ui.pu.m24.editor;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.vo.pu.m24.entity.PricestlHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表表头行改变事件监听处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 上午08:45:18
 */
public class HeadRowChangeHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // 精度处理
    this.setScale(e);

  }

  /**
   * 方法功能描述：列表的精度处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-21 上午08:45:32
   */
  private void setScale(ListHeadRowChangedEvent e) {
    int oldrow = e.getOldRow();
    int row = e.getRow();
    BillListPanel blp = e.getBillListPanel();
    Object oldOrg =
        blp.getHeadBillModel().getValueAt(oldrow,
            PricestlHeaderVO.PK_ORG + IBillItem.ID_SUFFIX);
    Object newOrg =
        blp.getHeadBillModel().getValueAt(row,
            PricestlHeaderVO.PK_ORG + IBillItem.ID_SUFFIX);
    if (null == newOrg) {
      return;
    }
    if (newOrg.equals(oldOrg)) {
      return;
    }
    new PricestlScaleUtil().setListScale(blp, e.getContext().getPk_group());
  }

}
