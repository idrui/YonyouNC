package nc.pubitf.pu.m23.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>根据查询方案查询到货单完整VO
 * <li>根据查询方案和单据字段查询到货单整单信息（查询字段参数可为空）
 * <li>根据查询方案查询到货单明细信息
 * <li>根据查询方案和单据字段查询到货单明细信息
 * <li>根据到货单ID查询到货单
 * <li>根据到货单行ID查询到货单视图VO
 * <li>根据来源单据行ID查询到货单
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单查询服务API
 * @since 6.5
 * @version 2015-10-29 下午9:34:28
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("到货单")
public interface IArriveBillQueryAPI {

	/**
   * <B>根据查询方案（queryscheme）查询到货单整单信息</B>
   * <li>查询到货单整单信息</li>  
	 * <li>返回到货单所有VO字段</li>
	 * @param queryscheme 查询方案
	 * @return ArriveVO[] 到货单聚合VO
	 * @throws BusinessException
	 */
	ArriveVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询到货单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回到货单主子表VO</li>
   * <li>仅返回需要查询的字段信息</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return ArriveVO[] 到货单聚合VO
	 * @throws BusinessException
	 */
	ArriveVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）查询到货单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>返回到货单所有明细字段</li>
	 * @param queryscheme 查询方案
	 * @return ArriveViewVO[] 到货单视图VO
	 * @throws BusinessException
	 */
	ArriveViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询到货单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>需要查询的明细字段</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return ArriveViewVO[] 到货单视图VO
	 * @throws BusinessException
	 */
	ArriveViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>根据到货单主键查询到货单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回到货单所有字段信息</li>
   * @param ids 到货单ID
   * @return ArriveVO[] 到货单VO
   * @throws BusinessException
   */
	ArriveVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>根据到货单IDs和单据字段，查询到货单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回需要查询的字段信息</li>
	 * @param ids 到货单IDs
	 * @param fields 需要查询的字段
	 * @return ArriveVO[] 到货单VO
	 */
	ArriveVO[] queryVOByIDs(String[] ids,String[] fields) throws BusinessException;
	
	/**
	 * <B>根据到货单行IDs查询到货单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>返回到货单所有明细字段信息</li>
	 * @param bids 到货单bids
	 * @return ArriveViewVO[] 到货单视图VO
	 */
	ArriveViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;
	
	/**
	 * <B>根据到货单行IDs和单据字段查询到货单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>返回需要查询的明细字段信息</li>
	 * @param bids 到货单bids
	 * @param fields 需要查询的单据字段
	 * @return ArriveViewVO[] 到货单视图VO
	 */
	ArriveViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据到货单来源单据行IDs查询到货单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回到货单视图VO</li>
   * <li>返回到货单所有明细字段信息</li>
	 * @param srcbids 到货单来源bids
	 * @return ArriveViewVO[] 到货单视图VO
	 */
	ArriveViewVO[] queryViewVOBySourceBIDs(String[] sourcebids) 
			throws BusinessException;
}
