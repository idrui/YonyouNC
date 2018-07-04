package nc.pubitf.pu.m4202.ia;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:IConsumeGatherQueryForIAClosingCheck
 * @Description:���Ļ��ܵ��Ĵ��������˽ӿ�
 * @author liyjp
 * @date 2014-11-24 ����3:34:04
 */
public interface IConsumeGatherQueryForIAClosingCheck {
  /**
   * @Title:queryUnsettledConsumeGather
   * @Description:�������δ�������Ļ��ܵ���ѯ�ӿ�
   * @param:@param queryParaVO ��ѯ����VO
   *               ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @return String[] ���Ļ��ܲ����ͷVO������[]��Pk_stockps_b��
   * @throws BusinessException ҵ���쳣
   */
  String[] queryUnsettledConsumeGather(QueryParaVO queryParaVO)
      throws BusinessException;
}
