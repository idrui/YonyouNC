package nc.ui.pu.ref;

import nc.ui.bd.ref.model.SupAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;

/**
 * 过滤供应商发货地址工具类。
 * 
 * @since 6.3
 * @version 2012-12-18 下午04:35:58
 * @author lixyp
 */
public class FilterSuppAddressRefUtils {

  private UIRefPane pane;

  public FilterSuppAddressRefUtils(UIRefPane pane) {
    this.pane = pane;
  }

  public void filteRefBySupplier(String pk_supplier, String pk_org) {
    SupAddressDefaultRefModel model =
        (SupAddressDefaultRefModel) this.pane.getRefModel();
    model.setPk_supplier(pk_supplier);
    model.setPk_org(pk_org);
    model.setWherePart("");
  }
}
