/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-13 ����01:19:09
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɱ�Ҫ�ض����������������
 * <li>�ɱ�Ҫ�ض�����²�����
 * <li>�ɱ�Ҫ�ض���ɾ��������
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-13 ����01:19:09
 */
public interface ICostFactorManageService {
  /**
   * ���������������ɱ�Ҫ�ض���ɾ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVo ��Ҫɾ���ĳɱ�Ҫ�ض��塣
   * @throws BusinessException
   * @since 6.0
   */
  void delete(CostfactorVO newVo) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض���ɾ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVos ��Ҫɾ���ĳɱ�Ҫ�ض������顣
   * @throws BusinessException
   * @since 6.0
   */
  void delete(CostfactorVO[] newVos) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض����������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVo ��Ҫ�������ݿ�ĳɱ�Ҫ�ض��塣
   * @return �����ĳɱ�Ҫ�ض���
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorVO insert(CostfactorVO newVo) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض����������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVos ��Ҫ�������ݿ�ĳɱ�Ҫ�ض������顣
   * @return �����ĳɱ�Ҫ�ض�������
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorVO[] insert(CostfactorVO[] newVos) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض�����²�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVo ��Ҫ���µ����ݿ�ĳɱ�Ҫ�ض��塣
   * @return �����ĳɱ�Ҫ�ض���
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorVO update(CostfactorVO newVo) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض�����²�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param newVos ��Ҫ���µ����ݿ�ĳɱ�Ҫ�ض������顣
   * @return �����ĳɱ�Ҫ�ض�������
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorVO[] update(CostfactorVO[] newVos) throws BusinessException;

}
