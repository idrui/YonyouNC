package nc.vo.pu.est.rule;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TaxValue;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2012-3-10 下午01:48:02
 * @author wuxla
 */
public class VmiGoodsVatValue {
  // 只有消耗汇总用
  public void setVatValue(VmiEstVO[] vos) {
    IKeyValue[] keyValues = new IKeyValue[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      keyValues[i] = new AggVOHelper<VmiEstVO>(vos[i]);
    }
    // 设置购销类型
    // 因为消耗汇总不支持跨国，没有影响
    new BuysellflagSetter().setHeadBuysellFlag(keyValues);
    new TriatradeflagSetter().setHeadTriatradeflag(keyValues);

    // 税码
    this.setTaxCode(vos);
  }

  private VATInfoQueryVO getTaxQueryVO(VmiEstHeaderVO headVO) {
    String pk_material = headVO.getPk_material();
    String ctaxcountryid = headVO.getCtaxcountryid();
    Integer fbuysellfalg = headVO.getFbuysellflag();
    UFBoolean btriatradeflag = headVO.getBtriatradeflag();
    String csendcountryid = headVO.getCsendcountryid();
    String crececountryid = headVO.getCrececountryid();
    String pk_supplier = headVO.getPk_supplier();
    UFDate dbilldate = headVO.getEstDate();
    String pk_org = headVO.getPk_financeorg();

    return new VATInfoQueryVO(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg), btriatradeflag, csendcountryid,
        crececountryid, pk_supplier, pk_material, dbilldate, pk_org);
  }

  private void setTaxCode(VmiEstVO[] vos) {
    VATInfoQueryVO[] queryvos = new VATInfoQueryVO[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      queryvos[i] = this.getTaxQueryVO(vos[i].getParentVO());
    }
    TaxValue value = new TaxValue(queryvos);
    value.prepare();
    value.process();
    VATInfoVO[] vatvos = value.getVatinfos();
    if (ArrayUtils.isEmpty(vatvos)) {
      return;
    }

    for (int i = 0; i < vos.length; ++i) {
      if (null == vatvos[i]) {
        continue;
      }
      VmiEstHeaderVO headvo = vos[i].getParentVO();
      headvo.setCesttaxcodeid(vatvos[i].getCtaxcodeid());
      headvo.setFesttaxtype(vatvos[i].getFtaxtypeflag());
      headvo.setNestnosubtaxrate(vatvos[i].getNnosubtaxrate());
      headvo.setNesttaxrate(vatvos[i].getNtaxrate());
    }
  }
}
