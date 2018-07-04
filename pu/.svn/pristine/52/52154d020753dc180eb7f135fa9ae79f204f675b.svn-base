/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-24 上午09:02:10
 */
package nc.vo.pu.m21.entity;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-24 上午09:02:10
 */
public class GroProVO extends AggregatedValueObject {

  private static final long serialVersionUID = -62377728335044970L;

  private GroProHeaderVO headerVO;

  private GroProItemVO[] itemVOs;

  public GroProItemVO[] getBVO() {
    return this.itemVOs;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getChildrenVO()
   */
  @Override
  public CircularlyAccessibleValueObject[] getChildrenVO() {
    return this.itemVOs;
  }

  public GroProHeaderVO getHVO() {
    return this.headerVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getParentVO()
   */
  @Override
  public CircularlyAccessibleValueObject getParentVO() {
    return this.headerVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#setChildrenVO(nc.vo.pub.CircularlyAccessibleValueObject[])
   */
  @Override
  public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
    this.itemVOs = (GroProItemVO[]) children;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#setParentVO(nc.vo.pub.CircularlyAccessibleValueObject)
   */
  @Override
  public void setParentVO(CircularlyAccessibleValueObject parent) {
    this.headerVO = (GroProHeaderVO) parent;
  }

}
