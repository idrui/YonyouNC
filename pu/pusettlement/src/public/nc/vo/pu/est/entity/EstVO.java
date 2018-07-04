/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 上午09:03:53
 */
package nc.vo.pu.est.entity;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的顶层的聚合VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-29 上午09:03:53
 */
public class EstVO extends AggregatedValueObject {

  /**
   * 
   */
  private static final long serialVersionUID = -1098049360557320070L;

  private FeeEstVO[] children;

  private GoodsEstVO parent;

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getChildrenVO()
   */
  @Override
  public FeeEstVO[] getChildrenVO() {
    return this.children;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getParentVO()
   */
  @Override
  public GoodsEstVO getParentVO() {
    return this.parent;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#setChildrenVO(nc.vo.pub.CircularlyAccessibleValueObject[])
   */
  @Override
  public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
    this.children = (FeeEstVO[]) children;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#setParentVO(nc.vo.pub.CircularlyAccessibleValueObject)
   */
  @Override
  public void setParentVO(CircularlyAccessibleValueObject parent) {
    this.parent = (GoodsEstVO) parent;
  }

}
