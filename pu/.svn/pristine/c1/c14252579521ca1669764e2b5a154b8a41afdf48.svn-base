/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 下午01:49:35
 */
package nc.ui.pu.m21.view.sideform;

import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单侧边栏
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-21 下午01:49:35
 */
public class OrderSideFormMediator implements
    IAppEventHandler<CardBodyRowChangedEvent> {

  private BillCardPanel bcp;

  /**
   * 侧边栏的form列表
   */
  private List<ISideFormDisplay> sideFormList;

  /**
   * OrderSideFormMediator 的构造子
   */
  public OrderSideFormMediator() {
    super();
  }

  /**
   * 单独显示某一个form的内容
   */
  public void display(ISideFormDisplay sideForm) {
    sideForm.display(this);
  }

  /**
   * 显示所有form
   */
  public void displayAll() {
    for (ISideFormDisplay sform : this.sideFormList) {
      sform.display(this);
    }
  }

  public BillCardPanel getBillCardPanel() {
    return this.bcp;
  }

  public int getCurrentRow() {
    return this.bcp.getBillTable().getSelectedRow();
  }

  public List<ISideFormDisplay> getSideFormList() {
    return this.sideFormList;
  }

  public Object getValueAtCurrentRow(String key) {
    return this.bcp.getBodyValueAt(this.getCurrentRow(), key);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
   */
  @Override
  public void handleAppEvent(CardBodyRowChangedEvent e) {
    this.bcp = e.getBillCardPanel();
    this.resetAll();
  }

  /**
   * 重置所有form的内容
   */
  public void resetAll() {
    for (ISideFormDisplay sform : this.sideFormList) {
      sform.reset(this);
    }
  }

  public void setSideFormList(List<ISideFormDisplay> sideFormList) {
    this.sideFormList = sideFormList;
  }

}
