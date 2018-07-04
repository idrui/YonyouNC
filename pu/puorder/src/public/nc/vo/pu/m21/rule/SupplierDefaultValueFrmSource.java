/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����02:34:41
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ת�����ɲɹ�����ʱ�����ñ�ͷĬ��ֵ�����ĳ��Ϊ�գ�������Ĭ��ֵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-24 ����02:34:41
 */
public class SupplierDefaultValueFrmSource {

  private IKeyValue keyValue;

  public SupplierDefaultValueFrmSource(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setDefaultValue(SupplierInfo supplier) {
    if (null == supplier) {
      return;
    }
    // ���ο��ܴ���ҵ��Ա�벿�Ų�ƥ�����
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_DEPT))
        && ObjectUtil.isEmptyWithTrim(this.keyValue
            .getHeadValue(OrderHeaderVO.CEMPLOYEEID))) {
      // ����
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT,
          supplier.getRespDepartment());
      // ҵ��Ա
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    // ��Ʊ��Ӧ��
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_INVCSUPLLIER))) {
      if (supplier.getBillingSupplier() != null) {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
            supplier.getBillingSupplier());
      }
      else {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
            this.keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER));
      }
    }

    // ����Э��
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_PAYTERM))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
    }

    // ����
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.CORIGCURRENCYID))) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // ��������
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_BANKDOC))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // ������ַ
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_DELIVERADD))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD,
          supplier.getAddress());
    }
  }
}
