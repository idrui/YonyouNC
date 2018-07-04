package nc.ui.pu.pub.editor.org;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购公共抽象类，处理主组织的变化事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-24 下午03:18:30
 */
public abstract class AbstractOrgChangedEventHandler implements
    IAppEventHandler<OrgChangedEvent> {

  /**
   * 卡片
   */
  private ShowUpableBillForm cardForm;

  /**
   * 是否第一次处理事件
   */
  private boolean isFirstFlag = true;

  /**
   * 主组织变化事件的监听类
   */
  private List<IOrgChangedEventListener> listenerList =
      new ArrayList<IOrgChangedEventListener>();

  /** 列表 **/
  private ShowUpableBillListView listView;

  /**
   * 组织改变事件
   */
  private OrgChangedEvent orgChangedEvent;

  public AbstractOrgChangedEventHandler() {
    // 缺省构造方法
  }

  public ShowUpableBillForm getCardForm() {
    return this.cardForm;
  }

  /**
   * @return listView
   */
  public ShowUpableBillListView getListView() {
    return this.listView;
  }

  public OrgChangedEvent getOrgChangedEvent() {
    return this.orgChangedEvent;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {

    this.setOrgChangedEvent(e);

    // 如果是第一次处理事件，注册事件监听类
    if (this.isFirstFlag) {
      this.registerEventListener(this.listenerList);
      this.setFirstFlag(false);
    }

    for (IOrgChangedEventListener eventListener : this.listenerList) {
      eventListener.process(this.cardForm);
    }
  }

  /**
   * 方法功能描述：注册主组织变化事件的监听类，由子类实现
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   *         实现了IOrgChangedEventListener接口的监听类List
   * @since 6.0
   * @author hanbin
   * @time 2010-2-24 下午03:21:00
   */
  public abstract void registerEventListener(
      List<IOrgChangedEventListener> listenerList1);

  public void setCardForm(ShowUpableBillForm cardForm) {
    this.cardForm = cardForm;
  }

  public void setFirstFlag(boolean isFirstFlag) {
    this.isFirstFlag = isFirstFlag;
  }

  /**
   * @param listView
   *          要设置的 listView
   */
  public void setListView(ShowUpableBillListView listView) {
    this.listView = listView;
  }

  public void setOrgChangedEvent(OrgChangedEvent orgChangedEvent) {
    this.orgChangedEvent = orgChangedEvent;
  }
}
