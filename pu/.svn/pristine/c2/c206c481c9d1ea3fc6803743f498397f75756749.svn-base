/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 上午11:59:15
 */
package nc.vo.pu.pub.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-1 上午11:59:15
 */
public enum OnwayStatusQryEnum {
  /** 报关 **/
  biscustom("biscustom", "4004000_0", "04004000-0008")/* @res "报关" */,
  /** 装运 **/
  bisload("bisload", "4004000_0", "04004000-0007")/* @res "装运" */,
  /** 出关 **/
  bisoutcustom("bisoutcustom", "4004000_0", "04004000-0063")/* @res "出关 " */,
  /** 发货 **/
  bissendout("bissendout", "4004000_0", "04004000-0006")/* @res "发货" */,
  /** 对方确认 **/
  confirm("confirm", "4004000_0", "04004000-0023")/* @res "对方确认" */,
  /** 输出 **/
  output("output", "4004000_0", "04004000-0004")/* @res "输出" */;

  private String code;

  private String resdir;

  private String resid;

  private OnwayStatusQryEnum(String code, String resdir, String resid) {
    this.code = code;
    this.resdir = resdir;
    this.resid = resid;
  }

  public String code() {
    return this.code;
  }

  public String getName() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(this.resdir,
        this.resid);
  }
}
