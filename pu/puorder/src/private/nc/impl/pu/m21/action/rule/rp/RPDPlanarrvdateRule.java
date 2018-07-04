/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 ����03:12:09
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.rule.RPDPlanarrvdateCheck;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ��������ڲ������ڶ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 ����03:12:09
 */
public class RPDPlanarrvdateRule implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public RPDPlanarrvdateRule(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = OrderReceivePlanUtils.getAddAndUpVOs(vos[0]);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    RPDPlanarrvdateCheck check = new RPDPlanarrvdateCheck();
    check.checkDPlanarrvdate(rpVOs, this.orderVO);
  }

}
