package nc.pubitf.pu.m422x.invp.inv9;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * �����������뵥��Ϊ���ƻ���������ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����06:18:00
 * @author duy
 */
public interface IStorereqQueryForInv9 {

  /**
   * ��������ƽ���ѯ�����������뵥
   * @param conMap<Map<String key,UFDouble value>>
   * @return StoreReqAppViewVO[]
   */
  StoreReqAppViewVO[] getReq(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ��ѯ�����������뵥Ԥ�Ƴ�
   * @param pk_req
   * @return Map<String, UFDouble>  
   *         Key:�����֯+����
   *         Value: ������������Ԥ�Ƴ�������ƽ������֯������ƽ�⡢
   *         δ�رյ������������뵥�ģ����������ۼ��������������ۼƳ����������ϼƣ�
   * @throws BusinessException
   */
  Map<String, UFDouble> getPlanOut(String[] pk_StoreOrgs, String[] pk_materials)
      throws BusinessException;

  /**
   * ��ѯ�����������뵥�Ƿ�������
   * @param pk_req
   * @return Map<String, UFBoolean>  Key:��ͷPK ;Value:�Ƿ���������trueΪ������
   * @throws BusinessException
   */
  Map<String, UFBoolean> getIsApproved(String[] pk_req)
      throws BusinessException;

}
