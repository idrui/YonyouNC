package nc.ui.pu.m23.billref.pu.m21;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给采购订单补货的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-5-14 下午03:28:46
 */
public class BillReferQueryFor21 extends DefaultBillReferQuery {

  // private QueryConditionDLG queryDlg;

  public BillReferQueryFor21(Container c, TemplateInfo info) {
    super(c, info);

    // this.initialize();
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.PURCHASEORGTYPE)) {
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
      ArriveHeaderVO.PK_PURCHASEORG
    });
    // 档案使用权限
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 设置交易类型参照范围
    new QTransTypeFilter(dlgDelegator, POBillType.Arrive.getCode()).filter();

    // 按库存组织过滤仓库
    new QWareHouseFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_receivestore").addEditorListener();

    // 按主组织过滤物料基本分类
    String marbascalss = "pk_arriveorder_b.pk_material.pk_marbasclass.code";
    new QMarbasclassFilter(dlgDelegator, "pk_purchaseorg", marbascalss)
        .addEditorListener();
    // 按主组织过滤物料采购分类
    String marpuclass =
        "pk_arriveorder_b.pk_material.materialstock.pk_marpuclass.code";
    QMarPuClassFilter puclassFilter =
        new QMarPuClassFilter(dlgDelegator, marpuclass);
    puclassFilter.setPk_orgCode("pk_purchaseorg");
    puclassFilter.addEditorListener();
    // 按主组织过滤物料
    new QMarterialFilter(dlgDelegator, "pk_purchaseorg",
        "pk_arriveorder_b.pk_material.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_purchaseorg",
        "pk_arriveorder_b.pk_material.name").addEditorListener();

    new QMarterialFilter(dlgDelegator, "pk_purchaseorg",
        "pk_arriveorder_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_purchaseorg",
        "pk_arriveorder_b.pk_srcmaterial.name").addEditorListener();
    // 项目
    new QProjectFilter(dlgDelegator, "pk_purchaseorg",
        "pk_arriveorder_b.cprojectid").addEditorListener();
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, ArriveHeaderVO.PK_DEPT);
    deptFilter.setPk_orgCode("pk_purchaseorg");
    deptFilter.addEditorListener();

    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            ArriveHeaderVO.PK_PUPSNDOC);
    psnFilter.setPk_orgCode("pk_purchaseorg");
    psnFilter.addEditorListener();

    // 供应商
    QSupplierFilter supcode =
        new QSupplierFilter(dlgDelegator, "pk_supplier.code");
    supcode.setPk_orgCode("pk_purchaseorg");
    supcode.addEditorListener();
    QSupplierFilter supname =
        new QSupplierFilter(dlgDelegator, "pk_supplier.name");
    supname.setPk_orgCode("pk_purchaseorg");
    supname.addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, ArriveItemVO.PK_ARRIVEORDER_B
        + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name())
        .addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue(ArriveHeaderVO.PK_PURCHASEORG,
        this.getDefaultPk_OrgValue());
    dlgDelegator.setDefaultValue("pk_org", null);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
