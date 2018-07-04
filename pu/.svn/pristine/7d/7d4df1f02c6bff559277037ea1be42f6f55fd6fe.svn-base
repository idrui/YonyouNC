package nc.ui.pu.report.supplierestdetail;

import java.awt.Container;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.scmmm.vo.scmpub.report.entity.SCMReportTransMap;
import nc.scmmm.vo.scmpub.report.linkparam.SCMRptDrillParam;
import nc.ui.pu.report.supplierest.SupplierEstQueryAction;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pu.report.scale.supplierest.SupplierEstDetailScaleStrategy;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.FreeReportDrillParam;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 供应商暂估明细查询
 * 
 * @since 6.0
 * @version 2011-5-9 下午02:49:49
 * @author 田锋涛
 */

public class SupplierEstDetailQueryAction extends SupplierEstQueryAction {

  @Override
  public IQueryCondition doQueryByDrill(Container parent, IContext context,
      AbsAnaReportModel reportModel, FreeReportDrillParam drillParam) {

    if (drillParam == null) {
      return this.createQueryCondition(context);
    }

    // 联查暂估明细
    SCMRptDrillParam para = (SCMRptDrillParam) drillParam;

    String pk_supplier =
        (String) para.getTraceDatas()[0].getRowData().getData(
            SupplierEstQueryAction.PK_SUPPLIER);
    // 暂估余额查询联查明细参数会放到tranmap里
    PuSupplierEstQryInfoPara estPara =
        (PuSupplierEstQryInfoPara) para.getTransMap().get(
            PuQueryInfoPara.QUERY_PARA);
    if (estPara == null) {
      // 暂估余额表联查明细参数放到tranmap里
      SCMReportTransMap tranMap = para.getTransMap();
      estPara =
          (PuSupplierEstQryInfoPara) tranMap.getIContext().getAttribute(
              PuQueryInfoPara.QUERY_PARA);
    }
    // 取不到参数意味着逻辑出问题了
    if (estPara == null) {
      ExceptionUtils.wrappBusinessException("Error!!estPara is null!")/*
                                                                       * -=
                                                                       * notranslate
                                                                       * =-
                                                                       */;
    }
    PuSupplierEstQryInfoPara detailPara = this.cloneEstPara(estPara);
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append(estPara.getWheresql());
    sqlBuilder.append(" and ");
    sqlBuilder.append(SupplierEstQueryAction.EB_PK_SUPPLIER, pk_supplier);
    detailPara.setWheresql(sqlBuilder.toString());
    // 联查时，context里没有#queryPara#属性，这里需要传给暂估明细查询
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, detailPara);

    IQueryCondition condition = drillParam.getSrcCondition();
    if (condition instanceof ReportQueryCondition) {
      ReportQueryCondition rptquerycond = (ReportQueryCondition) condition;
      rptquerycond.setBusiFormat(this.getScaleStrategy()
          .getReportScaleProcess());
    }
    return condition;
  }

  /**
   * 克隆暂估参数,不要跟暂估余额自身的参数混在一起
   * 
   * @param estPara
   * @return
   */
  private PuSupplierEstQryInfoPara cloneEstPara(PuSupplierEstQryInfoPara estPara) {
    if (estPara == null) {
      return null;
    }
    PuSupplierEstQryInfoPara newPara = new PuSupplierEstQryInfoPara();
    newPara.setBegindate(estPara.getBegindate());
    newPara.setBilltypes(estPara.getBilltypes());
    newPara.setBincldfinish(estPara.isBincldfinish());
    newPara.setEnddate(estPara.getEnddate());
    newPara.setGroupcondtion(estPara.getGroupcondtion());
    newPara.setWheresql(estPara.getWheresql());
    return newPara;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new SupplierEstDetailScaleStrategy();
  }

}
