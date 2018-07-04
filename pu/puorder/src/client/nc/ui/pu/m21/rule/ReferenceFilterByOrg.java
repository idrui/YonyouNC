package nc.ui.pu.m21.rule;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterPaytermRefUtils;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：过滤跟主组织关联的参照</b>
 * <ul>
 * <li>1、 过滤表头供应商参照：只能参照出采购组织可见（管控模式）的供应商档案
 * <li>2、 过滤表头部门参照：只能参照采购组织BU下的部门档案
 * <li>3、 过滤表头人员参照：只能参照采购组织BU下的所有部门的人员档案
 * <li>4、 过滤表头收货客户参照：只能参照出采购组织可见（管控模式）的客户档案
 * <li>5、 过滤表头开票供应商参照：同供应商参照
 * <li>6、 过滤表体物料参照：只能参照出采购组织可见（管控模式）的供应商档案
 * <li>7、 过滤表体收货地点参照：只能参照出采购组织可见（管控模式）的地点档案
 * <li>8、 过滤表体供应商发货地点参照：只能参照出采购组织可见（管控模式）的地点档案
 * <li>9、过滤订单类型参照：
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 上午11:33:04
 */
public class ReferenceFilterByOrg {
  private IBillCardPanelEditor editor;

  public ReferenceFilterByOrg(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * 方法功能描述：过滤参照。
   * <p>
   * <b>参数说明</b>
   * 
   * @param editor 编辑器
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-27 上午10:13:15
   */
  public void filter() {
    // 取当前采购组织，如果采购组织为空，则不执行下面的过滤过程
    String pk_org =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(OrderHeaderVO.PK_ORG).getValueObject();
    // 查询数据后，触发事件，pk_org为null
    if (null == pk_org) {
      ShowUpableBillForm form = (ShowUpableBillForm) this.editor;
      pk_org = form.getModel().getContext().getPk_org();
    }
    // 过滤订单类型，交易类型过滤放到编辑前，这里不再处理

    if (pk_org == null) {
      return;
    }

    // 过滤供应商
    this.filterSupplier(this.editor, OrderHeaderVO.PK_SUPPLIER, pk_org);

    // 过滤开票供应商
    this.filterSupplier(this.editor, OrderHeaderVO.PK_INVCSUPLLIER, pk_org);

    // 散户不用过滤参照，直接用的FreeCustRefModel参照，查询bd_freecustom表，以前可能是通过供应商参照的isfreecust过滤
    // comment by wangljc

    // 过滤收货客户
    this.filterCustomer(this.editor, pk_org);

    // 过滤人员
    this.filterPsn(this.editor, pk_org);

    // 过滤部门
    this.filterDepartment(this.editor, pk_org);

    // 过滤物料并允许多选
    String tableCode = this.editor.getBillCardPanel().getCurrentBodyTableCode();
    if ("material".equals(tableCode)) {
      this.filterMaterial(this.editor, pk_org);
      // 过滤项目
      this.filterProject(this.editor, pk_org);
    }

    // 付款协议
    this.filterPayterm(this.editor, pk_org);
  }

  private void filterCustomer(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterCustomerRefUtils filter =
        new FilterCustomerRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(OrderHeaderVO.PK_RECVCUSTOMER));
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterDepartment(IBillCardPanelEditor panelEditor, String pk_org) {
    // 表头部门参照
    FilterDeptRefUtils filterv =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) panelEditor
            .getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_DEPT_V)
            .getComponent());
    filterv.filterItemRefByOrg(pk_org);
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) panelEditor
            .getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_DEPT)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterMaterial(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterMaterialRefUtils filter =
        new FilterMaterialRefUtils((UIRefPane) panelEditor.getBillCardPanel()
            .getBodyItem(OrderItemVO.PK_MATERIAL).getComponent());
    filter.filterItemRefByOrg(pk_org);
    filter.setMultiSelectedEnabled(true);
  }

  private void filterPayterm(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterPaytermRefUtils filter =
        new FilterPaytermRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(OrderHeaderVO.PK_PAYTERM));
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterProject(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterProjectRefUtils filter =
        new FilterProjectRefUtils(panelEditor.getBillCardPanel().getBodyItem(
            OrderItemVO.CPROJECTID));
    filter.filterItemRefByOrg(pk_org);
    FilterProjectRefUtils hfilter =
        new FilterProjectRefUtils(panelEditor.getBillCardPanel().getHeadItem(
            OrderHeaderVO.PK_PROJECT));
    hfilter.filterItemRefByOrg(pk_org);
  }

  private void filterPsn(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils
            .createFilterPsndocRefUtilsOfPU((UIRefPane) panelEditor
                .getBillCardPanel().getHeadTailItem(OrderHeaderVO.CEMPLOYEEID)
                .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterSupplier(IBillCardPanelEditor panelEditor,
      String supplierItem, String pk_org) {
    FilterSupplierRefUtils filter =
        new FilterSupplierRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(supplierItem));
    filter.filterItemRefByOrg(pk_org);
  }
}
