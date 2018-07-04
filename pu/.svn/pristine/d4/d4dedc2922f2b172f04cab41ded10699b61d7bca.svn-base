package nc.impl.pub.ace;
import nc.bs.pu.qst.newoascc.ace.bp.AceNewoasccBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;
import nc.vo.uif2.LoginContext;

public abstract class AceNewoasccPubServiceImpl extends SmartServiceImpl {
	public NewoasccHeadVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceNewoasccBP().queryByQueryScheme(querySheme);
	}
}