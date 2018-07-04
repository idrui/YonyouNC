package nc.ui.pu.pub.util;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

public class PuVDefFilterUtil {
  public String vbdef = "vbdef";

  public String vdef = "vdef";

  public String vfree = "vfree";

  public void setOrgForDefRef(BillCardPanel cp, LoginContext lc) {
    // 因为有个性化设置，有可能导致组织为空
    if (StringUtils.isBlank(lc.getPk_org())) {
      return;
    }

    this.setOrgForDefRef(cp.getBillData().getHeadItems(), lc);
    this.setOrgForDefRef(cp.getBillData().getBodyItems(), lc);
    this.setOrgForDefRef(cp.getBillData().getTailItems(), lc);
  }

  public void setVbdef(String vbdef) {
    this.vbdef = vbdef;
  }

  public void setVdef(String vdef) {
    this.vdef = vdef;
  }

  public void setVfree(String vfree) {
    this.vfree = vfree;
  }

  private boolean isNeedFilter(BillItem billItem) {
    if (billItem.getDataType() != IBillItem.UFREF) {
      return false;
    }
    String key = billItem.getKey();
    if (key == null) {
      return false;
    }

    return key.startsWith(this.vdef) || key.startsWith(this.vbdef)
        || key.startsWith(this.vfree);
  }

  private void setOrgForDefRef(BillItem[] billItems, LoginContext lc) {
    if (null == billItems) {
      return;
    }

    for (BillItem billItem : billItems) {
      if (this.isNeedFilter(billItem)) {
        UIRefPane refPanel = (UIRefPane) billItem.getComponent();
        if (refPanel.getRefModel() != null) {
          refPanel.getRefModel().setPk_user(lc.getPk_loginUser());
          refPanel.getRefModel().setPk_group(lc.getPk_group());
          refPanel.getRefModel().setPk_org(lc.getPk_org());
        }
      }
    }
  }
}
