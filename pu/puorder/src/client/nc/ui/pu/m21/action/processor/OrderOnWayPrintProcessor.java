package nc.ui.pu.m21.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.rule.OrderOnWayScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

public class OrderOnWayPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model = null;

  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processData(Object[] datas) {
    // 转化为订单在途vo
    OrderOnwayVO[] vos = (OrderOnwayVO[]) ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processScale(vos);
    return vos;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private void processScale(OrderOnwayVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    TotalValueVOScaleProcessor totalScale = new TotalValueVOScaleProcessor(vos);
    new OrderOnWayScaleRule().setScale(scale, totalScale);
  }

}
