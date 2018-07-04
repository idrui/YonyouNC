package nc.ui.pu.m21.report.action;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.m21.report.dlg.OrderOnwayQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.scale.m21.OrderOnwayScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 采购订单在途查询类。
 * 
 * @since 6.0
 * @version 2012-9-18 上午10:48:07
 * @author lixyp
 */
public class OrderOnwayQryAction extends PURptDefaultQueryAction {

  /** 物料编码 **/
  private static final String BD_MATERIAL_CODE =
      "po_order_b.pk_srcmaterial.code";

  /** 物料名称 **/
  private static final String BD_MATERIAL_NAME =
      "po_order_b.pk_srmaterial.name";

  /** 供应商编码 **/
  private static final String BD_SUPPLIER_CODE = "po_order_b.pk_supplier.code";

  /** 供应商名称 **/
  private static final String BD_SUPPLIER_NAME = "po_order_b.pk_supplier.name";

  /** 物料元数据全路径 */
  private static final String MATERAIL_META_PATH = "pk_order_b.pk_srcmaterial";

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    this.setCondOrgAttribute(context, queryScheme);
    if (queryScheme == null) {
      return new BaseQueryCondition(false);
    }

    String whereSql = queryScheme.getWhereSQLOnly();
    if (whereSql != null && whereSql.length() > 0) {
      SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
      String rduncysql = conutil.getQueryWhereSqlAfterAddReduncy(queryScheme);
      if (rduncysql != null && rduncysql.length() > 0) {
        whereSql = whereSql + rduncysql;
      }
      context.setAttribute("#where#", whereSql);
    }

    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      ConditionVO[] conds =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      for (ConditionVO cond : conds) {
        cond.getFieldCode().equals("po_order.pk_org");
        context.setAttribute("#pk_org#", cond.getValue());
        context.setAttribute("#orgOperCode#", cond.getOperaCode());
        break;
      }

      context.setAttribute(PuQueryInfoPara.QUERY_CONDS, conds);
    }

    ICReportConditionUtils.setDescription(context, queryScheme);

    IQueryCondition condition = this.createQueryCondition(context);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new OrderOnwayQryDlgInit();
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    // key=查询模板 value=元数据全路径
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(OrderOnwayQryAction.BD_MATERIAL_CODE,
        OrderOnwayQryAction.MATERAIL_META_PATH);
    columnMapping.put(OrderOnwayQryAction.BD_MATERIAL_NAME,
        OrderOnwayQryAction.MATERAIL_META_PATH);
    columnMapping.put(OrderOnwayQryAction.BD_SUPPLIER_CODE,
        OrderHeaderVO.PK_SUPPLIER);
    columnMapping.put(OrderOnwayQryAction.BD_SUPPLIER_NAME,
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
    return new OrderOnwayScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }

    ConditionVO[] conds = this.getDelegator().getGeneralCondtionVOs();
    for (ConditionVO cond : conds) {
      cond.getFieldCode().equals("po_order.pk_org");
      context.setAttribute("#pk_org#", cond.getValue());
      context.setAttribute("#orgOperCode#", cond.getOperaCode());
      break;
    }

    context.setAttribute(PuQueryInfoPara.QUERY_CONDS, conds);
    // 将where条件放入上下文中，传到后台。
    this.setCondOrgAttribute(context);
    return condition;
  }
}
