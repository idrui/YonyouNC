/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 下午01:48:16
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询入库单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-31 下午01:48:16
 */
public class EstPurchaseInPriceQryStrategy extends
    AbstractEstPriceQueryStrategy {

  private void setMny(EstPriceQryResltData reslt, IEstPriceQueryInfoProvide info) {
    UFDouble estnum = info.getnum();
    UFDouble innum = info.getBillNum();
    UFDouble estexchgrate = info.getExchgRate();
    UFDouble exchgrate = info.getBillExchgRate();
    if (MathTool.compareTo(estnum, innum) == 0
        && MathTool.compareTo(estexchgrate, exchgrate) == 0) {
      reslt.setMny(info.getBillMny());
      reslt.setTax(info.getBillTax());
      reslt.setTotalmny(info.getBillTotalmny());
      reslt.setOrigmny(info.getBillOMny());
      reslt.setOrigtax(info.getBillOTax());
      reslt.setOrigtotalmny(info.getBillOTotalmny());
      reslt.setNeedCalc(false);

      // wuxla V61
      reslt.setNosubtax(info.getNnosubtax());
      reslt.setCalcostmny(info.getNcalcostmny());
      reslt.setCaltaxmny(info.getNcaltaxmny());
    }
  }

  private void setPrice(EstPriceQryResltData reslt,
      IEstPriceQueryInfoProvide info) {
    reslt.setOrigPrice(info.getBillOPrice());
    reslt.setOrigTaxPrice(info.getBillOTaxPrice());
    reslt.setPrice(info.getBillPrice());
    reslt.setTaxPrice(info.getBillTaxPrice());
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      EstPriceQryResltData reslt = resltData[i];
      this.setPrice(reslt, info);
      this.setMny(reslt, info);
    }
  }

}
