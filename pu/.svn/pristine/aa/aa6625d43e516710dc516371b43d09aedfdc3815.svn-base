package nc.ui.pu.cgfa.billref;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.itf.pu.IQg20RefQueyService;

/*上游单据查询服务*/

public class QG20QueryServiceForTJ01 implements IRefQueryService {

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		PraybillVO[] retbills = null;
		IQg20RefQueyService service = NCLocator.getInstance().lookup(
				IQg20RefQueyService.class);
		try {
			retbills = service.queryM20ForTJ01(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

	// IRefQueryService不集成IQueryService时可以删除
	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		return null;
	}
}