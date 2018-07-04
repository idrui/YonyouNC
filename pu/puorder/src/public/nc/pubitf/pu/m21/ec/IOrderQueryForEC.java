package nc.pubitf.pu.m21.ec;

import java.util.List;
import java.util.Map;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * ���������ѯ����
 * 
 * @since 6.0
 * @version 2011-3-15 ����05:57:55
 * @author wuxla
 */

public interface IOrderQueryForEC {
  /**
   * ���ݲ�ѯ��������ɹ������ۺ�VO
   * 
   * @param condVo ��ѯ����
   * @return �ɹ������ۺ�VO
   */
  OrderVO[] linkQueryOrderVOByConds(LinkQueryCondVO condVo)
      throws BusinessException;

  /**
   * ��ѯ����ͨ���Ķ���
   * 
   * @param queryScheme ��ѯ����
   * @return ������ͷVO����
   * @throws BusinessException
   */
  OrderApprovedViewVO[] queryApprovedOrder(OrderApprovedQueryCondVO condVO)
      throws BusinessException;

  /**
   * ����������ѯ��Ʒ�����¼�
   * 
   * @param purchaseOrg �ɹ���֯
   * @param moids ����OID����
   * @param startDate ��ʼѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @param endDate ����ѯ�۵�������<font color="red">����Ϊ�գ�</font>
   * @return OrderPriceData[]���ز鵽������
   * @throws BusinessException
   */
  OrderPriceData[] queryLatestPrice(String purchaseOrg, String[] moids,
      UFDate startDate, UFDate endDate, String pk_currery)
      throws BusinessException;

  /**
   * ��ѯ����״̬Ϊ���ͨ���ġ���Ӧ��ά������ҷ����ĵ����ƻ�����ѯ������Ϣ
   * ���б���ʽչ�֣����Ҳ�ѯ������չ�Ӧ���ύ�����ƻ�����˳������
   * 
   * @param queryScheme ��ѯ����
   * @return �����ƻ���ͼVO
   * @throws BusinessException
   */
  OrderReceivePlanPubedViewVO[] queryMaintainedAndPublishedRecPlans(
      OrderReceivePlanPubedQueryCondVO condVO, String srcType)
      throws BusinessException;

  /**
   * ��Ӧ�̵�½EC�Ż� -> ����ų̼ƻ��ڵ� -> �����ѯ���� -> ��ѯ��ȷ���ų̼ƻ���
   * ���������������ѯ��ȷ�ϵĲɹ�������
   * 
   * @param cond
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryOnConfirmOrder(OnConfirmOrderQueryCondVO condVO)
      throws BusinessException;

  /**
   * ����������ѯ����ִ�����
   * 
   * @param queryScheme ��ѯ����
   * @return ������ͷVO����
   * @throws BusinessException
   */
  OrderExecDetailHeadVO[] queryOrderExecDetail(OrderExecDetailQueryCondVO condVO)
      throws BusinessException;

  /**
   * ���ݶ�������������ѯ����ִ�������ϸ��Ϣ
   * 
   * @param hid ������ͷid
   * @return ������ͼVO����
   * @throws BusinessException
   */
  OrderExecDetailViewVO[] queryOrderExecDetailByHid(String[] hids)
      throws BusinessException;

  /**
   * ���ݶ�������������ѯ�����ӱ���Ϣ
   * 
   * @param hid �ɹ�������ͷ����
   * @return ��������
   * @throws BusinessException
   */
  OrderItemApprovedVO[] queryOrderItemApprovedVOByHid(String[] hids)
      throws BusinessException;

  /**
   * ���ݶ�������������ѯ�����ӱ���Ϣ���Ѿ�����
   * 
   * @param hids �ɹ�������ͷ����
   * @return
   * @throws BusinessException
   */
  OrderItemPubedVO[] queryOrderItemPubedVOByHid(String[] hids)
      throws BusinessException;

  /**
   * ���ݲ�Ʒ�������鿴�ò�Ʒ�Ķ�����ϸ
   * 
   * @param pk_org �ɹ���֯
   * @param pk_mateirals ��Ʒ����
   * @param pk_supplier ��Ӧ������
   * @param startDate ������ʼ����
   * @param endDate ���ܽ�������
   * @return ������ϸVO����
   * @throws BusinessException
   */
  OrderMatViewECVO[] queryOrderItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException;

  /**
   * ���ݱ���������ѯ����VO����
   * 
   * @param bids ��������
   * @return ����VO����
   * @throws BusinessException
   */
  OrderItemVO[] queryOrderItemsForEC(String[] bids) throws BusinessException;

  /**
   * ���ݱ���������ѯ�ɹ�����VO
   * 
   * @param bids ��������
   * @return �ɹ�����VO
   * @throws BusinessException
   */
  OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException;

  /**
   * ��ѯ�Ѿ������Ķ�����Ϣ
   * 
   * @param queryScheme ��ѯ����
   * @return ��������VO
   * @throws BusinessException
   */
  OrderPublishedViewVO[] queryPublishOrder(OrderPublishedQueryCondVO condVO)
      throws BusinessException;

  /**
   * ��ѯ�ɹ�����������Ӧ�����ƻ���
   * 
   * @param hid �ɹ���������
   * @return key:�ɹ���������, value:�����ƻ���ArrayList
   * @throws BusinessException
   */
  Map<String, List<String>> queryReceivePlanCode(String[] hids)
      throws BusinessException;

  /**
   * �Ż���Ӧ��ά�������ƻ�Ҫ���ݲɹ�������ά�������ƻ�����Ӧ���ܲ��յ��Ķ�
   * ����Χ��Ҫ����������ǣ�����ҵ�������е����ƻ�������״̬Ϊ���ͨ���ģ�
   * �Ҷ������ջ������֯��Ϊ�յı���Ӧ�̵Ĳɹ���������ѯ������Ϣ���б���ʽչ
   * �֣����Ҳ�ѯ��������ύ����˳������
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderReceivePlanViewECVO[] queryReceivePlansForSupplierMaintain(
      OrderReceivePlanQueryCondVO condVO) throws BusinessException;

  /**
   * ���ݶ�������������ѯ�ۼƵ����ƻ���ϸ
   * 
   * @param bid ������������
   * @return �����ƻ�VO����
   * @throws BusinessException
   */
  OrderReceivePlanECVO[] queryReceivePlanVOsByBId(String[] bids)
      throws BusinessException;

  /**
   * ���ݶ���������ѯ�ۼƵ����ƻ���ϸ
   * 
   * @param hid ��������
   * @return �����ƻ�VO����
   * @throws BusinessException
   */
  OrderReceivePlanECVO[] queryReceivePlanVOsByHId(String[] hids)
      throws BusinessException;

  /**
   * ��ѯ�Ƿ����������Ķ�����Ϣ
   * 
   * @param queryScheme ��ѯ����
   * @return ��������VO
   * @throws BusinessException
   */
  OrderReleasedOverViewVO[] queryReleasedOverOrder(
      OrderReleasedOverQueryCondVO condVO) throws BusinessException;

  /**
   * ���ݵ����ƻ�������ѯ�����ƻ���Ϣ
   * 
   * @param bb1ids �����ƻ�����
   * @return �����ƻ�VO
   * @throws BusinessException
   */
  OrderReceivePlanVO[] queryRPVOsByIds(String[] bb1ids)
      throws BusinessException;

  /**
   * ����������ѯ��Ʒ�Ĺ������
   * 
   * @param queryScheme ��ѯ����
   * @return �������VO����
   * @throws BusinessException
   */
  SupplyDetailVO[] querySupplyDetail(SupplyDetailQueryVO condVO)
      throws BusinessException;

  /**
   * ��������Ӧ���Ż�ȷ�ϣ����涩����
   * ���뱣֤���ݵ�������
   * 
   * @param orderVos
   * @param ctxs
   * @return
   * @throws BusinessException
   */
  OrderVO[] save(OrderVO[] orderVos) throws BusinessException;

  /**
   * @param batchVO ���������޸ĵĵ����ƻ�
   * @param orderVO �ɹ���������Ҫ��������ts
   * @return [0]ΪBatchOperateVO��[1]ΪOrderVO
   * @throws BusinessException
   */
  Object[] saveReveivePlanVO(BatchOperateVO batchVO, OrderVO orderVO)
      throws BusinessException;
}
