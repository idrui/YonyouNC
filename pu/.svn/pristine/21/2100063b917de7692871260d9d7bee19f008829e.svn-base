/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-1 下午04:01:49
 */
package nc.ui.pu.m25.action;

import nc.ui.pu.m25.editor.utils.NewBodyRowDefaultValueHandler;
import nc.ui.pu.pub.action.PuBodyAddLineAction;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-7-1 下午04:01:49
 */
public class InvoiceBodyAddLineAction extends PuBodyAddLineAction {

  private static final long serialVersionUID = 4511624762186178266L;

  private void setDefaultValue(InvoiceItemVO vo) {
    NewBodyRowDefaultValueHandler.setDefaultRowValue(this.getCardPanel(), vo);
  }

  /**
   * 父类方法重写
   * 在这可以设置默认值
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
