/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 ����08:48:08
 */
package nc.bs.pu.m21.state.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.pu.m21.rp.CloseWhenItemClose;
import nc.bs.pu.m21.rp.QueryMapListBidAsKey;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ������йرջ�д�����ƻ�
 * @scene
 *        �ɹ������йر�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����10:51:57
 * @author luojw
 */
public class CloseUpdateReceivePlanRule implements IRule<OrderCloseVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderCloseVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    Set<String> setPkOrderBs = new HashSet<String>();
    for (OrderCloseVO vo : vos) {
      if (null != vo) {
        String pkOrderB = (String) vo.getAttributeValue(OrderItemVO.PK_ORDER_B);
        setPkOrderBs.add(pkOrderB);
      }
    }

    if (setPkOrderBs.isEmpty()) {
      return;
    }

    String[] pkOrderBs = setPkOrderBs.toArray(new String[0]);
    QueryMapListBidAsKey query = new QueryMapListBidAsKey();

    MapList<String, OrderReceivePlanVO> mapList =
        query.queryMapListBidAsKey(pkOrderBs);
    if (null == mapList) {
      return;
    }

    List<OrderReceivePlanVO> list = new ArrayList<OrderReceivePlanVO>();
    for (String key : mapList.keySet()) {
      list.addAll(mapList.get(key));
    }
    OrderReceivePlanVO[] rpVOs =
        new ListToArrayTool<OrderReceivePlanVO>().convertToArray(list);
    CloseWhenItemClose close = new CloseWhenItemClose();
    close.closeWhenItemClose(rpVOs);
  }
}
