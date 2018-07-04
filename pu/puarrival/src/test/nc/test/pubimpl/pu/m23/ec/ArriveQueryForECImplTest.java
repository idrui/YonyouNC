package nc.test.pubimpl.pu.m23.ec;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.IFwLogin;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m23.ec.BackArriveQueryCondVO;
import nc.pubitf.pu.m23.ec.IArriveQueryForEC;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class ArriveQueryForECImplTest extends AbstractTestCase {

  // public void testQueryArriveByOrderBid() {
  // IArriveQueryForEC arriveQueryForEC =
  // NCLocator.getInstance().lookup(IArriveQueryForEC.class);
  // try {
  // arriveQueryForEC.queryArriveByOrderBid(new String[] {
  // "1002Z810000000000D6M"
  // });
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }

  public void testQueryBackArriveByCond() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    BackArriveQueryCondVO condVo = new BackArriveQueryCondVO();

    condVo.setBillCodeCond(new QueryCondition(ArriveHeaderVO.VBILLCODE, "=",
        new String[] {
          "DH2013010400000019"
        }));
    condVo.setSupplierCond(new QueryCondition(ArriveHeaderVO.PK_SUPPLIER, "=",
        new String[] {
          "1001E4100000000006GK"
        }));
    condVo.setPurorgNameCond(new QueryCondition("name", "like", new String[] {
      "%1220"
    }));
    condVo.setMatnameCond(new QueryCondition("name", "like", new String[] {
      "%iP%"
    }));

    IArriveQueryForEC query =
        NCLocator.getInstance().lookup(IArriveQueryForEC.class);
    ArriveVO[] results = query.queryBackArriveByCond(condVo);
    if (results != null) {
      for (ArriveVO vo : results) {
        for (ArriveItemVO item : vo.getBVO()) {
          System.out.println("i1:" + item.getVsourcecode());
        }
      }
    }
  }

  // public void testQueryBackArriveByHid() throws BusinessException {
  // IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
  // loginService.login("lxy", "1", null);
  //
  // IArriveQueryForEC query =
  // NCLocator.getInstance().lookup(IArriveQueryForEC.class);
  // ArriveVO[] vos = query.queryBackArriveByHid(new String[] {
  // "1002D3100000000008HQ"
  // });
  //
  // for (ArriveVO vo : vos) {
  // for (ArriveItemVO itemVo : vo.getBVO()) {
  // System.out.println(itemVo.getVsourcecode());
  // }
  // }
  // }
  //
  // public void testQueryBackArriveByOrderBid() {
  //
  // IArriveQueryForEC arriveQueryForEC =
  // NCLocator.getInstance().lookup(IArriveQueryForEC.class);
  // try {
  // arriveQueryForEC.queryBackArriveByOrderBid(new String[] {
  // "10025610000000003IOD"
  // });
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  //
  // public void testQueryNumSummaryByBids() throws BusinessException {
  // IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
  // loginService.login("lxy", "1", null);
  //
  // IArriveQueryForEC arriveQuery =
  // NCLocator.getInstance().lookup(IArriveQueryForEC.class);
  // ArriveNumSummaryECVO[] vos =
  // arriveQuery.queryNumSummaryByOrderBids(new String[] {
  // "1001Z810000000001DFV", "10015510000000000535"
  // });
  //
  // for (ArriveNumSummaryECVO vo : vos) {
  // System.out.println("id:" + vo.getPk_order_b() + ",arrive:"
  // + vo.getArrivenum() + ",back:" + vo.getBacknum());
  // }
  // }
  //
  // public void testQueryNumSummaryByCond() throws BusinessException {
  // IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
  // loginService.login("lxy", "1", null);
  //
  // SupplyDetailQueryCondVO condVo = new SupplyDetailQueryCondVO();
  // QueryCondition queryCondition1 =
  // new QueryCondition("pk_supplier", "=", new String[] {
  // "1001551000000000052V"
  // });
  // condVo.setSupplierCond(queryCondition1);
  //
  // IArriveQueryForEC arriveQuery =
  // NCLocator.getInstance().lookup(IArriveQueryForEC.class);
  // ArriveNumSummaryECVO[] vos = arriveQuery.queryNumSummaryByCond(condVo);
  //
  // for (ArriveNumSummaryECVO vo : vos) {
  // System.out.println("material:" + vo.getPk_srcmaterial() + ",arrive:"
  // + vo.getArrivenum() + ",back:" + vo.getBacknum());
  // }
  // }
}
