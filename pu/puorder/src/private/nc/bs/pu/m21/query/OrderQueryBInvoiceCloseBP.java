/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 ����07:32:34
 */
package nc.bs.pu.m21.query;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݶ�������������ѯ��Ʊ�ر�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-3 ����07:32:34
 */
public class OrderQueryBInvoiceCloseBP {

  /**
   * �����������������ݶ�������������ѯ��Ʊ�ر�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-3 ����07:34:09
   */
  public Map<String, UFBoolean> getBInvoiceCloseMap(String[] bids) {
    if (ArrayUtils.isEmpty(bids)) {
      return new HashMap<String, UFBoolean>();
    }

    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.BINVOICECLOSE
        });

    OrderItemVO[] itemVOs = query.query(bids);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return new HashMap<String, UFBoolean>();
    }

    Map<String, UFBoolean> retMap = new HashMap<String, UFBoolean>();
    for (OrderItemVO itemVO : itemVOs) {
      UFBoolean binvoiceclose = itemVO.getBinvoiceclose();
      if (null == binvoiceclose) {
        retMap.put(itemVO.getPk_order_b(), UFBoolean.FALSE);
      }
      else {
        retMap.put(itemVO.getPk_order_b(), binvoiceclose);
      }
    }

    return retMap;
  }
}
