/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午09:16:57
 */
package nc.vo.pu.m20.entity.writeback;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> 为采购订单和委外订单提供回写累计订单数量
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-4 上午09:16:57
 */
public class OrderWriteBackVO extends BaseWriteBackVO {

  private static final long serialVersionUID = 108880090755974024L;

  /** 用户是否确认过 **/
  private boolean bIsUserConfirm = false;

  /** 差额数量 **/
  private UFDouble diffNum;

  /** 本次回写数量 **/
  private UFDouble newNum;

  /** 修改前数量 **/
  private UFDouble oldNum;

  /** 采购组织 **/
  private String pk_org;

  /** 是否删除 **/
  private String strDelete = "N";

  /**
   * OrderWriteBackVO 的构造子
   */
  public OrderWriteBackVO() {
    super();
  }

  /**
   * @return diffNum
   */
  public UFDouble getDiffNum() {
    return this.diffNum;
  }

  /**
   * @return newNum
   */
  public UFDouble getNewNum() {
    return this.newNum;
  }

  /**
   * @return oldNum
   */
  public UFDouble getOldNum() {
    return this.oldNum;
  }

  /**
   * @return 采购组织
   */
  public String getPk_org() {
    return this.pk_org;
  }

  /**
   * 获取是否删除
   * 
   * @return 是否删除
   */
  public String getStrDelete() {
    return this.strDelete;
  }

  /**
   * 获取用户是否确认过
   * 
   * @return 用户确认过，不再提示
   */
  public boolean isUserConfirm() {
    return this.bIsUserConfirm;
  }

  /**
   * @param diffNum
   *          要设置的 diffNum
   */
  public void setDiffNum(UFDouble diffNum) {
    this.diffNum = diffNum;
  }

  /**
   * @param newNum
   *          要设置的 newNum
   */
  public void setNewNum(UFDouble newNum) {
    this.newNum = newNum;
  }

  /**
   * @param oldNum
   *          要设置的 oldNum
   */
  public void setOldNum(UFDouble oldNum) {
    this.oldNum = oldNum;
  }

  /**
   * @param pkOrg
   *          要设置的采购组织
   */
  public void setPk_org(String pkOrg) {
    this.pk_org = pkOrg;
  }

  /**
   * @param strDelete
   *          是否删除
   */
  public void setStrDelete(String strDelete) {
    this.strDelete = strDelete;
  }

  /**
   * 设置用户是否确认过
   * 
   * @param newVal 用户是否确认过
   */
  public void setUserConfirm(boolean newVal) {
    this.bIsUserConfirm = newVal;
  }

}
