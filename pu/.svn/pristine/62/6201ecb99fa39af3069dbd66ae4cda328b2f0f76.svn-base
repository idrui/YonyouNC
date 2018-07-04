/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午02:31:24
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

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
 * @time 2010-4-19 下午02:31:24
 */
public class RPBatchCancelAction extends BatchCancelAction {

  private static final long serialVersionUID = 8281696049726698191L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchCancelAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    this.setCrowno();

    this.getEditor().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
    this.getEditor().getBillCardPanel().getBillModel().execLoadFormula();
  }

  /**
   * 方法功能描述：设置行号
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 下午02:35:11
   */
  private void setCrowno() {
    BillCardPanel panel = this.getEditor().getBillCardPanel();
    // OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
    // OrderVO orderVO = model.getOrderVO();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    int rowCount = panel.getRowCount();
    for (int i = 0; i < rowCount; ++i) {
      // String pkOrderB =
      // (String) panel.getBodyValueAt(i, OrderReceivePlanVO.PK_ORDER_B);
      // String crowno = map.get(pkOrderB).getCrowno();
      String crowno =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.CROWNOBB1);
      panel.setBodyValueAt(crowno, i, "crowno");
    }
  }
}
