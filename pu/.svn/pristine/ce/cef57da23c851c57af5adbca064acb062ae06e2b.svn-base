package nc.ui.pu.m20.editor.card.afteredit;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.CalculateBodyDays;
import nc.vo.pu.pub.calculate.PuSimpleCalCondition;
import nc.vo.pu.pub.calculate.PuSimpleCalculateDataSet;
import nc.vo.pu.pub.calculate.PuSimpleCalculator;
import nc.vo.pu.pub.calculate.PuSimpleRelationItems;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算
 * </ul>
 * <p>
 * 
 * @param <E>
 *          <p>
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午04:28:31
 */
public class RelationCalculate extends AbstractRelationCalculateListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    // 联动计算
    this.calculate(e.getBillCardPanel(), e.getRow(), e.getKey());

    if (PraybillItemVO.NNUM.equals(e.getKey())
        || PraybillItemVO.NASTNUM.equals(e.getKey())) {
      CardEditorHelper kv = new CardEditorHelper(e.getBillCardPanel());
      // 需求日期和建议订货日期处理
      new CalculateBodyDays().setAdvDays(kv, new int[] {
        e.getRow()
      });
    }
  }

  @Override
  public void calculate(BillCardPanel panel, int row, String itemKey) {
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardEditorHelper bill = new CardEditorHelper(panel);
    PuSimpleCalculator calculator =
        new PuSimpleCalculator(new PuSimpleCalculateDataSet(bill, row,
            new PuSimpleRelationItems()), scale);
    PuSimpleCalCondition condition = new PuSimpleCalCondition();
    String material =
        (String) panel.getBodyValueAt(row, PraybillItemVO.PK_MATERIAL);
    String cunitid = (String) panel.getBodyValueAt(row, PraybillItemVO.CUNITID);
    String castunitid =
        (String) panel.getBodyValueAt(row, PraybillItemVO.CASTUNITID);
    // 设置是否固定单位换算率
    condition.setIsfixedChangeRate(this.isFixUnitRate(material, cunitid,
        castunitid));
    calculator.calculate(condition, itemKey);
  }

  @Override
  public Condition getCalculatorCondition(BillCardPanel panel, int row) {
    return null;
  }

  private boolean isFixUnitRate(String material, String cunitid,
      String castunitid) {
    boolean flag = true;
    if (material == null || cunitid == null || castunitid == null) {
      return flag;
    }
    flag =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
            cunitid, castunitid);
    return flag;
  }
}
