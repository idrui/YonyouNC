package nc.ui.pu.pub.editor.card.handler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.batch.IBatchRefQuery;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pu.pub.util.VBatchCodeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-9-5 ����09:53:48
 * @author wuxla
 */

public class PUBatchCode implements ICardBodyAfterEditEventListener {

  private int[] rows = new int[0];

  private List<BatchRefViewVO> voList;

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    String key = event.getKey();
    BillItem billItem = panel.getBodyItem(key);
    if (!(billItem.getComponent() instanceof BatchRefPane)) {
      return;
    }

    BatchRefPane refpane = (BatchRefPane) billItem.getComponent();
    int row = event.getRow();
    String batchStr =
        (String) panel.getBodyValueAt(row, PUPubMetaNameConst.VBATCHCODE);
    if (!this.isEditEnable(event, batchStr)) {
      return;
    }
    this.clearBatchInfo(panel, row);
    BatchDlgParam queryParam =
        VBatchCodeUtil.getBatchDlgParam(event, event.getRow(),
            this.getHeadDims(), this.getBodyDims());
    queryParam.setIsQueryZeroLot(UFBoolean.TRUE);

    if (!refpane.isClicked() || batchStr == null || !batchStr.endsWith(".")) {
      this.handleBatchByHand(batchStr, panel, row, queryParam);
      return;
    }
    List<BatchRefViewVO> refVOs = refpane.getRefVOs();
    // �жϼ����������ں�ʧЧ����
    this.setProDateAndDvalidate(refVOs, event);
    this.addLinesByVO(panel, refVOs, event.getRow());
  }

  public <T> T[] combineArrays(T[] array1, T[] array2) {
    List<T> list = new ArrayList<>();
    for (T array : array1) {
      list.add(array);
    }
    for (T array : array2) {
      list.add(array);
    }
    return list.toArray(array1);
  }

  public String getPk_batchcode() {
    return PUPubMetaNameConst.PK_BATCHCODE;
  }

  public int[] getRows() {
    return this.rows;
  }

  public String getVbatchcode() {
    return PUPubMetaNameConst.VBATCHCODE;
  }

  public List<BatchRefViewVO> getVoList() {
    return this.voList;
  }

  public void setAssistUnit(BillCardPanel panel, int[] rows) {
    String material =
        (String) panel.getBodyValueAt(rows[0], PUPubMetaNameConst.PK_MATERIAL);
    Map<String, String> assistunitMap =
        MaterialPubService.queryPuMeasdocIDByPks(new String[] {
          material
        });

    for (int row : rows) {
      String assistunit = assistunitMap.get(material);
      if (null == assistunit) {
        panel.setBodyValueAt(
            panel.getBodyValueAt(row, PUPubMetaNameConst.CUNITID), row,
            PUPubMetaNameConst.CASTUNITID);
      }
      else {
        panel.setBodyValueAt(assistunit, row, PUPubMetaNameConst.CASTUNITID);
      }
    }
  }

  public void setRows(int[] rows) {
    this.rows = rows;
  }

  public void setVoList(List<BatchRefViewVO> voList) {
    this.voList = voList;
  }

  private void addLineByBodyMenu(BillCardPanel panel) {
    BillScrollPane scrollPane = panel.getBodyPanel();
    ActionEvent actionEvent =
        new ActionEvent(scrollPane.getTable(), BillScrollPane.ADDLINE,
            "AddLine");
    Action action = scrollPane.getBillTableAction(BillScrollPane.ADDLINE);
    action.actionPerformed(actionEvent);
  }

  private void addLinesByVO(BillCardPanel panel, List<BatchRefViewVO> refVOs,
      int row) {
    int rowcount = panel.getRowCount() - 1;
    List<Integer> rowIndex = this.addLine(panel, refVOs.size(), row);
    int origrow = row;
    // ��������һ��ԭʼû�б䣬������list����ߵ�һ�С�
    if (row != rowcount) {
      origrow = rowIndex.remove(rowIndex.size() - 1).intValue();
    }
    else {
      origrow = rowIndex.remove(0).intValue();
    }
    BatchRefViewVO vo = refVOs.remove(0);
    this.synRowInfo(panel, vo, origrow);
    for (int i = 0; i < rowIndex.size(); i++) {
      int currow = rowIndex.get(i).intValue();
      this.synRowInfo(panel, refVOs.get(i), currow);
      panel.setBodyValueAt(
          panel.getBodyValueAt(origrow, OrderItemVO.PK_MATERIAL), currow,
          OrderItemVO.PK_MATERIAL);
      panel.getBillModel().loadEditRelationItemValue(currow,
          OrderItemVO.PK_MATERIAL);
      this.synSrcBillInfo(panel, origrow, currow);
    }
    refVOs.add(vo);
    // rowIndex.add(Integer.valueOf(orirow));
    this.rows = new int[rowIndex.size()];
    for (int i = 0; i < this.rows.length; i++) {
      this.rows[i] = rowIndex.get(i).intValue();
    }
    // if (this.rows.length > 1) {
    // // this.setDefaultValue(panel,
    // // Arrays.copyOf(this.rows, this.rows.length - 1));
    // this.setDefaultValue(panel, this.rows);
    // }
  }

  private void handleBatchByHand(String inputStr, BillCardPanel panel, int row,
      BatchDlgParam queryParam) {

    BatchRefViewVO[] refVOs = null;
    if (!StringUtil.isSEmptyOrNull(inputStr)) {
      queryParam.setVbatchcode(inputStr);
      try {
        refVOs =
            NCLocator.getInstance().lookup(IBatchRefQuery.class)
                .queryBatchNum(queryParam);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (refVOs == null || refVOs.length == 0) {
      this.clearBatchInfo(panel, row);
      panel.setBodyValueAt(inputStr, row, PUPubMetaNameConst.VBATCHCODE);
      return;
    }
    BatchRefViewVO refVO = refVOs[0];
    // ����ǵ�������棬������Զ������κ������
    if (StringUtil.isStringEqual(refVO.getAttributeValue(BatchcodeVO.BSEAL)
        .toString(), UFBoolean.TRUE.toString())) {
      this.clearBatchInfo(panel, row);
      return;
    }

    panel.setBodyValueAt(refVO.getAttributeValue(BatchcodeVO.VBATCHCODE), row,
        PUPubMetaNameConst.VBATCHCODE);
    panel.setBodyValueAt(
        refVO.getAttributeValue(ICPubMetaNameConst.PK_BATCHCODE), row,
        PUPubMetaNameConst.PK_BATCHCODE);
  }

  private void insertLineByBodyMenu(BillCardPanel panel, int startrow) {
    BillScrollPane scrollPane = panel.getBodyPanel();
    Action action = scrollPane.getBillTableAction(BillScrollPane.INSERTLINE);
    if (startrow == panel.getRowCount()) {
      this.addLineByBodyMenu(panel);
      return;
    }
    action.actionPerformed(null);
  }

  private boolean isEditEnable(CardBodyAfterEditEvent event, String batchStr) {
    BillCardPanel panel = event.getBillCardPanel();
    BillItem billItem = panel.getBodyItem(event.getKey());
    BatchRefPane refpane = (BatchRefPane) billItem.getComponent();
    String oldvalue = (String) event.getOldValue();
    if (!refpane.isClicked() && StringUtils.equals(batchStr, oldvalue)) {
      return false;
    }
    else if (!StringUtils.equals(batchStr, oldvalue)) {
      return true;
    }

    BatchRefViewVO refVO = refpane.getRefVOs().get(0);
    String pk_batchcodeStr =
        (String) panel.getBodyValueAt(event.getRow(),
            PUPubMetaNameConst.PK_BATCHCODE);
    boolean issamePk =
        StringUtils.equals(
            (String) refVO.getAttributeValue(ICPubMetaNameConst.PK_BATCHCODE),
            pk_batchcodeStr);

    if (refpane.isClicked() && issamePk) {
      panel.setBodyValueAt(refVO.getAttributeValue(BatchcodeVO.VBATCHCODE),
          event.getRow(), PUPubMetaNameConst.VBATCHCODE);
      return false;
    }
    return true;

  }

  /**
   * ��ȡ��Ҫ���뵥�ݱ�������κź���صĽ��ά���ֶ�
   * 
   * @return
   */
  protected Map<String, String> getDimItems(){
    Map<String, String> items = new HashMap<>();
    items.put(BatchcodeVO.VBATCHCODE, PUPubMetaNameConst.VBATCHCODE);
    items.put(BatchcodeVO.PK_BATCHCODE, PUPubMetaNameConst.PK_BATCHCODE);
    items.put(OnhandDimVO.CPROJECTID, PUPubMetaNameConst.CPROJECTID);
    items.put(OnhandDimVO.CPRODUCTORID, PUPubMetaNameConst.CPRODUCTORID);
    items.put(OnhandDimVO.CASSCUSTID, PUPubMetaNameConst.CASSCUSTID);
    return items;
  }
  
  private void synRowInfo(BillCardPanel panel, BatchRefViewVO refVO, int index) {
    for(Map.Entry<String, String> entry : this.getDimItems().entrySet()){
      Object value = refVO.getAttributeValue(entry.getKey());
      if(value != null){
        panel.setBodyValueAt(value, index, entry.getValue());
      }
    }
  }

  private void synSrcBillInfo(BillCardPanel panel, int orirow, int destrow) {
    // ������Ĭ���������еĻ�������Ϊ��
    String[] rates = this.getClearFields();
    if (!ArrayUtils.isEmpty(rates)) {
      for (String rate : rates) {
        panel.setBodyValueAt(null, destrow, rate);
      }
    }
    String[] fields = this.getSrcBillInfoFields(panel);
    if (ArrayUtils.isEmpty(fields)) {
      return;
    }
    for (String field : fields) {
      if (panel.getBodyValueAt(orirow, field) != null
          && panel.getBodyValueAt(destrow, field) == null) {
        panel.setBodyValueAt(panel.getBodyValueAt(orirow, field), destrow,
            field);
      }
    }

    // ������Ҫ���Ƶ��ֶ���Ϊ��
    String[] fieldsnot = this.getFieldsNotNeed();
    if (ArrayUtils.isEmpty(fieldsnot)) {
      return;
    }
    for (String field : fieldsnot) {
      if (panel.getBodyValueAt(destrow, field) != null) {
        panel.setBodyValueAt(null, destrow, field);
      }
    }
  }

  protected List<Integer> addLine(BillCardPanel panel, int length, int row) {
    int lastRowIndex = panel.getRowCount() - 1;
    List<Integer> rowIndex = new ArrayList<Integer>();
    int tempRow;
    rowIndex.add(Integer.valueOf(row));
    Action action =
        panel.getBodyPanel().getBillTableAction(BillScrollPane.ADDLINE);
    // ��Щ���ݲ���������
    if (action == null) {
      return rowIndex;
    }
    for (int i = 1; i < length; i++) {
      // ��������һ�У������¼ӣ��������ϲ���
      if (row == lastRowIndex) {
        this.addLineByBodyMenu(panel);
        tempRow = panel.getRowCount() - 1;
      }
      else {
        tempRow = row + i;
        this.insertLineByBodyMenu(panel, tempRow);
      }
      rowIndex.add(Integer.valueOf(tempRow));
    }
    return rowIndex;
  }

  protected void clearBatchInfo(BillCardPanel panel, int row) {
    panel.setBodyValueAt(null, row, PUPubMetaNameConst.PK_BATCHCODE);
    panel.setBodyValueAt(null, row, PUPubMetaNameConst.VBATCHCODE);
  }

  /**
   * ��ȡ��ѯ����ʱ����Ҫ�ı����ֶ�
   * 
   * @return
   */
  protected Map<String, String> getBodyDims() {
    return null;
  }

  /**
   * 1����Щ����������ʱ����������Ĭ�����ó�1/1����Ҫ�������ʵ��ֶδ��Զ�������ȥ��
   * 2����λ���ܲ���Ĭ�ϵĲɹ���λ
   * 
   * @return
   */
  protected String[] getClearFields() {
    return new String[] {
      PUPubMetaNameConst.CASTUNITID, PUPubMetaNameConst.CUNITID
    };
  }

  /**
   * ��ȡ����Ҫ���Ƶ��ֶ�
   * 
   * @return
   */
  protected String[] getFieldsNotNeed() {
    return null;
  }

  /**
   * ��ȡ��ѯ����ʱ����Ҫ�ı�ͷ�ֶ�
   * 
   * @return
   */
  protected Map<String, String> getHeadDims() {
    return null;
  }

  protected String[] getSrcBillInfoFields(BillCardPanel panel) {
    BillItem[] items = panel.getBodyItems();
    List<String> fields = new ArrayList<String>();
    for (BillItem item : items) {
      fields.add(item.getMetaDataAccessPath());
    }
    return fields.toArray(new String[fields.size()]);
  }

  protected void setDefaultValue(BillCardPanel panel, int[] rows) {
    this.setAssistUnit(panel, rows);
  }

  @SuppressWarnings("unused")
  protected void setProDateAndDvalidate(List<BatchRefViewVO> refVOs,
      CardBodyAfterEditEvent event) {
  }
}
