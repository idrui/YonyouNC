/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-20 下午03:28:04
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询并设置汇率，随日期变化而变化
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-20 下午04:34:53
 */
public class CurrencyAndExchangeRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    this.setCurrencyAndExchangeRate(vos);
  }

  /**
   * 方法功能描述：设置汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs 订单vo数组
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-20 下午04:40:29
   */
  private void setCurrencyAndExchangeRate(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO) {
        continue;
      }
      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      int[] rows = new int[keyValue.getItemCount()];
      for (int i = 0; i < rows.length; ++i) {
        rows[i] = i;
      }
      CurrencyAndExchangerate currencyAndExchangerate =
          new CurrencyAndExchangerate(keyValue);
      currencyAndExchangerate.setCurrencyAndExchangeRate(rows);
    }
  }
}
