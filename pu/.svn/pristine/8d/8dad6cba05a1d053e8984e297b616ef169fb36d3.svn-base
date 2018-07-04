package nc.bs.pu.newoaryxx.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pu.newoaryxx.NewoaryxxHeadVO;

public class AceNewoaryxxBP {

	public NewoaryxxHeadVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<NewoaryxxHeadVO>(NewoaryxxHeadVO.class).query(querySheme,
				null);
	}
}
