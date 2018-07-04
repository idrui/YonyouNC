package nc.ui.pu.report.supplierest.linkquery;

import nc.scmmm.ui.scmpub.report.action.SCMRptAbsLinkedQueryRegister;
import nc.scmmm.vo.scmpub.report.linkparam.SCMBaseLinkQueryParam;
import nc.scmmm.vo.scmpub.report.linkparam.SCMRptLinkQueryParam;

/**
 * 供应商暂余额联查暂估明细
 * 
 * @since 6.0
 * @version 2011-12-1 下午01:50:22
 * @author 田锋涛
 */

public class SupplierEstLinkQuery extends SCMRptAbsLinkedQueryRegister {

  // 供应商暂估明细格式设计id
  private static final String EST_DETAIL_REPTID = "1001Z8100000000067RL";

  @Override
  public SCMBaseLinkQueryParam[] getSCMLinkQueryParam() {
    return new SCMRptLinkQueryParam[] {
      this.getLinkQueryPara()
    };
  }

  private SCMRptLinkQueryParam getLinkQueryPara() {
    SCMRptLinkQueryParam param = new SCMRptLinkQueryParam();
    param.setMenucode("LinkQuery");
    param.setMenuname(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004090_0", "04004090-0012")/* @res "联查供应商暂估明细" */);
    param.setNewWind(false);
    param.setLinkquerytype(SCMRptLinkQueryParam.LinkQueryType.Ropert);
    param.setReportid(SupplierEstLinkQuery.EST_DETAIL_REPTID);
    param.setLinkQueryProcessor(new SupplierEstLinkQueryProcessor());
    return param;
  }

}
