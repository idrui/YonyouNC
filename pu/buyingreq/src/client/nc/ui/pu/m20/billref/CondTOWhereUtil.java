/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 下午12:01:59
 */
package nc.ui.pu.m20.billref;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单非元数据查询条件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-1 下午12:01:59;
 */
public class CondTOWhereUtil {

  /**
   * 方法功能描述：是否过滤未购辅数量为0的请购单行处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildFilterzeroflag(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NACCUMULATENUM + " = "
          + itemtb + "." + PraybillItemVO.NNUM);
    }
  }

  /**
   * 方法功能描述：是否有供应商有效价格处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIseffectprice(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }

  /**
   * 方法功能描述：是否已经生成询报价单处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsgenaskbill(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NQUOTEBILL + " > 0");
    }
    else if (UFBoolean.FALSE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NQUOTEBILL + " = 0");
    }
  }

  /**
   * 方法功能描述：是否已经生成采购订单处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsgenorderbill(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NACCUMULATENUM + " > 0");
    }
    else if (UFBoolean.FALSE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NACCUMULATENUM + " = 0");
    }
  }

  /**
   * 方法功能描述：是否已经生成合同处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsngenct(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NGENCT + " > 0");
    }
    else if (UFBoolean.FALSE.toString().equals(cond.getValue())) {
      where.append(" and ");
      where.append(itemtb + "." + PraybillItemVO.NGENCT + " = 0");
    }
  }

  /**
   * 方法功能描述：是否过滤未购辅数量为0的请购单行处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIspositioninv(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }
}
