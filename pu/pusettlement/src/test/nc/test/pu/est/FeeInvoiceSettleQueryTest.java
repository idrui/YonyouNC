package nc.test.pu.est;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m27.ISettleBillQueryFor25;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.MapUtils;

public class FeeInvoiceSettleQueryTest extends AbstractTestCase {
  public void testQueryFeeSettle() throws BusinessException {
    MapList<String, SettleBillInfo> map =
        NCLocator.getInstance().lookup(ISettleBillQueryFor25.class)
            .querySettleBills(new String[] {
              "0001Z81000000000G1YJ", "0001Z81000000000G1YN"
            });
    MapUtils.debugPrint(System.out, null, map.toMap());
  }
}
