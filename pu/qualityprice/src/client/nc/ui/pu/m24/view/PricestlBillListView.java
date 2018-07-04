package nc.ui.pu.m24.view;

import java.util.ArrayList;

import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListBodyRowChangedEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥�б����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-22 ����02:35:41
 */
public class PricestlBillListView extends
    nc.ui.pubapp.uif2app.view.ShowUpableBillListView {

  private static class MDBillListPanelValueSetter0 extends
      MDBillListPanelValueSetter {
    public MDBillListPanelValueSetter0() {
      super();
    }

    @Override
    public void setBodyData(BillListPanel listPanel, Object selectedData) {
      if (null == selectedData) {
        listPanel.getBodyBillModel().clearBodyData();
      }
      else {
        PricestlVO bill = (PricestlVO) selectedData;
        BillListData listData = listPanel.getBillListData();
        listData.setBodyValueVO("body_material", bill.getBVO());
        listData.getBodyBillModel("body_material").loadLoadRelationItemValue();

        listData.setBodyValueVO("body_hqhp", bill.getBBVO());
        listData.getBodyBillModel("body_hqhp").loadLoadRelationItemValue();

      }

    }
  }

  private static final long serialVersionUID = 2190579631313630286L;

  @Override
  public void handleEvent(AppEvent event) {
    super.handleEvent(event);

    if (event instanceof ListBodyRowChangedEvent) {
      // �иı�ʱ���������ż���Ϣ
      this.setHqhpInfo((ListBodyRowChangedEvent) event);
    }
  }

  @Override
  public void initUI() {
    super.initUI();
    this.setBillListPanelValueSetter(new MDBillListPanelValueSetter0());
  }

  /**
   * ��������������ȡ�ñ����ж�Ӧ�������ż���Ϣ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bitems
   * @param pk_base
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-12 ����12:45:17
   */
  private PricestlItemBVO[] getBitem(PricestlItemBVO[] bitems, String pk_base) {
    if (StringUtil.isEmptyWithTrim(pk_base)) {
      return null;
    }

    ArrayList<PricestlItemBVO> ret = new ArrayList<PricestlItemBVO>();
    for (PricestlItemBVO item : bitems) {
      if (pk_base.equals(item.getCqpschemeid())) {
        ret.add(item);
      }
    }

    return ret.toArray(new PricestlItemBVO[ret.size()]);
  }

  /**
   * ���������������иı�ʱ���������ż���Ϣ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-12 ����12:44:35
   */
  private void setHqhpInfo(ListBodyRowChangedEvent event) {
    int i = this.getBillListPanel().getBodyTabbedPane().getSelectedIndex();
    if (i == 0) {
      int row = event.getRow();
      BillModel bm = this.getBillListPanel().getBodyBillModel("body_hqhp");
      PricestlItemBVO[] setbitems = null;
      if (row >= 0) {
        PricestlItemBVO[] bitems =
            ((PricestlVO) this.getModel().getSelectedData()).getBBVO();
        String pk_base =
            (String) this.getBillListPanel().getBodyBillModel()
                .getValueAt(row, PricestlItemVO.CQPSCHEMEID);
        setbitems = this.getBitem(bitems, pk_base);
      }
      bm.setBodyDataVO(setbitems);
      bm.loadLoadRelationItemValue();
      bm.execLoadFormula();
    }
  }
}
