package nc.vo.pu.est.util;

import nc.vo.pubapp.calculator.data.IDataSetForCal;

public class EstTaxRateUtil {

  public static void feeTaxRate(IDataSetForCal ds, EstRelationCalcUtil calcUtil) {
    String rateKey = ds.getRelationForItem().getNtaxrateKey();
    calcUtil.calcDataSetForFee(ds, rateKey);
  }

}
