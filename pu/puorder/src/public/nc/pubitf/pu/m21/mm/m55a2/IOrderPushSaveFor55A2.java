/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 ����02:13:12
 */
package nc.pubitf.pu.m21.mm.m55a2;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������죺����������ʽ����ɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 ����02:13:12
 */
public interface IOrderPushSaveFor55A2 {

  /**
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>����������ʽ����ɹ�����
   * </ul>
   * 
   * @param orderVOs ����vo����
   * @param SourceReturnVOs ѰԴvo���飬�붩��vo����һһ��Ӧ���������κ������䵥�۵�
   * @throws BusinessException
   */
  OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException;
}
