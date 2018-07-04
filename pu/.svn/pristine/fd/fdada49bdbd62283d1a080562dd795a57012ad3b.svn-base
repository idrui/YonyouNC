package nc.ui.pu.m20.billref.so.m30;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.billref.push.NodeOpenBillInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单和调拨订单推式生成请购单数据初始化
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-24 上午11:01:08
 */
public class M30InitDataProcessor implements IInitDataProcessor {

  private TransferViewProcessor processor;

  /**
   * @return processor
   */
  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  @Override
  public void process(FuncletInitData data) {
    String type = "";
    if (data instanceof NodeOpenBillInitData) {
      type = ((NodeOpenBillInitData) data).getSourceBillType();
    }

    if (SOBillType.Order.getCode().equals(type)
        || TOBillType.TransOrder.getCode().equals(type)
        || ETBillType.CONTRACT.getCode().equals(type)) {

      PraybillVO[] vos = (PraybillVO[]) data.getInitData();
      if (null != vos) {

        // this.setUFDouble(vos);
        // vos = this.setDefaultValue(vos);

        BillForm editor = this.processor.getBillForm();
        // 设置精度
        new PraybillScaleUtil().orgChgScale(editor);
        editor.getModel().initModel(null);
        this.processor.processBillTransfer(vos);
      }
    }

  }

  /**
   * @param processor
   *          要设置的 processor
   */
  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }

}
