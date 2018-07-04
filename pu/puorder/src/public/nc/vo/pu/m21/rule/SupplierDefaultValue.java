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
 * <b>本类主要完成以下功能：主要职责是设置表头默认值</b>
 * <ul>
 * <li>如果供应商的值为空，则清空以下字段的值：开票供应商、运输方式、付款方式、开户行、帐号、发货地址
 * <li>
 * 利用缓存工具获得供应商信息的VO，根据供应商VO设置以下值：部门、业务员、开票供应商、运输方式、付款协议、币种（根据币种带出默认汇率）、开户行、帐号
 * 、发货地址
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:14:28
 */
public class SupplierDefaultValue {
  private IKeyValue keyValue;

  public SupplierDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setDefaultValue(SupplierInfo supplier) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (obj == null || supplier == null) {
      // 如果供应商的值为空，则清空以下字段的值：开票供应商、运输方式、付款方式、开户行、帐号、发货地址
      this.clearVendorValue();
      return;
    }

    // 部门
    if (supplier.getRespDepartment() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.PK_DEPT)) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT,
          supplier.getRespDepartment());
    }
    // 订单类型
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

    // 业务员
    if (supplier.getRespPerson() != null
        && null == this.keyValue.getHeadValue(OrderHeaderVO.CEMPLOYEEID)) {
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    // 开票供应商
    if (supplier.getBillingSupplier() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
          supplier.getBillingSupplier());
    }
    else {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER, obj);
    }

    // 运输方式
    if (supplier.getTransportType() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE,
          supplier.getTransportType());
    }

    // 付款协议
    if (supplier.getDefaultPaymentTerm() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
    }

    // 币种
    // 默认值规则：1、供应商档案默认交易币种 2、组织本位币
    // 由于采购组织的编辑事件在先，并且采购组织的编辑事件中已经把组织本位币带入币种字段，因此以下处理满足了以上两个默认值规则
    if (supplier.getDefaultCurrency() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // 开户银行
    if (supplier.getBankAccount() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // 发货地址
    if (supplier.getAddress() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD,
          supplier.getAddress());
      this.setBodyAddress(supplier.getAddress());
    }

  }
  
  /**
   * 外系统推订单时币种、部门等可能已经有值了，此时不应该覆盖
   * 
   * @param supplier
   */
  public void setDefaultValueNotClear(SupplierInfo supplier) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (obj == null || supplier == null) {
      return;
    }

    // 部门
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

    // 业务员
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.CEMPLOYEEID)
        && supplier.getRespPerson() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_INVCSUPLLIER)) {
      // 开票供应商
      if (supplier.getBillingSupplier() != null) {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER,
            supplier.getBillingSupplier());
      }
      else {
        this.keyValue.setHeadValue(OrderHeaderVO.PK_INVCSUPLLIER, obj);
      }
    }

    // 运输方式
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE)
        && supplier.getTransportType() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE,
          supplier.getTransportType());
    }

    // 付款协议
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_PAYTERM)
        && supplier.getDefaultPaymentTerm() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
      OrderPaymentVO[] paymentVOs = PaymentInfo.getOrderPaymentVOs(supplier.getDefaultPaymentTerm());
      BillHelper<OrderVO> helper = (BillHelper<OrderVO>) this.keyValue;
      helper.setBodyVOs(paymentVOs, OrderPaymentVO.class);
    }

    // 币种
    // 默认值规则：1、供应商档案默认交易币种 2、组织本位币
    // 由于采购组织的编辑事件在先，并且采购组织的编辑事件中已经把组织本位币带入币种字段，因此以下处理满足了以上两个默认值规则
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.CORIGCURRENCYID)
        && supplier.getDefaultCurrency() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // 开户银行
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_BANKDOC)
        && supplier.getBankAccount() != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // 发货地址
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
