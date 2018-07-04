package nc.ui.pu.report.puArrive;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.impl.pubapp.env.BSContext;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.report.adjustor.PuReportAdjustor;
import nc.vo.pu.report.enumeration.PuArrivalGroupEnum;
import nc.vo.pu.report.pub.smart.model.descriptor.PuAggrDescriptor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.arrival.PuArrivalQryInfoPara;
import nc.vo.pu.report.scale.m23.ArriveQueryScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.querytemplate.QueryTemplateConvertor;

import org.apache.commons.lang.ArrayUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 到货单查询
 * 
 * @since 6.0
 * @version 2011-3-10 上午09:14:02
 * @author yinfy
 */

public class PuArriveQueryAction extends PURptDefaultQueryAction {

  public static final String CMATERIALOID =
      "po_arriveorder_b.pk_srcmaterial.code";

  public static final String CMATERIALOID_NAME =
      "po_arriveorder_b.pk_srcmaterial.name";

  public static final String CVENDORID = "po_arriveorder.pk_supplier";

  public static final String CVENDORID_NAME = "puarrival.suppliername";

  public static final String GROUPCONDITION = "groupcondition";

  public static final String mainOrg = "po_arriveorder.pk_org";

  public static final String PUARRIVAL_PK_AREACL = "puarrival.pk_areacl";

  public static final String PUARRIVAL_PK_MARBASCLASS =
      "po_arriveorder_b.pk_material.pk_marbasclass";

  public static final String SRCMATERIAL = "pk_arriveorder_b."
      + OrderItemVO.PK_SRCMATERIAL;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    if (queryScheme == null) {
      return new BaseQueryCondition(false);
    }
    // 权限设置
    this.setRptPermission(context);
    SqlBuilder whereSql = new SqlBuilder();
    // 获取查询模板条件
    String dlgWhereSql = queryScheme.getWhereSQLOnly();
    whereSql.append(dlgWhereSql);
    whereSql.append(" and (po_arriveorder.pk_group='");
    whereSql.append(new BSContext().getGroupID());
    whereSql.append("')");

    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql.toString());
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    PuArrivalQryInfoPara para = new PuArrivalQryInfoPara();
    Descriptor[] desccriptors = null;
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] vos =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      List<ConditionVO> logicList = new ArrayList<ConditionVO>();
      for (ConditionVO vo : vos) {
        String code = vo.getFieldCode();
        if (PuArriveQueryAction.GROUPCONDITION.equals(code)) {
          logicList.add(vo);
        }
      }
      ConditionVO[] logicConds =
          logicList.toArray(new ConditionVO[logicList.size()]);
      if (ArrayUtils.isEmpty(logicConds)
          || logicConds[0].getValue().equals(
              PuArrivalGroupEnum.ARRIVEDETAIL.value().toString())) {
        // 明细查询
        para.setGroupcondtion(null);
        desccriptors = new Descriptor[] {
          filter
        };
      }
      else {
        // 汇总查询
        para.setGroupcondtion(logicConds[0].getValue());
        desccriptors = new Descriptor[] {
          filter, new PuAggrDescriptor<PuArrivalQryInfoPara>(para)
        };
      }
      context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    }
    ICReportConditionUtils.setDescription(context, queryScheme);
    ReportQueryCondition condition =
        (ReportQueryCondition) this.createQueryCondition(context);
    condition.setDescriptors(desccriptors);
    condition.setRoportAdjustor(new PuReportAdjustor());
    this.setCondOrgAttribute(context, queryScheme);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new PuArriveQueryDlgInit();
  }

  private void setContextContent(IContext context, ConditionVO[] vos) {

  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PuArriveQueryAction.CMATERIALOID,
        PuArriveQueryAction.SRCMATERIAL);
    columnMapping.put(PuArriveQueryAction.CMATERIALOID_NAME,
        PuArriveQueryAction.SRCMATERIAL);
    columnMapping
        .put(PuArriveQueryAction.CVENDORID, ArriveHeaderVO.PK_SUPPLIER);
    columnMapping.put(PuArriveQueryAction.CVENDORID_NAME,
        ArriveHeaderVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return ArriveHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return PuArriveQueryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new ArriveQueryScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    SqlBuilder whereSql = new SqlBuilder();
    // 获取查询模板条件
    String dlgWhereSql = queryDlg.getWhereSQL();
    whereSql.append(dlgWhereSql);
    BaseQueryCondition result = (BaseQueryCondition) condition;
    whereSql.append(" and (po_arriveorder.pk_group='");
    whereSql.append(new BSContext().getGroupID());
    whereSql.append("')");

    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql.toString());
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    PuArrivalQryInfoPara para = new PuArrivalQryInfoPara();
    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    if (ArrayUtils.isEmpty(conds)
        || conds[0].getValue().equals(
            PuArrivalGroupEnum.ARRIVEDETAIL.value().toString())) {
      // 明细查询
      para.setGroupcondtion(null);
      result.setDescriptors(new Descriptor[] {
        filter
      });
    }
    else {
      // 汇总查询
      para.setGroupcondtion(conds[0].getValue());
      result.setDescriptors(new Descriptor[] {
        filter, new PuAggrDescriptor<PuArrivalQryInfoPara>(para)
      });
    }
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    result.setRoportAdjustor(new PuReportAdjustor());
    this.setMainOrgFilter(filter, context);
    this.setCondOrgAttribute(context);
    return result;
  }

}
