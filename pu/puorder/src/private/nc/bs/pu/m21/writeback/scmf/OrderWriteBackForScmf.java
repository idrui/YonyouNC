package nc.bs.pu.m21.writeback.scmf;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class OrderWriteBackForScmf {
  public void udpateItemCenPuRuleB(String[] cenpurulebs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update po_order_b_ec");
    sql.append(" set ");
    sql.append(OrderItemVO.PK_CENPURULE_B + "=null");
    sql.append(" where ");
    // ¡Ÿ ±±Ì
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_34.name());
    sql.append(builder.buildSQL(OrderItemVO.PK_CENPURULE_B, cenpurulebs));

    new DataAccessUtils().update(sql.toString());
  }
}
