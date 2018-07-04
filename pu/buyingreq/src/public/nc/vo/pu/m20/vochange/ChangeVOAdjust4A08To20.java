package nc.vo.pu.m20.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.SetChangeRateRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetNnumRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pu.m20.rule.SetRownoRule;
import nc.vo.pu.m20.rule.SetUnitRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 资产配置申请到请购单的vo交换处理类
 * 
 * @since 6.5
 * @version 2014-1-20 上午09:02:50
 * @author fanly3
 */
public class ChangeVOAdjust4A08To20 implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

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
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    PraybillVO[] vos = ArrayUtil.convertArrayType(destVOs);
    // 补全信息
    this.fillInfo(vos);
    return vos;
  }

  /**
   * 补全信息
   * 
   * @param vos
   */
  private void fillInfo(PraybillVO[] vos) {
    // 补全行号
    new SetRownoRule().process(vos);
    // 补全本币币种
    new SetOrgCurrRule().process(vos);
    // 补全采购组织
    new SetPurchaseorgRule().process(vos);
    // 补全采购员
    new SetEmployeeRule().process(vos);
    // 补全订单类型
    new SetOrdertrantypeRule().process(vos);
    // 设置主单位，VO对照上没有对主单位，主单位取物料上的主单位
    new SetUnitRule().process(vos);
    // 设置换算率和是否固定换算率
    new SetChangeRateRule().process(vos);
    // 根据数量和换算率计算主数量
    new SetNnumRule().process(vos);
    // 补全需求日期和建议订货日期
    new CalculateDateRule().process(vos);
    // 补全含税单价和价税合计
    new SetPriceRule().process(vos);
  }
}
