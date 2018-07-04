package nc.itf.pu.reference.sc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.sc.m61.pu.ISCOrderFor21;
import nc.pubitf.sc.m61.pub.ISCOrderQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sc.m61.entity.SCOrderItemVO;

/**
 * 委外订单提供给采购的服务适配
 * 
 * @since 6.0
 * @version 2011-3-17 下午07:46:41
 * @author wuxla
 */

public class M61PUServices {
  /**
   * 根据表体ID,返回ID对应的合同号Map
   * 
   * @param pk_order_bs
   * @return
   */
  public static Map<String, String> getCtCodeByBID(String[] pk_order_bs) {
    try {
      if (!SysInitGroupQuery.isSCEnabled()) {
        return null;
      }
      return NCLocator.getInstance().lookup(ISCOrderQuery.class)
          .getCtCodeByBID(pk_order_bs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 根据采购组织、财务组织和供应商查询发票PK
   * 
   * @param pk_org
   * @param pk_supply
   * @param pk_financeorgs
   * @return
   */
  public static List<String> queryM25pkBySupply(String pk_org,
      String pk_supply, String[] pk_financeorgs) {
    try {
      if (!SysInitGroupQuery.isSCEnabled()) {
        return null;
      }
      ISCOrderFor21 service =
          NCLocator.getInstance().lookup(ISCOrderFor21.class);
      return service.queryM25pkBySupply(pk_org, pk_supply, pk_financeorgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  /**
   * 根据委外订单bid查询扣税类别值，并返回map. key=bid，value=扣税类别值
   * 只给升级用
   * 
   * @param pk_order_bs
   * @return
   */
  public static Map<String, Integer> getFtaxtypeflagByBid(String[] pk_order_bs) {
    // ISCOrderQuery service =
    // NCLocator.getInstance().lookup(ISCOrderQuery.class);
    // SCOrderItemVO[] vos = service.getbodyVOByBID(pk_order_bs);
    // 上面注释行，在大数据环境下会报内存溢出，改成用VOQuery查询，只查询需要的两个字段值
    VOQuery<SCOrderItemVO> query =
        new VOQuery<SCOrderItemVO>(SCOrderItemVO.class, new String[] {
          SCOrderItemVO.PK_ORDER_B, SCOrderItemVO.FTAXTYPEFLAG
        });
    SCOrderItemVO[] vos = query.query(pk_order_bs);
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (SCOrderItemVO vo : vos) {
      map.put(vo.getPk_order_b(), vo.getFtaxtypeflag());
    }
    return map;
  }

  /**
   * 根据采购组织、财务组织和供应商查询价税合计
   * 
   * @param pk_org
   * @param pk_supply
   * @param pk_financeorgs
   * @return
   */
  public static Map<String, UFDouble[]> queryMnyBySupply(String pk_org,
      String pk_supply, String[] pk_financeorgs) {
    try {
      if (!SysInitGroupQuery.isSCEnabled()) {
        return null;
      }
      ISCOrderFor21 service =
          NCLocator.getInstance().lookup(ISCOrderFor21.class);
      return service.queryMnyBySupply(pk_org, pk_supply, pk_financeorgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }
}
