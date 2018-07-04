/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 ����11:18:15
 */
package nc.bs.pu.m21.query.ic;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.ic.rule.FilterByOnWayStatusRule;
import nc.bs.pu.m21.query.ic.rule.StorePrivilegeChkRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�ɹ���ⵥ�ṩ��������ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 ����11:18:15
 */
public class OrderQueryFor45PullBP {

  /**
   * ��������������Ϊ��ⵥ���������ṩ��ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 ����04:14:32
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    // ����ID��ѯ����VO
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(cond, isLazy);
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL45);
    this.addRule(processer);
    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // ����;״̬����
    processer.addAfterRule(new FilterByOnWayStatusRule());
    // ������������
    // processer.addAfterRule(new CanStockNumCalcRule());
    // �����ԱȨ�޹���
    processer.addAfterRule(new StorePrivilegeChkRule());
  }
}
