package nc.test.pubitf.pu.m21.pim;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m20.pim.IBuyingReqQueryForPIM;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

public class IBuyingReqQueryForPIMTest extends AbstractTestCase {
  public void testShow() throws Exception {
    IBuyingReqQueryForPIM buyingReqQuery =
        NCLocator.getInstance().lookup(IBuyingReqQueryForPIM.class);
    PraybillVO[] a =
        buyingReqQuery.queryBuyingReqForLinkQuery(this.getVoForBuyingReq());
    System.out.println(a);
  }

  LinkQueryParamVOForPM getVoForBuyingReq() {
    LinkQueryParamVOForPM vo = new LinkQueryParamVOForPM();
    vo.setCmaterialoid("100301100000000001GV");
    vo.setCprojectid("1");
    vo.setCprojecttaskid("");
    vo.setEnddate(new UFDate("2012-03-31"));
    vo.setStartdate(new UFDate("2012-01-31"));
    return vo;
  }
}
