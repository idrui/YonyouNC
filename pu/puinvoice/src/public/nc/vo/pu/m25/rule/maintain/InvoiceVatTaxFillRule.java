package nc.vo.pu.m25.rule.maintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 采购发票税码、税率、扣税类型、不可抵扣税率默认值填充类，在前台支持合并远程调用<br>
 * 用于：（1）前台编辑事件 （2） 转单后处理类中设置默认值，也使用此规则<br>
 * 
 * @since 6.0
 * @version 2012-2-20 下午1:16:24
 * @author zhaoyha
 */
public class InvoiceVatTaxFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private List<Integer> buysellChangeBills = new ArrayList<Integer>();

  private MapList<String, Integer> chgNameIndexMap =
      new MapList<String, Integer>();

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills 单据VO或UI编辑数据
   */
  public InvoiceVatTaxFillRule(IKeyValue[] bills) {
    super();
    this.bills = bills;
  }

  public List<Integer> getBuysellChangeBills() {
    return this.buysellChangeBills;
  }

  /**
   * 变化字段对应的那些行序号，从第一个单据的第一行一直连续排到最后一个单据的最后一行
   * 
   * @return
   */
  public MapList<String, Integer> getChgNameIndexMap() {
    return this.chgNameIndexMap;
  }

  @Override
  public void prepare() {
    this.register_vatqueryrule();// 注册vat信息查询rule
  }

  @Override
  public void process() {
    // 先执行一下远程调用合并规则
    this.vatQueryRule.process();
    // 设置VAT相关信息默认值
    this.setVatTaxInfo();
  }

  public void setBuysellChangeBills(List<Integer> buysellChangeBills) {
    this.buysellChangeBills = buysellChangeBills;
  }

  private boolean buysellflagChange(int k) {
    for (Integer buysellChangeBill : this.buysellChangeBills) {
      if (buysellChangeBill.intValue() == k) {
        return true;
      }
    }
    return false;
  }

  private Map<String, PricePriority> getPricePriorityMap() {
    Map<String, PricePriority> map = new HashMap<String, PricePriority>();
    for (IKeyValue bill : this.bills) {
      String pk_purchaseorg =
          (String) bill.getHeadValue(InvoiceHeaderVO.PK_PURCHASEORG);
      if (pk_purchaseorg != null && !map.containsKey(pk_purchaseorg)) {
        PricePriority pricePriority = PUSysParamUtil.getPO28(pk_purchaseorg);
        if (null == pricePriority) {
          pricePriority = PricePriority.TAXPRICE_PRIOR_TO_PRICE;
        }
        map.put(pk_purchaseorg, pricePriority);
      }
    }
    return map;
  }

  private VATInfoQueryVO[] getVatInfoQueryVos() {
    VATInfoQueryVO[] vatInfoQueryVOs =
        InvoiceVOUtil.getVatInfoQueryVO(this.bills);
    VATInfoQueryVO[] validVos = new VATInfoQueryVO[vatInfoQueryVOs.length];
    for (int i = 0; i < validVos.length; i++) {
      if (null == vatInfoQueryVOs[i].getDbizdate()
          || null == vatInfoQueryVOs[i].getFbuysellfalg()
          || null == vatInfoQueryVOs[i].getPk_custsupp()
          || null == vatInfoQueryVOs[i].getCmaterialvid()) {
        continue;
      }
      validVos[i] = vatInfoQueryVOs[i];
    }
    return validVos;
  }

  private void register_vatqueryrule() {
    VATInfoQueryVO[] vatqvos = this.getVatInfoQueryVos();
    this.vatQueryRule = new VATCodeTaxValue(vatqvos, null, null, null);
    this.vatQueryRule.prepare();
  }

  private void setVatTaxInfo() {
    VATInfoVO[] vatinfos = this.vatQueryRule.getVatinfos();
    int procIndx = 0;
    Map<String, PricePriority> pricePriorityMap = this.getPricePriorityMap();
    int length = this.bills.length;
    for (int k = 0; k < length; ++k) {
      IKeyValue bill = this.bills[k];
      String pk_purchaseorg =
          (String) bill.getHeadValue(InvoiceHeaderVO.PK_PURCHASEORG);
      PricePriority pricePriority =
          pk_purchaseorg != null ? pricePriorityMap.get(pk_purchaseorg)
              : PricePriority.TAXPRICE_PRIOR_TO_PRICE;
      int rowcnt = bill.getItemCount();
      // VATValueSetProvider puorgbuysellGetter = new VATValueSetProvider(bill);
      // puorgbuysellGetter.setFbuysellflagPos(PosEnum.head);
      BillItemVatTaxInfoSetter vtsetter = new BillItemVatTaxInfoSetter(bill);
      boolean buysellchange = this.buysellflagChange(k);
      for (int i = 0; i < rowcnt; i++) {
        VATInfoVO vo = vatinfos[procIndx++];
        if (null == vo) {
          vtsetter.setVatTaxNull(i);
          continue;
        }
        // 设置单据的VAT税信息
        String chgName = vtsetter.setVatTax(vo, i, pricePriority);
        if (buysellchange) {
          if (PricePriority.TAXPRICE_PRIOR_TO_PRICE == pricePriority) {
            chgName = InvoiceItemVO.NASTORIGTAXPRICE;
            if (null == bill.getBodyValue(i, chgName)) {
              chgName = InvoiceItemVO.NORIGTAXMNY;
            }
          }
          else {
            chgName = InvoiceItemVO.NASTORIGPRICE;
            if (null == bill.getBodyValue(i, chgName)) {
              chgName = InvoiceItemVO.NORIGMNY;
            }
          }
        }
        if (null == chgName) {
          continue;
        }
        // 并将变化字段保存下来以便进行联动计算
        this.chgNameIndexMap.put(chgName, Integer.valueOf(procIndx - 1));
      }
    }
  }

}
