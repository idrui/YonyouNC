package nc.pubitf.pu.m21.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购订单给离散制造提供的供给单据信息
 * 
 * @since 6.3.1
 * @version 2013-7-30下午08:14:29
 * @author fanly3
 */
public class OrderBillSupplyMapVO implements ISupplyResult {
  // private boolean includeRed;
  //
  // public OrderBillSupplyMapVO(boolean includeRed) {
  // this.includeRed = includeRed;
  // }

  @Override
  public String getBatchCode() {
    return "po_order_b." + OrderItemVO.VBATCHCODE;
  }

  @Override
  public String getBoutendflag() {
    // 采购订单没有出库关闭字段,采购订单有入库关闭字段，与制造Chenjij确认了返回入库关闭字段
    return "po_order_b." + OrderItemVO.BSTOCKCLOSE;
  }

  @Override
  public String getCastunitid() {
    return "po_order_b." + OrderItemVO.CASTUNITID;
  }

  @Override
  public String getCcustmaterialid() {
    return null;
  }

  @Override
  public String getCffileid() {
    return "po_order_b." + OrderItemVO.CFFILEID;
  }

  @Override
  public String getCunitid() {
    return "po_order_b." + OrderItemVO.CUNITID;
  }

  @Override
  public String getCustomerid() {
    return "po_order_b." + OrderItemVO.CASSCUSTID;
  }

  @Override
  public String getDbilldate() {
    return "po_order." + OrderHeaderVO.DBILLDATE;
  }

  @Override
  public String getFirstBid() {
    return "po_order_b." + OrderItemVO.CFIRSTBID;
  }

  @Override
  public String getFirstCode() {
    return "po_order_b." + OrderItemVO.VFIRSTCODE;
  }

  @Override
  public String getFirstId() {
    return "po_order_b." + OrderItemVO.CFIRSTID;
  }

  @Override
  public String getFirstRowNo() {
    return "po_order_b." + OrderItemVO.VFIRSTROWNO;
  }

  @Override
  public String getFirstType() {
    return "po_order_b." + OrderItemVO.CFIRSTTYPECODE;
  }

  @Override
  public String getFree1() {
    return "po_order_b." + OrderItemVO.VFREE1;
  }

  @Override
  public String getFree10() {
    return "po_order_b." + OrderItemVO.VFREE10;
  }

  @Override
  public String getFree2() {
    return "po_order_b." + OrderItemVO.VFREE2;
  }

  @Override
  public String getFree3() {
    return "po_order_b." + OrderItemVO.VFREE3;
  }

  @Override
  public String getFree4() {
    return "po_order_b." + OrderItemVO.VFREE4;
  }

  @Override
  public String getFree5() {
    return "po_order_b." + OrderItemVO.VFREE5;
  }

  @Override
  public String getFree6() {
    return "po_order_b." + OrderItemVO.VFREE6;
  }

  @Override
  public String getFree7() {
    return "po_order_b." + OrderItemVO.VFREE7;
  }

  @Override
  public String getFree8() {
    return "po_order_b." + OrderItemVO.VFREE8;
  }

  @Override
  public String getFree9() {
    return "po_order_b." + OrderItemVO.VFREE9;
  }

  @Override
  public String getFrom() {
    StringBuilder fromSql = new StringBuilder();
    fromSql.append(" po_order_b po_order_b inner join po_order po_order ");
    fromSql.append(" on po_order.pk_order = po_order_b.pk_order ");
    fromSql.append(" inner join po_potrantype po_potrantype ");
    fromSql.append(" on po_order.ctrantypeid = po_potrantype.ctrantypeid ");
    return fromSql.toString();
  }

  @Override
  public String getMaterialid() {
    return "po_order_b." + OrderItemVO.PK_SRCMATERIAL;
  }

  @Override
  public String getMaterialvid() {
    return "po_order_b." + OrderItemVO.PK_MATERIAL;
  }

  @Override
  public String getNastnum() {
    return "po_order_b." + OrderItemVO.NASTNUM;
  }

  @Override
  public String getNexenum() {
    SqlBuilder sql = new SqlBuilder();
    SqlBuilder condition = new SqlBuilder();
    SqlBuilder trueExpression = new SqlBuilder();
    SqlBuilder falseExpression = new SqlBuilder();
    condition.append("po_potrantype." + PoTransTypeVO.BRECEIVEPLAN, "Y");
    trueExpression.append("po_order_b." + OrderItemVO.NACCUMRPNUM);
    falseExpression.append("po_order_b." + OrderItemVO.NACCUMSTORENUM);
    sql.appendCaseWhen(condition.toString(), trueExpression.toString(),
        falseExpression.toString());
    return sql.toString();
  }

  @Override
  public String getNnum() {
    return "po_order_b." + OrderItemVO.NNUM;
  }

  @Override
  public String getPk_BatchCode() {
    return "po_order_b." + OrderItemVO.PK_BATCHCODE;
  }

  @Override
  public String getPprojectid() {
    return "po_order_b." + OrderItemVO.CPROJECTID;
  }

  @Override
  public String getProductorid() {
    return "po_order_b." + OrderItemVO.CPRODUCTORID;
  }

  @Override
  public String getReservatioNnum() {
    return "po_order_b." + OrderItemVO.NSUPRSNUM;
  }

  @Override
  public String getSrcBid() {
    return "po_order_b." + OrderItemVO.CSOURCEBID;
  }

  @Override
  public String getSrcCode() {
    return "po_order_b." + OrderItemVO.VSOURCECODE;
  }

  @Override
  public String getSrcId() {
    return "po_order_b." + OrderItemVO.CSOURCEID;
  }

  @Override
  public String getSrcRowNo() {
    return "po_order_b." + OrderItemVO.VSOURCEROWNO;
  }

  @Override
  public String getSrcType() {
    return "po_order_b." + OrderItemVO.CSOURCETYPECODE;
  }

  @Override
  public String getSupplybid() {
    return "po_order_b." + OrderItemVO.PK_ORDER_B;
  }

  @Override
  public String getSupplyCode() {
    return "po_order." + OrderHeaderVO.VBILLCODE;
  }

  @Override
  public String getSupplyDate() {
    return "po_order_b." + OrderItemVO.DPLANARRVDATE;
  }

  @Override
  public String getSupplyid() {
    return "po_order." + OrderHeaderVO.PK_ORDER;
  }

  @Override
  public String getSupplyNnum() {
    // modified by fanly3 at 2014-03-24 65制造需求变化
    // 采购订单未执行量计算
    // 1) 如果交易类型走到货计划
    // 采购订单.供给量=订单量-累计到货计划量
    // 2) 如果交易类型不走到货计划
    // 采购订单.供给量=订单量-累计入库量
    String sql = this.getSupplyNnumSql();
    return sql;
  }

  @Override
  public String getSupplyOrgid() {
    return "po_order_b." + OrderItemVO.PK_ARRVSTOORG;
  }

  @Override
  public String getSupplyOrgvid() {
    return "po_order_b." + OrderItemVO.PK_ARRVSTOORG_V;
  }

  @Override
  public String getSupplyRowNo() {
    return "po_order_b." + OrderItemVO.CROWNO;
  }

  @Override
  public String getSupplyTypeCodeValue() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getSupplyTypeIdValue() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getTranType() {
    return "po_order." + OrderHeaderVO.CTRANTYPEID;
  }

  @Override
  public String getVBillStatus() {
    return "po_order." + OrderHeaderVO.FORDERSTATUS;
  }

  @Override
  public String getVBillStatusEnumID() {
    return PUMDValue.OrderStatus.value();
  }

  @Override
  public String getVchangerate() {
    return "po_order_b." + OrderItemVO.VCHANGERATE;
  }

  @Override
  public String getVendorid() {
    return "po_order_b." + OrderItemVO.PK_SUPPLIER;
  }

  @Override
  public String getWhere() {
    StringBuilder whereSql = new StringBuilder();
    whereSql.append(" po_order.dr = 0 and po_order_b.dr = 0 ");
    whereSql.append(" and po_order.bislatest = 'Y' ");// 最新版本
    whereSql.append(" and po_order_b.bstockclose = 'N' ");// 未入库关闭
    // includeRed 是否查询退库的单据
    // 与离散制造邓双伟确认过了，该字段没用，一直就是false，所以我这边直接按false处理了
    // if (includeRed) {
    // whereSql
    // .append("and ( ( po_order_b.nnum > 0 "
    // +
    // "and po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) > 0 ) ");
    // whereSql
    // .append("or ( po_order_b.nnum < 0 "
    // +
    // "and  po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) < 0 ) ");
    // whereSql.append(" )");
    // }
    // else {

    whereSql.append(" and (");
    whereSql.append(this.getSupplyNnumSql());
    whereSql.append(") > 0");

    // 2013-11-01 chenjij邮件 请将过滤条件中减去预留量的条件去掉，即判断“未执行量>0”时，不需要减预留量。
    // whereSql
    // .append(" and po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) > 0 ");
    whereSql.append(" and po_order.breturn = 'N' ");
    // }
    return whereSql.toString();
  }

  /**
   * 构造未执行量 的case when语句sql
   * 
   * @return
   */
  private String getSupplyNnumSql() {
    SqlBuilder sql = new SqlBuilder();
    SqlBuilder condition = new SqlBuilder();
    SqlBuilder trueExpression = new SqlBuilder();
    SqlBuilder falseExpression = new SqlBuilder();
    condition.append("po_potrantype." + PoTransTypeVO.BRECEIVEPLAN, "Y");
    trueExpression.append("po_order_b." + OrderItemVO.NNUM
        + " - isnull(po_order_b." + OrderItemVO.NACCUMRPNUM + ",0)");
    falseExpression.append("po_order_b." + OrderItemVO.NNUM
        + " - isnull(po_order_b." + OrderItemVO.NACCUMSTORENUM + ",0)");
    sql.appendCaseWhen(condition.toString(), trueExpression.toString(),
        falseExpression.toString());
    return sql.toString();
  }
}
