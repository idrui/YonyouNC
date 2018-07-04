/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 下午03:55:51
 */
package nc.vo.pu.atp;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询条件VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午03:55:51
 */
public class ATPParamVO implements Serializable {

  private static final long serialVersionUID = 5541131867204655872L;

  /** 行号 **/
  private String crowno;
  
  /** 计划到货日期 **/
  private UFDate end_date;

  /** 物料编码 **/
  private String materialCode;

  /** 物料名称 **/
  private String materialName;
  
  /** 单位 **/
  private String cunitid;

  /** 物料PK **/
  private String pk_material;
  
  /** 库存组织 **/
  private String[] pk_stockorgs;

  /** 自由辅助属性1 **/
  private String vfree1;

  /** 自由辅助属性10 **/
  private String vfree10;

  /** 自由辅助属性2 **/
  private String vfree2;

  /** 自由辅助属性3 **/
  private String vfree3;

  /** 自由辅助属性4 **/
  private String vfree4;

  /** 自由辅助属性5 **/
  private String vfree5;

  /** 自由辅助属性6 **/
  private String vfree6;

  /** 自由辅助属性7 **/
  private String vfree7;

  /** 自由辅助属性8 **/
  private String vfree8;

  /** 自由辅助属性9 **/
  private String vfree9;

  /**
   * @return end_date 计划到货日期
   */
  public UFDate getEnd_date() {
    return this.end_date;
  }

  /**
   * @return materialCode
   */
  public String getMaterialCode() {
    return this.materialCode;
  }

  /**
   * @return materialName
   */
  public String getMaterialName() {
    return this.materialName;
  }

  /**
   * @return pk_material 物料PK
   */
  public String getPk_material() {
    return this.pk_material;
  }

  /**
   * @return pk_stockorgs 库存组织
   */
  public String[] getPk_stockorgs() {
    return this.pk_stockorgs;
  }

  /**
   * @return 自由辅助属性
   */
  public String getVfree() {
    StringBuffer free = new StringBuffer();
    if (null != this.vfree1) {
      free.append(this.vfree1 + ",");
    }
    if (null != this.vfree2) {
      free.append(this.vfree2 + ",");
    }
    if (null != this.vfree3) {
      free.append(this.vfree3 + ",");
    }
    if (null != this.vfree4) {
      free.append(this.vfree4 + ",");
    }
    if (null != this.vfree5) {
      free.append(this.vfree5 + ",");
    }
    if (null != this.vfree6) {
      free.append(this.vfree6 + ",");
    }
    if (null != this.vfree7) {
      free.append(this.vfree7 + ",");
    }
    if (null != this.vfree8) {
      free.append(this.vfree8 + ",");
    }
    if (null != this.vfree9) {
      free.append(this.vfree9 + ",");
    }
    if (null != this.vfree10) {
      free.append(this.vfree10 + ",");
    }

    if (free.length() > 0) {
      return free.substring(0, free.length() - 1);
    }

    return null;
  }

  /**
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return this.vfree1;
  }

  /**
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return this.vfree10;
  }

  /**
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return this.vfree2;
  }

  /**
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return this.vfree3;
  }

  /**
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return this.vfree4;
  }

  /**
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return this.vfree5;
  }

  /**
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return this.vfree6;
  }

  /**
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return this.vfree7;
  }

  /**
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return this.vfree8;
  }

  /**
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return this.vfree9;
  }

  /**
   * @param endDate
   *          要设置的 end_date
   */
  public void setEnd_date(UFDate endDate) {
    this.end_date = endDate;
  }

  /**
   * @param materialCode
   *          要设置的 materialCode
   */
  public void setMaterialCode(String materialCode) {
    this.materialCode = materialCode;
  }

  /**
   * @param materialName
   *          要设置的 materialName
   */
  public void setMaterialName(Object materialName) {
    if (null != materialName) {
      this.materialName = materialName.toString();
    }
  }

  /**
   * @param pkMaterial
   *          要设置的 pk_material
   */
  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  /**
   * @param pkStockorgs
   *          要设置的 pk_stockorgs
   */
  public void setPk_stockorgs(String[] pkStockorgs) {
    this.pk_stockorgs = pkStockorgs;
  }

  /**
   * @param vfree1
   *          要设置的自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.vfree1 = vfree1;
  }

  /**
   * @param vfree10
   *          要设置的自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.vfree10 = vfree10;
  }

  /**
   * @param vfree2
   *          要设置的自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.vfree2 = vfree2;
  }

  /**
   * @param vfree3
   *          要设置的自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.vfree3 = vfree3;
  }

  /**
   * @param vfree4
   *          要设置的自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.vfree4 = vfree4;
  }

  /**
   * @param vfree5
   *          要设置的自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.vfree5 = vfree5;
  }

  /**
   * @param vfree6
   *          要设置的自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.vfree6 = vfree6;
  }

  /**
   * @param vfree7
   *          要设置的自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.vfree7 = vfree7;
  }

  /**
   * @param vfree8
   *          要设置的自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.vfree8 = vfree8;
  }

  /**
   * @param vfree9
   *          要设置的自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.vfree9 = vfree9;
  }

  /** 
   * @return 行号
   */
  public String getCrowno() {
    return this.crowno;
  }

  /**
   * @param row
   *          行号
   */
  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  public String getCunitid() {
    return this.cunitid;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }
}
