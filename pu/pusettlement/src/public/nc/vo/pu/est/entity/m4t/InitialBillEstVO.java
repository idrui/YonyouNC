/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 ����11:16:09
 */
package nc.vo.pu.est.entity.m4t;

import nc.vo.pu.est.entity.EstVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ�������ݹ���Ϣ�ľۺ�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-10-21 ����11:16:09
 */
public class InitialBillEstVO extends EstVO {

  private static final long serialVersionUID = -7143596292898888513L;

  @Override
  public InitialBillGoodsEstVO getParentVO() {
    return (InitialBillGoodsEstVO) super.getParentVO();
  }

}
