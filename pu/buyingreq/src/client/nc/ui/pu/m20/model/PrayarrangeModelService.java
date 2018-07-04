/**
 * 
 */
package nc.ui.pu.m20.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPrayarrange;
import nc.itf.pu.m20.IPrayarrangeQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IBatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-11 下午6:37:13
 */
public class PrayarrangeModelService implements IBatchAppModelService, IQueryService{

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.uif2.model.IBatchAppModelService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext)
	 */
	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.uif2.model.IBatchAppModelService#batchSave(nc.vo.bd.meta.BatchOperateVO)
	 */
	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws Exception {
		IPrayarrange service = NCLocator.getInstance().lookup(IPrayarrange.class);
	    return service.batchSave(batchVO);
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.pubapp.uif2app.query2.model.IQueryService#queryByQueryScheme(nc.ui.querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IPrayarrangeQuery service =
		        NCLocator.getInstance().lookup(IPrayarrangeQuery.class);
		return service.queryByQueryScheme(queryScheme);
	}

}
