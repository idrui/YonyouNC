package nc.vo.pu.report.view.order;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-9-16 ÏÂÎç04:06:15
 * @author wuxla
 */

public class OrderPayExecViewVO extends AbstractDataView {
  private static final long serialVersionUID = -5924098230808330820L;

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderPayExecViewMeta.class);
  }

  public UFDouble getNinvoicebalance() {
    return (UFDouble) this
        .getAttributeValue(OrderPayExecViewMeta.NINVOICEBALANCE);
  }

  public UFDouble getNinvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderPayExecViewMeta.NINVOICEMNY);
  }

  public UFDouble getNorderbalance() {
    return (UFDouble) this
        .getAttributeValue(OrderPayExecViewMeta.NORDERBALANCE);
  }

  public UFDouble getNordermny() {
    return (UFDouble) this.getAttributeValue(OrderPayExecViewMeta.NORDERMNY);
  }

  public UFDouble getNpaymny() {
    return (UFDouble) this.getAttributeValue(OrderPayExecViewMeta.NPAYMNY);
  }

  public UFDouble getNunverifymny() {
    return (UFDouble) this.getAttributeValue(OrderPayExecViewMeta.NUNVERIFYMNY);
  }

  public String getPk_order() {
    return (String) this.getAttributeValue(OrderPayExecViewMeta.PK_ORDER);
  }

  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderPayExecViewMeta.PK_SUPPLIER);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderPayExecViewMeta.VBILLCODE);
  }

  public String getVcoopordercode() {
    return (String) this.getAttributeValue(OrderPayExecViewMeta.VCOOPORDERCODE);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(OrderPayExecViewMeta.CCURRENCYID, ccurrencyid);
  }

  public void setNinvoicebalance(UFDouble ninvoicebalance) {
    this.setAttributeValue(OrderPayExecViewMeta.NINVOICEBALANCE,
        ninvoicebalance);
  }

  public void setNinvoicemny(UFDouble ninvoicemny) {
    this.setAttributeValue(OrderPayExecViewMeta.NINVOICEMNY, ninvoicemny);
  }

  public void setNorderbalance(UFDouble norderbalance) {
    this.setAttributeValue(OrderPayExecViewMeta.NORDERBALANCE, norderbalance);
  }

  public void setNordermny(UFDouble nordermny) {
    this.setAttributeValue(OrderPayExecViewMeta.NORDERMNY, nordermny);
  }

  public void setNpaymny(UFDouble npaymny) {
    this.setAttributeValue(OrderPayExecViewMeta.NPAYMNY, npaymny);
  }

  public void setNunverifymny(UFDouble nunverifymny) {
    this.setAttributeValue(OrderPayExecViewMeta.NUNVERIFYMNY, nunverifymny);
  }

  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderPayExecViewMeta.PK_ORDER, pk_order);
  }

  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderPayExecViewMeta.PK_SUPPLIER, pk_supplier);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderPayExecViewMeta.VBILLCODE, vbillcode);
  }

  public void setVcoopordercode(String vcoopordercode) {
    this.setAttributeValue(OrderPayExecViewMeta.VCOOPORDERCODE, vcoopordercode);
  }

}
