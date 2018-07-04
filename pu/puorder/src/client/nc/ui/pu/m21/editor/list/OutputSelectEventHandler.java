package nc.ui.pu.m21.editor.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ����� ��� ��ѡ�е��¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2012-2-23 ����08:39:25
 */
public class OutputSelectEventHandler implements
    IAppEventHandler<ListHeadRowStateChangeEvent> {
  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @SuppressWarnings("restriction")
  @Override
  public void handleAppEvent(ListHeadRowStateChangeEvent e) {
    BillListPanel panel = e.getBillListPanel();
    BillModel bm = panel.getHeadBillModel();

    int count = bm.getRowCount();
    HashSet<Integer> selectedRows = new HashSet<Integer>();
    for (int i = 0; i < count; i++) {
      if (BillModel.SELECTED == bm.getRowState(i)) {
        selectedRows.add(i);
      }
    }

    // ȡ��ѡ��ʱ�����model�е�ѡ�б��
    if (selectedRows.size() == 0) {
      this.getModel().setSelectedOperaRows(new int[0]);
      ((SelectBillManageModel) this.getModel())
          .setSelectedBills(new ArrayList<IBill>());
      return;
    }
    int[] intSelectedRows = new int[selectedRows.size()];

    Integer selectedRow = null;
    Iterator<Integer> iterRows = selectedRows.iterator();
    int i = 0;
    while (iterRows.hasNext()) {
      selectedRow = iterRows.next();
      intSelectedRows[i] = selectedRow;
      i++;
    }
    this.getModel().setSelectedOperaRows(intSelectedRows);

    // ��ѡ�е��зŵ�Model��
    List<IBill> bodySelectedVOList = new ArrayList<IBill>();
    OrderVO[] orderVOs =
        ArrayClassConvertUtil.convert(this.model.getSelectedOperaDatas(),
            OrderVO.class);
    bodySelectedVOList.addAll(Arrays.asList(orderVOs));
    ((SelectBillManageModel) this.getModel())
        .setSelectedBills(bodySelectedVOList);

    // this.model.getSelectedOperaDatas());
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
