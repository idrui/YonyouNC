package nc.ui.pu.m21.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购组织的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:21:24
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {
  private PUBillForm cardForm_Add;

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    this.setOrgChangedEvent(e);
    if (this.cardForm_Add != null && this.cardForm_Add.isVisible()) {
      new PurchaseOrganization().process(this.cardForm_Add);
    }
    else if (this.getCardForm() != null && this.getCardForm().isVisible()) {
      new PurchaseOrganization().process(this.getCardForm());
    }
  }

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    //
  }

  public void setCardForm_Add(PUBillForm cardForm_Add) {
    this.cardForm_Add = cardForm_Add;
  }

}
