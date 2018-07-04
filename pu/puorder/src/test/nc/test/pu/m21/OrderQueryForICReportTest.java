package nc.test.pu.m21;

import java.util.Arrays;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m21.ic.report.IOrderQueryForICReport;
import nc.pubitf.pu.m21.ic.report.OrderViewForICBorrowMemo;
import nc.vo.pub.BusinessException;

/**
 * 
 * @since 6.0
 * @version 2011-4-20 ÏÂÎç12:03:53
 * @author zhaoyha
 */

public class OrderQueryForICReportTest extends AbstractTestCase {

  public void testQuery() throws BusinessException {
    IOrderQueryForICReport query =
        NCLocator.getInstance().lookup(IOrderQueryForICReport.class);
    String[][] ids = this.getIDS();
    OrderViewForICBorrowMemo[] vos = query.queryForBorrowMemo(ids[0], ids[1]);
    System.out.println(Arrays.deepToString(vos));
  }

  private String[][] getIDS() {
    return new String[][] {
      {
        "10011310000000005KG8", "10011310000000005CT5"
      }, {
        "10011310000000005KG9", "10011310000000005CT6"
      }
    };
  }
}
