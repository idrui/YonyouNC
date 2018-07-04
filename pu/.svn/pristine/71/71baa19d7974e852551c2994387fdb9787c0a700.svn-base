package nc.vo.pu.m25.pub;

import java.util.HashSet;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.calculator.InvoiceCalculator;
import nc.vo.pu.m25.calculator.data.InvoiceRelationItemForCal;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算(用于VO计算，而不是卡片)
 * </ul>
 * <p>
 * TODO tianft 针对卡片和vo各有一个计算器，维护不是很方便，考虑以后核心能重构到一个
 */
public class VORelationCalculate {
  /**
   * @version 6.0
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 下午05:19:30
   */
  private class InvoiceVODataSet extends VODataSetForCal {

    private InvoiceHeaderVO headerVO = null;

    private InvoiceItemVO itemVO = null;

    private IRelationForItems relaItem = null;

    public InvoiceVODataSet(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
        IRelationForItems item) {
      super(itemVO, item);
      this.headerVO = headerVO;
      this.itemVO = itemVO;
      this.relaItem = item;
    }

    @Override
    public Object getAttributeValue(String key) {
      return super.getAttributeValue(key);
    }

    @Override
    public UFDate getBillDate() {
      return this.headerVO.getDbilldate();
    }

    @Override
    public String getCastunitid() {
      String value = this.itemVO.getCastunitid();
      return value;
    }

    @Override
    public String getCcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CCURRENCYID);
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    }

    @Override
    public String getCqtunitid() {
      return this.itemVO.getCastunitid();
    }

    @Override
    public UFDouble getNexchangerate() {
      return this.headerVO.getNexchangerate();
    }

    @Override
    public UFDouble getNglobalexchgrate() {
      return this.headerVO.getNglobalexchgrate();
    }

    @Override
    public UFDouble getNgroupexchgrate() {
      return this.headerVO.getNgroupexchgrate();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (VORelationCalculate.this.getKeys() != null
          && VORelationCalculate.this.getKeys().contains(key)) {
        return true;
      }
      if (this.relaItem.getNitemdiscountrateKey().equals(key)) {
        return true;
      }
      else if (this.relaItem.getCastunitidKey().equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }

    /**
     * 方法功能描述：取表头字段值
     * <p>
     * <b>参数说明</b>
     * 
     * @param itemKey
     * @return <p>
     * @since 6.0
     * @author tianft
     * @time 2010-3-30 下午07:40:59
     */
    private String getHeadItemStringValue(String itemKey) {
      Object value = this.headerVO.getAttributeValue(itemKey);
      return ValueUtils.getString(value);
    }
  }

  /** 强制使用固定换算率,一般为了强制从主单位发计算 **/
  private UFBoolean bForceFixChgRate = UFBoolean.FALSE;

  /** 是否强制无税优先 **/
  private boolean forceNoTaxPrior = false;

  /** 映射的itemkey，用于hasItemKey() */
  private HashSet<String> keys = null;

  public void calculate(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
      String itemKey) {
    IRelationForItems item = new InvoiceRelationItemForCal();
    ScaleUtils scale = new ScaleUtils(headerVO.getPk_group());
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new InvoiceVODataSet(headerVO, itemVO, item);
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();// 创建参数实例
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
    String pk_group = headerVO.getPk_group();
    // 获取集团参数
    int globalPara = PubSysParamUtil.getNC002IntValue();
    int groupPara = PubSysParamUtil.getNC001IntValue(pk_group);
    // 是否启用全局本位币
    cond.setGlobalLocalCurrencyEnable(globalPara != PubSysParamUtil.GLOBAL_DISABLE);
    // 全局本位币计算方式
    cond.setOrigCurToGlobalMoney(globalPara == PubSysParamUtil.GLOBAL_ORIG_BASE);
    // 是否启用集团本位币
    cond.setGroupLocalCurrencyEnable(groupPara != PubSysParamUtil.GROUP_DISABLE);
    // 集团本位币计算方式
    cond.setOrigCurToGroupMoney(groupPara == PubSysParamUtil.GROUP_ORIG_BASE);
    // 只有主单价没有单位的单价时，计算的策略
    if (InvoiceItemVO.NASTNUM.equals(itemKey)
        || InvoiceItemVO.NNUM.equals(itemKey)) {
      if (itemVO.getNastorigprice() == null
          || itemVO.getNastorigtaxprice() == null) {
        cond.setCalLocalPior(true);
        if (InvoiceItemVO.NNUM.equals(itemKey)) {
          cond.setUnitPriorType(Condition.MAIN_PRIOR);
        }
      }
    }

    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    if (this.bForceFixChgRate.booleanValue()) {
      cond.setIsFixNqtunitrate(true);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
    }
    else {
      cond.setIsFixNqtunitrate(this.IsFixNchangerate(itemVO));
      cond.setIsFixNchangerate(cond.getIsFixNqtunitrate());
    }
    // 是否强制无税
    if (this.isForceNoTaxPrior()) {
      // 设置无税优先
      cond.setIsTaxOrNet(false);
    }
    else {
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(this.IsTaxPrior(headerVO.getPk_purchaseorg()));
    }
    // 设置是否跨国采购标志
    InvoiceVO ivo = new InvoiceVO();
    ivo.setParentVO(headerVO);
    cond.setInternational(InvoiceCalculator
        .isInternationalPur(new BillHelper<InvoiceVO>(ivo)));

    tool.calculate(cond, itemKey);
  }

  /**
   * 只联动计算数量
   * 
   * @param headerVO
   * @param itemVO
   * @param itemKey
   */
  public void calculateOnlyNum(InvoiceHeaderVO headerVO, InvoiceItemVO itemVO,
      String itemKey) {
    IRelationForItems item = new InvoiceRelationItemForCal();
    ScaleUtils scale = new ScaleUtils(headerVO.getPk_group());
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new InvoiceVODataSet(headerVO, itemVO, item);
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();// 创建参数实例
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    cond.setIsFixNqtunitrate(true);
    InvoiceVO ivo = new InvoiceVO();
    ivo.setParentVO(headerVO);
    cond.setInternational(InvoiceCalculator
        .isInternationalPur(new BillHelper<InvoiceVO>(ivo)));
    tool.calculateOnlyNumAssNumQtNum(cond, itemKey);
  }

  /**
   * @return keys
   */
  public HashSet<String> getKeys() {
    if (this.keys == null) {
      this.keys = new HashSet<String>();
      // 发票日期
      this.keys.add(InvoiceHeaderVO.DBILLDATE);
      // 汇率
      this.keys.add(InvoiceHeaderVO.NEXCHANGERATE);

      this.keys.add(InvoiceHeaderVO.NGLOBALEXCHGRATE);
      this.keys.add(InvoiceHeaderVO.NGROUPEXCHGRATE);

    }
    return this.keys;
  }

  public boolean isForceNoTaxPrior() {
    return this.forceNoTaxPrior;
  }

  /**
   * @param forceFixChgRate 要设置的 是否强制使用固定换算率
   */
  public void setBForceFixChgRate(UFBoolean forceFixChgRate) {
    this.bForceFixChgRate = forceFixChgRate;
  }

  public void setForceNoTaxPrior(boolean noTaxPrior) {
    this.forceNoTaxPrior = noTaxPrior;
  }

  /**
   * @param keys 要设置的 keys
   */
  public void setKeys(HashSet<String> keys) {
    this.keys = keys;
  }

  /**
   * 方法功能描述：查询是否固定换算率
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 下午04:47:38
   */
  private boolean IsFixNchangerate(InvoiceItemVO itemVO) {
    if (this.bForceFixChgRate.booleanValue()) {
      return true;
    }
    String pk_material = itemVO.getPk_material();
    String castunitid = itemVO.getCastunitid();

    if (StringUtil.isEmptyWithTrim(pk_material)
        || StringUtil.isEmptyWithTrim(castunitid)) {
      return true;
    }

    if (castunitid.equals(itemVO.getCunitid())) {
      return true;
    }

    boolean isFixedChangeRate = false;

    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            castunitid);
    return isFixedChangeRate;
  }

  /**
   * 方法功能描述：是否含税优先
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-30 下午05:20:13
   */
  private boolean IsTaxPrior(String pk_purchaseorg) {
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }
}
