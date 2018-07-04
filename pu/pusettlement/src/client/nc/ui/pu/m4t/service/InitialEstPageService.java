/**
 * Project Name:pu
 * File Name:InitialEstPageService.java
 * Package Name:nc.ui.pu.m4t.service
 * Date:2015-11-17ÉÏÎç9:29:28
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
 */

package nc.ui.pu.m4t.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPuMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ClassName:InitialEstPageService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-11-17 ÉÏÎç9:29:28 <br/>
 * 
 * @author zhangshqb
 * @version
 * @since JDK 1.6
 * @see
 */
public class InitialEstPageService implements IBillPageQuery,
		IPaginationInitQuery {

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
    @SuppressWarnings("unchecked")
    IPuMaintainApp<InitialEstVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    IBill[] bills = null;
    try {
      bills = service.queryPuApp(pks, InitialEstVO.class);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return bills;
  }

	@Override
	public Integer getPageSize() {
		return Integer.valueOf(10);
	}

	@Override
	public PaginationQueryVO queryPaginationQueryVO(String condition,
			Integer billcount, Object... userobj) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageQueryVO query(IQueryScheme scheme) {

    @SuppressWarnings("unchecked")
    IPuMaintainApp<InitialEstVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    PageQueryVO page = null;
    try {
      page =
          service.queryPuApp(scheme, InitialEstVO.class, null,
              InitialEstHeaderVO.FBILLSTATUS, InitialEstHeaderVO.VBILLCODE,
              POBillType.InitEstimate);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return page;
  }

}
