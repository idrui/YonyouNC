package nc.bs.pu.m4203;

import nc.bs.pu.m27.AbstractStockFICancelDupBP;
import nc.bs.pu.m4203.rule.SettledCheckRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4203.entity.SubcontinFIVO;

/**
 * 删除委托加工入的副本BP
 * 
 * @since 6.0
 * @version 2011-1-20 下午03:38:08
 * @author zhaoyha
 */
public class SubcontinFICancelDupBP extends
    AbstractStockFICancelDupBP<SubcontinFIVO> {

  public SubcontinFICancelDupBP() {
    super(SubcontinFIVO.class);
  }

  @Override
  protected void addRule(AroundProcesser<SubcontinFIVO> prcr) {
    prcr.addBeforeFinalRule(new SettledCheckRule());// 检查是否已经进行过加工费或其它费用结算
  }

}
