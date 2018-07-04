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
 * <b>结算事件类型枚举</b>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-20 下午07:09:36
 */
public enum SettleEventType {

  BEFORE_SHOW_RESULT(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0128")/*@res "初始化结算结果界面"*/, 9),

  FEEDISTRIBUTE_FINISHED(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0129")/*@res "费用分摊结束"*/, 11),

  INIT_PROCESS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0130")/*@res "初始化匹配界面"*/, 6), INIT_QUERY(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0131")/*@res "初始化查询界面"*/, 5),

  MATCH_CANCEL(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0132")/*@res "取消匹配"*/, 10),

  PREPARE_MATCHVOS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0133")/*@res "准备匹配数据"*/, 8),

  PREPARE_SETTLEVOS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0134")/*@res "准备结算数据"*/, 7),

  SAME_MATERIAL_SETTLE(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0135")/*@res "同物料结算"*/, 4),

  SHOW_INVOICE(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0136")/*@res "显示发票"*/, 1),

  SHOW_PROCESS(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0137")/*@res "显示匹配界面"*/, 2),

  SHOW_RESULT(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0138")/*@res "显示结算结果界面"*/, 3), SHOW_STOCK(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0139")/*@res "显示入库单"*/, 0);

  private String name;

  private int value;

  SettleEventType(String name, int value) {
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