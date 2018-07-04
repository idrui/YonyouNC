package nc.vo.pu.m21.enumeration;

import nc.vo.ct.entity.CtAbstractBVO;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ic.m49.entity.BorrowInBodyVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.pp.m28.entity.PriceAuditHeaderVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.PPBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

/**
 * 请购单走表体订单类型，不走物料订单类型
 * 合同：有物料基本分类
 * 价格审批单：表体库存组织走请购单，需要单独处理
 * 离散生产订单
 * 流程生产订单
 * 计划订单
 * 库存计划订单
 * 库存借入单
 * 退货单
 * 退库单
 * 销售订单补货
 * 销售订单直运（需要判断直运字段），不为直运需要清空
 * 调拨订单
 * 销售订单协同，不走
 * 电子采购补货申请
 * 电子采购排程
 * 
 * @since 6.0
 * @version 2012-12-5 下午01:43:03
 * @author wuxla
 */
public enum SourceBillEnum {
  /** 退货单 */
  BACKARRIVE(POBillType.Arrive.getCode(), ArriveHeaderVO.CTRANTYPEID,
      ArriveHeaderVO.PK_PURCHASEORG, ArriveItemVO.PK_REQSTOORG,
      ArriveItemVO.PK_SRCMATERIAL, null),
  /** 补货申请 */
  // 先不加依赖
  EC38(ECBillType.EC38.getCode(), null, "pk_org", null, "pk_product", null),
  /** 排程结果 */
  // 先不加依赖
  EC49(ECBillType.EC49.getCode(), null, "pk_org", null, "pk_prod", null),
  /** 库存借入单 */
  ICBORROWIN(ICBillType.BorrowIn.getCode(), ICPubMetaNameConst.CTRANTYPEID,
      BorrowInBodyVO.CPURORGOID, ICPubMetaNameConst.PK_ORG,
      ICPubMetaNameConst.CMATERIALOID, null),
  /** 库存计划订单 */
  InvpPoOrder(INVPBillType.PoOrder.getCode(), null,
      nc.vo.invp.po.entity.PoVO.PK_DEST_PUORG,
      nc.vo.invp.po.entity.PoVO.PK_ORG, nc.vo.invp.po.entity.PoVO.CMATERIALOID,
      null),
  /** 离散生产订单 */
  LsProduceOrder(MMBillType.LsProduceOrder.getCode(), DmoVO.VTRANTYPEID,
      DmoVO.PK_PUORG, DmoVO.PK_ORG, DmoVO.CMATERIALID, null),
  /** 计划订单 */
  PlanOrder(MMBillType.PlanOrder.getCode(), null,
      nc.vo.mmpps.mps0202.PoVO.PK_PUORG, nc.vo.mmpps.mps0202.PoVO.PK_ORG,
      nc.vo.mmpps.mps0202.PoVO.CMATERIALID, null),
  /** 价格审批单 */
  PriceAudit(PPBillType.PriceAudit.getCode(), null, PriceAuditHeaderVO.PK_ORG,
      null, PriceAuditItemVO.MATERIAL_SAVE, null),
  /** 流程生产订单 */
  ProduceOrder(MMBillType.ProduceOrder.getCode(), PMOHeadVO.CTRANTYPEID,
      PMOItemVO.TEMP_PUORG, PMOItemVO.PK_ORG, PMOItemVO.CMATERIALID, null),
  /** 采购合同 */
  PURCT(CTBillType.PurDaily.getCode(), CtAbstractVO.CTRANTYPEID,
      CtAbstractVO.PK_BILLORG, null, CtAbstractBVO.PK_SRCMATERIAL,
      CtAbstractBVO.PK_MARBASCLASS),
  /** 销售订单 */
  SALEORDER(SOBillType.Order.getCode(), SaleOrderHVO.CTRANTYPEID,
      SaleOrderHVO.DEST_PK_ORG, SaleOrderBVO.CSENDSTOCKORGID,
      SaleOrderBVO.CMATERIALID, null),

  /** 调拨订单 */
  TransOrder(TOBillType.TransOrder.getCode(), BillHeaderVO.CTRANTYPEID,
      BillItemVO.CPURCHASEORG, BillHeaderVO.CTOUTSTOCKORGID,
      BillItemVO.CINVENTORYID, null);
  private String srcBilltype;

  private String srcMaterialBaseClassField;

  private String srcMaterialoidField;

  private String srcPurorgField;

  private String srcStockorgField;

  private String srcTrantypeidField;

  private SourceBillEnum(String srcBilltype, String srcTrantypeidField,
      String srcPurorgField, String srcStockorgField,
      String srcMaterialoidField, String srcMaterialBaseClassField) {
    this.srcBilltype = srcBilltype;
    this.srcTrantypeidField = srcTrantypeidField;
    this.srcPurorgField = srcPurorgField;
    this.srcStockorgField = srcStockorgField;
    this.srcMaterialoidField = srcMaterialoidField;
    this.srcMaterialBaseClassField = srcMaterialBaseClassField;
  }

  public String getSrcBilltype() {
    return this.srcBilltype;
  }

  public String getSrcMaterialBaseClassField() {
    return this.srcMaterialBaseClassField;
  }

  public String getSrcMaterialoidField() {
    return this.srcMaterialoidField;
  }

  public String getSrcPurorgField() {
    return this.srcPurorgField;
  }

  public String getSrcStockorgField() {
    return this.srcStockorgField;
  }

  public String getSrcTrantypeidField() {
    return this.srcTrantypeidField;
  }
}
