package nc.ui.pu.m21.report.action;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.m21.report.dlg.ReceivePlansQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.adjustor.OrderExecDefaultAdjustor;
import nc.vo.pu.report.enumeration.ReceivePlanEnum;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.scale.m21.ReceivePlanScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 到货计划查询
 * 
 * @since 6.0
 * @version 2011-3-23 上午10:26:43
 * @author wangfengd
 */
public class ReceivePlansQryAction extends PURptDefaultQueryAction {

  public static final String mainOrg = "po_order.pk_org";

  public static final String NAUF = "NaUF";

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
      this.setContextContent(context, vos);
    }

    String whereSql = queryScheme.getWhereSQLOnly();
    if (whereSql != null && whereSql.length() > 0) {
      SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
      String rduncysql = conutil.getQueryWhereSqlAfterAddReduncy(queryScheme);
      if (rduncysql != null && rduncysql.length() > 0) {
        whereSql = whereSql + rduncysql;
      }
      context.setAttribute(PUOrderQryInfoPara.RP_WHERE, whereSql);
    }

    ICReportConditionUtils.setDescription(context, queryScheme);

    IQueryCondition condition = this.createQueryCondition(context);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new ReceivePlansQryDlgInit();
  }

  private void setContextByGroupCond(IContext context, ConditionVO vo) {
    Integer value = Integer.valueOf(vo.getValue());
    context.setAttribute(PUOrderQryInfoPara.RP_GROUPCONDITION, value);
    // 执行汇总
    if (ReceivePlanEnum.PLAN_GATHER.toInt() == value.intValue()) {
      context.setAttribute(PUOrderQryInfoPara.GATHERNOSHOWPARA,
          PUOrderQryInfoPara.RP_GATHERNOSHOW);
    }
    else {
      context.setAttribute(PUOrderQryInfoPara.GATHERNOSHOWPARA, new String[0]);
    }
  }

  private void setContextContent(IContext context, ConditionVO[] vos) {
    List<ConditionVO> list = new ArrayList<ConditionVO>();
    boolean finishrec = false;
    for (ConditionVO vo : vos) {
      String code = vo.getFieldCode();
      if (PUOrderQryInfoPara.RP_GROUPCONDITION.equals(code)) {
        this.setContextByGroupCond(context, vo);
        continue;
      }
      else if (PUOrderQryInfoPara.RP_FINISHRECV.equals(code)) {
        UFBoolean bfinish = UFBoolean.valueOf(vo.getValue());
        context.setAttribute(PUOrderQryInfoPara.RP_FINISHRECV, bfinish);
        finishrec = true;
        continue;
      }
      list.add(vo);
    }
    if (!finishrec) {
      context.setAttribute(PUOrderQryInfoPara.RP_FINISHRECV,
          ReceivePlansQryAction.NAUF);
    }
    ConditionVO[] conds = list.toArray(new ConditionVO[list.size()]);
    context.setAttribute(PUOrderQryInfoPara.RP_COND, conds);
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_CODE,
        ReceivePlansQryAction.SRCMATERIAL);
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_NAME,
        ReceivePlansQryAction.SRCMATERIAL);
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
    return ReceivePlansQryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new ReceivePlanScaleStrategy();
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
      Map<String, String> map = new HashMap<String, String>();
      map.put(PUEntity.M21_H_TABLE + "." + OrderHeaderVO.DBILLDATE,
          PUEntity.M21_B_TABLE + "." + OrderItemVO.DBILLDATE);
      map.put(ReceivePlansQryAction.mainOrg, "po_order_b." + OrderItemVO.PK_ORG);
      String redunWhere = this.getRedundancyWhere(map);
      if (redunWhere != null && redunWhere.length() > 0) {
        whereSql = whereSql + redunWhere;
      }
      context.setAttribute(PUOrderQryInfoPara.RP_WHERE, whereSql);
    }
    BaseQueryCondition result = (BaseQueryCondition) condition;
    ConditionVO[] conds = this.getDelegator().getGeneralCondtionVOs();
    //
    this.setContextContent(context, conds);

    OrderExecDefaultAdjustor adjustor = new OrderExecDefaultAdjustor();
    result.setRoportAdjustor(adjustor);
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
