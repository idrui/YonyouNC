package nc.ui.pu.m21.report.action;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.m21.report.dlg.OrderPayExecQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.scale.m21.OrderPayExecScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * @since 6.0
 * @version 2011-7-5 下午03:30:02
 * @author wuxla
 */

public class OrderPayExecQryAction extends PURptDefaultQueryAction {

  public static final String mainOrg = "po_order.pk_org";

  public static final String SRCMATERIAL = "pk_order_b."
      + OrderItemVO.PK_SRCMATERIAL;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    if (queryScheme == null) {
      return new BaseQueryCondition(false);
    }
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] vos =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      context.setAttribute(PUOrderQryInfoPara.PAYEXEC_COND, vos);
    }

    String whereSql = queryScheme.getWhereSQLOnly();
    if (whereSql != null && whereSql.length() > 0) {
      SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
      conutil.setMapRedundancy(this.getRedunMap());
      String rduncysql = conutil.getQueryWhereSqlAfterAddReduncy(queryScheme);
      if (rduncysql != null && rduncysql.length() > 0) {
        whereSql = whereSql + rduncysql;
      }
      context.setAttribute(PUOrderQryInfoPara.PAYEXEC_WHERE, whereSql);
    }
    ICReportConditionUtils.setDescription(context, queryScheme);
    this.setCondOrgAttribute(context, queryScheme);
    IQueryCondition condition = this.createQueryCondition(context);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new OrderPayExecQryDlgInit();
  }

  private Map<String, String> getRedunMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(PUEntity.M21_H_TABLE + "." + OrderHeaderVO.DBILLDATE,
        PUEntity.M21_B_TABLE + "." + OrderItemVO.DBILLDATE);
    map.put(OrderPayExecQryAction.mainOrg, "po_order_b." + OrderItemVO.PK_ORG);
    return map;
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_CODE,
        OrderPayExecQryAction.SRCMATERIAL);
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_NAME,
        OrderPayExecQryAction.SRCMATERIAL);
    columnMapping.put(PUOrderQryInfoPara.BD_SUPPLIER_CODE,
        OrderHeaderVO.PK_SUPPLIER);
    columnMapping.put(PUOrderQryInfoPara.BD_SUPPLIER_NAME,
        OrderHeaderVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return OrderHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return OrderPayExecQryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new OrderPayExecScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    String whereSql = queryDlg.getWhereSQL();
    if (whereSql != null && whereSql.length() > 0) {
      String redunWhere = this.getRedundancyWhere(this.getRedunMap());
      if (redunWhere != null && redunWhere.length() > 0) {
        whereSql = whereSql + redunWhere;
      }
      context.setAttribute(PUOrderQryInfoPara.PAYEXEC_WHERE, whereSql);
    }
    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql);
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    BaseQueryCondition result = (BaseQueryCondition) condition;
    ConditionVO[] vos = this.getDelegator().getGeneralCondtionVOs();
    context.setAttribute(PUOrderQryInfoPara.PAYEXEC_COND, vos);
    this.setMainOrgFilter(filter, context);
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
