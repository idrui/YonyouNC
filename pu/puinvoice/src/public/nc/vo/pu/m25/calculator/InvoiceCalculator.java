package nc.vo.pu.m25.calculator;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-7-12 下午07:41:51
 * @author 田锋涛
 */

public class InvoiceCalculator {

  private IDataSetForCal data;

  /** 强制使用固定换算率,一般为了强制从主单位发计算 **/
  private UFBoolean forceFixChgRate = UFBoolean.FALSE;

  /** 是否强制无税优先 **/
  private boolean forceNoTaxPrior = false;

  /** key-value值对 */
  private IKeyValue keyValue;

  public InvoiceCalculator(IDataSetForCal data, IKeyValue keyValue) {
    this.data = data;
    this.keyValue = keyValue;
  }

  /**
   * 判断一张发票是否跨国采购
   * 
   * @param bill 发票（只需要表头数据）
   * @return true 跨国采购，false 国内采购
   */
  public static boolean isInternationalPur(IKeyValue bill) {
    return BuySellFlagEnum.IMPORT.value().equals(
        bill.getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG));
  }

  public void calculate(String itemKey, int row) {
    String pk_group =
        this.keyValue.getHeadValue(InvoiceHeaderVO.PK_GROUP).toString();
    ScaleUtils scale = new ScaleUtils(pk_group);
    // currentRow = row;
    // 创建数据集实例 初始化数据关系计算用的数据集
    Calculator tool = new Calculator(this.data, scale);//
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();// 创建参数实例
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
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
      if (this.keyValue.getBodyValue(row, InvoiceItemVO.NASTORIGPRICE) == null
          || this.keyValue.getBodyValue(row, InvoiceItemVO.NASTORIGTAXPRICE) == null) {
        cond.setCalLocalPior(true);
        if (InvoiceItemVO.NNUM.equals(itemKey)) {
          cond.setUnitPriorType(Condition.MAIN_PRIOR);
        }
      }
    }
    // 设置调单价方式调单价
    cond.setIsChgPriceOrDiscount(true);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(this.IsFixNchangerate(row));
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(this.IsTaxPrior(this.keyValue.getHeadValue(
        InvoiceHeaderVO.PK_PURCHASEORG).toString()));
    // 设置是否跨国采购标志
    cond.setInternational(InvoiceCalculator.isInternationalPur(this.keyValue));
    tool.calculate(cond, itemKey);
  }

  public UFBoolean getForceFixChgRate() {
    return this.forceFixChgRate;
  }

  public boolean isForceNoTaxPrior() {
    return this.forceNoTaxPrior;
  }

  public void setForceFixChgRate(UFBoolean forceFixChgRate) {
    this.forceFixChgRate = forceFixChgRate;
  }

  public void setForceNoTaxPrior(boolean forceNoTaxPrior) {
    this.forceNoTaxPrior = forceNoTaxPrior;
  }

  /**
   * 是否固定换算率
   * 
   * @param keyValue
   * @param row
   * @return
   */
  private boolean IsFixNchangerate(int row) {
    if (this.forceFixChgRate.booleanValue()) {
      return true;
    }
    String pk_material =
        this.keyValue.getBodyValue(row, InvoiceItemVO.PK_MATERIAL).toString();
    String castunitid =
        this.keyValue.getBodyValue(row, InvoiceItemVO.CASTUNITID).toString();
    String unitid =
        this.keyValue.getBodyValue(row, InvoiceItemVO.CUNITID).toString();

    if (StringUtils.isBlank(pk_material) || StringUtils.isBlank(castunitid)) {
      return true;
    }

    if (castunitid.equals(unitid)) {
      return true;
    }

    boolean isFixedChangeRate = false;

    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            castunitid);
    return isFixedChangeRate;
  }

  private boolean IsTaxPrior(String pk_purchaseorg) {
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }

}
