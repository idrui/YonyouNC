package nc.test.pubitf.pu.m21.mm;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m21.mm.IOrderQueryExecForMM;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;

public class IOrderQueryExecForMMTest extends AbstractTestCase {
  public void testGetLatestPrice() throws BusinessException {

    IOrderQueryExecForMM service =
        NCLocator.getInstance().lookup(IOrderQueryExecForMM.class);
    OrderPriceData[] orderPriceData = service.getLatestPrice(new String[] {
      "0001S210000000001W16", "00015610000000001D4U"
    }, new String[] {
      "1001S2100000000004T3", "100256100000000008QM"
    });
    System.out.println(orderPriceData);
  }
}
