package nc.vo.pu.m21.entity;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;

/**
 * 应付款VO
 * 
 * @since 6.0
 * @version 2011-3-9 上午08:02:07
 * @author wuxla
 */

public class ApDataVO extends CircularlyAccessibleValueObject {

  public static final String CCURRENCYID = "ccurrencyid";

  public static final String NCREDITBOUND = "ncreditbound";

  public static final String NFINANCEAP = "nfinanceap";

  public static final String NOPERATIONAP = "noperationap";

  public static final String NORDERAP = "norderap";

  public static final String NORDERPAYMNY = "norderpaymny";

  public static final String NUNVERIFYMNY = "nunverifymny";

  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  private static final long serialVersionUID = 3090752072603372151L;

  /**
   * 币种
   */
  private String ccurrencyid;

  /**
   * 供应商信用
   */
  private UFDouble ncreditbound;

  /**
   * 财务应付
   */
  private UFDouble nfinanceap;

  /**
   * 业务应付
   */
  private UFDouble noperationap;

  /**
   * 订单应付
   */
  private UFDouble norderap;

  /**
   * 订单付款
   */
  private UFDouble norderpaymny;

  /**
   * 付款未核销金额
   */
  private UFDouble nunverifymny;

  private String pk_apfinanceorg;

  private String pk_apfinanceorg_v;

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      ApDataVO.PK_APFINANCEORG_V, ApDataVO.PK_APFINANCEORG, ApDataVO.NORDERAP,
      ApDataVO.NOPERATIONAP, ApDataVO.NFINANCEAP, ApDataVO.NORDERPAYMNY,
      ApDataVO.NUNVERIFYMNY, ApDataVO.NCREDITBOUND, ApDataVO.CCURRENCYID
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    if (ApDataVO.PK_APFINANCEORG_V.equals(attributeName)) {
      return this.pk_apfinanceorg_v;
    }
    else if (ApDataVO.PK_APFINANCEORG.equals(attributeName)) {
      return this.pk_apfinanceorg;
    }
    else if (ApDataVO.NORDERAP.equals(attributeName)) {
      return this.norderap;
    }
    else if (ApDataVO.NOPERATIONAP.equals(attributeName)) {
      return this.noperationap;
    }
    else if (ApDataVO.NFINANCEAP.equals(attributeName)) {
      return this.nfinanceap;
    }
    else if (ApDataVO.NORDERPAYMNY.equals(attributeName)) {
      return this.norderpaymny;
    }
    else if (ApDataVO.NUNVERIFYMNY.equals(attributeName)) {
      return this.nunverifymny;
    }
    else if (ApDataVO.NCREDITBOUND.equals(attributeName)) {
      return this.ncreditbound;
    }
    else if (ApDataVO.CCURRENCYID.equals(attributeName)) {
      return this.ccurrencyid;
    }
    return null;
  }

  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public UFDouble getNcreditbound() {
    return this.ncreditbound;
  }

  public UFDouble getNfinanceap() {
    return this.nfinanceap;
  }

  public UFDouble getNoperationap() {
    return this.noperationap;
  }

  public UFDouble getNorderap() {
    return this.norderap;
  }

  public UFDouble getNorderpaymny() {
    return this.norderpaymny;
  }

  public UFDouble getNunverifymny() {
    return this.nunverifymny;
  }

  public String getPk_apfinanceorg() {
    return this.pk_apfinanceorg;
  }

  public String getPk_apfinanceorg_v() {
    return this.pk_apfinanceorg_v;
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    try {
      if (ApDataVO.PK_APFINANCEORG_V.equals(name)) {
        this.pk_apfinanceorg_v = (String) value;
      }
      else if (ApDataVO.PK_APFINANCEORG.equals(name)) {
        this.pk_apfinanceorg = (String) value;
      }
      else if (ApDataVO.NORDERAP.equals(name)) {
        this.norderap = (UFDouble) value;
      }
      else if (ApDataVO.NOPERATIONAP.equals(name)) {
        this.noperationap = (UFDouble) value;
      }
      else if (ApDataVO.NFINANCEAP.equals(name)) {
        this.nfinanceap = (UFDouble) value;
      }
      else if (ApDataVO.NORDERPAYMNY.equals(name)) {
        this.norderpaymny = (UFDouble) value;
      }
      else if (ApDataVO.NUNVERIFYMNY.equals(name)) {
        this.nunverifymny = (UFDouble) value;
      }
      else if (ApDataVO.NCREDITBOUND.equals(name)) {
        this.ncreditbound = (UFDouble) value;
      }
      else if (ApDataVO.CCURRENCYID.equals(name)) {
        this.ccurrencyid = (String) value;
      }
    }
    catch (ClassCastException e) {
      throw new ClassCastException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0297", null, new String[]{name,(String)value})/*setAttributeValue方法中为 {0} 赋值时类型转换错误！（值：{1}）*/);
    }
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.ccurrencyid = ccurrencyid;
  }

  public void setNcreditbound(UFDouble ncreditbound) {
    this.ncreditbound = ncreditbound;
  }

  public void setNfinanceap(UFDouble nfinanceap) {
    this.nfinanceap = nfinanceap;
  }

  public void setNoperationap(UFDouble noperationap) {
    this.noperationap = noperationap;
  }

  public void setNorderap(UFDouble norderap) {
    this.norderap = norderap;
  }

  public void setNorderpaymny(UFDouble norderpaymny) {
    this.norderpaymny = norderpaymny;
  }

  public void setNunverifymny(UFDouble nunverifymny) {
    this.nunverifymny = nunverifymny;
  }

  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.pk_apfinanceorg = pk_apfinanceorg;
  }

  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.pk_apfinanceorg_v = pk_apfinanceorg_v;
  }

  @Override
  public void validate() throws ValidationException {
    //
  }

}
