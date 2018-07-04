package nc.vo.pu.pub.rule.vat;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.StringUtils;

/**
 * 根据VAT税信息，设置单据对应的表体行<br>
 * 当税码与原有税码一致时，则不会设置新税率等值[forceSet＝true时强制设置税信息，不考虑税码是否变化]
 * 
 * @since 6.0
 * @version 2012-2-23 下午4:55:18
 * @author zhaoyha
 */
public class BillItemVatTaxInfoSetter {
  private IKeyValue bill;

  private boolean forceSet = false;

  private String origpriceKey = PuAttrNameEnum.nastorigprice.name();

  private String origtaxpriceKey = PuAttrNameEnum.nastorigtaxprice.name();

  // private IVATValueSetProvider puorgbuysellGetter;

  private String taxrateKey = PuAttrNameEnum.ntaxrate.name();

  /**
   * @param bill 要处理的单据
   */
  public BillItemVatTaxInfoSetter(IKeyValue bill) {
    super();
    this.bill = bill;
    // this.puorgbuysellGetter = new VATValueSetProvider(bill);
  }

  // public BillItemVatTaxInfoSetter(IKeyValue bill,
  // IVATValueSetProvider puorgbuysellGetter) {
  // this.bill = bill;
  // this.puorgbuysellGetter = puorgbuysellGetter;
  // }

  /**
   * 强制设置税信息，不考虑税码是否变化
   * 
   * @param forceSet
   */
  public void setForceSet(boolean forceSet) {
    this.forceSet = forceSet;
  }

  public void setOrigpriceKey(String origpriceKey) {
    this.origpriceKey = origpriceKey;
  }

  public void setOrigtaxpriceKey(String origtaxpriceKey) {
    this.origtaxpriceKey = origtaxpriceKey;
  }

  // public void setPuorgbuysellGetter(IVATValueSetProvider puorgbuysellGetter)
  // {
  // this.puorgbuysellGetter = puorgbuysellGetter;
  // }

  public void setTaxrateKey(String taxrateKey) {
    this.taxrateKey = taxrateKey;
  }

  /**
   * 将VAT税信息设置到单据指定行上，并返回变化的字段名称<br>
   * 注意：当税码与原有税码一致时，则不会设置新税率等值
   * 
   * @param vo VAT税信息VO
   * @param row 表体行
   * @return 变化（可用于联动计算的字段），无变化时返回null
   */
  public String setVatTax(VATInfoVO vo, int row, PricePriority pricePriority) {
    return this.setVatTax(vo, row, pricePriority, new ArrayList<Integer>());
  }

  /**
   * 将VAT税信息设置到单据指定行上，并返回变化的字段名称<br>
   * 注意：当税码与原有税码一致时，则不会设置新税率等值
   * 如果购销类型改变，含税优先，调用编辑含税单价的算法；无税优先，调用编辑无税单价的算法。
   * 
   * @param vo
   * @param row
   * @param pricePriority
   * @param buysellflagChangerows 购销类型变化的行
   * @return 变化（可用于联动计算的字段），无变化时返回null
   */
  public String setVatTax(VATInfoVO vo, int row, PricePriority pricePriority,
      List<Integer> buysellflagChangerows) {
    String oldtaxcode =
        (String) this.bill.getBodyValue(row, PuAttrNameEnum.ctaxcodeid.name());

    boolean buysellchanged = this.containsRow(buysellflagChangerows, row);

    // 根据需求，税码没有变化，则不处理后续税信息
    if (!this.forceSet && !buysellchanged && !StringUtils.isBlank(oldtaxcode)
        && oldtaxcode.equals(vo.getCtaxcodeid())) {
      return null;
    }
    // 如果购销类型改变，并且税码不变，直接返回单价，不用重新设置值。
    if (!this.forceSet && buysellchanged && !StringUtils.isBlank(oldtaxcode)
        && oldtaxcode.equals(vo.getCtaxcodeid())) {
      return PricePriority.TAXPRICE_PRIOR_TO_PRICE == pricePriority ? this.origtaxpriceKey
          : this.origpriceKey;
    }
    this.bill.setBodyValue(row, PuAttrNameEnum.ctaxcodeid.name(),
        vo.getCtaxcodeid());
    String chgAttName = null;
    // 扣税类别
    Integer oldttype =
        (Integer) this.bill.getBodyValue(row,
            PuAttrNameEnum.ftaxtypeflag.name());
    if (null != vo.getFtaxtypeflag() && !vo.getFtaxtypeflag().equals(oldttype)
        || null != oldttype && !oldttype.equals(vo.getFtaxtypeflag())) {
      chgAttName = PuAttrNameEnum.ftaxtypeflag.name();
    }
    this.bill.setBodyValue(row, PuAttrNameEnum.ftaxtypeflag.name(),
        vo.getFtaxtypeflag());
    // 不可抵扣税率
    UFDouble oldnosubtaxrate =
        (UFDouble) this.bill.getBodyValue(row,
            PuAttrNameEnum.nnosubtaxrate.name());
    if (null != vo.getNnosubtaxrate()
        && !vo.getNnosubtaxrate().equals(oldnosubtaxrate)
        || null != oldnosubtaxrate
        && !oldnosubtaxrate.equals(vo.getNnosubtaxrate())) {
      chgAttName = PuAttrNameEnum.nnosubtaxrate.name();
    }
    this.bill.setBodyValue(row, PuAttrNameEnum.nnosubtaxrate.name(),
        vo.getNnosubtaxrate());
    // 税率
    UFDouble oldtaxrate =
        (UFDouble) this.bill.getBodyValue(row, this.taxrateKey);
    if (null != vo.getNtaxrate() && !vo.getNtaxrate().equals(oldtaxrate)
        || null != oldtaxrate && !oldtaxrate.equals(vo.getNtaxrate())) {
      chgAttName = this.taxrateKey;
    }
    this.bill.setBodyValue(row, this.taxrateKey, vo.getNtaxrate());
    if (buysellchanged) {
      if (PricePriority.TAXPRICE_PRIOR_TO_PRICE == pricePriority) {
        chgAttName = this.origtaxpriceKey;
      }
      else {
        chgAttName = this.origpriceKey;
      }
    }

    return chgAttName;
  }

  /**
   * 如果寻不到税码就将税码、税率等信息清空
   * <p>
   * 使用场景：
   * <ul>
   * <li>寻到税码为null
   * </ul>
   * 
   * @param row 表体行
   */
  public void setVatTaxNull(int row) {
    this.bill.setBodyValue(row, PuAttrNameEnum.ctaxcodeid.name(), null);
    this.bill.setBodyValue(row, PuAttrNameEnum.ftaxtypeflag.name(), null);
    this.bill.setBodyValue(row, PuAttrNameEnum.nnosubtaxrate.name(), null);
    this.bill.setBodyValue(row, PuAttrNameEnum.ntaxrate.name(), null);
  }

  private boolean containsRow(List<Integer> changerows, int row) {
    for (Integer changerow : changerows) {
      if (changerow.intValue() == row) {
        return true;
      }
    }
    return false;
  }

}
