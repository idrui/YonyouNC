package nc.bs.pu.m21.query.price.cal;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 询最新价
 * 
 * @since 6.1
 * @version 2011-11-20 上午11:25:32
 * @author yangtian
 */
public class QueryLatestPriceStrategy implements IQueryPriceStrategy {

  private String[] financeOrgs;

  private String[] materials;

  private String[] purchaseOrgs;

  private String[] suppliers;

  protected String[] currencies;

  public QueryLatestPriceStrategy() {
  }

  public QueryLatestPriceStrategy(String[] purchaseOrgs, String[] financeOrgs,
      String[] suppliers, String[] currencies, String[] materials) {
    this.purchaseOrgs = purchaseOrgs;
    this.financeOrgs = financeOrgs;
    this.suppliers = suppliers;
    this.currencies = currencies;
    this.materials = materials;
  }

  @Override
  public String getAnalysisFunctionPart(String tableName) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getSelectPart(tableName));
    sql.append(", row_number() over(partition by ");
    sql.append(tableName + "pk_srcmaterial,");
    sql.append(tableName + "corigcurrencyid,");
    sql.append(tableName + "pk_supplier,");
    sql.append(tableName + "pk_org");
    sql.append(" order by " + tableName + "dbilldate desc ) rno ");
    return sql.toString();
  }

  @Override
  public String getFromPart() {
    return "";
  }

  @Override
  public String getQueryConditionPart(String modifiedTimeTableName,
      String tableName, UFDate beginTime, UFDate endTime) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(
        modifiedTimeTableName, beginTime, endTime, ICalendar.MILLIS_PER_SECOND));
    String marInSql =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_07.name()).buildSQL(
            tableName + OrderPriceVO.PK_SRCMATERIAL, this.materials);
    sql.append(" and " + marInSql);
    if (!ArrayUtils.isEmpty(this.currencies)) {
      String currInSql =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_56.name()).buildSQL(
              tableName + OrderPriceVO.CORIGCURRENCYID, this.currencies);
      sql.append(" and " + currInSql);
    }
    if (!ArrayUtils.isEmpty(this.financeOrgs)) {
      String finInSql =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_57.name()).buildSQL(
              tableName + OrderPriceVO.PK_PSFINANCEORG, this.financeOrgs);
      sql.append(" and " + finInSql);
    }
    if (!ArrayUtils.isEmpty(this.purchaseOrgs)) {
      String purInSql =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_58.name()).buildSQL(
              tableName + OrderPriceVO.PK_ORG, this.purchaseOrgs);
      sql.append(" and " + purInSql);
    }
    if (!ArrayUtils.isEmpty(this.suppliers)) {
      String supInSql =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_59.name()).buildSQL(
              tableName + OrderPriceVO.PK_SUPPLIER, this.suppliers);
      sql.append(" and " + supInSql);
    }
    return sql.toString();
  }

  @Override
  public OrderPriceData[] getResult(IRowSet rowset) {
    OrderPriceData[] prices = new OrderPriceData[rowset.size()];
    int i = 0;
    while (rowset.next()) {
      String pk_org = rowset.getString(0);
      String pk_supplier = rowset.getString(1);
      String currencyId = rowset.getString(2);
      String material = rowset.getString(3);
      UFDouble origPrice = rowset.getUFDouble(4);
      UFDouble origTaxPrice = rowset.getUFDouble(5);
      UFDouble price = rowset.getUFDouble(6);
      UFDouble taxPrice = rowset.getUFDouble(7);
      String pk_financeorg = rowset.getString(8);
      String cunit = rowset.getString(9);
      prices[i] =
          new OrderPriceData(pk_org, pk_supplier, currencyId, material,
              origPrice, origTaxPrice, price, taxPrice, pk_financeorg, cunit);

      prices[i].setTs(rowset.getUFDateTime(10));
      i++;
    }
    return prices;
  }

  @Override
  public String getSelectPart(String tableName) {
    String attributeNames[] =
        new String[] {
          OrderPriceVO.PK_ORG, OrderPriceVO.PK_SUPPLIER,
          OrderPriceVO.CORIGCURRENCYID, OrderPriceVO.PK_SRCMATERIAL,
          OrderPriceVO.NORIGNETPRICE, OrderPriceVO.NORIGTAXNETPRICE,
          OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,
          OrderPriceVO.PK_PSFINANCEORG, OrderPriceVO.CUNITID, OrderPriceVO.TS
        };

    SqlBuilder selectPart = new SqlBuilder();
    int i = 0;
    for (String attributeName : attributeNames) {
      i++;
      selectPart.append(tableName + attributeName);
      selectPart.append(i == attributeNames.length ? " " : ", ");
    }
    return selectPart.toString();
  }

  @Override
  public String getSelectPartFromUnionData(String tableName, String postfix) {
    return this.getAnalysisFunctionPart(tableName);
  }
}
