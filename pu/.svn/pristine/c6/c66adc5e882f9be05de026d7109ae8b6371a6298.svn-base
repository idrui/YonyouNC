package nc.ui.pu.m21.action.initdata;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pu.m21.entity.PayPlanViewVO;

/**
 * @since 6.0
 * @version 2011-1-18 ÏÂÎç02:23:55
 * @author wuxla
 */

public class PayPlanInitData implements IFuncNodeInitDataListener {
  private BatchBillTable list;

  private BatchBillTableModel model;

  public BatchBillTable getList() {
    return this.list;
  }

  public BatchBillTableModel getModel() {
    return this.model;
  }

  @Override
  public void initData(FuncletInitData data) {
    if (null == data) {
      this.getModel().initModel(null);
      return;
    }
    Object obj = data.getInitData();
    if (null == obj) {
      this.getModel().initModel(null);
      return;
    }

    PayPlanViewVO[] views = (PayPlanViewVO[]) obj;
    this.getModel().initModel(views);
  }

  public void setList(BatchBillTable list) {
    this.list = list;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
  }

}
