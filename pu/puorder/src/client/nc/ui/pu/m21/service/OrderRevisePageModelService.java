package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.itf.pu.pub.IPuMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderRevisePageModelService implements IBillPageQuery,
    IPaginationInitQuery {

  @Override
  public Integer getPageSize() {
    return Integer.valueOf(10);
  }

  @Override
  public PageQueryVO query(IQueryScheme scheme) {
    @SuppressWarnings("unchecked")
    IPuMaintainApp<OrderVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    PageQueryVO page = null;
    try {
      SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
      Integer[] values = new Integer[] {
        (Integer) POEnumBillStatus.APPROVE.value()
      };
      condition.appendHead(OrderHeaderVO.FORDERSTATUS, values);
      condition.appendHead(OrderHeaderVO.BFINALCLOSE, "N");
      page =
          service.queryPuApp(scheme, OrderVO.class, OrderHeaderVO.BISLATEST,
              OrderHeaderVO.FORDERSTATUS, OrderHeaderVO.VBILLCODE,
              POBillType.Order);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return page;
  }

  @Override
  public Object[] queryObjectByPks(String[] pks) throws BusinessException {
    @SuppressWarnings("unchecked")
    IPuMaintainApp<OrderVO> service =
        NCLocator.getInstance().lookup(IPuMaintainApp.class);
    IBill[] bills = null;
    try {
      bills = service.queryPuApp(pks, OrderVO.class);
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
