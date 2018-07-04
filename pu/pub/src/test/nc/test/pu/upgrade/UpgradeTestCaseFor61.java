package nc.test.pu.upgrade;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m25transtype.IQueryTranstype;
import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-4-20 обнГ12:03:53
 * @author zhaoyha
 */

public class UpgradeTestCaseFor61 extends AbstractTestCase {

  public void testUpgradeFor61() throws BusinessException {
    IQueryTranstype service =
        NCLocator.getInstance().lookup(IQueryTranstype.class);
    service.queryAttrByTypes(null, null, null);
  }
}
