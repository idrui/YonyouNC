package nc.pubitf.pu.m23.qc;

import java.util.Set;

import nc.vo.pub.BusinessException;

/**
 * �ṩ���ʼ�Ĳ�ѯ����
 * 
 * @since 6.3
 * @version 2013-3-8 ����12:18:17
 * @author fanly3
 */
public interface IQuery23ForC001 {
  /***
   * 
   * @param bids ����������ids
   * @return ���α���ĵ���������ids
   * @throws BusinessException
   */
  Set<String> queryBidsOfSecondCheck(String[] bids) throws BusinessException;
}
