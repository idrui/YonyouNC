package nc.itf.pu;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.vo.pub.BusinessException;

public interface INewoaspwhMaintain {

	public void delete(AggNewoaspwhaHeadVO[] vos) throws BusinessException;

	public AggNewoaspwhaHeadVO[] insert(AggNewoaspwhaHeadVO[] vos) throws BusinessException;

	public AggNewoaspwhaHeadVO[] update(AggNewoaspwhaHeadVO[] vos) throws BusinessException;

	public AggNewoaspwhaHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	//gm_prono 方案号，根据方案号查询OA审批结果中间表的审批意见
	public String queryOpinions(String gm_prono) throws BusinessException;
}