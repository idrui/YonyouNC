package nc.pubitf.pu.tbb;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-20 ����10:15:52
 * @author wuxla
 */

public interface ILinkQueryForTbb {

  /**
   * �����빺��
   * 
   * @param param Ԥ�㹫ʽ
   * @return �빺��
   * @throws BusinessException
   */
  PraybillVO[] linkQuery20ForTbb(NtbParamVO param) throws BusinessException;

  /**
   * ����ɹ�����
   * 
   * @param param Ԥ�㹫ʽ
   * @return �ɹ�����
   * @throws BusinessException
   */
  OrderVO[] linkQuery21ForTbb(NtbParamVO param) throws BusinessException;

  /**
   * �ɹ���Ʊ
   * 
   * @param param Ԥ�㹫ʽ
   * @return �ɹ���Ʊ
   * @throws BusinessException
   */
  InvoiceVO[] linkQuery25ForTbb(NtbParamVO param) throws BusinessException;
}
