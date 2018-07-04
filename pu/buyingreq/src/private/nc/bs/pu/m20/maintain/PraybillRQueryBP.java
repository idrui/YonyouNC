package nc.bs.pu.m20.maintain;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-22 上午11:42:02
 */
public class PraybillRQueryBP {

  /**
   * 方法功能描述：请购单修订查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 上午11:42:29
   */
  public PraybillVO[] query(IQueryScheme queryScheme) {
    return this.lazyQuery(queryScheme);
  }

  /**
   * 方法功能描述：请购单修订历史查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 上午11:42:29
   */
  public PraybillVO[] queryHistory(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(this.getHistorySql(sql));
    String[] ids = rowset.toOneDimensionStringArray();
    return new BillQuery<PraybillVO>(PraybillVO.class).query(ids);
  }

  /**
   * 方法功能描述：得到请购单只查询主键的sql语句。
   * <p>
   * <b>参数说明</b>
   * 
   * @param whereSql
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 上午11:42:49
   */
  private String getHistorySql(String whereSql) {
    String sql;

    if (!StringUtil.isEmptyWithTrim(whereSql)) {
      sql =
          " select pk_praybill from po_praybill "
              + " where dr = 0 and  pk_group='"
              + InvocationInfoProxy.getInstance().getGroupId() + "'" + whereSql
              + "  order by nversion desc";

    }
    else {
      sql =
          " select pk_praybill from po_praybill "
              + " where dr = 0 and  pk_group='"
              + InvocationInfoProxy.getInstance().getGroupId()
              + "'  order by nversion desc";
    }

    return sql;
  }

  private PraybillVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }
    qrySchemeProcessor.appendWhere(mainTableAlias + ".dr = 0 and ");
    qrySchemeProcessor.appendWhere(mainTableAlias + ".fbillstatus = "
        + POEnumBillStatus.APPROVE.value() + " and ");
    qrySchemeProcessor.appendWhere(mainTableAlias + ".bislatest= 'Y' ");
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    return (PraybillVO[]) new PUBillLazyQuery<PraybillVO>(PraybillVO.class,
        POBillType.PrayBill).queryOrderByBillcode(queryScheme);
  }

}
