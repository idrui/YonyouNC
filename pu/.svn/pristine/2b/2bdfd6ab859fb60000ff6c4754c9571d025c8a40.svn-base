/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 下午06:57:07
 */
package nc.ui.pu.m422x.rule;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 下午06:57:07
 */
public class ReferenceFilterByOrg {
  private IBillCardPanelEditor editor;

  public ReferenceFilterByOrg(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void filter() {
    // 取当前采购组织，如果采购组织为空，则不执行下面的过滤过程
    String pk_org =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();
    if (null == pk_org) {
      ShowUpableBillForm form = (ShowUpableBillForm) this.editor;
      pk_org = form.getModel().getContext().getPk_org();
    }

    // 过滤人员
    this.filterPsn(this.editor, pk_org);
    // 过滤部门
    this.filterDepartment(this.editor, pk_org);

    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    // 过滤物料并允许多选
    this.filterMaterial(this.editor, pk_org);

    // 过滤项目
    this.filterProject(this.editor, pk_org);

    // 过滤仓库
    this.filterStordoc(this.editor, pk_org);

    // 过滤地点
    // this.filterAddress(this.editor, pk_org);

    // 供应商
    this.filterSupplier(this.editor, pk_org);
  }

  private void filterDepartment(IBillCardPanelEditor panelEditor, String pk_org) {
    // 表头部门参照
    FilterDeptRefUtils headFilterv =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC((UIRefPane) panelEditor
            .getBillCardPanel()
            .getHeadTailItem(StoreReqAppHeaderVO.PK_APPDEPTH_V).getComponent());
    headFilterv.filterItemRefByOrg(pk_org);
    FilterDeptRefUtils headFilter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC((UIRefPane) panelEditor
            .getBillCardPanel()
            .getHeadTailItem(StoreReqAppHeaderVO.PK_APPDEPTH).getComponent());
    headFilter.filterItemRefByOrg(pk_org);

    FilterDeptRefUtils itemFilterv =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC((UIRefPane) panelEditor
            .getBillCardPanel().getBodyItem(StoreReqAppItemVO.PK_APPDEPT_V)
            .getComponent());
    itemFilterv.filterItemRefByOrg(pk_org);
    FilterDeptRefUtils itemFilter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC((UIRefPane) panelEditor
            .getBillCardPanel().getBodyItem(StoreReqAppItemVO.PK_APPDEPT)
            .getComponent());
    itemFilter.filterItemRefByOrg(pk_org);

  }

  private void filterMaterial(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterMaterialRefUtils filter =
        new FilterMaterialRefUtils((UIRefPane) panelEditor.getBillCardPanel()
            .getBodyItem(StoreReqAppItemVO.PK_MATERIAL).getComponent());
    filter.filterItemRefByOrg(pk_org);
    filter.setMultiSelectedEnabled(true);
  }

  private void filterProject(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterProjectRefUtils headFilter =
        new FilterProjectRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(StoreReqAppHeaderVO.PK_PROJECT));
    headFilter.filterItemRefByOrg(pk_org);

    FilterProjectRefUtils itemFilter =
        new FilterProjectRefUtils(panelEditor.getBillCardPanel().getBodyItem(
            StoreReqAppItemVO.CPROJECTID));
    itemFilter.filterItemRefByOrg(pk_org);
  }

  private void filterPsn(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterPsndocRefUtils headFilter =
        FilterPsndocRefUtils
            .createFilterPsndocRefUtilsOfIC((UIRefPane) panelEditor
                .getBillCardPanel()
                .getHeadTailItem(StoreReqAppHeaderVO.PK_APPPSNH).getComponent());
    headFilter.filterItemRefByOrg(pk_org);

    FilterPsndocRefUtils itemFilter =
        FilterPsndocRefUtils
            .createFilterPsndocRefUtilsOfIC((UIRefPane) panelEditor
                .getBillCardPanel().getBodyItem(StoreReqAppItemVO.PK_APPPSN)
                .getComponent());
    itemFilter.filterItemRefByOrg(pk_org);
  }

  private void filterStordoc(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) panelEditor.getBillCardPanel()
            .getBodyItem(StoreReqAppItemVO.PK_REQSTORDOC).getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  // private void filterAddress(IBillCardPanelEditor panelEditor, String pk_org)
  // {
  // FilterAddressRefUtils filter =
  // new FilterAddressRefUtils(panelEditor.getBillCardPanel().getBodyItem(
  // StoreReqAppItemVO.CDEVADDRID));
  // filter.filterItemRefByOrg(pk_org);
  // }

  private void filterSupplier(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterSupplierRefUtils filter =
        new FilterSupplierRefUtils(panelEditor.getBillCardPanel().getBodyItem(
            StoreReqAppItemVO.CVENDORID));
    filter.filterItemRefByOrg(pk_org);
  }

}
