package nc.vo.pu.report.initializer;

import nc.scmmm.pub.scmpub.report.adjustor.SCMRptDefaultAdjustor;
import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.pubitf.scmpub.report.adapter.ISCMReportQueryInitializer;
import nc.vo.pu.report.scale.m21.OrderSummaryScalePrcStrategy;
import nc.vo.pub.lang.UFBoolean;

public class OrderSummaryQueryInitializer implements ISCMReportQueryInitializer {

  private static final long serialVersionUID = 6570308856801225689L;

  @Override
  public SCMRptAbsScalePrcStrategy getScalePrcStrategy() {
    return new OrderSummaryScalePrcStrategy();
  }

  @Override
  public SCMRptDefaultAdjustor getSCMRptDefaultAdjustor() {
    return null;
  }

  @Override
  public UFBoolean isPowerEnable() {
    return UFBoolean.FALSE;
  }

}
