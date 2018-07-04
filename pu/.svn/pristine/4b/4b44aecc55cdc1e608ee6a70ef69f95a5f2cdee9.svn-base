package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * @since 6.0
 * @version 2010-12-15 下午06:11:09
 * @author wuxla
 */

public class RelationCalculate {

  private static class InitialEstDataSetForCal extends VODataSetForCal {
    private InitialEstVO initialEstVO;

    public InitialEstDataSetForCal(CircularlyAccessibleValueObject currVO,
        IRelationForItems item, InitialEstVO initialEstVO) {
      super(currVO, item);
      this.initialEstVO = initialEstVO;
    }

    @Override
    public UFDate getBillDate() {
      return this.initialEstVO.getHeader().getDbilldate();
    }

    @Override
    public String getCcurrencyid() {
      return this.initialEstVO.getHeader().getCcurrencyid();
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.initialEstVO.getHeader().getCorigcurrencyid();
    }

    @Override
    public UFDouble getNexchangerate() {
      return this.initialEstVO.getHeader().getNexchangerate();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (InitialEstHeaderVO.NEXCHANGERATE.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.CCURRENCYID.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.DBILLDATE.equals(key)) {
        return true;
      }
      else {
        return super.hasItemKey(key);
      }
    }
  }

  private static class InitialEstRelationItemForCal extends RelationItemForCal {
    public InitialEstRelationItemForCal() {
      // 构造函数
    }

    @Override
    public String getCastunitidKey() {
      return super.getCqtunitidKey();
    }

    @Override
    public String getCqtunitidKey() {
      return super.getCastunitidKey();
    }

    @Override
    public String getNnetpriceKey() {
      return InitialEstItemVO.NPRICE;
    }

    @Override
    public String getNorignetpriceKey() {
      return InitialEstItemVO.NORIGPRICE;
    }

    @Override
    public String getNorigtaxnetpriceKey() {
      return InitialEstItemVO.NORIGTAXPRICE;
    }

    @Override
    public String getNqtnetpriceKey() {
      return InitialEstItemVO.NASTPRICE;
    }

    @Override
    public String getNqtorignetpriceKey() {
      return InitialEstItemVO.NASTORIGPRICE;
    }

    @Override
    public String getNqtorigpriceKey() {
      return InitialEstItemVO.NASTORIGPRICE;
    }

    @Override
    public String getNqtorigtaxnetprcKey() {
      return InitialEstItemVO.NASTORIGTAXPRICE;
    }

    @Override
    public String getNqtorigtaxpriceKey() {
      return InitialEstItemVO.NASTORIGTAXPRICE;
    }

    @Override
    public String getNqtpriceKey() {
      return InitialEstItemVO.NASTPRICE;
    }

    @Override
    public String getNqttaxnetpriceKey() {
      return InitialEstItemVO.NASTTAXPRICE;
    }

    @Override
    public String getNqttaxpriceKey() {
      return InitialEstItemVO.NASTTAXPRICE;
    }

    @Override
    public String getNqtunitnumKey() {
      return InitialEstItemVO.NASTNUM;
    }

    @Override
    public String getNqtunitrateKey() {
      return InitialEstItemVO.VCHANGERATE;
    }

    @Override
    public String getNtaxnetpriceKey() {
      return InitialEstItemVO.NTAXPRICE;
    }
  }

  /** 强制使用固定换算率,一般为了强制从主单位发计算 **/
  private boolean forceFixChgRate = false;

  /**
   * 只计算数量线
   * 
   * @param vo
   * @param chgKey
   */
  public void calcNum(InitialEstVO vo, String chgKey) {
    this.calculate(vo, chgKey, true);
  }

  /**
   * 全联动计算
   * 
   * @param vo
   * @param chgKey
   */
  public void calculate(InitialEstVO vo, String chgKey) {
    this.calculate(vo, chgKey, false);
  }

  public boolean isForceFixChgRate() {
    return this.forceFixChgRate;
  }

  public void setForceFixChgRate(boolean forceFixChgRate) {
    this.forceFixChgRate = forceFixChgRate;
  }

  private void calculate(InitialEstVO initialEstVO, String itemKey,
      boolean onlyNum) {
    IRelationForItems item = new InitialEstRelationItemForCal();
    ScaleUtils scale = new ScaleUtils(initialEstVO.getHeader().getPk_group());
    String pk_org = initialEstVO.getHeader().getPk_purchaseorg();
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);
    InitialEstItemVO[] itemVOs = initialEstVO.getItems();
    for (InitialEstItemVO itemVO : itemVOs) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data =
          new InitialEstDataSetForCal(itemVO, item, initialEstVO);
      Calculator tool = new Calculator(data, scale);
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      // 创建参数实例
      Condition cond = new Condition();
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);

      // 只有主单价没有单位的单价时，计算的策略
      if (InitialEstItemVO.NASTNUM.equals(itemKey)
          || InitialEstItemVO.NNUM.equals(itemKey)) {
        if (itemVO.getNastorigprice() == null
            || itemVO.getNastorigtaxprice() == null) {
          cond.setCalLocalPior(true);
          if (InitialEstItemVO.NNUM.equals(itemKey)) {
            cond.setUnitPriorType(Condition.MAIN_PRIOR);
          }
        }
      }

      // 设置调单价方式调单价
      cond.setIsChgPriceOrDiscount(true);
      String material = itemVO.getPk_material();
      String cunitid = itemVO.getCunitid();
      String castunitid = itemVO.getCastunitid();
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      if (this.isForceFixChgRate()) {
        cond.setIsFixNqtunitrate(true);
      }
      else {
        // 是否固定报价单位换算率
        cond.setIsFixNqtunitrate(this.isFixUnitRate(material, cunitid,
            castunitid));
      }
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);

      // VAT联动计算，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
      // 因为采购支持进口采购、国内采购。所以不用判断出口销售
      Integer fbuysellflag = itemVO.getFbuysellflag();
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));

      if (onlyNum) {
        // 只计算数量线
        tool.calculateOnlyNumAssNumQtNum(cond, itemKey);
      }
      else {
        // 全联动计算
        tool.calculate(cond, itemKey);
      }
    }
  }

  /**
   * 方法功能描述：是否固定换算率
   * <p>
   * <b>参数说明</b>
   * 
   * @param material
   * @param cunitid
   * @param castunitid
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:50:58
   */
  private boolean isFixUnitRate(String material, String cunitid,
      String castunitid) {
    boolean flag = true;
    if (material == null || cunitid == null || castunitid == null) {
      return flag;
    }
    flag =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
            cunitid, castunitid);
    return flag;
  }

  /**
   * 方法功能描述：价格优先策略
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:51:21
   */
  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }
}
