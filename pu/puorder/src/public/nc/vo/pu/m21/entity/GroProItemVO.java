/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 ����07:36:33
 */
package nc.vo.pu.m21.entity;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.NullFieldException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ë��Ԥ������VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-23 ����07:36:33
 */
public class GroProItemVO extends CircularlyAccessibleValueObject {

  private static final long serialVersionUID = 4053675475746855041L;

  public String cunitid; // ����λ

  // public String cunitname; // ��λ����

  // public String matcode; // ���ϱ���
  //
  // public String matname; // ����
  //
  // public String matspec; // ���
  //
  // public String mattype; // �ͺ�

  // private String corigcurrencyid; //����

  private UFDouble ngrossprofit; // ë����

  private UFDouble ngrossprofitrate;// ë����

  private UFDouble nitemdiscountrate;

  private UFDouble nnum;

  private UFDouble norigmny;

  private UFDouble norignetprice;

  private UFDouble norigprice;

  private UFDouble nsalemny;// ���۽��

  private UFDouble nsaleprice; // ���۵���

  private String pk_material;// ����ID

  private String pk_order;

  private String pk_order_b;

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
   */
  @Override
  public String[] getAttributeNames() {
    return new String[] {
      "cunitid", "ngrossprofit", "ngrossprofitrate", "nitemdiscountrate",
      "nnum", "norigmny", "norignetprice", "norigprice", "nsalemny",
      "nsaleprice", "pk_material", "pk_order", "pk_order_b"
    // "cunitname", "matcode", "matspec", "mattype",
    // "corigcurrencyid",
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
    // else if (attributeName.equals("cunitname")) {
    // return this.cunitname;
    // }
    // else if (attributeName.equals("matcode")) {
    // return this.matcode;
    // }
    // else if (attributeName.equals("matspec")) {
    // return this.matspec;
    // }
    // else if (attributeName.equals("mattype")) {
    // return this.mattype;
    // }
    // else if (attributeName.equals("corigcurrencyid")) {
    // return this.corigcurrencyid;
    // }
    else if (attributeName.equals("nitemdiscountrate")) {
      return this.nitemdiscountrate;
    }
    else if (attributeName.equals("ngrossprofit")) {
      return this.ngrossprofit;
    }
    else if (attributeName.equals("ngrossprofitrate")) {
      return this.ngrossprofitrate;
    }
    else if (attributeName.equals("nnum")) {
      return this.nnum;
    }
    else if (attributeName.equals("norigmny")) {
      return this.norigmny;
    }
    else if (attributeName.equals("norignetprice")) {
      return this.norignetprice;
    }
    else if (attributeName.equals("norigprice")) {
      return this.norigprice;
    }
    else if (attributeName.equals("nsalemny")) {
      return this.nsalemny;
    }
    else if (attributeName.equals("nsaleprice")) {
      return this.nsaleprice;
    }
    else if (attributeName.equals("pk_material")) {
      return this.pk_material;
    }
    else if (attributeName.equals("pk_order")) {
      return this.pk_order;
    }
    else if (attributeName.equals("pk_order_b")) {
      return this.pk_order_b;
    }
    return null;
  }

  /**
   * @return corigcurrencyid
   */
  // public String getCorigcurrencyid() {
  // return this.corigcurrencyid;
  // }

  /**
   * @return cunitid
   */
  public String getCunitid() {
    return this.cunitid;
  }

  /**
   * @return cunitname
   */
  // public String getCunitname() {
  // return this.cunitname;
  // }

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

  /**
   * @return ngrossprofit
   */
  public UFDouble getNgrossprofit() {
    return this.ngrossprofit;
  }

  /**
   * @return ngrossprofitrate
   */
  public UFDouble getNgrossprofitrate() {
    return this.ngrossprofitrate;
  }

  /**
   * @return nitemdiscountrate
   */
  public UFDouble getNitemdiscountrate() {
    return this.nitemdiscountrate;
  }

  /**
   * @return nnum
   */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /**
   * @return norigmny
   */
  public UFDouble getNorigmny() {
    return this.norigmny;
  }

  /**
   * @return norignetprice
   */
  public UFDouble getNorignetprice() {
    return this.norignetprice;
  }

  /**
   * @return norigprice
   */
  public UFDouble getNorigprice() {
    return this.norigprice;
  }

  /**
   * @return nsalemny
   */
  public UFDouble getNsalemny() {
    return this.nsalemny;
  }

  /**
   * @return nsaleprice
   */
  public UFDouble getNsaleprice() {
    return this.nsaleprice;
  }

  /**
   * @return pk_material
   */
  public String getPk_material() {
    return this.pk_material;
  }

  /**
   * @return pk_order
   */
  public String getPk_order() {
    return this.pk_order;
  }

  /**
   * @return pk_order_b
   */
  public String getPk_order_b() {
    return this.pk_order_b;
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
      // else if (name.equals("cunitname")) {
      // this.cunitname = (String) value;
      // }
      // else if (name.equals("matcode")) {
      // this.matcode = (String) value;
      // }
      // else if (name.equals("matname")) {
      // this.matname = (String) value;
      // }
      // else if (name.equals("matspec")) {
      // this.matspec = (String) value;
      // }
      // else if (name.equals("mattype")) {
      // this.mattype = (String) value;
      // }
      // else if (name.equals("corigcurrencyid")) {
      // this.corigcurrencyid = (String) value;
      // }
      else if (name.equals("ngrossprofit")) {
        this.ngrossprofit = (UFDouble) value;
      }
      else if (name.equals("ngrossprofitrate")) {
        this.ngrossprofitrate = (UFDouble) value;
      }
      else if (name.equals("nitemdiscountrate")) {
        this.nitemdiscountrate = (UFDouble) value;
      }
      else if (name.equals("nnum")) {
        this.nnum = (UFDouble) value;
      }
      else if (name.equals("norigmny")) {
        this.norigmny = (UFDouble) value;
      }
      else if (name.equals("norignetprice")) {
        this.norignetprice = (UFDouble) value;
      }
      else if (name.equals("norigprice")) {
        this.norigprice = (UFDouble) value;
      }
      else if (name.equals("nsalemny")) {
        this.nsalemny = (UFDouble) value;
      }
      else if (name.equals("nsaleprice")) {
        this.nsaleprice = (UFDouble) value;
      }
      else if (name.equals("pk_material")) {
        this.pk_material = (String) value;
      }
      else if (name.equals("pk_order")) {
        this.pk_order = (String) value;
      }
      else if (name.equals("pk_order_b")) {
        this.pk_order_b = (String) value;
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
   * @param corigcurrencyid
   *          Ҫ���õ� corigcurrencyid
   */
  // public void setCorigcurrencyid(String corigcurrencyid) {
  // this.corigcurrencyid = corigcurrencyid;
  // }

  /**
   * @param cunitid
   *          Ҫ���õ� cunitid
   */
  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  /**
   * @param cunitname
   *          Ҫ���õ� cunitname
   */
  // public void setCunitname(String cunitname) {
  // this.cunitname = cunitname;
  // }

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
   * @param ngrossprofit
   *          Ҫ���õ� ngrossprofit
   */
  public void setNgrossprofit(UFDouble ngrossprofit) {
    this.ngrossprofit = ngrossprofit;
  }

  /**
   * @param ngrossprofitrate
   *          Ҫ���õ� ngrossprofitrate
   */
  public void setNgrossprofitrate(UFDouble ngrossprofitrate) {
    this.ngrossprofitrate = ngrossprofitrate;
  }

  /**
   * @param nitemdiscountrate
   *          Ҫ���õ� nitemdiscountrate
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.nitemdiscountrate = nitemdiscountrate;
  }

  /**
   * @param nnum
   *          Ҫ���õ� nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * @param norigmny
   *          Ҫ���õ� norigmny
   */
  public void setNorigmny(UFDouble norigmny) {
    this.norigmny = norigmny;
  }

  /**
   * @param norignetprice
   *          Ҫ���õ� norignetprice
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.norignetprice = norignetprice;
  }

  /**
   * @param norigprice
   *          Ҫ���õ� norigprice
   */
  public void setNorigprice(UFDouble norigprice) {
    this.norigprice = norigprice;
  }

  /**
   * @param nsalemny
   *          Ҫ���õ� nsalemny
   */
  public void setNsalemny(UFDouble nsalemny) {
    this.nsalemny = nsalemny;
  }

  /**
   * @param nsaleprice
   *          Ҫ���õ� nsaleprice
   */
  public void setNsaleprice(UFDouble nsaleprice) {
    this.nsaleprice = nsaleprice;
  }

  /**
   * @param pkMaterial
   *          Ҫ���õ� pk_material
   */
  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  /**
   * @param pkOrder
   *          Ҫ���õ� pk_order
   */
  public void setPk_order(String pkOrder) {
    this.pk_order = pkOrder;
  }

  /**
   * @param pkOrderB
   *          Ҫ���õ� pk_order_b
   */
  public void setPk_order_b(String pkOrderB) {
    this.pk_order_b = pkOrderB;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ValueObject#validate()
   */
  @Override
  public void validate() throws ValidationException {
    StringBuilder sb = new StringBuilder();
    if (null == this.pk_order_b) {
      sb.append("pk_order_b , ");
    }
    if (null == this.pk_order) {
      sb.append("pk_order");
    }
    if (sb.length() > 0) {
      throw new NullFieldException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0183")/* @res "�����ֶβ���Ϊ�գ�" */
          + sb.toString());
    }
  }
}
