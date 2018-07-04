package nc.ui.pu.ref;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;

/**
 * �����ջ���ַ�����ࡣ
 * 
 * @since 6.3
 * @version 2012-12-18 ����04:35:58
 * @author lixyp
 */
public class FilterCustAddressRefUtils {

  private UIRefPane pane;

  public FilterCustAddressRefUtils(UIRefPane pane) {
    this.pane = pane;
  }

  public void filteRefByCustomer(String pk_customer, String pk_org) {
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) this.pane.getRefModel();
    model.setPk_customer(pk_customer);
    if (pk_org != null) {
      model.setPk_org(pk_org);
    }
    model.setWherePart("");
  }
}
