package nc.vo.pu.m21.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 制造计划订单到采购订单vo交换处理类
 * 
 * @since 6.0
 * @version 2011-12-8 下午04:40:29
 * @author 田锋涛
 */

public class ChangeVOAdjust55B4To21 extends AbstractOrderChangeVOAdjust {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    new AutoSwitchPUAssistUnitRule().process((OrderVO[]) destVOs);
    return super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    return vos;// 计划订单不做处理，这个时候有可能组织都没有，是寻源补充的
  }

  @Override
  protected void fillOrgSuppValue(BillHelper<OrderVO> helper, int[] rows) {
    return;// 计划订单不做处理，这个时候有可能组织都没有，是寻源补充的
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // 放到推式保存接口补全
    return;
  }

}
