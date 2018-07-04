/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-1 ����04:01:49
 */
package nc.ui.pu.m25.action;

import nc.ui.pu.m25.editor.utils.NewBodyRowDefaultValueHandler;
import nc.ui.pu.pub.action.PuBodyAddLineAction;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-7-1 ����04:01:49
 */
public class InvoiceBodyAddLineAction extends PuBodyAddLineAction {

  private static final long serialVersionUID = 4511624762186178266L;

  private void setDefaultValue(InvoiceItemVO vo) {
    NewBodyRowDefaultValueHandler.setDefaultRowValue(this.getCardPanel(), vo);
  }

  /**
   * ���෽����д
   * �����������Ĭ��ֵ
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#getInsertVO()
   */
  @Override
  protected CircularlyAccessibleValueObject getInsertVO(int index) {
    InvoiceItemVO vo =
        (InvoiceItemVO) this.getCardPanel().getBillModel()
            .getBodyValueRowVO(index, InvoiceItemVO.class.getName());

    this.setDefaultValue(vo);
    return vo;
  }
}
