/**
 * 
 */
package nc.impl.pu;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.pu.IQueryForCgfaForward;
import nc.vo.pp.m28.entity.PriceAuditItemVO;

/**
 * @author wangzym
 * @version 2017年4月11日 上午9:40:26
 */
public class QueryForCgfaForwardImpl implements IQueryForCgfaForward {

	/**
	 * 
	 */
	public QueryForCgfaForwardImpl() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.itf.pu.IQueryForCgfaForward#hasForward()
	 */
	@Override
	public boolean hasForward(String hid) {
		// TODO 自动生成的方法存根

		VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
				PriceAuditItemVO.class);
		PriceAuditItemVO[] bvos = query.query("and dr='0' and csrcid ='" + hid + "'",
				null);
		if (bvos.length==0) {
			return false;
		} else {

			return true;
		}

	}

}
