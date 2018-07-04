package nc.bs.pu.m27.settlebill.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.lang.UFDateTime;

/**
 * 
 * @description
 * 审计信息的补充规则
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:12:18
 * @author zhangshqb
 */
public class AuditInfoRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    for (SettleBillVO vo : vos) {
      this.fillAuditInfo(vo);
    }
  }

  private void fillAuditInfo(SettleBillVO vo) {
    SettleBillHeaderVO header = vo.getParentVO();
    header.setCreator(InvocationInfoProxy.getInstance().getUserId());
    header.setBillmaker(InvocationInfoProxy.getInstance().getUserId());
    header.setCreationtime(new UFDateTime(System.currentTimeMillis()));
  }

}
