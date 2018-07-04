package nc.ui.pu.m21.action.payplan;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PULinkQueryAction;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-6-16 ÏÂÎç12:46:37
 * @author wuxla
 */

public class OrderPayPlanLinkQueryAction extends PULinkQueryAction {
  static class OrderPayPlanBillInfoFactory implements IBillInfoFactory<Object> {

    @Override
    public IBillInfo getBillInfo(Object t) {
      final PayPlanViewVO view = (PayPlanViewVO) t;
      return new IBillInfo() {

        @Override
        public String getBillCode() {
          return view.getVbillcode();
        }

        @Override
        public String getBillId() {
          return view.getPk_order();
        }

        @Override
        public String getBillType() {
          return POBillType.Order.getCode();
        }

      };
    }
  }

  private static final long serialVersionUID = 5162252725426116040L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderPayPlanBillInfoFactory factory = new OrderPayPlanBillInfoFactory();
    this.setBillInfoFactory(factory);
    super.doAction(e);
  }
}
