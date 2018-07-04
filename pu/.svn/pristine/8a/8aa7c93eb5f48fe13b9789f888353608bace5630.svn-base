package nc.vo.pu.report.adjustor;

import nc.pubimpl.ic.icreport.adjustor.ICRptDefaultAdjustor;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;

/**
 * @since 6.0
 * @version 2011-8-1 обнГ01:03:17
 * @author wuxla
 */

public class OrderExecDefaultAdjustor extends ICRptDefaultAdjustor {
  private static final long serialVersionUID = -7104106627738314406L;

  @Override
  protected String[] getHidenFields() {
    Object hidden =
        this.getContext().getAttribute(PUOrderQryInfoPara.GATHERNOSHOWPARA);
    if (null == hidden) {
      return null;
    }
    return (String[]) hidden;
  }
}
