/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 上午11:01:08
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>资产维修工单推式生成请购单数据初始化
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-24 上午11:01:08
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

  private PraybillVO[] setDefaultValue(PraybillVO[] vos) {
    // 设置默认值
    new SetDefaultValueRule().process(vos);

    // 下面的语句在ChgAfterTO20中已经被调用过了，所以在此不需要调用，在此调用的话，会导致在vo交换后的尾差处理失效。

    // try {
    // return NCLocator.getInstance().lookup(IPraybillMaintain.class)
    // .setDefaultValue(vos);
    // }
    // catch (BusinessException e) {
    // // 日志异常
    // ExceptionUtils.wrappException(e);
    // }
    return vos;

  }
}
