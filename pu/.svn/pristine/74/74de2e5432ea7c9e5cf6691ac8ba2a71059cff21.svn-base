package nc.ui.pu.m21.report.action;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.m21.report.dlg.OrderDetailsQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pu.report.scale.m21.OrderDetailsScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 订单明细查询按钮
 * 
 * @since 6.0
 * @version 2011-3-10 下午02:48:32
 * @author wuxla
 */

public class OrderDetailsQryAction extends PURptDefaultQueryAction {
  public static final String mainOrg = "pk_org";

  public static final String SRCMATERIAL = "pk_order_b."
      + OrderItemVO.PK_SRCMATERIAL;

  /** 物料采购分类编码 **/
  public static final String THIS_PK_MATERIAL_MATERIALSTOCK_PK_MARPUCLASS_CODE =
      "this.pk_material.materialstock.pk_marpuclass.code";

  /** 物料基本分类编码 **/
  public static final String THIS_PK_MATERIAL_PK_MARBASCLASS_CODE =
      "this.pk_material.pk_marbasclass.code";

  /** 物料编码 **/
  public static final String THIS_PK_SRCMATERIAL_CODE =
      "this.pk_srcmaterial.code";

  /** 物料名称 **/
  public static final String THIS_PK_SRCMATERIAL_NAME =
      "this.pk_srcmaterial.name";

  /** 供应商编码 **/
  public static final String THIS_PK_SUPPLIER_CODE = "this.pk_supplier.code";

  /** 供应商名称 **/
  public static final String THIS_PK_SUPPLIER_NAME = "this.pk_supplier.name";

  /** 供应商地区分类 **/
  public static final String THIS_PK_SUPPLIER_PK_AREACL =
      "this.pk_supplier.pk_areacl";

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.setMapRedundancy(this.getRedunMap());
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new OrderDetailsQryDlgInit();
  }

  private Map<String, String> getRedunMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(OrderHeaderVO.DBILLDATE, PUEntity.M21_B_TABLE + "."
        + OrderItemVO.DBILLDATE);
    map.put(OrderDetailsQryAction.mainOrg, "po_order_b." + OrderItemVO.PK_ORG);
    return map;
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PUPubMetaNameConst.THISSRCMATERIALCODE,
        OrderDetailsQryAction.SRCMATERIAL);
    columnMapping.put(PUPubMetaNameConst.THISSRCMATERIALNAME,
        OrderDetailsQryAction.SRCMATERIAL);
    columnMapping.put(PUPubMetaNameConst.THISSUPPLIERCODE,
        OrderHeaderVO.PK_SUPPLIER);
    columnMapping.put(PUPubMetaNameConst.THISSUPPLIERNAME,
        OrderHeaderVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return OrderHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return OrderDetailsQryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new OrderDetailsScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    // 获取查询模板条件
    String whereSql = queryDlg.getWhereSQL();
    if (whereSql != null && whereSql.length() > 0) {
      String redunWhere = this.getRedundancyWhere(this.getRedunMap());
      if (redunWhere != null && redunWhere.length() > 0) {
        whereSql = whereSql + redunWhere;
      }
    }
    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setExpression(whereSql);
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    // PUOrderQryInfoPara para = new PUOrderQryInfoPara();
    BaseQueryCondition result = (BaseQueryCondition) condition;
    result.setDescriptors(new Descriptor[] {
      filter
    });
    // context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    // result.setRoportAdjustor(new PuReportAdjustor());
    this.setMainOrgFilter(filter, context);
    return result;

  }
}
