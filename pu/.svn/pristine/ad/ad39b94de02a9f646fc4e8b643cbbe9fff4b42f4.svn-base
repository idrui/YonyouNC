package nc.test.pubimpl.pu.m21.ec;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.IFwLogin;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m21.ec.IOrderPushSaveForEC49;
import nc.pubitf.pu.m21.ec.PushSaveVO;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

public class OrderQueryForECImplTest extends AbstractTestCase {

  public void testOrderPushSaveForEC49() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("sc2", "a1234567", null);

    IOrderPubQuery query = NCLocator.getInstance().lookup(IOrderPubQuery.class);
    OrderVO[] vos = query.queryOrderVOByBids(new String[] {
      "10013110000000001YD3"
    });

    InvocationInfoProxy.getInstance().setGroupId("00013110000000000ABA");

    if (vos.length > 0) {
      OrderVO newVo = (OrderVO) vos[0].clone();
      newVo.getHVO().setPk_order(null);
      newVo.getHVO().setVbillcode(null);
      for (OrderItemVO itemVo : newVo.getBVO()) {
        itemVo.setPk_order_b(null);
      }

      IOrderPushSaveForEC49 service =
          NCLocator.getInstance().lookup(IOrderPushSaveForEC49.class);
      service.pushSave(new PushSaveVO(new OrderVO[] {
        newVo
      }, new OrderContext[] {
        new OrderContext()
      }));
    }
  }

  // public void testQueryLatestPrice() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // try {
  // OrderPriceData[] a =
  // orderQueryForEC.queryLatestPrice("00017110000000003UOF",
  // new String[] {
  // "1001711000000000013D"
  // }, new UFDate("2011-08-05"), new UFDate("2012-12-05"),
  // "1002Z0100000000001K1");
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQueryOnConfirmOrder() throws BusinessException {
  // IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
  // loginService.login("lxy", "1", null);
  //
  // OnConfirmOrderQueryCondVO condVo = new OnConfirmOrderQueryCondVO();
  // condVo.setSupplierCond(new QueryCondition(OrderHeaderVO.PK_SUPPLIER, "=",
  // new String[] {
  // "1002D3100000000002RR"
  // }));
  // condVo.setPurorgNameCond(new QueryCondition("name", "like", new String[] {
  // "%1022"
  // }));
  // condVo.setPlanArriveDate(new QueryCondition(OrderItemVO.DPLANARRVDATE,
  // "between", new String[] {
  // "2012-10-21 16:38:27", "2012-10-23 16:38:27"
  // }));
  //
  // IOrderQueryForEC query =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // OrderVO[] results = query.queryOnConfirmOrder(condVo);
  // if (results != null) {
  // for (OrderVO vo : results) {
  // System.out.println("OnConfirm:" + vo.getHVO().getPk_order());
  // }
  // }
  // }
  //
  // public void testQueryOrderExecDetailByHid() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // try {
  // orderQueryForEC.queryOrderExecDetailByHid(new String[] {
  // "10025610000000003IOD"
  // });
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQueryReceivePlansForSupplierMaintain() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // OrderReceivePlanQueryCondVO condVO = new OrderReceivePlanQueryCondVO();
  // // QueryCondition queryCondition1 =
  // // new QueryCondition("pk_order", "=", new String[] {
  // // "111"
  // // });
  // QueryCondition queryCondition2 =
  // new QueryCondition("pk_supplier", "=", new String[] {
  // "100556100000000004Y3"
  // });
  // // condVO.setOrderCodeCond(queryCondition1);
  // condVO.setSupplierCond(queryCondition2);
  // try {
  // OrderReceivePlanViewECVO[] ec =
  // orderQueryForEC.queryReceivePlansForSupplierMaintain(condVO);
  // ec = null;
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQueryReceivePlanVOsByBId() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // try {
  // OrderReceivePlanECVO[] ec =
  // orderQueryForEC.queryReceivePlanVOsByBId(new String[] {
  // "100382100000000036UV"
  // });
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQueryReleasedOverOrder() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // OrderReleasedOverQueryCondVO condVO = new OrderReleasedOverQueryCondVO();
  // // 传入供应商主键
  // QueryCondition supplyCond = new QueryCondition(null, null, new String[] {
  // "10019910000000001DSR"
  // });
  // condVO.setSupplierCond(supplyCond);
  //
  // Date orderBeginDate = new Date();
  // orderBeginDate.setYear(111);
  // orderBeginDate.setMonth(12);
  // orderBeginDate.setDate(12);
  // Date orderEndDate = new Date();
  // orderEndDate.setYear(112);
  // orderEndDate.setMonth(4);
  // orderEndDate.setDate(12);
  // // 如果订单日期不为空，可以作为查询条件
  // // if (null != orderBeginDate || null != orderEndDate) {
  // // QueryCondition orderDateCond =
  // // new QueryCondition(null, null, new String[] {
  // // DateTools.formatData(orderBeginDate),
  // // DateTools.formatData(orderEndDate)
  // // });
  // // condVO.setBilldateCond(orderDateCond);
  // // }
  // OrderReleasedOverViewVO[] a = null;
  // try {
  // a = orderQueryForEC.queryReleasedOverOrder(condVO);
  //
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQuerySupplyDetail() {
  // IOrderQueryForEC orderQueryForEC =
  // NCLocator.getInstance().lookup(IOrderQueryForEC.class);
  // SupplyDetailQueryVO condVO = new SupplyDetailQueryVO();
  // QueryCondition queryCondition2 =
  // new QueryCondition("pk_supplier", "=", new String[] {
  // "100556100000000004Y3"
  // });
  // condVO.setSupplierCond(queryCondition2);
  // try {
  // orderQueryForEC.querySupplyDetail(condVO);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
}
