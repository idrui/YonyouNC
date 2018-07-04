/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 ����05:04:13
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ж϶����Ƿ�ر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-31 ����05:04:13
 */
public class CloseCheck {

  /**
   * ���������������жϱ����Ƿ�ر�
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 ����06:08:50
   */
  public String closeCheck(OrderItemVO[] itemVOs) {
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : itemVOs) {
      // �жϼ���״̬
      if (this.isClose(itemVO)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0299", null, new String[]{itemVO.getCrowno()})/*��{0}���ѹر�\n*/);
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * ���������������ж϶����Ƿ�ر�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 ����04:55:21
   */
  public String closeCheck(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();

    for (OrderVO vo : vos) {
      if ((null == vo) || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      boolean first = true;
      for (OrderItemVO itemVO : vo.getBVO()) {
        // �жϼ���״̬
        if (this.isClose(itemVO)) {
          if (first) {
            sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0283", null, new String[]{vo.getHVO().getVbillcode()})/*����{0}\n*/);
            first = false;
          }

          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0299", null, new String[]{itemVO.getCrowno()})/*��{0}���ѹر�\n*/);
        }

      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * ���������������������Ƿ�ر�
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-15 ����01:42:21
   */
  private boolean isClose(OrderItemVO itemVO) {
    if (null == itemVO) {
      return false;
    }

    // �жϼ���״̬
    Integer iActive = itemVO.getFisactive();
    return ObjectUtils.equals(iActive, EnumActive.CLOSE.value());
  }
}
