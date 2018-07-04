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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶����͵���������ʽ�����빺�����ݳ�ʼ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-24 ����11:01:08
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
        // ���þ���
        new PraybillScaleUtil().orgChgScale(editor);
        editor.getModel().initModel(null);
        this.processor.processBillTransfer(vos);
      }
    }

  }

  /**
   * @param processor
   *          Ҫ���õ� processor
   */
  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }

}
