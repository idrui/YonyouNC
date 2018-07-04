package nc.vo.pu.est.rule;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 逆向征税标记
 * 
 * @since 6.0
 * @version 2012-3-10 下午12:30:06
 * @author wuxla
 */
public class GoodsEstOpptaxflagSetter {
  /**
   * 逆向征税
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param vos
   * @param pk_financeorg
   */
  public void setOpptaxFlag(EstVO[] vos, String pk_financeorg) {
    int length = vos.length;
    GoodsEstVO[] goodsvos = new GoodsEstVO[length];
    OppTaxFlagQueryVO[] opptaxqueryvos = new OppTaxFlagQueryVO[length];
    for (int i = 0; i < length; ++i) {
      goodsvos[i] = vos[i].getParentVO();
      opptaxqueryvos[i] = this.getOppTaxQueryVO(goodsvos[i], pk_financeorg);
    }

    VATCodeTaxValue value = new VATCodeTaxValue(null, opptaxqueryvos);
    value.prepare();
    value.process();
    UFBoolean[] opptaxflags = value.getOpptaxflags();
    if (!ArrayUtils.isEmpty(opptaxflags)) {
      for (int i = 0; i < length; ++i) {
        goodsvos[i].setBopptaxflag(opptaxflags[i]);
      }
    }
  }

  private OppTaxFlagQueryVO getOppTaxQueryVO(GoodsEstVO goodsvo,
      String pk_financeorg) {
    String ctaxcountryid = goodsvo.getCtaxcountryid();
    Integer fbuysellfalg = goodsvo.getFbuysellflag();
    return new OppTaxFlagQueryVO(pk_financeorg, ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg));
  }
}
