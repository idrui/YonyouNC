package nc.vo.pu.m27.util;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

public class SettlePublicUtil {

  /**
   * 发票和入库是否来自同一订单
   * 
   * @param voaInvoice 发票结算vo
   * @param voaStock 入库结算vo
   * @return
   */
  public static boolean fromSameOrder(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock) {
    for (InvoiceSettleVO voInvoice : voaInvoice) {
      for (StockSettleVO voStock : voaStock) {
        // 来源订单的发票作为判断依据
        if (!PubAppTool.isNull(voInvoice.getPk_order())
            && !PubAppTool.isEqual(voInvoice.getPk_order(),
                voStock.getPk_order())) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 方法功能描述：根据成本要素索引号获得对应的结算单上面的成本要素值名称(前后台费用分摊时使用)
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-11 上午08:49:16
   */
  public static String getNcostfactorNameByIndex(int index) {
    switch (index) {
      case 1:
        return SettleBillItemVO.NCOSTFACTOR1;
      case 2:
        return SettleBillItemVO.NCOSTFACTOR2;
      case 3:
        return SettleBillItemVO.NCOSTFACTOR3;
      case 4:
        return SettleBillItemVO.NCOSTFACTOR4;
      case 5:
        return SettleBillItemVO.NCOSTFACTOR5;
      case 6:
        return SettleBillItemVO.NCOSTFACTOR6;
      case 7:
        return SettleBillItemVO.NCOSTFACTOR7;
      case 8:
        return SettleBillItemVO.NCOSTFACTOR8;
    }
    return null;
  }

  /**
   * 得到一个结算单行的所有影响成本的成本要素之和
   * 
   * @param item
   * @return
   */
  public static UFDouble getSumCostfactor(SettleBillItemVO item,
      CostfactorViewVO[] cfviews) {
    UFDouble sum = null;
    if (ArrayUtils.isEmpty(cfviews)) {
      return null;
    }
    for (int i = 1; i <= CostfactorVO.MAX_NUM; ++i) {
      if (!SettlePublicUtil.isEnterCost(i, cfviews)) {
        continue;
      }
      UFDouble costfactor =
          (UFDouble) item.getAttributeValue(SettlePublicUtil
              .getNcostfactorNameByIndex(i));
      sum = MathTool.add(sum, costfactor);
    }
    return sum;
  }

  public static boolean isEnterCost(int i, CostfactorViewVO[] cfviews) {
    for (CostfactorViewVO cfview : cfviews) {
      if (Integer.valueOf(i).equals(cfview.getIfactororder())
          && UFBoolean.TRUE.equals(cfview.getBentercost())) {
        return true;
      }
    }
    return false;
  }
}
