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
 * @version 2011-5-16 ����01:15:36
 * @author wuxla
 */

public class InvoicePublishedViewVO extends AbstractDataView {

  public static class InvoicePublishedViewVOMeta extends DataViewMeta {
    public InvoicePublishedViewVOMeta() {
      this.init();
    }

    private void init() {
      // ��Ʊ�š���Ʊ���ڡ�Ʊ�����ڡ�����id���ɹ���֯id��
      // �ɹ�����id�����λid������Э ��id����˰�ϼơ���Ʊ����
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

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** Ʊ������ getter ���� */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** ��Ʊ���� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoicePublishedViewVOMeta.class);
  }

  /** ������˰�ϼ�(ԭ��) getter ���� */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NTOTALORIGMNY);
  }

  /** �ɹ�����(OID) getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT);
  }

  /** �ɹ�����(VID) getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_DEPT_V);
  }

  /** �ɹ���Ʊ getter ���� */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_INVOICE);
  }

  /** ������֯getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG);
  }

  /** ������֯(VID) getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG_V);
  }

  /** ����Э�� getter ���� */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTERM);
  }

  /** ���λ getter ���� */
  public String getPk_paytosupplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PAYTOSUPPLIER);
  }

  /** �ɹ���֯(OID) getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯(VID) getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG_V);
  }

  /** ��Ʊ�� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

}
