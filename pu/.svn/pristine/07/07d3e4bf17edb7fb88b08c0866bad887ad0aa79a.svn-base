package nc.pubimpl.pu.m20.mmpps.lotreg;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.mmpub.sdmanage.IBillTraceParam;
import nc.pubitf.mmpub.sdmanage.IBillTraceResult;
import nc.pubitf.pu.m20.mmpps.lotreq.IQueryPrayBillForLotreg;
import nc.pubitf.pu.m20.mmpps.lotreq.PrayBillTraceResult;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 请购单为"供需预订维护"节点提供的单据追溯接口实现类
 * 
 * @since 6.3.1
 * @version 2013-7-30下午07:43:00
 * @author fanly3
 */
public class QueryPrayBillForLotregImpl implements IQueryPrayBillForLotreg {

  @Override
  public List<IBillTraceResult> getBills(IBillTraceParam billTraceParam) {
    // 拼from部分
    String tableName = billTraceParam.getTableName();
    SqlBuilder fromSql = new SqlBuilder();
    fromSql
        .append(" po_praybill_b po_praybill_b inner join po_praybill po_praybill ");
    fromSql.append(" on po_praybill_b.pk_praybill = po_praybill.pk_praybill ");
    fromSql.append(" inner join " + tableName + " " + tableName);
    fromSql.append(" on po_praybill_b.csourceid = " + tableName + "."
        + billTraceParam.getSrcId());

    // 拼where部分
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" po_praybill.dr", 0);
    whereSql.append(" and po_praybill_b.dr", 0);
    // 最新版本
    whereSql.append(" and po_praybill.bislatest", UFBoolean.TRUE);
    // //
    // 计划订单是单表结构，来源bid为空，所以不能直接拼po_praybill_b.csourcebid=billTraceParam.getSrcBid()
    // whereSql.append(" and case when " + tableName + "."
    // + billTraceParam.getSrcBid() + "='~' then po_praybill_b.csourceid = "
    // + tableName + "." + billTraceParam.getSrcId()
    // + " else po_praybill_b.csourcebid = " + tableName + "."
    // + billTraceParam.getSrcBid() + " end");
    whereSql.append(" and po_praybill_b.csourcebid = case when " + tableName
        + "." + billTraceParam.getSrcBid() + "='~' then " + tableName + "."
        + billTraceParam.getSrcId() + " else " + tableName + "."
        + billTraceParam.getSrcBid() + " end");
    List<String> billTypeList = billTraceParam.getTargetBillType();
    whereSql.append(" and po_praybill_b.csourcetypecode",
        billTypeList.toArray(new String[billTypeList.size()]));
    List<IBillTraceResult> resultList = new ArrayList<IBillTraceResult>();
    PrayBillTraceResult result = new PrayBillTraceResult();
    // 设置from部分
    result.setFromSql(fromSql.toString());
    // 设置where部分
    result.setWhereSql(whereSql.toString());
    resultList.add(result);

    return resultList;
  }

}
