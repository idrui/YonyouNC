package nc.ui.pu.position.editor;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.handler.DefaultBillListEventHandler;
import nc.ui.scmpub.ref.FilterMaterialBaseClassRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterMaterialoidRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.uif2.LoginContext;

/**
 * ��λ���ñ༭�¼���
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ�༭���¼�
 * <li>����༭ǰ�¼�����
 * </ul>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author GGR
 * @time 2009-12-19 ����10:44:13
 */

public class PositionEventHandler extends DefaultBillListEventHandler {

  private LoginContext loginContext = null;

  // private String type;

  @Override
  public void afterBodyEdit(BillListPanel listPanel, BillEditEvent e) {
    this.setclass(listPanel, e);
  }

  @Override
  public void afterHeadEdit(BillListPanel listPanel, BillEditEvent e) {
    // ����δʹ��
  }

  @Override
  public boolean beforeBodyEdit(BillListPanel listPanel, BillEditEvent e) {
    this.setRefMode(listPanel, e);
    return true;
  }

  @Override
  public boolean beforeHeadEdit(BillListPanel listPanel, BillEditEvent e) {

    if (e.getKey().equals(PositionHeaderVO.CEMPLOYEEID)) {
      // ������Ա
      if (PuNodeCode.n40010515.code().equals(this.loginContext.getNodeCode())) {
        FilterPsndocRefUtils filter =
            FilterPsndocRefUtils
                .createFilterPsndocRefUtilsOfIC((UIRefPane) listPanel
                    .getHeadItem(PositionHeaderVO.CEMPLOYEEID).getComponent());
        filter.filterItemRefByOrg(this.getContext().getPk_org());
      }
      else {
        FilterPsndocRefUtils filter =
            FilterPsndocRefUtils
                .createFilterPsndocRefUtilsOfPU((UIRefPane) listPanel
                    .getHeadItem(PositionHeaderVO.CEMPLOYEEID).getComponent());
        filter.filterItemRefByOrg(this.getContext().getPk_org());
      }
    }
    return true;
  }

  public LoginContext getContext() {
    return this.loginContext;
  }

  public void setContext(LoginContext loginContext) {
    this.loginContext = loginContext;
  }

  // private String getType() {
  // if (null == this.type) {
  // this.type = PUSysParamUtil.getPO85(this.getContext().getPk_group());
  // }
  //
  // return this.type;
  // }

  private void clearMaterialInfo(BillListPanel listPanel, BillEditEvent e) {
    listPanel.getBodyBillModel().setValueAt(null, e.getRow(),
        PositionItemVO.PK_MATERIAL);
    listPanel.getBodyBillModel().setValueAt(null, e.getRow(),
        PositionItemVO.PK_SRCMATERIAL);
    listPanel.getBodyBillModel().setValueAt(null, e.getRow(),
        PositionItemVO.MATERIAL_CODE);
    listPanel.getBodyBillModel().setValueAt(null, e.getRow(),
        PositionItemVO.PK_SRCMATERIAL + ".name");
  }

  private void setclass(BillListPanel listPanel, BillEditEvent e) {
    // �����޸ĺ��������Ϸ���
    if (e.getKey().equals(PositionItemVO.PK_SRCMATERIAL)) {
      nc.ui.pub.bill.BillModel billModel = listPanel.getBodyBillModel();
      // �ɹ�����ʱ ��ջ������࣬ͬʱ���òɹ�����
      billModel.setValueAt(null, e.getRow(), PositionItemVO.PK_MARBASCLASS);
      if (PuNodeCode.n40010520.code().equals(this.getContext().getNodeCode())) {
        // MaterialStockClassPubService iservice =
        // NCLocator.getInstance().lookup(MaterialStockClassPubService.class);
        // String pk_mar =
        // billModel
        // .getValueAt(e.getRow(), PositionItemVO.PK_MATERIAL + "_ID")
        // .toString();
        // Map<String, MaterialStockVO> map =
        // MaterialStockClassPubService.queryMaterialStockInfoByPks(
        // new String[] {
        // pk_mar
        // }, this.getContext().getPk_org(), new String[] {
        // PositionItemVO.PK_MARPUCLASS
        // });
        // MaterialStockVO vo = map.get(pk_mar);
        int col = billModel.getBodyColByKey(PositionItemVO.PK_MARPUCLASS);
        // billModel.setValueAt(vo == null ? null : vo.getPk_marpuclass(), e
        // .getRow(), col);
        billModel.setValueAt(null, e.getRow(), col);

        billModel.loadLoadRelationItemValue(e.getRow(),
            PositionItemVO.PK_MARPUCLASS);

      }

      // ˢ����
      billModel.loadEditRelationItemValue(e.getRow(),
          PositionItemVO.PK_MARBASCLASS);

      billModel.loadEditRelationItemValue(e.getRow(),
          PositionItemVO.PK_MARPUCLASS);
    }

    // ���Ϸ����޸ĺ� ���������
    if (e.getKey().equals(PositionItemVO.PK_MARBASCLASS)) {
      this.clearMaterialInfo(listPanel, e);

    }

    // ���ϲɹ������޸ĺ� ���������
    if (e.getKey().equals(PositionItemVO.PK_MARPUCLASS)) {
      this.clearMaterialInfo(listPanel, e);
    }
  }

  private void setRefMode(BillListPanel listPanel, BillEditEvent e) {
    // ���ϻ�������
    if (e.getKey().equals(PositionItemVO.PK_MARBASCLASS)) {

      FilterMaterialBaseClassRefUtils filter =
          new FilterMaterialBaseClassRefUtils((UIRefPane) listPanel
              .getBodyItem(PositionItemVO.PK_MARBASCLASS).getComponent());
      filter.filterItemRefByOrg(this.getContext().getPk_org());
    }

    // ���ϲɹ�����
    if (e.getKey().equals(PositionItemVO.PK_MARPUCLASS)) {
      UIRefPane pane =
          (UIRefPane) listPanel.getBodyItem(PositionItemVO.PK_MARPUCLASS)
              .getComponent();
      if (pane != null && pane.getRefModel() != null) {
        pane.getRefModel().setPk_org(this.getContext().getPk_org());
      }
    }

    // ����
    if (e.getKey().equals(PositionItemVO.PK_MATERIAL)) {
      FilterMaterialRefUtils filter =
          new FilterMaterialRefUtils((UIRefPane) listPanel.getBodyItem(
              PositionItemVO.PK_MATERIAL).getComponent());
      filter.filterItemRefByOrg(this.getContext().getPk_org());
    }
    // ����oid
    if (e.getKey().equals(PositionItemVO.PK_SRCMATERIAL)) {
      FilterMaterialoidRefUtils filter =
          new FilterMaterialoidRefUtils((UIRefPane) listPanel.getBodyItem(
              PositionItemVO.PK_SRCMATERIAL).getComponent());
      filter.filterItemRefByOrg(this.getContext().getPk_org());
    }
  }

}
