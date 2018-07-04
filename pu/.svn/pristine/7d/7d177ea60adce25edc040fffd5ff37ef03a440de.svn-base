/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午09:57:23
 */
package nc.ui.pu.m21.action.initdata;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.pu.m21.IOrderReceivePlan;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.m21.view.rp.refmodel.PoRPRowNoModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

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
 * @time 2010-4-14 下午09:57:23
 */
public class RPInitData implements IFuncNodeInitDataListener {

  private BatchBillTable list;

  private BatchBillTableModel model;

  /**
   * @return list
   */
  public BatchBillTable getList() {
    return this.list;
  }

  /**
   * @return model
   */
  public BatchBillTableModel getModel() {
    return this.model;
  }

  @Override
  public void initData(FuncletInitData data) {
    OrderVO orderVO = (OrderVO) data.getInitData();
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    ((OrderReceivePlanModel) this.getModel()).setOrderVO(orderVO);

    this.initCrownoRefPane(orderVO);

    IOrderReceivePlan rp =
        NCLocator.getInstance().lookup(IOrderReceivePlan.class);
    OrderReceivePlanVO[] rpVOs = null;
    try {
      rpVOs = rp.queryPlanVOsByHId(orderVO.getHVO().getPk_order());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == rpVOs) {
      this.getModel().initModel(null);
      return;
    }

    for (OrderReceivePlanVO rpVO : rpVOs) {
      // rpVO.setCrownobb1(itemMap.get(rpVO.getPk_order_b()).getCrowno());
      rpVO.setCrownobb1(((OrderItemVO) index.get(meta, rpVO.getPk_order_b()))
          .getCrowno());
    }

    this.getModel().initModel(rpVOs);

    this.list.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
    this.list.getBillCardPanel().getBillModel().execLoadFormula();

    // this.initCrownoRefPane(orderVO);

    this.setCrowno();
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BatchBillTableModel model) {
    this.model = model;
  }

  /**
   * 方法功能描述：设置行号参照
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午04:05:35
   */
  private void initCrownoRefPane(OrderVO orderVO) {
    UIRefPane pane =
        (UIRefPane) this.list.getBillCardPanel().getBodyItem("crowno")
            .getComponent();
    pane.setMultiSelectedEnabled(true);
    // pane.setAutoCheck(false);
    pane.setRefModel(new PoRPRowNoModel(orderVO, this.list.getBillCardPanel()));
  }

  /**
   * 方法功能描述：设置行号
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午04:15:04
   */
  private void setCrowno() {
    BillCardPanel panel = this.list.getBillCardPanel();
    int rowCount = panel.getRowCount();
    if (0 == rowCount) {
      return;
    }

    for (int i = 0; i < rowCount; ++i) {
      String crowno =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.CROWNOBB1);
      // if (StringUtil.isEmptyWithTrim(pkOrderB)) {
      // continue;
      // }
      // panel.setBodyValueAt(crowno, i, "crowno");
      // UIRefPane pane = (UIRefPane)
      // panel.getBodyItem("crowno").getComponent();

      String pk_order_b =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.PK_ORDER_B);
      DefaultConstEnum value = new DefaultConstEnum(pk_order_b, crowno);
      panel.setBodyValueAt(value, i, "crowno");
      // pane.setValueObj(pk_order_b);
    }
  }
}
