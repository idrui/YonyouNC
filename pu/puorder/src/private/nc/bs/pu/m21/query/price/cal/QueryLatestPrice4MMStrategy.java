package nc.bs.pu.m21.query.price.cal;

import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class QueryLatestPrice4MMStrategy extends QueryLatestPrice4ToStrategy {

  private String[] pk_arrvstoorgs;

  public QueryLatestPrice4MMStrategy(String[] pk_arrvstoorgs, String[] moids) {
    super(null, moids);
    this.pk_arrvstoorgs = pk_arrvstoorgs;
  }

  @Override
  public String getFromPart() {
    String t = new TempTableDefine().get(this.pk_arrvstoorgs, this.moids);
    // 用于单元测试
    // String t = " bt";
    SqlBuilder builder = new SqlBuilder();
    builder.append("  inner join " + t + " on c.pk_arrvstoorg=");
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

    return sql.toString();
  }

  @Override
  public OrderPriceData[] getResult(IRowSet rowset) {
    OrderPriceData[] prices = new OrderPriceData[rowset.size()];
    int i = 0;
    while (rowset.next()) {
      String pk_org = rowset.getString(0);
      String pk_supplier = rowset.getString(1);
      String currencyId = null;
      String material = rowset.getString(3);
      UFDouble nOrigNetPrice = rowset.getUFDouble(4);
      UFDouble nOrigTaxPrice = rowset.getUFDouble(5);
      UFDouble price = rowset.getUFDouble(6);
      UFDouble taxPrice = rowset.getUFDouble(7);
      String pk_financeorg = rowset.getString(8);
      String cunit = rowset.getString(9);
      if (pk_financeorg != null) {// 这里是跟其他strategy的区别，其他strategy的币种都是原币币种，这里因为要本币币种所以不能从数据库里直接取，而是要根据结算财务组织进行本币的查询
        currencyId = OrgUnitPubService.queryOrgCurrByPk(pk_financeorg);
      }
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
        OrderPriceVO.NNETPRICE, OrderPriceVO.NTAXNETPRICE,// 主本币无税和含税价格
        OrderPriceVO.PK_PSFINANCEORG, OrderPriceVO.CUNITID
    };

    SqlBuilder selectPart = new SqlBuilder();
    for (String attributeName : attributeNames) {
      selectPart.append(tableName + attributeName);
      selectPart.append(attributeName == OrderPriceVO.CUNITID ? " " : ", ");
    }
    return selectPart.toString();
  }
}
