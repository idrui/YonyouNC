package nc.vo.pu.m4t.rule;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2012-2-16 下午04:52:40
 * @author wuxla
 */
public class InitialEstVatValue {

  public static VATInfoQueryVO getTaxQueryVO(IKeyValue keyValue, int row) {
    String pk_material =
        (String) keyValue.getBodyValue(row, InitialEstItemVO.PK_MATERIAL);
    if (StringUtils.isEmpty(pk_material)) {
      return null;
    }
    String ctaxcountryid =
        (String) keyValue.getBodyValue(row, InitialEstItemVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      return null;
    }
    Integer fbuysellfalg =
        (Integer) keyValue.getBodyValue(row, InitialEstItemVO.FBUYSELLFLAG);
    if (null == fbuysellfalg) {
      return null;
    }
    UFBoolean btriatradeflag =
        (UFBoolean) keyValue.getBodyValue(row, InitialEstItemVO.BTRIATRADEFLAG);
    if (null == btriatradeflag) {
      return null;
    }
    String csendcountryid =
        (String) keyValue.getBodyValue(row, InitialEstItemVO.CSENDCOUNTRYID);
    if (null == csendcountryid) {
      return null;
    }

    String crececountryid =
        (String) keyValue.getBodyValue(row, InitialEstItemVO.CRECECOUNTRYID);
    if (StringUtils.isEmpty(crececountryid)) {
      return null;
    }

    String pk_supplier =
        (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
    UFDate dbilldate =
        (UFDate) keyValue.getHeadValue(InitialEstHeaderVO.DBILLDATE);
    String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    return new VATInfoQueryVO(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg), btriatradeflag, csendcountryid,
        crececountryid, pk_supplier, pk_material, dbilldate, pk_org);
  }

  // public void setVatValue(IKeyValue[] keyValues) {
  // // 设置购销类型
  // new BuysellflagSetter().setBodyBuysellFlag(keyValues);
  // // 设置三角贸易
  // new TriatradeflagSetter().setBodyTriatradeflag(keyValues);
  // // 询税和逆向征税
  // this.setTaxValue(keyValues);
  // }

  /**
   * 设置逆向征税标记
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param keyValues
   */
  public void setOppTax(IKeyValue[] keyValues) {
    if (ArrayUtils.isEmpty(keyValues)) {
      return;
    }
    List<OppTaxFlagQueryVO> list = new ArrayList<OppTaxFlagQueryVO>();
    for (IKeyValue keyValue : keyValues) {
      int count = keyValue.getItemCount();
      for (int i = 0; i < count; ++i) {
        list.add(this.getOppTaxQueryVO(keyValue, i));
      }
    }

    OppTaxFlagQueryVO[] oppqueryvos =
        list.toArray(new OppTaxFlagQueryVO[list.size()]);
    VATCodeTaxValue value = new VATCodeTaxValue(null, oppqueryvos);
    value.prepare();
    value.process();
    UFBoolean[] opptaxflag = value.getOpptaxflags();

    int index = 0;
    for (IKeyValue keyValue : keyValues) {
      int count = keyValue.getItemCount();
      for (int i = 0; i < count; ++i) {
        this.setOppTax(keyValue, i, opptaxflag[index++]);
      }
    }
  }

  public MapList<String, Integer> setVatValue(IKeyValue keyValue, int[] rows) {
    // 设置购销类型
    List<Integer> changerows =
        new BuysellflagSetter().setBodyBuysellFlag(keyValue, rows);
    // 设置三角贸易
    new TriatradeflagSetter().setBodyTriatradeflag(keyValue, rows);
    // 询税和逆向征税
    return this.setTaxValue(keyValue, rows, changerows);
  }

  private OppTaxFlagQueryVO getOppTaxQueryVO(IKeyValue keyValue, int row) {
    String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtils.isEmpty(pk_org)) {
      return null;
    }
    String ctaxcountryid =
        (String) keyValue.getBodyValue(row, InitialEstItemVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      return null;
    }
    Integer fbuysellfalg =
        (Integer) keyValue.getBodyValue(row, InitialEstItemVO.FBUYSELLFLAG);
    if (null == fbuysellfalg) {
      return null;
    }
    return new OppTaxFlagQueryVO(pk_org, ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellfalg));
  }

  private void setOppTax(IKeyValue keyValue, int row, UFBoolean opptaxflag) {
    keyValue.setBodyValue(row, InitialEstItemVO.BOPPTAXFLAG, opptaxflag);
  }

  private MapList<String, Integer> setTaxValue(IKeyValue keyValue, int[] rows,
      List<Integer> changerows) {

    List<Integer> vatrowlist = new ArrayList<Integer>();
    List<Integer> opprowlist = new ArrayList<Integer>();
    List<VATInfoQueryVO> vatlist = new ArrayList<VATInfoQueryVO>();
    List<OppTaxFlagQueryVO> oppList = new ArrayList<OppTaxFlagQueryVO>();
    for (int row : rows) {
      VATInfoQueryVO vatqueryvo =
          InitialEstVatValue.getTaxQueryVO(keyValue, row);
      if (vatqueryvo != null) {
        vatrowlist.add(Integer.valueOf(row));
        vatlist.add(vatqueryvo);
      }
      OppTaxFlagQueryVO oppqueryvo = this.getOppTaxQueryVO(keyValue, row);
      if (oppqueryvo != null) {
        opprowlist.add(Integer.valueOf(row));
        oppList.add(oppqueryvo);
      }
    }

    if (vatlist.size() == 0 && oppList.size() == 0) {
      return null;
    }

    VATInfoQueryVO[] vatqueryvos = null;
    if (vatlist.size() > 0) {
      vatqueryvos = vatlist.toArray(new VATInfoQueryVO[vatlist.size()]);
    }
    OppTaxFlagQueryVO[] oppqueryvos = null;
    if (oppList.size() > 0) {
      oppqueryvos = oppList.toArray(new OppTaxFlagQueryVO[oppList.size()]);
    }
    VATCodeTaxValue value = new VATCodeTaxValue(vatqueryvos, oppqueryvos);
    value.prepare();
    value.process();
    VATInfoVO[] vatinfovos = value.getVatinfos();
    UFBoolean[] opptaxs = value.getOpptaxflags();
    MapList<String, Integer> mapList = new MapList<String, Integer>();
    if (!ArrayUtils.isEmpty(vatinfovos)) {
      BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(keyValue);
      String pk_purchaseorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_PURCHASEORG);
      PricePriority pricePriority =
          pk_purchaseorg != null ? PUSysParamUtil.getPO28(pk_purchaseorg)
              : PricePriority.TAXPRICE_PRIOR_TO_PRICE;
      for (int i = 0; i < vatrowlist.size(); ++i) {
        Integer vatrow = vatrowlist.get(i);
        int row = vatrow.intValue();
        if (vatinfovos[i] != null) {
          String changeKey =
              setter.setVatTax(vatinfovos[i], row, pricePriority, changerows);
          if (changeKey != null) {
            mapList.put(changeKey, vatrow);
          }
        }
        else {
          setter.setVatTaxNull(row);
        }
      }
    }
    if (!ArrayUtils.isEmpty(opptaxs)) {
      for (int i = 0; i < opprowlist.size(); ++i) {
        int row = opprowlist.get(i).intValue();
        this.setOppTax(keyValue, row, opptaxs[i]);
      }
    }

    return mapList;
  }

}
