/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 ����11:01:48
 */
package nc.vo.pu.est.entity.m50;

import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ����ݹ��ۺ�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 ����11:01:48
 */
public class VmiEstVO extends EstVO {

  /**
   * 
   */
  private static final long serialVersionUID = -5927313430739699385L;

  @Override
  public VmiFIFeeVO[] getChildrenVO() {
    return (VmiFIFeeVO[]) super.getChildrenVO();
  }

  @Override
  public VmiEstHeaderVO getParentVO() {
    return (VmiEstHeaderVO) super.getParentVO();
  }

}
