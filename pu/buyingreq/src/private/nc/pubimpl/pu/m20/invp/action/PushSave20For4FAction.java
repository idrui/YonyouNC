package nc.pubimpl.pu.m20.invp.action;

import nc.pubimpl.pu.m20.mm.action.PushSave20For55B4Action;
import nc.vo.scmpub.res.billtype.INVPBillType;

/**
 * 请购单为库存计划计划订单的推式保存action
 * 
 * @since 6.0
 * @version 2011-12-13 上午11:11:57
 * @author 田锋涛
 */

public class PushSave20For4FAction extends PushSave20For55B4Action {
  // 逻辑与制造过来的计划订单一样，继承之
  // 逻辑与制造过来的计划订单一样，继承之
  @Override
  protected String getBillCode() {
    return INVPBillType.PoOrder.getCode();

  }
}
