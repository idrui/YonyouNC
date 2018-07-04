/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 ����07:56:24
 */
package nc.bs.pu.m21.rp;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ���������ĵ����ƻ��� Ĭ�ϰ��ƻ��������ڡ������֯���ֿ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-22 ����07:56:24
 */
public class QueryMapListBidAsKey {

  public MapList<String, OrderReceivePlanVO> queryMapListBidAsKey(
      String[] pkOrderBs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_32.name());
    sql.append(builder.buildSQL(OrderReceivePlanVO.PK_ORDER_B, pkOrderBs));

    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
    OrderReceivePlanVO[] rpVOs =
        query.query(sql.toString(), " order by "
            + OrderReceivePlanVO.DPLANARRVDATE);

    if (ArrayUtils.isEmpty(rpVOs)) {
      return null;
    }

    MapList<String, OrderReceivePlanVO> mapList =
        new MapList<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      mapList.put(rpVO.getPk_order_b(), rpVO);
    }

    return mapList;
  }
}
