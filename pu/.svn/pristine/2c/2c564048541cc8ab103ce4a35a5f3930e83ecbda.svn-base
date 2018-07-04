/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午06:44:02
 */
package nc.bs.pu.m20.pf.function;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

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
 * @author GGR
 * @time 2010-7-5 下午06:44:02
 */
public class FunctionUtil {

  /**
   * 方法功能描述：取得请购单表体的物料PK。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:46:08
   */
  public static List<String> getPkMaterial(PraybillVO vo) {
    if (null == vo) {
      return null;
    }

    PraybillItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    // 组织不重复的存货基本档案ID
    ArrayList<String> listIds = new ArrayList<String>();
    String strBaseId = null;
    for (PraybillItemVO item : itemVOs) {
      if (item == null) {
        continue;
      }
      strBaseId = item.getPk_material();
      if (listIds.contains(strBaseId)) {
        continue;
      }
      listIds.add(strBaseId);
    }
    if (listIds.size() == 0) {
      return null;
    }

    return listIds;
  }

  /**
   * 方法功能描述：取得请购单表体合计值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          请购单VO
   * @param arr
   *          合计属性
   * @return <p>
   *         合计值
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午08:45:01
   */
  public static UFDouble getSum(PraybillVO vo, String arr) {
    UFDouble sum = new UFDouble(0.0);// 请购单合计数量
    UFDouble dblPrayNum;// 请购单数量
    if (vo.getBVO() != null && vo.getBVO().length > 0) {
      // 如果表体不为空，计算合计数量
      for (int i = 0; i < vo.getBVO().length; i++) {
        Object objPrayNum = vo.getBVO()[i].getAttributeValue(arr);// 获取VO中的数量

        if (objPrayNum == null || objPrayNum.toString().length() == 0) {
          dblPrayNum = new UFDouble(0.0);// 如果为空则置为0
        }
        else {
          dblPrayNum = new UFDouble(objPrayNum.toString().trim());
        }

        sum = sum.add(dblPrayNum);// 累加数量
      }
    }
    return sum;
  }

  /**
   * 方法功能描述：从数据库中取得请购单表体合计值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          请购单VO
   * @param arr
   *          合计属性
   * @return <p>
   *         合计值
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午08:45:01
   */
  public static UFDouble getSumFromDB(String pk_praybill, String arr) {
    UFDouble sum = new UFDouble(0.0);// 请购单合计数量

    try {
      String strSql =
          "SELECT SUM ( " + arr + " ) FROM po_praybill_b WHERE pk_praybill = '"
              + pk_praybill + "' AND dr = 0";

      IRowSet rs = new DataAccessUtils().query(strSql);
      while (rs.next()) {
        if (null == rs.getUFDouble(0) || sum.toString().length() == 0) {
          return UFDouble.ZERO_DBL;
        }
        sum = rs.getUFDouble(0);
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0082")/*
                                                                   * @res
                                                                   * "计算请购单整单数量时出错!\n"
                                                                   */
          + e.getMessage());
    }
    return sum;

  }
}
