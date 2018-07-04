package nc.ui.pu.m25.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m25.entity.InvoiceVO;

/**
 * @since 6.0
 * @version 2011-6-21 ÏÂÎç01:39:59
 * @author wuxla
 */

public class InitDataForTbbProcessor implements IInitDataProcessor {
  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    InvoiceVO[] vos = (InvoiceVO[]) data.getInitData();
    this.model.initModel(vos);
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
