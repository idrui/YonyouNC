package nc.ui.pu.m21.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * @since 6.0
 * @version 2011-6-20 ÏÂÎç04:22:39
 * @author wuxla
 */

public class InitDataProcessorForTbb implements IInitDataProcessor {
  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    Object initParam = data.getInitData();
    OrderVO[] vos = (OrderVO[]) initParam;
    this.model.initModel(vos);
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
