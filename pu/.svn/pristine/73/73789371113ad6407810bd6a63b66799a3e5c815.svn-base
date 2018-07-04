package nc.pubimpl.pu.m21.pu.m25;

import java.util.List;

import nc.bs.pu.m21.query.pu.OrderQueryFor25BP;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.pu.m21.pu.m25.IOrderQueryFor25;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderQueryFor25Impl implements IOrderQueryFor25 {

  @Override
  public IQueryScheme getNonVMITrantypeQS(String pk_group)
      throws BusinessException {
    try {
      FromWhereSQLImpl fwsql = new FromWhereSQLImpl();
      fwsql.setFrom(PUEntity.ORDER_TRANTYPE_TAB);
      SqlBuilder whr = new SqlBuilder();
      whr.append("dr", 0);
      whr.append(" and isnull(" + PoTransTypeVO.BVMI + ",'" + UFBoolean.FALSE
          + "')", UFBoolean.FALSE);
      whr.append(" and " + PoTransTypeVO.PK_GROUP, pk_group);
      fwsql.setWhere(whr.toString());
      QueryScheme qs = new QueryScheme();
      qs.putTableJoinFromWhereSQL(fwsql);
      return qs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor25(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.createSql(queryScheme);
    try {
      return new OrderQueryFor25BP().query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  private String createAppendWhere(IQueryScheme queryScheme, boolean feeQuery) {
    // 为了拼sql方便，主表用H代替，子表用B代替，最后用表别名统一替换
    SqlBuilder partWhr = new SqlBuilder();
    // 固定查询条件，如审批通过，未冻结等
    partWhr.append(this.createFixedQuerySql());
    if (feeQuery) {
      partWhr.append(this.createTaxMnySql());
    }
    else {
      // 数量限制
      partWhr.append(this.createNumSql());
      // 针对货物查询，过滤掉流程里既有入库又有订单的流程
      partWhr.append(this.createGoodBusiTypeSql(queryScheme));
    }
    // 物料过滤
    partWhr.append(this.createMaterialJoinSql(feeQuery));
    return partWhr.toString();
  }

  /**
   * 以“ and ... ”形式返回固定的查询过滤条件
   * 
   * @return
   */
  private String createFixedQuerySql() {
    SqlBuilder partWhr = new SqlBuilder();
    // 最新版本
    partWhr.append(" and H." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    // 未冻结
    partWhr.append(" and H." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    // 审批通过或输出
    partWhr.append(" and H." + OrderHeaderVO.FORDERSTATUS, new Integer[] {
      (Integer) POEnumBillStatus.APPROVE.value(),
      (Integer) EnumBillStatus.EXPORT.value()
    });
    // 未开票关闭
    partWhr.append(" and B." + OrderItemVO.BINVOICECLOSE, UFBoolean.FALSE);
    // 非赠品
    partWhr.append(" and B." + OrderItemVO.BLARGESS, UFBoolean.FALSE);
    return partWhr.toString();
  }

  /**
   * 针对货物查询，过滤掉流程里既有入库又有订单的流程
   * 
   * @param busiTypes
   * @return
   */
  @SuppressWarnings("unchecked")
  private String createGoodBusiTypeSql(IQueryScheme queryScheme) {
    // 对于流程里既有入库又有订单，订单只能参照费用物料。
    // 所以对于查询货物发票时要过滤掉这些流程
    List<String> busiTypes =
        (List<String>) queryScheme.get(PUQueryConst.BUSITYPES_QS_KEY);

    if (ListUtil.isEmpty(busiTypes)) {
      return "";
    }
    String sql =
        PfServiceScmUtil.getQueryBillRefBillSql(POBillType.Invoice.getCode(),
            ICBillType.PurchaseIn.getCode(),
            busiTypes.toArray(new String[busiTypes.size()]));
    return " and H." + OrderHeaderVO.PK_BUSITYPE + " not in (" + sql + ")";
  }

  /**
   * 连接物料表，查询费用或货物
   * 
   * @param feeMaterial
   * @return
   */
  private String createMaterialJoinSql(boolean feeQuery) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and B." + OrderItemVO.PK_MATERIAL);

    sql.append(" in (select pk_material from bd_material where  ");
    if (feeQuery) {
      sql.append(" isnull(bd_material.fee,'N')='Y' or  isnull(bd_material.discountflag,'N') ='Y' )");
    }
    else {
      sql.append(" isnull(bd_material.fee,'N')='N' and isnull(bd_material.discountflag,'N') ='N' )");
    }
    return sql.toString();
  }

  private String createNumSql() {
    SqlBuilder partWhr = new SqlBuilder();
    partWhr.append(" and (");
    partWhr.append("     B." + OrderItemVO.NNUM, ">", 0);
    partWhr.append("      and ");
    partWhr.append("     B." + OrderItemVO.NNUM + " > isnull( B."
        + OrderItemVO.NACCUMINVOICENUM + ",0) ");
    partWhr.append("      or ");
    partWhr.append("     B." + OrderItemVO.NNUM, "<", 0);
    partWhr.append("      and ");
    partWhr.append("     B." + OrderItemVO.NNUM + " < isnull(B."
        + OrderItemVO.NACCUMINVOICENUM + ",0) ");
    partWhr.append(" ) ");
    return partWhr.toString();
  }

  private String createSql(IQueryScheme queryScheme) {
    StringBuilder sql = new StringBuilder();
    // 货物查询
    sql.append(this.creatSql(queryScheme, false));
    sql.append(" union all ");
    // 费用查询
    sql.append(this.creatSql(queryScheme, true));
    
    sql.append(" order by pk_order_b ");
    
    return sql.toString();
  }

  /**
   * 针对费用查询，累计开票金额小于价税合计
   * 
   * @return
   */
  private String createTaxMnySql() {
    SqlBuilder partWhr = new SqlBuilder();
    partWhr.append(" and (");
    partWhr.append("     B." + OrderItemVO.NTAXMNY, ">", 0);
    partWhr.append("      and ");
    partWhr.append("     B." + OrderItemVO.NTAXMNY + " > isnull( B."
        + OrderItemVO.NACCUMINVOICEMNY + ",0) ");
    partWhr.append("      or ");
    partWhr.append("     B." + OrderItemVO.NTAXMNY, "<", 0);
    partWhr.append("      and ");
    partWhr.append("     B." + OrderItemVO.NTAXMNY + " < isnull(B."
        + OrderItemVO.NACCUMINVOICEMNY + ",0) ");
    partWhr.append("     ) ");
    return partWhr.toString();
  }

  /**
   * 构造费用或者货物的查询sql
   * 
   * @param queryScheme 查询方案
   * @param fee 是否费用查询
   * @return
   */
  private String creatSql(IQueryScheme queryScheme, boolean feeQuery) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();

    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    SqlBuilder wholeSql = new SqlBuilder();
    // 为了拼sql方便，主表用H代替，子表用B代替，最后用表别名统一替换
    wholeSql.append("select B." + OrderItemVO.PK_ORDER_B);
    wholeSql.append(qrySchemeProcessor.getFinalFromWhere());
    wholeSql.append(this.createAppendWhere(queryScheme, feeQuery));
    // 用表别名进行替换
    String finalSql = wholeSql.toString().replace("H.", mainTableAlias + ".");
    finalSql = finalSql.replace("B.", itemTableAlias + ".");

    return finalSql;
  }
}
