/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:31:24
 */
package nc.ui.pu.m422x.editor.card.afteredit;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.calculate.PuSimpleCalCondition;
import nc.vo.pu.pub.calculate.PuSimpleCalculateDataSet;
import nc.vo.pu.pub.calculate.PuSimpleCalculator;
import nc.vo.pu.pub.calculate.PuSimpleRelationItems;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:31:24
 */
public class RelationCalculate implements IAppEventHandler<AppEvent> {

  public void calculate(BillCardPanel panel, int[] rows, String itemKey) {
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardEditorHelper bill = new CardEditorHelper(panel);
    for (int row : rows) {
      PuSimpleCalculator calculator =
          new PuSimpleCalculator(new PuSimpleCalculateDataSet(bill, row,
              new PuSimpleRelationItems()), scale);
      PuSimpleCalCondition condition = new PuSimpleCalCondition();
      String material =
          (String) panel.getBodyValueAt(row, StoreReqAppItemVO.PK_MATERIAL);
      String cunitid =
          (String) panel.getBodyValueAt(row, StoreReqAppItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(row, StoreReqAppItemVO.CASTUNITID);
      // 设置是否固定单位换算率
      condition.setIsfixedChangeRate(this.isFixUnitRate(material, cunitid,
          castunitid));
      calculator.calculate(condition, itemKey);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
   */
  @Override
  public void handleAppEvent(AppEvent e) {
    if (e instanceof CardBodyAfterEditEvent) {
      CardBodyAfterEditEvent event = (CardBodyAfterEditEvent) e;
      String itemKey = event.getKey();
      BillCardPanel panel = event.getBillCardPanel();
      if (itemKey == null || panel == null) {
        return;
      }
      int[] rows = new int[1];
      rows[0] = event.getRow();
      this.calculate(panel, rows, itemKey);
    }
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
