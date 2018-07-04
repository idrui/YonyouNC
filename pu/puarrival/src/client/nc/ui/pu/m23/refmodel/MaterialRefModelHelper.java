package nc.ui.pu.m23.refmodel;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

public class MaterialRefModelHelper {
  private BillCardPanel card;

  private boolean isFromSC;

  private String pk_stockorg;

  private int rowNo;

  private String sourceBid;

  public MaterialRefModelHelper(BillCardPanel card, int rowNo) {
    this.card = card;
    this.rowNo = rowNo;

    String sourceType =
        ArriveClientUtil.getStringCellValue(card, rowNo,
            ArriveItemVO.CSOURCETYPECODE).trim();
    this.isFromSC = SCBillType.Order.getCode().equals(sourceType);

    this.sourceBid =
        ArriveClientUtil.getStringCellValue(card, rowNo,
            ArriveItemVO.CSOURCEBID).trim();

    this.pk_stockorg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveItemVO.PK_ORG);
  }

  public String getPk_stockorg() {
    return this.pk_stockorg;
  }

  public String getSourceMaterialPK() {
    String pk_material;
    if (this.isFromSC) {
      pk_material = this.getSCOrderMaterial();
    }
    else {
      pk_material = this.getPOOrderMaterial();
    }
    return pk_material;
  }

  public String getSourceSubQuery() {
    StringBuilder builder = new StringBuilder();
    if (this.isFromSC) {
      builder.append(" (select pk_material from sc_order_b");
      builder.append(" where pk_order_b ='").append(this.sourceBid)
          .append("')");
    }
    else {
      builder.append(" (select pk_material from po_order_b");
      builder.append(" where pk_order_b ='").append(this.sourceBid)
          .append("')");
    }
    return builder.toString();
  }

  public boolean isQueryAllMaterial() {
    // 当前行是否赠品、来源订单是否赠品
    boolean isPresent =
        ArriveClientUtil.getBooleanCellValue(this.card, this.rowNo,
            ArriveItemVO.BPRESENT);
    boolean isPresentSource =
        ArriveClientUtil.getBooleanCellValue(this.card, this.rowNo,
            ArriveItemVO.BPRESENTSOURCE);

    if (!isPresentSource && isPresent) {
      // 物料参照 = 所有物料（含订单物料＋替换件）
      return true;
    }
    // 物料参照 = 订单物料＋替换件
    return false;
  }

  private String getPOOrderMaterial() {
    OrderItemVO[] orderItemArray = null;
    try {
      IOrderPubQuery serv =
          NCLocator.getInstance().lookup(IOrderPubQuery.class);
      orderItemArray = serv.queryOrderItem(new String[] {
        this.sourceBid
      });
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (ArrayUtils.isEmpty(orderItemArray) || (orderItemArray == null)) {
      return null;
    }
    return orderItemArray[0].getPk_material();
  }

  private String getSCOrderMaterial() {
    return null;
  }
}
