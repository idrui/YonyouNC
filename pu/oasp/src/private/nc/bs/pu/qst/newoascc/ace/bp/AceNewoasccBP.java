package nc.bs.pu.qst.newoascc.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;

public class AceNewoasccBP {

	public NewoasccHeadVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<NewoasccHeadVO>(NewoasccHeadVO.class).query(querySheme,
				null);
	}
}
