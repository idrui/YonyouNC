/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午03:43:14
 */
package nc.vo.pu.m21.entity;

import java.math.BigDecimal;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单付款执行情况VO类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-8 下午03:43:14
 */
public class OrderPayExecStatVO extends CircularlyAccessibleValueObject {

  private static final long serialVersionUID = 7201878443327072371L;

  // 应付余额 应付接口：订单的发票所对应所有应付单的原币余额合计
  private UFDouble napmny;

  // 订单累计发票价税合计
  private UFDouble ninvoicemny;

  // 订单未核销预付款 应付接口
  private UFDouble nnotcheckpaymny;

  // 原币预付款
  private UFDouble norgprepaymny;

  // 原币价税合计
  private UFDouble norigtaxmny;

  // 订单累计付款金额
  private UFDouble npaymny;

  // 订单应付余额 应付余额—未核销预付款
  private UFDouble npopaybalance;

  // 采购订单ID
  private String pk_order;

  // 供应商基本信息ID
  private String pk_supplier;

  // 订单编号
  private String vbillcode;

  public OrderPayExecStatVO() {
    super();
  }

  /**
   * @return serialversionuid
   */
  public static long getSerialversionuid() {
    return OrderPayExecStatVO.serialVersionUID;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
   */
  @Override
  public String[] getAttributeNames() {
    return new String[] {
      "pk_order", "pk_supplier", "vbillcode", "norigtaxmny", "norgprepaymny",
      "ninvoicemny", "npaymny", "napmny", "nnotcheckpaymny", "npopaybalance"
    };
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String attributeName) {
    if (attributeName.equals("pk_order")) {
      return this.pk_order;
    }
    else if (attributeName.equals("pk_supplier")) {
      return this.pk_supplier;
    }
    else if (attributeName.equals("vbillcode")) {
      return this.vbillcode;
    }
    else if (attributeName.equals("norigtaxmny")) {
      return this.norigtaxmny;
    }
    else if (attributeName.equals("norgprepaymny")) {
      return this.norgprepaymny;
    }
    else if (attributeName.equals("ninvoicemny")) {
      return this.ninvoicemny;
    }
    else if (attributeName.equals("npaymny")) {
      return this.npaymny;
    }
    else if (attributeName.equals("napmny")) {
      return this.napmny;
    }
    else if (attributeName.equals("nnotcheckpaymny")) {
      return this.nnotcheckpaymny;
    }
    else if (attributeName.equals("npopaybalance")) {
      return this.npopaybalance;
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ValueObject#getEntityName()
   */
  @Override
  public String getEntityName() {
    return OrderPayExecStatVO.class.getName();
  }

  /**
   * @return napmny
   */
  public UFDouble getNapmny() {
    return this.napmny;
  }

  /**
   * @return ninvoicemny
   */
  public UFDouble getNinvoicemny() {
    return this.ninvoicemny;
  }

  /**
   * @return nnotcheckpaymny
   */
  public UFDouble getNnotcheckpaymny() {
    return this.nnotcheckpaymny;
  }

  /**
   * @return norgprepaymny
   */
  public UFDouble getNorgprepaymny() {
    return this.norgprepaymny;
  }

  /**
   * @return norigtaxmny
   */
  public UFDouble getNorigtaxmny() {
    return this.norigtaxmny;
  }

  /**
   * @return npaymny
   */
  public UFDouble getNpaymny() {
    return this.npaymny;
  }

  /**
   * @return npopaybalance
   */
  public UFDouble getNpopaybalance() {
    return this.npopaybalance;
  }

  /**
   * @return pk_order
   */
  public String getPk_order() {
    return this.pk_order;
  }

  /**
   * @return pk_supplier
   */
  public String getPk_supplier() {
    return this.pk_supplier;
  }

  /**
   * @return vbillcode
   */
  public String getVbillcode() {
    return this.vbillcode;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String,
   *      java.lang.Object)
   */
  @Override
  public void setAttributeValue(String attributeName, Object value) {
    try {
      if (attributeName.equals("pk_order")) {
        this.pk_order = this.getString_TrimZeroLenAsNull(value);
      }
      else if (attributeName.equals("pk_supplier")) {
        this.pk_supplier = this.getString_TrimZeroLenAsNull(value);
      }
      else if (attributeName.equals("vbillcode")) {
        this.vbillcode = this.getString_TrimZeroLenAsNull(value);
      }
      else if (attributeName.equals("norigtaxmny")) {
        this.norigtaxmny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("norgprepaymny")) {
        this.norgprepaymny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("ninvoicemny")) {
        this.ninvoicemny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("npaymny")) {
        this.npaymny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("napmny")) {
        this.napmny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("nnotcheckpaymny")) {
        this.nnotcheckpaymny = this.getUFDouble_ZeroAsNull(value);
      }
      else if (attributeName.equals("npopaybalance")) {
        this.npopaybalance = this.getUFDouble_ZeroAsNull(value);
      }
    }
    catch (ClassCastException e) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0297", null, new String[] {
            attributeName, (String) value
          })/* setAttributeValue方法中为 {0} 赋值时类型转换错误！（值：{1}） */);
    }

  }

  /**
   * @param napmny
   *          要设置的 napmny
   */
  public void setNapmny(UFDouble napmny) {
    this.napmny = napmny;
  }

  /**
   * @param ninvoicemny
   *          要设置的 ninvoicemny
   */
  public void setNinvoicemny(UFDouble ninvoicemny) {
    this.ninvoicemny = ninvoicemny;
  }

  /**
   * @param nnotcheckpaymny
   *          要设置的 nnotcheckpaymny
   */
  public void setNnotcheckpaymny(UFDouble nnotcheckpaymny) {
    this.nnotcheckpaymny = nnotcheckpaymny;
  }

  /**
   * @param norgprepaymny
   *          要设置的 norgprepaymny
   */
  public void setNorgprepaymny(UFDouble norgprepaymny) {
    this.norgprepaymny = norgprepaymny;
  }

  /**
   * @param norigtaxmny
   *          要设置的 norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.norigtaxmny = norigtaxmny;
  }

  /**
   * @param npaymny
   *          要设置的 npaymny
   */
  public void setNpaymny(UFDouble npaymny) {
    this.npaymny = npaymny;
  }

  /**
   * @param npopaybalance
   *          要设置的 npopaybalance
   */
  public void setNpopaybalance(UFDouble npopaybalance) {
    this.npopaybalance = npopaybalance;
  }

  /**
   * @param pkOrder
   *          要设置的 pk_order
   */
  public void setPk_order(String pkOrder) {
    this.pk_order = pkOrder;
  }

  /**
   * @param pkSupplier
   *          要设置的 pk_supplier
   */
  public void setPk_supplier(String pkSupplier) {
    this.pk_supplier = pkSupplier;
  }

  /**
   * @param vbillcode
   *          要设置的 vbillcode
   */
  public void setVbillcode(String vbillcode) {
    this.vbillcode = vbillcode;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ValueObject#validate()
   */
  @Override
  public void validate() throws ValidationException {
    return;
  }

  /**
   * 方法功能描述：根据一个对象的值得到String的值，如果为空串，返回空
   * <p>
   * <b>参数说明</b>
   * 
   * @param value
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-8 下午04:21:25
   */
  private String getString_TrimZeroLenAsNull(Object value) {
    if (value == null || value.toString().trim().length() == 0) {
      return null;
    }
    return value.toString();
  }

  /**
   * 方法功能描述：根据一个对象的值得到UFDouble的值，如果为空，返回零
   * <p>
   * <b>参数说明</b>
   * 
   * @param value
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-8 下午04:33:48
   */
  private UFDouble getUFDouble_NullAsZero(Object value) {
    if (value == null || value.toString().trim().equals("")) {
      return UFDouble.ZERO_DBL;
    }
    else if (value instanceof UFDouble) {
      return (UFDouble) value;
    }
    else if (value instanceof BigDecimal) {
      return new UFDouble((BigDecimal) value);
    }
    else {
      return new UFDouble(value.toString().trim());
    }
  }

  /**
   * 方法功能描述：根据一个对象的值得到UFDouble的值，如果为零，返回空
   * <p>
   * <b>参数说明</b>
   * 
   * @param value
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-8 下午04:33:44
   */
  private UFDouble getUFDouble_ZeroAsNull(Object value) {
    UFDouble dValue = this.getUFDouble_NullAsZero(value);
    if (dValue.compareTo(UFDouble.ZERO_DBL) == 0) {
      return null;
    }
    return dValue;
  }

}
