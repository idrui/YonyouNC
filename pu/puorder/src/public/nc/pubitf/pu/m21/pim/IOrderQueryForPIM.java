package nc.pubitf.pu.m21.pim;

import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

/**
 * Ϊ��Ŀ�����ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2012-3-5 ����01:30:40
 * @author lixyp
 */
public interface IOrderQueryForPIM {

  /**
   * Ϊ��Ŀ�ṩ�Ķ������¼�ѯ�۷���
   * 
   * @param param
   * @return
   * @throws BusinessException
   */
  public OrderPriceData[] queryLatestPrice(QueryLatestPriceParam param)
      throws BusinessException;

  /**
   * Ϊ��Ŀ���������ṩ�Ĳ�ѯ����
   * 
   * @param pvo ��ѯ����vo
   * @return ���������Ķ����ۺ�vo
   */
  public OrderVO[] queryOrderForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException;

  /**
   * �������ʼ���������ʱ, ������Ŀ�͡��������ڡ������ڼ��ѯ�ɹ���������̬�������С�����ͨ��������δͨ����������
   * 
   * @param cprojectids ��Ŀ��������
   * @param beginDateTime ץȡ��ʼ����
   * @param endDateTime ץȡ��������
   * @return ���ܵ�����������������
   * @throws BusinessException
   */
  public IFetchDataForPim[] queryOrderForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException;
}
