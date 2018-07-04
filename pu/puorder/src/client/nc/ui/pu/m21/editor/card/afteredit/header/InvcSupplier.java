package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * 开票供应商编辑后事件
 * 
 * @since 6.3
 * @version 2013-5-25 上午09:54:43
 * @author lixyp
 */
public class InvcSupplier implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());

    // 清空供应商时
    if (StringUtil.isEmptyWithTrim(newValue)) {
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.PK_BANKDOC, null);
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.VBANKACCOUNT, null);
      return;
    }

    // 非清空 填充银行账户信息
    SupplierFreeCustInfoUtil freeCustInfoUtil = new SupplierFreeCustInfoUtil();
    freeCustInfoUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
    freeCustInfoUtil.setSuppliername(OrderHeaderVO.PK_INVCSUPLLIER);
    freeCustInfoUtil.setFreeCustBankAcc(editor);
  }

}
