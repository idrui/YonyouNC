package nc.ui.pu.pub.lazilyload;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pub.ISuperVO;

public class PUCardPanelLazilyLoad extends CardPanelLazilyLoad {

  private List<Class<? extends ISuperVO>> children;

  public List<Class<? extends ISuperVO>> getChildren() {
    if (this.children == null) {
      this.children = new ArrayList<Class<? extends ISuperVO>>();
      this.children.add(OrderItemVO.class);
      this.children.add(OrderPaymentVO.class);
    }
    return this.children;
  }

  @Override
  public List<Class<? extends ISuperVO>> getCurrentChildren() {
    return this.getChildren();
  }
}
