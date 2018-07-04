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
 * 扣税类别升级
 * 只适用于第一种场景，只处理单子表
 * （1）扣税类别 采购订单
 * 采购发票
 * 期初暂估单
 * 暂估处理--货物暂估
 * 消耗汇总暂估--货物暂估
 * 如果为应税外加、应税内含：仍保留原来的扣税类别。
 * 如果为不计税，升级为应税外加，同时税率升为0；
 * （2）暂估处理--费用暂估
 * 消耗汇总暂估--费用暂估 原来即按内含方式处理的，全部升级为“应税内含”。
 * 
 * @since 6.0
 * @version 2012-3-3 上午10:28:52
 * @author wuxla
 */
public class TaxtypeFlagUpgradeRule<E extends IBill> implements IRule<E> {
  private String ftaxtypeflagh;

  private String ntaxrateh;

  /**
   * 没有表头整单扣税类别和整单税率
   */
  public TaxtypeFlagUpgradeRule() {

  }

  /**
   * 包含整单扣税类别、整单税率
   * 
   * @param ftaxtypeflagh 整单扣税类别
   * @param ntaxrateh 整单税率
   */
  public TaxtypeFlagUpgradeRule(String ftaxtypeflagh, String ntaxrateh) {
    this.ftaxtypeflagh = ftaxtypeflagh;
    this.ntaxrateh = ntaxrateh;
  }

  @Override
  public void process(E[] vos) {
    // 表头整单
    this.headUpgrade(vos);
    // 表体
    this.bodyUpgrade(vos);
  }

  private void bodyUpgrade(E[] vos) {
    for (E vo : vos) {
      IBillMeta billMeta = vo.getMetaData();
      IVOMeta childMeta = billMeta.getChildren()[0];
      ISuperVO[] childvos = vo.getChildren(childMeta);
      if (ArrayUtils.isEmpty(childvos)) {
        continue;
      }
      for (ISuperVO childvo : childvos) {
        this.taxtypeFlagUpgrade(childvo, PuAttrNameEnum.ftaxtypeflag.name(),
            PuAttrNameEnum.ntaxrate.name());
      }
    }
  }

  private void headUpgrade(E[] vos) {
    if (null == this.ftaxtypeflagh) {
      return;
    }
    for (E vo : vos) {
      ISuperVO parent = vo.getParent();
      this.taxtypeFlagUpgrade(parent, this.ftaxtypeflagh, this.ntaxrateh);
    }
  }

  private void taxtypeFlagUpgrade(ISuperVO vo, String taxtype, String taxrate) {
    // 如果为应税外加、应税内含：仍保留原来的扣税类别。
    // 如果为不计税，升级为应税外加，同时税率升为0；
    if (vo == null) {
      return;
    }
    Integer ftaxtypeflag = (Integer) vo.getAttributeValue(taxtype);
    if (ftaxtypeflag != null && ftaxtypeflag.intValue() == 2) {
      vo.setAttributeValue(taxtype, EnumDiscounttaxtype.TAXOUT.toInteger());
      vo.setAttributeValue(taxrate, UFDouble.ZERO_DBL);
    }
  }
}
