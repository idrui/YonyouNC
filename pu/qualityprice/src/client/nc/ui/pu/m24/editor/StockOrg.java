package nc.ui.pu.m24.editor;

import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存组织的编辑后事件类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-04 上午11:32:22
 */
public class StockOrg implements IOrgChangedEventListener {

  // 表体页签 优质优价信息
  public static final String BODY_HQHP = "body_hqhp";

  // 表体页签 物料信息
  public static final String BODY_MATERIAL = "body_material";

  @Override
  public void process(ShowUpableBillForm cardForm) {

    // 过滤参照
    this.filter(cardForm);

    // 设置精度
    new PricestlScaleUtil().orgChgScale(cardForm);

  }

  private void filter(ShowUpableBillForm editor) {
    // 取当前采购组织，如果采购组织为空，则不执行下面的过滤过程
    String pk_org = editor.getModel().getContext().getPk_org();

    if (pk_org == null) {
      return;
    }

    // 过滤供应商
    this.filterSupplier(editor, PricestlHeaderVO.PK_SUPPLIER, pk_org);

    // 过滤人员
    this.filterPsn(editor, pk_org);

    // 过滤部门
    this.filterDepartment(editor, pk_org);

    // 过滤物料
    this.filterMaterial(editor, pk_org);

    new PuVDefFilterUtil().setOrgForDefRef(editor.getBillCardPanel(), editor
        .getModel().getContext());

  }

  private void filterDepartment(IBillCardPanelEditor editor, String pk_org) {
    // 表头部门参照
    FilterDeptRefUtils filterv =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) editor
            .getBillCardPanel().getHeadTailItem(PricestlHeaderVO.PK_DEPT_V)
            .getComponent());
    filterv.filterItemRefByOrg(pk_org);
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) editor
            .getBillCardPanel().getHeadTailItem(PricestlHeaderVO.PK_DEPT)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterMaterial(IBillCardPanelEditor editor, String pk_org) {

    FilterMaterialRefUtils filter =
        new FilterMaterialRefUtils((UIRefPane) editor.getBillCardPanel()
            .getBodyItem(StockOrg.BODY_MATERIAL, PricestlItemVO.PK_MATERIAL)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterPsn(IBillCardPanelEditor editor, String pk_org) {
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU((UIRefPane) editor
            .getBillCardPanel().getHeadTailItem(PricestlHeaderVO.PK_EMPLOYEE)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterSupplier(IBillCardPanelEditor editor, String supplierItem,
      String pk_org) {
    FilterSupplierRefUtils filter =
        new FilterSupplierRefUtils(editor.getBillCardPanel().getHeadTailItem(
            supplierItem));
    filter.filterItemRefByOrg(pk_org);
  }
}
