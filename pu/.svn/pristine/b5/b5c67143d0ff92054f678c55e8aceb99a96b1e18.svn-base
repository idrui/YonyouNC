package nc.bs.pu.m21.query.price.cal;

import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 询最低价
 * 
 * @since 6.1
 * @version 2011-11-20 上午11:25:32
 * @author yangtian
 */
public class QueryLowestPriceStrategy extends QueryLatestPriceStrategy {

  public QueryLowestPriceStrategy() {
  }

  public QueryLowestPriceStrategy(String[] purchaseOrgs, String[] suppliers,
      String[] currencies, String[] materials) {
    super(purchaseOrgs, null, suppliers, currencies, materials);
  }

  @Override
  public String getAnalysisFunctionPart(String tableName) {
    return this.getSelectPartFromUnionData(tableName, " ");
  }

  @Override
  public OrderPriceData[] getResult(IRowSet rowset) {
    OrderPriceData[] prices = new OrderPriceData[rowset.size()];
    int i = 0;
    while (rowset.next()) {
      // 采购组织
      String pk_org = rowset.getString(0);
      // 供应商
      String pk_supplier = rowset.getString(1);
      // 币种
      String currency = rowset.getString(2);
      // 物料OID
      String material = rowset.getString(3);
      // 结算财务组织
      String pk_financeorg = rowset.getString(4);
      // 本币无税净价的最低价
      UFDouble price = rowset.getUFDouble(5);
      // 本币含税净价的最低价
      UFDouble taxPrice = rowset.getUFDouble(6);
      // 原币无税净价的最低价
      UFDouble origPrice = rowset.getUFDouble(7);
      // 原币含税净价的最低价
      UFDouble origTaxPrice = rowset.getUFDouble(8);
      prices[i] =
          new OrderPriceData(pk_org, pk_supplier, currency, material,
              origPrice, origTaxPrice, price, taxPrice, pk_financeorg, null);
      i++;
    }
    return prices;
  }

  @Override
  public String getSelectPart(String tableName) {
    SqlBuilder selectPart = new SqlBuilder();
    selectPart.append(tableName + OrderPriceVO.PK_ORG + ", ");
    selectPart.append(tableName + OrderPriceVO.PK_SUPPLIER + ", ");
    selectPart.append(tableName + OrderPriceVO.CORIGCURRENCYID + ", ");
    selectPart.append(tableName + OrderPriceVO.PK_SRCMATERIAL + ", ");
    selectPart.append(tableName + OrderPriceVO.PK_PSFINANCEORG + ", ");
    selectPart.append(PriceCalculatorUtil.getSelectPartForLowestQuery(
        tableName, "m "));
    return selectPart.toString();
  }

  @Override
  public String getSelectPartFromUnionData(String tableName, String postfix) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.NTAXNETPRICE, OrderPriceVO.NORIGTAXNETPRICE,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NNETPRICE
        };
    SqlBuilder sql = new SqlBuilder();
    sql.append(tableName + OrderPriceVO.PK_ORG + ", ");
    sql.append(tableName + OrderPriceVO.PK_SUPPLIER + ", ");
    sql.append(tableName + OrderPriceVO.CORIGCURRENCYID + ", ");
    sql.append(tableName + OrderPriceVO.PK_SRCMATERIAL + ", ");
    sql.append(tableName + OrderPriceVO.PK_PSFINANCEORG + ", ");
    for (String attributeName : attributeNames) {
      sql.append(" min( " + tableName + attributeName + postfix
          + ") over(partition by " + tableName + "pk_srcmaterial )  ");
      sql.append(" " + attributeName + "m, ");
    }
    sql.append("  row_number() over(partition by " + tableName
        + "pk_srcmaterial order by " + tableName + "dbilldate desc ) rno ");
    return sql.toString();

  }

}
