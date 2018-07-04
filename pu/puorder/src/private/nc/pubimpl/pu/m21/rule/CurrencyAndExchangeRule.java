/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-20 ����03:28:04
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�����û��ʣ������ڱ仯���仯
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-20 ����04:34:53
 */
public class CurrencyAndExchangeRule implements IRule<OrderVO> {

  /**
   * ���෽����д
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
   * �����������������û���
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs ����vo����
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-20 ����04:40:29
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
