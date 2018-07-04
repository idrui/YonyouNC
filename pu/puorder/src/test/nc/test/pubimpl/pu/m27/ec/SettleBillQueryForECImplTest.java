package nc.test.pubimpl.pu.m27.ec;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m27.ec.ISettleBillQueryForEC;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SettleBillQueryForECImplTest extends AbstractTestCase {
  public void testQuerySettleBillByOrderBid() {
    ISettleBillQueryForEC settleBillQuery =
        NCLocator.getInstance().lookup(ISettleBillQueryForEC.class);
    try {
      settleBillQuery.querySettleBillByOrderBid(new String[] {
        "10025610000000003IOD"
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
