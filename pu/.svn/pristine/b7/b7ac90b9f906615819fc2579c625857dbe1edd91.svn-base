package nc.pubitf.pu.m422x.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 *	 物资需求申请单持久化服务
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		物资需求申请单持久化服务
 * @since 6.5
 * @version 2015-10-29 下午9:33:16
 * @author luojw
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("物资需求申请单")
public interface IStoreReqAppMaintainAPI {
  
  /**
   * <b>物资需求申请单新增保存</b>
   * <li>对前台传入数据补全信息<li>  
   * 校验内容有：   
   * <ol>   
   * <li>字段非空校验</li>   
   * <li>字段值合法性校验</li>   
   * </ol> 
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] insertVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>物资需求申请单审批</B>
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] approveVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>物资需求申请单取消审批</B>
   * @param vos
   * @return StoreReqAppVO[] 
   * @throws BusinessException
   */
  StoreReqAppVO[] unapproveVO(StoreReqAppVO[] vos) throws BusinessException;
  
  /**
   * <B>根据物资需求申请单ID删除物资需求申请单</B>
   * @param ids
   * @return 
   * @throws BusinessException
   */
  void deleteVOByIDs(String[] ids) throws BusinessException;

  /**
   * <B>根据来源ID删除物资需求申请单</B>
   * @param sourceIDs
   * @return 
   * @throws BusinessException
   */
  void deleteVOBySourceIDs(String[] sourceIDs) throws BusinessException;
}
