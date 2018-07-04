/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-25 上午11:41:55
 */
package nc.vo.pu.m20.context;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单操作时前台到后台的环境信息，一般随平台动作的userObj向外传
 * <ul>
 * <li>后台检查需要用户确认的标志
 * <li>动作脚本是否由前台手工触发标志
 * </ul>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2012-5-22 上午11:41:55
 */
public class PraybillContext implements Serializable {
  private static final long serialVersionUID = 8369670137222113733L;

  /**
   * 请购单数量容差控制
   */
  private UFBoolean PrayNumToleranceConfirm = UFBoolean.FALSE;

  public UFBoolean getPrayNumToleranceConfirm() {
    return this.PrayNumToleranceConfirm;
  }

  public void setPrayNumToleranceConfirm(UFBoolean prayNumToleranceConfirm) {
    this.PrayNumToleranceConfirm = prayNumToleranceConfirm;
  }

}
