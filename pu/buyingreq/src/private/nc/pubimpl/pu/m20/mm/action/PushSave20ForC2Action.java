package nc.pubimpl.pu.m20.mm.action;

import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * 离散生产订单产能分流推式保存请购单
 * 
 * @since 6.3
 * @version 2012-10-31 下午03:10:01
 * @author fanly3
 */
public class PushSave20ForC2Action extends PushSave20ForA2Action {

  @Override
  protected String getBillCode() {
    return MMBillType.LsProduceOrder.getCode();
  }
}
