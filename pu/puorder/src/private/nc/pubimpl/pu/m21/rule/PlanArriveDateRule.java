/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-22 ����02:49:28
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���üƻ���������Ϊ��ֹ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-22 ����02:49:28
 */
public class PlanArriveDateRule implements IRule<OrderVO> {

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

    this.setPlanArriveDate(vos);
  }

  /**
   * �����������������üƻ���������
   * �������ϼƻ�ҳǩ�ϵĲɹ���ǰ�����üƻ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 ����03:10:16
   */
  private void setPlanArriveDate(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO) {
        continue;
      }
      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      PlanArriveDate planArriveDate = new PlanArriveDate(keyValue);
      planArriveDate.setPlanArriveDate(0, keyValue.getItemCount() - 1);
    }
  }
}
