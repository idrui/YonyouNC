/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-4 下午04:20:33
 */
package nc.pubimpl.pu.m21.ia.mif;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.ia.mif.IOrderQueryForIf;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为存货核算提供的查询接口实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-4 下午04:20:33
 */
public class OrderQueryForIfImpl implements IOrderQueryForIf {

  @Override
  public Map<String, UFDouble> getNewPriceForIA(String pkFinanceorg,
      String[] pk_srcmaterials) {
    Map<String, UFDouble> newPriceMap = new HashMap<String, UFDouble>();

    try {
      String sql = this.createSql(pkFinanceorg, pk_srcmaterials);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      while (rowset.next()) {
        String pk_srcmaterial = rowset.getString(0);
        UFDouble norignetprice = rowset.getUFDouble(1);
        if (newPriceMap.containsKey(pk_srcmaterial) || null == norignetprice) {
          continue;
        }
        newPriceMap.put(pk_srcmaterial, norignetprice);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    return newPriceMap;
  }

  /**
   * 方法功能描述：内部查询语句
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkFinanceorg
   * @param pkMaterials
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 下午06:44:39
   */
  private String createInnerSql(String pkFinanceorg, String[] pkMaterials) {
    SqlBuilder sql = new SqlBuilder();
    IDExQueryBuilder ids =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_25.name());
    sql.append("select pk_srcmaterial, max(dplanarrvdate) as dplanarrvdate from po_order_b where ");
    sql.append(ids.buildSQL(OrderItemVO.PK_SRCMATERIAL, pkMaterials));
    sql.append(" and ");
    sql.append(OrderItemVO.PK_PSFINANCEORG, pkFinanceorg);
    sql.append(" and ");
    sql.append("dr = 0");
    sql.append(" and ");
    sql.append(OrderItemVO.FISACTIVE, "<>",
        (Integer) EnumActive.REVISEHISTORY.value());
    sql.append(" group by pk_srcmaterial ");
    return sql.toString();
  }

  /**
   * 方法功能描述：查询语句
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkFinanceorg
   * @param pkMaterials
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 下午08:14:49
   */
  private String createSql(String pkFinanceorg, String[] pkMaterials) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.pk_srcmaterial, b.nnetprice from ");
    sql.append("(" + this.createInnerSql(pkFinanceorg, pkMaterials) + ") t ");
    sql.append(" inner join po_order_b b on ");
    sql.append("b.pk_srcmaterial = t.pk_srcmaterial");
    sql.append(" and ");
    sql.append("b.dplanarrvdate = t.dplanarrvdate");
    sql.append(" where ");
    sql.append("b.pk_psfinanceorg", pkFinanceorg);
    sql.append(" and ");
    sql.append("dr = 0");
    sql.append(" and ");
    sql.append("b.fisactive", "<>", (Integer) EnumActive.REVISEHISTORY.value());
    sql.append(" order by b.pk_order_b desc");

    return sql.toString();
  }

}
