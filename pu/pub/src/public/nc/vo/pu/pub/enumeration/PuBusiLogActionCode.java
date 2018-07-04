package nc.vo.pu.pub.enumeration;

/**
 * 业务日志的action枚举
 * 
 * @since 6.0
 * @version 2011-5-25 下午09:35:13
 * @author 田锋涛
 */
public enum PuBusiLogActionCode {
  /** 审批 **/
  approve("approve", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0003")/* @res "审批" */),
  /**
   * 到货关闭
   */
  arriveClose("arvClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0071")/* @res "到货关闭" */),

  /**
   * 到货打开
   */
  arriveOpen("arvOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0072")/* @res "到货打开" */),

  /**
   * 关闭
   */
  close("close", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0073")/* @res "整单关闭" */),
  /**
   * 提交
   */
  commit("commit", null/* @res "提交" */),
  /**
   * 删除
   */
  delete("delete", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000039")/* @res "删除" */),

  // 更新
  edit("edit", null),
  /**
   * 冻结
   */
  freeze("freeze", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000030")/* @res "冻结" */),

  // 插入
  insert("insert", null),

  /**
   * 开票关闭
   */
  invoiceClose("invClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0074")/* @res "开票关闭" */),
  /**
   * 开票打开
   */
  invoiceOpen("invOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0075")/* @res "开票打开" */),
  /**
   * 打开
   */
  open("open", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0076")/* @res "整单打开" */),

  /**
   * 取消审批
   */
  /**
   * 付款关闭
   */
  payClose("payClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0077")/* @res "付款关闭" */),
  /**
   * 付款打开
   */
  payOpen("payOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0078")/* @res "付款打开" */),

  /**
   * 行关闭
   */
  rowClose("rowClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0079")/* @res "行关闭" */),
  /**
   * 行打开
   */
  rowOpen("rowOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0080")/* @res "行打开" */),
  /**
   * 保存
   */
  save("save", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
      "UC001-0000001")/* @res "保存" */),
  /**
   * 提交
   */
  sendapprove("sendapprove", null/* @res "提交" */),

  /**
   * 入库关闭
   */
  stockClose("stockClose", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4004000_0", "04004000-0081")/* @res "入库关闭" */),
  /**
   * 入库打开
   */
  stockOpen("stockOpen", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0082")/* @res "入库打开" */),

  unapprove("unapprove", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4004000_0", "04004000-0083")/* @res "取消审批" */),
  /**
   * 不提交
   */
  unCommit("uncommit", null/* @res "不提交" */),
  /**
   * 解冻
   */
  unfreeze("unfreeze", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "common", "UC001-0000031")/* @res "解冻" */),
  /**
   * 收回
   */
  unSendapprove("unSendapprove", null/* @res "收回" */),
  // 更新
  update("update", null);

  private String code;

  private String name;

  private PuBusiLogActionCode(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
