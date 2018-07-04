/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午08:13:29
 */
package nc.bs.pu.m21.query.mm;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成制造查询采购订单供给量BP类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 下午08:13:29
 */
public class OrderQueryExecForMMBP {

  /**
   * 方法功能描述：查询采购订单信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkArrvstoorg
   * @param pkMaterial
   * @param dplanarrvdate
   *          [0]开始日期 [1]结束日期
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-7 上午09:43:02
   */
  public OrderViewVO[] queryExecForM52(String pkArrvstoorg,
      String[] pkMaterial, UFDate[] dplanarrvdate) {
    SqlBuilder sql = new SqlBuilder();

    sql.append("select pk_order_b from po_order_b  where ");
    sql.append(" bstockclose = 'N' ");
    sql.append(" and ");
    sql.append("pk_arrvstoorg", pkArrvstoorg);
    sql.append(" and ");

    // 因为生产制造那边肯定不会传空值，所以不用判断
    sql.append("dplanarrvdate >= '" + dplanarrvdate[0] + "'");
    sql.append(" and ");
    sql.append("dplanarrvdate <= '" + dplanarrvdate[1] + "'");

    sql.append(" and ");
    // 激活
    sql.append(OrderItemVO.FISACTIVE, (Integer) EnumActive.ACTIVE.value());
    sql.append(" and dr = 0");

    if (!ArrayUtils.isEmpty(pkMaterial)) {
      sql.append(" and ");
      // 临时表
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_31.name());
      sql.append(builder.buildSQL("pk_material", pkMaterial));
    }

    DataAccessUtils utils = new DataAccessUtils();
    // 执行sql，查询表头id
    IRowSet rowset = utils.query(sql.toString());
    ViewQuery<OrderViewVO> query =
        new ViewQuery<OrderViewVO>(OrderViewVO.class);
    return query.query(rowset.toOneDimensionStringArray());
  }
}
