package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ���Ҫְ�������ñ�ͷĬ��ֵ</b>
 * <ul>
 * <li>�����Ӧ�̵�ֵΪ�գ�����������ֶε�ֵ����Ʊ��Ӧ�̡����䷽ʽ�����ʽ�������С��ʺš�������ַ
 * <li>
 * ���û��湤�߻�ù�Ӧ����Ϣ��VO�����ݹ�Ӧ��VO��������ֵ�����š�ҵ��Ա����Ʊ��Ӧ�̡����䷽ʽ������Э�顢���֣����ݱ��ִ���Ĭ�ϻ��ʣ��������С��ʺ�
 * ��������ַ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:14:28
 */
public class SupplierDefaultValue {
  private IKeyValue keyValue;

  public SupplierDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setDefaultValue(SupplierInfo supplier) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (obj == null || supplier == null) {
      // �����Ӧ�̵�ֵΪ�գ�����������ֶε�ֵ����Ʊ��Ӧ�̡����䷽ʽ�����ʽ�������С��ʺš�������ַ
      this.clearVendorValue();
      return;
    }

    // ����
    if (supplier.getRespDepartment() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DEPT)) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT,
          supplier.getRespDepartment());
    }
    // ��������
    if (supplier.getCtranTypeid() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.CTRANTYPEID)) {
      this.keyValue.setHeadValue(OrderHeaderVO.CTRANTYPEID,
          supplier.getCtranTypeid());
      this.keyValue.setHeadValue(OrderHeaderVO.VTRANTYPECODE, 
          supplier.getVtrantypecode());
    }

    if (supplier.getRespDepartment_v() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DEPT_V)) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT_V,
          supplier.getRespDepartment_v());
    }

    // ҵ��Ա
    if (supplier.getRespPerson() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.CEMPLOYEEID)) {
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    // ��Ʊ��Ӧ��
    if (supplier.getBillingSupplier() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
          supplier.getBillingSupplier());
    }
    else {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER, obj);
    }

    // ���䷽ʽ
    if (supplier.getTransportType() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE,
          supplier.getTransportType());
    }

    // ����Э��
    if (supplier.getDefaultPaymentTerm() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
    }

    // ����
    // Ĭ��ֵ����1����Ӧ�̵���Ĭ�Ͻ��ױ��� 2����֯��λ��
    // ���ڲɹ���֯�ı༭�¼����ȣ����Ҳɹ���֯�ı༭�¼����Ѿ�����֯��λ�Ҵ�������ֶΣ�������´�����������������Ĭ��ֵ����
    if (supplier.getDefaultCurrency() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // ��������
    if (supplier.getBankAccount() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // ������ַ
    if (supplier.getAddress() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD,
          supplier.getAddress());
      this.setBodyAddress(supplier.getAddress());
    }

  }
  
  /**
   * ��ϵͳ�ƶ���ʱ���֡����ŵȿ����Ѿ���ֵ�ˣ���ʱ��Ӧ�ø���
   * 
   * @param supplier
   */
  public void setDefaultValueNotClear(SupplierInfo supplier) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (obj == null || supplier == null) {
      return;
    }

    // ����
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DEPT)
        && supplier.getRespDepartment() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT,
          supplier.getRespDepartment());
    }

    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DEPT_V)
        && supplier.getRespDepartment_v() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT_V,
          supplier.getRespDepartment_v());
    }

    // ҵ��Ա
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.CEMPLOYEEID)
        && supplier.getRespPerson() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_INVCSUPLLIER)) {
      // ��Ʊ��Ӧ��
      if (supplier.getBillingSupplier() != null) {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
            supplier.getBillingSupplier());
      }
      else {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER, obj);
      }
    }

    // ���䷽ʽ
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE)
        && supplier.getTransportType() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE,
          supplier.getTransportType());
    }

    // ����Э��
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_PAYTERM)
        && supplier.getDefaultPaymentTerm() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
      OrderPaymentVO[] paymentVOs = PaymentInfo.getOrderPaymentVOs(supplier.getDefaultPaymentTerm());
      BillHelper<OrderVO> helper = (BillHelper<OrderVO>) this.keyValue;
      helper.setBodyVOs(paymentVOs, OrderPaymentVO.class);
    }

    // ����
    // Ĭ��ֵ����1����Ӧ�̵���Ĭ�Ͻ��ױ��� 2����֯��λ��
    // ���ڲɹ���֯�ı༭�¼����ȣ����Ҳɹ���֯�ı༭�¼����Ѿ�����֯��λ�Ҵ�������ֶΣ�������´�����������������Ĭ��ֵ����
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.CORIGCURRENCYID)
        && supplier.getDefaultCurrency() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // ��������
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_BANKDOC)
        && supplier.getBankAccount() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // ������ַ
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DELIVERADD)
        && supplier.getAddress() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD,
          supplier.getAddress());
      this.setBodyAddress(supplier.getAddress());
    }

  }

  private void clearVendorValue() {
    this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_FREECUST, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC, null);
  }

  private void setBodyAddress(String pk_deliveradd) {
    int rows = this.keyValue.getItemCount();
    if (rows > 0) {
      for (int row = 0; row < rows; row++) {
        this.keyValue
            .setBodyValue(row, OrderItemVO.VVENDDEVADDR, pk_deliveradd);
      }
    }
  }
}
