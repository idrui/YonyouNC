/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-18 ����04:24:28
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�ɱ�Ҫ�ض���
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
 * @time 2009-5-18 ����04:24:28
 */
public interface ICostFactorQueryService {
  /**
   * ���������������ɱ�Ҫ�ط������Ƿ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����09:32:42
   */
  public boolean beReferenced(String pk_group, String pk_org,
      String pk_srcmaterial) throws BusinessException;

  /**
   * ���������������ɱ�Ҫ�ض����ѯ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param context �û���¼��Ϣ��
   * @return �ɱ�Ҫ�ض���VO����
   * @throws BusinessException
   * @since 6.0
   */
  public CostfactorVO[] queryAllCostFactor(LoginContext context)
      throws BusinessException;

  /**
   * ���ݿɼ��Ĳ�����֯��ѯ�ɱ�Ҫ��
   * 
   * @param context
   * @param permissonOrgs
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryAllCostFactor(LoginContext context,
      String[] permissonOrgs) throws BusinessException;

  /**
   * ��ѯָ��������֯�µĳɱ�Ҫ��
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryCostfacotorIn(String pk_org)
      throws BusinessException;

  /**
   * ��ѯָ��������֯�µĳɱ�Ҫ��(�����˱���Ϊ��)
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryCostfacotorInNofilter(String pk_org)
      throws BusinessException;

  /**
   * ���������������ڳɱ�Ҫ���в�ѯ�����ݹ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_org��������֯
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-19 ����09:42:39
   */
  public CostfactorViewVO[] queryEstCostItems(String pk_org)
      throws BusinessException;

}
