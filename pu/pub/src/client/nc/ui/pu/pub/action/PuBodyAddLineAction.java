package nc.ui.pu.pub.action;

import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * ����pubapp�ı仯���ɹ��Լ�����action
 * 
 * @since 6.0
 * @version 2011-9-1 ����10:00:37
 * @author �����
 */

public class PuBodyAddLineAction extends BodyAddLineAction {

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    this.onDataInsert(index);
  }

  protected CircularlyAccessibleValueObject getInsertVO(int index) {
    return null;
  }

  protected void onDataInsert(int index) {
    this.getCardPanel().stopEditing();
    CircularlyAccessibleValueObject vo = this.getInsertVO(index);
    if (vo == null) {
      return;
    }

    if (this.getCardPanel().getBillData().isMeataDataTemplate()) {
      this.getCardPanel().getBillModel().setBodyRowObjectByMetaData(vo, index);
    }
    else {
      this.getCardPanel().getBillModel().setBodyRowVO(vo, index);

      this.getCardPanel().getBillModel().loadLoadRelationItemValue();
    }
  }

}
