package nc.vo.pu.m27.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m27.entity.SettleBillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 结算单保存精度检查
 * @scene
 * 结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:20:45
 * @author zhangshqb
 */
public class SettleBillScaleCheckRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 精度校验处理
    BillVOScaleCheckProcessor checker =
        new BillVOScaleCheckProcessor(vos[0].getParentVO().getPk_group(), vos);
    new SettleBillScaleRule().setBodyScale(checker);

  }

}
