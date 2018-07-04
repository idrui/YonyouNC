package nc.pubitf.pu.it;

import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;

/**
 * �������ṩ����ط���
 * 
 * @since 6.31
 * @version 2013-11-21 ����01:34:00
 * @author mengjian
 */
public interface ISettleBillMaintainForIT {

  /**
   * �����������������㵥ȡ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param settleBillVOs
   *          ��Ҫȡ��������Ľ��㵥
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-3 ����08:06:48
   */
  public SettleBillVO[] cancelToIA4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException;

  /**
   * ���㵥ɾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param settleBillVOs
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-26 ����10:39:31
   */
  public void deleteSettleBills4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException;

  /**
   * �����������������㵥�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param settleBillVOs
   *          ��Ҫ������Ľ��㵥
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-3 ����08:06:20
   */
  public SettleBillVO[] toIA4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException;
}
