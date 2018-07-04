package nc.bs.pu.m27.settlebill.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.itf.pu.m4202.IVmiFinanceQuery;
import nc.pubitf.pu.m4t.pu.settle.IInitialEstSettleQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m27.entity.GeneralInSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存单据信息的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-16 下午02:36:53
 */
public class StockInfoUtil {
  /** 库存单据信息 */
  private Map<String, StockSettleVO> m_hmapStock =
      new HashMap<String, StockSettleVO>();

  /**
   * StockInfoUtil 的构造子<br>
   * 在此构造子中完成对数据的初始化
   */
  public StockInfoUtil(SettleBillVO[] vos) {
    this.queryAllInfo(vos);
  }

  /**
   * 得到相应的库存单据VO
   * 
   * @param bid 库存单据表体VO
   * @return <p>
   * @author wangyf
   * @time 2009-7-9 下午01:27:23
   */
  public StockSettleVO getStockSettleVO(String bid) {
    return this.m_hmapStock.get(bid);
  }

  private void putMap(StockSettleVO[] stockVOs) {
    for (StockSettleVO stockVO : stockVOs) {
      this.m_hmapStock.put(stockVO.getPk_stockps_b(), stockVO);
    }
  }

  /**
   * 查询所有数据
   * 
   * @author wangyf
   * @time 2009-7-9 下午01:40:03
   */
  private void queryAllInfo(SettleBillVO[] vos) {
    // ----------------1、整理所有ID
    HashSet<String> purchaseinBids = new HashSet<String>();
    HashSet<String> generalinBids = new HashSet<String>();
    HashSet<String> voiconsumeBids = new HashSet<String>();
    HashSet<String> subcontractBids = new HashSet<String>();
    HashSet<String> initialestBids = new HashSet<String>();

    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO voTempItem : items) {
        if (!SettleBillItemVOUtil.isStockLine(voTempItem)
            || StringUtil.isEmptyWithTrim(voTempItem.getPk_stock())) {
          continue;
        }
        else if (ICBillType.PurchaseIn.getCode().equals(
            voTempItem.getVstockbilltype())) {
          purchaseinBids.add(voTempItem.getPk_stock_b());
        }
        else if (ICBillType.GeneralIn.getCode().equals(
            voTempItem.getVstockbilltype())) {
          generalinBids.add(voTempItem.getPk_stock_b());
        }
        else if (ICBillType.VmiSum.getCode().equals(
            voTempItem.getVstockbilltype())) {
          voiconsumeBids.add(voTempItem.getPk_stock_b());
        }
        else if (ICBillType.SubContinIn.getCode().equals(
            voTempItem.getVstockbilltype())) {
          subcontractBids.add(voTempItem.getPk_stock_b());
        }
        else if (POBillType.InitEstimate.getCode().equals(
            voTempItem.getVstockbilltype())) {
          initialestBids.add(voTempItem.getPk_stock_b());
        }
      }
    }

    // ----------查询采购入
    this.queryPurchaseinStockInfo(purchaseinBids
        .toArray(new String[purchaseinBids.size()]));
    // ----------查询其他入
    this.queryGeneralinStockInfo(generalinBids.toArray(new String[generalinBids
        .size()]));
    // ----------查询消耗汇总
    this.queryVoiconsumeStockInfo(voiconsumeBids
        .toArray(new String[voiconsumeBids.size()]));
    // ----------查询委外入
    this.querySubcontractStockInfo(subcontractBids
        .toArray(new String[subcontractBids.size()]));
    // ----------查询期初暂估入
    this.queryInitialestStockInfo(initialestBids
        .toArray(new String[initialestBids.size()]));
  }

  /**
   * 方法功能描述：查询其他入库单信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 表体ID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午03:03:46
   */
  private void queryGeneralinStockInfo(String[] bids) {
    if (bids == null || bids.length == 0) {
      return;
    }
    GeneralInSettleVO[] vos =
        new ViewQuery<GeneralInSettleVO>(GeneralInSettleVO.class).query(bids);
    StockSettleVOUtil.calcStockCanSettle(vos);
    this.putMap(vos);
  }

  /**
   * 方法功能描述：查询期初暂估入库单信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 表体ID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午03:05:31
   */
  private void queryInitialestStockInfo(String[] bids) {
    if (bids == null || bids.length == 0) {
      return;
    }
    try {
      IInitialEstSettleQuery stockQuery =
          NCLocator.getInstance().lookup(IInitialEstSettleQuery.class);

      StockSettleVO[] stockVOs = stockQuery.queryByBID(bids);

      this.putMap(stockVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：查询库存采购入信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 行ID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午03:03:11
   */
  private void queryPurchaseinStockInfo(String[] bids) {
    if (bids == null || bids.length == 0) {
      return;
    }

    try {
      IStockFinanceQuery stockQuery =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);

      StockSettleVO[] stockVOs = stockQuery.queryPurchaseInByBID(bids);

      this.putMap(stockVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：查询委外入库单信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 表体ID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午03:05:13
   */
  private void querySubcontractStockInfo(String[] bids) {
    if (bids == null || bids.length == 0) {
      return;
    }
    try {
      IStockFinanceQuery query =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);

      StockSettleVO[] stockVOs = query.querySubcontractByBID(bids);

      this.putMap(stockVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：查询VMI汇总信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param vmiids VMI汇总的ID数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午03:03:08
   */
  private void queryVoiconsumeStockInfo(String[] vmiids) {
    if (vmiids == null || vmiids.length == 0) {
      return;
    }

    try {
      IVmiFinanceQuery vmiQuery =
          NCLocator.getInstance().lookup(IVmiFinanceQuery.class);

      StockSettleVO[] stockVOs = vmiQuery.queryVMIByIds(vmiids);

      this.putMap(stockVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }
}
