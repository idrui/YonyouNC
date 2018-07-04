package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSalePubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderFunctionUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @since 6.0
 * @version 2011-4-12 ÏÂÎç05:48:51
 * @author wuxla
 */

public class GrossProfitUtil {

  public static UFDouble[] getGrossProfit(OrderVO orderVO)
      throws BusinessException {
    String pk_org = orderVO.getHVO().getPk_org();
    if (!OrgUnitPubService.isTypeOf(pk_org, IOrgConst.SALEORGTYPE)) {
      return null;
    }
    OrderFunctionUtil util = OrderFunctionUtil.getInstance();
    OrderVO vo = util.getCheckVO(orderVO);
    if (null == vo) {
      return null;
    }

    List<String> pkList = new ArrayList<String>();
    for (OrderItemVO itemVO : vo.getBVO()) {
      if (itemVO.getPk_material() != null) {
        pkList.add(itemVO.getPk_material());
      }
    }
    Map<String, UFDouble> priceMap =
        MaterialSalePubService.queryResaleprice(
            pkList.toArray(new String[pkList.size()]), pk_org);
    UFDouble profitMny = UFDouble.ZERO_DBL;
    UFDouble saleMny = UFDouble.ZERO_DBL;
    for (OrderItemVO itemVO : vo.getBVO()) {
      if (UFBoolean.TRUE.equals(itemVO.getBlargess())) {
        continue;
      }
      UFDouble nnum = itemVO.getNnum();
      UFDouble ntaxmny = itemVO.getNtaxmny();
      UFDouble nresaleprice =
          MathTool.nvl(priceMap.get(itemVO.getPk_material()));
      UFDouble tempMny = nresaleprice.multiply(nnum);
      saleMny = saleMny.add(tempMny);
      profitMny = profitMny.add(tempMny).sub(ntaxmny);
    }
    return new UFDouble[] {
      profitMny, saleMny
    };
  }
}
