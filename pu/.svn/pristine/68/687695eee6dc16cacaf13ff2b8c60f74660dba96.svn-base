package nc.ui.pu.m21.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.rule.PayPlanScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;

/**
 * @since 6.0
 * @version 2011-1-6 ÏÂÎç07:44:58
 * @author wuxla
 */

public class PayPlanPrintProcesser implements IBeforePrintDataProcess {
  private AbstractAppModel model;

  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processData(Object[] datas) {
    AggPayPlanVO[] vos = (AggPayPlanVO[]) ArrayUtil.convertArrayType(datas);
    this.processScale(vos);
    return vos;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private void processScale(AggPayPlanVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    new PayPlanScaleRule().setScale(scale, null);
  }

}
