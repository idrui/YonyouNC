package nc.ui.pu.m20.billref.tbb;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * @since 6.0
 * @version 2011-6-21 ÏÂÎç12:06:13
 * @author wuxla
 */

public class TbbInitDataProcessor implements IInitDataProcessor {
  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    Object initParam = data.getInitData();
    PraybillVO[] vos = (PraybillVO[]) initParam;
    this.model.initModel(vos);
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
