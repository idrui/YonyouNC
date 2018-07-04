package nc.ui.pu.m21.billref.srm.m4s21;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 评估申请单参照订单查询条件
 * 
 * @since 6.31
 * @version 2014-3-26 下午03:10:00
 * @author zhangyhh
 */

@SuppressWarnings("restriction")
public class BillReferQueryFor4s21 extends DefaultBillReferQuery {

  public BillReferQueryFor4s21(Container c, TemplateInfo info) {
    super(c, info);
  }

  private void setupFiler(QueryConditionDLGDelegator dlgDelegator) {

    // 物料基本分类编码
    new QMarbasclassFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_material.pk_marbasclass").addEditorListener();

    // 物料编码
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_srcmaterial.code").addEditorListener();

    // 物料名称
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "pk_order_b.pk_srcmaterial.name").addEditorListener();

    // 交易类型，参照采购订单交易类型录入
    new QTransTypeFilter(dlgDelegator, POBillType.Order.getCode()).filter();

    // 供应商，参照采购组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, OrderItemVO.PK_SUPPLIER);
    qSupplierFilter.addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, OrderItemVO.PK_ORG, OrderItemVO.CPROJECTID)
        .addEditorListener();

    // 部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 人员，参照采购组织下的人员档案输入
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            OrderHeaderVO.CEMPLOYEEID);
    psndocFilter.addEditorListener();

    // 过滤客户辅助属性
    QCustomerFilter custFilter =
        new QCustomerFilter(dlgDelegator, OrderItemVO.CASSCUSTID);
    custFilter.setPk_orgCode(OrderItemVO.PK_ORG);
    custFilter.addEditorListener();

  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // 主组织权限
    // 转单查询的主组织支持功能权限的主组织权限，需要按照角色分配的组织过滤
    dlgDelegator.registerNeedPermissionOrgFieldCode(OrderHeaderVO.PK_ORG);

    // 增加 物料自由辅助属性处理类
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 档案使用权限
    dlgDelegator.setPowerEnable(true);

    // 设置参照过滤
    this.setupFiler(dlgDelegator);

  }
}
