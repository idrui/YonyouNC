package nc.impl.pub.ace;
import nc.bs.pu.newoaryxx.ace.bp.AceNewoaryxxBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.pu.newoaryxx.NewoaryxxHeadVO;
import nc.vo.uif2.LoginContext;

public abstract class AceNewoaryxxPubServiceImpl extends SmartServiceImpl {
	public NewoaryxxHeadVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceNewoaryxxBP().queryByQueryScheme(querySheme);
	}
}