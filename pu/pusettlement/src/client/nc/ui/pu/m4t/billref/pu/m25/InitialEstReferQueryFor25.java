/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 上午10:01:06
 */
package nc.ui.pu.m4t.billref.pu.m25;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单提供给采购发票的查询服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 上午10:01:06
 */
public class InitialEstReferQueryFor25 extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public InitialEstReferQueryFor25(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.FINANCEORGTYPE)) {
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
      InitialEstHeaderVO.PK_ORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 物料编码
    new QMarterialFilter(dlgDelegator, "pk_org",
        "po_initialest_b.pk_material.code").addEditorListener();
    new QMarterialoidFilter(dlgDelegator, "pk_org",
        "po_initialest_b.pk_srcmaterial.code").addEditorListener();

    // 供应商
    new QSupplierFilter(dlgDelegator, "pk_supplier").addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(dlgDelegator, "po_initialest_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
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
