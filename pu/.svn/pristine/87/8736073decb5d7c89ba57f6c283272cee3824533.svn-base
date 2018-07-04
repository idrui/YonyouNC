/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-12 上午11:44:09
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表表头行改变事件监听处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-12 上午11:44:09
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
   * @author zhaoyha
   * @time 2010-5-12 上午11:53:22
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
