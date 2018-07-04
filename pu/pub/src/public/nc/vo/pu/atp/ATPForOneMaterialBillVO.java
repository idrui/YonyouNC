package nc.vo.pu.atp;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午01:50:26
 */
public class ATPForOneMaterialBillVO extends nc.vo.pub.AggregatedValueObject {

  private static final long serialVersionUID = 722545634369966539L;

  private ATPForOneMaterialItemVO[] m_children;

  private ATPForOneMaterialHeaderVO m_parent;

  /**
   * ATPForOneInvVO 构造子注解。
   */
  public ATPForOneMaterialBillVO() {
    super();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getChildrenVO()
   */
  @Override
  public nc.vo.pub.CircularlyAccessibleValueObject[] getChildrenVO() {
    return this.m_children;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.AggregatedValueObject#getParentVO()
   */
  @Override
  public nc.vo.pub.CircularlyAccessibleValueObject getParentVO() {
    return this.m_parent;
  }

  @Override
  public void setChildrenVO(nc.vo.pub.CircularlyAccessibleValueObject[] children) {
    this.m_children = (ATPForOneMaterialItemVO[]) children;
  }

  @Override
  public void setParentVO(nc.vo.pub.CircularlyAccessibleValueObject parent) {
    this.m_parent = (ATPForOneMaterialHeaderVO) parent;
  }
}
