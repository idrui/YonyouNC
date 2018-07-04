package nc.test.pu.m25;

import java.util.Vector;

import nc.bs.framework.test.AbstractTestCase;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.dbcache.DBCacheFacade;

public class OrderQueryFor25Test extends AbstractTestCase {

  public void testGetQuerySql() {
    String sql =
        PfServiceScmUtil.getQueryBillRefBillSql("25", "45", new String[] {
          "0001A11000000000LHPA", "1003A110000000000KXT"
        });
    Vector<?> result = DBCacheFacade.getFromDBCache(sql);
    System.out.println(result);
  }

}
