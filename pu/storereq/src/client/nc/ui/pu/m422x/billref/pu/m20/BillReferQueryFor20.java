package nc.ui.pu.m422x.billref.pu.m20;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.querytemplate.TemplateInfo;

public class BillReferQueryFor20 extends DefaultBillReferQuery {

  public BillReferQueryFor20(Container c, TemplateInfo info) {
    super(c, info);
  }

  private void setupFiler(QueryConditionDLGDelegator dlgDelegator) {
    // 物料基本分类编码
    new QMarbasclassFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_material.pk_marbasclass").addEditorListener();

    // 物料编码
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.code").addEditorListener();

    // 物料名称
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_storereq_b.pk_srcmaterial.name").addEditorListener();

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

    // 项目
    new QProjectFilter(dlgDelegator, "pk_org", "pk_storereq_b.cprojectid")
        .addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      StoreReqAppHeaderVO.PK_ORG
    });

    // 档案使用权限
    dlgDelegator.setPowerEnable(true);

    // 设置参照过滤
    this.setupFiler(dlgDelegator);

    //
    // dlgDelegator.setDefaultValue("pk_org", this.getDefaultPk_OrgValue());
    // dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
