package nc.test.pu.buyingreq;

import java.util.Map;

import nc.bs.framework.test.AbstractTestCase;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.BusinessException;

public class QueryForStockInfo extends AbstractTestCase {

  public void testCheck() throws BusinessException {
    String[] mats = {
      "1006D4100000000002F8"
    };
    String org = "0001D410000000006LUH";

    String[] fields = {
      MaterialStockVO.MARTYPE
    };
    Map<String, MaterialStockVO> info =
        MaterialPubService.queryMaterialStockInfo(mats, org, fields);
    System.out.println(info);
  }
}
