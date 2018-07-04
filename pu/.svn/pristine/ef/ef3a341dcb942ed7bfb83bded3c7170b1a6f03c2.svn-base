package nc.ui.pu.m422x.report.dlg;

import nc.ui.pu.m422x.report.action.StoreReqDetailsQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 物资需求申请执行查询模板参照过滤器
 * 
 * @since 6.32
 * @version 2014-5-26 上午9:56:39
 * @author zhangyhh
 */
public class StoreReqDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // 申请部门，参照库存组织下的部门档案输入。
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PUEntity.M422X_B_TABLE + "." + StoreReqAppItemVO.PK_APPDEPT);
    deptFilter.setPk_orgCode(StoreReqDetailsQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 申请人，参照请购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PUEntity.M422X_B_TABLE + "." + StoreReqAppItemVO.PK_APPPSN);
    psnFilter.setPk_orgCode(StoreReqDetailsQryAction.mainOrg);
    psnFilter.addEditorListener();

    QMarterialoidFilter filter =
        new QMarterialoidFilter(condDLGDelegator,
            StoreReqDetailsQryAction.mainOrg,
            StoreReqDetailsQryAction.THIS_PK_SRCMATERIAL_CODE);
    // 物料编码(OID)
    filter.addEditorListener();

    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, StoreReqDetailsQryAction.mainOrg,
        StoreReqDetailsQryAction.THIS_PK_SRCMATERIAL_NAME).addEditorListener();

    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator, StoreReqDetailsQryAction.mainOrg,
        "bd_marbasclass.code").addEditorListener();
    // 需求仓库
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator,
            StoreReqDetailsQryAction.mainOrg, "po_storereq_b.pk_reqstordoc");
    // 根据设置条件过滤仓库
    whfilter.filter();
    whfilter.addEditorListener();
    // 项目
    new QProjectFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_storereq.pk_project").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 物资需求申请类型
    new QTransTypeFilter(condDLGDelegator, POBillType.MRBill.getCode())
        .filter();

    // // 自定义属性
    // condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
    // .getQueryParams(new PraybillHeaderVO(), new PraybillItemVO())));

  }
}
