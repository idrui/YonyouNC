package nc.ui.pu.m21.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.rule.ReceivePlanScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.VOScaleProcessor;

/**
 * @since 6.0
 * @version 2011-4-16 上午08:30:14
 * @author wuxla
 */

public class ReceivePlanPrintProcessor implements IBeforePrintDataProcess {
  private BatchBillTableModel model;

  public BatchBillTableModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processData(Object[] datas) {
    OrderReceivePlanVO[] vos = ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processScale(vos);
    return vos;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
  }

  private void processScale(OrderReceivePlanVO[] vos) {
    VOScaleProcessor scale =
        new VOScaleProcessor(this.getModel().getContext().getPk_group(), vos);
    new ReceivePlanScaleRule().setScale(scale);
  }
}
