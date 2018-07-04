package nc.pubitf.pu.m4201;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ɹ���ⵥǩ��д�������
 * <li>���ɹ���ⵥȡ��ǩ��ɾ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-27 ����04:08:04
 */
public interface IStockFinanceMaintain {
  /**
   * �����������������ɹ���ⵥȡ��ǩ��ɾ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cpurchaseinids
   *          �ɹ���ⵥ�ı�ͷID����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 ����04:13:08
   */
  public void deleteForM45Unsign(String[] cpurchaseinids)
      throws BusinessException;

  /**
   * �����������������ɹ���ⵥǩ��д�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param purchaseinVos
   *          �ɹ���ⵥ��VO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 ����04:13:03
   */
  public void insertForM45Sign(PurchaseInVO[] purchaseinVos)
      throws BusinessException;
}
