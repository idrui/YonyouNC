package nc.test.pu.m21.pim;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m21.pim.IOrderQueryForPIM;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

public class OrderQueryForPIMTest extends AbstractTestCase {

  public void testQueryForLinkQuery() {
    try {
      LinkQueryParamVOForPM pvo = new LinkQueryParamVOForPM();
      pvo.setCmaterialoid("100121100000000001UV");
      pvo.setCprojectid("~");
      pvo.setCprojecttaskid("~");
      pvo.setEnddate(new UFDate("3000-1-1"));
      pvo.setStartdate(new UFDate("2000-1-1"));

      IOrderQueryForPIM orderQueryForPIM =
          NCLocator.getInstance().lookup(IOrderQueryForPIM.class);
      OrderVO[] vos = orderQueryForPIM.queryOrderForLinkQuery(pvo);
      System.out.println("vos:" + vos.length);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
