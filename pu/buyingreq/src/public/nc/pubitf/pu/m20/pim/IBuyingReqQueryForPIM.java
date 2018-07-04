package nc.pubitf.pu.m20.pim;

import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

/**
 * Ϊ��Ŀ�����ṩ�Ĳ�ѯ����
 * 
 * @since 6.1
 * @version 2012-3-6 ����01:30:40
 * @author yangtian
 */
public interface IBuyingReqQueryForPIM {

  /**
   * Ϊ��Ŀ���������ṩ�Ĳ�ѯ����
   * 
   * @param pvo ��ѯ����vo
   * @return ���������Ķ����ۺ�vo
   * @throws BusinessException
   */
  public PraybillVO[] queryBuyingReqForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException;

  /**
   * �������ʼ���������ʱ, ������Ŀ�͡��빺���ڡ������ڼ��ѯ�빺��������̬�������С�����ͨ��������δͨ����
   * 
   * @param cprojectids ��Ŀ��������
   * @param beginDateTime ץȡ��ʼ����
   * @param endDateTime ץȡ��������
   * @return ���ܵ�����������������
   * @throws BusinessException
   */
  public IFetchDataForPim[] queryBuyingReqForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException;
}
