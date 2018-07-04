/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 下午01:49:37
 */
package nc.vo.pu.pub.entity;

import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>PO27参数的值VO
 * <li>需求变更，将计划价是否按计划价暂估去掉
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 下午01:49:37
 */
public class PO27VO {
  private UFBoolean bplanprior = UFBoolean.FALSE;

  private PriceSource[] ps;

  /**
   * PO27VO 的构造子
   * 
   * @param bplanprior
   * @param ps
   */
  public PO27VO(UFBoolean bplanprior, PriceSource[] ps) {
    this.bplanprior = bplanprior;
    this.ps = ps;
  }

  /**
   * @return bplanprior
   */
  public UFBoolean getBplanprior() {
    return this.bplanprior;
  }

  /**
   * @return ps
   */
  public PriceSource[] getPs() {
    return this.ps;
  }

  /**
   * @param bplanprior 要设置的 bplanprior
   */
  public void setBplanprior(UFBoolean bplanprior) {
    this.bplanprior = bplanprior;
  }

  /**
   * @param ps 要设置的 ps
   */
  public void setPs(PriceSource[] ps) {
    this.ps = ps;
  }

}
