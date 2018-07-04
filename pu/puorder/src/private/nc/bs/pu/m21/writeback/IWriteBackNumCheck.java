/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-16 下午09:31:12
 */
package nc.bs.pu.m21.writeback;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>累计入库(到货)数量检查参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-16 下午09:31:12
 */
public interface IWriteBackNumCheck {
  public UFDouble getAccNum();

  public String getAccNumName();

  public UFDouble getAccRetNum();

  public String getAccRetNumName();

  public UFDouble getAllAccRetNum();

  public String getBillCode();

  public String getCodeName();

  public UFDouble getOnWayNum();

  public UFDouble getOrderNum();

  public String getPk_order();

  public String getPk_order_b();

  public String getPk_order_bb1();

  public String getRowNo();

  public boolean isRefWhenRet();

  public boolean isReturn();

  public boolean isUserComfirm();

}
