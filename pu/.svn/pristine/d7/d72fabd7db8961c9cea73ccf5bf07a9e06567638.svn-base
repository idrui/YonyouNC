package nc.ui.pu.m20.billref.invp.m4f;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * 为库存计划计划订单打开请购单界面提供的处理器
 * 
 * @since 6.0
 * @version 2011-12-6 下午02:55:20
 * @author 田锋涛
 */

public class InitDataProcessor4Fto20 implements IInitDataProcessor {

  private TransferViewProcessor processor;

  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  @Override
  public void process(FuncletInitData data) {
    this.getProcessor().getBillForm().getModel().initModel(null);
    PraybillVO[] prayBillVOs = (PraybillVO[]) data.getInitData();
    this.getProcessor().processBillTransfer(prayBillVOs);
  }

  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }
}
