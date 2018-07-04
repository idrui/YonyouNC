package nc.pubitf.pu.m21.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>根据查询方案查询采购订单完整VO
 * <li>根据查询方案和单据字段查询采购订单整单信息（查询字段参数可为空）
 * <li>根据查询方案查询采购订单明细信息
 * <li>根据查询方案和单据字段查询采购订单明细信息
 * <li>根据采购订单ID查询采购订单
 * <li>根据采购订单行ID查询采购订单视图VO
 * <li>根据来源单据行ID查询采购订单
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		采购订单查询公共服务API
 * @since 6.5
 * @version 2015-10-29 下午9:21:53
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("采购订单")
public interface IOrderQueryAPI {

	/**
	 * <B>根据查询方案（queryscheme）查询采购订单整单信息</B>
	 * <li>查询单整单信息</li>  
	 * <li>返回到货单所有VO字段</li>
	 * @param queryscheme 查询方案
	 * @return OrderVO[] 采购订单聚合VO
	 * @throws BusinessException
	 */
	OrderVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询采购订单</B>
	 * <li>整单信息，遵循整单原则</li>
   * <li>返回采购订单主子表VO</li>
   * <li>仅返回需要查询的字段信息</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return OrderVO[] 采购订单聚合VO
	 * @throws BusinessException
	 */
	OrderVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）查询采购订单明细信息。</B>
	 * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回采购订单视图VO</li>
   * <li>返回采购订单所有明细字段</li>
	 * @param queryscheme 查询方案
	 * @return OrderViewVO[] 采购订单视图VO
	 * @throws BusinessException
	 */
	OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询采购订单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>需要查询的明细字段</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return OrderViewVO[] 采购订单视图VO
	 * @throws BusinessException
	 */
	OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>根据采购订单IDs查询采购订单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回采购订单所有字段信息</li>
   * @param ids 采购订单ID
   * @return OrderVO[] 采购订单VO
   * @throws BusinessException
   */
	OrderVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>根据采购订单IDs和单据字段，查询采购订单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回需要查询的字段信息</li>
	 * @param ids 采购订单IDs
	 * @param fields 单据字段或枚举字段（fields可为空）
	 * @return OrderVO[] 采购订单VO
	 */
	OrderVO[] queryVOByIDs(String[] ids,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据采购订单行IDs查询采购订单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回采购订单视图VO</li>
   * <li>返回采购订单所有明细字段信息</li>
	 * @param bids 采购订单bids
	 * @return OrderViewVO[] 采购订单视图VO
	 */
	OrderViewVO[] queryViewVOByBIDs(String[] bids) 
			throws BusinessException;
	
	/**
	 * <B>根据采购订单行IDs和单据字段查询采购订单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回采购订单视图VO</li>
   * <li>返回需要查询的明细字段信息</li>
	 * @param bids 采购订单bids
	 * @param fields 单据字段
	 * @return PraybillViewVO[] 采购订单视图VO
	 */
	OrderViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据采购订单来源单据行IDs查询采购订单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回采购订单视图VO</li>
   * <li>返回采购订单所有明细字段信息</li>
	 * @param srcbids 采购订单来源bids
	 * @return OrderViewVO[] 采购订单视图VO
	 */
	OrderViewVO[] queryViewVOBySourceBIDs(String[] srcbids) 
			throws BusinessException;
}
