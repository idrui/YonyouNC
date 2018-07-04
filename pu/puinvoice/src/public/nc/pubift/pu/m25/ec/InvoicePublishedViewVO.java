package nc.pubift.pu.m25.ec;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:15:36
 * @author wuxla
 */

public class InvoicePublishedViewVO extends AbstractDataView {

  public static class InvoicePublishedViewVOMeta extends DataViewMeta {
    public InvoicePublishedViewVOMeta() {
      this.init();
    }

    private void init() {
      // 发票号、发票日期、票到日期、币种id、采购组织id、
      // 采购部门id、付款单位id、付款协 议id、价税合计、发票主键
      this.add(InvoiceHeaderVO.class, new String[] {
        InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.PK_ORG_V,
        InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.DBILLDATE,
        InvoiceHeaderVO.DARRIVEDATE, InvoiceHeaderVO.CORIGCURRENCYID,
        InvoiceHeaderVO.PK_PURCHASEORG, InvoiceHeaderVO.PK_PURCHASEORG_V,
        InvoiceHeaderVO.PK_DEPT, InvoiceHeaderVO.PK_DEPT_V,
        InvoiceHeaderVO.PK_PAYTOSUPPLIER, InvoiceHeaderVO.PK_PAYTERM,
        InvoiceHeaderVO.NTOTALORIGMNY, InvoiceHeaderVO.PK_INVOICE
      });
    }
  }

  private static final long serialVersionUID = 1L;

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** 票到日期 getter 方法 */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** 发票日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoicePublishedViewVOMeta.class);
  }

  /** 整单价税合计(原币) getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY);
  }

  /** 采购部门(OID) getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT);
  }

  /** 采购部门(VID) getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT_V);
  }

  /** 采购发票 getter 方法 */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_INVOICE);
  }

  /** 财务组织getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG);
  }

  /** 财务组织(VID) getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG_V);
  }

  /** 付款协议 getter 方法 */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTERM);
  }

  /** 付款单位 getter 方法 */
  public String getPk_paytosupplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER);
  }

  /** 采购组织(OID) getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** 采购组织(VID) getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V);
  }

  /** 发票号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

}
