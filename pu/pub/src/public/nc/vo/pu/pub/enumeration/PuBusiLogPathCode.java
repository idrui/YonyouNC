package nc.vo.pu.pub.enumeration;

public enum PuBusiLogPathCode {
  /**
   * 到货单维护action接口路径
   */
  arriveMaintainPath("nc.itf.pu.m23.maintain.IArriveMaintain"),
  /**
   * 暂估审批action接口路径
   */
  initialEstApprovePath("nc.itf.pu.m4t.IInitialEstApprove"),

  /**
   * 发票审批action接口路径
   */
  invoiceApprovePath("nc.itf.pu.m25.IInvoiceApprove"),
  /**
   * 请购单审批action接口路径
   */
  orderApprovePath("nc.itf.pu.m21.IOrderApprove"),
  /**
   * 采购订单维护action接口路径
   */
  orderMaintainPath("nc.itf.pu.m21.IOrderMaintain"),

  /**
   * 请购单审批action接口路径
   */
  prayBillApprovePath("nc.itf.pu.m20.IPraybillApprove"),
  /**
   * 请购单维护action接口路径
   */
  prayBillMaintainPath("nc.itf.pu.m20.IPraybillMaintain"),
  /**
   * 价格结算审批action接口路径
   */
  pricestlApprovePath("nc.itf.pu.m24.IPricestlApprove"),
  /**
   * 到货单审批action接口路径
   */
  puarrivalApprovePath("nc.itf.pu.m23.approve.IArriveApprove"), /**
   * 
   * 物资需求申请审批action接口路径
   */
  storeReqApprovePath("nc.itf.pu.m422x.IStoreReqAppApprove");

  private String code;

  private PuBusiLogPathCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

}
