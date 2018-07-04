/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 ����08:19:42
 */
package nc.itf.pu.m21;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ƻ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-13 ����08:19:42
 */
public interface IOrderReceivePlan extends ISmartService {

  /**
   * �����������������浽���ƻ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param batchVO
   * @param orderVO
   * @return [0]ΪBatchOperateVO��[1]ΪOrderVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 ����10:35:06
   */
  public Object[] batchSave(BatchOperateVO batchVO, OrderVO orderVO,
      UFBoolean confirm) throws BusinessException;

  /**
   * ��������������Ϊ����ͷID��ѯ���������Ķ��������ƻ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param hid
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 ����11:21:46
   */
  public OrderReceivePlanVO[] queryPlanVOsByHId(String hid)
      throws BusinessException;

}
