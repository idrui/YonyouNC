package nc.vo.pu.atp;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询表体VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午01:49:35
 */
public class ATPForOneMaterialItemVO extends
    nc.vo.pub.CircularlyAccessibleValueObject {

  private static final long serialVersionUID = 1650214922055796639L;

  /** 现存量 **/
  private UFDouble handnum;

  /** 行号 **/
  private String crowno;

  /** 库存组织 **/
  private String stockorgname;

  /** 物料编码 **/
  private String materialcode;

  /** 物料名称 **/
  private String materialname;
  
  /** 主单位 **/
  private String cunitid;

  /** 可用量（不按日期展开） **/
  private UFDouble usablenum;

  /** 计划发货日可用量 **/
  private UFDouble usablenumbydate;

  /**
   * ATPForOneInvBVO 构造子注解。
   */
  public ATPForOneMaterialItemVO() {
    super();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
   */
  @Override
  public java.lang.String[] getAttributeNames() {
    return new String[] {
      "crowno", "stockorgname", "materialcode", "materialname", "cunitid", "handnum", "usablenum", "usablenumbydate"
    };
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String attributeName) {

    if (attributeName.equals("crowno")) {
      return this.crowno;
    }
    if (attributeName.equals("stockorgname")) {
      return this.stockorgname;
    }
    if (attributeName.equals("materialcode")) {
      return this.materialcode;
    }
    if (attributeName.equals("materialname")) {
      return this.materialname;
    }
    if (attributeName.equals("cunitid")) {
      return this.cunitid;
    }
    if (attributeName.equals("handnum")) {
      return this.handnum;
    }
    if (attributeName.equals("usablenum")) {
      return this.usablenum;
    }
    if (attributeName.equals("usablenumbydate")) {
      return this.usablenumbydate;
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
    return "ATPForOneInv";
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String,
   *      java.lang.Object)
   */
  @Override
  public void setAttributeValue(String name, Object value) {
    try {

      if (name.equals("crowno")) {
        this.crowno = (String) value;
      }
      if (name.equals("stockorgname")) {
        this.stockorgname = (String) value;
      }
      if (name.equals("materialcode")) {
        this.materialcode = (String) value;
      }
      if (name.equals("materialname")) {
        this.materialname = (String) value;
      }
      if (name.equals("cunitid")) {
        this.cunitid = (String) value;
      }
      if (name.equals("handnum")) {
        this.handnum = (UFDouble) value;
      }
      if (name.equals("usablenum")) {
        this.usablenum = (UFDouble) value;
      }
      if (name.equals("usablenumbydate")) {
        this.usablenumbydate = (UFDouble) value;
      }
    }
    catch (ClassCastException e) {
      throw new ClassCastException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0102", null, new String[]{name,(String)value})/*setAttributeValue方法中为 {0} 赋值时类型转换错误！（值：{1}）*/);
    }
  }

  /**
   * @param handnum
   *          要设置的 handnum
   */
  public void setHandnum(UFDouble handnum) {
    this.handnum = handnum;
  }

  /**
   * @param stockorgname
   *          要设置的 stockorgname
   */
  public void setStockorgname(String stockorgname) {
    this.stockorgname = stockorgname;
  }

  /**
   * @param usablenum
   *          要设置的 usablenum
   */
  public void setUsablenum(UFDouble usablenum) {
    this.usablenum = usablenum;
  }

  /**
   * @param usablenumbydate
   *          要设置的 usablenumbydate
   */
  public void setUsablenumbydate(UFDouble usablenumbydate) {
    this.usablenumbydate = usablenumbydate;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.ValueObject#validate()
   */
  @Override
  public void validate() throws nc.vo.pub.ValidationException {
    // 无检验
  }

  public void setMaterialcode(String materialcode) {
    this.materialcode = materialcode;
  }

  public void setMaterialname(String materialname) {
    this.materialname = materialname;
  }

  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }
}
