package nc.bs.pu.m21.maintain.rule.delete;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单如果有修订历史，删除修订历史
 *              订单审批，然后修订，此时会有修订历史，然后弃审删除，此时需要删除历史记录
 * @scene
 *        采购订单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:45:57
 * @author luojw
 */

public class DelHistoryVORule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (null != originVOs) {
      this.deleteHistory(originVOs);
    }
  }

  private void deleteHistory(OrderVO[] vos) {
    List<String> codeList = new ArrayList<String>();
    List<String> pkList = new ArrayList<String>();
    for (OrderVO vo : vos) {
      Integer version = vo.getHVO().getNversion();
      if (version != null && version.intValue() > 1) {
        codeList.add(vo.getHVO().getVbillcode());
        for (OrderItemVO itemVO : vo.getBVO()) {
          pkList.add(itemVO.getPk_order_b());
        }
      }
    }

    if (0 == codeList.size()) {
      return;
    }

    DataAccessUtils util = new DataAccessUtils();
    String[] values =
        util.query(this.getQuerySql(codeList, pkList))
            .toOneDimensionStringArray();

    if (ArrayUtils.isEmpty(values)) {
      return;
    }

    BillQuery<OrderVO> query = new BillQuery<OrderVO>(OrderVO.class);
    OrderVO[] delVOs = query.query(values);
    BillDelete<OrderVO> delete = new BillDelete<OrderVO>();
    delete.delete(delVOs);
  }

  private String getCodeCond(List<String> codeList) {
    SqlBuilder sql = new SqlBuilder();
    if (codeList.size() == 1) {
      sql.append("  h." + OrderHeaderVO.VBILLCODE, codeList.get(0));
    }
    else {
      sql.append("  h." + OrderHeaderVO.VBILLCODE);
      sql.append(" in ");
      sql.startParentheses();
      sql.append(" select id1 from ");
      String temptable = this.getTempTable(codeList);
      sql.append(temptable);
      sql.endParentheses();
    }
    return sql.toString();
  }

  private String getQuerySql(List<String> codeList, List<String> pkList) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct h." + OrderHeaderVO.PK_ORDER);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h ");
    sql.append(" inner join " + PUEntity.M21_B_TABLE + " b ");
    sql.append(" on h." + OrderHeaderVO.PK_ORDER + " = b."
        + OrderItemVO.PK_ORDER);
    sql.append(" where ");
    sql.append(" h." + OrderHeaderVO.PK_GROUP, BSContext.getInstance()
        .getGroupID());
    // 这里必须拼接单据号（走索引，不然有效率问题），另外必须控制住修订不允许改订单号
    sql.append(" and " + this.getCodeCond(codeList));
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_03.name());
    String pkCond =
        builder.buildSQL(" and b." + OrderItemVO.PK_SRCORDER_B,
            pkList.toArray(new String[pkList.size()]));
    sql.append(pkCond);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    return sql.toString();
  }

  private String getTempTable(List<String> codeList) {
    List<List<Object>> data = new ArrayList<List<Object>>();

    int length = codeList.size();
    for (int i = 0; i < length; i++) {
      List<Object> row = new ArrayList<Object>();
      data.add(row);
      row.add(codeList.get(i));
    }
    String[] columns = {
      "id1"
    };
    String[] columnTypes = {
      "VARCHAR(40)"
    };
    JavaType[] types = new JavaType[] {
      JavaType.String
    };

    TempTable dao = new TempTable();
    String name =
        dao.getTempTable(PUTempTable.tmp_po_21_01.name(), columns, columnTypes,
            types, data);
    return name;
  }
}
