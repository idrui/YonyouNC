package nc.vo.pu.m21.vochange;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.pf.IPFExchangeUpgradeEx;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class PFExchangeUpgradeEx20To21 implements IPFExchangeUpgradeEx {

  @Override
  public String[] getIgnoreDestAttrs(String srcBillType, String srcTransType,
      String destBillType, String destTransType) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_vosplititem ");
    sql.append(" from pub_vosplititem ");
    sql.append(" where dr = 0 ");
    sql.append(" and ");
    sql.append("billtype", srcBillType);
    sql.append(" and ");
    sql.append("dest_billtype", destBillType);
    sql.append(" and ");
    sql.append("ismust", UFBoolean.FALSE);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] ignoreDestAttrs = rowset.toOneDimensionStringArray();
    return ignoreDestAttrs;
  }

}
