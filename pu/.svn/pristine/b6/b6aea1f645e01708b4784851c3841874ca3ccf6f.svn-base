package nc.bs.pu.m27.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 库存入库生成采购副本的数据校验
 *
 * @since 6.0
 * @version 2011-6-20 上午11:22:15
 * @author 田锋涛
 */

public class StockFIDuplicateCheckRule<E extends AbstractBill> implements
    IRule<E> {

  public static final String BAFFECTCOST = "baffectcost";

  public static final String NINNUM = "ninnum";// 入库数量

  public static final String NMNY = "nmny";// 入库金额

  public static final String PK_COSTREGION = "pk_costregion";

  @Override
  public void process(E[] vos) {
    this.checkCostRegion(vos);
    this.checkBody(vos);
  }

  private void checkBody(E[] vos) {
    for (E vo : vos) {
      // 针对消耗汇总，没有表体
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        return;
      }
      // 其他判断表体
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        UFDouble nmy =
            ValueUtils.getUFDouble(item
                .getAttributeValue(StockFIDuplicateCheckRule.NMNY));
        UFDouble ninum =
            ValueUtils.getUFDouble(item
                .getAttributeValue(StockFIDuplicateCheckRule.NINNUM));
        // 主要是采购入的限制
        if (MathTool.isDiffSign(nmy, ninum)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0072")/*@res "库存单据数量与金额符号不一致，无法保持！"*/);
        }
      }
    }
  }

  /**
   * 成本域校验
   *
   * @param vos
   */
  private void checkCostRegion(E[] vos) {
    String errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0073")/*@res "成本域不能为空！"*/;
    for (E vo : vos) {
      // 消耗汇总在表头
      String pk_costRegion =
          ValueUtils.getString(vo.getParentVO().getAttributeValue(
              StockFIDuplicateCheckRule.PK_COSTREGION));
      UFBoolean effectIA =
          ValueUtils.getUFBoolean(vo.getParentVO().getAttributeValue(
              StockFIDuplicateCheckRule.BAFFECTCOST));
      if (effectIA.booleanValue() && StringUtils.isEmpty(pk_costRegion)) {
        ExceptionUtils.wrappBusinessException(errorMsg);
      }
      // 针对消耗汇总，没有表体
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        return;
      }
      // 其他判断表体
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        pk_costRegion =
            ValueUtils.getString(item
                .getAttributeValue(StockFIDuplicateCheckRule.PK_COSTREGION));
        effectIA =
            ValueUtils.getUFBoolean(item
                .getAttributeValue(StockFIDuplicateCheckRule.BAFFECTCOST));
        if (effectIA.booleanValue() && StringUtils.isEmpty(pk_costRegion)) {
          ExceptionUtils.wrappBusinessException(errorMsg);
        }
      }
    }
  }

}