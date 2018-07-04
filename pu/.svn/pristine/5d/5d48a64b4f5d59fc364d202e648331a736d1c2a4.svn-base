package nc.vo.pu.est.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 费用暂估设置VAT
 * 注意IKeyValue如果是VO级的，一定是nc.vo.pu.pub.util.AggVOHelper<E>
 * 不能是nc.vo.pu.pub.util. BillHelper<E>
 * 
 * @since 6.0
 * @version 2012-3-8 下午05:04:08
 * @author wuxla
 */
public class FeeEstVatValue {

  /**
   * 设置VAT值，包括国家
   * <p>
   * 使用场景：
   * <ul>
   * <li>费用物料编辑后
   * </ul>
   * 
   * @param keyValue
   * @param pk_fiorg 财务组织
   * @param rows
   */
  public Map<Integer, String> setCountryAndVatValue(IKeyValue keyValue,
      String pk_fiorg, int[] rows) {
    String pk_country = this.getPk_country(pk_fiorg);
    for (int row : rows) {
      this.setCountry(keyValue, pk_country, row);
    }
    return this.setVatValue(keyValue, pk_fiorg, pk_country, rows);
  }

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>供应商编辑后事件
   * </ul>
   * 
   * @param keyValue
   * @param pk_fiorg
   * @param rows
   * @return
   */
  public Map<Integer, String> setSendCountryAndVatValue(IKeyValue keyValue,
      String pk_fiorg, int[] rows) {
    Map<String, String> supCountryMap = this.getSupCountryMap(keyValue, rows);
    if (null == supCountryMap) {
      return null;
    }
    this.setSendCountry(keyValue, rows, supCountryMap);
    String pk_country = this.getPk_country(pk_fiorg);
    List<Integer> changeRows =
        new FeeBuysellflagSetter().setBodyBuysellFlag(keyValue, pk_country,
            rows);
    new FeeTriatradeflagSetter().setBodyTriatradeflag(keyValue, pk_country,
        rows);
    return this.setTaxValue(keyValue, pk_fiorg, pk_country, rows, changeRows);
  }

  /**
   * 设置VAT值，不包括国家
   * <p>
   * 使用场景：
   * <ul>
   * <li>编辑事件，主要是编辑国家后
   * </ul>
   * 
   * @param keyValue
   * @param pk_fiorg
   * @param rows
   */
  public Map<Integer, String> setVatValue(IKeyValue keyValue, String pk_fiorg,
      int[] rows) {
    String pk_country = this.getPk_country(pk_fiorg);
    return this.setVatValue(keyValue, pk_fiorg, pk_country, rows);
  }

  /**
   * 设置费用VAT信息，包括设置国家
   * <p>
   * 使用场景：
   * <ul>
   * <li>查询以及升级，调用方法后没有联动计算
   * </ul>
   * 
   * @param keyValues
   * @param pk_fiorg
   */
  public void setVatValue(IKeyValue[] keyValues, String pk_fiorg) {
    String pk_country = this.getPk_country(pk_fiorg);
    for (IKeyValue keyValue : keyValues) {
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        this.setCountry(keyValue, pk_country, i);
      }
    }

    // 因为字段一致，可以用公共类
    new FeeTriatradeflagSetter().setBodyTriatradeflag(keyValues, pk_country);
    new FeeBuysellflagSetter().setBodyBuysellFlag(keyValues, pk_country);
    this.setTaxValue(keyValues, pk_fiorg, pk_country);
  }

  private OppTaxFlagQueryVO getOppTaxQueryVO(IKeyValue keyValue,
      String pk_fiorg, String pk_country, int row) {
    Integer fbuysellfalg =
        (Integer) keyValue.getBodyValue(row, FeeEstVO.FBUYSELLFLAG);
    if (null == fbuysellfalg) {
      return null;
    }
    String ctaxcountryid =
        (String) keyValue.getBodyValue(row, FeeEstVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      ctaxcountryid = pk_country;
    }
    return new OppTaxFlagQueryVO(pk_fiorg, ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg));
  }

  private String getPk_country(String pk_fiorg) {
    Map<String, String> map =
        FinanceOrgPubService.queryCountryByFinanceOrg(new String[] {
          pk_fiorg
        });
    if (null == map) {
      return null;
    }
    return map.get(pk_fiorg);
  }

  /**
   * 供应商国家
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  private Map<String, String> getSupCountryMap(IKeyValue keyValue, int[] rows) {
    Set<String> supSet = new HashSet<String>();
    for (int row : rows) {
      String pk_supplier =
          (String) keyValue.getBodyValue(row, FeeEstVO.PK_SUPPLIER);
      if (pk_supplier != null) {
        supSet.add(pk_supplier);
      }
    }
    String[] sups = supSet.toArray(new String[supSet.size()]);
    return SupplierPubService.queryCountryBySupplier(sups);
  }

  private VATInfoQueryVO getTaxQueryVO(IKeyValue keyValue, String pk_country,
      int row) {
    String pk_material =
        (String) keyValue.getBodyValue(row, FeeEstVO.PK_SRCFEEMATERIAL);
    if (StringUtils.isEmpty(pk_material)) {
      return null;
    }

    Integer fbuysellfalg =
        (Integer) keyValue.getBodyValue(row, FeeEstVO.FBUYSELLFLAG);
    if (null == fbuysellfalg) {
      return null;
    }
    UFBoolean btriatradeflag =
        (UFBoolean) keyValue.getBodyValue(row, FeeEstVO.BTRIATRADEFLAG);
    if (null == btriatradeflag) {
      return null;
    }
    String ctaxcountryid =
        (String) keyValue.getBodyValue(row, FeeEstVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      ctaxcountryid = pk_country;
    }
    String csendcountryid =
        (String) keyValue.getBodyValue(row, FeeEstVO.CSENDCOUNTRYID);
    if (null == csendcountryid) {
      csendcountryid = pk_country;
    }

    String crececountryid =
        (String) keyValue.getBodyValue(row, FeeEstVO.CRECECOUNTRYID);
    if (StringUtils.isEmpty(crececountryid)) {
      crececountryid = pk_country;
    }

    String pk_supplier =
        (String) keyValue.getBodyValue(row, FeeEstVO.PK_SUPPLIER);
    UFDate destdate = (UFDate) keyValue.getBodyValue(row, FeeEstVO.DESTDATE);
    String pk_fiorg =
        (String) keyValue.getBodyValue(row, FeeEstVO.PK_FINANCEORG);

    return new VATInfoQueryVO(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg), btriatradeflag, csendcountryid,
        crececountryid, pk_supplier, pk_material, destdate, pk_fiorg);
  }

  private void setCountry(IKeyValue keyValue, String pk_country, int row) {
    keyValue.setBodyValue(row, FeeEstVO.CSENDCOUNTRYID, pk_country);
    keyValue.setBodyValue(row, FeeEstVO.CRECECOUNTRYID, pk_country);
    keyValue.setBodyValue(row, FeeEstVO.CTAXCOUNTRYID, pk_country);
  }

  private void setOppTax(IKeyValue keyValue, int row, UFBoolean opptaxflag) {
    keyValue.setBodyValue(row, FeeEstVO.BOPPTAXFLAG, opptaxflag);
  }

  private void setSendCountry(IKeyValue keyValue, int[] rows,
      Map<String, String> supCountryMap) {
    for (int row : rows) {
      String pk_supplier =
          (String) keyValue.getBodyValue(row, FeeEstVO.PK_SUPPLIER);
      if (pk_supplier != null) {
        String csendcountryid = supCountryMap.get(pk_supplier);
        String oldValue =
            (String) keyValue.getBodyValue(row, FeeEstVO.CSENDCOUNTRYID);
        if (!StringUtils.equals(csendcountryid, oldValue)) {
          keyValue.setBodyValue(row, FeeEstVO.CSENDCOUNTRYID, csendcountryid);
        }
      }
    }
  }

  private Map<Integer, String> setTaxValue(IKeyValue keyValue, String pk_fiorg,
      String pk_country, int[] rows, List<Integer> changeRows) {
    List<Integer> vatrowlist = new ArrayList<Integer>();
    List<Integer> opprowlist = new ArrayList<Integer>();
    List<VATInfoQueryVO> vatlist = new ArrayList<VATInfoQueryVO>();
    List<OppTaxFlagQueryVO> oppList = new ArrayList<OppTaxFlagQueryVO>();
    for (int row : rows) {
      VATInfoQueryVO vatqueryvo = this.getTaxQueryVO(keyValue, pk_country, row);
      if (vatqueryvo != null) {
        vatrowlist.add(Integer.valueOf(row));
        vatlist.add(vatqueryvo);
      }
      OppTaxFlagQueryVO oppqueryvo =
          this.getOppTaxQueryVO(keyValue, pk_fiorg, pk_country, row);
      if (oppqueryvo != null) {
        opprowlist.add(Integer.valueOf(row));
        oppList.add(oppqueryvo);
      }
    }
    if (vatlist.size() == 0 && oppList.size() == 0) {
      return null;
    }

    VATInfoQueryVO[] vatqueryvos =
        vatlist.toArray(new VATInfoQueryVO[vatlist.size()]);
    OppTaxFlagQueryVO[] oppqueryvos =
        oppList.toArray(new OppTaxFlagQueryVO[oppList.size()]);
    VATCodeTaxValue value = new VATCodeTaxValue(vatqueryvos, oppqueryvos);
    value.prepare();
    value.process();
    VATInfoVO[] vatinfovos = value.getVatinfos();
    UFBoolean[] opptaxs = value.getOpptaxflags();
    Map<Integer, String> map = new HashMap<Integer, String>();
    if (!ArrayUtils.isEmpty(vatinfovos)) {
      for (int i = 0; i < vatrowlist.size(); ++i) {
        int row = vatrowlist.get(i).intValue();
        String changeAttr =
            this.setVatInfo(keyValue, row, vatinfovos[i], changeRows);
        if (null != changeAttr) {
          map.put(vatrowlist.get(i), changeAttr);
        }
      }
    }
    if (!ArrayUtils.isEmpty(opptaxs)) {
      for (int i = 0; i < opprowlist.size(); ++i) {
        int row = opprowlist.get(i).intValue();
        this.setOppTax(keyValue, row, opptaxs[i]);
      }
    }
    return map;
  }

  private void setTaxValue(IKeyValue[] keyValues, String pk_fiorg,
      String pk_country) {
    List<VATInfoQueryVO> vatlist = new ArrayList<VATInfoQueryVO>();
    List<OppTaxFlagQueryVO> oppList = new ArrayList<OppTaxFlagQueryVO>();
    for (IKeyValue keyValue : keyValues) {
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        VATInfoQueryVO vatqueryvo = this.getTaxQueryVO(keyValue, pk_country, i);
        vatlist.add(vatqueryvo);
        OppTaxFlagQueryVO oppqueryvo =
            this.getOppTaxQueryVO(keyValue, pk_fiorg, pk_country, i);
        oppList.add(oppqueryvo);
      }
    }
    VATInfoQueryVO[] vatqueryvos =
        vatlist.toArray(new VATInfoQueryVO[vatlist.size()]);
    OppTaxFlagQueryVO[] oppqueryvos =
        oppList.toArray(new OppTaxFlagQueryVO[oppList.size()]);
    VATCodeTaxValue value = new VATCodeTaxValue(vatqueryvos, oppqueryvos);
    value.prepare();
    value.process();
    VATInfoVO[] vatinfovos = value.getVatinfos();
    UFBoolean[] opptaxs = value.getOpptaxflags();
    int vatindex = 0;
    for (IKeyValue keyValue : keyValues) {
      int count = keyValue.getItemCount();
      for (int i = 0; i < count; ++i) {
        this.setVatInfo(keyValue, i, vatinfovos[vatindex]);
        this.setOppTax(keyValue, i, opptaxs[vatindex]);
        ++vatindex;
      }
    }
  }

  /**
   * 设置税码信息，不涉及购销类型改变
   * 
   * @param keyValue
   * @param row
   * @param vatinfovo
   * @return
   */
  private String setVatInfo(IKeyValue keyValue, int row, VATInfoVO vatinfovo) {
    if (null == vatinfovo) {
      keyValue.setBodyValue(row, FeeEstVO.CTAXCODEID, null);
      keyValue.setBodyValue(row, FeeEstVO.FTAXTYPEFLAG, null);
      keyValue.setBodyValue(row, FeeEstVO.NNOSUBTAXRATE, null);
      keyValue.setBodyValue(row, FeeEstVO.NESTTAXRATE, null);
      return null;
    }
    BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(keyValue);
    setter.setTaxrateKey(FeeEstVO.NESTTAXRATE);
    setter.setOrigtaxpriceKey(FeeEstVO.NESTOTOTALMNY);
    setter.setOrigpriceKey(FeeEstVO.NESTOMNY);
    return setter.setVatTax(vatinfovo, row,
        PricePriority.TAXPRICE_PRIOR_TO_PRICE);
  }

  /**
   * 设置税码信息，涉及购销类型改变，如果购销类型改变，使用单价联动计算
   * 
   * @param keyValue
   * @param row
   * @param vatinfovo
   * @param changeRows
   * @return
   */
  private String setVatInfo(IKeyValue keyValue, int row, VATInfoVO vatinfovo,
      List<Integer> changeRows) {
    if (null == vatinfovo) {
      keyValue.setBodyValue(row, FeeEstVO.CTAXCODEID, null);
      keyValue.setBodyValue(row, FeeEstVO.FTAXTYPEFLAG, null);
      keyValue.setBodyValue(row, FeeEstVO.NNOSUBTAXRATE, null);
      keyValue.setBodyValue(row, FeeEstVO.NESTTAXRATE, null);
      return null;
    }
    BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(keyValue);
    setter.setTaxrateKey(FeeEstVO.NESTTAXRATE);
    setter.setOrigtaxpriceKey(FeeEstVO.NESTOTOTALMNY);
    setter.setOrigpriceKey(FeeEstVO.NESTOMNY);
    return setter.setVatTax(vatinfovo, row,
        PricePriority.TAXPRICE_PRIOR_TO_PRICE, changeRows);
  }

  private Map<Integer, String> setVatValue(IKeyValue keyValue, String pk_fiorg,
      String pk_country, int[] rows) {
    List<Integer> changeRows =
        new FeeBuysellflagSetter().setBodyBuysellFlag(keyValue, pk_country,
            rows);
    new FeeTriatradeflagSetter().setBodyTriatradeflag(keyValue, pk_country,
        rows);
    return this.setTaxValue(keyValue, pk_fiorg, pk_country, rows, changeRows);
  }
}
