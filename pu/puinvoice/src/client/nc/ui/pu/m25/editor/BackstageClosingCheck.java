package nc.ui.pu.m25.editor;

import java.util.Map;

import nc.bd.accperiod.AccperiodmonthAccessor;
import nc.itf.org.check.ICloseCheck;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.pu.m25.service.ClosingCheckModelService;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.org.closeaccbook.check.CheckResultEnum;
import nc.vo.org.closeaccbook.check.CloseParam;
import nc.vo.org.closeaccbook.check.ParamType;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * ºó¶Ë¼ì²éÂß¼­¡£
 * 
 * @since 6.36
 * @time 2015-6-01 ÏÂÎç3:20:04
 * @author luojw
 */
public abstract class BackstageClosingCheck implements ICloseCheck {

  @Override
  public CheckResultEnum checkClose(CloseParam param) throws BusinessException {
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
    ClosingCheckModelService service = this.getService();
    InvoiceHeaderVO[] vo = service.getInvoiceHeaderVO(paraVO);
    CheckResultEnum returntype = new CheckResultEnum();
    if (vo == null || vo.length == 0) {
      returntype.setResultType(1);
    }else{
      returntype.setResultType(-1);
    }
    return returntype;
  }

  protected abstract ClosingCheckModelService getService() ;
}
