/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 上午09:08:11
 */
package nc.ui.pu.m21.service.onway;

import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单状态维护装运服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 上午09:08:11
 */
public class OrderLoadService extends AbstractOrderOnwayService {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.service.onway.AbstractOrderOnwayService#getIsDone()
   */
  // @Override
  // public boolean getIsDone(String sqlWhere) {
  // boolean isDone = false;
  // if (sqlWhere.indexOf("bisload = 'N'") > 0) {
  // isDone = false;
  // }
  // else if (sqlWhere.indexOf("bisload = 'Y'") > 0) {
  // isDone = true;
  // }
  // return isDone;
  // }

  @Override
  public String getOnwayStatusStr() {
    return OnwayStatusQryEnum.bisload.code();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.m21.service.onway.AbstractOrderOnwayService#getStatus()
   */
  @Override
  public int getStatus() {
    return OnwayStatus.STATUS_SHIP.toInt();
  }
}
