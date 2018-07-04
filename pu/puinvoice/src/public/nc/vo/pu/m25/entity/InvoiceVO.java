/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 下午02:58:00
 */
package nc.vo.pu.m25.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-6-26 下午02:58:00
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m25.entity.InvoiceHeaderVO")
public class InvoiceVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = -7610755647132725938L;

  @Override
  public InvoiceItemVO[] getChildrenVO() {
    return (InvoiceItemVO[]) super.getChildrenVO();
  }

  public InvoiceSettleItemVO[] getInvoiceSettleItemVO() {
    return (InvoiceSettleItemVO[]) super.getChildren(InvoiceSettleItemVO.class);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(InvoiceVOMeta.class);
    return billMeta;
  }

  @Override
  public InvoiceHeaderVO getParentVO() {

    return (InvoiceHeaderVO) super.getParentVO();
  }

  @Override
  public String[] getTableCodes() {
    String[] tabs = super.getTableCodes();
    // 默认第一个tabcode是PK_INVOICE_B，防止vo交换的时候出问题
    for (int i = 0; i < tabs.length; i++) {
      if ("invoicebody".equals(tabs[i])) {
        tabs[i] = tabs[0];
        tabs[0] = "invoicebody";
      }
    }
    return tabs;
  }

  public void setParentVO(InvoiceHeaderVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public void setTableVO(String tableCode,
      CircularlyAccessibleValueObject[] values) {
    if (PUEntity.M25_B_TAB.equals(tableCode)
        || PUEntity.M25_SETTLE_TABLE.equals(tableCode)) {
      super.setChildrenVO(values);
    }
    else {
      super.setTableVO(tableCode, values);
    }
  }

}
