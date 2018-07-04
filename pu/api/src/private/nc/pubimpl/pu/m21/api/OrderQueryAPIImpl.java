package nc.pubimpl.pu.m21.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.pu.m21.api.IOrderQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/**
 * 
 * @description
 * <b>采购订单查询公共服务API实现</b>
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
 *
 * @since 6.5
 * @version 2015-10-29 下午9:19:06
 * @author wandl
 */
public class OrderQueryAPIImpl implements IOrderQueryAPI{

	@Override
	public OrderVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMBillQuery<OrderVO> queryTool = 
					new SCMBillQuery<OrderVO>(OrderVO.class);
			return queryTool.queryVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public OrderVO[] queryVOByScheme(IQueryScheme queryscheme, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<OrderVO> queryTool = 
					new SCMBillQuery<OrderVO>(OrderVO.class);
			return queryTool.queryVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMViewQuery<OrderViewVO> queryTool = 
					new SCMViewQuery<OrderViewVO>(OrderViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public OrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
			String[] fields) throws BusinessException {
		try {
			SCMViewQuery<OrderViewVO> queryTool = 
					new SCMViewQuery<OrderViewVO>(OrderViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public OrderVO[] queryVOByIDs(String[] ids) 
			throws BusinessException {
		try {
			SCMBillQuery<OrderVO> queryTool = 
					new SCMBillQuery<OrderVO>(OrderVO.class);
			return queryTool.queryVOByIDs(ids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public OrderVO[] queryVOByIDs(String[] ids, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<OrderVO> queryTool = 
					new SCMBillQuery<OrderVO>(OrderVO.class);
			return queryTool.queryVOByIDs(ids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public OrderViewVO[] queryViewVOByBIDs(String[] bids) 
			throws BusinessException {
		try {
			SCMViewQuery<OrderViewVO> queryTool = 
					new SCMViewQuery<OrderViewVO>(OrderViewVO.class);
			return queryTool.queryViewVOByBIDs(bids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public OrderViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException {
		try {
			SCMViewQuery<OrderViewVO> queryTool = 
					new SCMViewQuery<OrderViewVO>(OrderViewVO.class);
			return queryTool.queryViewVOByBIDs(bids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public OrderViewVO[] queryViewVOBySourceBIDs(String[] srcbids) 
			throws BusinessException {
		try {
			SCMViewQuery<OrderViewVO> queryTool = 
					new SCMViewQuery<OrderViewVO>(OrderViewVO.class);
			return queryTool.queryViewVOBySourceBIDs(OrderItemVO.CSOURCEBID, srcbids, null);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}
}
