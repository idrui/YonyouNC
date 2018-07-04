/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 下午04:29:45
 */
package nc.ui.pu.m27.settlebill.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.rule.SettleBillScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单打印前处理，如精度等
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-11-3 下午04:29:45
 */
public class SettleBillPrintProcessor implements IBeforePrintDataProcess {

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
    // 转化为结算单vo
    SettleBillVO[] vos = (SettleBillVO[]) ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processScale(vos);
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
   * @time 2010-11-3 下午04:51:10
   */
  private void processScale(SettleBillVO[] vos) {
    BillVOScaleProcessor voScale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    new SettleBillScaleRule().setBodyScale(voScale);
  }
}
