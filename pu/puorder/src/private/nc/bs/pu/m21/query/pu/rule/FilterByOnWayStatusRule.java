/**
 * $�ļ�˵��$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-19 ����08:56:04
 */
package nc.bs.pu.m21.query.pu.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.AssertUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������;״̬Ϊ���˵�����VO,Ϊ��������ѯ�ṩ����
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-19 ����08:56:04
 */
public class FilterByOnWayStatusRule implements IFilterRule<OrderVO> {

  StatusOnWayItemVO[] statusVO = null;

  public FilterByOnWayStatusRule(StatusOnWayItemVO[] statusVO) {
    this.statusVO = statusVO;
  }

  @Override
  public OrderVO[] process(OrderVO[] vos) {

    Map<String, UFDouble> numMap = this.getNumMap();

    // ���ÿɵ�������
    for (OrderVO orderVO : vos) {
      OrderItemVO[] bvos = orderVO.getBVO();
      for (OrderItemVO bvo : bvos) {
        bvo.setNcanarrivenum(numMap.get(bvo.getPk_order_b()));
      }
    }

    return vos;
  }

  /**
   * ���������������������״̬����;����
   * <p>
   * <b>����˵��</b>
   *
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-15 ����04:19:34
   */
  private Map<String, UFDouble> getNumMap() {
    Map<String, UFDouble> numMap = new HashMap<String, UFDouble>();
    for (StatusOnWayItemVO vo : this.statusVO) {
      UFDouble onwayNum = vo.getNonwaynum();
      AssertUtils.assertValue(onwayNum != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0137")/*@res "������;������Ӧ��Ϊ��!"*/);
      numMap.put(vo.getPk_order_b(), onwayNum);
    }
    return numMap;
  }
}