/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����06:05:43
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݱ��������������ñ�ͷ�˻���־�������������Ϊ�������ñ�ͷ�˻���־ΪY
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����06:05:43
 */
public class HeadBreturnRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      UFDouble nnum = vo.getBVO()[0].getNnum();
      if (MathTool.compareTo(nnum, UFDouble.ZERO_DBL) < 0) {
        vo.getHVO().setBreturn(UFBoolean.TRUE);
      }
    }
  }

}
