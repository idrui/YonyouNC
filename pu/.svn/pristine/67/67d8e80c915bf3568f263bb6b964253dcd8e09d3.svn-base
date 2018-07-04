package nc.ui.pu.m422x.billref.ic.m4d;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请提供给材料出库单的单据参照查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 上午11:25:38
 */
public class BillReferQueryFor4d extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public BillReferQueryFor4d(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      StoreReqAppHeaderVO.PK_ORG
    });
    // 档案使用权限
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 交易类型，参照交易类型录入
    new QTransTypeFilter(dlgDelegator, "pk_praybill_b.cordertrantypecode",
        POBillType.MRBill.getCode()).filter();

    // 需求仓库
    new QWareHouseFilter(dlgDelegator, "pk_org", "pk_storereq_b.pk_reqstordoc")
        .addEditorListener();

    // 申请部门，如果查询一个库存组织的数据，则只可参照该库存组织可见的部门档案，否则参照集团范围的部门档案录入。
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(dlgDelegator,
            "pk_storereq_b.pk_appdept");
    deptFilter.setPk_orgCode("pk_org");
    deptFilter.addEditorListener();

    // 申请人，如果查询一个库存组织的数据，则只可参照该库存组织可见的人员档案，否则参照集团范围的人员档案录入。
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            "pk_storereq_b.pk_apppsn");
    psndocFilter.setPk_orgCode("pk_org");
    psndocFilter.addEditorListener();

    // 物料基本分类编码，参照采购组织的物料基本分类
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator,
            "pk_storereq_b.pk_material.pk_marbasclass");
    qMarbasclassFilter.setPk_orgCode("pk_org");
    qMarbasclassFilter.addEditorListener();

    // 物料编码，参照采购组织可见的物料档案录入
    new QMarterialFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_material.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_material.name").addEditorListener();

    // 物料编码，参照采购组织可见的物料档案录入
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.name").addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, "pk_org", "pk_storereq_b.cprojectid")
        .addEditorListener();

  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue("pk_org", this.getDefaultPk_OrgValue());
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

}
