/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午03:40:54
 */
package nc.pubitf.pu.m21.arap.mf3;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>付款核销回写订单的参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-28 下午03:40:54
 */
public interface IOrderWriteBackParaForF3Verify extends IWriteBackPubPara {
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午08:39:33
   */
  public UFDate getBillDate();

  /**
   * 方法功能描述：原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午01:46:31
   */
  public String getCurrency();

  /**
   * 方法功能描述：累计已核销本币付款金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-28 下午04:00:17
   */
  public UFDouble getDiffVerifyMny();

  /**
   * 方法功能描述：原币金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午08:38:03
   */
  public UFDouble getDiffVerifyOrgMny();
}
