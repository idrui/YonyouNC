/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 上午11:54:22
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 处理红蓝对冲的发票传应付
 * @scene
 * 传应付,结算(匹配)后自动传应付
 * @param
 * sttlInfoMap 发票的结算明细
 * env 采购发票操作时前台到后台的环境信息，一般随平台动作的userObj向外传 
 * 
 * @since 6.3
 * @version 2014-10-22 下午3:40:55
 * @author zhangshqb
 */
public class RedBlueSettleTOAPRule implements IFilterRule<InvoiceVO> {

  /** 环境信息 */
  private InvoiceUIToBSEnv env;

  /** 发票的结算明细--包含红蓝对冲的双方发票 **/
  private MapList<String, SettleBillInfo> sttlInfoMap;

  /**
   * RedBlueSettleTOAPRule 的构造子
   * 
   * @param sttlInfoMap
   *          发票的结算明细
   */
  public RedBlueSettleTOAPRule(MapList<String, SettleBillInfo> sttlInfoMap,
      InvoiceUIToBSEnv env) {
    this.sttlInfoMap = sttlInfoMap;
    this.env = env;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos) || null == this.sttlInfoMap
        || 0 == this.sttlInfoMap.size()) {
      return vos;
    }
    // 如果是自动结算传应付，之前已经加了此规则，不需要重新走一遍；手动传应付必须走此规则
    if (this.env != null && this.env.isAutoSettleSendAP()) {
      return vos;
    }
    return this.processRedBlue(vos);
  }

  private InvoiceVO[] filterVOs(InvoiceVO[] vos, Set<String> unSendAPIDs,
      Set<String> unSendAPRushIDs) {
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      if (unSendAPIDs.contains(vo.getPrimaryKey())
          || unSendAPRushIDs.contains(vo.getPrimaryKey())) {
        continue;
      }
      filterVos.add(vo);
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  private InvoiceVO[] getRushInvoiceVOs(InvoiceVO[] vos, Set<String> rushIDs) {
    List<InvoiceVO> rushVOList = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      String pk_invoice = vo.getPrimaryKey();
      if (rushIDs.contains(pk_invoice)) {
        rushVOList.add(vo);
      }
    }
    return rushVOList.toArray(new InvoiceVO[rushVOList.size()]);
  }

  /**
   * 方法功能描述：遍历结算明细，取值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 包含对冲双方发票的VO数组
   * @param RushABMap 用于返回，保存对冲的Map(A1--B1,B1--A1时只返回A1--B1，即将结算明细去重)
   * @param rushIDs 用于返回，保存分配到对冲部分的发票ID
   * @param normStlInvcBIDs 用于返回，保存与入库单结算的发票行ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-4 上午10:21:50
   */
  private void iterateSttleInfo(InvoiceVO[] vos, Map<String, String> RushABMap,
      Set<String> rushIDs, Set<String> normStlInvcBIDs) {
    InvoiceItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    Set<String> bids =
        CirVOUtil.getDistinctFieldSet(items, InvoiceItemVO.PK_INVOICE_B);
    for (String pk_invoice_b : bids) {
      if (!this.sttlInfoMap.containsKey(pk_invoice_b)) {
        continue;
      }
      List<SettleBillInfo> stlInfo = this.sttlInfoMap.get(pk_invoice_b);
      if (CollectionUtils.isEmpty(stlInfo)) {
        continue;
      }
      for (SettleBillInfo info : stlInfo) {
        String pk_stockps_b = info.getPk_stock_b();
        if (!StringUtil.isEmptyWithTrim(pk_stockps_b)) {
          if (null != normStlInvcBIDs) {
            normStlInvcBIDs.add(pk_invoice_b);
          }
        }
        else {
          String pk_rush_b = info.getPk_rushinvoice_b();
          String pk_rush = info.getPk_rushinvoice();
          String pk_invoice = info.getPk_invoice();
          if (!StringUtil.isEmptyWithTrim(pk_rush_b)
              && !StringUtil.isEmptyWithTrim(pk_rush)
              && (null == rushIDs || !rushIDs.contains(pk_invoice))) {
            if (null != RushABMap) {
              RushABMap.put(pk_invoice_b, pk_rush_b);
            }
            if (null != rushIDs) {
              rushIDs.add(pk_rush);
            }
          }
        }
      }
    }
  }

  /**
   * 对红对冲的发票，自动结算传过来的是蓝字发票ID 根据以下需求：
   * <p>
   * 红蓝发票自动结算时，如果红蓝双方的发票数量绝对值完全相等，
   * <p>
   * 并且两方都未传过应付时， 则两方都不用再传应付，否则都需要传应付
   * <p>
   * （也就是以上两个条件不同时满足的情况下未传应付的发票则需要传应付）。
   * <p>
   * （补充：已传应付或者已参与过结算的采购发票也允许作对冲自动结算）
   * <p>
   * 规则处理如下：
   * <p>
   * <li>如果蓝字传过应付，而红字未传过应付，则将蓝字过滤掉，将红字传应付</li>
   * <li>如果蓝字未传应付，而红字传应付，则直接将蓝字传，不管红字</li>
   * <li>如果双方都已经传过应付，则一定都不用再传</li>
   * <li>如果双方都未传应付，且数量绝对值相等，则也不需要再传</li>
   * <li>如果双方都未传应付，且数量绝对值不等，则都需要传</li>
   * 
   * @param vos -- 包含了红蓝对冲的双方发票VO，不用再查
   */
  private InvoiceVO[] processRedBlue(InvoiceVO[] vos) {
    Map<String, String> rushABMap = new HashMap<String, String>();
    Set<String> rushIDs = new HashSet<String>();
    Set<String> normStlInvcBIDs = new HashSet<String>();
    // 遍历结算明细，生成对冲的VOmap,对冲发票BID的set，正常用结算的发票BID的set等
    this.iterateSttleInfo(vos, rushABMap, rushIDs, normStlInvcBIDs);
    if (0 == rushIDs.size()) {
      return vos;
    }
    // 从发票VO中拆出对冲的发票
    InvoiceVO[] rushVos = this.getRushInvoiceVOs(vos, rushIDs);
    // 如果当前发票vo里没有对冲vo，则可视为发票手工传应付，需重新查vo
    if (ArrayUtils.isEmpty(rushVos)) {
      rushVos =
          new BillQuery<InvoiceVO>(InvoiceVO.class).query(rushIDs
              .toArray(new String[rushIDs.size()]));
    }
    Map<String, InvoiceItemVO> normItemMap = AggVOUtil.createItemMap(vos);
    Map<String, InvoiceItemVO> rushItemMap = AggVOUtil.createItemMap(rushVos);
    Map<String, InvoiceVO> VOMap = AggVOUtil.createVOMap(vos);
    Map<String, InvoiceVO> rushVOMap = AggVOUtil.createVOMap(rushVos);
    // 不需要传应付的对冲A发票ID--需从vos中移除
    Set<String> unSendAPIDs = new HashSet<String>();
    // 不需要传应付的对冲B发票ID
    Set<String> unSendAPRushIDs = new HashSet<String>();
    for (Entry<String, String> entry : rushABMap.entrySet()) {
      String pk_invoice_b = entry.getKey();
      InvoiceItemVO normItem = normItemMap.get(pk_invoice_b);
      String pk_invoice = normItem.getPk_invoice();
      String pk_rush_b = entry.getValue();
      InvoiceItemVO rushItem = rushItemMap.get(pk_rush_b);
      String pk_rush = rushItem.getPk_invoice();
      UFDouble num = MathTool.nvl(normItem.getNnum());
      UFDouble rushNum = MathTool.nvl(rushItem.getNnum());
      UFBoolean apFlag = VOMap.get(pk_invoice).getParentVO().getBapflag();
      UFBoolean rushApFlag = rushVOMap.get(pk_rush).getParentVO().getBapflag();
      /**
       * 红蓝两方都未传过应付 &&
       * 双方的发票数量绝对值完全相等 &&
       * 双方的发票未参与的此次正常结算(如果参与相当于已传应付)
       * 则双方不传应付，否则必传应付
       */
      if (!normStlInvcBIDs.contains(pk_invoice_b)
          && !normStlInvcBIDs.contains(pk_rush_b)
          && !ValueUtils.getBoolean(apFlag)
          && !ValueUtils.getBoolean(rushApFlag)
          && num.abs().equals(rushNum.abs())) {
        unSendAPIDs.add(pk_invoice);
        unSendAPRushIDs.add(pk_rush);
      }
      else {
        unSendAPIDs.remove(pk_invoice);
        unSendAPRushIDs.remove(pk_rush);
      }
    }
    return this.filterVOs(vos, unSendAPIDs, unSendAPRushIDs);
  }

}
