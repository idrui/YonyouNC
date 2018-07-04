/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 下午07:59:39
 */
package nc.ui.pu.m4t.editor.body.after;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m4t.rule.EditableByUnit;
import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.rule.AssistUnit;
import nc.vo.pu.m4t.rule.DefaultReceCountrySetter;
import nc.vo.pu.m4t.rule.DefaultSendCountrySetter;
import nc.vo.pu.m4t.rule.DefaultTaxCountrySetter;
import nc.vo.pu.m4t.rule.OrganizationValue;
import nc.vo.pu.m4t.rule.UnitAndChangeRate;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 下午07:59:39
 */
public class Material implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    // 物料的多选处理
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(panel);
    int[] rows =
        utils.refMoreSelected(event.getRow(), InitialEstItemVO.PK_MATERIAL,
            true);

    CardEditorHelper card = new CardEditorHelper(panel);
    
    //清空批次号信息
    this.clearBatchCode(panel, rows);

    // 组织组织
    new OrganizationValue(card).setFinanceOrg(rows);

    // 设置单位
    new AssistUnit(card).setAssistUnit(rows);

    // 换算率
    new UnitAndChangeRate(card).setChangeRate(rows);

    // 换算率可编辑性
    new EditableByUnit(panel);

    List<Integer> list = new ArrayList<Integer>();
    for (int i : rows) {
      String csourcetype =
          (String) panel.getBodyValueAt(i, InitialEstItemVO.CSOURCETYPECODE);
      if (StringUtils.isEmpty(csourcetype)) {
        list.add(Integer.valueOf(i));
      }
    }
    if (list.size() == 0) {
      return;
    }
    rows = ArrayUtils.toPrimitive(list.toArray(new Integer[list.size()]));
    // 设置发货国家
    new DefaultSendCountrySetter().setSendCountry(card, rows);
    // 设置收货国家
    new DefaultReceCountrySetter().setReceCountry(card, rows);
    // 设置报税国家
    new DefaultTaxCountrySetter().setTaxCountry(card, rows);
    // 税码
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
    // // 设置利润中心
    // new PCCostregionSetter(card).setPK_apliabcenter();
  }
  
  private void clearBatchCode(BillCardPanel panel,int[] rows){
  	for (int i : rows) {
			panel.setBodyValueAt(null, i, InitialEstItemVO.VBATCHCODE);
			panel.setBodyValueAt(null, i, InitialEstItemVO.PK_BATCHCODE);
		}
  }
  
}
