package nc.bs.pu.m20.query;

import nc.bs.pu.m20.query.logicutil.CondTOWhereUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单为价格审批单提供查询服务的查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-12 上午11:34:23
 */
public class QueryFor28BP extends AbstractRefQueryBP {

  public QueryFor28BP(QuerySchemeProcessor psor) {
    super(psor);
  }

  @Override
  public StringBuffer makeGetPKSql() {

    String sqlWhere = this.psor.getFinalFromWhere();
    String where = sqlWhere;
    if (null != sqlWhere && "1=1".equals(sqlWhere.trim())) {
      where = null;
    }

    StringBuffer sql = new StringBuffer();
    sql.append(" select distinct " + this.headtb + ".pk_praybill,");
    sql.append(this.itemtb + ".pk_praybill_b, ");
    sql.append(this.itemtb + ".Crowno ");
    sql.append(where);
    sql.append(" and " + this.headtb + ".bislatest = 'Y'");
    sql.append(" and " + this.headtb + ".fbillstatus = "
        + POEnumBillStatus.APPROVE.value());
    sql.append(" and " + this.headtb + ".dr = 0");
    sql.append(" and " + this.headtb + ".pk_praybill = " + this.itemtb
        + ".pk_praybill");
    sql.append(" and " + this.itemtb + ".browclose = 'N'");
    sql.append(" and " + this.itemtb + ".bpublishtoec = 'N'");
    // 2012-10-17 fanly3 V63新需求 已生成总括订单的请购单行不允许生成下游单据
    sql.append(" and " + this.itemtb + ".bisgensaorder = 'N'");
    sql.append(" and " + this.itemtb + ".dr = 0");

    // 处理 是否 已经生成合同
    CondTOWhereUtil.buildIsngenct(this.psor, sql, this.itemtb);

    // 处理 是否 已经生成价格审批单
    CondTOWhereUtil.buildIsngenAudit(this.psor, sql, this.itemtb);

    // 处理 是否 已经生成采购订单
    CondTOWhereUtil.buildIsgenorderbill(this.psor, sql, this.itemtb);

    // 请购安排处理
    CondTOWhereUtil.buildIsarrange(sql, this.headtb, this.itemtb);

    // 按照采购岗过滤物料
    FilterForPosUtil.filterMaterialByPos(this.psor, sql);

    sql.append(" order by " + this.itemtb + ".crowno");
    return sql;
  }

  /**
   * 处理返回结果,各个子类根据情况可以覆写
   * 处理是否有供应商有效价格
   * 
   * @param queryResult
   * @return
   */
  @Override
  protected PraybillVO[] processQueryResult(PraybillVO[] queryResult) {

    QueryCondition con =
        this.psor.getQueryCondition(PraybillHeaderVO.BISEFFECTPRICE);
    if (con != null && con.getValues() != null) {
      Object isFilter = con.getValues()[0];
      if (UFBoolean.TRUE.toString().equals(isFilter)) {
        return super.filterEffectivePrice(queryResult);
      }
    }
    return queryResult;
  }
}
