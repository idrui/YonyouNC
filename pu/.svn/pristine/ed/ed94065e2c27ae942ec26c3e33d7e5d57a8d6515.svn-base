package nc.ui.pu.pub.action;

import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 适配pubapp的变化，采购自己的行action
 * 
 * @since 6.0
 * @version 2011-9-1 上午10:00:12
 * @author 田锋涛
 */

public class PuBodyInsertLineAction extends BodyInsertLineAction {
  //

  protected CircularlyAccessibleValueObject getInsertVO() {
    return null;
  }

  @Override
  protected void onDataInsert() {
    int seletctIndex = this.getCurrentSelectIndex();
    if (seletctIndex == -1) {
      return;
    }
    this.getCardPanel().stopEditing();
    // 使插入行时可以设置模板取值
    if (this.getInsertVO() != null) {
      if (this.getCardPanel().getBillData().isMeataDataTemplate()) {
        this.getCardPanel().getBillModel()
            .setBodyRowObjectByMetaData(this.getInsertVO(), seletctIndex);
      }
      else {
        this.getCardPanel().getBillModel()
            .setBodyRowVO(this.getInsertVO(), seletctIndex);
        this.getCardPanel().getBillModel().loadLoadRelationItemValue();
      }
      this.afterLineInsert(seletctIndex);
      this.execLoadFormula(seletctIndex);
    }

    this.getCardPanel().getBodyPanel().getTable().getSelectionModel()
        .setSelectionInterval(seletctIndex, seletctIndex);
    ((BillScrollPane) this.getCardPanel().getBodySelectedScrollPane())
        .getTable().changeSelection(seletctIndex, 0, false, false);
    ((BillScrollPane) this.getCardPanel().getBodySelectedScrollPane())
        .getTable().requestFocus();

  }
}
