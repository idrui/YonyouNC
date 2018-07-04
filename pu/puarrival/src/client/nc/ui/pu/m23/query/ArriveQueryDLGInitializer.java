package nc.ui.pu.m23.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 到货单查询初始化
 * 
 * @since 6.0
 * @version 2011-1-13 上午11:33:48
 * @author yinfy
 */

public class ArriveQueryDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置交易类型参照范围
    new QTransTypeFilter(condDLGDelegator, POBillType.Arrive.getCode())
        .filter();

    // 按库存组织过滤仓库
    new QWareHouseFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_receivestore").addEditorListener();

    // 按主组织过滤物料基本分类
    String marbascalss = "pk_arriveorder_b.pk_material.pk_marbasclass.code";
    new QMarbasclassFilter(condDLGDelegator, marbascalss).addEditorListener();

    // 按主组织过滤物料采购分类
    String marpuclass =
        "pk_arriveorder_b.pk_material.materialstock.pk_marpuclass.code";
    new QMarPuClassFilter(condDLGDelegator, marpuclass).addEditorListener();
    // 按主组织过滤物料
    new QMarterialFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_material.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_material.name").addEditorListener();
    new QMarterialFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_srcmaterial.name").addEditorListener();

    // 项目
    new QProjectFilter(condDLGDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.cprojectid").addEditorListener();

    // 供应商
    QSupplierFilter filter1 =
        new QSupplierFilter(condDLGDelegator, "pk_supplier.code");
    filter1.setIsAlwaysShowAllData(UFBoolean.TRUE);
    filter1.addEditorListener();
    QSupplierFilter filter2 =
        new QSupplierFilter(condDLGDelegator, "pk_supplier.name");
    filter2.setIsAlwaysShowAllData(UFBoolean.TRUE);
    filter2.addEditorListener();

    // 采购员(人员) -- 根据需求查看集团的所有人员
    QPsndocFilter qpsnfilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator, "pk_pupsndoc");
    qpsnfilter.setPk_orgCode(null);
    qpsnfilter.addEditorListener();

    // 部门 -- 根据需求查看集团的所有部门
    QDeptFilter qdeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, "pk_dept");
    qdeptfilter.setPk_orgCode(null);
    qdeptfilter.addEditorListener();
    
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, ArriveItemVO.PK_ARRIVEORDER_B
        + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name())
        .addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_arriveorder_b.pk_srcmaterial.code",
        "pk_arriveorder_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_arriveorder_b.pk_srcmaterial.code",
        "pk_arriveorder_b.cffileid.vskucode").addEditorListener();
    
    // 过滤请购类型
    new QTransTypeFilter(condDLGDelegator, POBillType.Arrive.getCode())
        .filter();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      ArriveHeaderVO.PK_ORG
    });
    // 档案使用权
    condDLGDelegator.setPowerEnable(true);
  }

}
