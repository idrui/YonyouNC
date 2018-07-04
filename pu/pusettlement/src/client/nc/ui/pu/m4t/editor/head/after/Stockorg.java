/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 上午10:50:51
 */
package nc.ui.pu.m4t.editor.head.after;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.rule.CostregionSetter;
import nc.vo.pu.m4t.rule.DefaultReceCountrySetter;
import nc.vo.pu.m4t.rule.DefaultTaxCountrySetter;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存组织
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 上午10:50:51
 */
public class Stockorg implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    // 仓库设为空
    panel.setHeadItem(InitialEstHeaderVO.PK_STORDOC, null);
    // 成本域
    panel.setHeadItem(InitialEstHeaderVO.PK_COSTREGION, null);
    // 保管员
    panel.setHeadItem(InitialEstHeaderVO.PK_MANAGEPSN, null);

    panel.getHeadItem(InitialEstHeaderVO.PK_MANAGEPSN).setEnabled(true);
    panel.getHeadItem(InitialEstHeaderVO.PK_STORDOC).setEnabled(true);
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    new CostregionSetter(editor).setCostregion();
    // 结算利润中心设值逻辑是根据业务委托关系设置同采购订单表体结算利润中心，不需要编辑后事件
    // new PCCostregionSetter(editor).setPK_apliabcenter();
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

    // 设置收货国家
    Set<Integer> recChangeSet =
        new DefaultReceCountrySetter().setReceCountry(editor, rows);
    // 设置报税国家
    Set<Integer> taxChangeSet =
        new DefaultTaxCountrySetter().setTaxCountry(editor, rows);
    int[] countrychangerows = this.getChangeRows(recChangeSet, taxChangeSet);
    if (null == countrychangerows) {
      return;
    }
    // 税率
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
  }

  private int[] getChangeRows(Set<Integer> recChangeSet,
      Set<Integer> taxChangeSet) {
    Set<Integer> set = null;
    if (recChangeSet != null) {
      set = recChangeSet;
    }
    if (set == null) {
      set = taxChangeSet;
    }
    else if (taxChangeSet != null) {
      set.addAll(taxChangeSet);
    }
    if (set == null || set.size() == 0) {
      return null;
    }

    Integer[] rows = set.toArray(new Integer[set.size()]);
    return ArrayUtils.toPrimitive(rows);
  }

}
