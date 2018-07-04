package nc.test.pu.m21;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m21.to.IOrderPriceQuery4TO;
import nc.pubitf.pu.m21.to.IOrderPriceQueryPara4TO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;

/**
 * 
 * @since 6.0
 * @version 2011-4-20 ÏÂÎç12:03:53
 * @author zhaoyha
 */

public class PriceQuery4TOTestCase extends AbstractTestCase {

  public static class QueryPara implements IOrderPriceQueryPara4TO,
      Serializable {

    private static final long serialVersionUID = 8241650106290231607L;

    @Override
    public String getFinanceOrg() {
      return "0001H21000000000CE82";
    }

    @Override
    public List<String> getMaterial() {
      List<String> lst = new ArrayList<String>();
      lst.add("1001H210000000000KGL");
      lst.add("1015H2100000000001MR");
      return lst;
    }

  }

  public void testQuery() throws BusinessException {
    List<IOrderPriceQueryPara4TO> paraLst =
        new ArrayList<IOrderPriceQueryPara4TO>();
    paraLst.add(new QueryPara());
    OrderPriceData[] datas =
        NCLocator.getInstance().lookup(IOrderPriceQuery4TO.class)
            .queryLatestPrice(paraLst);
    System.out.println(Arrays.deepToString(datas));
  }
}
