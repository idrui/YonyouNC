package nc.vo.pu.ec.enumeration;

public enum BillSrcEnum {
  /** 采购订单付款执行情况查询 **/
  ALL("all"),
  /** 采购订单 **/
  EC("ec"),
  /** 来源供应链 **/
  SCM("scm");

  private String code;

  private BillSrcEnum(String code) {
    this.code = code;
  }

  public String code() {
    return this.code;
  }

}
