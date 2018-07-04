/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-1 ����03:03:22
 */
package nc.ui.pu.m25.action;

import nc.ui.pu.m25.editor.utils.NewBodyRowDefaultValueHandler;
import nc.ui.pu.pub.action.PuBodyInsertLineAction;
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
 * @time 2009-7-1 ����03:03:22
 */
public class InvoiceBodyInsertLineAction extends PuBodyInsertLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = -3640476107866879734L;

  private void setDefaultValue(InvoiceItemVO vo) {
    NewBodyRowDefaultValueHandler.setDefaultRowValue(this.getCardPanel(), vo);
  }

  /**
   * ���෽����д �����������Ĭ��ֵ
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#getInsertVO()
   */
  @Override
  protected CircularlyAccessibleValueObject getInsertVO() {
    InvoiceItemVO vo = (InvoiceItemVO) super.getInsertVO();
    if (null == vo) {
      vo =
          (InvoiceItemVO) this
              .getCardPanel()
              .getBillModel()
              .getBodyValueRowVO(this.getCurrentSelectIndex(),
                  InvoiceItemVO.class.getName());
    }
    this.setDefaultValue(vo);
    return vo;
  }

}
