package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.BillHelper;

/**
 * @since 6.0
 * @version 2011-6-17 обнГ02:14:48
 * @author wuxla
 */

public class CoopCurrencyValue implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate();
    }
  }

}
