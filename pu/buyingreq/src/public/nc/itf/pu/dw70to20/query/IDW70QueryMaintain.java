package nc.itf.pu.dw70to20.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.kycgjhxxjk.AggKycgjhxxjkHVO;
import nc.vo.pub.BusinessException;

public interface IDW70QueryMaintain {
	AggKycgjhxxjkHVO[] queryUpForPrayBillDates(IQueryScheme queryScheme) throws BusinessException;

}
