package nc.impl.pu;

import nc.impl.pub.ace.AceNewoabasePubServiceImpl;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;
import nc.itf.pu.INewoabaseMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class NewoabaseMaintainImpl extends AceNewoabasePubServiceImpl implements
		INewoabaseMaintain {

	@Override
	public void delete(AggNewoabaseaHeadVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggNewoabaseaHeadVO[] insert(AggNewoabaseaHeadVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggNewoabaseaHeadVO[] update(AggNewoabaseaHeadVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggNewoabaseaHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
