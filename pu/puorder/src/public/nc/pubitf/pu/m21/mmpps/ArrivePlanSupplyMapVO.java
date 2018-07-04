package nc.pubitf.pu.m21.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 到货计划给离散制造提供的供给单据信息
 * 
 * @since 6.5
 * @version 2014-3-21 上午11:16:02
 * @author fanly3
 */
public class ArrivePlanSupplyMapVO implements ISupplyResult {

  @Override
  public String getBatchCode() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VBATCHCODE;
  }

  @Override
  public String getBoutendflag() {
    // 采购订单没有出库关闭字段,采购订单有入库关闭字段，与制造Chenjij确认了返回入库关闭字段
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.BSTOCKCLOSE;
  }

  @Override
  public String getCastunitid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.CASTUNITID;
  }

  @Override
  public String getCcustmaterialid() {
    return null;
  }

  @Override
  public String getCffileid() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.CFFILEID;
  }

  @Override
  public String getCunitid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.CUNITID;
  }

  @Override
  public String getCustomerid() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.CASSCUSTID;
  }

  @Override
  public String getDbilldate() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.DBILLDATE;
  }

  @Override
  public String getFirstBid() {
    return this.getFirstExp(PUEntity.M21_B_TABLE + "." + OrderItemVO.CFIRSTBID,
        this.getSrcBid());
  }

  @Override
  public String getFirstCode() {
    return this.getFirstExp(
        PUEntity.M21_B_TABLE + "." + OrderItemVO.VFIRSTCODE, this.getSrcCode());
  }

  @Override
  public String getFirstId() {
    return this.getFirstExp(PUEntity.M21_B_TABLE + "." + OrderItemVO.CFIRSTID,
        this.getSrcId());
  }

  @Override
  public String getFirstRowNo() {
    return this.getFirstExp(PUEntity.M21_B_TABLE + "."
        + OrderItemVO.VFIRSTROWNO, this.getSrcRowNo());
  }

  @Override
  public String getFirstType() {
    return this.getFirstExp(PUEntity.M21_B_TABLE + "."
        + OrderItemVO.CFIRSTTYPECODE, this.getSrcType());
  }

  @Override
  public String getFree1() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE1;
  }

  @Override
  public String getFree10() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE10;
  }

  @Override
  public String getFree2() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE2;
  }

  @Override
  public String getFree3() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE3;
  }

  @Override
  public String getFree4() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE4;
  }

  @Override
  public String getFree5() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE5;
  }

  @Override
  public String getFree6() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE6;
  }

  @Override
  public String getFree7() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE7;
  }

  @Override
  public String getFree8() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE8;
  }

  @Override
  public String getFree9() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VFREE9;
  }

  @Override
  public String getFrom() {
    SqlBuilder fromSql = new SqlBuilder();
    fromSql.append(" po_order_b po_order_b inner join po_order po_order ");
    fromSql.append(" on po_order.pk_order = po_order_b.pk_order ");
    fromSql.append(" inner join po_order_bb1 po_order_bb1 ");
    fromSql.append(" on po_order_b.pk_order_b = po_order_bb1.pk_order_b ");
    return fromSql.toString();
  }

  @Override
  public String getMaterialid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_SRCMATERIAL;
  }

  @Override
  public String getMaterialvid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_MATERIAL;
  }

  @Override
  public String getNastnum() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NASTNUM;
  }

  @Override
  public String getNexenum() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NACCUMSTORENUM;
  }

  @Override
  public String getNnum() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NNUM;
  }

  @Override
  public String getPk_BatchCode() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_BATCHCODE;
  }

  @Override
  public String getPprojectid() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.CPROJECTID;
  }

  @Override
  public String getProductorid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.CVENDORID;
  }

  @Override
  public String getReservatioNnum() {
    return null;
  }

  @Override
  public String getSrcBid() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.PK_ORDER_B;
  }

  @Override
  public String getSrcCode() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.VBILLCODE;
  }

  @Override
  public String getSrcId() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.PK_ORDER;
  }

  @Override
  public String getSrcRowNo() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.CROWNO;
  }

  @Override
  public String getSrcType() {
    return "to_char(" + POBillType.Order.getCode() + ")";
  }

  @Override
  public String getSupplybid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_ORDER_BB1;
  }

  @Override
  public String getSupplyCode() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.VBILLCODE;
  }

  @Override
  public String getSupplyDate() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.DPLANARRVDATE;
  }

  @Override
  public String getSupplyid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_ORDER;
  }

  @Override
  public String getSupplyNnum() {
    // 未执行量=订单数量－累计入库量
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NNUM
        + " - isnull(" + PUEntity.M21_BB1_TABLE + "."
        + OrderReceivePlanVO.NACCUMSTORENUM + ", 0)";
  }

  @Override
  public String getSupplyOrgid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_ARRVSTOORG;
  }

  @Override
  public String getSupplyOrgvid() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.PK_ARRVSTOORG_V;
  }

  @Override
  public String getSupplyRowNo() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VBILLCODE;
  }

  @Override
  public String getSupplyTypeCodeValue() {
    return "to_char(" + POBillType.Order.getCode() + ")";
  }

  @Override
  public String getSupplyTypeIdValue() {
    return "to_char(" + POBillType.Order.getCode() + ")";
  }

  @Override
  public String getTranType() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.CTRANTYPEID;
  }

  @Override
  public String getVBillStatus() {
    return PUEntity.M21_H_TABLE + "." + OrderHeaderVO.FORDERSTATUS;
  }

  @Override
  public String getVBillStatusEnumID() {
    return PUMDValue.OrderStatus.value();
  }

  @Override
  public String getVchangerate() {
    return PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.VCHANGERATE;
  }

  @Override
  public String getVendorid() {
    return PUEntity.M21_B_TABLE + "." + OrderItemVO.PK_SUPPLIER;
  }

  @Override
  public String getWhere() {
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append("po_order.dr", 0);
    whereSql.append(" and ");
    whereSql.append("po_order_b.dr", 0);
    whereSql.append(" and ");
    whereSql.append("po_order_bb1.dr", 0);
    whereSql.append(" and ");
    whereSql.append("po_order.bislatest", "Y");// 最新版本
    whereSql.append(" and ");
    whereSql.append("po_order_b.bstockclose", "N");// 未入库关闭
    whereSql.append(" and ");
    whereSql.append("po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0)",
        ">", 0);
    whereSql.append(" and ");
    whereSql.append("po_order.breturn", "N");
    return whereSql.toString();
  }

  private String getFirstExp(String firstField, String srcField) {
    SqlBuilder str = new SqlBuilder();
    SqlBuilder condition = new SqlBuilder();
    SqlBuilder trueExpression = new SqlBuilder();
    SqlBuilder falseExpression = new SqlBuilder();
    condition.append("isnull(" + firstField + ",'~') = '~'");
    trueExpression.append(srcField);
    falseExpression.append(firstField);
    str.appendCaseWhen(condition.toString(), trueExpression.toString(),
        falseExpression.toString());
    return str.toString();
  }

}
