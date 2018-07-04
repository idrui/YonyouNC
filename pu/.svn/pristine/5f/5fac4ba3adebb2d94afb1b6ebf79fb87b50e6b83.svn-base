package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查数量、单价、金额、税率、扣率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-23 下午01:54:49
 */
public class ValueCheck {
  private IKeyValue keyValue;

  private UFDouble oldValue;

  public ValueCheck(IKeyValue keyValue, UFDouble oldValue) {
    this.keyValue = keyValue;
    this.oldValue = oldValue;
  }

  /**
   * 方法功能描述：检查数量、单价、金额、税率、扣率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @param key
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午01:58:36
   */
  public void checkValue(int row, String itemKey) {

    if (OrderItemVO.NASTNUM.equals(itemKey) || OrderItemVO.NNUM.equals(itemKey)
        || OrderItemVO.NQTUNITNUM.equals(itemKey)) {
      // 数量
      this.checkNum(row, itemKey);
    }
    else if (OrderItemVO.NTAXRATE.equals(itemKey)) {
      // 税率
      this.checkNTaxRate(row);
    }
    else if (OrderItemVO.NNOSUBTAXRATE.equals(itemKey)) {
      // 不可抵扣税率
      this.checkNoSubTaxRate(row);
    }
    else if (OrderItemVO.NQTORIGPRICE.equals(itemKey)) {
      // 报价原币无税单价
      this.checkNqtorigprice(row);
    }
    else if (OrderItemVO.NQTORIGTAXPRICE.equals(itemKey)) {
      // 报价原币含税单价
      this.checkNqtorigtaxprice(row);
    }
    else if (OrderItemVO.NITEMDISCOUNTRATE.equals(itemKey)) {
      // 单品折扣率
      this.checkNitemdiscountrate(row);
    }
    else if (OrderItemVO.NORIGTAXNETPRICE.equals(itemKey)) {
      // 主单位原币含税净价
      this.checkNorigtaxnetprice(row);
    }
    else if (OrderItemVO.NORIGPRICE.equals(itemKey)) {
      // 主单位原币无税单价
      this.checkNorigprice(row);
    }
  }

  /**
   * 方法功能描述：单品折扣率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午03:16:00
   */
  private void checkNitemdiscountrate(int row) {
    UFDouble nitemdiscountrate =
        (UFDouble) this.keyValue.getBodyValue(row,
            OrderItemVO.NITEMDISCOUNTRATE);
    if (MathTool.compareTo(nitemdiscountrate, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NITEMDISCOUNTRATE,
          this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0199")/*
                                                                   * @res
                                                                   * "单品折扣率不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：主单位原币无税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-24 上午08:12:22
   */
  private void checkNorigprice(int row) {
    UFDouble nitemdiscountrate =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NORIGPRICE);
    if (MathTool.compareTo(nitemdiscountrate, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NORIGPRICE, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0200")/*
                                                                   * @res
                                                                   * "主单位原币无税单价不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：主单位原币含税净价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-24 上午08:09:59
   */
  private void checkNorigtaxnetprice(int row) {
    UFDouble nitemdiscountrate =
        (UFDouble) this.keyValue
            .getBodyValue(row, OrderItemVO.NORIGTAXNETPRICE);
    if (MathTool.compareTo(nitemdiscountrate, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NORIGTAXNETPRICE,
          this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0201")/*
                                                                   * @res
                                                                   * "主单位原币含税净价不能小于0"
                                                                   */);
    }
  }

  private void checkNoSubTaxRate(int row) {
    UFDouble ntaxrate =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNOSUBTAXRATE);
    if (MathTool.compareTo(ntaxrate, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NNOSUBTAXRATE, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0355")/*
                                                                   * @res
                                                                   * "不可抵扣税率不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：报价原币无税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午03:11:55
   */
  private void checkNqtorigprice(int row) {
    UFDouble nqtorigprice =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NQTORIGPRICE);
    if (MathTool.compareTo(nqtorigprice, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NQTORIGPRICE, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0202")/*
                                                                   * @res
                                                                   * "无税单价不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：报价原币含税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午03:12:38
   */
  private void checkNqtorigtaxprice(int row) {
    UFDouble nqtorigtaxprice =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NQTORIGTAXPRICE);
    if (MathTool.compareTo(nqtorigtaxprice, UFDouble.ZERO_DBL) < 0) {
      this.keyValue.setBodyValue(row, OrderItemVO.NQTORIGTAXPRICE,
          this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0203")/*
                                                                   * @res
                                                                   * "含税单价不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：税率检查。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午03:06:01
   */
  private void checkNTaxRate(int row) {
    UFDouble nheadTaxRate =
        (UFDouble) this.keyValue.getHeadValue(OrderHeaderVO.NHTAXRATE);
    UFDouble ntaxrate =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NTAXRATE);
    if (MathTool.compareTo(nheadTaxRate, UFDouble.ZERO_DBL) < 0
        || MathTool.compareTo(ntaxrate, UFDouble.ZERO_DBL) < 0) {
    	this.keyValue.setHeadValue(OrderHeaderVO.NHTAXRATE, null);
      this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0204")/*
                                                                   * @res
                                                                   * "税率不能小于0"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：数量检查。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @param numKey
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-23 下午02:08:42
   */
  private void checkNum(int row, String numKey) {
    UFBoolean breturn =
        ValueUtils.getUFBoolean(this.keyValue
            .getHeadValue(OrderHeaderVO.BRETURN));
    UFDouble num = (UFDouble) this.keyValue.getBodyValue(row, numKey);
    if (MathTool.compareTo(num, UFDouble.ZERO_DBL) > 0
        && breturn.booleanValue()) {
      this.keyValue.setBodyValue(row, numKey, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0197")/*
                                                                   * @res
                                                                   * "退货订单，数量不允许为正"
                                                                   */);
    }
    else if (MathTool.compareTo(num, UFDouble.ZERO_DBL) < 0
        && !breturn.booleanValue()) {
      this.keyValue.setBodyValue(row, numKey, this.oldValue);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0198")/*
                                                                   * @res
                                                                   * "非退货订单，数量不允许为负"
                                                                   */);
    }
  }
}
