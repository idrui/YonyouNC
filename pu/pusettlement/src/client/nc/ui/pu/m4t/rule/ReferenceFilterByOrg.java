/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 ����08:45:45
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���˸�����֯�����Ĳ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 ����08:45:45
 */
public class ReferenceFilterByOrg {
  private IBillCardPanelEditor editor;

  public ReferenceFilterByOrg(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * �����������������˲���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-7 ����09:28:18
   */
  public void filter() {
    this.filterByPkOrg();
    this.filterByPurchaseOrg();
  }

  private void filterByPkOrg() {
    // ȡ��ǰ������֯�����������֯Ϊ�գ���ִ������Ĺ��˹���
    String pk_org =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_ORG).getValueObject();

    // ��������
    this.filterInitialestType(this.editor, pk_org);

    if (pk_org == null) {
      return;
    }

    // ���˹�Ӧ��
    this.filterSupplier(this.editor, InitialEstHeaderVO.PK_SUPPLIER, pk_org);

    // ���˱���Ա
    this.filterManagepsn(this.editor);

    // ���˲ֿ�
    this.filterStordoc(this.editor);

    // �������ϲ������ѡ
    this.filterMaterial(this.editor, pk_org);

    // ������Ŀ
    this.filterProject(this.editor, pk_org);
  }

  private void filterByPurchaseOrg() {
    // ȡ��ǰ������֯�����������֯Ϊ�գ���ִ������Ĺ��˹���
    String pk_org =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_PURCHASEORG)
            .getValueObject();
    if (null == pk_org) {
      return;
    }

    // ���˲ɹ�Ա
    this.filterPsn(this.editor, pk_org);

    // ���˲ɹ�����
    this.filterDepartment(this.editor, pk_org);
  }

  private void filterDepartment(IBillCardPanelEditor panelEditor, String pk_org) {
    // ��ͷ���Ų���
    FilterDeptRefUtils filterv =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) panelEditor
            .getBillCardPanel().getHeadTailItem(InitialEstHeaderVO.PK_DEPT_V)
            .getComponent());
    filterv.filterItemRefByOrg(pk_org);
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU((UIRefPane) panelEditor
            .getBillCardPanel().getHeadTailItem(InitialEstHeaderVO.PK_DEPT)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterInitialestType(IBillCardPanelEditor panelEditor,
      String pk_org) {
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.CTRANTYPEID), pk_org);
    filter.filterTranType(new String[] {
      POBillType.InitEstimate.getCode()
    }, null);
  }

  private void filterManagepsn(IBillCardPanelEditor panelEditor) {
    String pk_stockorg =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_STOCKORG).getValueObject();
    if (null == pk_stockorg) {
      return;
    }
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils
            .createFilterPsndocRefUtilsOfIC((UIRefPane) panelEditor
                .getBillCardPanel()
                .getHeadTailItem(InitialEstHeaderVO.PK_MANAGEPSN)
                .getComponent());
    filter.filterItemRefByOrg(pk_stockorg);
  }

  private void filterMaterial(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterMaterialRefUtils filter =
        new FilterMaterialRefUtils((UIRefPane) panelEditor.getBillCardPanel()
            .getBodyItem(InitialEstItemVO.PK_MATERIAL).getComponent());
    filter.filterItemRefByOrg(pk_org);
    filter.setMultiSelectedEnabled(true);
  }

  private void filterProject(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterProjectRefUtils filter =
        new FilterProjectRefUtils(panelEditor.getBillCardPanel().getBodyItem(
            InitialEstItemVO.CPROJECTID));
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterPsn(IBillCardPanelEditor panelEditor, String pk_org) {
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils
            .createFilterPsndocRefUtilsOfPU((UIRefPane) panelEditor
                .getBillCardPanel()
                .getHeadTailItem(InitialEstHeaderVO.PK_BIZPSN).getComponent());
    filter.filterItemRefByOrg(pk_org);
  }

  private void filterStordoc(IBillCardPanelEditor panelEditor) {
    String pk_stockorg =
        (String) this.editor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_STOCKORG).getValueObject();
    if (null == pk_stockorg) {
      return;
    }
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) panelEditor.getBillCardPanel()
            .getHeadTailItem(InitialEstHeaderVO.PK_STORDOC).getComponent());
    filter.filterItemRefByOrg(pk_stockorg);
  }

  private void filterSupplier(IBillCardPanelEditor panelEditor,
      String supplierItem, String pk_org) {
    FilterSupplierRefUtils filter =
        new FilterSupplierRefUtils(panelEditor.getBillCardPanel()
            .getHeadTailItem(supplierItem));
    filter.filterItemRefByOrg(pk_org);
  }
}
