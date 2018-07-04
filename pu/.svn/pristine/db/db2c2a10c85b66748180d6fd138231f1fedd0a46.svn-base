package nc.pubitf.pu.m20.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>根据查询方案查询请购单完整VO
 * <li>根据查询方案和单据字段查询请购单整单信息（查询字段参数可为空）
 * <li>根据查询方案查询请购单明细信息
 * <li>根据查询方案和单据字段查询请购单明细信息
 * <li>根据请购单ID查询请购单
 * <li>根据请购单行ID查询请购单视图VO
 * <li>根据来源单据行ID查询请购单
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		请购单查询服务API
 * @since 6.5
 * @version 2015-10-29 下午9:34:28
 * @author wandl
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("请购单")
public interface IPrayBillQueryAPI {

	/**
   * <B>根据查询方案（queryscheme）查询请购单整单信息</B>
   * <li>查询请购单整单信息</li>  
	 * <li>返回请购单所有VO字段</li>
	 * @param queryscheme 查询方案
	 * @return PraybillVO[] 请购单聚合VO
	 * @throws BusinessException
	 */
	PraybillVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询请购单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回请购单主子表VO</li>
   * <li>仅返回需要查询的字段信息</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return PraybillVO[] 请购单聚合VO
	 * @throws BusinessException
	 */
	PraybillVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）查询请购单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回请购单视图VO</li>
   * <li>返回请购单所有明细字段<li>
	 * @param queryscheme 查询方案
	 * @return PraybillViewVO[] 请购单视图VO
	 * @throws BusinessException
	 */
	PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询请购单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回请购单视图VO</li>
   * <li>需要查询的明细字段</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return PraybillViewVO[] 请购单视图VO
	 * @throws BusinessException
	 */
	PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>根据请购单主键查询请购单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回请购单所有字段信息</li>
   * @param ids 请购单ID
   * @return PraybillVO[] 请购单VO
   * @throws BusinessException
   */
	PraybillVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>根据请购单IDs和单据字段，查询请购单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回需要查询的字段信息</li>
	 * @param ids 请购单IDs
	 * @param fields 需要查询的字段
	 * @return PraybillVO[] 请购单VO
	 */
	PraybillVO[] queryVOByIDs(String[] ids,String[] fields) throws BusinessException;
	
	/**
	 * <B>根据请购单行IDs查询请购单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回请购单视图VO</li>
   * <li>返回请购单所有明细字段信息</li>
	 * @param bids 请购单bids
	 * @return PraybillViewVO[] 请购单视图VO
	 */
	PraybillViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;
	
	/**
	 * <B>根据请购单行IDs和单据字段查询请购单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回请购单视图VO</li>
   * <li>返回需要查询的明细字段信息</li>
	 * @param bids 请购单bids
	 * @param fields 需要查询的单据字段
	 * @return PraybillViewVO[] 请购单视图VO
	 */
	PraybillViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据请购单来源单据行IDs查询请购单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回请购单视图VO</li>
   * <li>返回请购单所有明细字段信息</li>
	 * @param srcbids 请购单来源bids
	 * @return PraybillViewVO[] 请购单视图VO
	 */
	PraybillViewVO[] queryViewVOBySourceBIDs(String[] sourcebids) 
			throws BusinessException;
}
