package nc.ui.pu.m4t.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pu.m4t.rule.LineDefaultValue;

/**
 * @since 6.0
 * @version 2010-11-11 ����01:02:03
 * @author wuxla
 */

public class InitialEstAddLineAction extends BodyAddLineAction {

  private static final long serialVersionUID = 6595788607611149674L;

  @Override
  public void doAction() {
    super.doAction();
    // modified by fanly3 2013-06-03
    // pubapp �޸�Ч�����⣬��nc.ui.pubapp.uif2app.actions.BodyAddLineAction
    // ��ʵ����IBatchBodyLine�ӿڡ�
    // ԭ���� doAction ����������Ĭ��ֵ�Ĵ��룬��������ѡ�󲻻�ִ�С�
    // �����Ҫ��дafterLineInsert ����������Ĭ��ֵ��
    // �����ڼ�������������Ĭ��ֵ��

    // CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // int index = this.getCardPanel().getBillModel().getRowCount() - 1;
    // // ���ñ���Ĭ��ֵ
    // new LineDefaultValue(helper, this.getModel().getContext())
    // .setDefaultValue(index);
  }

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // ���ñ���Ĭ��ֵ
    new LineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(index);
  }

}
