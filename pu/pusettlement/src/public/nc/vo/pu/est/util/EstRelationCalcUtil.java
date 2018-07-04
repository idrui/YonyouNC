/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 下午06:27:18
 */
package nc.vo.pu.est.util;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估计算工具
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-18 下午06:27:18
 */
public class EstRelationCalcUtil {

  private Condition condition;

  private String pk_group;

  private IRelationForItems relaItems;

  /**
   * EstRelationCalcUtil 的构造子
   * 
   * @param pk_group
   */
  public EstRelationCalcUtil(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * EstRelationCalcUtil 的构造子
   * 
   * @param pk_group
   */
  public EstRelationCalcUtil(String pk_group, IRelationForItems relaItems) {
    this.pk_group = pk_group;
    this.relaItems = relaItems;
  }

  /**
   * 方法功能描述：UI联动计算，请使用一个参数(集团)的构造函数。
   * 如果在其他地方调用，需要将获取fbuysellflag后面的ID_SUFFIX去掉
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param chgKey
   * @param priceprior
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-18 下午03:26:18
   */
  public void calcDataSet(IDataSetForCal dataset, String chgKey,
      PricePriority priceprior) {
    Calculator calculator =
        new Calculator(dataset, new ScaleUtils(this.pk_group));

    Condition cond = this.getCondition(priceprior);
    // wuxla V61 VAT设置，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
    // 采购只需判断进口采购就行
    // 因为改方法主要用于前台，所以使用ID_SUFFIX
    Integer fbuysellflag =
        (Integer) dataset.getAttributeValue(GoodsEstVO.FBUYSELLFLAG
            + IBillItem.ID_SUFFIX);
    cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));
    calculator.calculate(cond, chgKey);
  }

  /**
   * 方法功能描述：费用暂估的UI联动计算，请使用一个参数(集团)的构造函数。
   * 如果不是UI，需要将获取fbuysellflag的IBillItem.ID_SUFFIX去掉。
   * <p>
   * <b>参数说明</b>
   * 
   * @param dataset
   * @param chgKey
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-7 下午04:45:07
   */
  public void calcDataSetForFee(IDataSetForCal dataset, String chgKey) {
    Condition cond = this.getCondition();
    cond.setIsTaxOrNet(true);// 需求确认含税优先
    // wuxla V61 VAT设置，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
    // 采购只需判断进口采购就行
    // 因为该方法主要用于前台，所以使用ID_SUFFIX，取主键，否则会取到名称
    Integer fbuysellflag =
        (Integer) dataset.getAttributeValue(GoodsEstVO.FBUYSELLFLAG
            + IBillItem.ID_SUFFIX);
    cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));

    Calculator calculator =
        new Calculator(dataset, new ScaleUtils(this.pk_group));
    calculator.calculate(cond, chgKey);
  }

  /**
   * 方法功能描述：VO联动计算，请使用两个参数的构造函数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param chgKey
   * @param priceprior
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-18 下午03:26:18
   */
  public void calcVO(CircularlyAccessibleValueObject vo, String chgKey,
      PricePriority priceprior) {
    // this.calcVOs(new CircularlyAccessibleValueObject[] {
    // vo
    // }, chgKey, priceprior);
    Calculator calculator =
        new Calculator(new CircularlyAccessibleValueObject[] {
          vo
        }, this.relaItems);
    Condition cond = this.getCondition(priceprior);
    // VAT设置，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
    // 采购只需判断进口采购就行
    Integer fbuysellflag =
        (Integer) vo.getAttributeValue(GoodsEstVO.FBUYSELLFLAG);
    cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));
    calculator.calculate(cond, chgKey, new ScaleUtils(this.pk_group));
  }

  /**
   * 方法功能描述：VO联动计算，请使用两个参数的构造函数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param chgKey
   * @param priceprior
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-18 下午03:26:18
   */
  public void calcVOs(CircularlyAccessibleValueObject[] vos, String chgKey,
      PricePriority priceprior) {
    // Calculator calculator = new Calculator(vos, this.relaItems);
    // calculator.calculate(this.getCondition(priceprior), chgKey, new
    // ScaleUtils(
    // this.pk_group));

    for (CircularlyAccessibleValueObject vo : vos) {
      this.calcVO(vo, chgKey, priceprior);
    }
  }

  public Condition getCondition() {
    if (null == this.condition) {
      this.condition = new Condition();
      this.condition.setIsCalLocalCurr(true);
      this.condition.setUnitPriorType(Condition.MAIN_PRIOR);
      // 扣税类别默认处理为应税内含，计算的Item中包括扣税类别，则走Item中的值
      // 对费用的联动计算走默认（其Item中没有扣税类别），而货物暂估也走Item值
      this.condition.setOutTax(false);
    }
    return this.condition;
  }

  /**
   * @return relaItems
   */
  public IRelationForItems getRelaItems() {
    return this.relaItems;
  }

  /**
   * @param relaItems
   *          要设置的 relaItems
   */
  public void setRelaItems(IRelationForItems relaItems) {
    this.relaItems = relaItems;
  }

  private Condition getCondition(PricePriority priceprior) {
    Condition cond = this.getCondition();
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE == priceprior) {
      cond.setIsTaxOrNet(true);
    }
    else {
      cond.setIsTaxOrNet(false);
    }
    return cond;
  }

}
