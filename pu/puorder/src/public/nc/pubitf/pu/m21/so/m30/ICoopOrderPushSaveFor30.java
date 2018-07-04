/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����02:01:13
 */
package nc.pubitf.pu.m21.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������Эͬ�ɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����02:01:13
 */
public interface ICoopOrderPushSaveFor30 {

  /**
   * �����������������۶�������Эͬ�ɹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param srcVOs
   *          ��Ҫ����Эͬ�ɹ����������۶���
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 ����02:03:50
   */
  public void coopOrderPushSaveFor30(SaleOrderVO[] srcVOs,
      Map<String, Object> result) throws BusinessException;

}
