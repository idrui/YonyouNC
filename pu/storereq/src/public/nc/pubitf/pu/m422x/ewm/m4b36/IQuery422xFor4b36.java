package nc.pubitf.pu.m422x.ewm.m4b36;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
/**
 * �����������뵥Ϊ�ʲ�ά�޹����ṩ�Ĳ�ѯ����
 * @author zhangshqb
 *
 */
public interface IQuery422xFor4b36 {
  /**
   * �������������������ʲ�ά�޹�����ͷID��ѯ���������������뵥��
   * <p>
   * <b>����˵��</b>
   * 
   * @param billids
   *          �ʲ�ά�޹�����ͷid����
   * @return �����������뵥
   * @throws BusinessException
   *           <p>
   */
  StoreReqAppVO[] queryBillBySource(String[] billids) throws BusinessException;

  /**
   * �������������������ʲ�ά�޹�������ID�����ѯ���������������뵥��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemids
   *          �ʲ�ά�޹�������id����
   * @return �����������뵥
   * @throws BusinessException
   */
  StoreReqAppVO[] queryBillBySourceBID(String[] itemids) throws BusinessException;

}
