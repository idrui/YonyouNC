package nc.ui.pu.m422x.report.action;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.ui.pu.m422x.report.dlg.StoreReqDetailsQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pu.report.scale.m422x.StoreReqDetailScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.scmpub.report.SCMReportQueryConUtil;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 物资需求申请明细查询按钮
 * 
 * @since 6.32
 * @version 2014-5-25 下午02:48:32
 * @author zhangyhh
 */

public class StoreReqDetailsQryAction extends PURptDefaultQueryAction {
  public static final String mainOrg = "pk_org";

  public static final String SRCMATERIAL = "po_storereq_b."
      + StoreReqAppItemVO.PK_SRCMATERIAL;

  /** 物料基本分类编码 **/
  public static final String THIS_PK_MATERIAL_PK_MARBASCLASS_CODE =
      "po_storereq_b.pk_srcmaterial.pk_marbasclass.code";

  /** 物料编码 **/
  public static final String THIS_PK_SRCMATERIAL_CODE =
      "po_storereq_b.pk_srcmaterial.code";

  /** 物料名称 **/
  public static final String THIS_PK_SRCMATERIAL_NAME =
      "po_storereq_b.pk_srcmaterial.name";

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
    return new StoreReqDetailsQryDlgInit();
  }

  private Map<String, String> getRedunMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(StoreReqAppHeaderVO.DBILLDATE, PUEntity.M422X_H_TABLE + "."
        + StoreReqAppItemVO.DBILLDATE);
    map.put(StoreReqDetailsQryAction.mainOrg, "po_storereq_b."
        + StoreReqAppItemVO.PK_ORG);
    return map;
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(PUPubMetaNameConst.THISSRCMATERIALCODE,
        StoreReqDetailsQryAction.SRCMATERIAL);
    columnMapping.put(PUPubMetaNameConst.THISSRCMATERIALNAME,
        StoreReqDetailsQryAction.SRCMATERIAL);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return StoreReqAppHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return StoreReqDetailsQryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new StoreReqDetailScaleStrategy();
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
