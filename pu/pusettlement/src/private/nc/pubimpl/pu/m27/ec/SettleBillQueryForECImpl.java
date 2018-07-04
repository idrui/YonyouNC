package nc.pubimpl.pu.m27.ec;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m27.ec.ISettleBillQueryForEC;
import nc.pubitf.pu.m27.ec.SettleBillViewECVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @since 6.0
 * @version 2011-5-8 ÏÂÎç12:21:47
 * @author wuxla
 */

public class SettleBillQueryForECImpl implements ISettleBillQueryForEC {
  @Override
  public SettleBillViewECVO[] querySettleBillByOrderBid(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createOrderBidSql(bids);
      EfficientViewQuery<SettleBillViewECVO> query =
          new EfficientViewQuery<SettleBillViewECVO>(SettleBillViewECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public SettleBillViewECVO[] querySettleBillByOrderHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createOrderHidSql(hids);
      EfficientViewQuery<SettleBillViewECVO> query =
          new EfficientViewQuery<SettleBillViewECVO>(SettleBillViewECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, UFDouble[]> querySettleInfoByOrderBid(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createBidSql(bids);
      DataAccessUtils util = new DataAccessUtils();
      String[][] values = util.query(sql).toTwoDimensionStringArray();
      if (values == null || values.length == 0) {
        return null;
      }

      Map<String, UFDouble[]> map = new HashMap<String, UFDouble[]>();
      for (String[] value : values) {
        if (value[1] != null && value[2] != null) {
          UFDouble[] settle = new UFDouble[] {
            new UFDouble(value[1]), new UFDouble(value[2])
          };
          map.put(value[0], settle);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_13.name());
    String orderBidCond =
        builder.buildSQL(SettleBillItemVO.PK_INVOICEORDER_B, bids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(SettleBillItemVO.PK_INVOICEORDER_B + ",");
    sql.append("sum(isnull(" + SettleBillItemVO.NSETTLENUM + ",0)),");
    sql.append("sum(isnull(" + SettleBillItemVO.NMONEY + ",0))");
    sql.append(" from " + PUEntity.SettleBill_B_TAB + " where ");
    sql.append(orderBidCond);
    sql.append("and dr", 0);
    sql.append(" group by " + SettleBillItemVO.PK_INVOICEORDER_B);
    return sql.toString();
  }

  private String createOrderBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_14.name());
    String orderBidCond =
        builder.buildSQL(" and " + SettleBillItemVO.PK_INVOICEORDER_B, bids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderBidCond);
    return sql.toString();
  }

  private String createOrderHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_14.name());
    String orderHidCond =
        builder.buildSQL(" and " + SettleBillItemVO.PK_INVOICEORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    return sql.toString();
  }
}
