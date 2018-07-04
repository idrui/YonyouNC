package nc.ui.pu.m23.view;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetterExt;
import nc.vo.pu.m23.entity.ArriveViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������ĸ�ֵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-7-12 ����03:17:53
 */
public class CheckListPanelValueSetter implements IBillListPanelValueSetterExt {

  @Override
  public void setBodyData(BillListPanel listPanel, Object selectedData) {
    // ���ñ���ֵ
  }

  @Override
  public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
    // ���ñ�ͷֵ
    ArriveViewVO[] objs = null;
    if (!ArrayUtils.isEmpty(allDatas)) {
      objs = new ArriveViewVO[allDatas.length];
      for (int i = 0, len = allDatas.length; i < len; i++) {
        objs[i] = (ArriveViewVO) allDatas[i];
      }
    }
    listPanel.getHeadBillModel().setBodyDataVO(objs);
    listPanel.getHeadBillModel().loadLoadRelationItemValue();
  }

  @Override
  public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
    // ���ñ�ͷ�ض��е�ֵ
    if (rowData == null) {
      return;
    }

    BillModel headModel = listPanel.getBillListData().getHeadBillModel();
    if (headModel == null) {
      return;
    }

    headModel.setBodyRowVO((ArriveViewVO) rowData, row);
    // ���������Ϣ
    if (listPanel.getBillListData().isMeataDataTemplate()) {
      BillItem[] items = headModel.getBodyItems();
      if (items == null || items.length == 0) {
        return;
      }
      headModel.loadLoadRelationItemValue(row);
    }
    // ����װ�ع�ʽ
    headModel.execLoadFormulaByRow(row);
  }

  @Override
  public void setHeaderRowsData(BillListPanel listPanel, Object[] datas,
      int[] indexs) {
    // ���ñ�ͷ�ض��е�ֵ
    if (datas == null) {
      return;
    }

    BillModel headModel = listPanel.getBillListData().getHeadBillModel();
    if (headModel == null) {
      return;
    }
    for (int j = 0; j < indexs.length; j++) {
    	headModel.setBodyRowVO((ArriveViewVO) datas[j], j);
	}
    // ����װ�ع�ʽ
    headModel.loadLoadRelationItemValue();
    headModel.execLoadFormula();
  }
}
