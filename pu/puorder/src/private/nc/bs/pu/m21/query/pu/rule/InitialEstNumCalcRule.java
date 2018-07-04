/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-14 ����12:10:56
 */
package nc.bs.pu.m21.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݹ���������������>0���ݹ�����=��������-�ۼ�������� 2����������<0���ݹ�����=��������+�ۼ��������
 * <li>ʹ�ÿ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-14 ����12:10:56
 */
public class InitialEstNumCalcRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (null == vos) {
      return;
    }
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccumstorenum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcaninnum(num);
      }
    }
  }

}
