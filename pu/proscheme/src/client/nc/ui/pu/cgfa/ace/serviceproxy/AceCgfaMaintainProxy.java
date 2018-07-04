package nc.ui.pu.cgfa.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.ICgfaMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceCgfaMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ICgfaMaintain query = NCLocator.getInstance().lookup(
				ICgfaMaintain.class);
		return query.query(queryScheme);
	}

}