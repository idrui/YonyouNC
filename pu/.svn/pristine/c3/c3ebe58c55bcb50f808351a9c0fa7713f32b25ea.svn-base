package nc.pubimpl.pu.m23.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.pu.m23.api.IArriveBillQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *		到货单查询服务实现
 * @scene
 * 		到货单查询
 * @param
 * 
 * @since 6.5
 * @version 2015-10-27 下午8:12:25
 * @author wandl
 */
public class ArriveBillQueryAPIImpl implements IArriveBillQueryAPI{

	@Override
	public ArriveVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMBillQuery<ArriveVO> queryTool = 
					new SCMBillQuery<ArriveVO>(ArriveVO.class);
			return queryTool.queryVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public ArriveVO[] queryVOByScheme(IQueryScheme queryscheme, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<ArriveVO> queryTool = 
					new SCMBillQuery<ArriveVO>(ArriveVO.class);
			return queryTool.queryVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public ArriveViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMViewQuery<ArriveViewVO> queryTool = 
					new SCMViewQuery<ArriveViewVO>(ArriveViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public ArriveViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
			String[] fields) throws BusinessException {
		try {
			SCMViewQuery<ArriveViewVO> queryTool = 
					new SCMViewQuery<ArriveViewVO>(ArriveViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public ArriveVO[] queryVOByIDs(String[] ids) 
			throws BusinessException {
		try {
			SCMBillQuery<ArriveVO> queryTool = 
					new SCMBillQuery<ArriveVO>(ArriveVO.class);
			return queryTool.queryVOByIDs(ids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public ArriveVO[] queryVOByIDs(String[] ids, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<ArriveVO> queryTool = 
					new SCMBillQuery<ArriveVO>(ArriveVO.class);
			return queryTool.queryVOByIDs(ids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public ArriveViewVO[] queryViewVOByBIDs(String[] bids) 
			throws BusinessException {
		try {
			SCMViewQuery<ArriveViewVO> queryTool = 
					new SCMViewQuery<ArriveViewVO>(ArriveViewVO.class);
			return queryTool.queryViewVOByBIDs(bids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public ArriveViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException {
		try {
			SCMViewQuery<ArriveViewVO> queryTool = 
					new SCMViewQuery<ArriveViewVO>(ArriveViewVO.class);
			return queryTool.queryViewVOByBIDs(bids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public ArriveViewVO[] queryViewVOBySourceBIDs(String[] srcbids) 
			throws BusinessException {
		try {
			SCMViewQuery<ArriveViewVO> queryTool = 
					new SCMViewQuery<ArriveViewVO>(ArriveViewVO.class);
			return queryTool.queryViewVOBySourceBIDs(ArriveItemVO.CSOURCEBID, srcbids, null);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}
}
