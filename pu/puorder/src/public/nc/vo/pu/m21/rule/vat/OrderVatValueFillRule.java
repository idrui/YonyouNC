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
 * 采购订单VAT相关信息默认值填充类，在前台支持合并远程调用<br>
 * 此规则处理：国家的设置、税码税率等VAT税的设置 <br>
 * 用于:<br>
 * <ul>
 * <li>自制单据;
 * <li>转单后处理类中设置默认值.
 * </ul>
 * 
 * @since 6.0
 * @version 2012-2-21 下午01:26:22
 * @author tianft
 */
public class OrderVatValueFillRule implements IPURemoteCallCombinator {

  /**
   * 地区设置
   */
  public static interface IAreaSetter {
    void setArea();
  }

  /**
   * 国家设置
   */
  public static interface ICountrySetter {
    void setCountry();
  }

  /**
   * vat变化体，存储字段变化信息
   * 
   * @since 6.0
   * @version 2012-2-27 下午05:17:46
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

  /** 地区设置器 */
  private List<IAreaSetter> areaSetterList;

  /** 单据或者卡片 */
  private IKeyValue[] bills;

  private MapList<Integer, Integer> buysellFlagChange =
      new MapList<Integer, Integer>();

  /** 国家设置器 */
  private List<ICountrySetter> countrysetterList;

  /**
   * 是否拉单处理
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
    // 设置国家
    this.setCountries();
    // 设置地区
    this.setAreas();
    // 购销类型(设置三角贸易之前调用)
    this.buysellFlagChange =
        new BuysellflagSetter().setBodyBuysellFlag(this.bills);
    // 三角贸易
    new TriatradeflagSetter().setBodyTriatradeflag(this.bills);
    // 注册远程调用的服务
    this.registerVatQueryRule();
  }

  @Override
  public void process() {
    this.vatQueryRule.process();
    // 设置税率相关
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
    // 如果未匹配到新税码，原有的税码、扣税类别、税率、不可抵扣税率清空
    if (!this.fromSrcBill) {
      bill.setBodyValue(row, OrderItemVO.CTAXCODEID, null);
      bill.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG, null);
      bill.setBodyValue(row, OrderItemVO.NTAXRATE, null);
      bill.setBodyValue(row, OrderItemVO.NNOSUBTAXRATE, null);
    }
  }

  /**
   * 根据无税优先含税优先得到变化单价字段
   * 
   * @param bill
   * @return
   */
  private String getPriceKeyByPara(IKeyValue bill) {
    String changeKey = OrderItemVO.NQTORIGTAXPRICE;// 含税单价
    String pk_org = (String) bill.getHeadValue(OrderHeaderVO.PK_ORG);
    if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(PUSysParamUtil
        .getPO28(pk_org))) {
      changeKey = OrderItemVO.NQTORIGPRICE;// 无税单价
    }
    return changeKey;
  }

  /**
   * 构造税码税率查询参数
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
    // 税码为空认为没从上游带入
    if (!this.fromSrcBill
        || bill.getBodyValue(row, OrderItemVO.CTAXCODEID) == null) {
      // 当购销类型=国内采购、且供应商为内部供应商、供应商对应的财务组织所属公司=表体结算财务组织所属公司时，直接取得平台内置的0税率税码
      Integer buyflag =
          (Integer) bill.getBodyValue(row, OrderItemVO.FBUYSELLFLAG);
      // 非国内采购时不处理
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
      // by 20141105 mengjian 批量查询供应商
      SupplierVO supplierVO = suppliermap.get(pk_supplier);

      // 内部供应商时处理
      if (Integer.valueOf(1).equals(supplierVO.getSupprop())) {
        String supplierFinanceOrg = supplierVO.getPk_financeorg();
        // 财务组织一样
        if (pk_settleorg.equals(supplierFinanceOrg)) {
          this.setBodyZeroTaxtRow(bill, row);
          return true;
        }
        Map<String, String> orgCorp  = new HashMap<String,String>();
        /*
         * change by wandl
         * 缓存解决0税率设置效率问题
         */
        if(!orgcorpmap.containsKey(supplierFinanceOrg) && !orgcorpmap.containsKey(pk_settleorg)){
        	orgCorp =
              FinanceOrgPubService.queryPKCorp(new String[] {
                supplierFinanceOrg, pk_settleorg
              });
        	orgcorpmap.putAll(orgCorp);
        }
        // 财务组织所属公司一样
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
   * by 20141105 mengjian 批量查询供应商
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
    // 第一次拉单时： 无论税码是否与上游一致，只要下游税率、扣税类别与上游一致，所有价税合计、本币价税合计、税额、本币无税金额、无税金额……全部携带。
    // 拉单时，已经对照有值后续不处理
    this.setVatValueByKey(bill, vo, pos, OrderItemVO.CTAXCODEID);// 税码
    // 联动计算依据的key，有优先顺序
    String changeKey =
        this.setVatValueByKey(bill, vo, pos, OrderItemVO.FTAXTYPEFLAG);
    changeKey = this.setVatValueByKey(bill, vo, pos, OrderItemVO.NNOSUBTAXRATE);
    changeKey = this.setVatValueByKey(bill, vo, pos, OrderItemVO.NTAXRATE);

    if (!this.fromSrcBill && !StringUtils.isEmpty(changeKey)) {
      // 进口业务，跨国时,按需求根据含税单价或无税单价联动
      Object buySellFlag = bill.getBodyValue(pos, OrderItemVO.FBUYSELLFLAG);
      if (BuySellFlagEnum.IMPORT.value().toString()
          .equals(buySellFlag.toString())) {
        changeKey = this.getPriceKeyByPara(bill);
      }
    }
    return changeKey;
  }

  private void setBodyZeroTaxtRow(IKeyValue bill, int pos) {
    // 直接取得平台内置的0税率税码；
    bill.setBodyValue(pos, OrderItemVO.NTAXRATE, Integer.valueOf(0));
    bill.setBodyValue(pos, OrderItemVO.NNOSUBTAXRATE, Integer.valueOf(0));
    bill.setBodyValue(pos, OrderItemVO.CTAXCODEID,
        ZeroTaxCodeEnum.ZEROTAXCODE.getCode());// 零税码
    bill.setBodyValue(pos, OrderItemVO.FTAXTYPEFLAG,
        EnumDiscounttaxtype.TAXOUT.value());// 默认应税外加

  }

  /**
   * 设置国家相关：发货国，报税国，收货国等。
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
   * 设置税率相关
   */
  protected void setVatTaxInfo() {
    // 当购销类型=国内采购、且供应商为内部供应商、供应商对应的财务组织所属公司=表体结算财务组织所属公司时，直接取得平台内置的0税率税码；
    VATInfoVO[] vatinfos = this.vatQueryRule.getVatinfos();
    if (ArrayUtils.isEmpty(vatinfos)) {
      return;
    }
    // by 20141105 mengjian 批量查询供应商
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
        // 处理0税率税码
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
          // 2012-03-16按需求（wangyf），如果未匹配到新税码，原有的税码、扣税类别、税率、不可抵扣税率清空
          this.clearDataWhenNoTaxcode(bill, pos);
          continue;
        }
        String taxcode =
            (String) bill.getBodyValue(pos, OrderItemVO.CTAXCODEID);
        // 不是从上游过来时，只要税码一致，就不进行后续的处理
        if (!this.fromSrcBill
            && StringUtils.equals(taxcode, vo.getCtaxcodeid())) {
          if (ovcb != null) {// 可能的情况是，购销类型改变
            valueChangeList.add(ovcb);
          }
          continue;
        }
        String changeKey = this.setBodyValue(bill, vo, pos);
        if (ovcb != null) {// 购销类型改变
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
