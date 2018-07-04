/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 下午03:44:41
 */
package nc.vo.pu.pub.writeback;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购的公共回写VO接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-4 下午03:44:41
 */
public interface IWriteBackPubPara {

  /** 上游的体ID **/
  public String getBID();

  /**
   * 回写累计数量差
   * <p>
   * 如，到货回写订单时的累计到货数量
   **/
  public UFDouble getDiffNum();

  /** 上游的头ID **/
  public String getHID();

}
