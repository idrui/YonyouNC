package nc.vo.pu.m21.pub;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.reference.so.M30SOServices;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * @since 6.0
 * @version 2011-6-28 下午04:32:39
 * @author wuxla
 */

public class DirectUtil {

  public static boolean isDirect(IKeyValue keyValue, int row) {
    String cfirsttypecode =
        (String) keyValue.getBodyValue(row, OrderItemVO.CFIRSTTYPECODE);
    String vfirsttrantype =
        (String) keyValue.getBodyValue(row, OrderItemVO.VFIRSTTRANTYPE);
    String cfirstid = (String) keyValue.getBodyValue(row, OrderItemVO.CFIRSTID);

    String csourcetypecode =
        (String) keyValue.getBodyValue(row, OrderItemVO.CSOURCETYPECODE);
    String csourcetrantype =
        (String) keyValue.getBodyValue(row, OrderItemVO.VSOURCETRANTYPE);
    String csourceid =
        (String) keyValue.getBodyValue(row, OrderItemVO.CSOURCEID);

    if (SOBillType.Order.getCode().equals(csourcetypecode)) {
      return DirectUtil.isSODirect(csourcetrantype);
    }
    else if (SOBillType.Order.getCode().equals(cfirsttypecode)) {
      return DirectUtil.isSODirect(vfirsttrantype);
    }
    else if (POBillType.PrayBill.getCode().equals(csourcetypecode)) {
      return DirectUtil.isPrayDirect(csourceid);
    }
    else if (POBillType.PrayBill.getCode().equals(cfirsttypecode)) {
      return DirectUtil.isPrayDirect(cfirstid);
    }

    return false;
  }

  /**
   * 查询请购单直运属性
   * 
   * @param pk_praybills
   * @return
   */
  public static Map<String, UFBoolean> queryPrayDirectAttrs(
      String[] pk_praybills) {
    IQueryPrayBill service =
        NCLocator.getInstance().lookup(IQueryPrayBill.class);
    try {
      return service.isDirect(pk_praybills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 查询销售订单交易类型的直运属性
   * 
   * @param trantypes
   * @return
   */
  public static Map<String, UFBoolean> querySOTrantypeDirectAttrs(
      String[] trantypeids) {
    return M30SOServices.querySOTrantypeDirectAttrs(trantypeids);
  }

  private static boolean isPrayDirect(String cfirstid) {
    IQueryPrayBill service =
        NCLocator.getInstance().lookup(IQueryPrayBill.class);
    try {
      Map<String, UFBoolean> map = service.isDirect(new String[] {
        cfirstid
      });
      return map != null && UFBoolean.TRUE.equals(map.get(cfirstid));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  private static boolean isSODirect(String csourcetrantype) {
    UFBoolean ret = M30SOServices.queryIsDirectPO(csourcetrantype);
    return ret.booleanValue();
  }

}
