package nc.ui.pu.m24.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPuMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class PircestlPageModelService implements IBillPageQuery,
    IPaginationInitQuery {

  @Override
  public Integer getPageSize() {
    return Integer.valueOf(10);
  }

  @Override
  public PageQueryVO query(IQueryScheme scheme) {

    @SuppressWarnings("unchecked")
    IPuMaintainApp<PricestlVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    PageQueryVO page = null;
    try {
      page =
          service.queryPuApp(scheme, PricestlVO.class, null,
              PricestlHeaderVO.FBILLSTATUS, PricestlHeaderVO.VBILLCODE,
              POBillType.Arrive);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return page;
  }

  @Override
  public Object[] queryObjectByPks(String[] pks) throws BusinessException {
    @SuppressWarnings("unchecked")
    IPuMaintainApp<PricestlVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    IBill[] bills = null;
    try {
      bills = service.queryPuApp(pks, PricestlVO.class);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return bills;
  }

  @Override
  public PaginationQueryVO queryPaginationQueryVO(String condition,
      Integer billcount, Object... userobj) throws BusinessException {
    return null;
  }

}
