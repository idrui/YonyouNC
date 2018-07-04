package nc.test.pubitf.pu.position;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.position.IQueryPositionForMM;
import nc.vo.pub.BusinessException;

public class IQueryPositionForMMTest extends AbstractTestCase {

  public void testGetMaterialSqlByCemployee() throws BusinessException {

    IQueryPositionForMM service =
        NCLocator.getInstance().lookup(IQueryPositionForMM.class);
    String sql = service.getMaterialSqlByCemployee("100933100000000001JZ");
    System.out.println(sql);
    // IQueryPositionForMM service = new QueryPositionForMMImpl();
    // String sql = service.getMaterialSqlByCemployee("1002331000000000591W");
    // System.out.println(sql);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
}
