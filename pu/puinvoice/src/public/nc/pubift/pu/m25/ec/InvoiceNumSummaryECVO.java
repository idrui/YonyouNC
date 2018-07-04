package nc.pubift.pu.m25.ec;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

public class InvoiceNumSummaryECVO implements Serializable {

  private static final long serialVersionUID = -7445438982545325199L;

  /** 开票辅数量 */
  private UFDouble nnum = null;

  /** 开票价税合计（原币含税金额） */
  private UFDouble norigtaxmny = null;

  /** 采购订单表体PK */
  private String pk_order_b = null;

  /** 物料OID */
  private String pk_srcmaterial = null;

  /** 开票辅数量 getter 方法 */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /** 开票价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return this.norigtaxmny;
  }

  /** 采购订单表体PK getter 方法 */
  public String getPk_order_b() {
    return this.pk_order_b;
  }

  /** 物料OID getter 方法 */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /** 开票辅数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /** 开票价税合计 setter 方法 */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.norigtaxmny = norigtaxmny;
  }

  /** 采购订单表体PK setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.pk_order_b = pk_order_b;
  }

  /** 物料OID setter */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }
}
