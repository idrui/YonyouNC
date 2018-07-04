package nc.pubift.pu.m25.ia;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:IInvoiceQueryForIAClosingCheck
 * @Description:�ɹ���Ʊ��Ϊ��������ṩ���˼��ӿ�
 * @author liyjp
 * @date 2014-11-17 ����4:06:12
 */
public interface IInvoiceQueryForIAClosingCheck {

  /**
   * @Title:queryUnapprovedInvoice
   * @Description:�������δ�����ɹ���Ʊ��ѯ�ӿڷ���
   *                                ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬������֯=��ǰҵ��Ԫ������̬�Ĳɹ���Ʊ
   * @param @param queryParaVO ��ѯ����VO
   *        ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @param @throws BusinessException ҵ���쳣
   * @return InvoiceHeaderVO[] �ɹ���Ʊ������ͷ��VO
   * @throws
   */
  InvoiceHeaderVO[] queryUnapprovedInvoice(QueryParaVO queryParaVO)
      throws BusinessException;

  /**
   * @Title:queryUnsettledInvoice
   * @Description:�������δ����ɹ���Ʊ��ѯ�ӿڷ���
   *                                ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬������֯=��ǰҵ��Ԫ��������δ����Ĳɹ���Ʊ
   * @param @param queryParaVO ��ѯ����VO
   *        ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @param @throws BusinessException ҵ���쳣
   * @return InvoiceHeaderVO[] �ɹ���Ʊ������ͷ��VO
   * @throws
   */
  InvoiceHeaderVO[] queryUnsettledInvoice(QueryParaVO queryParaVO)
      throws BusinessException;
}
