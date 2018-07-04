/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:38:37
 */
package nc.bs.pu.m25.ap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.est.m45.IPurchaseInEstPubQuery;
import nc.pubitf.pu.est.m4t.IInitEstimateEstPubQuery;
import nc.pubitf.pu.est.m50.IVMIEstPubQuery;
import nc.pubitf.pu.m27.ISettleBillQueryFor25;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询与发票结算的库存单据暂估和更新暂估信息的工具
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-19 上午10:38:37
 */
public class EstInfoMaintainUtil {

  /**
   * 获取需要查询结算信息的发票主键
   * 
   * @param vos 发票vo
   * @param env 结算环境
   * @return 发票主键
   */
  public static String[] getSettleQueryInvoices(InvoiceVO[] vos,
      InvoiceUIToBSEnv env) {
    MapList<String, SettleBillInfo> stlbillInfo =
        new MapList<String, SettleBillInfo>();
    if (env != null && env.getSttlInfoMap() != null) {
      stlbillInfo = env.getSttlInfoMap();
    }
    Set<String> hid = new HashSet<String>();
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // 表体没查询过就要重新查一次
        if (!stlbillInfo.containsKey(item.getPk_invoice_b())) {
          hid.add(item.getPk_invoice());
          break;
        }
      }
    }
    if (hid.size() == 0) {
      return null;
    }
    return hid.toArray(new String[hid.size()]);
  }

  /**
   * 方法功能描述：根据发票查询结算明细。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          发票VO数组
   * @param env
   *          如果env中已经查询过结算明细，则直接取
   * @return 发票对象的结算明细
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-28 下午02:10:45
   */
  public static MapList<String, SettleBillInfo> getSttlInfo(InvoiceVO[] vos,
      InvoiceUIToBSEnv env) {
    MapList<String, SettleBillInfo> sttlInfo = null;
    String[] invoiceHids = EstInfoMaintainUtil.getSettleQueryInvoices(vos, env);
    // 需要查询
    if (!ArrayUtils.isEmpty(invoiceHids)) {
      // 查询结算明细
      ISettleBillQueryFor25 srv =
          NCLocator.getInstance().lookup(ISettleBillQueryFor25.class);

      try {
        sttlInfo = srv.querySettleBills(invoiceHids);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      // 组合一下
      if (null != env && env.getSttlInfoMap() != null && sttlInfo != null) {
        for (Entry<String, List<SettleBillInfo>> entry : env.getSttlInfoMap()
            .entrySet()) {
          // 2012-03-27 zhaoyha，tianft
          // 裕丰项目项目反馈，发票有多行相同费用项时，与暂估过此费用项的入库单结算完毕自动传应付，回冲时费用项会重复
          if (sttlInfo.containsKey(entry.getKey())) {
            continue;
          }
          sttlInfo.putAll(entry.getKey(), entry.getValue());
        }
      }
      if (null != env && sttlInfo != null && 0 != sttlInfo.size()) {
        env.setSttlInfoMap(sttlInfo);
      }
    }
    return null == env ? sttlInfo : env.getSttlInfoMap();
  }

  /**
   * 方法功能描述：查询库存单据的暂估信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   *          存库单据类型
   * @param bids
   *          存库单据BIDS
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-19 上午10:42:18
   */
  public static EstVO[] queryEst(String billtype, String[] bids) {
    // 采购入库
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      return EstInfoMaintainUtil.queryPurchaseInEst(bids);
    }
    // 消耗汇总
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      return EstInfoMaintainUtil.queryVMIEst(bids);
    }
    // 期初暂估
    else if (POBillType.InitEstimate.getCode().equals(billtype)) {
      return EstInfoMaintainUtil.queryInitEstimate(bids);
    }
    return null;
  }

  /**
   * 方法功能描述：根据结算明细和发票VO得到暂估VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          发票VO数组
   * @param sttlInfo
   *          结算明细
   * @return MapList <billtype,EstVO>
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-28 下午02:09:19
   */
  public static Map<String, EstVO[]> queryEstInfo(InvoiceVO[] vos,
      MapList<String, SettleBillInfo> sttlInfo) {
    MapList<String, String> btBIDMap =
        EstInfoMaintainUtil.getStockBilltypeBIDMap(sttlInfo);
    if (null == btBIDMap || 0 == btBIDMap.size()) {
      return null;
    }
    Map<String, EstVO[]> estVOMap = new HashMap<String, EstVO[]>();
    for (Entry<String, List<String>> entry : btBIDMap.toMap().entrySet()) {
      List<String> bids = entry.getValue();
      if (CollectionUtils.isEmpty(bids)) {
        continue;
      }
      String bt = entry.getKey();
      EstVO[] estVos =
          EstInfoMaintainUtil.queryEst(bt,
              bids.toArray(new String[bids.size()]));
      if (ArrayUtils.isEmpty(estVos)) {
        continue;
      }
      estVOMap.put(bt, estVos);
    }
    return estVOMap;
  }

  /**
   * 方法功能描述：更新库存单据的暂估回冲、确认调差等信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   *          库存单据类型
   * @param estVos
   *          要更新的暂估VO
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-19 上午10:42:55
   */
  public static void updateEstInfo(String billtype, EstVO[] estVos) {
    // 采购人
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      EstInfoMaintainUtil.updatePurchaseInEst(estVos);
    }
    // 消耗汇总
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      EstInfoMaintainUtil.updateVMIEst(estVos);
    }
    // 期初暂估
    else if (POBillType.InitEstimate.getCode().equals(billtype)) {
      EstInfoMaintainUtil.updateInitEstimate(estVos);
    }
  }

  /** 得到结算的库存单据类型和BID的MAP，用于查询暂估和回写 **/
  private static MapList<String, String> getStockBilltypeBIDMap(
      MapList<String, SettleBillInfo> sttlInfo) {
    if (null == sttlInfo || 0 == sttlInfo.size()) {
      return null;
    }
    MapList<String, String> btBIDMap = new MapList<String, String>();
    for (Entry<String, List<SettleBillInfo>> entry : sttlInfo.toMap()
        .entrySet()) {
      List<SettleBillInfo> sttlList = entry.getValue();
      if (CollectionUtils.isEmpty(sttlList)) {
        continue;
      }
      for (SettleBillInfo si : sttlList) {
        String bt = si.getVstockbilltype();
        String pk_stockps_b = si.getPk_stock_b();
        if (StringUtil.isEmptyWithTrim(bt)
            || StringUtil.isEmptyWithTrim(pk_stockps_b)) {
          continue;
        }
        btBIDMap.put(bt, pk_stockps_b);
      }
    }
    return btBIDMap;
  }

  /**
   * 查询期暂估
   * 
   * @param bids
   * @return
   */
  private static EstVO[] queryInitEstimate(String[] bids) {
    IInitEstimateEstPubQuery query =
        NCLocator.getInstance().lookup(IInitEstimateEstPubQuery.class);
    try {
      return query.query(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private static EstVO[] queryPurchaseInEst(String[] bids) {
    IPurchaseInEstPubQuery query =
        NCLocator.getInstance().lookup(IPurchaseInEstPubQuery.class);
    try {
      return query.query(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private static EstVO[] queryVMIEst(String[] bids) {
    IVMIEstPubQuery query =
        NCLocator.getInstance().lookup(IVMIEstPubQuery.class);
    try {
      return query.query(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 更新期初暂估的回冲信息
   * 
   * @param estVos
   */
  private static void updateInitEstimate(EstVO[] estVos) {
    // 暂时没有需要回写的数据
  }

  private static void updatePurchaseInEst(EstVO[] estVos) {
    // 暂时没有需要回写的数据
  }

  private static void updateVMIEst(EstVO[] estVos) {
    // 暂时没有需要回写的数据
  }

}
