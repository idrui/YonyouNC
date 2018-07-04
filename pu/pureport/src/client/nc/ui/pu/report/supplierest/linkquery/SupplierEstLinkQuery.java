package nc.ui.pu.report.supplierest.linkquery;

import nc.scmmm.ui.scmpub.report.action.SCMRptAbsLinkedQueryRegister;
import nc.scmmm.vo.scmpub.report.linkparam.SCMBaseLinkQueryParam;
import nc.scmmm.vo.scmpub.report.linkparam.SCMRptLinkQueryParam;

/**
 * ��Ӧ������������ݹ���ϸ
 * 
 * @since 6.0
 * @version 2011-12-1 ����01:50:22
 * @author �����
 */

public class SupplierEstLinkQuery extends SCMRptAbsLinkedQueryRegister {

  // ��Ӧ���ݹ���ϸ��ʽ���id
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
        "4004090_0", "04004090-0012")/* @res "���鹩Ӧ���ݹ���ϸ" */);
    param.setNewWind(false);
    param.setLinkquerytype(SCMRptLinkQueryParam.LinkQueryType.Ropert);
    param.setReportid(SupplierEstLinkQuery.EST_DETAIL_REPTID);
    param.setLinkQueryProcessor(new SupplierEstLinkQueryProcessor());
    return param;
  }

}
