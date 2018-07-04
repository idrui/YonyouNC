/**
 * 
 */
package nc.pubimpl.pu.m20transtype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import nc.bs.framework.common.NCLocator;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.pu.m20transtype.IBuyrTransTypeQuery;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-24 上午9:18:22
 */
public class BuyrTransTypeQueryImpl implements IBuyrTransTypeQuery {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.pubitf.pu.m20transtype.IBuyrTransTypeQuery#queryAttrByTypes(java.lang.String, java.lang.String[], java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, BuyrTranTypeVO> queryAttrByTypes(String pk_group,
			String[] transTypes, String[] sAttriName) throws BusinessException {
		HashMap<String, BuyrTranTypeVO> hmap = new HashMap<String, BuyrTranTypeVO>();
		try {
			IMDPersistenceQueryService queryService = NCLocator.getInstance()
					.lookup(IMDPersistenceQueryService.class);
			SqlBuilder sqlBld = new SqlBuilder();

			sqlBld.append(BuyrTranTypeVO.VTRANTYPECODE, transTypes);
			sqlBld.append(" and ");
			sqlBld.append(BuyrTranTypeVO.PK_GROUP, pk_group);
			sqlBld.append(" and dr=0 ");
			Collection<BuyrTranTypeVO> colet = queryService
					.queryBillOfVOByCond(BuyrTranTypeVO.class,
							sqlBld.toString(), false);

			Iterator<BuyrTranTypeVO> iterVO = colet.iterator();
			BuyrTranTypeVO voTemp = null;
			while (iterVO.hasNext()) {
				voTemp = iterVO.next();
				hmap.put(voTemp.getVtrantypecode(), voTemp);
			}
		} catch (Exception e) {
			// 按规范包装异常
			ExceptionUtils.marsh(e);
		}
		return hmap;
	}

}
