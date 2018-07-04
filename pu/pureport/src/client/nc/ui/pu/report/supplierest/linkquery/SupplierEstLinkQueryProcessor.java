package nc.ui.pu.report.supplierest.linkquery;

import java.awt.Container;

import nc.scmmm.ui.scmpub.report.adapter.ISCMRptLinkQueryProcessor;
import nc.scmmm.vo.scmpub.report.entity.SCMReportTransMap;
import nc.scmmm.vo.scmpub.report.linkparam.SCMBaseLinkQueryParam;
import nc.ui.pu.report.supplierest.SupplierEstQueryAction;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.StringUtils;

/**
 * ��Ӧ������������ݹ���ϸ������
 * 
 * @since 6.0
 * @version 2011-12-1 ����01:50:22
 * @author �����
 */

public class SupplierEstLinkQueryProcessor implements ISCMRptLinkQueryProcessor {

  @Override
  public void doBusinessProcess(Container container,
      SCMBaseLinkQueryParam linkQueryParam, SCMReportTransMap tranMap)
      throws BusinessException {
    return;
  }

  @Override
  public boolean isEnableQuery(Container container,
      SCMBaseLinkQueryParam linkQueryParam, SCMReportTransMap tranMap)
      throws BusinessException {
    String pk_supplier =
        (String) tranMap
            .getLinkQueryRowDataValue(SupplierEstQueryAction.PK_SUPPLIER);
    // ˵����ǰѡ����в�����Ӧ�����ݣ�����������
    if (StringUtils.isEmpty(pk_supplier)) {
      return false;
    }
    return true;
  }

}
