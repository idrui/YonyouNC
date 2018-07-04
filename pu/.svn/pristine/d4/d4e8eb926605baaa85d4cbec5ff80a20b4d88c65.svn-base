/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午02:34:41
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>转单生成采购订单时，设置表头默认值。如果某项为空，则设置默认值。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-24 下午02:34:41
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
    // 上游可能存在业务员与部门不匹配情况
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_DEPT))
        && ObjectUtil.isEmptyWithTrim(this.keyValue
            .getHeadValue(OrderHeaderVO.CEMPLOYEEID))) {
      // 部门
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DEPT,
          supplier.getRespDepartment());
      // 业务员
      this.keyValue.setHeadValue(OrderHeaderVO.CEMPLOYEEID,
          supplier.getRespPerson());
    }

    // 开票供应商
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

    // 付款协议
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_PAYTERM))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_PAYTERM,
          supplier.getDefaultPaymentTerm());
    }

    // 币种
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.CORIGCURRENCYID))) {
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID,
          supplier.getDefaultCurrency());
    }

    // 开户银行
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_BANKDOC))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_BANKDOC,
          supplier.getBankAccount());
    }

    // 发货地址
    if (ObjectUtil.isEmptyWithTrim(this.keyValue
        .getHeadValue(OrderHeaderVO.PK_DELIVERADD))) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_DELIVERADD,
          supplier.getAddress());
    }
  }
}
