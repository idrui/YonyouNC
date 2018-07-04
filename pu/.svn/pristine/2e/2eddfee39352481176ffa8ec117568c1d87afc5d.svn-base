/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-12 下午01:49:39
 */
package nc.pubimpl.pu.position;

import nc.pubitf.pu.position.IQueryPositionForMM;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2011-12-29 下午01:49:39
 */
public class QueryPositionForMMImpl implements IQueryPositionForMM {

  @Override
  public String getMaterialSqlByCemployee(String cemployeeid) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_srcmaterial ");
    sql.append("    from po_position a inner join po_position_b b on a.pk_position=b.pk_position ");
    sql.append("    Where a.cemployeeid='" + cemployeeid + "' ");
    sql.append("    and b.fflag=1 and b.dr=0");
    sql.append("    Union all");
    sql.append("    select b.pk_source oid");
    sql.append("    from po_position_t t inner join bd_material_v b on t.pk_marclass = b.pk_marbasclass ");
    sql.append("    Where t.dr =0 and b.dr=0  and t.cemployeeid='"
        + cemployeeid + "' ");
    sql.append("      and not exists (select b.pk_source ");
    sql.append("      from po_position a inner join po_position_b c on a.pk_position=c.pk_position  ");
    sql.append("      where  a.cemployeeid='" + cemployeeid + "' ");
    sql.append("        and c.fflag=2 and c.dr=0  and a.dr=0 ");
    sql.append("        and c.pk_srcmaterial = b.pk_source ");
    sql.append("    ) ");
    return sql.toString();
  }

}
