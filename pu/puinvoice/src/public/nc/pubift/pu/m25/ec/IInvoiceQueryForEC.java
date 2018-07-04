package nc.pubift.pu.m25.ec;

import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pub.BusinessException;

/**
 * Ϊ���������ṩ��Ʊ��ѯ
 * 
 * @since 6.0
 * @version 2011-5-7 ����11:24:20
 * @author wuxla
 */

public interface IInvoiceQueryForEC {

  /**
   * ���ݶ�������������ѯ��Ʊ��ϸ��Ϣ
   * 
   * @param bid ��������
   * @return ��Ʊ��ͼVO����
   * @throws BusinessException
   */
  InvoiceOrderViewECVO[] queryInvoiceByOrderBid(String[] bids)
      throws BusinessException;

  /**
   * ���ݶ���������ѯ��Ʊ��ϸ��Ϣ
   * 
   * @param hid ��������
   * @return ��Ʊ��ͼVO����
   * @throws BusinessException
   */
  InvoiceOrderViewECVO[] queryInvoiceByOrderHid(String[] hid)
      throws BusinessException;

  /**
   * ���ݲ�Ʒ�������鿴�ò�Ʒ�ķ�Ʊ��ϸ
   * 
   * @param pk_materials ��Ʒ����
   * @param pk_supplier ��Ӧ������
   * @return ��Ʊ��ͼVO����
   * @throws BusinessException
   */
  InvoiceMatViewECVO[] queryInvoiceItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException;

  /**
   * ���ݷ�Ʊ������ѯ��Ʊ��ϸ
   * 
   * @param hid ��ͷ����
   * @return ��Ʊ��ϸVO����
   * @throws BusinessException
   */
  InvoiceItemECVO[] queryInvoiceItemVOByHid(String[] hid)
      throws BusinessException;

  /**
   * ���ݲ�ѯ������ѯ��Ʊ��Ϣ
   * 
   * @param condVo ��ѯ����VO
   * @return ��������VO����
   * @throws BusinessException
   */
  InvoiceNumSummaryECVO[] queryNumSummaryByCond(
      InvoiceSupplyDetailQueryCondVO condVo) throws BusinessException;

  /**
   * ���ݶ�����ʵ��ID��ѯ��Ʊ��Ϣ
   * 
   * @param bids ��������ID����
   * @return ��������VO����
   * @throws BusinessException
   */
  InvoiceNumSummaryECVO[] queryNumSummaryByOrderBids(String[] bids)
      throws BusinessException;

  /**
   * ��������Ӧ���Ż��Ĳɹ�������Ӧ�ķ�Ʊ�����ҷ�Ʊ״̬Ϊ����ͨ������ѯ����
   * ��Ϣ���б���ʽչ�֣����Ҳ�ѯ������շ�Ʊ����˳������
   * 
   * @param queryScheme
   * @return �ɹ���Ʊ��ͷVO����
   * @throws BusinessException
   */
  InvoicePublishedViewVO[] queryPublishedAndApprovedInvoice(
      InvoicePublishedQueryCondVO condVO) throws BusinessException;
}
