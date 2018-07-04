package nc.impl.pu;

import nc.impl.pub.ace.AceCgfaPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.cgfa.AggCgfa;
import nc.itf.pu.ICgfaMaintain;
import nc.vo.pub.BusinessException;

public class CgfaMaintainImpl extends AceCgfaPubServiceImpl
		implements ICgfaMaintain {

	@Override
	public void delete(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] insert(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] update(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggCgfa[] save(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] unsave(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] approve(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCgfa[] unapprove(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
