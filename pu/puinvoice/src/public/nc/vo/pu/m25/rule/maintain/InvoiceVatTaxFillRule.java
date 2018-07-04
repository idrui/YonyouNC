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
 * �ɹ���Ʊ˰�롢˰�ʡ���˰���͡����ɵֿ�˰��Ĭ��ֵ����࣬��ǰ̨֧�ֺϲ�Զ�̵���<br>
 * ���ڣ���1��ǰ̨�༭�¼� ��2�� ת��������������Ĭ��ֵ��Ҳʹ�ô˹���<br>
 * 
 * @since 6.0
 * @version 2012-2-20 ����1:16:24
 * @author zhaoyha
 */
public class InvoiceVatTaxFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private List<Integer> buysellChangeBills = new ArrayList<Integer>();

  private MapList<String, Integer> chgNameIndexMap =
      new MapList<String, Integer>();

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills ����VO��UI�༭����
   */
  public InvoiceVatTaxFillRule(IKeyValue[] bills) {
    super();
    this.bills = bills;
  }

  public List<Integer> getBuysellChangeBills() {
    return this.buysellChangeBills;
  }

  /**
   * �仯�ֶζ�Ӧ����Щ����ţ��ӵ�һ�����ݵĵ�һ��һֱ�����ŵ����һ�����ݵ����һ��
   * 
   * @return
   */
  public MapList<String, Integer> getChgNameIndexMap() {
    return this.chgNameIndexMap;
  }

  @Override
  public void prepare() {
    this.register_vatqueryrule();// ע��vat��Ϣ��ѯrule
  }

  @Override
  public void process() {
    // ��ִ��һ��Զ�̵��úϲ�����
    this.vatQueryRule.process();
    // ����VAT�����ϢĬ��ֵ
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
        // ���õ��ݵ�VAT˰��Ϣ
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
        // �����仯�ֶα��������Ա������������
        this.chgNameIndexMap.put(chgName, Integer.valueOf(procIndx - 1));
      }
    }
  }

}
