package nc.vo.pu.report.enumeration;

public enum OrderSummaryDynamicFieldCode {

  /** ÍË»õ½ð¶î */
  returnmny("returnmny");

  private String code = null;

  private OrderSummaryDynamicFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
