/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-4 下午04:12:44
 */
package nc.pubitf.pu.m21.ia.mif;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为存货核算提供的接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-4 下午04:12:44
 */
public interface IOrderQueryForIf {

  /**
   * 方法功能描述：根据财务组织+物料取最新市价
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_financeorg 财务组织
   * @param pk_srcmaterials 物料OID[]
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 下午04:15:56
   */
  Map<String, UFDouble> getNewPriceForIA(String pk_financeorg,
      String[] pk_srcmaterials);
}
