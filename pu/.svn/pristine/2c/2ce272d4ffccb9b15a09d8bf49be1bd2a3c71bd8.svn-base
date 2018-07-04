package nc.test.pu.est;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.itf.pu.m27.ISettleMatch;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;

public class SettleTOIAChkTest extends AbstractTestCase {
  public void testCheck() throws BusinessException {
    StockSettleVO[] ssVos =
        NCLocator.getInstance().lookup(IStockFinanceQuery.class)
            .queryPurchaseInByBID(new String[] {
              "0001901000000000FD9A", "0001901000000000FD9E"

            });
    NCLocator.getInstance().lookup(ISettleMatch.class).checkGoodsSettleTOIA(
        ssVos);
  }
}
