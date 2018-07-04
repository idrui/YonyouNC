package nc.itf.pu;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pub.BusinessException;

public interface ICgfaMaintain {

	public void delete(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] insert(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] update(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggCgfa[] save(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] unsave(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] approve(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;

	public AggCgfa[] unapprove(AggCgfa[] clientFullVOs,
			AggCgfa[] originBills) throws BusinessException;
}
