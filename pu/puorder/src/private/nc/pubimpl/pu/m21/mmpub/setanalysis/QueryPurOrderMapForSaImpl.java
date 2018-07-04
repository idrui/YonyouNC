package nc.pubimpl.pu.m21.mmpub.setanalysis;

import nc.pubitf.pu.m21.mmpub.setanalysis.IQueryPurOrderMapForSa;
import nc.pubitf.pu.m21.mmpub.setanalysis.PurchaseOrderSaSupplyMapVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 齐套分析查询供给-采购订单字段映射实现类
 * 
 * @since 6.0
 * @version 2012-11-5 下午04:28:35
 * @author liuyand
 */
public class QueryPurOrderMapForSaImpl implements IQueryPurOrderMapForSa {

  @Override
  public PurchaseOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException {
    try {
      // 来源表
      StringBuilder fromSql = new StringBuilder();
      fromSql.append(" po_order_b po_order_b inner join po_order po_order ");
      fromSql.append(" on po_order_b.pk_order = po_order.pk_order ");
      fromSql.append(" inner join po_potrantype po_potrantype ");
      fromSql.append(" on po_order.ctrantypeid = po_potrantype.ctrantypeid ");
      // 过滤条件
      SqlBuilder whereSql = new SqlBuilder();
      whereSql.append(" po_order_b.dr = 0 and po_order.dr = 0 ");
      // 行关闭为False = “激活=0”
      whereSql.append(" and po_order_b.fisactive = 0 ");
      // 最新版本
      whereSql.append(" and po_order.bislatest", UFBoolean.TRUE);
      // 主数量-累计入库主数量-预留主数量大于零，预留主数量=被预留数量
      whereSql
          .append(" and (po_order_b.nnum - isnull(po_order_b.naccumstorenum,0) - isnull(po_order_b.nsuprsnum,0)) > 0");
      // 非红字单据
      whereSql.append(" and po_order.breturn", UFBoolean.FALSE);

      PurchaseOrderSaSupplyMapVO mapVo = new PurchaseOrderSaSupplyMapVO();
      mapVo.setFromSql(fromSql.toString());
      mapVo.setWhereSql(whereSql.toString());
      // 供给单据类型的编码值
      mapVo.setSupplyTypeCodeValue(POBillType.Order.getCode());
      // 供给单据类型的主键值
      mapVo.setSupplyTypeIdValue(POBillType.Order.getCode());
      // 供给表头主键
      mapVo.setSupplyid("po_order." + OrderHeaderVO.PK_ORDER);
      // 供给表体主键
      mapVo.setSupplybid("po_order_b." + OrderItemVO.PK_ORDER_B);
      // 供给单据行号
      mapVo.setSupplyRowNo("po_order_b." + OrderItemVO.CROWNO);
      // 供给库存组织=收货库存组织
      mapVo.setSupplyOrgid("po_order_b." + OrderItemVO.PK_ARRVSTOORG);
      // 供给库存组织版本
      mapVo.setSupplyOrgvid("po_order_b." + OrderItemVO.PK_ARRVSTOORG_V);
      // 物料主键
      mapVo.setMaterialid("po_order_b." + OrderItemVO.PK_SRCMATERIAL);
      // 物料版本主键
      mapVo.setMaterialvid("po_order_b." + OrderItemVO.PK_MATERIAL);
      // 供给单据号
      mapVo.setSupplyCode("po_order." + OrderHeaderVO.VBILLCODE);
      // 供给日期=计划到货日期
      mapVo.setSupplyDate("po_order_b." + OrderItemVO.DPLANARRVDATE);
      // 单据日期
      mapVo.setDbillDate("po_order." + OrderHeaderVO.DBILLDATE);
      // 主数量
      mapVo.setNnum("po_order_b." + OrderItemVO.NNUM);
      // 执行主数量 “是否进行到货计划安排”=N，执行量=已入库数量，否则执行量=累计到货计划量
      mapVo.setNexenum(this.getNexenumStr());
      // 供给主数量 “是否进行到货计划安排”=N，可供给量=采购订单主数量-已入库数量；否则可供给量=采购订单主数量-累计到货计划量
      mapVo.setNsupplynum(this.getnsupplynumStr());
      // 预留主数量=被预留数量
      mapVo.setReservatioNnum("po_order_b." + OrderItemVO.NSUPRSNUM);
      // 供应商
      mapVo.setVendorid("po_order_b." + OrderItemVO.PK_SUPPLIER);
      // 生产厂商
      mapVo.setProductorid("po_order_b." + OrderItemVO.CPRODUCTORID);
      // 项目
      mapVo.setProjectid("po_order_b." + OrderItemVO.CPROJECTID);
      // 客户
      mapVo.setCustomerid("po_order_b." + OrderItemVO.CASSCUSTID);
      // 自由项1-10
      mapVo.setFree1("po_order_b." + OrderItemVO.VFREE1);
      mapVo.setFree2("po_order_b." + OrderItemVO.VFREE2);
      mapVo.setFree3("po_order_b." + OrderItemVO.VFREE3);
      mapVo.setFree4("po_order_b." + OrderItemVO.VFREE4);
      mapVo.setFree5("po_order_b." + OrderItemVO.VFREE5);
      mapVo.setFree6("po_order_b." + OrderItemVO.VFREE6);
      mapVo.setFree7("po_order_b." + OrderItemVO.VFREE7);
      mapVo.setFree8("po_order_b." + OrderItemVO.VFREE8);
      mapVo.setFree9("po_order_b." + OrderItemVO.VFREE9);
      mapVo.setFree10("po_order_b." + OrderItemVO.VFREE10);

      // 源头单据行ID
      mapVo.setFirstBid("po_order_b." + OrderItemVO.CFIRSTBID);
      // 源头单据号
      mapVo.setFirstCode("po_order_b." + OrderItemVO.VFIRSTCODE);
      // 源头单据ID
      mapVo.setFirstId("po_order_b." + OrderItemVO.CFIRSTID);
      // 源头单据行号
      mapVo.setFirstRowNo("po_order_b." + OrderItemVO.VFIRSTROWNO);
      // 源头单据类型
      mapVo.setFirstType("po_order_b." + OrderItemVO.CFIRSTTYPECODE);
      // 单据状态
      mapVo.setvBillStatus("po_order." + OrderHeaderVO.FORDERSTATUS);
      // 单据状态枚举元数据id
      mapVo.setvBillStatusEnumID(PUMDValue.OrderStatus.value());
      // 来源单据行id
      mapVo.setSrcBid("po_order_b." + OrderItemVO.CSOURCEBID);
      // 来源单据id
      mapVo.setSrcId("po_order_b." + OrderItemVO.CSOURCEID);
      // 来源单据号
      mapVo.setSrcCode("po_order_b." + OrderItemVO.VSOURCECODE);
      // 来源单据行号
      mapVo.setSrcRowNo("po_order_b." + OrderItemVO.VSOURCEROWNO);
      // 来源单据类型
      mapVo.setSrcType("po_order_b." + OrderItemVO.CSOURCETYPECODE);
      // 特征码
      mapVo.setCffileid("po_order_b." + OrderItemVO.CFFILEID);
      // 单据交易类型
      mapVo.setTransType("po_order." + OrderHeaderVO.CTRANTYPEID);
      return mapVo;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 执行主数量 “是否进行到货计划安排”=N，执行量=已入库数量,否则执行量=累计到货计划量
   */
  private String getNexenumStr() {
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

  /**
   * 供给主数量 “是否进行到货计划安排”=N，可供给量=采购订单主数量-已入库数量,否则可供给量=采购订单主数量-累计到货计划量
   */
  private String getnsupplynumStr() {
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
