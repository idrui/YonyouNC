/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-3 ����09:28:59
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݺϷ���У�飬ֻ���ڷ������˼�飬��ΪScaleUtilsʹ�õ��Ƿ�������
 * <li>У�鵥�ۣ�����
 * <li>У�鵥�ۡ�����������ϵ
 * <li>У���������������������ʹ�ϵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-3 ����09:28:59
 */
public class DataValidateRule implements IRule<OrderVO> {
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

    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }
      OrderItemVO[] itemVOs = vo.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      this.checkItemValidate(itemVOs, vo);
    }
  }

  /**
   * �����������������ݺϷ��Լ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����10:33:43
   */
  private void checkItemValidate(OrderItemVO[] itemVOs, OrderVO vo) {
    PrecisionValidate precisionValidate = new PrecisionValidate();
    RelationValidate relationValidate = new RelationValidate(vo);
    for (OrderItemVO itemVO : itemVOs) {
      precisionValidate.precisionValidate(itemVO);
      relationValidate.relationValidate(itemVO);
    }
  }
}
