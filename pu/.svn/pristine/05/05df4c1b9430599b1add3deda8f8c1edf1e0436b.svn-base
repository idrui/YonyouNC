package nc.impl.pu.report.journal;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.journal.JournalConstant;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/*
 * 物料基本分类 * 物料 * 采购组织 * 采购部门 * 采购员 *
 *  月份 * 日期 供应商 订单主数量  * 订单无税单价 * 订单无税金额* 
 *  订单税额 订单价税合计 订单金额百分比(%) 付款金额 付款金额百分比(%)  
 *  退货主数量 到货主数量  到货金额  退库主数量  入库主数量  入库金额  发票主数量
 *    发票无税单价  发票无税金额 发票税额,发票价税合计,发票金额百分比(%)
 *    ,结算数量,结算单价, 结算金额
 * 汇总口径  ：// 物料基本分类 // 物料 // 采购组织 // 采购部门 // 采购员// 月份 日期 供应商
 */
public class OrderSqlGetter implements ISqlGetter {

  private ConditionVO[] conditions;

  private String Join_21 = " "
      + " from po_order po_order inner join po_order_b "
      + " on po_order.pk_order = po_order_b.pk_order ";

  private String Join_21_where =
      " and po_order.dr=0 and po_order_b.dr=0 and po_order.bislatest = 'Y' ";

  private String Join_MARCLASS = " inner join bd_material bd_material "
      + " on po_order_b.pk_material = bd_material.pk_material"
      + " inner join bd_marbasclass bd_marbasclass "
      + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass ";

  private String JOIN_SUPPLIER = " inner join BD_SUPPLIER BD_SUPPLIER  "
      + "on BD_SUPPLIER.pk_supplier = po_order.pk_supplier";

  // 物料基本分类// 物料// 采购组织// 采购部门
  // 采购员// 月份// 日期// 供应商// 币种
  private String SELECT_Feild = " po_order_b.pk_material pk_material,"
      + " po_order.pk_org_v pk_org_v,"
      + " PO_ORDER.PK_DEPT_V pk_dept_v , "
      + " PO_ORDER.cemployeeid pk_psndoc,"
      + " substring(PO_ORDER.dbilldate,6,2) monthvalue,"
      + " substring(PO_ORDER.dbilldate,1,10) dbilldate, "
      + " po_order.PK_SUPPLIER PK_SUPPLIER, "
      + " po_order_b.corigcurrencyid corigcurrencyid ," 
      + " po_order_b.nnum ordernnum, " // 主数量
      + " po_order_b.nmny ordernmny , " // 无税金额
      + " po_order_b.ntax orderntax ," // 税额
      + " po_order_b.nacccancelinvmny nacccancelinvmny ," // 累计已核销本币开票金额
      + " 0 arrivennum ," + " 0 arrivebacknnum ," + " 0 arrivenmny ,"
      + " 0 purnnum ," + " 0 purbacknnum ," + " 0 purnmny ,"
      + " 0 invoicennum ," + " 0 invoicenmny ," + " 0 invoicetax ,"
      + " 0 settlennum ," + " 0 settlenmny ";

  private String whereConds;

  public OrderSqlGetter(String wheresql, ConditionVO[] conditions) {
    this.whereConds = wheresql;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {
    SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.getSelectFeilByGroups(groups));
    sqlSelect.append(this.getJoinFeildByGroups(groups));
    sqlSelect.append(" where ");
    String where = this.replaceWhere();
    sqlSelect.append(this.appendOrderItemDate(where, this.conditions));
    sqlSelect.append(this.Join_21_where);
    return sqlSelect.toString();
  }

  /**
   * 为了语义模型完成时不会出错提供的方法。
   * 没有实际的业务含义及作用。
   * 
   * @return SQL
   */
  public String getSqlWhenNull() {
    SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.getSelectFeilByGroups(null));
    sqlSelect.append(this.Join_21);
    sqlSelect.append(" where 1=2 ");
    return sqlSelect.toString();
  }

  private String appendOrderItemDate(String where, ConditionVO[] conditions) {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(where);
    for (ConditionVO con : conditions) {
      if (JournalConstant.DBILLDATE.equalsIgnoreCase(con.getFieldCode())) {
        String appendDate = new ConditionVO().getWhereSQL(new ConditionVO[] {
          con
        });
        appendDate =
            appendDate.replace(JournalConstant.DBILLDATE, PUEntity.M21_B_TABLE
                + "." + OrderHeaderVO.DBILLDATE);// 单据日期
        wheresql.append(" and (");
        wheresql.append(appendDate);
        wheresql.append(" ) ");
      }
      if (JournalConstant.PK_ORG.equalsIgnoreCase(con.getFieldCode())) {
        String appendOrg = new ConditionVO().getWhereSQL(new ConditionVO[] {
          con
        });
        appendOrg =
            appendOrg.replace(JournalConstant.PK_ORG, PUEntity.M21_B_TABLE
                + "." + OrderHeaderVO.PK_ORG);// 单据日期
        wheresql.append(" and (");
        wheresql.append(appendOrg);
        wheresql.append(" ) ");
      }

    }
    return wheresql.toString();
  }

  private String getJoinFeildByGroups(String[] groups) {
    StringBuffer joinSql = new StringBuffer(this.Join_21);

    for (String groupby : groups) {
      if (groupby.equalsIgnoreCase("pk_marbasclass")) {
        joinSql.append(this.Join_MARCLASS);
      }
    }
    for (ConditionVO con : this.conditions) {
      if (con.getFieldCode().equalsIgnoreCase("bd_supplier.name")
          || con.getFieldCode().equalsIgnoreCase("bd_supplier.code")
          || con.getFieldCode().equalsIgnoreCase("bd_supplier.pk_areacl")) {
        joinSql.append(this.JOIN_SUPPLIER);
        break;
      }
    }

    return joinSql.toString();
  }

  private String getSelectFeilByGroups(String[] groups) {
    String selectFeild = null;
    if (null == groups) {
      return " select null pk_marbasclass ," + this.SELECT_Feild;
    }
    for (String groupby : groups) {
      if (groupby.equalsIgnoreCase("pk_marbasclass")) {
        selectFeild =
            "  select BD_MARBASCLASS.pk_marbasclass pk_marbasclass , ";
        return selectFeild + this.SELECT_Feild;
      }
    }
    return " select null pk_marbasclass ," + this.SELECT_Feild;
  }

  /**
   * 替换sql
   * 
   * @return
   */
  private String replaceWhere() {

    String whereSql =
        this.whereConds.replace(JournalConstant.DBILLDATE, PUEntity.M21_H_TABLE
            + "." + OrderHeaderVO.DBILLDATE);// 单据日期
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, PUEntity.M21_H_TABLE + "."
            + OrderHeaderVO.PK_ORG);// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, PUEntity.M21_B_TABLE
            + "." + OrderItemVO.PK_ARRVSTOORG);// 替换收货库存组织
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, PUEntity.M21_H_TABLE + "."
            + OrderHeaderVO.CEMPLOYEEID);// 业务员
    whereSql =
        whereSql.replace(JournalConstant.PK_DEPT, PUEntity.M21_H_TABLE + "."
            + OrderHeaderVO.PK_DEPT);// 部门
    whereSql = whereSql.replace(JournalConstant.VFREE, "po_order_b.vfree");// 自由辅助属性
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID, PUEntity.M21_B_TABLE
            + "." + OrderItemVO.CPRODUCTORID);// 生产厂商
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID, PUEntity.M21_B_TABLE + "."
            + OrderItemVO.CPROJECTID);// 项目
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, PUEntity.M21_B_TABLE + "."
            + OrderItemVO.PK_GROUP);// 集团

    return whereSql;
  }
}
