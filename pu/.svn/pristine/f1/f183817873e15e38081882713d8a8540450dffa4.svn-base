package nc.ui.pu.m422x.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-1-13 上午08:37:14
 * @author wuxla
 */

public class StoreReqQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 物资需求申请类型
    new QTransTypeFilter(condDLGDelegator, POBillType.MRBill.getCode())
        .filter();

    // 物料基本分类编码
    new QMarbasclassFilter(condDLGDelegator,
        "pk_storereq_b.pk_material.pk_marbasclass.code").addEditorListener();

    // 物料编码
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "pk_storereq_b.pk_material.code").addEditorListener();

    // 物料名称
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "pk_storereq_b.pk_material.name").addEditorListener();

    // 物料编码
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.code").addEditorListener();

    // 物料名称
    new QMarterialFilter(condDLGDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.name").addEditorListener();

    // 需求仓库
    new QWareHouseFilter(condDLGDelegator, "pk_org",
        "pk_storereq_b.pk_reqstordoc").addEditorListener();

    // 申请部门
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            "pk_storereq_b.pk_appdept");
    deptFilter.setPk_orgCode(StoreReqAppHeaderVO.PK_ORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 申请人
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            "pk_storereq_b.pk_apppsn");
    psnFilter.setPk_orgCode(StoreReqAppHeaderVO.PK_ORG);
    psnFilter.addEditorListener();

    // 项目
    new QProjectFilter(condDLGDelegator, "pk_org", "pk_storereq_b.cprojectid")
        .addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      StoreReqAppHeaderVO.PK_ORG
    });
    // 档案使用权
    condDLGDelegator.setPowerEnable(true);
  }

}
