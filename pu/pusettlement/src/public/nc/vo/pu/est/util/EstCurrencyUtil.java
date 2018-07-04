/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-4 下午01:57:34
 */
package nc.vo.pu.est.util;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的币种处理工具
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-4 下午01:57:34
 */
public class EstCurrencyUtil {

  /**
   * @param pp
   *          价格优先策略
   */
  public static void feeCurrencyChange(IDataSetForCal ds,
      EstRelationCalcUtil calcUtil, PricePriority pp) {
    String rateKey = ds.getRelationForItem().getNexchangerateKey();
    EstCurrencyUtil.setExchgrate(ds);
    calcUtil.calcDataSetForFee(ds, rateKey);
  }

  public static UFDouble getExchgrate(String pk_fiorg, String pk_currency,
      String pk_loccurr, UFDate date) {
    UFDouble rate = null;
    rate =
        CurrencyRate.getCurrencySellRateByOrg(pk_fiorg, pk_currency,
            pk_loccurr, date);
    rate = MathTool.nvl(rate);
    return rate;
  }

  public static void goodsCurrencyChange(String chgKey, IDataSetForCal ds,
      EstRelationCalcUtil calcUtil, PricePriority pp) {
    String currencyKey = calcUtil.getRelaItems().getCorigcurrencyidKey();
    if (!chgKey.equals(currencyKey)) {
      return;
    }
    String rateKey = calcUtil.getRelaItems().getNexchangerateKey();
    EstCurrencyUtil.setExchgrate(ds);
    calcUtil.calcDataSet(ds, rateKey, pp);
  }

  private static void setExchgrate(IDataSetForCal dataset) {
    String pk_fiorg = dataset.getPk_org();
    String pk_currency = dataset.getCorigcurrencyid();
    String pk_loccurr = dataset.getCcurrencyid();
    UFDate date = dataset.getBillDate();
    UFDouble rate =
        EstCurrencyUtil.getExchgrate(pk_fiorg, pk_currency, pk_loccurr, date);
    dataset.setNexchangerate(rate);
  }

}
