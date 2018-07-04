/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午04:52:38
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体应付财务组织编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-31 下午04:52:38
 */
public class ApFinanceOrg implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    /*
     * 非空。参照采购订单或者采购入库单生成采购发票时，以采购订单或者采购入库单表体的应付组织作为采购发票的表体应付组织，
     * 并且不可编辑(有来源时就不可编辑)； 自制采购发票时，应付组织根据表头财务组织带入默认值，可编辑。
     * 参照集团范围的组织类型为财务组织的组织单元录入。
     */
    int row = event.getRow();
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    String source = helper.getBodyStringValue(row, InvoiceItemVO.CSOURCEBID);
    if (StringUtil.isEmptyWithTrim(source)) {
      event.setReturnValue(Boolean.TRUE);
    }
    else {
      event.setReturnValue(Boolean.FALSE);
    }

  }

}
