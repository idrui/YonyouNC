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
import nc.ui.pu.m21.report.dlg.OrderExecQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.adjustor.OrderExecDefaultAdjustor;
import nc.vo.pu.report.enumeration.OrderExeStatType;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pu.report.scale.m21.OrderExecScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * @since 6.0
 * @version 2011-3-18 下午02:03:22
 * @author wangfengd
 */
public class OrderExecQryAction extends PURptDefaultQueryAction {

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
      this.setContextContent(context, vos);
    }

    ICReportConditionUtils.setDescription(context, queryScheme);

    IQueryCondition condition = this.createQueryCondition(context);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new OrderExecQryDlgInit();
  }

  private void setContextContent(IContext context, ConditionVO[] vos) {
    List<ConditionVO> list = new ArrayList<ConditionVO>();
    List<ConditionVO> logicList = new ArrayList<ConditionVO>();
    for (ConditionVO vo : vos) {
      String code = vo.getFieldCode();
      if (PUOrderQryInfoPara.STATTYPE.equals(code)
          || PUOrderQryInfoPara.STATCONTENT.equals(code)) {
        logicList.add(vo);
        continue;
      }
      list.add(vo);
    }
    ConditionVO[] conds = list.toArray(new ConditionVO[list.size()]);
    context.setAttribute(PUOrderQryInfoPara.EXEC_COND, conds);

    ConditionVO[] logicConds =
        logicList.toArray(new ConditionVO[logicList.size()]);
    for (ConditionVO logicCond : logicConds) {
      String code = logicCond.getFieldCode();
      String value = logicCond.getValue();
      if (PUOrderQryInfoPara.STATTYPE.equals(code)) {
        context.setAttribute(PUOrderQryInfoPara.STATTYPE,
            Integer.valueOf(value));
        // 采购订单行执行汇总
        if (OrderExeStatType.EXECGATHER.toInt() == Integer.valueOf(value)
            .intValue()) {
          context.setAttribute(PUOrderQryInfoPara.GATHERNOSHOWPARA,
              PUOrderQryInfoPara.GATHERNOSHOW);
        }
        else {
          context.setAttribute(PUOrderQryInfoPara.GATHERNOSHOWPARA,
              new String[0]);
        }
      }
      else if (PUOrderQryInfoPara.STATCONTENT.equals(code)) {
        context.setAttribute(PUOrderQryInfoPara.STATCONTENT, value);
      }
    }
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_CODE,
        OrderExecQryAction.SRCMATERIAL);
    columnMapping.put(PUOrderQryInfoPara.BD_MATERIAL_V_NAME,
        OrderExecQryAction.SRCMATERIAL);
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
    return OrderExecQryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new OrderExecScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
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
