/**
 * 该报表最大的难点在于主数量 = 当前状态主数量之和 - 下一状态主数量之和，大致实现思路如下：
 * 查出有在途的订单表体数据union在途数据，组成结果集a，
 * 查询时，使用分析函数lead，取出结果集中a的下一行，用a的数量 - 下一行的数量，就是我们需要的数值。
 *  
 *  
 *  示例SQL如下，仅供参考。（示例使用的Oracle语法）
select po_order.pk_order, po_order.pk_org_v, po_order.vbillcode, po_order.dbilldate,po_order.pk_supplier,
    po_order.cemployeeid, po_order.nversion, po_order.bisreplenish, po_order.breturn, po_order_b.crowno, po_order_b.blargess, 
    po_order_b.pk_material, po_order_b.nqttaxnetprice, po_order_b.dplanarrvdate, po_order_b.cunitid, po_order_b.ccurrencyid,
    b.nnum, b.fonwaystatus, po_order_b.nqttaxnetprice * b.nnum ntaxmny from (
        select a.pk_order_b, a.fonwaystatus, a.nnum - nvl(lead(a.nnum, 1) over (partition by a.pk_order_b order by a.fonwaystatus), 0) nnum from (
            -- 查询有在途状态的、审批态的，并且交易类型的在途开始状态是审批的订单表体
            select po_order_b.pk_order_b, 0 fonwaystatus, po_order_b.nnum nnum from po_order_b po_order_b
            inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.dr = 0 and po_order.bislatest = 'Y' and forderstatus = 3
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where po_order_b.dr = 0 and po_potrantype.ionwaybegin = 0 and po_order.pk_org = '0001A510000000003Z19'
            union all
            -- 查询在途表，取得本集团内，已经执行的在途数量
            select po_order_bb.pk_order_b, po_order_bb.fonwaystatus, sum(nonwaynum) nnum from po_order_bb
            inner join po_order_b po_order_b on po_order_bb.pk_order_b = po_order_b.pk_order_b
            inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where isoperated = 'Y' and po_order_b.dr = 0 and po_order_bb.dr = 0 and po_order_bb.pk_org='0001A510000000003Z19'
            group by po_order_bb.pk_order_b, po_order_bb.fonwaystatus
            union all
            -- 联合到货表，因为到货单不会回写在途表
            select po_arriveorder_b.pk_order_b, 7 fonwaystatus, sum(po_arriveorder_b.nnum) nnum from po_arriveorder_b
            inner join po_arriveorder on po_arriveorder_b.pk_arriveorder = po_arriveorder.pk_arriveorder
            inner join po_order po_order on po_order.pk_order = po_arriveorder_b.pk_order and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where po_arriveorder_b.dr = 0 and po_arriveorder.fbillstatus = 3 and po_order.pk_org='0001A510000000003Z19'
            group by po_arriveorder_b.pk_order_b
            union all
            -- 联合入库表，因为采购入库单不会回写在途表
            select ic_purchasein_b.cfirstbillbid, 8 fonwaystatus, sum(nnum) from ic_purchasein_b
            inner join ic_purchasein_h on ic_purchasein_b.cgeneralhid = ic_purchasein_h.cgeneralhid
            inner join po_order po_order on po_order.pk_order = ic_purchasein_b.cfirstbillhid and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where ic_purchasein_b.dr = 0 and ic_purchasein_h.fbillflag = 3 and po_order.pk_org='0001A510000000003Z19'
            group by ic_purchasein_b.cfirstbillbid
        ) a
    ) b
inner join po_order_b po_order_b on b.pk_order_b = po_order_b.pk_order_b
inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y'
inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0

-- 以下为可选的一些连接
--inner join bd_supplier on po_order.pk_supplier = bd_supplier.pk_supplier
--inner join bd_areacl on bd_supplier.pk_areacl = bd_areacl.pk_areacl
--inner join bd_material_v on po_order_b.pk_srcmaterial = bd_material_v.pk_source
--inner join bd_marbasclass on bd_material_v.pk_marbasclass = bd_marbasclass.pk_marbasclass
--inner join bd_materialstock on bd_materialstock.pk_material = bd_material_v.pk_material
--inner join bd_marpuclass on bd_marpuclass.pk_marpuclass = bd_marpuclass.pk_marpuclass

where b.nnum <> 0 and fonwaystatus >= po_potrantype.ionwaybegin and fonwaystatus < po_potrantype.ionwayend -- 开始状态之前的和结束状态及以后的不显示
and not exists (
    select 1 from po_order_b ib
    where ib.pk_order_b = po_order_b.pk_order_b and (
        (po_potrantype.ionwayend = 7 and po_order_b.barriveclose = 'Y') or  
        (po_potrantype.ionwayend = 8 and po_order_b.bstockclose = 'Y') or  
        (po_potrantype.ionwayend = 8 and po_order_b.barriveclose = 'Y' and b.fonwaystatus <> 7)
    )
)

-- 在这里拼接界面上的查询条件
-- and ......
 */

package nc.bs.pu.report.order;

import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.report.enumeration.OrderOnwayQueryFieldCode;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.order.OrderOnwayQueryView;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 拼接订单在途状态查询SQL的BP类。
 * 
 * @since 6.3
 * @version 2012-8-23 下午06:49:06
 * @author lixyp
 */
public class OrderOnwayQueryRptBP {

  private String orgOperCode = null;

  private String pk_org = null;

  private SqlBuilder sqlBuilder = new SqlBuilder();

  /**
   * 获取SQL。最终形成的SQL可参考最上面的文件注释。
   * 
   * @param context 语义模型上下文
   * @return 查询SQL
   */
  public String getQuerySql(SmartContext context) {
    this.pk_org = (String) context.getAttribute("#pk_org#");
    this.orgOperCode = (String) context.getAttribute("#orgOperCode#");
    ConditionVO[] conds= (ConditionVO[])context.getAttribute(PuQueryInfoPara.QUERY_CONDS);
    OrderOnwayQueryView view = new OrderOnwayQueryView(conds, context);
    this.sqlBuilder.append("select ");
    this.appendField("head", OrderHeaderVO.PK_ORDER);
    this.appendField("head", OrderHeaderVO.PK_ORG);
    this.appendField("head", OrderHeaderVO.PK_ORG_V);
    this.appendField("head", OrderHeaderVO.VBILLCODE);
    this.appendField("head", OrderHeaderVO.DBILLDATE);
    this.appendField("head", OrderHeaderVO.PK_SUPPLIER);
    this.appendField("head", OrderHeaderVO.CEMPLOYEEID);
    this.appendField("head", OrderHeaderVO.NVERSION);
    this.appendField("head", OrderHeaderVO.BISREPLENISH);
    this.appendField("head", OrderHeaderVO.BRETURN);
    this.appendField("head", OrderItemVO.CROWNO);
    this.appendField("head", OrderItemVO.BLARGESS);
    this.appendField("head", OrderItemVO.PK_MATERIAL);
    this.appendField("head", OrderItemVO.NQTTAXNETPRICE);
    this.appendField("head", OrderItemVO.DPLANARRVDATE);
    this.appendField("head", OrderItemVO.CUNITID);
    this.appendField("head", OrderItemVO.CCURRENCYID);
    this.appendField("b", OrderItemVO.NNUM);
    this.appendField("b", OrderOnwayItemVO.FONWAYSTATUS);
    this.sqlBuilder.append(" head.nqttaxnetprice * b.nnum ntaxmny ");
    this.sqlBuilder.append(" from (");
    this.buildSubQueryB();
    this.sqlBuilder.append(") b ");
    String headSql = view.getViewSqlName();
    this.processJoin(headSql);
    this.processWhere();

    return this.sqlBuilder.toString();
  }

  /**
   * 添加查询字段。
   * 
   * @param table 表名
   * @param field 字段名
   */
  private void appendField(String table, String field) {
    this.appendField(table, field, null);
  }

  /**
   * 添加查询字段。
   * 
   * @param table 表名
   * @param field 字段名
   * @param alias 别名
   */
  private void appendField(String table, String field, String alias) {
    if (table != null) {
      this.sqlBuilder.append(table);
      this.sqlBuilder.append(".");
    }
    this.sqlBuilder.append(field);

    if (alias != null) {
      this.sqlBuilder.append(" ");
      this.sqlBuilder.append(alias);
    }

    this.sqlBuilder.append(",");
  }

  /**
   * 拼接组织字段的SQL片段。
   * 因为前台拿到的组织字段单选和多选处理方式不一样。所以提出一个方法统一处理。
   * 
   * @param field 组织字段SQL
   */
  private void appendOrgField(String field) {
    // 在语义模型定义时可能为空
    if (this.pk_org != null) {
      this.sqlBuilder.append(field);
      if ("=".equals(this.orgOperCode)) {
        this.sqlBuilder.append(this.orgOperCode);
        this.sqlBuilder.append("'");
        this.sqlBuilder.append(this.pk_org);
        this.sqlBuilder.append("'");
      }
      else {
        this.sqlBuilder.append(this.orgOperCode);
        this.sqlBuilder.append(this.pk_org);
      }
    }
  }

  /**
   * 构造到货单查询。查询具有在途交易类型的、已经审批的订单下游到货单。
   */
  private void buildArriveQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M23_B_TABLE, ArriveItemVO.PK_ORDER_B);
    this.appendField(null, "7", "fonwaystatus");
    this.appendField(null, "sum(po_arriveorder_b.nnum)", "nnum");
    this.sqlBuilder.deleteLastChar(); // 删除最后一个逗号

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M23_B_TABLE);
    this.sqlBuilder
        .append(" inner join po_arriveorder on po_arriveorder_b.pk_arriveorder = po_arriveorder.pk_arriveorder");
    this.sqlBuilder
        .append(" inner join po_order po_order on po_order.pk_order = po_arriveorder_b.pk_order and po_order.bislatest='Y' ");
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where po_arriveorder_b.dr = 0 and po_arriveorder.fbillstatus = ");
    this.sqlBuilder.append(POEnumBillStatus.APPROVE.toInteger());
    this.appendOrgField(" and po_order.pk_org ");

    this.sqlBuilder.append(" group by po_arriveorder_b.pk_order_b");
  }

  /**
   * 从在途表中查询已经执行的在途数据。
   */
  private void buildOnwayQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M21_BB_TABLE, OrderOnwayItemVO.PK_ORDER_B);
    this.appendField(PUEntity.M21_BB_TABLE, OrderOnwayItemVO.FONWAYSTATUS);
    this.appendField(null, "sum(nonwaynum)", "nnum");
    this.sqlBuilder.deleteLastChar();

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M21_BB_TABLE);
    this.sqlBuilder.append(" inner join po_order_b po_order_b on");
    this.sqlBuilder.append(" po_order_bb.pk_order_b = po_order_b.pk_order_b");
    this.sqlBuilder.append(" inner join po_order po_order on");
    this.sqlBuilder
        .append(" po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y' ");
    this.sqlBuilder.append(" inner join po_potrantype on");
    this.sqlBuilder
        .append(" po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> ");
    this.sqlBuilder.append(OnwayStatus.STATUS_AUDIT.toInteger());
    this.sqlBuilder.append(" where isoperated = 'Y' and po_order_b.dr = 0 ");
    this.sqlBuilder.append(" and po_order_bb.dr = 0 ");
    this.appendOrgField(" and po_order_bb.pk_org ");

    this.sqlBuilder
        .append(" group by po_order_bb.pk_order_b, po_order_bb.fonwaystatus");
  }

  /**
   * 构造订单查询。查询具有在途交易类型的、已经审批的、且交易类型的在途开始状态是审批的订单数据。
   */
  private void buildOrderQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M21_B_TABLE, OrderItemVO.PK_ORDER_B);
    this.appendField(null, "0", "fonwaystatus");
    this.appendField(PUEntity.M21_B_TABLE, OrderItemVO.NNUM, "nnum");
    this.sqlBuilder.deleteLastChar(); // 删除最后一个逗号

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M21_B_TABLE);
    this.sqlBuilder
        .append(" po_order_b inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.dr = 0 and po_order.bislatest='Y' and ");
    this.sqlBuilder.append(OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where po_order_b.dr = 0 and po_potrantype.ionwaybegin = 0 ");
    this.appendOrgField(" and po_order.pk_org ");
  }

  /**
   * 构造到货单查询。查询具有在途交易类型的、已经审批的订单下游采购入库单。
   */
  private void buildPurchaseinQuery() {
    this.sqlBuilder.append("select ic_purchasein_b.cfirstbillbid, ");
    this.sqlBuilder.append("8 fonwaystatus, sum(nnum) nnum ");
    this.sqlBuilder.append(" from ic_purchasein_b ");
    this.sqlBuilder
        .append(" inner join ic_purchasein_h on ic_purchasein_b.cgeneralhid = ic_purchasein_h.cgeneralhid");
    this.sqlBuilder
        .append(" inner join po_order po_order on po_order.pk_order = ic_purchasein_b.cfirstbillhid and po_order.bislatest='Y' ");
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where ic_purchasein_b.dr = 0 and ic_purchasein_h.fbillflag = 3 ");
    this.appendOrgField(" and po_order.pk_org ");

    this.sqlBuilder.append(" group by ic_purchasein_b.cfirstbillbid ");
  }

  /**
   * 构造子查询b。<br />
   * 关于a,b两个子查询和SQL实现思路，请参考最上面的文件注释。
   */
  private void buildSubQueryB() {
    this.sqlBuilder.append(" select a.pk_order_b,a.fonwaystatus, ");
    this.sqlBuilder
        .append("a.nnum - isnull(lead(a.nnum, 1) over (partition by a.pk_order_b order by a.fonwaystatus), 0) nnum ");

    // 构造子查询a
    this.sqlBuilder.append("from (");
    this.buildOrderQuery();
    this.sqlBuilder.append(" union all ");
    this.buildOnwayQuery();
    this.sqlBuilder.append(" union all ");
    this.buildArriveQuery();
    this.sqlBuilder.append(" union all ");
    this.buildPurchaseinQuery();
    this.sqlBuilder.append(") a");
  }

  /**
   * 处理子查询b的连接，这里和前台的查询条件相关。
   * @param headSql 
   */
  private void processJoin(String headSql) {
    // 必需的连接。
    this.sqlBuilder
        .append(" inner join (" + headSql + ") head on b.pk_order_b = head.pk_order_b");


  }

  /**
   * 处理子查询b的条件，这里和前台的查询条件相关。
   */
  private void processWhere() {
    this.sqlBuilder.append(" where ");
    this.sqlBuilder.append(" b.nnum <> 0 ");
    this.sqlBuilder.append(" and b.fonwaystatus >= head.ionwaybegin ");
    this.sqlBuilder.append(" and b.fonwaystatus < head.ionwayend ");
    this.sqlBuilder.append(" and not exists (");
    this.sqlBuilder.append(" select 1 from po_order_b ib ");
    this.sqlBuilder.append(" where ib.pk_order_b = head.pk_order_b and(");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_ARRIVE.toInteger());
    this.sqlBuilder.append(" and head.barriveclose = 'Y') or ");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_STORE.toInteger());
    this.sqlBuilder.append(" and head.bstockclose = 'Y') or ");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_STORE.toInteger());
    this.sqlBuilder.append(" and head.barriveclose = 'Y' ");
    this.sqlBuilder.append(" and b.fonwaystatus <> ");
    this.sqlBuilder.append(OnwayStatus.STATUS_ARRIVE.toInteger());
    this.sqlBuilder.append(")))");

//    if (!StringUtils.isEmpty(this.whereClause)) {
//      this.sqlBuilder.append(" and ");
//      this.sqlBuilder.append(this.whereClause);
//    }
  }
}
