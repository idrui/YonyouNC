/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * 
 * @Title: AceCgfaMaintainProxy.java 
 * @Prject: pu
 * @Package: nc.ui.pu.cgfarevise.ace.serviceproxy 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年1月24日 下午3:23:30 
 * @version: V6.5   
 */
package nc.ui.pu.cgfarevise.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.cgfarevise.ICgfaReviseService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @ClassName: AceCgfaMaintainProxy
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年1月24日 下午3:23:30
 */
public class AceCgfaMaintainProxy implements IQueryService {

	/**
	 * @Title:AceCgfaMaintainProxy
	 * @Description:TODO
	 */
	public AceCgfaMaintainProxy() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: queryByQueryScheme
	 * 
	 * @Description: TODO
	 * 
	 * @param queryScheme
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * nc.ui.pubapp.uif2app.query2.model.IQueryService#queryByQueryScheme(nc
	 * .ui.querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggCgfa[] bills = null;
		ICgfaReviseService service = NCLocator.getInstance().lookup(
				ICgfaReviseService.class);
		try {
			bills = service.queryCgfaForReviseApp(queryScheme);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return bills;
	}

}
