package nc.bs.pu.m4202;

import nc.bs.pu.m27.AbstractStockFICancelDupBP;
import nc.bs.pu.m4202.rule.EstimatedCheckRule;
import nc.bs.pu.m4202.rule.SettledCheckRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * 删除消耗汇总的副本BP
 * 
 * @since 6.0
 * @version 2011-1-20 下午03:19:25
 * @author zhaoyha
 */
public class VMIFICancelDupBP extends AbstractStockFICancelDupBP<VmiFIVO> {

  public VMIFICancelDupBP() {
    super(VmiFIVO.class);
  }

  @Override
  protected void addRule(AroundProcesser<VmiFIVO> prcr) {
    prcr.addBeforeFinalRule(new EstimatedCheckRule());// 暂估检查
    prcr.addBeforeFinalRule(new SettledCheckRule());// 结算检查
  }

}
