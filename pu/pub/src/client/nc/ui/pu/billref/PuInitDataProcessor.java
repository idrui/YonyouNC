package nc.ui.pu.billref;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;

/**
 * 项目推单实现的公共类
 * 
 * @since 6.0
 * @version 2012-3-14 上午11:34:15
 * @author yangtian
 */
public class PuInitDataProcessor implements IInitDataProcessor {
  private TransferViewProcessor processor;

  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  @Override
  public void process(FuncletInitData data) {
    // 必须先清空才能触发事件
    this.getProcessor().getBillForm().getModel().initModel(null);
    Object[] objs = (Object[]) data.getInitData();
    this.getProcessor().processBillTransfer(objs);
  }

  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }

}
