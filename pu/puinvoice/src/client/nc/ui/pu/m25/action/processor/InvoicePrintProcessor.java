/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-10 上午11:56:54
 */
package nc.ui.pu.m25.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceScaleProcessor;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>打印前的处理，主要是精度处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-7-10 上午11:56:54
 */
public class InvoicePrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model = null;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess#processData(java.lang.Object[])
   */
  @Override
  public Object[] processData(Object[] datas) {
    // 转化为发票vo
    InvoiceVO[] vos = (InvoiceVO[]) ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processSacle(vos);
    return vos;

  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * 方法功能描述：精度处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-11-3 下午04:58:17
   */
  private void processSacle(InvoiceVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    TotalValueVOScaleProcessor totalScale = new TotalValueVOScaleProcessor(vos);
    new InvoiceScaleProcessor().setScale(scale, totalScale);
  }

}
