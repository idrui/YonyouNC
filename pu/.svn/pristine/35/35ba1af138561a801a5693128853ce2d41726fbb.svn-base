package nc.test.pubitf.pu.m21.pim;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m422x.pim.IStoreReqQueryForPIM;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

public class IStoreReqQueryForPIMTest extends AbstractTestCase {
  public void testShow() throws Exception {

    IStoreReqQueryForPIM storeReqQuery =
        NCLocator.getInstance().lookup(IStoreReqQueryForPIM.class);
    StoreReqAppVO[] a =
        storeReqQuery.queryStoreReqForLinkQuery(this.getVoForStoreReq());
    System.out.println(a);
  }

  LinkQueryParamVOForPM getVoForStoreReq() {
    LinkQueryParamVOForPM vo = new LinkQueryParamVOForPM();
    vo.setCmaterialoid("1001Z81000000000111H");
    vo.setCprojectid("1");
    vo.setCprojecttaskid("");
    vo.setEnddate(new UFDate("2012-03-31"));
    vo.setStartdate(new UFDate("2012-01-31"));
    return vo;
  }
}
