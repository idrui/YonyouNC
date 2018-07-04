package nc.pubimpl.pu.m4201;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4201.IStockFinanceSettleInfoQuery;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 查询采购入库财务副本结算信息
 * 
 * @since 6.0
 * @version 2011-3-25 下午03:22:28
 * @author yinfy
 */

public class StockFinanceSettleInfoQueryImpl implements
    IStockFinanceSettleInfoQuery {

  @Override
  public Map<String, UFDouble> getPriceArrayByRowIdsNoTax(String[] bids)
      throws BusinessException {
    Map<String, UFDouble> result = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(bids)) {
      return result;
    }
    try {
      VOQuery<PurchaseinFIItemVO> qry =
          new VOQuery<PurchaseinFIItemVO>(PurchaseinFIItemVO.class);
      PurchaseinFIItemVO[] items = qry.query(bids);
      if (ArrayUtils.isEmpty(items)) {
        return result;
      }
      for (PurchaseinFIItemVO item : items) {
        ScaleUtils utils = new ScaleUtils(item.getPk_group());
        UFDouble nnum = item.getNaccumsettlenum();
        UFDouble nmny = item.getNaccgoodssettlemny();
        // 结算平均价
        UFDouble price =
            MathTool.isZero(nmny) || MathTool.isZero(nnum) ? null : nmny
                .div(nnum);
        result.put(item.getPk_stockps_b(),
            utils.adjustSoPuPriceScale(price, item.getCorigcurrencyid()));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return result;
  }

  @Override
  public Map<String, UFDouble> getTotalSettleNum(String[] bids)
      throws BusinessException {
    Map<String, UFDouble> result = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(bids)) {
      return result;
    }
    try {
      VOQuery<PurchaseinFIItemVO> qry =
          new VOQuery<PurchaseinFIItemVO>(PurchaseinFIItemVO.class);
      PurchaseinFIItemVO[] items = qry.query(bids);
      if (ArrayUtils.isEmpty(items)) {
        return result;
      }
      for (PurchaseinFIItemVO item : items) {
        result.put(item.getPk_stockps_b(),
            MathTool.nvl(item.getNaccumsettlenum()));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return result;
  }

}
