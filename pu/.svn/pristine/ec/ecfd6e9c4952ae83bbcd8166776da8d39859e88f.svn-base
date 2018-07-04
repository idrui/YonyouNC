package nc.bs.pu.m20.maintain;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 下午04:37:55
 */
public class PraybillQueryBP {

  public PraybillVO[] query(IQueryScheme queryScheme) {
    return this.lazyQuery(queryScheme);
  }

  private PraybillVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    qrySchemeProcessor.appendWhere(" and " + mainTableAlias
        + ".bislatest= 'Y' ");
    this.makeLogicalCondition(qrySchemeProcessor);

    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    // wuxla 因为请购单查询模板比较特殊，用的不是通用字段，只能写成固定值了。改查询模板涉及升级问题。
    return (PraybillVO[]) new PUBillLazyQuery<PraybillVO>(PraybillVO.class,
        POBillType.PrayBill).queryOrderByBillcode(queryScheme, "biswaitaudit");
  }

  private void makeLogicalCondition(QuerySchemeProcessor qrySchemeProcessor) {
    String tableB =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_praybill_b.pk_org");
    // 处理 是否已经生成合同
    QueryCondition cond =
        qrySchemeProcessor.getQueryCondition(PraybillHeaderVO.BISNGENCT);
    if (null != cond && UFBoolean.valueOf(cond.getValues()[0]).booleanValue()) {
      qrySchemeProcessor.appendWhere(" and " + tableB + "."
          + PraybillItemVO.NGENCT + " > 0");
    }
    else if (null != cond
        && !UFBoolean.valueOf(cond.getValues()[0]).booleanValue()) {
      qrySchemeProcessor.appendWhere(" and " + tableB + "."
          + PraybillItemVO.NGENCT + " = 0");
    }

    // 待审批
    // cond =
    // qrySchemeProcessor.getLogicalCondition(PraybillHeaderVO.BISWAITAUDIT);
  }

  // /**
  // * 方法功能描述：简要描述本方法的功能。
  // * <p>
  // * 得到请购单只查询主键的sql语句 <b>参数说明</b>
  // *
  // * @return <p>
  // * @since 6.0
  // * @author linsf
  // * @time 2010-1-28 下午04:43:45
  // */
  // private String getPraybillKeysSql(String whereSql) {
  // String sql;
  //
  // if (StringUtils.isNotEmpty(whereSql)) {
  // sql = " select distinct po_praybill.pk_praybill " + whereSql;
  // sql +=
  // " and po_praybill.dr = 0 and po_praybill.bislatest = 'Y' and po_praybill.pk_group='"
  // + InvocationInfoProxy.getInstance().getGroupId() + "'";
  // }
  // else {
  // sql =
  // " select distinct po_praybill.pk_praybill from po_praybill "
  // +
  // " where po_praybill.dr = 0  and bislatest = 'Y' and po_praybill.pk_group='"
  // + InvocationInfoProxy.getInstance().getGroupId() + "'";
  // }
  //
  // return sql;
  // }
}
