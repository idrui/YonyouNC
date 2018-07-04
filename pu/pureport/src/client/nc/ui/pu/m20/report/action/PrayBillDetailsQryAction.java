package nc.ui.pu.m20.report.action;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.m20.report.dlg.PrayBillDetailsQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.report.adjustor.PuReportAdjustor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pu.report.scale.m20.PrayBillDetailScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 请购单执行明细。
 * 
 * @since 6.0
 * @version 2011-5-10 上午11:07:20
 * @author liuchx
 */
public class PrayBillDetailsQryAction extends PURptDefaultQueryAction {

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    if (queryScheme == null) {
      return new BaseQueryCondition(false);
    }
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    PrayBillQryInfoPara para = new PrayBillQryInfoPara();
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] conds =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      String where = queryScheme.getWhereSQLOnly();
      context.setAttribute("#queryconds#", where);
      for (ConditionVO cond : conds) {
        String feildCode = cond.getFieldCode();

        if ((feildCode != null)
            && feildCode.equalsIgnoreCase("po_praybill.bsctype")) {
          String bsc = cond.getValue();
          para.setBsc(bsc);
        }
        if ((feildCode != null)
            && feildCode.equals(PrayBillQryInfoPara.QUERY_GROUP_KEY)) {
          para.setGroupcondtion(cond.getValue());
          context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
        }
      }

      context.setAttribute(PuQueryInfoPara.QUERY_CONDS, conds);

    }
    ICReportConditionUtils.setDescription(context, queryScheme);
    ReportQueryCondition condition =
        (ReportQueryCondition) this.createQueryCondition(context);
    if (para.getHideKeys() != null) {
      condition.setRoportAdjustor(new PuReportAdjustor());
    }
    this.setCondOrgAttribute(context, queryScheme);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new PrayBillDetailsQryDlgInit();
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PrayBillQryInfoPara.CMATERIALOID,
        PrayBillQryInfoPara.SRCMATERIAL);
    columnMapping.put(PrayBillQryInfoPara.CMATERIALOID_NAME,
        PrayBillQryInfoPara.SRCMATERIAL);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return PraybillHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return PrayBillQryInfoPara.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new PrayBillDetailScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if ((condition == null) || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    BaseQueryCondition result = (BaseQueryCondition) condition;
    PrayBillQryInfoPara para = new PrayBillQryInfoPara();
    ConditionVO[] conds = this.getDelegator().getGeneralCondtionVOs();
    context.setAttribute(PuQueryInfoPara.QUERY_CONDS, conds);

    for (ConditionVO cond : queryDlg.getLogicalConditionVOs()) {
      String feildCode = cond.getFieldCode();

      if ((feildCode != null)
          && feildCode.equalsIgnoreCase("po_praybill.bsctype")) {
        String bsc = cond.getValue();
        para.setBsc(bsc);
      }

      if ((feildCode != null)
          && feildCode.equals(PrayBillQryInfoPara.QUERY_GROUP_KEY)) {

        para.setGroupcondtion(cond.getValue());
        PuReportAdjustor adjustor = new PuReportAdjustor();
        if (para.getHideKeys() != null) {
          result.setRoportAdjustor(adjustor);
        }
        context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
      }
    }
    this.setCondOrgAttribute(context);

    return result;
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    if (queryDlg.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new BaseQueryCondition(false);
  }
}
