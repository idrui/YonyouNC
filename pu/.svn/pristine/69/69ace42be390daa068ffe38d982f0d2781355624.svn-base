package nc.vo.pu.atp;

import nc.vo.ml.NCLangRes4VoTransl;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询表头VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午01:50:19
 */
public class ATPForOneMaterialHeaderVO extends
    nc.vo.pub.CircularlyAccessibleValueObject {

  private static final long serialVersionUID = 9129798928186462286L;

  /** 自由项 **/
  private String freeitem;

  /** 物料编码 **/
  private String materialcode;

  /** 物料名称 **/
  private String materialname;

  /**
   * ATPForOneInvHVO 构造子注解。
   */
  public ATPForOneMaterialHeaderVO() {
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
      "materialcode", "materialname", "freeitem"
    };
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String attributeName) {
    if (attributeName.equals("materialcode")) {
      return this.materialcode;
    }
    if (attributeName.equals("materialname")) {
      return this.materialname;
    }
    if (attributeName.equals("freeitem")) {
      return this.freeitem;
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
      if (name.equals("materialcode")) {
        this.materialcode = (String) value;
      }
      if (name.equals("materialname")) {
        this.materialname = (String) value;
      }
      if (name.equals("freeitem")) {
        this.freeitem = (String) value;
      }
    }
    catch (ClassCastException e) {
      throw new ClassCastException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0102", null, new String[]{name,(String)value})/*setAttributeValue方法中为 {0} 赋值时类型转换错误！（值：{1}）*/);
    }
  }

  /**
   * @param freeitem
   *          要设置的 freeitem
   */
  public void setFreeitem(String freeitem) {
    this.freeitem = freeitem;
  }

  /**
   * @param materialcode
   *          要设置的 materialcode
   */
  public void setMaterialcode(String materialcode) {
    this.materialcode = materialcode;
  }

  /**
   * @param materialname
   *          要设置的 materialname
   */
  public void setMaterialname(String materialname) {
    this.materialname = materialname;
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
}
