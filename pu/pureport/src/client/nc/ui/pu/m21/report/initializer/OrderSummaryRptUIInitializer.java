package nc.ui.pu.m21.report.initializer;

import java.util.Map;

import nc.scmmm.pub.scmpub.report.adjustor.SCMRptDefaultAdjustor;
import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.ui.scmpub.report.action.SCMRptQueryConditionDLGInitializer;
import nc.scmmm.ui.scmpub.report.adapter.ISCMReportQueryUIInitializer;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;

public class OrderSummaryRptUIInitializer implements
    ISCMReportQueryUIInitializer {

  private static final long serialVersionUID = 2305806825014804471L;

  @Override
  public SCMRptQueryConditionDLGInitializer getQueryDLGInitializer() {
    return new OrderSummaryRptRefInitializer();
  }

  @Override
  public Map<String, String> getQueryTmpColumnMapping() {
    return null;
  }

  @Override
  public Class<? extends ISuperVO> getQueryTmpMainClass() {
    return null;
  }

  @Override
  public SCMRptAbsScalePrcStrategy getScalePrcStrategy() {
    return null;
  }

  @Override
  public SCMRptDefaultAdjustor getSCMRptDefaultAdjustor() {
    return null;
  }

  @Override
  public UFBoolean isPowerEnable() {
    return UFBoolean.TRUE;
  }

}
