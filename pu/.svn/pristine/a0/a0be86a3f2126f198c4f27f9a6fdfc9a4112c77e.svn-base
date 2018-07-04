package nc.pu.pos.test;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.position.IQueryPosition;
import nc.vo.pub.BusinessException;

public class QueryPlanMaterialForCurrentUserTestCase extends AbstractTestCase {

  public void testQuery() throws BusinessException {
    IQueryPosition qp = NCLocator.getInstance().lookup(IQueryPosition.class);
    String sql = qp.queryPlanMaterialsForUser("0001H210000000016ZG9");
    System.out.println(sql);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
}
