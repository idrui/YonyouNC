/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午07:56:24
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询符合条件的到货计划， 默认按计划到货日期、库存组织、仓库排序
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-22 下午07:56:24
 */
public class QueryMapListBidAsKey {

  public MapList<String, OrderReceivePlanVO> queryMapListBidAsKey(
      String[] pkOrderBs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    // 临时表
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
