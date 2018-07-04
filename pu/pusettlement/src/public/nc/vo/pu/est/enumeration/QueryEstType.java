/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-3 下午01:46:10
 */
package nc.vo.pu.est.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>当前所要进行暂估操作的类型，也作为查询条件的中的“暂估类型”
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-3 下午01:46:10
 */
public enum QueryEstType {
  /** 暂估类型-费用暂估 **/
  FEE_EST,
  /** 暂估类型-货物暂估 **/
  GOODS_EST,
  /** 取消暂估 **/
  UN_EST;

  public String dispName() {
    String name = null;
    switch (this) {
      case GOODS_EST:
        name = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0120")/*@res "货物暂估"*/;
        break;
      case FEE_EST:
        name = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0121")/*@res "费用暂估"*/;
        break;
      case UN_EST:
        name = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0122")/*@res "取消暂估"*/;
        break;
    }
    return name;
  }
}