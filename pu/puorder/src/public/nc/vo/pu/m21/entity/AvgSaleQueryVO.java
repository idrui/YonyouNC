/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 ����09:18:36
 */
package nc.vo.pu.m21.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.NullFieldException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ѯVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-23 ����09:18:36
 */
public class AvgSaleQueryVO extends CircularlyAccessibleValueObject {

  private static final long serialVersionUID = -2532086237321134406L;

  public String cunitid; // ����λ

  public UFDate dquerydate; // ��ѯʱ��

  // public String cunitname; // ��λ����

  public Integer iqueryday; // ��ѯ����

  public UFDouble ninvoicenum; // ���۷�Ʊ��

  // public String matcode; // ���ϱ���

  // public String matname; // ����

  // public String matspec; // ���

  // public String mattype; // ����ͺ�

  public UFDouble nordernum; // ���۶�����

  public UFDouble noutnum; // ���۳�����

  public String pk_material; // ����ID

  public String pk_org; // ��֯

  // ����OID
  public String pk_srcmaterial;

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
   */
  @Override
  public String[] getAttributeNames() {
    return new String[] {
      "cunitid", "dquerydate", "iqueryday", "ninvoicenum", "nordernum",
      "noutnum", "pk_material", "pk_org", "pk_srcmaterial"
    // "cunitname","matcode", "matname",
    // "matspec", "mattype",
    };
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String attributeName) {
    if (attributeName.equals("cunitid")) {
      return this.cunitid;
    }
    // if (attributeName.equals("cunitname")) {
    // return this.cunitname;
    // }
    else if (attributeName.equals("dquerydate")) {
      return this.dquerydate;
    }
    else if (attributeName.equals("iqueryday")) {
      return this.iqueryday;
    }
    // else if (attributeName.equals("matcode")) {
    // return this.matcode;
    // }
    // else if (attributeName.equals("matname")) {
    // return this.matname;
    // }
    // if (attributeName.equals("matspec")) {
    // return this.matspec;
    // }
    // if (attributeName.equals("mattype")) {
    // return this.mattype;
    // }
    else if (attributeName.equals("ninvoicenum")) {
      return this.ninvoicenum;
    }
    else if (attributeName.equals("nordernum")) {
      return this.nordernum;
    }
    else if (attributeName.equals("noutnum")) {
      return this.noutnum;
    }
    else if (attributeName.equals("pk_material")) {
      return this.pk_material;
    }
    else if (attributeName.equals("pk_org")) {
      return this.pk_org;
    }
    else if (attributeName.equals("pk_srcmaterial")) {
      return this.pk_srcmaterial;
    }
    return null;
  }

  /**
   * @return cunitid
   */
  public String getCunitid() {
    return this.cunitid;
  }

  /**
   * @return dquerydate
   */
  public UFDate getDquerydate() {
    return this.dquerydate;
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
   * @return iqueryday
   */
  public Integer getIqueryday() {
    return this.iqueryday;
  }

  /**
   * @return ninvoicenum
   */
  public UFDouble getNinvoicenum() {
    return this.ninvoicenum;
  }

  /**
   * @return cunitname
   */
  // public String getCunitname() {
  // return this.cunitname;
  // }

  /**
   * @return nordernum
   */
  public UFDouble getNordernum() {
    return this.nordernum;
  }

  /**
   * @return noutnum
   */
  public UFDouble getNoutnum() {
    return this.noutnum;
  }

  /**
   * @return pk_material
   */
  public String getPk_material() {
    return this.pk_material;
  }

  /**
   * @return matcode
   */
  // public String getMatcode() {
  // return this.matcode;
  // }

  /**
   * @return matname
   */
  // public String getMatname() {
  // return this.matname;
  // }

  /**
   * @return matspec
   */
  // public String getMatspec() {
  // return this.matspec;
  // }

  /**
   * @return mattype
   */
  // public String getMattype() {
  // return this.mattype;
  // }

  public String getPk_org() {
    return this.pk_org;
  }

  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String,
   *      java.lang.Object)
   */
  @Override
  public void setAttributeValue(String name, Object value) {
    try {
      if (name.equals("cunitid")) {
        this.cunitid = (String) value;
      }
      // if (name.equals("cunitname")) {
      // this.cunitname = (String) value;
      // return;
      // }
      else if (name.equals("dquerydate")) {
        this.dquerydate = (UFDate) value;
      }
      else if (name.equals("iqueryday")) {
        this.iqueryday = (Integer) value;
      }
      // if (name.equals("matcode")) {
      // this.matcode = (String) value;
      // }
      // if (name.equals("matname")) {
      // this.matname = (String) value;
      // }
      // if (name.equals("matspec")) {
      // this.matspec = (String) value;
      // }
      // if (name.equals("mattype")) {
      // this.mattype = (String) value;
      // }
      else if (name.equals("ninvoicenum")) {
        this.ninvoicenum = (UFDouble) value;
      }
      else if (name.equals("nordernum")) {
        this.nordernum = (UFDouble) value;
      }
      else if (name.equals("noutnum")) {
        this.noutnum = (UFDouble) value;
      }
      else if (name.equals("pk_material")) {
        this.pk_material = (String) value;
      }
      else if (name.equals("pk_org")) {
        this.pk_org = (String) value;
      }
      else if (name.equals("pk_srcmaterial")) {
        this.pk_srcmaterial = (String) value;
      }
    }
    catch (ClassCastException e) {
      throw new ClassCastException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0297", null, new String[] {
            name, (String) value
          })/* setAttributeValue������Ϊ {0} ��ֵʱ����ת�����󣡣�ֵ��{1}�� */);
    }

  }

  /**
   * @param cunitid
   *          Ҫ���õ� cunitid
   */
  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  /**
   * @param dquerydate
   *          Ҫ���õ� dquerydate
   */
  public void setDquerydate(UFDate dquerydate) {
    this.dquerydate = dquerydate;
  }

  /**
   * @param iqueryday
   *          Ҫ���õ� iqueryday
   */
  public void setIqueryday(Integer iqueryday) {
    this.iqueryday = iqueryday;
  }

  /**
   * @param cunitname
   *          Ҫ���õ� cunitname
   */
  // public void setCunitname(String cunitname) {
  // this.cunitname = cunitname;
  // }

  /**
   * @param ninvoicenum
   *          Ҫ���õ� ninvoicenum
   */
  public void setNinvoicenum(UFDouble ninvoicenum) {
    this.ninvoicenum = ninvoicenum;
  }

  /**
   * @param nordernum
   *          Ҫ���õ� nordernum
   */
  public void setNordernum(UFDouble nordernum) {
    this.nordernum = nordernum;
  }

  /**
   * @param matcode
   *          Ҫ���õ� matcode
   */
  // public void setMatcode(String matcode) {
  // this.matcode = matcode;
  // }

  /**
   * @param matname
   *          Ҫ���õ� matname
   */
  // public void setMatname(String matname) {
  // this.matname = matname;
  // }

  /**
   * @param matspec
   *          Ҫ���õ� matspec
   */
  // public void setMatspec(String matspec) {
  // this.matspec = matspec;
  // }

  /**
   * @param mattype
   *          Ҫ���õ� mattype
   */
  // public void setMattype(String mattype) {
  // this.mattype = mattype;
  // }

  /**
   * @param noutnum
   *          Ҫ���õ� noutnum
   */
  public void setNoutnum(UFDouble noutnum) {
    this.noutnum = noutnum;
  }

  /**
   * @param pkMaterial
   *          Ҫ���õ� pk_material
   */
  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ValueObject#validate()
   */
  @Override
  public void validate() throws ValidationException {
    List<String> errFields = new ArrayList<String>();
    if (ObjectUtil.isEmptyWithTrim(this.iqueryday)) {
      errFields.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004030_0", "04004030-0181")/* @res "��ѯ����" */);
    }
    if (ObjectUtil.isEmptyWithTrim(this.dquerydate)) {
      errFields.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004030_0", "04004030-0182")/* @res "��ѯʱ��" */);
    }

    StringBuffer message = new StringBuffer();
    message.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
        "04004030-0183")/* �����ֶβ���Ϊ�գ� */);
    if (errFields.size() > 0) {
      String[] temp = errFields.toArray(new String[0]);

      message.append(temp[0].toString());
      for (int i = 1; i < temp.length; i++) {
        message.append(", ");
        message.append(temp[i].toString());
      }

      throw new NullFieldException(message.toString());
    }
  }

}
