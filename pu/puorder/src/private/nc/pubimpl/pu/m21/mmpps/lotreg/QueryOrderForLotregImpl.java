package nc.pubimpl.pu.m21.mmpps.lotreg;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.mmpub.sdmanage.IBillTraceParam;
import nc.pubitf.mmpub.sdmanage.IBillTraceResult;
import nc.pubitf.pu.m21.mmpps.lotreg.IQueryOrderForLotreg;
import nc.pubitf.pu.m21.mmpps.lotreg.OrderBillTraceResult;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 采购订单为"供需预订维护"节点提供的单据追溯接口实现类
 * 
 * @since 6.3.1
 * @version 2013-7-30下午08:40:29
 * @author fanly3
 */
public class QueryOrderForLotregImpl implements IQueryOrderForLotreg {

  @Override
  public List<IBillTraceResult> getBills(IBillTraceParam billTraceParam) {
    // 拼from部分
    String tableName = billTraceParam.getTableName();
    SqlBuilder commonFromSql = new SqlBuilder();
    commonFromSql
        .append(" po_order_b po_order_b inner join po_order po_order ");
    commonFromSql.append(" on po_order_b.pk_order = po_order.pk_order ");

    // 拼where部分
    SqlBuilder commonWhereSql = new SqlBuilder();
    commonWhereSql.append(" po_order.dr", 0);
    commonWhereSql.append(" and po_order_b.dr", 0);
    // 单据追溯时红字单据，非红字单据都要
    // 最新版本
    commonWhereSql.append(" and po_order.bislatest", UFBoolean.TRUE);

    List<IBillTraceResult> resultList = new ArrayList<IBillTraceResult>();
    List<String> billTypeList = billTraceParam.getTargetBillType();

    // 存放billTypeList中除了"EC49"之外的单据类型,EC49是电子商务的排程单据类型
    List<String> tempList = new ArrayList<String>();
    for (String billType : billTypeList) {
      if (!"EC49".equals(billType)) {
        tempList.add(billType);
      }
    }
    if (tempList != null && tempList.size() > 0) {
      // 拼最终from语句
      SqlBuilder fromSql = new SqlBuilder();
      fromSql.append(commonFromSql);
      fromSql.append(" inner join " + tableName + " " + tableName);
      fromSql.append(" on po_order_b.csourceid = " + tableName + "."
          + billTraceParam.getSrcId());
      // 拼最终where语句
      SqlBuilder whereSql = new SqlBuilder();
      whereSql.append(commonWhereSql);
      whereSql.append(" and po_order_b.csourcebid = case when " + tableName
          + "." + billTraceParam.getSrcBid() + "='~' then " + tableName + "."
          + billTraceParam.getSrcId() + " else " + tableName + "."
          + billTraceParam.getSrcBid() + " end ");
      whereSql.append(" and po_order_b.csourcetypecode",
          tempList.toArray(new String[tempList.size()]));
      OrderBillTraceResult result = new OrderBillTraceResult();
      result.setFromSql(fromSql.toString());
      result.setWhereSql(whereSql.toString());
      resultList.add(result);
    }

    // 返回来源排程的采购订单
    if (billTypeList.contains("EC49")) {
      // 拼最终from语句
      SqlBuilder fromSql = new SqlBuilder();
      fromSql.append(commonFromSql);
      fromSql.append(" inner join po_order_b_ec po_order_b_ec ");
      fromSql.append(" on po_order_b.pk_order_b = po_order_b_ec.pk_order_b");
      fromSql.append(" inner join " + tableName + " " + tableName);
      fromSql.append(" on po_order_b_ec.pk_schedule = " + tableName + "."
          + billTraceParam.getSrcId());
      // 拼最终where语句
      SqlBuilder whereSql = new SqlBuilder();
      whereSql.append(commonWhereSql);
      whereSql.append(" and po_order_b_ec.pk_schedule_b = " + tableName + "."
          + billTraceParam.getSrcBid());
      // whereSql.appendIDIsNotNull(" and po_order_b_ec.pk_schedule");
      OrderBillTraceResult result = new OrderBillTraceResult();
      result.setFromSql(fromSql.toString());
      result.setWhereSql(whereSql.toString());
      resultList.add(result);
    }

    return resultList;
  }

}
