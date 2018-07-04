package nc.test.pubimpl.pu.m25.ec;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.IFwLogin;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubift.pu.m25.ec.IInvoiceQueryForEC;
import nc.pubift.pu.m25.ec.InvoiceItemECVO;
import nc.pubift.pu.m25.ec.InvoiceNumSummaryECVO;
import nc.pubift.pu.m25.ec.InvoiceOrderViewECVO;
import nc.pubift.pu.m25.ec.InvoiceSupplyDetailQueryCondVO;
import nc.pubimpl.pu.m25.ec.InvoiceQueryForECImpl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class InvoiceQueryForECImplTest extends AbstractTestCase {

  public void testQueryInvoiceByOrderBid() {
    IInvoiceQueryForEC impl =
        NCLocator.getInstance().lookup(IInvoiceQueryForEC.class);
    try {
      InvoiceOrderViewECVO[] a = impl.queryInvoiceByOrderBid(new String[] {
        "1002Z810000000000D6O"
      });
      System.out.println(a[0]);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void testQueryInvoiceByOrderHid() {
    InvoiceQueryForECImpl impl = new InvoiceQueryForECImpl();
    try {
      InvoiceOrderViewECVO[] a = impl.queryInvoiceByOrderHid(new String[] {
        "111", "222"
      });
      System.out.println(a[0]);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  public void testQueryInvoiceItemVOByHid() {
    InvoiceQueryForECImpl impl = new InvoiceQueryForECImpl();
    try {
      InvoiceItemECVO[] a = impl.queryInvoiceItemVOByHid(new String[] {
        "111", "222"
      });
      System.out.println(a[0]);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void testQueryNumSummaryByCond() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    InvoiceSupplyDetailQueryCondVO cond = new InvoiceSupplyDetailQueryCondVO();
    QueryCondition queryCondition1 =
        new QueryCondition("pk_supplier", "=", new String[] {
          "1008A510000000001DI8"
        });
    cond.setSupplierCond(queryCondition1);

    IInvoiceQueryForEC query =
        NCLocator.getInstance().lookup(IInvoiceQueryForEC.class);
    InvoiceNumSummaryECVO[] vos = query.queryNumSummaryByCond(cond);
    for (InvoiceNumSummaryECVO vo : vos) {
      System.out.println("pk_order_b:" + vo.getPk_order_b() + ",nnum:"
          + vo.getNnum() + ",norigtaxmny:" + vo.getNorigtaxmny()
          + ",pk_srcmaterial:" + vo.getPk_srcmaterial());
    }
  }

  public void testQueryNumSummaryByOrderBids() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    IInvoiceQueryForEC query =
        NCLocator.getInstance().lookup(IInvoiceQueryForEC.class);
    InvoiceNumSummaryECVO[] vos =
        query.queryNumSummaryByOrderBids(new String[] {
          "1008A510000000001E83", "1008A510000000001F3D",
          "1008A510000000001VEU", "1008Z810000000000H0D"
        });

    for (InvoiceNumSummaryECVO vo : vos) {
      System.out.println("pk_order_b:" + vo.getPk_order_b() + ",nnum:"
          + vo.getNnum() + ",norigtaxmny:" + vo.getNorigtaxmny()
          + ",pk_srcmaterial:" + vo.getPk_srcmaterial());
    }
  }
}
