package nc.ui.pu.m23.editor.card.afteredit.body;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import nc.impl.ic.util.DateCalUtil;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;

/**
 * <p>
 * <b>“批次号”字段的编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：根据批次号是否为空，设置“生产日期”、“失效日期”
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    for (int row : super.getRows()) {
      RelationCalculate rc = new RelationCalculate();
      rc.calculate(event.getBillCardPanel(), row, ArriveItemVO.NNUM);
    }
  }

  @Override
  protected List<Integer> addLine(BillCardPanel panel, int length, int row) {
    int lastRowIndex = panel.getRowCount() - 1;
    List<Integer> rowIndex = new ArrayList<Integer>();
    int tempRow;
    rowIndex.add(Integer.valueOf(row));

    for (int i = 1; i < length; i++) {

      BillScrollPane scrollPane = panel.getBodyPanel();
      ActionEvent actionEvent =
          new ActionEvent(scrollPane.getTable(), BillScrollPane.COPYLINE,
              "CopyLine");
      Action action = scrollPane.getBillTableAction(BillScrollPane.COPYLINE);
      action.actionPerformed(actionEvent);

      // 如果是最后一行，则向下加；否则向上插入
      if (row == lastRowIndex) {
        // this.addLineByBodyMenu(panel);

        ActionEvent pasteLineEvent =
            new ActionEvent(scrollPane.getTable(),
                BillScrollPane.PASTELINETOTAIL, "PasteLineToTail");
        Action pasteLineAction =
            scrollPane.getBillTableAction(BillScrollPane.PASTELINETOTAIL);
        pasteLineAction.actionPerformed(pasteLineEvent);

        tempRow = panel.getRowCount() - 1;
      }
      else {
        tempRow = row + i;
        // this.insertLineByBodyMenu(panel, tempRow);PasteLineToTail

        if (tempRow == panel.getRowCount()) {
          ActionEvent pasteLineEvent =
              new ActionEvent(scrollPane.getTable(),
                  BillScrollPane.PASTELINETOTAIL, "PasteLineToTail");
          Action pasteLineAction =
              scrollPane.getBillTableAction(BillScrollPane.PASTELINETOTAIL);
          pasteLineAction.actionPerformed(pasteLineEvent);
          continue;
        }
        Action pasteLineAction =
            scrollPane.getBillTableAction(BillScrollPane.PASTELINE);
        pasteLineAction.actionPerformed(null);
      }
      rowIndex.add(Integer.valueOf(tempRow));
    }
    return rowIndex;
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, ArriveItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, ArriveItemVO.PK_RECEIVESTORE);
    return bodyDims;
  }

  @Override
  protected String[] getFieldsNotNeed() {
    return new String[] {
      ArriveItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.CVENDORID, ArriveHeaderVO.PK_SUPPLIER);
    return headDims;
  }

  @Override
  protected void setProDateAndDvalidate(List<BatchRefViewVO> voList,
      CardBodyAfterEditEvent e) {
    if (voList == null || voList.size() == 0) {
      // 清空生产日期、失效日期
      String[] produceItemArray = new String[] {
        ArriveItemVO.DPRODUCEDATE, ArriveItemVO.DINVALIDDATE
      };
      ArriveClientUtil.clearBodyCellValues(e.getBillCardPanel(), e.getRow(),
          produceItemArray);
      return;
    }

    BillCardPanel panel = e.getBillCardPanel();
    int row = e.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, ArriveItemVO.PK_MATERIAL);
    String pk_stockorg =
        (String) panel.getHeadItem(ArriveHeaderVO.PK_ORG).getValueObject();
    if (pk_material == null || pk_stockorg == null) {
      return;
    }
    Map<String, MaterialStockVO> materialstockMap =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(new String[] {
          pk_material
        }, pk_stockorg, new String[] {
          MaterialStockVO.WHOLEMANAFLAG, MaterialStockVO.QUALITYMANFLAG,
          MaterialStockVO.QUALITYNUM, MaterialStockVO.QUALITYUNIT
        });

    if (materialstockMap == null || !materialstockMap.containsKey(pk_material)) {
      return;
    }

    MaterialStockVO materialStockVO = materialstockMap.get(pk_material);

    if (UFBoolean.FALSE.equals(materialStockVO.getWholemanaflag())
        || UFBoolean.FALSE.equals(materialStockVO.getQualitymanflag())) {
      return;
    }

    Integer qualityUnit = materialStockVO.getQualityunit();
    Integer qualityNum = materialStockVO.getQualitynum();

    BatchRefViewVO refVO = voList.get(0);
    // 根据生产日期计算失效日期
    if (refVO.getAttributeValue(BatchcodeVO.DPRODUCEDATE) != null
        && refVO.getAttributeValue(BatchcodeVO.DVALIDATE) == null) {
      UFDate dproduceDate =
          (UFDate) refVO.getAttributeValue(BatchcodeVO.DPRODUCEDATE);
      panel.setBodyValueAt(
          DateCalUtil.calDvalidate(dproduceDate, qualityNum, qualityUnit), row,
          ArriveItemVO.DINVALIDDATE);
    }// 根据失效日期计算生产日期
    else if (refVO.getAttributeValue(BatchcodeVO.DVALIDATE) != null
        && refVO.getAttributeValue(BatchcodeVO.DPRODUCEDATE) == null) {
      UFDate dvalidate =
          (UFDate) refVO.getAttributeValue(BatchcodeVO.DVALIDATE);
      panel.setBodyValueAt(
          DateCalUtil.calDvalidate(dvalidate,
              Integer.valueOf(0 - qualityNum.intValue()), qualityUnit), row,
          ArriveItemVO.DPRODUCEDATE);

    }
    else {
      panel.setBodyValueAt(refVO.getAttributeValue(BatchcodeVO.DVALIDATE), row,
          ArriveItemVO.DINVALIDDATE);
      panel.setBodyValueAt(refVO.getAttributeValue(BatchcodeVO.DPRODUCEDATE),
          row, ArriveItemVO.DPRODUCEDATE);
    }

  }
}
