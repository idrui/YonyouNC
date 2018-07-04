/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 上午08:51:05
 */
package nc.pubitf.pu.m21.pu.m25;

import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pub.lang.UFDouble;

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
 * @author zhaoyha
 * @time 2010-2-5 上午08:51:05
 */
public interface IOrderWriteBackParaFor25 extends IOrderWriteBackPara {
  /**
   * 回写累计金额差
   * <p>
   * 发票回写订单时的累计开票金额
   **/
  public UFDouble getDiffMny();

  /** 发票的单价 **/
  public UFDouble getPrice();

  /** 是否删除发票 **/
  public boolean isDiscard();

  /** 是否费用发票 **/
  public boolean isFee();

  /** 是否用户确认了单价超容差的提示 **/
  public boolean isPriceUserConfirm();
}
