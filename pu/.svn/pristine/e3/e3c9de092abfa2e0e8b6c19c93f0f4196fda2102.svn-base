package nc.vo.pu.m21.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 库存计划计划订单到采购订单vo交换处理类
 * 
 * @since 6.0
 * @version 2011-12-8 下午04:40:29
 * @author 田锋涛
 */

public class ChangeVOAdjust4fTo21 extends AbstractOrderChangeVOAdjust {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    new AutoSwitchPUAssistUnitRule().process((OrderVO[]) destVOs);
    return destVOs;
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // 放到推式保存接口补全
    return;
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }

}
