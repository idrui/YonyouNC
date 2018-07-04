package nc.pubimpl.pu.m21.purp.discount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.purp.discount.DiscountRefQueryVO;
import nc.pubitf.pu.m21.purp.discount.IOrderQueryForDiscount;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class OrderQueryForDiscountImpl implements IOrderQueryForDiscount {

  @Override
  public Map<String, UFBoolean> queryRefDiscount(DiscountRefQueryVO[] queryvos)
      throws BusinessException {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    try {
      for (DiscountRefQueryVO queryvo : queryvos) {
        map.put(queryvo.getPk_discount(), UFBoolean.FALSE);
      }

      String sql = this.getQuerySql(queryvos);
      DataAccessUtils util = new DataAccessUtils();
      String[][] values = util.query(sql).toTwoDimensionStringArray();
      for (String[] value : values) {
        int count = Integer.valueOf(value[1]).intValue();
        if (count > 0) {
          map.put(value[0], UFBoolean.TRUE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }

  private String getQuerySql(DiscountRefQueryVO[] queryvos) {
    Set<String> pk_orgSet = new HashSet<String>();
    Set<String> pk_supplierSet = new HashSet<String>();
    Set<String> pk_discountSet = new HashSet<String>();

    for (DiscountRefQueryVO queryvo : queryvos) {
      pk_orgSet.addAll(Arrays.asList(queryvo.getPk_orgs()));
      pk_supplierSet.add(queryvo.getPk_supplier());
      pk_discountSet.add(queryvo.getPk_discount());
    }
    String[] pk_orgs = pk_orgSet.toArray(new String[pk_orgSet.size()]);
    String[] pk_suppliers =
        pk_supplierSet.toArray(new String[pk_supplierSet.size()]);
    String[] pk_discounts =
        pk_discountSet.toArray(new String[pk_discountSet.size()]);
    String begindate = queryvos[0].getBegindate();
    String enddate = queryvos[0].getEnddate();
    for (int i = 1; i < queryvos.length; ++i) {
      if (queryvos[i].getBegindate().compareTo(begindate) < 0) {
        begindate = queryvos[i].getBegindate();
      }
      if (queryvos[i].getEnddate().compareTo(enddate) > 0) {
        enddate = queryvos[i].getEnddate();
      }
    }
    return this.getQuerySql(pk_orgs, pk_suppliers, pk_discounts, begindate,
        enddate);
  }

  private String getQuerySql(String[] pk_orgs, String[] pk_suppliers,
      String[] pk_discounts, String begindate, String enddate) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select po_order_b.pk_discount,count(po_order_b.pk_discount) from ");
    sql.append("po_order po_order inner join po_order_b po_order_b ");
    sql.append(" on po_order.pk_order = po_order_b.pk_order ");
    sql.append(" where ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_61.name());
    String orgCond = builder.buildSQL(" ", pk_orgs);
    sql.append("po_order." + OrderHeaderVO.PK_ORG + orgCond);
    sql.append(" and po_order." + OrderHeaderVO.DBILLDATE, ">=", begindate);
    sql.append(" and po_order." + OrderHeaderVO.DBILLDATE, "<=", enddate);

    IDExQueryBuilder supbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_62.name());
    String supCond = supbuilder.buildSQL(" ", pk_suppliers);
    sql.append(" and po_order." + OrderHeaderVO.PK_SUPPLIER + supCond);
    sql.append(" and po_order." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and po_order_b." + OrderItemVO.PK_ORG + orgCond);
    sql.append(" and po_order_b." + OrderItemVO.DBILLDATE, ">=", begindate);
    sql.append(" and po_order_b." + OrderItemVO.DBILLDATE, "<=", enddate);
    sql.append(" and po_order_b." + OrderItemVO.PK_SUPPLIER + supCond);
    sql.append(" and po_order.dr", 0);
    sql.append(" and po_order_b.dr", 0);

    IDExQueryBuilder discountbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_63.name());
    sql.append(discountbuilder.buildSQL(" and po_order_b."
        + OrderItemVO.PK_DISCOUNT, pk_discounts));
    sql.append(" group by po_order_b." + OrderItemVO.PK_DISCOUNT);
    return sql.toString();
  }
}
