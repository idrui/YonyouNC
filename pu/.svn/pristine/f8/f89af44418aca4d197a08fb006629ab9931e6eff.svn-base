package nc.ui.pu.report.pub.action;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.ui.ic.icreport.pub.util.MainOrgPowerUtil;
import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreePrivateContextKey;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

public class PURptDefaultQueryAction extends DefaultQueryAction {

  public static final String PK_ORG = "pk_org";

  private static final String CMATERIALOID = "eb.pk_material";

  private static final String CMATERIALVID = "eb.pk_material";

  private static final String CVENDORID = "eb.pk_supplier";

  private QueryConditionDLGDelegator delegator;

  private boolean mainOrgPower = true;

  private IQueryConditionDLGInitializer qryCondDLGInitializer;

  private IQueryCondition queryCondition;

  /**
   * 根据指定查询方案进行查询 （IQueryAction接口的方法的实现）。
   * 
   * @param parent
   * @param context
   * @param reportModel
   * @param queryScheme
   * @return
   */
  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    ICReportConditionUtils.setDescription(context, queryScheme);
    // 权限设置
    this.setRptPermission(context);
    return super.doQueryByScheme(parent, context, reportModel, queryScheme);
  }

  public QueryConditionDLGDelegator getDelegator() {
    return this.delegator;
  }

  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return this.qryCondDLGInitializer;
  }

  public boolean isMainOrgPower() {
    return this.mainOrgPower;
  }

  public void setDelegator(QueryConditionDLGDelegator delegator) {
    this.delegator = delegator;
  }

  public void setMainOrgPower(boolean mainOrgPower) {
    this.mainOrgPower = mainOrgPower;
  }

  public void setQryCondDLGInitializer(
      IQueryConditionDLGInitializer qryCondDLGInitializer) {
    this.qryCondDLGInitializer = qryCondDLGInitializer;
  }

  /**
   * 为了适配平台的主组织权限，报表必须查出组织字段（可以不显示），对于汇总报表，取查询条件中第一个组织写死在SQL里。
   * 此方法的作用就是将查询条件中的主组织条件的第一个PK值存入上下文，供后台拼SQL使用。
   */
  private void setCondOrgAttribute(IContext context, ConditionVO[] conds) {
    String pk_org_cond = "''";
    for (ConditionVO condVo : conds) {
      if (this.getMainOrgKey().equals(condVo.getFieldCode())) {
        String[] temp = condVo.getValue().split(",");
        if (temp.length == 1) {
          pk_org_cond = "'" + temp[0] + "'";
        }
        else {
          pk_org_cond = StringUtils.remove(temp[0], "(");
        }
      }
    }
    context.setAttribute(FreePrivateContextKey.KEY_USER_PK_ORG, pk_org_cond);
  }

  /**
   * 设置查询精度 zhoudi
   */
  @Override
  protected IQueryCondition createQueryCondition(IContext context) {
    // lixyp - 2012.4.17 - 修改
    // 去掉了判断空的条件，因为如果加上非空条件，当没有关闭节点的情况下，最大精度变小时，其不会变化。
    // 例如：第一次查询主数量字段最大精度为4，第二次查询最大精度变为了1，但显示时还会是4。

    ReportQueryCondition condition = new ReportQueryCondition(true);
    ICRptBaseScalePrcStrategy scaleStrategy = this.getScaleStrategy();
    if (scaleStrategy != null) {
      condition.setBusiFormat(scaleStrategy.getReportScaleProcess());
    }
    this.queryCondition = condition;
    return this.queryCondition;
  }

  /**
   * 构建查询面版
   */
  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      IContext context, IQueryCondition oldCondition) {
    if (this.delegator == null) {
      this.delegator = new QueryConditionDLGDelegator(parent, ti);
    }
    // 查询模板初始化
    if (this.getQryCondDLGInitializer() != null) {
      this.getQryCondDLGInitializer().initQueryConditionDLG(this.delegator);
    }
    // 查询模板过滤
    this.setFilter(this.delegator);
    if (this.getMainClass() != null) {
      this.delegator.registerRefPowerFilterInfo(this.getMainClass(),
          this.getColumnMapping());
    }
    // 主组织权限
    if (this.isMainOrgPower()) {
      MainOrgPowerUtil mainOrgUitl = new MainOrgPowerUtil(context);
      mainOrgUitl.setMainOrgPower(this.delegator, this.getMainOrgKey());
    }

    this.delegator.addQueryCondVODealer(new MarAssistantDealer());
    return this.delegator.getQueryConditionDLG();
  }

  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    return columnMapping;
  }

  protected Class<? extends ISuperVO> getMainClass() {
    return null;
  }

  /**
   * @return
   */
  protected String getMainOrgKey() {
    return PURptDefaultQueryAction.PK_ORG;
  }

  /**
   * 得到冗余字段的SQL片断
   * 
   * @param RedunMap Map{fieldcode,redun_fieldcode}
   * @return
   */
  protected String getRedundancyWhere(Map<String, String> RedunMap) {
    if (null == RedunMap || RedunMap.size() == 0) {
      return null;
    }
    SCMReportQueryConUtil util = new SCMReportQueryConUtil();
    util.setMapRedundancy(RedunMap);
    return util.getQueryWhereSqlAfterAddReduncy(this.getDelegator()
        .getQueryScheme());
  }

  /**
   * 子类需覆写，实现自己的精度处理
   * 
   * @return
   */
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return null;
  }

  /**
   * 适配平台主组织权限，供查询框使用。
   * 
   * @param context
   */
  protected void setCondOrgAttribute(IContext context) {
    this.setCondOrgAttribute(context, this.getDelegator()
        .getGeneralCondtionVOs());
  }

  /**
   * 适配平台主组织权限，供快速查询使用。
   * 
   * @param context
   */
  protected void setCondOrgAttribute(IContext context, IQueryScheme queryScheme) {
    IFilter[] filters = (IFilter[]) queryScheme.get(IQueryScheme.KEY_FILTERS);
    ConditionVO[] conds =
        QueryTemplateConvertor.convertIFilter2ConditionVO(Arrays
            .asList(filters));
    this.setCondOrgAttribute(context, conds);
  }

  /**
   * 参照过滤,需要可重写
   * 
   * @param delegator
   */
  protected void setFilter(QueryConditionDLGDelegator delegator) {
    return;
  }

  /**
   * @param filterDescm
   * @param context
   */
  protected void setMainOrgFilter(FilterDescriptor filterDescm, IContext context) {
    MainOrgPowerUtil mainOrgUitl = new MainOrgPowerUtil(context);
    // 参数1：过滤描述器对象，参数2：主组织字段
    mainOrgUitl.setMainOrgFilter(filterDescm, this.getMainOrgKey());

  }

  /**
   * 从查询对话框中获取用户设置信息
   * 
   * @param condition
   * @param queryDlg
   * @param normalPanel
   * @param repParams
   * @param context
   * @return
   */
  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    ICReportConditionUtils.setDescription(context, queryDlg.getQueryScheme());
    return super.setQueryResult(condition, queryDlg, reportModel, context);
  }

  /**
   * 权限设置,如果子类重写了showQueryDialog方法,需手工调用此方法
   * 
   * @param context
   *          2011-9-13
   *          wangljc
   */
  protected void setRptPermission(IContext context) {
    // 权限设置
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(this.getMainClass());
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    if (queryDlg.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      // 权限设置
      this.setRptPermission(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    else {
      return new BaseQueryCondition(false);
    }
  }

}
