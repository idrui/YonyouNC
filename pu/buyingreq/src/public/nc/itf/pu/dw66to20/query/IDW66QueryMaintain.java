package nc.itf.pu.dw66to20.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.erpcgjhjk.AggErpcgjhjkHVO;
import nc.vo.pub.BusinessException;

public interface IDW66QueryMaintain {
	AggErpcgjhjkHVO[] queryUpForPrayBillDates(IQueryScheme queryScheme)
			throws BusinessException;

	// 王梓懿 2018-05-23 增加拒绝原因参数
	public void Changestsreg(String pk, String ret);

	// 王梓懿 2018-05-22 增加取消拒绝的方法
	void UnRefuse(String[] bpks);

}
