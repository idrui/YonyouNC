package nc.bs.pu.m23.upgrade.v63.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.uap.busibean.ISysInitGetValue;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * v63税额字段的升级规则。
 * 
 * @since 6.3
 * @version 2013-1-10 上午10:10:40
 * @author lixyp
 */
public class NtaxUpgradeRule implements IRule<ArriveVO> {

  // 缓存所有到货单对应采购组织的价格优先策略参数。
  private Map<String, String> pricePriorityParams = null;

  @Override
  public void process(ArriveVO[] vos) {
    try {
      this.initPO28ParamCache(vos);

      String pricePriority = null;
      for (ArriveVO vo : vos) {
        ScaleUtils scaleUtils = new ScaleUtils(vo.getHVO().getPk_group());

        // 根据采购组织获取价格优先策略
        pricePriority =
            this.pricePriorityParams.get(vo.getHVO().getPk_purchaseorg());

        boolean taxpriceprior =
            PricePriority.TAXPRICE_PRIOR_TO_PRICE.toString().equals(
                pricePriority);
        for (ArriveItemVO itemVo : vo.getBVO()) {
          // 跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
          if (BuySellFlagEnum.IMPORT.value().equals(itemVo.getFbuysellflag())
              || BuySellFlagEnum.OUTPUT.value()
                  .equals(itemVo.getFbuysellflag())) {
            if (taxpriceprior) {
              // 跨国业务，含税优先
              this.procOverseasBusiForTaxPrice(itemVo, scaleUtils);
            }
            else {
              // 跨国业务，无税优先
              this.procOverseasBusiForPrice(itemVo, scaleUtils);
            }
          }
          // 国内业务
          else {
            if (taxpriceprior) {
              // 国内业务，含税优先
              this.processForTaxPrice(itemVo, scaleUtils);
            }
            else {
              // 国内业务，无税优先
              this.processForPrice(itemVo, scaleUtils);
            }
          }
        }

      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  /**
   * 根据到货单表头采购组织查询价格优先策略参数。
   * 
   * @param vos 到货单VO数组
   * @throws BusinessException 业务异常
   */
  private void initPO28ParamCache(ArriveVO[] vos) throws BusinessException {
    Set<String> purchaseOrgs = new HashSet<String>();
    for (ArriveVO vo : vos) {
      purchaseOrgs.add(vo.getHVO().getPk_purchaseorg());
    }
    ISysInitGetValue service =
        NCLocator.getInstance().lookup(ISysInitGetValue.class);
    this.pricePriorityParams =
        service.getBatchParaString(
            purchaseOrgs.toArray(new String[purchaseOrgs.size()]),
            POParas.PO28.name());
  }

  /**
   * 处理无税优先的到货单，这部分到货单无税金额和本币无税金额是正确的，需要重算价税合计和本币价税合计。
   * 
   * @param itemVo 到货单表体VO
   * @param scaleUtils 精度处理对象
   */
  private void processForPrice(ArriveItemVO itemVo, ScaleUtils scaleUtils) {
    // 税率
    UFDouble ntaxrate =
        CalculatorUtil.div(MathTool.nvl(itemVo.getNtaxrate()),
            new UFDouble(100));
    // 扣税类别为应税外加
    if (itemVo.getFtaxtypeflag().intValue() == EnumDiscounttaxtype.TAXOUT
        .toInt()) {
      // 税额 = 本币无税金额 * 税率
      UFDouble ntax = (itemVo.getNmny()).multiply(ntaxrate);
      itemVo.setNtax(scaleUtils.adjustMnyScale(ntax, itemVo.getCcurrencyid()));
      // 本币价税合计 = 本币无税金额 + 税额
      itemVo.setNtaxmny(scaleUtils.adjustMnyScale((itemVo.getNmny()).add(ntax),
          itemVo.getCcurrencyid()));
      // 折本汇率=1
      if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
        // 价税合计 = 本币价税合计
        itemVo.setNorigtaxmny(itemVo.getNtaxmny());
      }
      else {
        // 价税合计 = 无税金额 * （1 + 税率）
        itemVo.setNorigtaxmny(scaleUtils.adjustMnyScale(
            (itemVo.getNorigmny()).multiply((UFDouble.ONE_DBL).add(ntaxrate)),
            itemVo.getCorigcurrencyid()));
      }

    }
    // 扣税类别为应税内含
    else if (itemVo.getFtaxtypeflag().intValue() == EnumDiscounttaxtype.TAXIN
        .toInt()) {
      // 税额 = 本币无税金额 * 税率 / （1 - 税率）
      UFDouble ntax =
          ((itemVo.getNmny()).multiply(ntaxrate)).div((UFDouble.ONE_DBL).sub(ntaxrate));
      itemVo.setNtax(scaleUtils.adjustMnyScale(ntax, itemVo.getCcurrencyid()));
      // 本币价税合计 = 本币无税金额 + 税额
      itemVo.setNtaxmny(scaleUtils.adjustMnyScale((itemVo.getNmny()).add(ntax),
          itemVo.getCcurrencyid()));
      // 折本汇率=1
      if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
        // 价税合计 = 本币价税合计
        itemVo.setNorigtaxmny(itemVo.getNtaxmny());
      }
      else {
        // 价税合计 = 无税金额 / （1 - 税率）
        itemVo.setNorigtaxmny(scaleUtils.adjustMnyScale(
            (itemVo.getNorigmny()).div((UFDouble.ONE_DBL).sub(ntaxrate)),
            itemVo.getCorigcurrencyid()));
      }

    }
  }

  /**
   * 处理含税优先的到货单，这部分到货单价税合计和本币价税合计是正确的，需要重算无税金额和本币无税金额。
   * 
   * @param itemVo 到货单表体VO
   * @param scaleUtils 精度处理对象
   */
  private void processForTaxPrice(ArriveItemVO itemVo, ScaleUtils scaleUtils) {

    // 税率
    UFDouble ntaxrate =
        CalculatorUtil.div(MathTool.nvl(itemVo.getNtaxrate()),
            new UFDouble(100));
    // 扣税类别为应税外加
    if (itemVo.getFtaxtypeflag().intValue() == EnumDiscounttaxtype.TAXOUT
        .toInt()) {
      // 税额 = 本币价税合计 * 税率 / （1 + 税率）
      UFDouble ntax =
          ((itemVo.getNtaxmny()).multiply(ntaxrate)).div((UFDouble.ONE_DBL)
              .add(ntaxrate));

      itemVo.setNtax(scaleUtils.adjustMnyScale(ntax, itemVo.getCcurrencyid()));
      // 本币无税金额 = 本币价税合计 - 税额
      itemVo.setNmny(scaleUtils.adjustMnyScale((itemVo.getNtaxmny()).sub(ntax),
          itemVo.getCcurrencyid()));
      // 折本汇率=1
      if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
        // 无税金额 = 本币无税金额
        itemVo.setNorigmny(itemVo.getNmny());
      }
      else {
        // 无税金额 = 价税合计 / （1 + 税率）
        itemVo.setNorigmny(scaleUtils.adjustMnyScale(
            (itemVo.getNorigtaxmny()).div((UFDouble.ONE_DBL).add(ntaxrate)),
            itemVo.getCorigcurrencyid()));
      }

    }
    // 扣税类别为应税内含
    else if (itemVo.getFtaxtypeflag().intValue() == EnumDiscounttaxtype.TAXIN
        .toInt()) {
      // 税额 = 本币价税合计 * 税率
      UFDouble ntax = (itemVo.getNtaxmny()).multiply(ntaxrate);
      itemVo.setNtax(scaleUtils.adjustMnyScale(ntax, itemVo.getCcurrencyid()));
      // 本币无税金额 = 本币价税合计 - 税额
      itemVo.setNmny(scaleUtils.adjustMnyScale((itemVo.getNtaxmny()).sub(ntax),
          itemVo.getCcurrencyid()));
      // 折本汇率=1
      if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
        // 无税金额 = 本币无税金额
        itemVo.setNorigmny(itemVo.getNmny());
      }
      else {
        // 无税金额= 价税合计 * （1 - 税率）
        itemVo.setNorigmny(scaleUtils.adjustMnyScale((itemVo.getNorigtaxmny())
            .multiply((UFDouble.ONE_DBL).sub(ntaxrate)), itemVo
            .getCorigcurrencyid()));
      }
    }
  }

  /**
   * 跨国业务，处理含税优先的到货单，这部分到货单价税合计和本币价税合计是正确的，需要重算无税金额和本币无税金额。
   * 
   * @param itemVo 到货单表体VO
   * @param scaleUtils 精度处理对象
   */
  private void procOverseasBusiForTaxPrice(ArriveItemVO itemVo,
      ScaleUtils scaleUtils) {
    // 税率
    UFDouble ntaxrate =
        CalculatorUtil.div(MathTool.nvl(itemVo.getNtaxrate()),
            new UFDouble(100));
    itemVo.setNorigmny(itemVo.getNorigtaxmny());
    // 税额 = 计税金额 * 税率,计税金额 = 本币价税合计。即税额 = 本币价税合计 * 税率
    itemVo.setNtax(scaleUtils.adjustMnyScale(
        (itemVo.getNtaxmny()).multiply(ntaxrate), itemVo.getCcurrencyid()));
    // 本币无税金额 = 本币价税合计
    itemVo.setNmny(itemVo.getNtaxmny());
    // 折本汇率=1
    if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
      // 无税金额 = 本币无税金额
      itemVo.setNorigmny(itemVo.getNmny());
    }
    else {
      // 无税金额 = 价税合计
      itemVo.setNorigmny(itemVo.getNorigtaxmny());
    }

  }

  /**
   * 跨国业务，处理无税优先的到货单，这部分到货单无税金额和本币无税金额是正确的，需要重算价税合计和本币价税合计。
   * 
   * @param itemVo 到货单表体VO
   * @param scaleUtils 精度处理对象
   */
  private void procOverseasBusiForPrice(ArriveItemVO itemVo,
      ScaleUtils scaleUtils) {
    // 税率
    UFDouble ntaxrate =
        CalculatorUtil.div(MathTool.nvl(itemVo.getNtaxrate()),
            new UFDouble(100));
    // 税额 = 计税金额 * 税率,计税金额 = 本币无税金额。即税额 = 本币无税金额 * 税率
    itemVo.setNtax(scaleUtils.adjustMnyScale(
        (itemVo.getNmny()).multiply(ntaxrate), itemVo.getCcurrencyid()));
    // 本币价税合计 = 本币无税金额
    itemVo.setNtaxmny(itemVo.getNmny());
    // 折本汇率=1
    if (MathTool.compareTo(itemVo.getNexchangerate(), UFDouble.ONE_DBL) == 0) {
      // 价税合计 = 本币价税合计
      itemVo.setNorigtaxmny(itemVo.getNtaxmny());
    }
    else {
      // 价税合计 = 无税金额
      itemVo.setNorigtaxmny(itemVo.getNorigmny());
    }

  }
}
