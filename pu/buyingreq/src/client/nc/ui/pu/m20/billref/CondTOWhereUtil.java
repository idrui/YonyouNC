/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 ����12:01:59
 */
package nc.ui.pu.m20.billref;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����Ԫ���ݲ�ѯ����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-1 ����12:01:59;
 */
public class CondTOWhereUtil {

  /**
   * ���������������Ƿ����δ��������Ϊ0���빺���д���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��й�Ӧ����Ч�۸���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
   */
  public static void buildIseffectprice(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }

  /**
   * ���������������Ƿ��Ѿ�����ѯ���۵�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��Ѿ����ɲɹ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��Ѿ����ɺ�ͬ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ����δ��������Ϊ0���빺���д���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-1 ����11:57:00
   */
  public static void buildIspositioninv(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }
}
