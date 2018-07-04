package nc.pubitf.pu.m21.pub;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.SourceBillInfo;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ����������ṩ�Ĺ�����ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-10 ����10:48:34
 */
public interface IOrderPubQuery {

  /**
   * ���������������������������Ƿ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param transType ��������
   * @return �����õĽ�������
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-11-3 ����09:00:02
   */
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException;

  /**
   * ��������������ͨ����������ID���飬��ѯ����VO����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bidArray
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-10 ����10:51:08
   */
  public OrderItemVO[] queryOrderItem(String[] bidArray)
      throws BusinessException;

  /**
   * ��������������ͨ����������ID���飬��ѯ��ͼVO����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bidArray
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-10 ����10:51:52
   */
  public OrderViewVO[] queryOrderView(String[] bidArray)
      throws BusinessException;

  /**
   * ���ݲɹ�����BID�����ѯ���������գ��ĺ�ͬ��
   * 
   * @param pk_order_bs
   * @return ����Ϊnull
   * @throws BusinessException
   */
  Map<String, String> getCtCodeByBID(String[] pk_order_bs)
      throws BusinessException;

  /**
   * ��������������ȡ�����βɹ�����������Ϣ������������Ϣʱʹ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderIds
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-17 ����09:31:32
   */
  SourceBillInfo[] getOrderBillInfo(String[] orderIds) throws BusinessException;

  /**
   * @param hids
   * @param attrs
   * @return
   * @throws BusinessException
   */
  OrderHeaderVO[] queryOrderHeaderVOByHids(String[] hids, String[] attrs)
      throws BusinessException;

  /**
   * ���ݱ���id��ѯ����VO
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException;

  /**
   * ���ݲɹ�����BID�����ѯ���������գ��Ľ��㷽ʽ����
   * 
   * @param pk_order_bs
   * @return ����Ϊnull
   * @throws BusinessException
   */
  Map<String, String> splitByStypeByBID(String[] pk_order_bs)
		  throws BusinessException;
}
