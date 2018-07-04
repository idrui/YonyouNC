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
 * <b>�ɹ�������ѯ��������APIʵ��</b>
 * <ul>
 * <li>���ݲ�ѯ������ѯ�ɹ���������VO
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�ɹ�����������Ϣ����ѯ�ֶβ�����Ϊ�գ�
 * <li>���ݲ�ѯ������ѯ�ɹ�������ϸ��Ϣ
 * <li>���ݲ�ѯ�����͵����ֶβ�ѯ�ɹ�������ϸ��Ϣ
 * <li>���ݲɹ�����ID��ѯ�ɹ�����
 * <li>���ݲɹ�������ID��ѯ�ɹ�������ͼVO
 * <li>������Դ������ID��ѯ�ɹ�����
 * </ul>
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-29 ����9:19:06
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
