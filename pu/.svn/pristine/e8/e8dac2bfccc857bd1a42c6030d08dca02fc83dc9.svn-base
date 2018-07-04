package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              请购单和采购订单检查表头的vtrantypecode和ctrantypeid不为空
 * @scene
 *        请购单和采购订单和到货单新增、保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:42:45
 * @author yanxm5
 */
public class TrantypeNotNullCheckRule<E extends AbstractBill> implements
    IRule<E> {

  public static final String CTRANTYPEID = "ctrantypeid";

  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 检查
   * 
   * @param vos
   */
  public void checkTranType(E[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (AggregatedValueObject vo : vos) {
      CircularlyAccessibleValueObject head = vo.getParentVO();
      if (StringUtils.isBlank((String) head
          .getAttributeValue(TrantypeNotNullCheckRule.VTRANTYPECODE))
          || StringUtils.isBlank((String) head
              .getAttributeValue(TrantypeNotNullCheckRule.CTRANTYPEID))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0089")/*
                                                                     * @res
                                                                     * "交易类型编码、交易类型不能为空"
                                                                     */);
      }
    }
  }

  @Override
  public void process(E[] vos) {
    this.checkTranType(vos);
  }
}
