package nc.vo.pu.report.enumeration;

public enum StoreReqSummaryDynamicFieldCode {

  /** ��֯�ֶ� */
  pk_org_cond("pk_org_cond");

  private String code = null;

  private StoreReqSummaryDynamicFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
