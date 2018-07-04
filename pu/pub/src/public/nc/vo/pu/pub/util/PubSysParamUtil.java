package nc.vo.pu.pub.util;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.scmpub.res.para.NCPara;

/**
 * @since 6.0
 * @version 2011-5-14 下午01:03:39
 * @author wuxla
 */

public class PubSysParamUtil {

  /** 全局本位币计算方式：基于本位币计算 */
  public static final int GLOBAL_CURRY_BASE = 2;

  /** 全局本位币计算方式：不启用全局本位币 */
  public static final int GLOBAL_DISABLE = 0;

  /** 全局本位币计算方式：基于原币计算 */
  public static final int GLOBAL_ORIG_BASE = 1;

  /** 集团本位币计算方式：基于本位币计算 */
  public static final int GROUP_CURRY_BASE = 2;

  /** 集团本位币计算方式：不启用集团本位币 */
  public static final int GROUP_DISABLE = 0;

  /** 集团本位币计算方式：基于原币计算 */
  public static final int GROUP_ORIG_BASE = 1;

  /**
   * 集团本位币计算方式
   *
   * @return
   */
  public static String getNC001(String pk_group) {
    AssertUtils.assertValue(pk_group != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0091")/*@res "集团为空"*/);
    return SysParaInitQuery.getParaString(pk_group, "NC001");
  }

  /**
   * 集团本位币计算方式
   *
   * @return
   */
  public static int getNC001IntValue(String pk_group) {
    AssertUtils.assertValue(pk_group != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0091")/*@res "集团为空"*/);
    String value = SysParaInitQuery.getParaString(pk_group, "NC001");
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GROUP_ORIG_BASE;
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GROUP_CURRY_BASE;
    }
    // 不启用集团本位币
    return PubSysParamUtil.GROUP_DISABLE;
  }

  /**
   * 全局本位币计算方式
   *
   * @return
   */
  public static String getNC002() {
    return SysParaInitQuery.getParaString(IOrgConst.GLOBEORG, "NC002");
  }

  /**
   * 全局本位币计算方式
   *
   * @return
   */
  public static int getNC002IntValue() {
    String value = SysParaInitQuery.getParaString(IOrgConst.GLOBEORG, "NC002");
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GLOBAL_ORIG_BASE;
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GLOBAL_CURRY_BASE;
    }
    // 不启用全局本位币
    return PubSysParamUtil.GLOBAL_DISABLE;
  }
}