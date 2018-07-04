package nc.vo.pu.report.view.order;

import java.sql.Types;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * @since 6.0
 * @version 2011-9-14 下午07:36:29
 * @author wuxla
 */

public class OrderPayExecViewMeta extends DataViewMeta {

  public static final String CCURRENCYID = "ccurrencyid";

  public static final String NINVOICEBALANCE = "ninvoicebalance";

  public static final String NINVOICEMNY = "ninvoicemny";

  public static final String NORDERBALANCE = "norderbalance";

  public static final String NORDERMNY = "nordermny";

  public static final String NPAYMNY = "npaymny";

  public static final String NUNVERIFYMNY = "nunverifymny";

  /**
   * 临时表名
   */
  public static final String ORDERPAYEXECTABLE = "t_pu_orderpayexec";

  public static final String PK_ORDER = "pk_order";

  public static final String PK_SUPPLIER = "pk_supplier";

  public static final String VBILLCODE = "vbillcode";

  public static final String VCOOPORDERCODE = "vcoopordercode";

  private RptExtAttribute[] extattrs;

  public OrderPayExecViewMeta() {
    this.init();
  }

  public JavaType[] getJavaType() {
    JavaType[] javaTypes = new JavaType[this.extattrs.length];
    for (int i = 0; i < this.extattrs.length; ++i) {
      javaTypes[i] = this.extattrs[i].getJavaType();
    }
    return javaTypes;
  }

  private void addExtAttribute() {
    for (RptExtAttribute attr : this.extattrs) {
      Attribute attribute = new Attribute(attr.getName(), null, null);
      attribute.setJavaType(attr.getJavaType());
      attribute.setCustom(false);
      attribute.setStatic(false);
      attribute.setPersistence(false);
      attribute.setSerializable(true);
      this.add(attribute);
    }
  }

  private void init() {
    this.initExtAttrs();
    this.addExtAttribute();
  }

  private void initExtAttrs() {
    // 订单编号
    // 对方订单号
    // 供应商
    // 币种
    // 订单本币金额
    // 发票本币金额
    // 订单付款金额
    // 未核销付款金额
    // 发票应付余额
    // 订单应付余额
    // 采购组织OID
    this.extattrs = new RptExtAttribute[11];
    this.extattrs[0] =
        new RptExtAttribute(OrderPayExecViewMeta.VBILLCODE, Types.VARCHAR,
            JavaType.String);
    this.extattrs[1] =
        new RptExtAttribute(OrderPayExecViewMeta.VCOOPORDERCODE, Types.VARCHAR,
            JavaType.String);
    this.extattrs[2] =
        new RptExtAttribute(OrderPayExecViewMeta.PK_SUPPLIER, Types.VARCHAR,
            JavaType.String);
    this.extattrs[3] =
        new RptExtAttribute(OrderPayExecViewMeta.CCURRENCYID, Types.VARCHAR,
            JavaType.String);
    this.extattrs[4] =
        new RptExtAttribute(OrderPayExecViewMeta.NORDERMNY, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[5] =
        new RptExtAttribute(OrderPayExecViewMeta.NINVOICEMNY, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[6] =
        new RptExtAttribute(OrderPayExecViewMeta.NPAYMNY, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[7] =
        new RptExtAttribute(OrderPayExecViewMeta.NUNVERIFYMNY, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[8] =
        new RptExtAttribute(OrderPayExecViewMeta.NINVOICEBALANCE,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[9] =
        new RptExtAttribute(OrderPayExecViewMeta.NORDERBALANCE, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[10] =
        new RptExtAttribute(OrderPayExecViewMeta.PK_ORDER, Types.VARCHAR,
            JavaType.String);
  }
}
