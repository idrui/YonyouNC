package nc.vo.pu.report.initializer;

import nc.scmmm.pub.scmpub.report.adjustor.SCMRptDefaultAdjustor;
import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.pubitf.scmpub.report.adapter.ISCMReportQueryInitializer;
import nc.vo.pu.report.adjustor.StoreReqSummaryAdjustor;
import nc.vo.pu.report.scale.m422x.StoreReqSummaryScalePrcStrategy;
import nc.vo.pub.lang.UFBoolean;

public class StoreReqSummaryQueryInitializer implements
    ISCMReportQueryInitializer {

  private static final long serialVersionUID = 7790983176725529014L;

  @Override
  public SCMRptAbsScalePrcStrategy getScalePrcStrategy() {
    return new StoreReqSummaryScalePrcStrategy();
  }

  @Override
  public SCMRptDefaultAdjustor getSCMRptDefaultAdjustor() {
    return new StoreReqSummaryAdjustor();
  }

  @Override
  public UFBoolean isPowerEnable() {
    return UFBoolean.TRUE;
  }
}
