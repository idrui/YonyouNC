package nc.pubitf.pu.m21.api.rest;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.util.translate.BillTranslator;
import nc.vo.scmpub.util.translate.MDTranslateParamProvider;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年11月14日 下午3:09:45
 * @author wangweir
 */

public class PurchaseOrderTranslateAdaptor {

  public OrderVO[] doTranslate(OrderVO[] bills) {
    MDTranslateParamProvider<IBill> provider = new MDTranslateParamProvider<>();

    BillTranslator billTanslator = new BillTranslator();
    billTanslator.translateCodeToId(bills, provider);
    return bills;
  }
}
