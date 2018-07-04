/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-15 下午08:53:21
 */
package nc.ui.pu.est.action.body;

import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>粘贴到行尾动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-15 下午08:53:21
 */
@SuppressWarnings("serial")
public class EstBodyPasteToTailAction extends BodyPasteToTailAction {

  @Override
  public void doAction() {
    super.doAction();
    BillScrollPane bsp = this.getCardPanel().getBodyPanel();
    UITable table = bsp.getTable();
    int row = table.getRowCount();
    int rowlength = bsp.getTableModel().getPasteLineNumer();
    for (int l = 1; l <= rowlength; l++) {
      int currentrow = row - l;
      BillModel bm = this.getCardPanel().getBillModel();
      String bodyClassName =
          bm.getTabvo().getBillMetaDataBusinessEntity().getFullClassName();
      CircularlyAccessibleValueObject vo =
          bm.getBodyValueRowVO(currentrow, bodyClassName);
      this.setDefaultValue(vo);
      bm.setBodyRowVO(vo, currentrow);
      bm.loadLoadRelationItemValue();
      this.getCardPanel().getBillModel().execLoadFormulaByRow(row);
    }
  }

  private void setDefaultValue(CircularlyAccessibleValueObject vo) {
    AggregatedValueObject aggVo =
        (AggregatedValueObject) this.getModel().getSelectedData();
    if (null == aggVo) {
      return;
    }
    EstVODefualtValueUtil.setFeeEstDefValue((FeeEstVO) vo,
        (GoodsEstVO) aggVo.getParentVO(), this.getModel().getContext()
            .getPk_loginUser());
  }

}
