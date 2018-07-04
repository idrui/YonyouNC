package nc.bs.pu.m21.query.ia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.pu.reference.so.M30SOServices;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-15 ÏÂÎç01:42:31
 * @author wuxla
 */

public class OrderQuerySOForIABP {

  public Map<String, String> queryDirectSOBidForIA(String[] bids) {
    Map<String, String> map = new HashMap<String, String>();
    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.CSOURCEBID,
          OrderItemVO.VSOURCETRANTYPE, OrderItemVO.CSOURCETYPECODE
        });
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + OrderItemVO.CSOURCETYPECODE, new String[] {
      SOBillType.Order.getCode(), POBillType.PrayBill.getCode()
    });
    OrderItemVO[] itemVOs = query.query(bids);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return map;
    }

    Map<String, UFBoolean> directMap = new HashMap<String, UFBoolean>();
    Set<String> praybset = new HashSet<String>();
    for (OrderItemVO itemVO : itemVOs) {
      String vsourcetrantype = itemVO.getVsourcetrantype();
      if (StringUtil.isEmptyWithTrim(vsourcetrantype)) {
        continue;
      }
      if (directMap.containsKey(vsourcetrantype)) {
        continue;
      }
      String csourcetypecode = itemVO.getCsourcetypecode();
      if (SOBillType.Order.getCode().equals(csourcetypecode)) {
        UFBoolean direct = M30SOServices.queryIsDirectPO(vsourcetrantype);
        if (UFBoolean.TRUE == direct) {
          directMap.put(vsourcetrantype, UFBoolean.TRUE);
        }
        else {
          directMap.put(vsourcetrantype, UFBoolean.FALSE);
        }
      }
      else if (POBillType.PrayBill.getCode().equals(csourcetypecode)) {
        praybset.add(itemVO.getCsourcebid());
      }
    }

    Map<String, String> praydirect = null;
    if (praybset.size() > 0) {
      String[] praybids = praybset.toArray(new String[praybset.size()]);
      praydirect = this.queryDirectSourceBidMap(praybids);
    }

    for (OrderItemVO itemVO : itemVOs) {
      String vsourcetrantype = itemVO.getVsourcetrantype();
      String csourcetype = itemVO.getCsourcetypecode();
      if (StringUtil.isEmptyWithTrim(vsourcetrantype)) {
        continue;
      }
      if (SOBillType.Order.getCode().equals(csourcetype)) {
        if (UFBoolean.TRUE.equals(directMap.get(vsourcetrantype))) {
          map.put(itemVO.getPk_order_b(), itemVO.getCsourcebid());
        }
      }
      else if (POBillType.PrayBill.getCode().equals(csourcetype)
          && praydirect != null) {
        String sobid = praydirect.get(itemVO.getCsourcebid());
        if (sobid != null) {
          map.put(itemVO.getPk_order_b(), sobid);
        }
      }
    }

    return map;
  }

  public MapList<String, String> querySOBidAndCtransIdForIA(String[] bids) {
    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.CSOURCEBID,
          OrderItemVO.VSOURCETRANTYPE
        });
    OrderItemVO[] itemVOs = query.query(bids);
    MapList<String, String> mapList = new MapList<String, String>();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return mapList;
    }

    for (OrderItemVO itemVO : itemVOs) {
      String pk_order_b = itemVO.getPk_order_b();
      mapList.put(pk_order_b, itemVO.getCsourcebid());
      mapList.put(pk_order_b, itemVO.getVsourcetrantype());
    }

    return mapList;
  }

  private Map<String, String> queryDirectSourceBidMap(String[] bids) {
    IQueryPrayBill prayservice =
        NCLocator.getInstance().lookup(IQueryPrayBill.class);
    try {
      return prayservice.queryDirectSourceBidMap(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
