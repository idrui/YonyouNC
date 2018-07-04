package nc.vo.pu.pub.rule.upgrade;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

import org.apache.commons.lang.ArrayUtils;

/**
 * VAT金额税率升级
 * 只适用于单子表，该方法必须放在TaxtypeFlagUpgradeRule之后
 * 计税金额 按购销类型=国内采购的方式升级。
 * 不可抵扣税率
 * 不可抵扣税额
 * 计成本金额
 * 采购订单 采购发票 期初暂估单
 * 暂估处理--货物及费用暂估
 * 消耗汇总暂估--货物及费用暂估
 * 不可抵扣税率、不可抵扣税额：均升级为0。
 * 计成本金额：升级为本币无税金额。
 * 
 * @since 6.0
 * @version 2012-3-3 上午10:45:54
 * @author wuxla
 */
public class VATValueUpgrade<E extends IBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    for (E vo : vos) {
      IBillMeta billMeta = vo.getMetaData();
      IVOMeta childMeta = billMeta.getChildren()[0];
      ISuperVO[] childvos = vo.getChildren(childMeta);
      if (ArrayUtils.isEmpty(childvos)) {
        continue;
      }
      for (ISuperVO childvo : childvos) {
        if (childvo == null) {
          continue;
        }
        this.setNnosubtaxrate(childvo);
        this.setNnosubtax(childvo);
        this.setNcalcostmny(childvo);
        this.setNcaltaxmny(childvo);
      }
    }
  }

  /**
   * 计成本金额:升级为本币无税金额。
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  private void setNcalcostmny(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.ncalcostmny.name(),
        childvo.getAttributeValue(PuAttrNameEnum.nmny.name()));
  }

  /**
   * 计税金额:按购销类型=国内采购的方式升级。
   * 默认值规则：
   * 如果扣税类别=应税内含：从本币价税合计携带默认值；
   * 如果扣税类别=应税外加：从本币无税金额携带默认值。
   * （以上由公共算法解决）
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param childvo
   */
  private void setNcaltaxmny(ISuperVO childvo) {
    Integer ftaxtypeflag =
        (Integer) childvo.getAttributeValue(PuAttrNameEnum.ftaxtypeflag.name());
    if (null == ftaxtypeflag) {
      return;
    }
    if (ftaxtypeflag.intValue() == EnumDiscounttaxtype.TAXOUT.toInt()) {
      childvo.setAttributeValue(PuAttrNameEnum.ncaltaxmny.name(),
          childvo.getAttributeValue(PuAttrNameEnum.nmny.name()));
      return;
    }
    childvo.setAttributeValue(PuAttrNameEnum.ncaltaxmny.name(),
        childvo.getAttributeValue(PuAttrNameEnum.ntaxmny.name()));
  }

  /**
   * <p>
   * 使用场景：不可抵扣税额
   * <ul>
   * <li>
   * </ul>
   * 
   * @param childvo
   */
  private void setNnosubtax(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.nnosubtax.name(),
        UFDouble.ZERO_DBL);
  }

  /**
   * 不可抵扣税率
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  private void setNnosubtaxrate(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.nnosubtaxrate.name(),
        UFDouble.ZERO_DBL);
  }

}
