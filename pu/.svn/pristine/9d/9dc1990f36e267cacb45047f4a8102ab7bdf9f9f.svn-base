/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 上午11:35:45
 */
package nc.ui.pu.m25.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体备注编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 上午11:35:45
 */
public class Memo implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 表体备注变化完成后，保证如果是参照结果，保留参照；如果是手工输入，保存手工输入
    String str =
        ((UIRefPane) event.getBillCardPanel().getBodyItem(InvoiceItemVO.VMEMOB)
            .getComponent()).getRefName();
    if (StringUtil.isEmptyWithTrim(str)) {
      event.getBillCardPanel().setBodyValueAt(
          ((UIRefPane) event.getBillCardPanel()
              .getBodyItem(InvoiceItemVO.VMEMOB).getComponent()).getText(),
          event.getRow(), InvoiceItemVO.VMEMOB);
    }
    else {
      event.getBillCardPanel().setBodyValueAt(str, event.getRow(),
          InvoiceItemVO.VMEMOB);

    }

  }
  // /**
  // * 作者：王印芬 功能：表体备注变化完成后，保证如果是参照结果，保留参照；如果是手工输入，保存手工输入 参数：BillEditEvent e
  // * 捕捉到的BillEditEvent事件 返回：无 例外：无 日期：(2002-3-13 11:39:21) 修改日期，修改人，修改原因，注释标志：
  // * 2002-03-20 wyf 提高效率，在该函数中即完成分支 wyf add/modify/delete 2002-03-21 begin/end
  // */
  // private void afterEditInvBillBodyMemo(BillEditEvent e) {
  //
  // String str = ((UIRefPane)
  // getBillCardPanel().getBodyItem("vmemo").getComponent()).getRefName();
  // if (str != null && !str.trim().equals("")) {
  // getBillCardPanel().setBodyValueAt(str, e.getRow(), "vmemo");
  // }
  // else {
  // getBillCardPanel().setBodyValueAt(((UIRefPane)
  // getBillCardPanel().getBodyItem("vmemo").getComponent()).getText(),
  // e.getRow(), "vmemo");
  // }
  // }

}
