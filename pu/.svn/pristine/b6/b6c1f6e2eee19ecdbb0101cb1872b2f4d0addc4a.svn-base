/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午03:45:25
 */
package nc.pubitf.pu.m21.arap.mf1;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>核销回写订单累计已核销本币开票金额参数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-8 下午03:45:25
 */
public interface IOrderWriteBackParaForF1 extends IWriteBackPubPara {
  /**
   * 方法功能描述：订单日期
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午08:39:03
   */
  UFDate getBillDate();

  /**
   * 方法功能描述：原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-16 下午01:46:04
   */
  String getCurrency();

  /**
   * 方法功能描述：本币金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午08:36:27
   */
  UFDouble getDiffMny();

  /**
   * 方法功能描述：原币金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午08:36:47
   */
  UFDouble getDiffOrgMny();

  /**
   * 是否核销
   * 
   * @return 如果是核销为true，如果取消核销为false
   */
  boolean isVerify();
}
