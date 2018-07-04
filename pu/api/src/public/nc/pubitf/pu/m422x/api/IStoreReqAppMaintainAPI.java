package nc.pubitf.pu.m422x.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 *	 �����������뵥�־û�����
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		�����������뵥�־û�����
 * @since 6.5
 * @version 2015-10-29 ����9:33:16
 * @author luojw
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("�����������뵥")
public interface IStoreReqAppMaintainAPI {
  
  /**
   * <b>�����������뵥��������</b>
   * <li>��ǰ̨�������ݲ�ȫ��Ϣ<li>  
   * У�������У�   
   * <ol>   
   * <li>�ֶηǿ�У��</li>   
   * <li>�ֶ�ֵ�Ϸ���У��</li>   
   * </ol> 
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] insertVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>�����������뵥����</B>
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] approveVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>�����������뵥ȡ������</B>
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] unapproveVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>���������������뵥IDɾ�������������뵥</B>
   * @param ids
   * @return 
   * @throws BusinessException
   */
  void deleteVOByIDs(String[] ids) throws BusinessException;

  /**
   * <B>������ԴIDɾ�������������뵥</B>
   * @param sourceIDs
   * @return 
   * @throws BusinessException
   */
  void deleteVOBySourceIDs(String[] sourceIDs) throws BusinessException;
}
