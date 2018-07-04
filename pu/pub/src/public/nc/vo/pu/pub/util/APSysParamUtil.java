/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-11 下午02:02:54
 */
package nc.vo.pu.pub.util;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.AssertUtils;

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
 * @author wuxla
 * @time 2010-7-11 下午02:02:54
 */
public class APSysParamUtil {

  /**
   * 方法功能描述：是否进行采购的预付款控制
   * <p>
   * <b>参数说明</b>
   *
   * @param pk_org
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-11 下午02:04:22
   */
  public static UFBoolean getAP17(String pk_org) {
    AssertUtils.assertValue(pk_org != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0090")/*@res "财务组织为空"*/);
    return SysParaInitQuery.getParaBoolean(pk_org, "AP17");
  }
}