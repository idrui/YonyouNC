package nc.ui.pu.m21.action.payplan;

import java.util.List;

import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;

/**
 * @since 6.0
 * @version 2011-5-22 ÉÏÎç10:56:59
 * @author wuxla
 */

public class OrderPayPlanPrintAction extends MetaDataBasedPrintAction {

  public class OrderPayPlanPrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = -599162188242635618L;

    @Override
    public Object[] getMDObjects() {
      Object[] datas = this.getPayPlanPrintData();
      if (OrderPayPlanPrintAction.this.getBeforePrintDataProcess() != null) {
        OrderPayPlanPrintAction.this.getBeforePrintDataProcess().processData(
            datas);
      }
      return datas;
    }

    private Object[] getPayPlanPrintData() {
      BatchBillTableModel payplanModel =
          (BatchBillTableModel) OrderPayPlanPrintAction.this.getModel();
      List<Object> obj = payplanModel.getRows();
      AggPayPlanVO[] printVOs = new AggPayPlanVO[obj.size()];
      for (int i = 0; i < obj.size(); ++i) {
        PayPlanViewVO view = (PayPlanViewVO) obj.get(i);
        OrderHeaderVO headerVO =
            (OrderHeaderVO) view.getVO(OrderHeaderVO.class);
        PayPlanVO payplanVO = (PayPlanVO) view.getVO(PayPlanVO.class);
        printVOs[i] = new AggPayPlanVO();
        printVOs[i].setParent(headerVO);
        printVOs[i].setChildrenVO(new PayPlanVO[] {
          payplanVO
        });
      }
      return printVOs;
    }

  }

  private static final long serialVersionUID = 8895339085590261502L;

  @Override
  public IMetaDataDataSource getDataSource() {
    return new OrderPayPlanPrintDataSource();
  }
}
