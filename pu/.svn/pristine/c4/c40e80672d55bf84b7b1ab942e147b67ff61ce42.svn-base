package nc.bs.pu.it;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m27.feesettle.distribute.SecondDisResult;
import nc.bs.pu.m27.settlebill.SettleBillItemQueryBP;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>进出口调整发票分摊
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-28 下午01:12:53
 */
public class SecondDistributeUtilForIT {
  /** 本币精度 **/
  private int digit = 2;

  // 成本要素
  private CostfactorViewVO[] factors;

  // 第一次分摊结果
  private FirstDisResultForIT[] firstReuslts;

  // <入库单行ID,相应的货物结算单行>
  private Map<String, List<SettleBillItemVO>> stockbid2SettleItems;

  // 入库单单
  private StockSettleVO[] stocks;

  public SecondDistributeUtilForIT(StockSettleVO[] stocks,
      FirstDisResultForIT[] firstReuslts, CostfactorViewVO[] factors) {
    this.stocks = stocks;
    // 第一次分摊和第二次分摊都进行排序，以保证分摊数据一致
    StockSettleVOUtil.sortByCodeAndBid(this.stocks);
    this.firstReuslts = firstReuslts;
    this.factors = factors;
    // 初始化本币精度
    this.digit =
        new ScaleObjectFactory.CurrtypeScaleObject(2, 4).getDigit(stocks[0]
            .getCcurrencyid());
  }

  public MapList<String, SettleFeeAllotDetailVO> distribute() {
    if (ArrayUtils.isEmpty(this.stocks)
        || ArrayUtils.isEmpty(this.firstReuslts)) {
      return null;
    }
    // 可能参与货物结算的入库单行主键
    Set<String> goodsStlStockBIDSet = new HashSet<String>();
    for (StockSettleVO stock : this.stocks) {
      String stockType = stock.getCbilltypecode();
      if (ICBillType.PurchaseIn.getCode().equals(stockType)
          || ICBillType.VmiSum.getCode().equals(stockType)
          || POBillType.InitEstimate.getCode().equals(stockType)
          || ICBillType.SubContinIn.getCode().equals(stockType)) {
        goodsStlStockBIDSet.add(stock.getPk_stockps_b());
      }
    }
    // 1、准备数据，加载入库单对应的结算单+暂估单
    this.prepareData(goodsStlStockBIDSet);

    List<SecondDisResultForIT> results = new ArrayList<SecondDisResultForIT>();
    // 2、货物结算单、货物暂估之间的费用分摊
    for (StockSettleVO stock : this.stocks) {
      String stockbid = stock.getPk_stockps_b();
      if (goodsStlStockBIDSet.contains(stockbid)) {
        this.stockSecondDistribute(results, stock);
      }
      else {
        FirstDisResultForIT[] firstResults =
            this.findFirstDisResultByStockBid(stockbid);
        for (FirstDisResultForIT firstresult : firstResults) {
          results.addAll(this.recordDistributeDetail(firstresult, null));
        }
      }
    }
    SecondDisResult[] secondResults = results.toArray(new SecondDisResult[0]);
    return this.buildStockBid2AllotDetailMap(secondResults);
  }

  private MapList<String, SettleFeeAllotDetailVO> buildStockBid2AllotDetailMap(
      SecondDisResult[] secondResults) {
    // <入库单行ID,相应的费用分摊明细>
    MapList<String, SettleFeeAllotDetailVO> bid2DetailMap = null;
    bid2DetailMap = new MapList<String, SettleFeeAllotDetailVO>();

    if (ArrayUtils.isEmpty(secondResults)) {
      return bid2DetailMap;
    }

    for (SecondDisResult secondresult : secondResults) {
      String bid = secondresult.getStockbid();
      SettleFeeAllotDetailVO detail = new SettleFeeAllotDetailVO();
      detail.setBestfirstsettle(secondresult.getBestfirstsettle());
      detail.setNallotmoney(secondresult.getDismny());
      detail.setNtimesafterfirst(Integer.valueOf(0));
      detail.setVallotbilltype(secondresult.getVallotbilltype());
      detail.setPk_allotbillbid(secondresult.getPk_allotbillbid());
      detail.setPk_allotbillid(secondresult.getPk_allotbillid());
      detail.setNallotbillmny(secondresult.getBymny());
      detail.setNallotbillnum(secondresult.getBynum());
      detail.setPk_material(secondresult.getPk_material());
      detail.setPk_srcmaterial(secondresult.getPk_srcmaterial());
      detail.setPk_supplier(secondresult.getPk_supplier());
      detail.setPk_invoice_b(secondresult.getInvoicebid());// 发票行主键
      detail.setCostfactorvo(secondresult.getCostfactorvo());// 成本要素
      bid2DetailMap.put(bid, detail);
    }
    return bid2DetailMap;
  }

  private FeeMnyDivideVO convertGoodEstToFeeMnyDivideVO(StockSettleVO stock) {
    if (stock == null) {
      return null;
    }
    // 把货物暂估信息转为继续分摊所需要的结构
    // UFDouble estmny = stock.getNestmny();
    // wuxla v61
    UFDouble estmny = stock.getNestcalcostmny();

    UFDouble estnum = stock.getNestnum();
    if (MathTool.compareTo(estmny, UFDouble.ZERO_DBL) == 0
        && MathTool.compareTo(estnum, UFDouble.ZERO_DBL) == 0) {
      return null;// 没有暂估过
    }
    // 费用暂估时不处理确认成本，所以此处也不考虑确认成本的分摊方式
    String pk_material = stock.getPk_material();
    FeeMnyDivideVO fmdvo = new FeeMnyDivideVO(estmny, estnum, pk_material);
    fmdvo.setBillhpk(stock.getPk_stockps());
    fmdvo.setBillpk(stock.getPk_stockps_b());
    fmdvo.setBilltype(stock.getCbilltypecode());
    return fmdvo;
  }

  private List<FeeMnyDivideVO> convertSettleItemToFeeMnyDivideVO(
      List<SettleBillItemVO> settleItems) {
    if (settleItems == null || settleItems.size() == 0) {
      return null;
    }
    // 把入库单对应的货物结算明细转为继续分摊所需要的结构
    List<FeeMnyDivideVO> mnyDivides = new ArrayList<FeeMnyDivideVO>();
    for (int i = 0, len = settleItems.size(); i < len; i++) {
      String pk_settle = settleItems.get(i).getPk_settlebill();
      String pk_settle_b = settleItems.get(i).getPk_settlebill_b();
      UFDouble innum = settleItems.get(i).getNsettlenum();
      UFDouble inmny = settleItems.get(i).getNgoodsmoney();
      // 红蓝对冲金额为0，取数量 2011-11-17 孙聪、赵玉行、田锋涛一起确认
      if (MathTool.isZero(inmny)) {
        inmny = innum;
      }
      String mrlvid = settleItems.get(i).getPk_material();
      FeeMnyDivideVO fmdvo = new FeeMnyDivideVO(inmny, innum, mrlvid);
      fmdvo.setBillhpk(pk_settle);
      fmdvo.setBillpk(pk_settle_b);
      fmdvo.setBilltype(POBillType.SettleBill.getCode());
      mnyDivides.add(fmdvo);
    }
    return mnyDivides;

  }

  private FeeMnyVO[] convertToFeeMnyVO(FirstDisResultForIT firstReuslt) {
    if (firstReuslt == null) {
      return null;
    }
    FeeMnyVO[] feemnyvo = new FeeMnyVO[1];
    String feemrloid = firstReuslt.getPk_srcmaterial();
    UFDouble feemny = firstReuslt.getDismny();
    feemnyvo[0] = new FeeMnyVO(feemrloid, feemny, this.digit);
    return feemnyvo;
  }

  private FirstDisResultForIT[] findFirstDisResultByStockBid(String stockbid) {
    if (StringUtils.isEmpty(stockbid) || ArrayUtils.isEmpty(this.firstReuslts)) {
      return null;
    }
    List<FirstDisResultForIT> retList = new ArrayList<FirstDisResultForIT>();
    for (FirstDisResultForIT result : this.firstReuslts) {
      String resultbid = result.getStockbid();
      if (!stockbid.equals(resultbid)) {
        continue;
      }
      retList.add(result);
    }
    return retList.toArray(new FirstDisResultForIT[0]);
  }

  private void prepareData(Set<String> m45bids) {
    if (m45bids == null || m45bids.size() == 0) {
      return;
    }
    // 入库单对应的货物结算ITEM
    this.stockbid2SettleItems = new HashMap<String, List<SettleBillItemVO>>();

    String[] inbids = m45bids.toArray(new String[0]);
    SettleBillItemQueryBP settleQuery = new SettleBillItemQueryBP();
    SettleBillItemVO[] items = settleQuery.queryBillItemFromGoodSettle(inbids);
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (SettleBillItemVO item : items) {
      String stockbid = item.getPk_stock_b();
      if (this.stockbid2SettleItems.get(stockbid) == null) {
        this.stockbid2SettleItems.put(stockbid,
            new ArrayList<SettleBillItemVO>());
      }
      this.stockbid2SettleItems.get(stockbid).add(item);
    }
  }

  private List<SecondDisResultForIT> recordDistributeDetail(
      FirstDisResultForIT firstresult, FeeMnyDivideVO[] fmvos) {
    List<SecondDisResultForIT> results = new ArrayList<SecondDisResultForIT>();
    if (firstresult == null) {
      return results;
    }
    if (fmvos != null && fmvos.length != 0) {
      for (FeeMnyDivideVO fmvo : fmvos) {
        SecondDisResultForIT secondResult = new SecondDisResultForIT();
        // secondResult.setBfeedistribute(firstresult.isBfeedistribute());
        secondResult.setNdistributetype(firstresult.getNdistributetype());
        secondResult.setFactorindex(firstresult.getFactorindex());
        secondResult.setDismny(fmvo.getDividedmny());// 分摊金额
        secondResult.setInvoicebid(firstresult.getInvoicebid());
        secondResult.setPk_material(firstresult.getPk_material());
        secondResult.setPk_srcmaterial(firstresult.getPk_srcmaterial());
        secondResult.setPk_supplier(firstresult.getPk_supplier());
        secondResult.setStockbid(firstresult.getStockbid());// 入库单行主键
        secondResult.setCostfactorvo(firstresult.getCostfactorvo());
        secondResult.setPk_allotbillid(fmvo.getBillhpk());// 分摊是所依赖的单据主键
        secondResult.setPk_allotbillbid(fmvo.getBillpk());// 分摊是所依赖的单据行主键
        secondResult.setBymny(fmvo.getBillmny());// 分摊是所依赖的金额
        secondResult.setBynum(fmvo.getBillnum());// //分摊是所依赖的数量
        secondResult.setVallotbilltype(fmvo.getBilltype());
        results.add(secondResult);
      }
    }
    else {
      // 对应其他入库单等不存在二次分摊，直接转换
      SecondDisResultForIT secondResult = new SecondDisResultForIT();
      // secondResult.setBfeedistribute(firstresult.isBfeedistribute());
      secondResult.setNdistributetype(firstresult.getNdistributetype());
      secondResult.setFactorindex(firstresult.getFactorindex());
      secondResult.setDismny(firstresult.getDismny());// 分摊金额
      secondResult.setInvoicebid(firstresult.getInvoicebid());
      secondResult.setPk_material(firstresult.getPk_material());
      secondResult.setPk_srcmaterial(firstresult.getPk_srcmaterial());
      secondResult.setPk_supplier(firstresult.getPk_supplier());
      secondResult.setStockbid(firstresult.getStockbid());// 入库单行主键
      secondResult.setCostfactorvo(firstresult.getCostfactorvo());
      secondResult.setPk_allotbillid(firstresult.getStockid());// 分摊是所依赖的单据主键
      secondResult.setPk_allotbillbid(firstresult.getStockbid());// 分摊是所依赖的单据行主键
      secondResult.setVallotbilltype(firstresult.getStockbilltype());
      results.add(secondResult);
    }
    return results;
  }

  private void stockSecondDistribute(List<SecondDisResultForIT> results,
      StockSettleVO stock) {
    String stockbid = stock.getPk_stockps_b();
    String pk_group = stock.getPk_group();
    // 把第一次分摊结果构造成继续分摊所需要的结构
    FirstDisResultForIT[] firstResults =
        this.findFirstDisResultByStockBid(stockbid);
    for (FirstDisResultForIT result : firstResults) {
      FeeMnyVO[] fmvos = this.convertToFeeMnyVO(result);

      // 把入库单对应的货物结算明细、货物暂估明细转为继续分摊所需要的结构
      List<SettleBillItemVO> settleItems =
          this.stockbid2SettleItems.get(stockbid);
      List<FeeMnyDivideVO> fmdvoList =
          this.convertSettleItemToFeeMnyDivideVO(settleItems);
      if (fmdvoList == null) {
        fmdvoList = new ArrayList<FeeMnyDivideVO>();
      }
      // 把货物暂估信息转为继续分摊所需要的结构
      FeeMnyDivideVO estfmdvo = this.convertGoodEstToFeeMnyDivideVO(stock);
      if (estfmdvo != null) {
        fmdvoList.add(estfmdvo);
      }
      FeeMnyDivideVO[] fmdvos = fmdvoList.toArray(new FeeMnyDivideVO[0]);
      FeeDivideRuleForIT divRule =
          new FeeDivideRuleForIT(pk_group, this.factors, fmdvos);
      if (result.getNdistributetype() == 0) {
        divRule.divide(fmvos);// 劳务发票分摊结果
      }
      else if (result.getNdistributetype() == 1) {
        divRule.divideForDiscount(fmvos);// 折扣发票分摊结果
      }
      else {
        divRule.divideForAjust(fmvos);// 折扣发票分摊结果
      }
      results.addAll(this.recordDistributeDetail(result, fmdvos));
    }
  }
}
