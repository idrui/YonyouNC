package nc.pubitf.pu.m20.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>请购单新增保存
 * <li>根据请购单ID删除请购单
 * <li>根据请购单来源ID删除请购单
 * <li>请购单审批
 * <li>请购单弃审
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		请购单持久化服务
 * @since 6.5
 * @version 2015-10-29 下午9:29:54
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("请购单")
public interface IPrayBillMaintainAPI {
	
	/**
   * <b>到货单新增保存</b>
   * <li>对前台传入数据补全信息<li>  
   * 校验内容有：   
   * <ol>   
   * <li>字段非空校验</li>   
   * <li>字段值合法性校验</li>   
   * </ol>   
   * @param bills 请购单单据
   * @return PraybillVO[] 保存后的请购单单据
   * @throws BusinessException
   */
	PraybillVO[] insertBills(PraybillVO[] bills) throws BusinessException;
	
	/**
	 * <B>根据请购单IDs删除请购单</B>
	 * @param ids 请购单id数组
	 * @throws BusinessException
	 */
	void deleteBillsByIDs(String[] ids) throws BusinessException;

	/**
	 * <B>根据请购单来源ID删除请购单</B>
	 * @param srcids 请购单来源id数组
	 * @throws BusinessException
	 */
	void deleteBillsBySourceIDs(String[] srcids) throws BusinessException;
	
	/**
	 * <B>请购单审批</B>
	 * @param bills 请购单单据
	 * @return PraybillVO[] 审批后的请购单单据
	 * @throws BusinessException
	 */
	PraybillVO[] approve(PraybillVO[] bills) throws BusinessException;
	
  /**
   * <B>请购单弃审</B>
   * @param bills 审批后的请购单
   * @throws BusinessException
   * @return PraybillVO[] 弃审后的请购单
   */
  PraybillVO[] unApprove(PraybillVO[] bills)
      throws BusinessException;
}
