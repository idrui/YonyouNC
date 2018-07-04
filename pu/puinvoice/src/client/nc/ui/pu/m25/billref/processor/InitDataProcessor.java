/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 上午10:56:57
 */
package nc.ui.pu.m25.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m25.entity.InvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>转单处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-9-25 上午10:56:57
 */
public class InitDataProcessor implements IInitDataProcessor {

  private TransferViewProcessor transferViewProcessor;

  public TransferViewProcessor getTransferViewProcessor() {
    return this.transferViewProcessor;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor#process(nc.funcnode.ui.FuncletInitData)
   */
  @Override
  public void process(FuncletInitData data) {
    InvoiceVO[] invoiceVOs = (InvoiceVO[]) data.getInitData();
    this.getTransferViewProcessor().processBillTransfer(invoiceVOs);

  }

  public void setTransferViewProcessor(
      TransferViewProcessor transferViewProcessor) {
    this.transferViewProcessor = transferViewProcessor;
  }

}
