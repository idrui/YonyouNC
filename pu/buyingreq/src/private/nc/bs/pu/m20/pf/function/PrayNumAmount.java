/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 ����08:42:35
 */
package nc.bs.pu.m20.pf.function;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ϼ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 ����08:42:35
 */
public class PrayNumAmount {
  /**
   * ��������:������ҵ����(��ȡ�빺���ϼ�������)<br>
   * ���VOΪ��������VO�����ݼ���<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          �빺��VO
   * @return <p>
   *         �ϼ�������
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����08:43:19
   */
  public UFDouble getPrayNumAmount(AggregatedValueObject vo) {
    if ((vo == null) || (vo.getParentVO() == null)
        || (vo.getChildrenVO() == null) || (vo.getChildrenVO().length <= 0)) {
      return new UFDouble(0.0);
    }

    String strHeadPK = ((PraybillVO) vo).getHVO().getPk_praybill();// ��ȡͷ������
    // ͷ������Ϊ�գ�˵��������VO,��VO�л�ȡ���ݼ���ϼ�������
    if ((strHeadPK == null) || strHeadPK.trim().equals("")) {
      return FunctionUtil.getSum((PraybillVO) vo, PraybillItemVO.NNUM);
    }

    return FunctionUtil.getSumFromDB(strHeadPK, PraybillItemVO.NNUM);

  }
}
