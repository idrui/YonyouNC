package nc.vo.pu.m21.rule.vat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.itf.scmpub.reference.uap.bd.vat.ZeroTaxCodeEnum;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TaxValue;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.enumeration.EnumDiscounttaxtype;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * �ɹ�����VAT�����ϢĬ��ֵ����࣬��ǰ̨֧�ֺϲ�Զ�̵���<br>
 * �˹��������ҵ����á�˰��˰�ʵ�VAT˰������ <br>
 * ����:<br>
 * <ul>
 * <li>���Ƶ���;
 * <li>ת��������������Ĭ��ֵ.
 * </ul>
 * 
 * @since 6.0
 * @version 2012-2-21 ����01:26:22
 * @author tianft
 */
public class OrderVatValueFillRule implements IPURemoteCallCombinator {

  /**
   * ��������
   */
  public static interface IAreaSetter {
    void setArea();
  }

  /**
   * ��������
   */
  public static interface ICountrySetter {
    void setCountry();
  }

  /**
   * vat�仯�壬�洢�ֶα仯��Ϣ
   * 
   * @since 6.0
   * @version 2012-2-27 ����05:17:46
   * @author tianft
   */
  public static class OrderValueChangeObject {
    int billIndex;

    String changeKey;

    int row;

    public OrderValueChangeObject(int billIndex, int row, String changeKey) {
      this.billIndex = billIndex;
      this.row = row;
      this.changeKey = changeKey;
    }

    public int getBillIndex() {
      return this.billIndex;
    }

    public String getChangeKey() {
      return this.changeKey;
    }

    public int getRow() {
      return this.row;
    }

  }

  /** ���������� */
  private List<IAreaSetter> areaSetterList;

  /** ���ݻ��߿�Ƭ */
  private IKeyValue[] bills;

  private MapList<Integer, Integer> buysellFlagChange =
      new MapList<Integer, Integer>();

  /** ���������� */
  private List<ICountrySetter> countrysetterList;

  /**
   * �Ƿ���������
   */
  private boolean fromSrcBill = false;

  private int[] rows;

  private OrderValueChangeObject[] valueChangeObject;

  private TaxValue vatQueryRule;

  public OrderVatValueFillRule(IKeyValue bill, int[] rows) {
    this(new IKeyValue[] {
      bill
    });
    this.rows = rows;
  }

  public OrderVatValueFillRule(IKeyValue bill, int[] rows,
      List<ICountrySetter> countrysetterList) {
    this(bill, rows);
    this.countrysetterList = countrysetterList;
  }

  public OrderVatValueFillRule(IKeyValue[] bills) {
    super();
    this.bills = bills;
  }

  public OrderVatValueFillRule(IKeyValue[] bills,
      List<ICountrySetter> countrysetterList) {
    this(bills);
    this.countrysetterList = countrysetterList;
  }

  public List<IAreaSetter> getAreaSetterList() {
    return this.areaSetterList;
  }

  public IKeyValue[] getBills() {
    return this.bills;
  }

  public List<ICountrySetter> getCountrysetterList() {
    return this.countrysetterList;
  }

  public int[] getRows() {
    return this.rows;
  }

  public OrderValueChangeObject[] getValueChangeObject() {
    return this.valueChangeObject;
  }

  public TaxValue getVatQueryRule() {
    return this.vatQueryRule;
  }

  public boolean isFromSrcBill() {
    return this.fromSrcBill;
  }

  @Override
  public void prepare() {
    // ���ù���
    this.setCountries();
    // ���õ���
    this.setAreas();
    // ��������(��������ó��֮ǰ����)
    this.buysellFlagChange =
        new BuysellflagSetter().setBodyBuysellFlag(this.bills);
    // ����ó��
    new TriatradeflagSetter().setBodyTriatradeflag(this.bills);
    // ע��Զ�̵��õķ���
    this.registerVatQueryRule();
  }

  @Override
  public void process() {
    this.vatQueryRule.process();
    // ����˰�����
    this.setVatTaxInfo();
  }

  public void setAreaSetterList(List<IAreaSetter> areaSetterList) {
    this.areaSetterList = areaSetterList;
  }

  public void setCountrysetterList(List<ICountrySetter> countrysetterList) {
    this.countrysetterList = countrysetterList;
  }

  public void setFromSrcBill(boolean fromSrcBill) {
    this.fromSrcBill = fromSrcBill;
  }

  private void clearDataWhenNoTaxcode(IKeyValue bill, int row) {
    // ���δƥ�䵽��˰�룬ԭ�е�˰�롢��˰���˰�ʡ����ɵֿ�˰�����
    if (!this.fromSrcBill) {
      bill.setBodyValue(row, OrderItemVO.CTAXCODEID, null);
      bill.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG, null);
      bill.setBodyValue(row, OrderItemVO.NTAXRATE, null);
      bill.setBodyValue(row, OrderItemVO.NNOSUBTAXRATE, null);
    }
  }

  /**
   * ������˰���Ⱥ�˰���ȵõ��仯�����ֶ�
   * 
   * @param bill
   * @return
   */
  private String getPriceKeyByPara(IKeyValue bill) {
    String changeKey = OrderItemVO.NQTORIGTAXPRICE;// ��˰����
    String pk_org = (String) bill.getHeadValue(OrderHeaderVO.PK_ORG);
    if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(PUSysParamUtil
        .getPO28(pk_org))) {
      changeKey = OrderItemVO.NQTORIGPRICE;// ��˰����
    }
    return changeKey;
  }

  /**
   * ����˰��˰�ʲ�ѯ����
   * 
   * @return
   */
  private VATInfoQueryVO[] getVatInfoQueryVos() {
    VATInfoQueryVO[] vatInfoQueryVOs;
    if (ArrayUtils.isEmpty(this.rows)) {
      vatInfoQueryVOs = OrderVOUtil.getVatInfoQueryVO(this.bills);
    }
    else {
      vatInfoQueryVOs = OrderVOUtil.getVatInfoQueryVO(this.bills[0], this.rows);
    }

    VATInfoQueryVO[] validVos = new VATInfoQueryVO[vatInfoQueryVOs.length];
    for (int i = 0; i < validVos.length; i++) {
      if (null == vatInfoQueryVOs[i].getDbizdate()
          || null == vatInfoQueryVOs[i].getFbuysellfalg()
          || null == vatInfoQueryVOs[i].getCtaxcountryid()
          || null == vatInfoQueryVOs[i].getCmaterialvid()) {
        continue;
      }
      validVos[i] = vatInfoQueryVOs[i];
    }
    return validVos;
  }

  private Object getVATInfoVOValue(VATInfoVO vo, String key) {
    if (vo == null) {
      return null;
    }
    if (OrderItemVO.FTAXTYPEFLAG.equals(key)) {
      return vo.getFtaxtypeflag();
    }
    else if (OrderItemVO.CTAXCODEID.equals(key)) {
      return vo.getCtaxcodeid();
    }
    else if (OrderItemVO.NNOSUBTAXRATE.equals(key)) {
      return vo.getNnosubtaxrate();
    }
    else if (OrderItemVO.NTAXRATE.equals(key)) {
      return vo.getNtaxrate();
    }

    return null;
  }

  private boolean processZeroTaxRow(IKeyValue bill, int row,
      Map<String, SupplierVO> suppliermap,Map<String,String> orgcorpmap) {
    // ˰��Ϊ����Ϊû�����δ���
    if (!this.fromSrcBill
        || bill.getBodyValue(row, OrderItemVO.CTAXCODEID) == null) {
      // ����������=���ڲɹ����ҹ�Ӧ��Ϊ�ڲ���Ӧ�̡���Ӧ�̶�Ӧ�Ĳ�����֯������˾=������������֯������˾ʱ��ֱ��ȡ��ƽ̨���õ�0˰��˰��
      Integer buyflag =
          (Integer) bill.getBodyValue(row, OrderItemVO.FBUYSELLFLAG);
      // �ǹ��ڲɹ�ʱ������
      if (!BuySellFlagEnum.NATIONAL_BUY.value().equals(buyflag)) {
        return false;
      }
      String pk_supplier =
          (String) bill.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
      String pk_settleorg =
          (String) bill.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);
      if (pk_supplier == null || pk_settleorg == null) {
        return false;
      }
      // by 20141105 mengjian ������ѯ��Ӧ��
      SupplierVO supplierVO = suppliermap.get(pk_supplier);

      // �ڲ���Ӧ��ʱ����
      if (Integer.valueOf(1).equals(supplierVO.getSupprop())) {
        String supplierFinanceOrg = supplierVO.getPk_financeorg();
        // ������֯һ��
        if (pk_settleorg.equals(supplierFinanceOrg)) {
          this.setBodyZeroTaxtRow(bill, row);
          return true;
        }
        Map<String, String> orgCorp  = new HashMap<String,String>();
        /*
         * change by wandl
         * ������0˰������Ч������
         */
        if(!orgcorpmap.containsKey(supplierFinanceOrg) && !orgcorpmap.containsKey(pk_settleorg)){
        	orgCorp =
              FinanceOrgPubService.queryPKCorp(new String[] {
                supplierFinanceOrg, pk_settleorg
              });
        	orgcorpmap.putAll(orgCorp);
        }
        // ������֯������˾һ��
        if (orgcorpmap.get(supplierFinanceOrg) != null
            && orgcorpmap.get(supplierFinanceOrg)
                .equals(orgcorpmap.get(pk_settleorg))) {       	
          this.setBodyZeroTaxtRow(bill, row);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * by 20141105 mengjian ������ѯ��Ӧ��
   * 
   * @return
   */
  private Map<String, SupplierVO> querySupplierVOs() {
    Map<String, SupplierVO> map = new HashMap<String, SupplierVO>();
    List<String> pk_suppliers = new ArrayList<String>();
    for (int billPos = 0; billPos < this.bills.length; billPos++) {
      IKeyValue bill = this.bills[billPos];
      String pk_supplier =
          (String) bill.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
      if (pk_supplier != null) {
        pk_suppliers.add(pk_supplier);
      }
    }
    if (pk_suppliers.size() == 0) {
      return map;
    }
    SupplierVO[] supplierVOs =
        SupplierPubService.getSupplierVO(pk_suppliers.toArray(new String[0]),
            new String[] {
              SupplierVO.SUPPROP, SupplierVO.PK_FINANCEORG,
              SupplierVO.PK_SUPPLIER
            });
    for (SupplierVO supplierVO : supplierVOs) {
      String pk_supplier = supplierVO.getPk_supplier();
      map.put(pk_supplier, supplierVO);
    }
    return map;
  }

  private void registerVatQueryRule() {
    VATInfoQueryVO[] vatqueryvos = this.getVatInfoQueryVos();
    this.vatQueryRule = new TaxValue(vatqueryvos);
    this.vatQueryRule.prepare();
  }

  private void setAreas() {
    if (this.areaSetterList == null) {
      return;
    }
    for (IAreaSetter setter : this.areaSetterList) {
      setter.setArea();
    }
  }

  private String setBodyValue(IKeyValue bill, VATInfoVO vo, int pos) {
    // ��һ������ʱ�� ����˰���Ƿ�������һ�£�ֻҪ����˰�ʡ���˰���������һ�£����м�˰�ϼơ����Ҽ�˰�ϼơ�˰�������˰����˰����ȫ��Я����
    // ����ʱ���Ѿ�������ֵ����������
    this.setVatValueByKey(bill, vo, pos, OrderItemVO.CTAXCODEID);// ˰��
    // �����������ݵ�key��������˳��
    String changeKey =
        this.setVatValueByKey(bill, vo, pos, OrderItemVO.FTAXTYPEFLAG);
    changeKey = this.setVatValueByKey(bill, vo, pos, OrderItemVO.NNOSUBTAXRATE);
    changeKey = this.setVatValueByKey(bill, vo, pos, OrderItemVO.NTAXRATE);

    if (!this.fromSrcBill && !StringUtils.isEmpty(changeKey)) {
      // ����ҵ�񣬿��ʱ,��������ݺ�˰���ۻ���˰��������
      Object buySellFlag = bill.getBodyValue(pos, OrderItemVO.FBUYSELLFLAG);
      if (BuySellFlagEnum.IMPORT.value().toString()
          .equals(buySellFlag.toString())) {
        changeKey = this.getPriceKeyByPara(bill);
      }
    }
    return changeKey;
  }

  private void setBodyZeroTaxtRow(IKeyValue bill, int pos) {
    // ֱ��ȡ��ƽ̨���õ�0˰��˰�룻
    bill.setBodyValue(pos, OrderItemVO.NTAXRATE, Integer.valueOf(0));
    bill.setBodyValue(pos, OrderItemVO.NNOSUBTAXRATE, Integer.valueOf(0));
    bill.setBodyValue(pos, OrderItemVO.CTAXCODEID,
        ZeroTaxCodeEnum.ZEROTAXCODE.getCode());// ��˰��
    bill.setBodyValue(pos, OrderItemVO.FTAXTYPEFLAG,
        EnumDiscounttaxtype.TAXOUT.value());// Ĭ��Ӧ˰���

  }

  /**
   * ���ù�����أ�����������˰�����ջ����ȡ�
   */
  private void setCountries() {
    if (this.countrysetterList == null) {
      return;
    }
    for (ICountrySetter setter : this.countrysetterList) {
      setter.setCountry();
    }
  }

  private String setVatValueByKey(IKeyValue bill, VATInfoVO vo, int pos,
      String itemKey) {
    Object vatValue = this.getVATInfoVOValue(vo, itemKey);
    String changeKey = null;
    if (vatValue != null) {
      if (vatValue.equals(bill.getBodyValue(pos, itemKey))) {
        return itemKey;
      }
      if (!this.fromSrcBill || bill.getBodyValue(pos, itemKey) == null) {
        changeKey = itemKey;
        bill.setBodyValue(pos, itemKey, vatValue);
      }
    }
    return changeKey;
  }

  /**
   * ����˰�����
   */
  protected void setVatTaxInfo() {
    // ����������=���ڲɹ����ҹ�Ӧ��Ϊ�ڲ���Ӧ�̡���Ӧ�̶�Ӧ�Ĳ�����֯������˾=������������֯������˾ʱ��ֱ��ȡ��ƽ̨���õ�0˰��˰�룻
    VATInfoVO[] vatinfos = this.vatQueryRule.getVatinfos();
    if (ArrayUtils.isEmpty(vatinfos)) {
      return;
    }
    // by 20141105 mengjian ������ѯ��Ӧ��
    Map<String, SupplierVO> suppliermap = this.querySupplierVOs();  
    int procIndx = 0;
    int billIndex = 0;
    List<OrderValueChangeObject> valueChangeList =
        new ArrayList<OrderValueChangeObject>();
    Map<String,String> orgCorpmap = new HashMap<String,String>();
    for (int billPos = 0; billPos < this.bills.length; billPos++) {
      IKeyValue bill = this.bills[billPos];
      int rowcnt =
          ArrayUtils.isEmpty(this.rows) ? bill.getItemCount()
              : this.rows.length; 
      for (int i = 0; i < rowcnt; i++) {
        int pos = ArrayUtils.isEmpty(this.rows) ? i : this.rows[i];
        OrderValueChangeObject ovcb = null;
        if (this.buysellFlagChange.get(Integer.valueOf(billPos)) != null
            && this.buysellFlagChange.get(Integer.valueOf(billPos)).contains(
                Integer.valueOf(pos))) {
          ovcb =
              new OrderValueChangeObject(billIndex, pos,
                  this.getPriceKeyByPara(bill));
        }
        // ����0˰��˰��
        if (this.processZeroTaxRow(bill, pos, suppliermap,orgCorpmap)) {
          if (ovcb == null) {
            ovcb =
                new OrderValueChangeObject(billIndex, pos, OrderItemVO.NTAXRATE);
          }
          valueChangeList.add(ovcb);
          continue;
        }
        VATInfoVO vo = vatinfos[procIndx++];
        if (null == vo) {
          // 2012-03-16������wangyf�������δƥ�䵽��˰�룬ԭ�е�˰�롢��˰���˰�ʡ����ɵֿ�˰�����
          this.clearDataWhenNoTaxcode(bill, pos);
          continue;
        }
        String taxcode =
            (String) bill.getBodyValue(pos, OrderItemVO.CTAXCODEID);
        // ���Ǵ����ι���ʱ��ֻҪ˰��һ�£��Ͳ����к����Ĵ���
        if (!this.fromSrcBill
            && StringUtils.equals(taxcode, vo.getCtaxcodeid())) {
          if (ovcb != null) {// ���ܵ�����ǣ��������͸ı�
            valueChangeList.add(ovcb);
          }
          continue;
        }
        String changeKey = this.setBodyValue(bill, vo, pos);
        if (ovcb != null) {// �������͸ı�
          valueChangeList.add(ovcb);
        }
        else if (!StringUtils.isEmpty(changeKey)) {
          ovcb = new OrderValueChangeObject(billIndex, pos, changeKey);
          valueChangeList.add(ovcb);
        }
      }
      billIndex++;
    }
    if (valueChangeList.size() > 0) {
      this.valueChangeObject =
          valueChangeList.toArray(new OrderValueChangeObject[valueChangeList
              .size()]);
    }
  }
}
