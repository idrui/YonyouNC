/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����11:01:08
 */
package nc.ui.pu.m20.billref.am.m4b36;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.SetDefaultValueRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ʲ�ά�޹�����ʽ�����빺�����ݳ�ʼ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-24 ����11:01:08
 */
public class InitDataProcessor implements IInitDataProcessor {

  private TransferViewProcessor processor;

  /**
   * @return processor
   */
  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  @Override
  public void process(FuncletInitData data) {
    int type = data.getInitType();
    if (type == 36) {
      PraybillVO[] vos = (PraybillVO[]) data.getInitData();
      if (null != vos) {
        vos = this.setDefaultValue(vos);
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

  private PraybillVO[] setDefaultValue(PraybillVO[] vos) {
    // ����Ĭ��ֵ
    new SetDefaultValueRule().process(vos);

    // ����������ChgAfterTO20���Ѿ������ù��ˣ������ڴ˲���Ҫ���ã��ڴ˵��õĻ����ᵼ����vo�������β���ʧЧ��

    // try {
    // return NCLocator.getInstance().lookup(IPraybillMaintain.class)
    // .setDefaultValue(vos);
    // }
    // catch (BusinessException e) {
    // // ��־�쳣
    // ExceptionUtils.wrappException(e);
    // }
    return vos;

  }
}
