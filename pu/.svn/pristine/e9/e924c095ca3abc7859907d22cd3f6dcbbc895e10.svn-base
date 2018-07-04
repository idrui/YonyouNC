package nc.impl.pu.dw70to20.query;

import java.util.ArrayList;
import java.util.List;

import nc.bs.trade.business.HYPubBO;
import nc.itf.pu.dw70to20.query.IDW70QueryMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.kycgjhxxjk.AggKycgjhxxjkHVO;
import nc.vo.lm.kycgjhxxjk.KycgjhxxjkHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class DW70QueryMaintainImpl implements IDW70QueryMaintain {

	@Override
	public AggKycgjhxxjkHVO[] queryUpForPrayBillDates(IQueryScheme queryScheme)
			throws BusinessException {
		AggKycgjhxxjkHVO[] bills = null;
		bills = this.queryAggCgagForSource(queryScheme);
		return bills;
	}

	private AggKycgjhxxjkHVO[] queryAggCgagForSource(IQueryScheme queryScheme)
			throws BusinessException {

		String sql = queryScheme.getWhereSQLOnly();
		List<AggKycgjhxxjkHVO> aggvos = new ArrayList<AggKycgjhxxjkHVO>();
		HYPubBO bo = new HYPubBO();
		SqlBuilder sb = new SqlBuilder();
		sb.append(sql);
		sb.append(" and nvl(dr,0)=0 and nvl(msgflag, 0) <> 2 ");
		KycgjhxxjkHVO[] hvos = (KycgjhxxjkHVO[]) bo.queryByCondition(
				KycgjhxxjkHVO.class, sb.toString());
		for (KycgjhxxjkHVO hvo : hvos) {
			AggKycgjhxxjkHVO aggvo = new AggKycgjhxxjkHVO();
			aggvo.setParent(hvo);
			aggvos.add(aggvo);
		}
		return aggvos.toArray(new AggKycgjhxxjkHVO[aggvos.size()]);

	}

}
