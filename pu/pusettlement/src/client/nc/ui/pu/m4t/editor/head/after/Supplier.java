/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 下午01:22:40
 */
package nc.ui.pu.m4t.editor.head.after;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m4t.rule.CurrencyRelated;
import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.rule.DefaultSendCountrySetter;
import nc.vo.pu.m4t.rule.SupplierDefaultValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 下午01:22:40
 */
public class Supplier implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    CardEditorHelper editor = new CardEditorHelper(panel);

    SupplierDefaultValue supplierRule = new SupplierDefaultValue(editor);
    supplierRule.setDefaultValue();

    CurrencyRelated currencyRule = new CurrencyRelated(panel);
    currencyRule.setCurrencyAndExchangeRate(false);

    int rowcount = panel.getRowCount();
    if (0 == rowcount) {
      return;
    }
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < rowcount; ++i) {
      String csourcetype =
          (String) panel.getBodyValueAt(i, InitialEstItemVO.CSOURCETYPECODE);
      if (StringUtils.isEmpty(csourcetype)) {
        list.add(Integer.valueOf(i));
      }
    }
    if (list.size() == 0) {
      return;
    }
    int[] rows = ArrayUtils.toPrimitive(list.toArray(new Integer[list.size()]));
    // 设置发货国家
    new DefaultSendCountrySetter().setSendCountry(editor, rows);
    // 设置供应商后肯定要询税，否则零税码可能就取不到了，设置税码时自己判断是否税码重复吧
    // 设置税率和逆向征税
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
  }

}
