/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 ����06:18:38
 */
package nc.ui.pu.m20.rule;

import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ں��������ڹ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-11 ����06:18:38
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
