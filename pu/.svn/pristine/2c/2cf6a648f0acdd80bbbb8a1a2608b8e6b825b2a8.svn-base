/**
 * 
 */
package nc.ui.pu.m20.query;

import nc.ui.pu.m21.action.status.StatusQueryFieldValueElementEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-11 下午4:50:44
 */
public class PrayarrangeQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  /*
   * 父类方法重写
   * @see
   * nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer#initQueryConditionDLG
   * (nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator)
   */
  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    this.setFieldValueElementEditor(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // 按主组织过滤物料基本分类
    new QMarbasclassFilter(condDLGDelegator,
        "pk_praybill_b.pk_material.pk_marbasclass.code").addEditorListener();

    // 按主组织过滤物料
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material.name").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();

    // 按主组织过滤项目
    new QProjectFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.cprojectid").addEditorListener();

    new QProjectFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        PraybillItemVO.PK_PURCHASEORG).addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PraybillHeaderVO.PK_ORG
    });
    // 档案使用权
    condDLGDelegator.setPowerEnable(true);
  }

  /**
   * 请购安排改为只有是否两个值
   * 
   * @param condDLGDelegator
   */
  private void setFieldValueElementEditor(
      QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.setFieldValueElementEditor("", "pk_praybill_b."
        + PraybillItemVO.BISARRANGE,
        new StatusQueryFieldValueElementEditorCreator());
  }

}
