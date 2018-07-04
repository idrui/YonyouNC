/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-16 下午04:36:29
 */
package nc.vo.pu.m20.pub;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>固定提前期计算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-16 下午04:36:29
 */
public class AdDaysUtil {
  /**
   * 提前期算法
   * 
   * @param ufdNum
   *          数量
   * @param ufdFixedahead
   *          固定提前期
   * @param ufdAheadcoff
   *          提前期系数
   * @param ufdAheadbatch
   *          提前期批量
   * @return 提前期
   * @since 6.0
   * @author GGR
   * @time 2010-4-16 下午04:37:21
   */
  public static int getAdDaysArith(UFDouble ufdNum, UFDouble ufdFixedahead,
      UFDouble ufdAheadcoff, UFDouble ufdAheadbatch) {

    double dNum = 0;
    if (ufdNum != null && ufdNum.doubleValue() > 0.0) {
      dNum = ufdNum.doubleValue();
    }
    double dFix = 0;
    if (ufdFixedahead != null && ufdFixedahead.doubleValue() > 0.0) {
      dFix = ufdFixedahead.doubleValue();
    }
    double dBatch = 0;
    if (ufdAheadbatch != null && ufdAheadbatch.doubleValue() > 0.0) {
      dBatch = ufdAheadbatch.doubleValue();
    }

    double dRate = 0;
    if (ufdAheadcoff != null && ufdAheadcoff.doubleValue() > 0.0) {
      dRate = ufdAheadcoff.doubleValue();
    }

    if (dNum > dBatch && dBatch > 0.0) {
      double dResult = dFix + (dNum - dBatch) * dRate / dBatch;
      int iResult = (int) dResult;
      if (dResult - iResult > 0) {
        iResult++;
      }
      return iResult;
    }

    return (int) dFix;

  }

  /**
   * 功能描述:判断日期是否溢出
   * 
   * @param d
   * @return 是否溢出
   */
  public static boolean isDateOverflow(UFDate d) {
    String s = d.toString();
    final int i = s.indexOf("-");
    String s1 = s.substring(0, i);
    final int j = s1.length();
    if (j > 4) {
      return true;
    }
    return false;
  }
}
