/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 ����03:22:32
 */
package nc.pubitf.pu.m20.pub;

import java.util.Map;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����ѯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 ����03:22:32
 */
public interface IQueryPrayBill {

  /**
   * ��鶩�����������Ƿ��빺������Ķ�����������
   * 
   * @param ordertransType ����������������
   * @return
   * @throws BusinessException
   */
  String[] checkOrderTransTypeReference(String[] ordertransType)
      throws BusinessException;

  /**
   * ���������������������������Ƿ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param transType �빺���������ͱ���
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-11-3 ����03:23:26
   */
  String[] checkTransTypeReference(String[] transType) throws BusinessException;

  /**
   * �빺���Ƿ�ֱ��
   * 
   * @param pk_praybills
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> isDirect(String[] pk_praybills)
      throws BusinessException;

  /**
   * �����빺������������ѯֱ����Դ���۶�������
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, String> queryDirectSourceBidMap(String[] bids)
      throws BusinessException;

  /**
   * �����빺���в�ѯ��Ӧ�빺������
   * 
   * @param pk_praybill_bs �빺����
   * @param arrs ��Ҫ��ѯ������
   * @return Map<String, PraybillItemVO> KEY:�빺����
   * @throws BusinessException
   */
  Map<String, PraybillItemVO> queryItemByPK(String[] pk_praybill_bs,
      String[] arrs) throws BusinessException;

  /**
   * �������������������빺���в�ѯ��Ӧ�������Һ�˰���ۣ�����Ϊ�빺������֯����������֯�ı�λ�ң���λΪ���ϵ�����λ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_praybill_bs
   *          ��Ҫ��ѯ���빺����
   * @return Map<�빺���У������Һ�˰����>
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 ����10:18:43
   */
  Map<String, UFDouble> queryPriceByItemPK(String[] pk_praybill_bs)
      throws BusinessException;

  /**
   * �����빺���в�ѯ��Ӧ�빺������
   * 
   * @param pk_praybill_bs �빺����
   * @param arrs ��Ҫ��ѯ������
   * @return Map<String, PraybillViewVO> KEY:�빺����
   * @throws BusinessException
   */
  Map<String, PraybillViewVO> queryViewByItemPK(String[] pk_praybill_bs,
      String[] arrs) throws BusinessException;

  PraybillVO[] queryVOByBids(String[] bids) throws BusinessException;
}
