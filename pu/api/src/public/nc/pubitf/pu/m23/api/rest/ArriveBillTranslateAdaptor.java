package nc.pubitf.pu.m23.api.rest;

import nc.vo.pu.m23.entity.ArriveVO;
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
 * @version 2015��11��14�� ����3:09:45
 * @author wangweir
 */

public class ArriveBillTranslateAdaptor {

  public ArriveVO[] doTranslate(ArriveVO[] bills) {
    MDTranslateParamProvider<IBill> provider = new MDTranslateParamProvider<>();

    BillTranslator billTanslator = new BillTranslator();
    billTanslator.translateCodeToId(bills, provider);
    return bills;
  }
}
