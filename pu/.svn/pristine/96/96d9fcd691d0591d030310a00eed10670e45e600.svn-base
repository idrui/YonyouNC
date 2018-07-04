package nc.ui.pu.pub.view;

import java.util.Collection;

import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.util.BillCardPanelUtils;

/**
 * 采购卡片
 * 
 * @since 6.0
 * @version 2011-1-18 上午10:45:00
 * @author wuxla
 */

public class PUBillForm extends ShowUpableBillForm {

  private static final long serialVersionUID = -4066776166301370901L;

  /**
   * 设置超链接
   */
  private Collection<String> clearHyperlink;

  @Override
  public boolean canBeHidden() {
    return true;
  }

  public Collection<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  @Override
  public void initUI() {
    super.initUI();
    // 处理超链
    this.procHyperlink();
    // 屏蔽批改字段
    this.disableFillableItems();
    this.enableFillableItems();
  }

  public void setClearHyperlink(Collection<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  private void procHyperlink() {
    if (null == this.getClearHyperlink()
        || 0 == this.getClearHyperlink().size()) {
      return;
    }

    BillData billdata = this.getBillCardPanel().getBillData();
    for (String key : this.getClearHyperlink()) {
      BillItem item = billdata.getHeadItem(key);
      // item.setHyperlink(false);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  protected void disableFillableItems() {
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // 封掉所有字段的批改
    cardUtils.disableItemsFill();
  }

  protected void enableFillableItems() {
    // 子类覆写支持可以批改字段
  }
}
