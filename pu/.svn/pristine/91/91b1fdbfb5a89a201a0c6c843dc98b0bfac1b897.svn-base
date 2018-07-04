/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午06:18:38
 */
package nc.ui.pu.m20.rule;

import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购日期和需求日期规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-11 下午06:18:38
 */
public class BillDateAndReqDate {

  public boolean isBillDateAfterReqDate(UFDate billdate, UFDate reqDate) {
    if ((null == billdate) || (null == reqDate)) {
      return false;
    }

    return billdate.after(reqDate);
  }

  public boolean isBillDateAfterReqDate(UFDate billdate, UFDate[] reqDates) {
    if (null == billdate) {
      return false;
    }

    if (null != reqDates) {
      for (UFDate reqDate : reqDates) {
        if ((null != reqDate) && billdate.after(reqDate)) {
          return true;
        }
      }
    }

    return false;
  }
}
