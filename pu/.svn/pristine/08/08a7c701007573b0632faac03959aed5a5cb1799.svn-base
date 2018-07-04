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
 * 列表表头行改变事件监听处理
 * 
 * @since 6.0
 * @version 2011-5-7 下午12:54:44
 * @author zhaoyha
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
   * @param e <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-12 上午11:53:22
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
