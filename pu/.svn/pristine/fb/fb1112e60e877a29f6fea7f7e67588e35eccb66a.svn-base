/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-19 上午10:48:31
 */
package nc.ui.pu.m422x.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.StoreReqScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>打印前处理，如精度等
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-19 上午10:48:31
 */
public class StoreReqPrintProcessor implements IBeforePrintDataProcess {

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
    // 转化为物资需求vo
    StoreReqAppVO[] vos = (StoreReqAppVO[]) ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processScale(vos);
    return vos;
  }

  /**
   * @param model 要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * 处理精度
   * 
   * @param bills
   */
  private void processScale(StoreReqAppVO[] bills) {
    BillVOScaleProcessor scaleProcessor =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            bills);
    TotalValueVOScaleProcessor totalScale =
        new TotalValueVOScaleProcessor(bills);
    new StoreReqScaleRule().setScale(scaleProcessor, totalScale);
  }

}
