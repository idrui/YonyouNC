package nc.bs.pu.m21.query.price.cal;

import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 内部交易询最新价
 * 
 * @since 6.1
 * @version 2011-11-20 上午11:25:32
 * @author yangtian
 */
public class QueryLatestPrice4ToStrategy implements IQueryPriceStrategy {

  private String[] fiorgs;

  protected String[] moids;

  public QueryLatestPrice4ToStrategy(String[] fiorgs, String[] moids) {
    this.fiorgs = fiorgs;
    this.moids = moids;
  }

  @Override
  public String getAnalysisFunctionPart(String tableName) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getSelectPart(tableName)
        + ", row_number() over(partition by " + tableName
        + "pk_srcmaterial order by " + tableName + "dbilldate desc ) rno ");
    return sql.toString();
  }

  @Override
  public String getFromPart() {
    String t = new TempTableDefine().get(this.fiorgs, this.moids);
    // 用于单元测试
    // String t = " bt";
    SqlBuilder builder = new SqlBuilder();
    builder.append("  inner join " + t + " on c.pk_psfinanceorg=");
    builder.append(t + ".id1 and c.pk_srcmaterial=");
    builder.append(t + ".id2 ");
    return builder.toString();
  }

  @Override
  public String getQueryConditionPart(String modifiedTimeTableName,
      String tableName, UFDate beginTime, UFDate endTime) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(PriceCalculatorUtil.getBeginAndEndTimeQueryPart(
        modifiedTimeTableName, beginTime, endTime, ICalendar.MILLIS_PER_SECOND));
    sql.append(" and not exists(select pk_stockorg from org_stockorg stockorg");
    sql.append(" where stockorg.pk_financeorg = c.pk_psfinanceorg ");
    sql.append(" and stockorg.pk_stockorg=c.pk_arrvstoorg )");
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
      UFDouble nOrigNetPrice = rowset.getUFDouble(4);
      UFDouble nOrigTaxPrice = rowset.getUFDouble(5);
      UFDouble price = rowset.getUFDouble(6);
      UFDouble taxPrice = rowset.getUFDouble(7);
      String pk_financeorg = rowset.getString(8);
      String cunit = rowset.getString(9);
      prices[i] =
          new OrderPriceData(pk_org, pk_supplier, currencyId, material,
              nOrigNetPrice, nOrigTaxPrice, price, taxPrice, pk_financeorg,
              cunit);
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
          OrderPriceVO.NQTNETPRICE, OrderPriceVO.NQTTAXNETPRICE,
          OrderPriceVO.PK_PSFINANCEORG, OrderPriceVO.CUNITID
        };

    SqlBuilder selectPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      selectPart.append(tableName + attributeName);
      selectPart.append(attributeName == OrderPriceVO.CUNITID ? " " : ", ");
    }
    return selectPart.toString();
  }

  @Override
  public String getSelectPartFromUnionData(String tableName, String postfix) {
    return this.getAnalysisFunctionPart(tableName);
  }
}
