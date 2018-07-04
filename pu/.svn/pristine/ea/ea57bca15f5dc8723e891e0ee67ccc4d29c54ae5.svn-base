package nc.impl.pu.report.journal;

import nc.vo.pu.report.enumeration.OrderType;
import nc.vo.pub.query.ConditionVO;

public class SqlGetterFactory {

  public ISqlGetter sqlGetter(String billtype, String wheresql,
      ConditionVO[] conditions) {
    String billtypeString = billtype.substring(1, 2);
    Integer billInt = Integer.valueOf(billtypeString);
    if (billInt.equals(OrderType.PUORDER.value())) {
      return new OrderSqlGetter(wheresql, conditions);
    }
    if (billInt.equals(OrderType.PUARRIVE.value())) {
      return new ArriveSqlGetter(wheresql, conditions);
    }
    if (billInt.equals(OrderType.PURIN.value())) {
      return new PurSqlGetter(wheresql, conditions);
    }
    if (billInt.equals(OrderType.PUINVOICE.value())) {
      return new InvoiceSqlGetter(wheresql, conditions);
    }
    if (billInt.equals(OrderType.PUSETTLE.value())) {
      return new SettleSqlGetter(wheresql, conditions);
    }
    return null;
  }

}
