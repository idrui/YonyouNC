/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-24 ����01:05:56
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-24 ����01:05:56
 */
public class M4CPUServices {

  /**
   * ������ѯ
   * 
   * @param cmaterialvid ����VID����
   * @param queryDate ��ѯ����
   * @param queryDay ��ѯ����
   * @param pk_group ����
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
