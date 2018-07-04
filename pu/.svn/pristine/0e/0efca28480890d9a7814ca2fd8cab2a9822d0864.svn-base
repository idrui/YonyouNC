package nc.vo.pu.report.convertor;

import java.io.Serializable;
import java.util.TimeZone;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.scmmm.pub.scmpub.report.rptutil.SCMRptQuerySchemeUtils;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.report.pub.PuReportSubConvertor;
import nc.vo.pu.report.queryinfo.supplierstatcomprate.SupplierStatCompRateQryInfoPara;
import nc.vo.pubapp.AppContext;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 按供应商统计订单完成率-报表订阅条件处理类
 * 
 * @since 6.3
 * @version 2012-12-20 上午10:38:08
 * @author fanly3
 */
public class SupplierStatCompRateConvertor extends PuReportSubConvertor
    implements Serializable {

  /** 带表别名的日期字段 */
  private static final String DBILLDATE_WITH_ALIAS = "po_order_b.dbilldate";

  /** 查询模板传过来的不带表别名的日期字段 */
  private static final String DBILLDATE_WITHOUT_ALIAS = "dbilldate";

  /** 带表别名的集团字段 */
  private static final String PK_GROUP_WITH_ALIAS = "po_order_b.pk_group";

  /** 查询模板传过来的不带表别名的集团字段 */
  private static final String PK_GROUP_WITHOUT_ALIAS = "pk_group";

  /** 查询模板组织字段名 */
  private static final String PK_ORG = "po_order_b.pk_org";

  private static final long serialVersionUID = -8316012396149920384L;

  /**
   * 设置查询参数
   * 
   * @param context 上下文context对象
   * @param wherePart 查询对话框中的where条件
   */
  private void processQueryPara(IContext context) {
    String wherePart = this.getTranMap().getWherePart();
    String newWherePart = wherePart;
    // 集团字段加表别名
    if (newWherePart
        .contains(SupplierStatCompRateConvertor.PK_GROUP_WITHOUT_ALIAS)) {
      newWherePart =
          wherePart.replace(
              SupplierStatCompRateConvertor.PK_GROUP_WITHOUT_ALIAS,
              SupplierStatCompRateConvertor.PK_GROUP_WITH_ALIAS);
    }
    // 日期字段加表别名
    if (newWherePart
        .contains(SupplierStatCompRateConvertor.DBILLDATE_WITHOUT_ALIAS)) {
      newWherePart =
          newWherePart.replace(
              SupplierStatCompRateConvertor.DBILLDATE_WITHOUT_ALIAS,
              SupplierStatCompRateConvertor.DBILLDATE_WITH_ALIAS);
    }
    context.setAttribute(SupplierStatCompRateQryInfoPara.QUERY_WHERE,
        newWherePart);
    // 截止日期取当前业务日期
    String deadlineDate =
        AppContext.getInstance().getBusiDate().asLocalEnd().toString();
    context.setAttribute(SupplierStatCompRateQryInfoPara.DEADLINEDATE,
        deadlineDate);
  }

  /**
   * 自己处理的where条件，供后台用
   * 
   * @param condition 查询交互返回的条件
   * @param context 上下文context对象
   */
  private void setQueryResult(IQueryCondition condition, IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return;
    }
    // 获取查询模板条件
    this.processQueryPara(context);
    this.setCondOrgAttribute(context, SupplierStatCompRateConvertor.PK_ORG);
  }

  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    super.doBusinessProcess(condition, context);
    this.setQueryResult(condition, context);
  }
  
  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel rptModel) {
    IQueryCondition condition = super.getQueryCondition(subscribCondition, context, rptModel);
    BaseSubscribeCondition subCondition =
        (BaseSubscribeCondition) subscribCondition;
    // 获得任务设置的执行时间
    TimeZone timezone =
        (TimeZone) context
            .getAttribute(FreeReportContextKey.REPORT_EXEC_TIMEZONE);
    // 获得订阅的方案
    IQueryScheme queryScheme = subCondition.getScheme();
    SCMRptQuerySchemeUtils.setDescription(this.getTranMap(), queryScheme, timezone);
    return condition;
  }
}
