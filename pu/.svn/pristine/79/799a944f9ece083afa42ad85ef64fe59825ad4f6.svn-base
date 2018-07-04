package nc.pubitf.pu.m21.mmpps.lotreg;

import nc.pubitf.mmpub.sdmanage.IBillTraceResult;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购定提供给"供需预订维护"节点的单据追溯返回信息
 * 
 * @since 6.3.1
 * @version 2013-7-30下午08:43:00
 * @author fanly3
 */
public class OrderBillTraceResult implements IBillTraceResult {

  private String fromSql;

  private String whereSql;

  public void setFromSql(String fromSql) {
    this.fromSql = fromSql;
  }

  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  @Override
  public String getFrom() {
    return this.fromSql;
  }

  @Override
  public String getWhere() {
    return this.whereSql;
  }

  @Override
  public String getSrcBid() {
    return "po_order_b." + OrderItemVO.CSOURCEBID;
  }

  @Override
  public String getSrcId() {
    return "po_order_b." + OrderItemVO.CSOURCEID;
  }

  @Override
  public String getSrcType() {
    return "po_order_b." + OrderItemVO.CSOURCETYPECODE;
  }

  @Override
  public String getBillBid() {
    return "po_order_b." + OrderItemVO.PK_ORDER_B;
  }

  @Override
  public String getBillId() {
    return "po_order_b." + OrderHeaderVO.PK_ORDER;
  }

  @Override
  public String getBillType() {
    return POBillType.Order.getCode();
  }

}
