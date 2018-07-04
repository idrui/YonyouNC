/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 上午08:44:28
 */
package nc.pubitf.pu.m21.ic.m45;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单公共回写参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-5 上午08:44:28
 */
public interface IOrderWriteBackPara extends IWriteBackPubPara {

  /**
   * 上游的子子表ID
   * <p>
   * 如采购订单的到货计划ID
   **/
  public String getBBID();

  /** 是否同时关闭(到货、入库) **/
  public boolean isClose();

  /** 是否用户确认了数量超容差的提示 **/
  public boolean isNumUserConfirm();

  /** 是否退货(库) **/
  public boolean isReturn();
}
