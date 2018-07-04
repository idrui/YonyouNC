/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午02:39:15
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderReceivePlan;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.batch.BatchRefreshAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

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
 * @time 2010-4-19 下午02:39:15
 */
public class RPBatchRefreshAction extends BatchRefreshAction {

  private static final long serialVersionUID = 6789234411976869601L;

  private BatchBillTable list;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
    OrderVO orderVO = model.getOrderVO();
    String pkOrder = orderVO.getHVO().getPk_order();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    IOrderReceivePlan rp =
        NCLocator.getInstance().lookup(IOrderReceivePlan.class);
    OrderReceivePlanVO[] rpVOs = rp.queryPlanVOsByHId(pkOrder);
    if (ArrayUtils.isEmpty(rpVOs)) {
      model.initModel(null);
      return;
    }
    for (OrderReceivePlanVO rpVO : rpVOs) {
      // rpVO.setCrownobb1(map.get(rpVO.getPk_order_b()).getCrowno());
      rpVO.setCrownobb1(((OrderItemVO) index.get(meta, rpVO.getPk_order_b()))
          .getCrowno());
    }

    model.initModel(rpVOs);

    this.setCrowno();

    this.getList().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
    this.getList().getBillCardPanel().getBillModel().execLoadFormula();
  }

  /**
   * @return list
   */
  public BatchBillTable getList() {
    return this.list;
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  private void setCrowno() {
    BillCardPanel panel = this.getList().getBillCardPanel();
    int rowCount = panel.getRowCount();
    if (0 == rowCount) {
      return;
    }
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });

    for (int i = 0; i < rowCount; ++i) {
      String pkOrderB =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.PK_ORDER_B);
      // String crowno = map.get(pkOrderB).getCrowno();
      String crowno =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.CROWNOBB1);
      DefaultConstEnum value = new DefaultConstEnum(pkOrderB, crowno);
      panel.setBodyValueAt(value, i, OrderItemVO.CROWNO);
    }
  }
}
