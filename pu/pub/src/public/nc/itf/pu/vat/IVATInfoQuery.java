package nc.itf.pu.vat;

import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pub.BusinessException;

/**
 * VAT��Ϣ��ѯ�ӿ�
 * 
 * @since 6.0
 * @version 2012-2-15 ����09:28:37
 * @author wuxla
 */
public interface IVATInfoQuery {

  /**
   * ��ѯ˰���˰�ʵ������Ϣ
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param queryVOs VAT��Ϣ��ѯ����VO����
   * @return �����һһ��Ӧ��VAT˰����Ϣ
   * @throws BusinessException
   */
  VATInfoVO[] queryTaxInfo(VATInfoQueryVO[] queryVOs) throws BusinessException;

  /**
   * ��ѯ˰�롢˰�ʵ������Ϣ�Լ�������˰��־
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param vatqueryVOs VAT��Ϣ��ѯ����VO����
   * @param opptaxqueryvos ������˰��־��ѯ����VO����
   * @return
   * @throws BusinessException
   */
  VATInfo queryTaxInfoAndOppTaxFlag(VATInfoQueryVO[] vatqueryVOs,
      OppTaxFlagQueryVO[] opptaxqueryvos,
      CustSuppVATCodeQueryVO[] custSuppVATCodeQueryVOs,
      OrgVATCodeQueryVO[] orgVATCodeQueryVOs) throws BusinessException;

  /**
   * ����˰�롢ҵ�����ڲ�ѯVAT˰����Ϣ����VO����
   * <p>
   * ʹ�ó������༭˰���
   * <ul>
   * <li>
   * </ul>
   * 
   * @param queryVOs
   * @return
   * @throws BusinessException
   */
  VATInfoVO[] queryTaxInfoByTaxcode(VATInfoByTaxcodeQueryVO[] queryVOs)
      throws BusinessException;
}
