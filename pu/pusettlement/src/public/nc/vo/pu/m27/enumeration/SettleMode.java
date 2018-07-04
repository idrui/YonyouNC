/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-18 下午07:09:36
 */
package nc.vo.pu.m27.enumeration;

/**
 * <p>
 * <b>结算操作状态枚举</b>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-18 下午07:09:36
 */
public enum SettleMode {

  MatchMode(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0140")/*@res "匹配态"*/, 1),

  QueryMode(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0141")/*@res "查询态"*/, 0),

  ResultMode(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0142")/*@res "匹配结果态"*/, 2);

  private String name;

  private int value;

  SettleMode(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public int getValue() {
    return this.value;
  }

}