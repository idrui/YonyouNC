/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����11:19:43
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ݹ���������ʾ˳�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-19 ����11:19:43
 */
public interface ICostFactorItemService {
  /**
   * ���������������ɱ�Ҫ�ض�����²�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVo
   *          ��Ҫ���µ����ݿ���ݹ������
   * @return �����ĳɱ�Ҫ�ض���
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorItemVO update(CostfactorItemVO newVo) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض�����²�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVos
   *          ��Ҫ���µ����ݿ���ݹ����������顣
   * @return �����ĳɱ�Ҫ�ض�������
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorItemVO[] update(CostfactorItemVO[] newVos) throws BusinessException;
}
