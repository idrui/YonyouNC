package nc.vo.pu.pub.calculate;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 采购简单单价金额处理类，用于计算简单的数量单价金额的计算。
 * 要求:只有数量,主数量，单价为主单位的单价，金额为主单位的金额。
 * 
 * @since 6.1
 * @version 2012-8-3 下午04:40:53
 * @author tianft
 */
public class PuSimpleCalculator {

  public class AstNumCalculator implements IPuSimpleCalStrategy {
    @Override
    public void calculate(PuSimpleCalCondition condition) {
      // 1.反算主数量->根据主数量和主单价反算金额
      UFDouble astnum = PuSimpleCalculator.this.getDataSet().getAstNnum();
      // 清空数量
      if (astnum == null) {
        PuSimpleCalculator.this.getDataSet().setNum(null);
        PuSimpleCalculator.this.getDataSet().setMny(null);
        return;
      }
      String changeRate = PuSimpleCalculator.this.getDataSet().getChangeRate();
      String unitid = PuSimpleCalculator.this.getDataSet().getUnitid();
      UFDouble value = HslParseUtil.hslMultiplyUFDouble(changeRate, astnum);
      PuSimpleCalculator.this.getDataSet()
          .setNum(
              PuSimpleCalculator.this.getScaleUtils().adjustNumScale(value,
                  unitid));
      // 根据主数量和主单价反算金额
      new PriceCalculator().calculate(condition);
    }
  }

  public class ChangeRateCalculator implements IPuSimpleCalStrategy {
    @Override
    public void calculate(PuSimpleCalCondition condition) {
      // 根据主数量换算数量
      UFDouble num = PuSimpleCalculator.this.getDataSet().getNnum();
      String changeRate = PuSimpleCalculator.this.getDataSet().getChangeRate();
      // 清空换算率，不处理
      if (StringUtils.isEmpty(changeRate)) {
        return;
      }
      String astUnitid = PuSimpleCalculator.this.getDataSet().getAstUnitid();
      UFDouble value = HslParseUtil.hslDivUFDouble(changeRate, num);
      PuSimpleCalculator.this.getDataSet().setAstNum(
          PuSimpleCalculator.this.getScaleUtils().adjustNumScale(value,
              astUnitid));
    }
  }

  /**
   * 计算策略
   * 
   * @since 6.1
   * @version 2012-8-7 上午11:19:39
   * @author tianft
   */
  public interface IPuSimpleCalStrategy {
    void calculate(PuSimpleCalCondition condition);
  }

  /**
   * 主单位金额发起的计算
   * 
   * @since 6.1
   * @version 2012-8-4 下午04:25:54
   * @author tianft
   */
  public class MnyCalculator implements IPuSimpleCalStrategy {
    @Override
    public void calculate(PuSimpleCalCondition condition) {
      // 主单位金额根据主数量反算主单价
      UFDouble mny = PuSimpleCalculator.this.getDataSet().getMny();
      UFDouble price = PuSimpleCalculator.this.getDataSet().getPrice();
      UFDouble num = PuSimpleCalculator.this.getDataSet().getNnum();
      if (mny == null) {
        PuSimpleCalculator.this.getDataSet().setPrice(null);
        return;
      }
      if (MathTool.isZero(num) && MathTool.isZero(price)) {
        if (MathTool.isZero(mny)) {
          // 单价、数量，主数量，都0
          PuSimpleCalculator.this.getDataSet().setPrice(
              PuSimpleCalculator.this.getScaleUtils().adjustSoPuPriceScale(
                  UFDouble.ZERO_DBL,
                  PuSimpleCalculator.this.getDataSet().getCurrencyid()));
          PuSimpleCalculator.this.getDataSet().setNum(
              PuSimpleCalculator.this.getScaleUtils().adjustNumScale(
                  UFDouble.ZERO_DBL,
                  PuSimpleCalculator.this.getDataSet().getUnitid()));
          PuSimpleCalculator.this.getDataSet().setAstNum(
              PuSimpleCalculator.this.getScaleUtils().adjustNumScale(
                  UFDouble.ZERO_DBL,
                  PuSimpleCalculator.this.getDataSet().getAstUnitid()));
        }
        else {
          // 单价、数量,主数量，都空
          PuSimpleCalculator.this.getDataSet().setPrice(null);
          PuSimpleCalculator.this.getDataSet().setNum(null);
          PuSimpleCalculator.this.getDataSet().setAstNum(null);
        }
        return;
      }
      if (MathTool.isZero(num)) {
        this.calculateNum(condition);
      }
      else {
        UFDouble tempValue = CalculatorUtil.div(mny, num);
        // 设置单价，处理精度
        PuSimpleCalculator.this.getDataSet().setPrice(
            PuSimpleCalculator.this.getScaleUtils()
                .adjustSoPuPriceScale(tempValue,
                    PuSimpleCalculator.this.getDataSet().getCurrencyid()));
      }

    }

    /**
     * 由金额、单价反算数量
     * 
     * @param condition
     */
    public void calculateNum(PuSimpleCalCondition condition) {
      UFDouble mny = PuSimpleCalculator.this.getDataSet().getMny();
      UFDouble price = PuSimpleCalculator.this.getDataSet().getPrice();
      UFDouble tempValue = CalculatorUtil.div(mny, price);
      PuSimpleCalculator.this.getDataSet().setNum(
          PuSimpleCalculator.this.getScaleUtils().adjustNumScale(tempValue,
              PuSimpleCalculator.this.getDataSet().getUnitid()));
      // 由主数量计算数量
      new NumCalculator().calAstNum(condition);
    }
  }

  public class NumCalculator implements IPuSimpleCalStrategy {
    public void calAstNum(PuSimpleCalCondition condition) {
      // 反算数量（考虑是否固定换算率），根据主数量和主单价反算金额
      UFDouble num = PuSimpleCalculator.this.getDataSet().getNnum();
      if (num == null) {
        PuSimpleCalculator.this.getDataSet().setAstNum(null);
        return;
      }
      String changeRate = PuSimpleCalculator.this.getDataSet().getChangeRate();
      String astUnitid = PuSimpleCalculator.this.getDataSet().getAstUnitid();
      if (condition.isIsfixedChangeRate()
          || PuSimpleCalculator.this.getDataSet().getAstNnum() == null) {
        // 固定换算率，根据换算率反算数量
        UFDouble astnum = HslParseUtil.hslDivUFDouble(changeRate, num);
        PuSimpleCalculator.this.getDataSet().setAstNum(
            PuSimpleCalculator.this.getScaleUtils().adjustNumScale(astnum,
                astUnitid));
      }
      else {
        UFDouble astnum = PuSimpleCalculator.this.getDataSet().getAstNnum();
        // 根据数量反算换算率
        String value = HslParseUtil.buildHslString(num, astnum);
        value = PuSimpleCalculator.this.getScaleUtils().adjustHslScale(value);
        PuSimpleCalculator.this.getDataSet().setChangeRate(value);
      }
    }

    @Override
    public void calculate(PuSimpleCalCondition condition) {
      // 计算数量
      this.calAstNum(condition);
      UFDouble price = PuSimpleCalculator.this.getDataSet().getPrice();
      UFDouble mny = PuSimpleCalculator.this.getDataSet().getMny();
      UFDouble num = PuSimpleCalculator.this.getDataSet().getNnum();
      if (num == null) {
        // 金额置为空
        PuSimpleCalculator.this.getDataSet().setMny(null);
        return;
      }
      if (price == null) {
        // 根据金额算单价
        UFDouble tempValue = CalculatorUtil.div(mny, num);
        // 设置单价，处理精度
        PuSimpleCalculator.this.getDataSet().setPrice(
            PuSimpleCalculator.this.getScaleUtils()
                .adjustSoPuPriceScale(tempValue,
                    PuSimpleCalculator.this.getDataSet().getCurrencyid()));
      }
      else {
        // 根据单价算金额
        UFDouble tempValue = CalculatorUtil.multiply(price, num);
        PuSimpleCalculator.this.getDataSet().setMny(
            PuSimpleCalculator.this.getScaleUtils().adjustMnyScale(tempValue,
                PuSimpleCalculator.this.getDataSet().getCurrencyid()));
      }

    }
  }

  public class PriceCalculator implements IPuSimpleCalStrategy {
    @Override
    public void calculate(PuSimpleCalCondition condition) {
      // 根据单价和主数量计算金额
      UFDouble price = PuSimpleCalculator.this.getDataSet().getPrice();
      UFDouble num = PuSimpleCalculator.this.getDataSet().getNnum();
      // 数量空的时候，从单价反算数量
      if (num == null) {
        new MnyCalculator().calculateNum(condition);
      }
      else {
        UFDouble value = CalculatorUtil.multiply(price, num);
        PuSimpleCalculator.this.getDataSet().setMny(
            PuSimpleCalculator.this.getScaleUtils().adjustMnyScale(value,
                PuSimpleCalculator.this.getDataSet().getCurrencyid()));
      }
    }
  }

  /**
   * 数据存储类
   */
  private PuSimpleCalculateDataSet dataSet;

  /**
   * 精度处理工具类
   */
  private ScaleUtils scale;

  /**
   * 计算策略
   */
  private Map<String, IPuSimpleCalStrategy> strategy =
      new HashMap<String, IPuSimpleCalStrategy>();

  public PuSimpleCalculator(PuSimpleCalculateDataSet dataSet, ScaleUtils scale) {
    this.dataSet = dataSet;
    this.scale = scale;
    this.initCalStrategy();
  }

  /**
   * 计算入口
   * 
   * @param condition
   * @param itemKey
   */
  public void calculate(PuSimpleCalCondition condition, String itemKey) {
    IPuSimpleCalStrategy calStrategy = this.strategy.get(itemKey);
    if (calStrategy != null) {
      calStrategy.calculate(condition);
    }
  }

  public PuSimpleCalculateDataSet getDataSet() {
    return this.dataSet;
  }

  public ScaleUtils getScaleUtils() {
    return this.scale;
  }

  /**
   * 初始化策略
   */
  private void initCalStrategy() {
    // 数量
    this.strategy.put(this.getDataSet().getItems().getAstnumKey(),
        new AstNumCalculator());
    // 换算率
    this.strategy.put(this.getDataSet().getItems().getChangeRateKey(),
        new ChangeRateCalculator());
    // 金额
    this.strategy.put(this.getDataSet().getItems().getMnyKey(),
        new MnyCalculator());
    // 主数量
    this.strategy.put(this.getDataSet().getItems().getNumKey(),
        new NumCalculator());
    // 单价
    this.strategy.put(this.getDataSet().getItems().getPriceKey(),
        new PriceCalculator());

  }

}
