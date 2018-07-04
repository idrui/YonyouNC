/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 ����06:44:02
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 ����06:44:02
 */
public class FunctionUtil {

  /**
   * ��������������ȡ���빺�����������PK��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����06:46:08
   */
  public static List<String> getPkMaterial(PraybillVO vo) {
    if (null == vo) {
      return null;
    }

    PraybillItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    // ��֯���ظ��Ĵ����������ID
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
   * ��������������ȡ���빺������ϼ�ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          �빺��VO
   * @param arr
   *          �ϼ�����
   * @return <p>
   *         �ϼ�ֵ
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����08:45:01
   */
  public static UFDouble getSum(PraybillVO vo, String arr) {
    UFDouble sum = new UFDouble(0.0);// �빺���ϼ�����
    UFDouble dblPrayNum;// �빺������
    if (vo.getBVO() != null && vo.getBVO().length > 0) {
      // ������岻Ϊ�գ�����ϼ�����
      for (int i = 0; i < vo.getBVO().length; i++) {
        Object objPrayNum = vo.getBVO()[i].getAttributeValue(arr);// ��ȡVO�е�����

        if (objPrayNum == null || objPrayNum.toString().length() == 0) {
          dblPrayNum = new UFDouble(0.0);// ���Ϊ������Ϊ0
        }
        else {
          dblPrayNum = new UFDouble(objPrayNum.toString().trim());
        }

        sum = sum.add(dblPrayNum);// �ۼ�����
      }
    }
    return sum;
  }

  /**
   * �������������������ݿ���ȡ���빺������ϼ�ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          �빺��VO
   * @param arr
   *          �ϼ�����
   * @return <p>
   *         �ϼ�ֵ
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����08:45:01
   */
  public static UFDouble getSumFromDB(String pk_praybill, String arr) {
    UFDouble sum = new UFDouble(0.0);// �빺���ϼ�����

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
                                                                   * "�����빺����������ʱ����!\n"
                                                                   */
          + e.getMessage());
    }
    return sum;

  }
}
