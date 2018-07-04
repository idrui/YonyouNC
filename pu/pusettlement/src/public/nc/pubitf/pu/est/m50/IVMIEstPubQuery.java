/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-25 ����10:52:54
 */
package nc.pubitf.pu.est.m50;

import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ���Ļ����ݹ���Ϣ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-25 ����10:52:54
 */
public interface IVMIEstPubQuery {
  /**
   * ���������������������Ļ���BID(��HID��ͬ)��ѯ�ݹ���Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids
   *          ���Ļ���BID(��HID)����
   * @return VmiEstVO[] ָ��ID���ݹ�VO�������ݹ���δ�ݹ�������
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-25 ����10:57:48
   */
  public VmiEstVO[] query(String[] bids) throws BusinessException;
}
