package nc.ui.pu.m25.model;

import java.util.Map;

import nc.bd.accperiod.AccperiodmonthAccessor;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.org.closeaccbook.check.ICloseCheckModel;
import nc.ui.pu.m25.service.ClosingCheckModelService;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.org.closeaccbook.check.CloseParam;
import nc.vo.org.closeaccbook.check.ParamType;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * 未审批采购发票
 * 
 * @since 6.36
 * @time 2015-6-01 下午3:20:04
 * @author luojw
 */
public class InvoiceCheckItemModel extends BatchBillTableModel implements
    ICloseCheckModel {

  private ClosingCheckModelService modelService ;

  @Override
  public void checkClose(CloseParam param) {

    String pk_period = (String) param.getAttributeValue(ParamType.PERIOD);
    String[] pk_orgs = (String[]) param.getAttributeValue(ParamType.PK_COSTORG);
    Map<String, String> financeOrgs =
        CostRegionPubService.getFinanceOrgMapByCostRegionIDS(pk_orgs);
    String[] financeOrg = financeOrgs.values().toArray(new String[0]);
    UFDate period_begin = null;
    UFDate period_end = null;
    try {
      AccperiodmonthVO formalPeriod =
          AccperiodmonthAccessor.getInstance().queryAccperiodmonthVOByPk(
              pk_period);
      if (formalPeriod != null) {
        period_begin = formalPeriod.getBegindate();
        period_end = formalPeriod.getEnddate();
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    QueryParaVO paraVO = new QueryParaVO(financeOrg, period_begin, period_end);
    InvoiceHeaderVO[] vo = this.modelService.getInvoiceHeaderVO(paraVO);
    this.initModel(vo);
  }

  public ClosingCheckModelService getModelService() {
    return this.modelService;
  }

  public void setModelService(ClosingCheckModelService modelService) {
    this.modelService = modelService;
  }
}
