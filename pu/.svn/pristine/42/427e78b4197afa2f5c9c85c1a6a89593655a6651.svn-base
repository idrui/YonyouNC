package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 结算单单据号的生成规则
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:12:01
 * @author zhangshqb
 */
public class BillCodeRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    PUBillCodeUtils.getSettleBillCode().createBillCode(vos);
  }
}
