/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 下午07:44:45
 */
package nc.ui.pu.m422x.editor.card.afteredit.body;

import nc.ui.pu.m422x.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m422x.rule.DefaultPriceSetter;
import nc.ui.pu.m422x.rule.EditableByUnit;
import nc.ui.pu.m422x.rule.ReqDateSetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.rule.AppDeptSetter;
import nc.vo.pu.m422x.rule.AppPsnSetter;
import nc.vo.pu.m422x.rule.AssistUnit;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.m422x.rule.NextbalanceorgSetter;
import nc.vo.pu.m422x.rule.OrganizationValue;
import nc.vo.pu.m422x.rule.ProjectSetter;
import nc.vo.pu.m422x.rule.UnitAndChangeRate;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 下午07:44:45
 */
public class Material implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 物料的多选处理
    RefMoreSelectedUtils utils =
        new RefMoreSelectedUtils(event.getBillCardPanel());
    int[] rows =
        utils.refMoreSelected(event.getRow(), StoreReqAppItemVO.PK_MATERIAL,
            true);

    if (ArrayUtils.isEmpty(rows)) {
      return;
    }

    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());

    //清空批次号信息
    this.clearBatchCode(event.getBillCardPanel(), rows);
    
    //清空表体单价和金额
    this.clearNmnyAndPrice(event.getBillCardPanel(),event.getRow());
    
    new OrganizationValue(card).setStockOrg(rows);

    new AppDeptSetter(card).setBodyAppDept(rows);

    new AppPsnSetter(card).setBodyAppPsn(rows);

    new ProjectSetter(card).setBodyProject(rows);

    new AssistUnit(card).setAssistUnit(rows);

    new UnitAndChangeRate(card).setChangeRate(rows);
    this.relationCalculate(event.getBillCardPanel(), rows);

    new ReqDateSetter(card).setReqDate(rows);

    new CurrencySetter(card).setCurrency(rows);

    new EditableByUnit(event.getBillCardPanel()).setEditable(rows);

    // 物料取价
    new DefaultPriceSetter(event.getBillCardPanel()).setMaterialPrice(rows);

    // 下次平衡库存组织
    NextbalanceorgSetter nextsetter = new NextbalanceorgSetter(card);
    nextsetter.setBclear(false);
    nextsetter.setNextbalanceorg(rows);
  }

  private void relationCalculate(BillCardPanel panel, int[] rows) {
    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, rows, StoreReqAppItemVO.VCHANGERATE);
  }

  private void clearBatchCode(BillCardPanel panel, int[] rows){
  	for (int i : rows) {
			panel.setBodyValueAt(null, i, StoreReqAppItemVO.VBATCHCODE);
			panel.setBodyValueAt(null, i, StoreReqAppItemVO.PK_BATCHCODE);
		}
  }
  
  private void clearNmnyAndPrice(BillCardPanel panel, int row){
  	panel.setBodyValueAt(null, row, StoreReqAppItemVO.NTAXMNY);
  	panel.setBodyValueAt(null, row, StoreReqAppItemVO.NTAXPRICE);
  }
}
