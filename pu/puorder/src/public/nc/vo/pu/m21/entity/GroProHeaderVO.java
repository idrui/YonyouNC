/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 ����07:38:19
 */
package nc.vo.pu.m21.entity;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.NullFieldException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ë��Ԥ����ͷVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-23 ����07:38:19
 */
public class GroProHeaderVO extends CircularlyAccessibleValueObject {

  private static final long serialVersionUID = -7180354013604962285L;

  private String corigcurrencyid;

  // private String corigcurrencyname;

  private UFDate dbilldate;

  private String pk_order;

  private String vbillcode;

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
   */
  @Override
  public String[] getAttributeNames() {
    return new String[] {
      "corigcurrencyid", "dbilldate", "pk_order", "vbillcode"
    // "corigcurrencyname"
    };
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String attributeName) {
    if (attributeName.equals("corigcurrencyid")) {
      return this.corigcurrencyid;
    }
    // else if (attributeName.equals("corigcurrencyname")) {
    // return this.corigcurrencyname;
    // }
    else if (attributeName.equals("dbilldate")) {
      return this.dbilldate;
    }
    else if (attributeName.equals("pk_order")) {
      return this.pk_order;
    }
    else if (attributeName.equals("vbillcode")) {
      return this.vbillcode;
    }

    return null;
  }

  /**
   * @return corigcurrencyid
   */
  public String getCorigcurrencyid() {
    return this.corigcurrencyid;
  }

  /**
   * @return corigcurrencyname
   */
  // public String getCorigcurrencyname() {
  // return this.corigcurrencyname;
  // }

  /**
   * @return dbilldate
   */
  public UFDate getDbilldate() {
    return this.dbilldate;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ValueObject#getEntityName()
   */
  @Override
  public String getEntityName() {
    return null;
  }

  /**
   * @return pk_order
   */
  public String getPk_order() {
    return this.pk_order;
  }

  /**
   * @return vbillcode
   */
  public String getVbillcode() {
    return this.vbillcode;
  }

  /**
   * ���෽����д"pk_order", "vbillcode"
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String,
   *      java.lang.Object)
   */
  @Override
  public void setAttributeValue(String name, Object value) {
    try {
      if (name.equals("corigcurrencyid")) {
        this.corigcurrencyid = (String) value;
      }
      // else if (name.equals("corigcurrencyname")) {
      // this.corigcurrencyname = (String) value;
      // }
      else if (name.equals("dbilldate")) {
        this.dbilldate = (UFDate) value;
      }
      else if (name.equals("pk_order")) {
        this.pk_order = (String) value;
      }
      else if (name.equals("vbillcode")) {
        this.vbillcode = (String) value;
      }
    }
    catch (ClassCastException e) {
      throw new ClassCastException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0297", null, new String[]{name,(String)value})/*setAttributeValue������Ϊ {0} ��ֵʱ����ת�����󣡣�ֵ��{1}��*/);
    }

  }

  /**
   * @param corigcurrencyid
   *          Ҫ���õ� corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.corigcurrencyid = corigcurrencyid;
  }

  /**
   * @param corigcurrencyname
   *          Ҫ���õ� corigcurrencyname
   */
  // public void setCorigcurrencyname(String corigcurrencyname) {
  // this.corigcurrencyname = corigcurrencyname;
  // }

  /**
   * @param dbilldate
   *          Ҫ���õ� dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.dbilldate = dbilldate;
  }

  /**
   * @param pkOrder
   *          Ҫ���õ� pk_order
   */
  public void setPk_order(String pkOrder) {
    this.pk_order = pkOrder;
  }

  /**
   * @param vbillcode
   *          Ҫ���õ� vbillcode
   */
  public void setVbillcode(String vbillcode) {
    this.vbillcode = vbillcode;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ValueObject#validate()
   */
  @Override
  public void validate() throws ValidationException {
    if (this.pk_order == null) {
      throw new NullFieldException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0298")/*pk_order����Ϊ��*/);
    }
  }

}
