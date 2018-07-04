package nc.pubitf.pu.m21.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>采购订单新增保存
 * <li>根据采购订单ID删除采购订单
 * <li>根据采购订单来源ID删除采购订单
 * <li>采购订单审批
 * <li>采购订单弃审
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *	      采购订单持久化服务
 * @since 6.5
 * @version 2015-10-27 下午7:22:43
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("采购订单")
public interface IOrderMaintainAPI {
	
	/**
	 * <b>采购订单新增保存</b> <li>对前台传入数据补全信息<li>
	 * 校验内容有：
	 * <ol>
	 * <li>字段非空校验</li>
	 * <li>字段值合法性校验</li>
	 * </ol>
	 * 补全内容有：
	 * <ol>
	 * <li>订单的单据号，行号补全</li>
	 * <li>订单单价数量等必输项补全</li>
	 * <li>
	 * </ol>
	 * 
	 * @param bills
	 *          采购订单单据
	 * @return OrderVO[] 保存后的采购订单单据
	 * @throws BusinessException
	 */
	OrderVO[] insertBills(OrderVO[] bills) throws BusinessException;

	/**
	 * <B>方法功能描述：根据采购订单ID删除采购订单</B>
	 * 
	 * @param ids
	 *          采购订单id数组
	 * @throws BusinessException
	 */
	void deleteBillsByIDs(String[] ids) throws BusinessException;

	/**
	 * <B>方法功能描述：根据采购订单来源ID删除采购订单</B>
	 * 
	 * @param sourceids
	 *          采购订单来源id数组
	 * @throws BusinessException
	 */
	void deleteBillsBySourceIDs(String[] sourceids) throws BusinessException;

	/**
	 * <B>方法功能描述：采购订单审批<B/>
	 * 
	 * @param bills
	 *          采购订单单据
	 * @return OrderVO[] 审批后的采购订单单据
	 * @throws BusinessException
	 */
	OrderVO[] approve(OrderVO[] bills) throws BusinessException;

	/**
	 * <B>采购订单弃审</B>
	 * 
	 * @param bills
	 *          审批后的采购订单
	 * @throws BusinessException
	 * @return OrderVO[] 弃审后的采购订单
	 */
	OrderVO[] unApprove(OrderVO[] bills) throws BusinessException;
}
