package nc.pubitf.pu.m422x.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.BusinessException;

/**
 * @description
 * <ul>
 * <li>根据查询方案查询物资需求申请单完整VO
 * <li>根据查询方案和单据字段查询物资需求申请单整单信息（查询字段参数可为空）
 * <li>根据查询方案查询物资需求申请单明细信息
 * <li>根据查询方案和单据字段查询物资需求申请单明细信息
 * <li>根据物资需求申请单ID查询物资需求申请单
 * <li>根据物资需求申请单行ID查询物资需求申请单视图VO
 * <li>根据来源单据行ID查询物资需求申请单
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		物资需求申请单查询服务API
 * @since 6.5
 * @version 2015-10-29 下午9:34:28
 * @author luojw
 */
@OpenAPI(value = OpenLevel.SHARED)
@Component("物资需求申请单")
public interface IStoreReqAppQueryAPI {

	/**
   * <B>根据查询方案（queryscheme）查询物资需求申请单整单信息</B>
   * <li>查询物资需求申请单整单信息</li>  
	 * <li>返回物资需求申请单所有VO字段</li>
	 * @param queryscheme 查询方案
	 * @return StoreReqAppVO[] 物资需求申请单聚合VO
	 * @throws BusinessException
	 */
	StoreReqAppVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询物资需求申请单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回物资需求申请单主子表VO</li>
   * <li>仅返回需要查询的字段信息</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return StoreReqAppVO[] 物资需求申请单聚合VO
	 * @throws BusinessException
	 */
	StoreReqAppVO[] queryVOByScheme(IQueryScheme queryscheme,String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）查询物资需求申请单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回物资需求申请单视图VO</li>
   * <li>返回物资需求申请单所有明细字段</li>
	 * @param queryscheme 查询方案
	 * @return StoreReqAppViewVO[] 物资需求申请单视图VO
	 * @throws BusinessException
	 */
	StoreReqAppViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException;
	
	/**
	 * <B>根据查询方案（queryscheme）和单据字段查询条件查询物资需求申请单明细信息</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回物资需求申请单视图VO</li>
   * <li>需要查询的明细字段</li>
	 * @param queryscheme 查询方案
	 * @param fileds 单据字段
	 * @return StoreReqAppViewVO[] 物资需求申请单视图VO
	 * @throws BusinessException
	 */
	StoreReqAppViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,String[] fileds) 
			throws BusinessException;
	
	/**
   * <B>根据物资需求申请单主键查询物资需求申请单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回物资需求申请单所有字段信息</li>
   * @param ids 物资需求申请单ID
   * @return StoreReqAppVO[] 物资需求申请单VO
   * @throws BusinessException
   */
	StoreReqAppVO[] queryVOByIDs(String[] ids) throws BusinessException;
	
	/**
	 * <B>根据物资需求申请单IDs和单据字段，查询物资需求申请单</B>
   * <li>整单信息，遵循整单原则</li>
   * <li>返回主子表VO</li>
   * <li>返回需要查询的字段信息</li>
	 * @param ids 物资需求申请单IDs
	 * @param fields 需要查询的字段
	 * @return StoreReqAppVO[] 物资需求申请单VO
	 */
	StoreReqAppVO[] queryVOByIDs(String[] ids,String[] fields) throws BusinessException;
	
	/**
	 * <B>根据物资需求申请单行IDs查询物资需求申请单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回物资需求申请单视图VO</li>
   * <li>返回物资需求申请单所有明细字段信息</li>
	 * @param bids 物资需求申请单bids
	 * @return StoreReqAppViewVO[] 物资需求申请单视图VO
	 */
	StoreReqAppViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;
	
	/**
	 * <B>根据物资需求申请单行IDs和单据字段查询物资需求申请单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回物资需求申请单视图VO</li>
   * <li>返回需要查询的明细字段信息</li>
	 * @param bids 物资需求申请单bids
	 * @param fields 需要查询的单据字段
	 * @return StoreReqAppViewVO[] 物资需求申请单视图VO
	 */
	StoreReqAppViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException;
	
	/**
	 * <B>根据物资需求申请单来源单据行IDs查询物资需求申请单</B>
   * <li>不遵循整单原则，按照查询条件查询出明细数据</li>
   * <li>返回物资需求申请单视图VO</li>
   * <li>返回物资需求申请单所有明细字段信息</li>
	 * @param srcbids 物资需求申请单来源bids
	 * @return StoreReqAppViewVO[] 物资需求申请单视图VO
	 */
	StoreReqAppViewVO[] queryViewVOBySourceBIDs(String[] sourcebids) 
			throws BusinessException;
}
