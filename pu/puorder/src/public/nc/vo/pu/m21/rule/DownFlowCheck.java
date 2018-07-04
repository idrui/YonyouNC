/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-27 ����11:14:02
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-27 ����11:14:02
 */
public class DownFlowCheck {

  /**
   * ��������������ĳһ�������Ƿ���ں�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-15 ����02:38:48
   */
  public boolean hasDownFlow(OrderItemVO itemVO) {
    if (null == itemVO) {
      return false;
    }

    // �ж��ۼƷ�Ʊ���� ���ۼ�������� ���ۼƵ���������ȷ���Ƿ��к�������
    return MathTool.compareTo(itemVO.getNaccuminvoicenum(), UFDouble.ZERO_DBL) > 0
        || MathTool.compareTo(itemVO.getNaccumstorenum(), UFDouble.ZERO_DBL) > 0
        || MathTool.compareTo(itemVO.getNaccumarrvnum(), UFDouble.ZERO_DBL) > 0;
  }

  /**
   * ���������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 ����06:04:09
   */
  public String hasDownFlow(OrderItemVO[] itemVOs) {
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : itemVOs) {
      // �ж��Ƿ��к�������
      if (this.hasDownFlow(itemVO)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0301", null, new String[]{itemVO.getCrowno()})/*��{0}�д��ں�������\n*/);
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * �����������������ĳһ������û�к�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 ����03:09:53
   */
  public boolean hasDownFlow(OrderVO orderVO) {
    if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
      return false;
    }

    for (OrderItemVO itemVO : orderVO.getBVO()) {
      if (this.hasDownFlow(itemVO)) {
        return true;
      }
    }

    return false;
  }

  /**
   * ����������������鶩����û�к�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 ����05:59:22
   */
  public String hasDownFlow(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      boolean first = true;
      for (OrderItemVO itemVO : vo.getBVO()) {
        // �ж��Ƿ��к�������
        if (this.hasDownFlow(itemVO)) {
          if (first) {
            sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0302", null, new String[]{vo.getHVO().getVbillcode()})/*�ɹ�����{0}��\n*/);
            first = false;
          }

          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0301", null, new String[]{itemVO.getCrowno()})/*��{0}�д��ں�������\n*/);
        }
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

}
