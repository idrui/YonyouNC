package nc.ui.pu.dw70.billref.pu.m20;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.dw66to20.query.IDW66QueryMaintain;
import nc.itf.pu.dw70to20.query.IDW70QueryMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.kycgjhxxjk.AggKycgjhxxjkHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class QueryServiceFor20 implements IRefQueryService {

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {

		AggKycgjhxxjkHVO[] retbills = null;
		IDW70QueryMaintain service = NCLocator.getInstance().lookup(
				IDW70QueryMaintain.class);
		try {
			retbills = service.queryUpForPrayBillDates(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;

	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
}
