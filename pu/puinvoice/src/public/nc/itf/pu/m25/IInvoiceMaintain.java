/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����10:49:24
 */
package nc.itf.pu.m25;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊά������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 ����10:49:24
 */
public interface IInvoiceMaintain {
  public void delete(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException;

  /**
   * ����ǰ̨ȫvo����Ķ���
   * 
   * @param clientVos ǰ̨ȫvo����
   * @param origVos ԭʼvo����
   * @return ȫvo
   * @throws BusinessException
   */
  public InvoiceVO[] freeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException;

  public InvoiceVO[] freezeByLightVOs(InvoiceVO[] vos) throws BusinessException;

  /**
   * ��������
   * 
   * @param vos Ҫ����ķ�ƱVO������ΪȫVO
   * @param env ������Ϣ
   * @return ����һ����ȫVO
   * @throws BusinessException
   */
  public InvoiceVO[] insert(InvoiceVO[] vos, InvoiceUIToBSEnv env)
      throws BusinessException;

  /**
   * �ɹ���Ʊ���棨�������޸ģ�
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>������ƽ̨�ű����ñ���
   * <li>ǰ̨�������棨��֤����ȫVO��
   * </ul>
   * 
   * @param vos Ҫ����ķ�ƱVO������ΪȫVO
   * @param envs ������Ϣ
   * @return ����һ����ȫVO
   * @throws BusinessException
   */
  public InvoiceVO[] save(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException;

  /**
   * ����ǰ̨ȫvo����Ľⶳ
   * 
   * @param clientVos ǰ̨ȫvo����
   * @param origVos ԭʼȫvo����
   * @return ȫvo
   * @throws BusinessException
   */
  public InvoiceVO[] unFreeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException;

  public InvoiceVO[] unFreezeByLightVOs(InvoiceVO[] vos)
      throws BusinessException;

  /**
   * �޸ı���
   * 
   * @param vos Ҫ����ķ�ƱVO������ΪȫVO
   * @param env ������Ϣ
   * @return ����һ����ȫVO
   * @throws BusinessException
   */
  public InvoiceVO[] update(InvoiceVO[] vos, InvoiceUIToBSEnv env)
      throws BusinessException;
}
