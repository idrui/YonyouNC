package nc.ui.pu.m20.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-1-13 上午09:38:59
 * @author 高光荣
 */

public class PraybillQueryDLGInitializer implements
    IQueryConditionDLGInitializer {
  /** 采购组织fieldcode **/
  public static final String PK_PRAYBILL_B_PK_PURCHASEORG =
      "pk_praybill_b.pk_purchaseorg";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 按主组织过滤计划部门
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANDEPT);
    deptFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    deptFilter.addEditorListener();

    QDeptFilter deptFilter_v =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANDEPT_V);
    deptFilter_v.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    deptFilter_v.addEditorListener();

    QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
        "pk_praybill_b.pk_reqdept").addEditorListener();

    QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
        "pk_praybill_b.pk_reqdept_v").addEditorListener();

    // 按主组织过滤计划员
    QPsndocFilter planPsndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANPSN);
    planPsndocFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planPsndocFilter.addEditorListener();

    // 过滤请购类型
    new QTransTypeFilter(condDLGDelegator, POBillType.PrayBill.getCode())
        .filter();

    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator,
        "pk_praybill_b.pk_material.pk_marbasclass.code").addEditorListener();

    // 按主组织过滤物料
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material.name").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();
    // 按主组织过滤需求仓库
    new QWareHouseFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_reqstor").addEditorListener();

    // 按主组织过滤项目
    new QProjectFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.cprojectid").addEditorListener();

    // 按采购组织过滤建议供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            "pk_praybill_b.pk_suggestsupplier");
    supplierFilter
        .setPk_orgCode(PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG);
    supplierFilter.addEditorListener();

    // 按采购组织过滤采购员
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            "pk_praybill_b.pk_employee");
    psndocFilter
        .setPk_orgCode(PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG);
    psndocFilter.addEditorListener();

    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, "pk_praybill_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_praybill_b.pk_srcmaterial.code",
        "pk_praybill_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_praybill_b.pk_srcmaterial.code",
        "pk_praybill_b.cffileid.vskucode").addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PraybillHeaderVO.PK_ORG
    });
    // 档案使用权
    condDLGDelegator.setPowerEnable(true);
  }

}
