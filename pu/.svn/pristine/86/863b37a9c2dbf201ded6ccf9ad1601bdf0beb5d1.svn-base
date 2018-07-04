/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 下午09:07:10
 */
package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pu.scale.PuScaleUtils;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.para.NCPara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算，主要用于由其他单据生成采购订单时的计算。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-9 下午09:07:10
 */
public class RelationCalculate {
  private static class OrderDataSetForCal extends VODataSetForCal {
    private OrderVO orderVO;

    public OrderDataSetForCal(CircularlyAccessibleValueObject currVO,
        IRelationForItems item, OrderVO orderVO) {
      super(currVO, item);
      this.orderVO = orderVO;
    }

    @Override
    public UFDate getBillDate() {
      return this.orderVO.getHVO().getDbilldate();
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.orderVO.getHVO().getCorigcurrencyid();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      else if (OrderHeaderVO.DBILLDATE.equals(key)) {
        return true;
      }
      else {
        return super.hasItemKey(key);
      }
    }
  }

  public void calculate(OrderVO orderVO, String itemKey) {
    this.calculate(orderVO, itemKey, null);
  }

  /**
   * 联动计算，不计算体积件数等信息
   * <p>
   * 使用场景：
   * <ul>
   * <li>在联动计算前已经计算了体积件数等信息，所以联动计算时不需要再重新计算
   * <li>例如转单时，先根据主数量进行体积件数等信息计算，之后的联动计算不需要再计算
   * </ul>
   * 
   * @param orderVO
   * @param itemKey
   * @param bfixrate
   * @param bdiscount
   */
  public void calculate(OrderVO orderVO, String itemKey, boolean bfixrate,
      boolean bdiscount) {
    this.calculate(orderVO, itemKey, bfixrate, bdiscount, null);
  }

  /**
   * 联动计算，需要计算体积件数等信息
   * <p>
   * 使用场景：
   * <ul>
   * <li>在联动计算时计算体积件数等信息，比如主数量改变，体积件数也要改变。
   * <li>为效率考虑，先查询物料信息，在联动计算
   * </ul>
   * 
   * @param orderVO
   * @param itemKey
   * @param bfixrate
   * @param bdiscount
   * @param wvpCalc 计算体积件数工具
   */
  public void calculate(OrderVO orderVO, String itemKey, boolean bfixrate,
      boolean bdiscount, WeightVolumePieceCalc wvpCalc) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = new ScaleUtils(orderVO.getHVO().getPk_group());
    String pkOrg = orderVO.getHVO().getPk_org();
    boolean isChangePrice = false;
    if (!bdiscount) {
      isChangePrice = this.isChangePrice(pkOrg);
    }
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pkOrg);
    String pk_group = orderVO.getHVO().getPk_group();
    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

    OrderItemVO[] itemVOs = orderVO.getBVO();
    int[] rows = new int[orderVO.getBVO().length];
    for (int i = 0; i < orderVO.getBVO().length; i++) {
      rows[i] = i;
    }
    for (OrderItemVO itemVO : itemVOs) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new OrderDataSetForCal(itemVO, item, orderVO);
      Calculator tool = new Calculator(data, scale);
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();// 创建参数实例
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);
      // 设置调单价方式调折扣
      cond.setIsChgPriceOrDiscount(isChangePrice);
      // 全局本位币计算方式
      cond.setGlobalLocalCurrencyEnable(publicLocalMnyCal);
      // 集团本位币计算方式
      cond.setGroupLocalCurrencyEnable(groupLobalMnyCal);

      cond.setOrigCurToGlobalMoney(isOrigCurToGlobalMoney);
      cond.setOrigCurToGroupMoney(isOrigCurToGroupMoney);

      if (bfixrate) {
        // 设置是否固定单位换算率
        cond.setIsFixNchangerate(true);
        // 是否固定报价单位换算率
        cond.setIsFixNqtunitrate(true);
      }
      else {
        String material = itemVO.getPk_material();
        String cunitid = itemVO.getCunitid();
        String castunitid = itemVO.getCastunitid();
        String cqtunitid = itemVO.getCqtunitid();
        // 设置是否固定单位换算率
        cond.setIsFixNchangerate(this.isFixUnitRate(material, cunitid,
            castunitid));
        // 是否固定报价单位换算率
        cond.setIsFixNqtunitrate(this.isFixUnitRate(material, cunitid,
            cqtunitid));
      }

      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag = itemVO.getFbuysellflag();
      // 是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, itemKey);
    }
    if (null != wvpCalc) {
      wvpCalc.calc(rows);// 总体积、数量、重量计算
    }
  }

  /**
   * 此方法只用于固定换算率
   * 拉单过来时需要保持来源的换算率
   * 
   * @param orderVO
   * @param itemKey
   */
  public void calculate(OrderVO orderVO, String itemKey,
      WeightVolumePieceCalc wvpCalc) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = new PuScaleUtils(orderVO.getHVO().getPk_group());
    String pkOrg = orderVO.getHVO().getPk_org();
    boolean isChangePrice = this.isChangePrice(pkOrg);
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pkOrg);
    String pk_group = orderVO.getHVO().getPk_group();
    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

    OrderItemVO[] itemVOs = orderVO.getBVO();
    int[] rows = new int[orderVO.getBVO().length];
    for (int i = 0; i < orderVO.getBVO().length; i++) {
      rows[i] = i;
    }
    for (OrderItemVO itemVO : itemVOs) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new OrderDataSetForCal(itemVO, item, orderVO);
      Calculator tool = new Calculator(data, scale);
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();// 创建参数实例
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);
      // 设置调单价方式调折扣
      cond.setIsChgPriceOrDiscount(isChangePrice);
      // 全局本位币计算方式
      cond.setGlobalLocalCurrencyEnable(publicLocalMnyCal);
      // 集团本位币计算方式
      cond.setGroupLocalCurrencyEnable(groupLobalMnyCal);
      cond.setOrigCurToGlobalMoney(isOrigCurToGlobalMoney);
      cond.setOrigCurToGroupMoney(isOrigCurToGroupMoney);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(true);
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag = itemVO.getFbuysellflag();
      // 是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, itemKey);
    }
    if (null != wvpCalc) {
      wvpCalc.calc(rows);// 总体积、数量、重量计算
    }
  }

  private boolean groupLobalMnyCal(String pk_group) {
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    if (NCPara.NC001_NOUSEGROUPCURRTYPE.name().equals(nc001)) {
      return false;
    }
    return true;
  }

  private boolean isChangePrice(String pk_org) {
    return PUParaValue.po84.adjust_price == PUSysParamUtil.getPO84(pk_org);
  }

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

  private boolean isOrigCurToGlobalMoney() {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
      return true;
    }
    return false;
  }

  private boolean isOrigCurToGroupMoney(String pk_group) {
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
      return true;
    }
    return false;
  }

  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }

  private boolean publicLocalMnyCal() {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC002_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
      return false;
    }
    return true;
  }

}
