package nc.ui.pu.est.query;

import nc.ui.pu.est.view.EstQueryComboEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @since 6.0
 * @version 2011-1-17 下午04:33:20
 * @author yinfy
 */

public class VMIEstQueryDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.init(condDLGDelegator);
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      VmiFIHeaderVO.PK_FINANCEORG
    });
    // 不支持数据权限
    condDLGDelegator.setPowerEnable(false);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  protected void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置交易类型参照范围
    new QTransTypeFilter(condDLGDelegator, ICBillType.VmiSum.getCode())
        .filter();
    String fiorgcode = GoodsEstVO.PK_FINANCEORG;
    String matrlcode = GoodsEstVO.PK_MATERIAL;
    String srcmatrlcode = GoodsEstVO.PK_SRCMATERIAL;
    // 按财务组织过滤物料基本分类
    String filtercode = matrlcode + ".pk_marbasclass";
    QMarbasclassFilter mbclf =
        new QMarbasclassFilter(condDLGDelegator, filtercode);
    mbclf.setPk_orgCode(fiorgcode);
    mbclf.addEditorListener();
    // 按财务组织过滤物料采购分类
    filtercode = matrlcode + ".materialstock.pk_marpuclass";
    QMarPuClassFilter mpclf =
        new QMarPuClassFilter(condDLGDelegator, filtercode);
    mpclf.setPk_orgCode(fiorgcode);
    mpclf.addEditorListener();
    // 按主组织过滤物料
    filtercode = matrlcode;
    new QMarterialFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    filtercode = srcmatrlcode;
    new QMarterialFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    // 项目
    filtercode =
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.CPROJECTID;
    new QProjectFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    // 供应商
    filtercode = GoodsEstVO.PK_SUPPLIER;
    QSupplierFilter supf = new QSupplierFilter(condDLGDelegator, filtercode);
    supf.setPk_orgCode(fiorgcode);
    supf.addEditorListener();
    // 按库存组织过滤仓库
    filtercode = VmiFIHeaderVO.PK_STORDOC;
    new QWareHouseFilter(condDLGDelegator, VmiFIHeaderVO.PK_STOREORG,
        filtercode).addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_srcmaterial.code",
        "cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_srcmaterial.code",
        "cffileid.vskucode").addEditorListener();
  }

  protected void init(QueryConditionDLGDelegator condDLGDelegator) {
    // 编辑器
    IFieldValueElementEditorCreator comboCrt = new EstQueryComboEditorCreator();
    condDLGDelegator.setFieldValueElementEditor(null,
        QueryNonMetaFieldCode.bestfee.name(), comboCrt);
    condDLGDelegator.setFieldValueElementEditor(null,
        QueryNonMetaFieldCode.festtype.name(), comboCrt);
  }
}
