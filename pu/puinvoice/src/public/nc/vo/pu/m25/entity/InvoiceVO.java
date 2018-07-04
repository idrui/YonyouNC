/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 ����02:58:00
 */
package nc.vo.pu.m25.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-6-26 ����02:58:00
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
   * ���෽����д
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
    // Ĭ�ϵ�һ��tabcode��PK_INVOICE_B����ֹvo������ʱ�������
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
