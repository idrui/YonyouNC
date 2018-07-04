/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-22 ����03:37:26
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������϶�Ӧ�����������ã��������νӿڹ�ϵ����������ƥ���Ӧ�Ķ���������ΪĬ��ֵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-22 ����03:37:26
 */
public class TrantypeRule implements IRule<OrderVO> {

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

    // this.setTrantype(vos);
    // �������̾Ϳ�����
    PfServiceScmUtil.setBusiType(vos, POBillType.Order.getCode());
  }

  /**
   * ���������������������϶�Ӧ�����������ã��������νӿڹ�ϵ����������ƥ���Ӧ�Ķ���������ΪĬ��ֵ������ֱ�����۲ɹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 ����04:37:18
   */
  // private void setTrantype(OrderVO[] orderVOs) {
  // TrantypeValueRule trantypeValueRule = new TrantypeValueRule(orderVOs);
  // trantypeValueRule.setTrantypeValue();
  // }
}
