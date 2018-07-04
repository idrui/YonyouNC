package nc.pubitf.pu.m23.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
/**
 * @description
 * <ul>
 * <li>到货单新增保存
 * <li>根据到货单ID删除到货单
 * <li>根据到货单来源ID删除到货单
 * <li>到货单审批
 * <li>到货单弃审
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单持久化服务
 * @since 6.5
 * @version 2015-10-29 下午9:35:08
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("到货单")
public interface IArriveBillMaintainAPI {
	/**
   * <b>到货单新增保存</b>
   * <li>对前台传入数据补全信息<li>  
   * 校验内容有：   
   * <ol>   
   * <li>字段非空校验</li>   
   * <li>字段值合法性校验</li>   
   * </ol>   
   * 补全内容有：   
   * <ol>   
   * <li>来源于采购订单的数据补全</li>  
   * <li>补全到货单应到数量等信息</li>
   * <li> 
   * </ol> 
   * @param bills 到货单单据
   * @return ArriveVO[] 保存后的到货单单据
   * @throws BusinessException
   */
	ArriveVO[] insertBills(ArriveVO[] bills) throws BusinessException;
	
	/**
	 * <B>根据到货单IDs删除到货单</B>
	 * @param ids 到货单id数组
	 * @throws BusinessException
	 */
	void deleteBillsByIDs(String[] ids) throws BusinessException;

	/**
	 * <B>根据到货单来源ID删除到货单</B>
	 * @param srcids 到货单来源id数组
	 * @throws BusinessException
	 * <p>
	 * @since 6.5
   * @author wandl
   * @time 2015-10-22 16:35:07
	 */
	void deleteBillsBySourceIDs(String[] sourceids) throws BusinessException;
	
	/**
	 * <B>到货单审批<B/>
	 * @param bills 到货单单据
	 * @return ArriveVO[] 审批后的到货单单据
	 * @throws BusinessException
	 */
	ArriveVO[] approve(ArriveVO[] bills) throws BusinessException;
	
  /**
   * <B>到货单弃审</B>
   * @param bills 审批后的到货单
   * @throws BusinessException
   * @return ArriveVO[] 弃审后的到货单
   */
  ArriveVO[] unApprove(ArriveVO[] bills)
      throws BusinessException;
}
