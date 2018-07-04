package nc.pubitf.pu.m20.invp.inv9;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * �빺����Ϊ���ƻ��Ĺ������ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����06:18:00
 * @author duy
 */
public interface IBuyingreqQueryForInv9 {

  /**
   * ��ѯ�빺���Ƿ�������
   * @param pk_req
   * @return Map<String, UFBoolean>  Key:��ͷPK ;Value:�Ƿ���������trueΪ������
   * @throws BusinessException
   */
  Map<String, UFBoolean> getIsApprovedOfBuyingreq(String[] pk_praybill)
      throws BusinessException;
}
