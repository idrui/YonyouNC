package nc.bs.pu.m20.query;

import nc.bs.pu.m20.query.logicutil.CondTOWhereUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ITBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 请购单为进口合同提供的转单查询服务BP
 * 
 * @since 6.3.1
 * @version 2013-7-3上午10:58:28
 * @author fanly3
 */
public class QueryFor5801BP extends AbstractRefQueryBP {
  private IQueryScheme queryScheme = null;

  public QueryFor5801BP(IQueryScheme queryScheme) {
    super(new QuerySchemeProcessor(queryScheme));
    this.queryScheme = queryScheme;
  }

  @Override
  public StringBuffer makeGetPKSql() {
    String sqlWhere = this.psor.getFinalFromWhere();
    String where = sqlWhere;
    if (null != sqlWhere && "1=1".equals(sqlWhere.trim())) {
      where = null;
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct " + this.headtb + ".pk_praybill,");
    sql.append(this.itemtb + ".pk_praybill_b ");
    sql.append(where);
    sql.append(" and " + this.headtb + ".pk_praybill = " + this.itemtb
        + ".pk_praybill");
    sql.append(" and " + this.headtb + ".bsctype", UFBoolean.FALSE);
    sql.append(" and " + this.headtb + ".bislatest ", UFBoolean.TRUE);
    sql.append(" and " + this.headtb + ".fbillstatus ",
        +POEnumBillStatus.APPROVE.toInt());
    sql.append(" and " + this.headtb + ".dr = 0");
    sql.append(" and " + this.itemtb + ".nnum > isnull(" + this.itemtb
        + ".naccumulatenum, 0)");
    sql.append(" and " + this.itemtb + ".browclose ", UFBoolean.FALSE);
    sql.append(" and " + this.itemtb + ".bpublishtoec ", UFBoolean.FALSE);
    // 2012-10-17 fanly3 V63新需求 已生成总括订单的请购单行不允许生成下游单据
    sql.append(" and " + this.itemtb + ".bisgensaorder ", UFBoolean.FALSE);
    sql.append(" and " + this.itemtb + ".dr = 0");

    String transTypes = this.getOrderTranstype();
    if (!StringUtils.isEmpty(transTypes)) {
      sql.append(" and ( ");
      sql.append(this.itemtb + ".cordertrantypecode", transTypes);
      sql.append(" or ");
      sql.appendIDIsNull(this.itemtb + ".cordertrantypecode");
      sql.append(")");
    }
    else {
      // 进口合同可以参照到表体订单类型为空或者为进口合同交易类型的请购单
      SqlBuilder inSqlStr = new SqlBuilder();
      inSqlStr.append("select pk_billtypeid from bd_billtype ");
      inSqlStr.append(" where ");
      inSqlStr.append("parentbilltype", ITBillType.Contract.getCode());
      inSqlStr.append(" and ");
      inSqlStr.append("pk_group", AppContext.getInstance().getPkGroup());

      sql.append(" and ( ");
      sql.append(this.itemtb + ".cordertrantypecode in (" + inSqlStr.toString()
          + ")");
      sql.append(" or ");
      sql.appendIDIsNull(this.itemtb + ".cordertrantypecode");
      sql.append(")");
    }

    // 请购安排处理
    sql = CondTOWhereUtil.buildIsarrange(sql, this.headtb, this.itemtb);

    return new StringBuffer(sql.toString());
  }

  /**
   * 处理交易类型发布节点
   * 
   * @return
   */
  private String getOrderTranstype() {
    String ttypecode =
        (String) this.queryScheme.get(PUQueryConst.BILLTYPE_QS_KEY);
    if (null != ttypecode && PfUtilBaseTools.isTranstype(ttypecode)) {
      return PfServiceScmUtil.getTrantypeidByCode(new String[] {
        ttypecode
      }).get(ttypecode);
    }
    return null;
  }
}
