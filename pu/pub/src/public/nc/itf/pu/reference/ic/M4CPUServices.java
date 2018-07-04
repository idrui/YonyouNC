/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-24 下午01:05:56
 */
package nc.itf.pu.reference.ic;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @time 2010-10-24 下午01:05:56
 */
public class M4CPUServices {

  /**
   * 销量查询
   * 
   * @param cmaterialvid 物料VID数组
   * @param queryDate 查询日期
   * @param queryDay 查询天数
   * @param pk_group 集团
   * @return
   */
  public static Map<String, UFDouble> getInvOutNumber(String[] cmaterialvid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org) {
    I4CQueryPubService service =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      return service.getInvOutNumber(cmaterialvid, queryDate, queryDay,
          pk_group, pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
