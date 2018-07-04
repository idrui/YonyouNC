package nc.ui.pu.m20.billref.pu.m29;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单提供给询报价单的查询类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-10 下午06:19:55
 */
public class BillReferQueryFor29 extends DefaultBillReferQuery {

  public BillReferQueryFor29(Container c, TemplateInfo info) {
    super(c, info);

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
      PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG
    });
    // 档案使用权限
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 计划部门，如果查询一个库存组织的数据，则只可参照该库存组织可见的部门档案，否则参照集团范围的部门档案录入。
    QDeptFilter planDeptFilter =
        QDeptFilter.createDeptFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANDEPT);
    planDeptFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planDeptFilter.addEditorListener();

    // 计划员，如果查询一个库存组织的数据，则只可参照该库存组织可见的人员档案，否则参照集团范围的人员档案录入。
    QPsndocFilter planPsndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANPSN);
    planPsndocFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planPsndocFilter.addEditorListener();

    // 请购类型，参照请购单交易类型录入
    new QTransTypeFilter(dlgDelegator, "ctrantypeid",
        POBillType.PrayBill.getCode()).filter();

    // 物料基本分类编码，参照采购组织的物料基本分类
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator,
            "pk_praybill_b.pk_material.pk_marbasclass.code");
    qMarbasclassFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    qMarbasclassFilter.addEditorListener();

    // 物料编码，参照采购组织可见的物料档案录入
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();

    // 项目
    new QProjectFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.cprojectid").addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, "pk_praybill_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 组织权限过滤
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue("pk_org", null);
    dlgDelegator.setDefaultValue("pk_praybill_b.pk_purchaseorg",
        this.getDefaultPk_OrgValue());
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
