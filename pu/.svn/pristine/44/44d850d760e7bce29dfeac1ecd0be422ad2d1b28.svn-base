package nc.pubimpl.pu.m20.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.pu.m20.api.IPrayBillQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据查询方案查询请购单完整VO
 * <li>根据查询方案和单据字段查询请购单整单信息（查询字段参数可为空）
 * <li>根据查询方案查询请购单明细信息
 * <li>根据查询方案和单据字段查询请购单明细信息
 * <li>根据请购单ID查询请购单
 * <li>根据请购单行ID查询请购单视图VO
 * <li>根据来源单据行ID查询请购单
 * </ul>
 * <p>
 * <p>
 *
 * @since 6.5
 * @version 2015-10-13 上午9:47:15
 * @author wandl
 */
public class PrayBillQueryAPIImpl implements IPrayBillQueryAPI{

	@Override
	public PraybillVO[] queryVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMBillQuery<PraybillVO> queryTool = 
					new SCMBillQuery<PraybillVO>(PraybillVO.class);
			return queryTool.queryVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public PraybillVO[] queryVOByScheme(IQueryScheme queryscheme, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<PraybillVO> queryTool = 
					new SCMBillQuery<PraybillVO>(PraybillVO.class);
			return queryTool.queryVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme) 
			throws BusinessException {
		try {
			SCMViewQuery<PraybillViewVO> queryTool = 
					new SCMViewQuery<PraybillViewVO>(PraybillViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public PraybillViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
			String[] fields) throws BusinessException {
		try {
			SCMViewQuery<PraybillViewVO> queryTool = 
					new SCMViewQuery<PraybillViewVO>(PraybillViewVO.class);
			return queryTool.queryViewVOByScheme(queryscheme, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public PraybillVO[] queryVOByIDs(String[] ids) 
			throws BusinessException {
		try {
			SCMBillQuery<PraybillVO> queryTool = 
					new SCMBillQuery<PraybillVO>(PraybillVO.class);
			return queryTool.queryVOByIDs(ids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public PraybillVO[] queryVOByIDs(String[] ids, String[] fields) 
			throws BusinessException {
		try {
			SCMBillQuery<PraybillVO> queryTool = 
					new SCMBillQuery<PraybillVO>(PraybillVO.class);
			return queryTool.queryVOByIDs(ids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}	
	}

	@Override
	public PraybillViewVO[] queryViewVOByBIDs(String[] bids) 
			throws BusinessException {
		try {
			SCMViewQuery<PraybillViewVO> queryTool = 
					new SCMViewQuery<PraybillViewVO>(PraybillViewVO.class);
			return queryTool.queryViewVOByBIDs(bids);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public PraybillViewVO[] queryViewVOByBIDs(String[] bids, String[] fields) 
			throws BusinessException {
		try {
			SCMViewQuery<PraybillViewVO> queryTool = 
					new SCMViewQuery<PraybillViewVO>(PraybillViewVO.class);
			return queryTool.queryViewVOByBIDs(bids, fields);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}

	@Override
	public PraybillViewVO[] queryViewVOBySourceBIDs(String[] srcbids) 
			throws BusinessException {
		try {
			SCMViewQuery<PraybillViewVO> queryTool = 
					new SCMViewQuery<PraybillViewVO>(PraybillViewVO.class);
			return queryTool.queryViewVOBySourceBIDs(PraybillItemVO.CSOURCEBID, srcbids, null);
		}
		catch (Exception e){
			ExceptionUtils.marsh(e);
			return null;
		}
	}
}
