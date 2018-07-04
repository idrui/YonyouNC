package nc.itf.pu.reference.uap.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import nc.itf.pu.reference.uap.org.parameter.IFinanceOrgForArrliabCenter;
import nc.itf.pu.reference.uap.org.parameter.IStoreOrganization;
import nc.vo.pub.BusinessException;
import nc.vo.util.remotecallcombination.Token;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货利润中心 查询服务公共接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.35
 * @since 6.35
 * @author guoyk
 * @time 2014年9月16日
 */
public class ArrliabCenterPubService {
	protected static class FinanceOrgForArrliabCenter implements
			IFinanceOrgForArrliabCenter {
		private String arrliabcenter;

		@Override
		public String getArrliabcenter() {
			return this.arrliabcenter;
		}

		public void setArrliabcenter(String arrliabcenter) {
			this.arrliabcenter = arrliabcenter;
		}

	}

	/**
	 * 方法功能描述：从仓库和收货库存组织中查找收货利润中心。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param params
	 *            仓库和收货库存组织
	 * @return 收货利润中心
	 *         <p>
	 * @since 6.35
	 * @author guoyk
	 * @throws BusinessException
	 * @time 2014年9月16日
	 */
	public Token register_findArrliabCenter(
			Collection<IStoreOrganization> params) throws BusinessException {
		// 准备参数
		String[] recvstorIDs = new String[params.size()];
		String[] storeOrganizationIds = new String[params.size()];
		int i = 0;
		for (IStoreOrganization param : params) {
			recvstorIDs[i] = param.getRecvstorageId();
			storeOrganizationIds[i] = param.getStoreOrganizationId();
			i++;
		}
		// TODO 调用后台服务
		return null;
	}

	/**
	 * 方法功能描述：从仓库和收货库存组织中查找收货利润中心。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param params
	 *            仓库和收货库存组织
	 * @return 收货利润中心
	 *         <p>
	 * @since 6.35
	 * @author guoyk
	 * @throws BusinessException
	 * @time 2014年9月16日
	 */
	public Collection<IFinanceOrgForArrliabCenter> findArrliabCenter(
			Collection<IStoreOrganization> params, Token t) {
		// 准备参数
		String[] recvstorIDs = new String[params.size()];
		String[] storeOrganizationIds = new String[params.size()];
		int i = 0;
		for (IStoreOrganization param : params) {
			recvstorIDs[i] = param.getRecvstorageId();
			storeOrganizationIds[i] = param.getStoreOrganizationId();
			i++;
		}
		Map<String, String> map = null;
		// TODO 获取后台数据
		return this.constructResult(map, params);
	}
	//将返回的结果拼装成IFinanceOrgForArrliabCenter对象（收货利润中心对象）
	private Collection<IFinanceOrgForArrliabCenter> constructResult(
			Map<String, String> map, Collection<IStoreOrganization> params) {
		//返回的结果为空
		if(map == null || map.size() == 0)
		{
			return null;
		}
		Collection<IFinanceOrgForArrliabCenter> arrliabCengters = new ArrayList<IFinanceOrgForArrliabCenter>(
				params.size());
		for(IStoreOrganization param : params)
		{
			
		}
		return null;
	}
	//根据收货仓库和收货库存组织获取返回结果中的key
	private String getKey(IStoreOrganization storeOrganization)
	{
		StringBuilder key = new StringBuilder();
		key.append(storeOrganization.getRecvstorageId());
		key.append("|");
		key.append(storeOrganization.getStoreOrganizationId());
		return key.toString();
	}
}
