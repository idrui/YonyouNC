package nc.vo.pu.m20.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.CalculateNumRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pu.m20.rule.margin.PrayBillMarginRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 库存计划计划订单到请购单的vo交换处理类
 * 
 * @since 6.0
 * @version 2011-12-6 下午01:50:38
 * @author 田锋涛
 */

public class ChangeVOAdjustor4fTo20 implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    PraybillVO[] vos = ArrayUtil.convertArrayType(destVOs);
    // 补全信息
    this.fillInofo(vos);
    // 进行尾差倒挤。
    new PrayBillMarginRule(PfUtilBaseTools.getRealBilltype(adjustContext
        .getSrcBilltype()), srcVOs).process(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  /**
   * 补全信息
   * 
   * @param vos
   */
  private void fillInofo(PraybillVO[] vos) {
    // 补全表体采购组织
    new SetPurchaseorgRule().process(vos);
    // 补全本位币
    new SetOrgCurrRule().process(vos);
    // 单位数量计算
    new CalculateNumRule().process(vos);
    // 处理订单类型
    new SetOrdertrantypeRule().process(vos);
    // 单价
    // new SetPriceRule().process(vos);
  }

}
