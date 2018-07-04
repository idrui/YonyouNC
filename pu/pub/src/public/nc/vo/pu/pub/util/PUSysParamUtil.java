package nc.vo.pu.pub.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.settle.IAutoSettleRule;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.entity.PO27VO;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购模块用到的参数查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-9 下午01:24:07
 */

public class PUSysParamUtil {

  /**
   * 采购期初日期
   * 
   * @param pk_org -财务组织
   * @return 类似 '2011-09-11'格式日期
   */
  public static String getINI02(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return SysParaInitQuery.getParaString(pk_org, POParas.INI02.name());
  }

  /**
   * 取参数日期的前一天
   * 
   * @param pk_org
   * @return
   */
  public static UFDate getINI02BeforeDate(String pk_org) {
    String value = PUSysParamUtil.getINI02(pk_org);
    if (StringUtils.isBlank(value)) {
      return null;
    }
    value = value.trim();
    int index = value.indexOf(' ');
    // 不带时间
    if (index < 0) {
      value += " 00:00:00";
    }
    return new UFDateTime(value, ICalendar.BASE_TIMEZONE).getDateTimeBefore(1)
        .getDate();
  }

  /**
   * 最高库存控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO00(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO00.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 最高限价控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO01(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO01.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 订单与到货的数量容差控制
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO02(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO02.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 订单与入库的数量容差控制
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO03(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO03.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 单价容差控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO04(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO04.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 单价容差数值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFDouble getPO05(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    UFDouble value = SysParaInitQuery.getParaDbl(pk_org, POParas.PO05.name());
    return value == null ? UFDouble.ZERO_DBL : value;

  }

  /**
   * 采购默认价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PriceSource[] getPO06(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    String po06 = SysParaInitQuery.getParaString(pk_org, POParas.PO06_V.name());
    return PUSysParamUtil.getPrice(po06);

  }

  /**
   * 请购单与进口合同数量容差控制
   * 
   * @param pk_org 采购组织
   * @return
   */
  public static PUParaValue.ctrltype getPO07(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO07.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 自动转换采购计量单位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFBoolean getPO08(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return SysParaInitQuery.getParaBoolean(pk_org, POParas.PO08.name());
  }

  /**
   * 订单最终关闭方式。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po09 getPO09(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po09.class, pk_org,
        POParas.PO09.name(), PUParaValue.po09.instance);
  }

  /**
   * 暂估处理方式。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po12 getPO12(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po12.class, pk_org,
        POParas.PO12.name(), PUParaValue.po12.mend);
  }

  /**
   * 差异转入方式。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po13 getPO13(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po13.class, pk_org,
        POParas.PO13.name(), PUParaValue.po13.cost);
  }

  /**
   * 采购订单自动询价条件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.5
   * @author mengjian
   * @time 2014-1-17 上午09:38:46
   */
  public static PriceParam[] getPO16(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    String po16 = SysParaInitQuery.getParaString(pk_org, POParas.PO16_V.name());
    return PUSysParamUtil.getPriceParam(po16);

  }

  /**
   * 暂估单价来源。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PO27VO getPO27(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    String value =
        SysParaInitQuery.getParaString(pk_org, POParas.PO27_V.name());
    String pattern = "(N|Y),[\\w,]+";// 这里只进行粗略的检查，以备二次开发扩展
    if (StringUtils.isBlank(value) || !value.matches(pattern)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0094")/*
                                                                   * @res
                                                                   * "暂估单价来源（PO27）参数定义错误，请重新定义！"
                                                                   */);
    }
    UFBoolean isplan = UFBoolean.valueOf(value.substring(0, 1));
    PriceSource[] ps = PUSysParamUtil.getPrice(value.substring(2));
    return new PO27VO(isplan, ps);
  }

  /**
   * 采购价格优先策略。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PricePriority getPO28(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);

    String po28 = SysParaInitQuery.getParaString(pk_org, POParas.PO28.name());
    if (StringUtil.isEmptyWithTrim(po28)) {
      return null;
    }
    return PricePriority.valueOf(po28);

  }

  /**
   * 采购发票读取含税优先参数的专用服务 默认为含税优先
   * 
   * @param pk_org
   * @return true 含税优先；false 无税优先
   */
  public static boolean getPO28For25(String pk_org) {
    if (StringUtils.isBlank(pk_org)) {
      return true;
    }
    PricePriority prior = PUSysParamUtil.getPO28(pk_org);
    if (null == prior) {
      return true;
    }
    return PricePriority.TAXPRICE_PRIOR_TO_PRICE == prior;

  }

  /**
   * 请购单价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PriceSource getPO29(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PriceSource.class, pk_org,
        POParas.PO29.name(), PriceSource.PlanPrice);
  }

  /**
   * 优质优价方案和标准确定规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po38 getPO38(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po38.class, pk_org,
        POParas.PO38.name(), PUParaValue.po38.latest_schm_std);
  }

  /**
   * 到货入库数量容差控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO40(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO40.name(), PUParaValue.ctrltype.not_save);
  }

  /**
   * 请购单审批后是否发消息给专管采购员。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFBoolean getPO43(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return SysParaInitQuery.getParaBoolean(pk_org, POParas.PO43.name());
  }

  /**
   * 批次规则应用单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po44 getPO44(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po44.class, pk_org,
        POParas.PO44.name(), PUParaValue.po44.no_bill);
  }

  /**
   * 订单生成发票的自动结算配置
   * 
   * @param pk_org
   * @return
   */
  public static PUParaValue.po46 getPO46(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po46.class, pk_org,
        POParas.PO46.name(), PUParaValue.po46.not_settle);
  }

  /**
   * 请购订单数量容差控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.ctrltype getPO47(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.ctrltype.class, pk_org,
        POParas.PO47.name(), PUParaValue.ctrltype.not_control);
  }

  /**
   * 是否暂估应付——原“入库单暂估时是否暂估应付”改名。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFBoolean getPO52(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return SysParaInitQuery.getParaBoolean(pk_org, POParas.PO52.name());
  }

  /**
   * 集采集结的采购入库单签字是否生成调拨订单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFBoolean getPO53(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return SysParaInitQuery.getParaBoolean(pk_org, POParas.PO53.name());
  }

  /**
   * 消耗汇总传存货核算单据入库类型
   * 
   * @param pk_org 财务组织id
   * @return 采购入库交易类型id
   */
  public static String getPO54(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return SysParaInitQuery.getParaString(pk_org, POParas.PO54.name());
  }

  /**
   * 结算时发票合理损耗是否进入成本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static UFBoolean getPO75(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return SysParaInitQuery.getParaBoolean(pk_org, POParas.PO75.name());
  }

  /**
   * 物资需求申请单价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PriceSource getPO78(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0092")/* @res "库存组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PriceSource.class, pk_org,
        POParas.PO78.name(), PriceSource.RefCostPrice);
  }

  /**
   * 采购发票价格来源。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PriceSource[] getPO83(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    String po83 = SysParaInitQuery.getParaString(pk_org, POParas.PO83_V.name());
    return PUSysParamUtil.getPrice(po83);

  }

  /**
   * 调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 采购组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po84 getPO84(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0093")/* @res "采购组织为空" */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po84.class, pk_org,
        POParas.PO84.name(), PUParaValue.po84.adjust_price);
  }

  /**
   * 采购基础设置物料分类方式。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group 集团
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static PUParaValue.po85 getPO85(String pk_group) {
    AssertUtils.assertValue(pk_group != null, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004000_0", "04004000-0091")/*
                                                                 * @res
                                                                 * "集团为空"
                                                                 */);
    return PUSysParamUtil.getPOEnumPara(PUParaValue.po85.class, pk_group,
        POParas.PO85.name(), PUParaValue.po85.base_marclass);
  }

  /**
   * 自动结算规则默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static String getPO86(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    return SysParaInitQuery.getParaString(pk_org, POParas.PO86.name());
  }

  /**
   * 方法功能描述：自动结算规则默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return 自动结算规则数组（三个元素分别是：自动结算规则-红蓝入库单；自动结算规则-红蓝发票；自动结算规则-入库单发票）
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-1 下午01:12:22
   */
  public static SuperVO[] getPO86_v(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    SuperVO[] vos = null;
    try {
      SysInitVO initVO =
          SysParaInitQuery.queryByParaCode(pk_org, POParas.PO86_V.name());
      if (null == initVO || initVO.getValue() == null) {
        vos = new SuperVO[3];
        // 红蓝入库单默认值
        RBStockOptionableVO rbstockVO = new RBStockOptionableVO();
        rbstockVO.setBbatchcodesame(UFBoolean.FALSE);
        rbstockVO.setBbuyersame(UFBoolean.FALSE);
        rbstockVO.setBdeptsame(UFBoolean.TRUE);
        rbstockVO.setBfinanceorgsame(UFBoolean.TRUE);
        rbstockVO.setBfreeitemsame(UFBoolean.FALSE);
        rbstockVO.setBmaterialsame(UFBoolean.TRUE);
        rbstockVO.setBnumabssame(UFBoolean.FALSE);
        rbstockVO.setBordersame(UFBoolean.FALSE);
        rbstockVO.setBorigpricesame(UFBoolean.FALSE);
        rbstockVO.setBproductorsame(UFBoolean.FALSE);
        rbstockVO.setBprojectsame(UFBoolean.FALSE);
        rbstockVO.setBsuppliersame(UFBoolean.TRUE);
        rbstockVO.setBtrantypesame(UFBoolean.FALSE);
        vos[0] = rbstockVO;
        // 红蓝发票默认值
        RBInvoiceOptionableVO rbinvoiceVO = new RBInvoiceOptionableVO();
        rbinvoiceVO.setBbatchcodesame(UFBoolean.FALSE);
        rbinvoiceVO.setBbuyersame(UFBoolean.FALSE);
        rbinvoiceVO.setBdeptsame(UFBoolean.TRUE);
        rbinvoiceVO.setBfinanceorgsame(UFBoolean.TRUE);
        rbinvoiceVO.setBfreeitemsame(UFBoolean.FALSE);
        rbinvoiceVO.setBinvoicetypesame(UFBoolean.TRUE);
        rbinvoiceVO.setBmaterialsame(UFBoolean.TRUE);
        rbinvoiceVO.setBnorigpricesame(UFBoolean.TRUE);
        rbinvoiceVO.setBnumabssame(UFBoolean.FALSE);
        rbinvoiceVO.setBordersame(UFBoolean.FALSE);
        rbinvoiceVO.setBproductorsame(UFBoolean.FALSE);
        rbinvoiceVO.setBprojectsame(UFBoolean.FALSE);
        rbinvoiceVO.setBsuppliersame(UFBoolean.TRUE);
        vos[1] = rbinvoiceVO;
        // 入库单发票默认值
        InvoiceStockOptionableVO invoicestockVO =
            new InvoiceStockOptionableVO();
        invoicestockVO.setBbatchcodesame(UFBoolean.FALSE);
        invoicestockVO.setBbuyersame(UFBoolean.FALSE);
        invoicestockVO.setBdeptsame(UFBoolean.TRUE);
        invoicestockVO.setBfinanceorgsame(UFBoolean.TRUE);
        invoicestockVO.setBfreeitemsame(UFBoolean.FALSE);
        invoicestockVO.setBmaterialsame(UFBoolean.TRUE);
        invoicestockVO.setBnumsame(UFBoolean.FALSE);
        invoicestockVO.setBorigpricesame(UFBoolean.FALSE);
        invoicestockVO.setBproductorsame(UFBoolean.FALSE);
        invoicestockVO.setBprojectsame(UFBoolean.FALSE);
        invoicestockVO.setBsuppliersame(UFBoolean.TRUE);
        vos[2] = invoicestockVO;
      }
      else {
        String value = initVO.getValue();
        // 如果有数据，查询数据库，设值
        String[] pks = value.split(",");
        IAutoSettleRule itf =
            NCLocator.getInstance().lookup(IAutoSettleRule.class);
        vos = itf.queryAutoSettleRule(pks);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return vos;
  }

  /**
   * 自动结算生成采购结算单是否自动传存货核算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 财务组织
   * @return @
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-9 上午11:38:46
   */
  public static boolean getPO87(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    boolean btoia = true;
    UFBoolean value =
        SysParaInitQuery.getParaBoolean(pk_org, POParas.PO87.name());
    if (UFBoolean.FALSE.equals(value)) {
      btoia = false;
    }
    return btoia;
  }

  /**
   * 采购订单和合同付款是否需做付款申请
   * 
   * @param pk_org 财务组织
   * @return
   */
  public static boolean getPO88(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    boolean bpayapp = false;
    UFBoolean value =
        SysParaInitQuery.getParaBoolean(pk_org, POParas.PO88.name());
    if (UFBoolean.TRUE.equals(value)) {
      bpayapp = true;
    }
    return bpayapp;
  }

  public static SysInitVO getSysInitVOPO86(String pk_org) {
    AssertUtils
        .assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0090")/* @res "财务组织为空" */);
    SysInitVO initVO = null;
    initVO = SysParaInitQuery.queryByParaCode(pk_org, POParas.PO86_V.name());
    if (initVO == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0095")/*
                                                                   * @res
                                                                   * "参数PO86为空！"
                                                                   */);
    }
    return initVO;
  }

  private static <T extends Enum<T>> T getPOEnumPara(Class<T> enumType,
      String pk_org, String paraName, T defaultEnum) {
    String value = SysParaInitQuery.getParaString(pk_org, paraName);
    if (StringUtil.isEmptyWithTrim(value)) {
      ExceptionUtils.wrappBusinessException(paraName
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0096")/* @res "参数未设置" */);
    }
    return PUSysParamUtil.valueOf(enumType, value, defaultEnum);
  }

  /**
   * 根据价格来源参数值取得采购价格来源的枚举。
   * <p>
   * <b>参数说明</b>
   * 
   * @param param 根据价格来源参数值
   * @return <p>
   *         采购价格来源的枚举
   * @since 6.0
   * @author GGR
   * @time 2010-4-12 上午09:56:10
   */
  private static PriceSource[] getPrice(String param) {
    if (StringUtil.isEmptyWithTrim(param)) {
      return null;
    }

    String[] sparams = param.split(",");
    PriceSource[] ret = new PriceSource[sparams.length];
    for (int i = 0, len = sparams.length; i < len; i++) {
      ret[i] = PriceSource.valueOf(sparams[i]);
    }
    return ret;
  }

  /**
   * 根据参数值取得采购订单自动询价条件的枚举。
   * <p>
   * <b>参数说明</b>
   * 
   * @param param 根据采购订单自动询价条件的参数值
   * @return <p>
   *         采购订单自动询价条件来源的枚举
   * @since 6.5
   * @author mengjian
   * @param param
   * @return
   */
  private static PriceParam[] getPriceParam(String param) {
    if (StringUtil.isEmptyWithTrim(param)) {
      return null;
    }

    String[] sparams = param.split(",");
    PriceParam[] ret = new PriceParam[sparams.length];
    for (int i = 0, len = sparams.length; i < len; i++) {
      ret[i] = PriceParam.valueOf(sparams[i]);
    }
    return ret;
  }

  private static <T extends Enum<T>> T valueOf(Class<T> enumType, String name,
      T defaultEnum) {
    try {
      return Enum.valueOf(enumType, name);
    }
    catch (IllegalArgumentException e) {
      return defaultEnum;
    }
  }

}
