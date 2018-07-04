package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 单据号回退规则
 * @scene
 * 删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:11:15
 * @author zhangshqb
 */
public class SettleBillCodeReturnRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    PUBillCodeUtils.getSettleBillCode().returnBillCode(vos);
  }
}
