package nc.ui.pu.m20.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购订单执行查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class PrayBillDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // 请购部门，参照库存组织下的部门档案输入。
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator, PUEntity.M20_H_TABLE
            + "." + PraybillHeaderVO.PK_PLANDEPT);
    deptFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 请购员，参照请购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PUEntity.M20_H_TABLE + "." + PraybillHeaderVO.PK_PLANPSN);
    psnFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    psnFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_srcmaterial.code").addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_srcmaterial.name").addEditorListener();

    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_material.pk_marbasclass").addEditorListener();
    // 需求仓库
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
            "po_praybill_b.pk_reqstor");
    // 根据设置条件过滤仓库
    whfilter.filter();
    whfilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new PraybillHeaderVO(), new PraybillItemVO())));
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M20_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    customerFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilter(condDLGDelegator, PrayBillQryInfoPara.CMATERIALOID,
        "po_praybill_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, PrayBillQryInfoPara.CMATERIALOID,
        "po_praybill_b.cffileid.vskucode").addEditorListener();
    
    // 过滤请购类型
    QTransTypeFilter filter = new QTransTypeFilter(condDLGDelegator, POBillType.PrayBill.getCode());
    filter.setTransTypeCode("po_praybill.ctrantypeid");
    filter.filter();
  }
}
