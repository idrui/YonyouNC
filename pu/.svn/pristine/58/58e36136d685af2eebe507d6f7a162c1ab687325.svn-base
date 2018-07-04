package nc.vo.pu.report.scale.journal;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;

/**
 * 综合日报
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:59:20
 * @author 田锋涛
 */

public class JournalQueryScaleStrategy extends ICRptBaseScalePrcStrategy {
  @Override
  protected String[] getPriceFields() {
    return new String[] {
      "orderprice", "invoiceprice", "settleprice"
    };
  }

  @Override
  protected void init() {
    super.init();
    this.getReportScaleProcess().setConstantDigits(new String[] {
      "orderrate", "invoicerate", "nacccancelinvmnyrate"
    }, 8);
  }
  
  @Override
  protected String getCurrencyKey() {
    return "corigcurrencyid";
  }
  
  @Override
  protected String[] getMnyFields() {
    return new String[]{
        "ordernmny", "orderntax", "nacccancelinvmny", "arrivenmny", "purnmny",
        "invoicenmny", "settlenmny", "ordertaxmny", "invoicetaxmny", "nacccancelinvmny"
    };
  }
}
