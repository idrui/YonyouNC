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
 * ��Ӧ���ݹ���ϸ��ѯ
 * 
 * @since 6.0
 * @version 2011-5-9 ����02:49:49
 * @author �����
 */

public class SupplierEstDetailQueryAction extends SupplierEstQueryAction {

  @Override
  public IQueryCondition doQueryByDrill(Container parent, IContext context,
      AbsAnaReportModel reportModel, FreeReportDrillParam drillParam) {

    if (drillParam == null) {
      return this.createQueryCondition(context);
    }

    // �����ݹ���ϸ
    SCMRptDrillParam para = (SCMRptDrillParam) drillParam;

    String pk_supplier =
        (String) para.getTraceDatas()[0].getRowData().getData(
            SupplierEstQueryAction.PK_SUPPLIER);
    // �ݹ�����ѯ������ϸ������ŵ�tranmap��
    PuSupplierEstQryInfoPara estPara =
        (PuSupplierEstQryInfoPara) para.getTransMap().get(
            PuQueryInfoPara.QUERY_PARA);
    if (estPara == null) {
      // �ݹ�����������ϸ�����ŵ�tranmap��
      SCMReportTransMap tranMap = para.getTransMap();
      estPara =
          (PuSupplierEstQryInfoPara) tranMap.getIContext().getAttribute(
              PuQueryInfoPara.QUERY_PARA);
    }
    // ȡ����������ζ���߼���������
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
    // ����ʱ��context��û��#queryPara#���ԣ�������Ҫ�����ݹ���ϸ��ѯ
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
   * ��¡�ݹ�����,��Ҫ���ݹ��������Ĳ�������һ��
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
