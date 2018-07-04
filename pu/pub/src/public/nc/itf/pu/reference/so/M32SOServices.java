package nc.itf.pu.reference.so;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m32.pu.m21.ISaleInvoiceQryFor21;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 销售发票
 * 
 * @since 6.0
 * @version 2010-11-12 下午02:55:28
 * @author wuxla
 */

public class M32SOServices {

  /**
   * 销量查询
   * 
   * @param cmaterialvid 物料VID
   * @param queryDate 查询时间
   * @param queryDay 查询天数
   * @param pk_group 集团
   * @return
   */
  public static Map<String, UFDouble> getInvInvoiceNumber(
      String[] cmaterialvid, UFDate queryDate, Integer queryDay,
      String pk_group, String pk_org) {

    if (!SysInitGroupQuery.isSOEnabled()) {
      return new HashMap<String, UFDouble>();
    }
    ISaleInvoiceQryFor21 service =
        NCLocator.getInstance().lookup(ISaleInvoiceQryFor21.class);

    try {
      return service.getInvInvoiceNumber(cmaterialvid, queryDate, queryDay,
          pk_group, pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return new HashMap<String, UFDouble>();
  }

}
