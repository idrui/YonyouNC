package nc.ui.pu.report.supplierest.linkquery;

import java.awt.Container;

import nc.scmmm.ui.scmpub.report.adapter.ISCMRptLinkQueryProcessor;
import nc.scmmm.vo.scmpub.report.entity.SCMReportTransMap;
import nc.scmmm.vo.scmpub.report.linkparam.SCMBaseLinkQueryParam;
import nc.ui.pu.report.supplierest.SupplierEstQueryAction;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.StringUtils;

/**
 * 供应商暂余额联查暂估明细处理器
 * 
 * @since 6.0
 * @version 2011-12-1 下午01:50:22
 * @author 田锋涛
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
    // 说明当前选择的行不含供应商数据，不进行联查
    if (StringUtils.isEmpty(pk_supplier)) {
      return false;
    }
    return true;
  }

}
