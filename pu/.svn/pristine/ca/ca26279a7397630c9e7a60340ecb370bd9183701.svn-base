package nc.bs.pu.it;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.pu.m25.it.IInvoiceSettleQueryForIT;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.util.AbstrictFirstFeeDistribute;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用结算之第一次费用分摊工具类（后台使用for进出口）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-7 上午08:44:10
 */
public class FirstDistributeUtilForIT extends AbstrictFirstFeeDistribute {
  private SettleBillItemVO[] adjusts;

  // 分摊入库单 key=orderid+ctcode
  private MapList<String, StockSettleVO> stockMapList =
      new MapList<String, StockSettleVO>();

  /** 本币精度 **/
  protected int digit = 2;

  protected SettleBillItemVO[] discounts;

  protected SettleBillItemVO[] fees;

  protected List<FirstDisResultForIT> firstResults =
      new ArrayList<FirstDisResultForIT>();

  protected String group;

  // 入库单单
  protected StockSettleVO[] stocks;

  protected CostfactorViewVO[] totalFactors;

  // 发票bid,合同号
  Map<String, String> bidCodeMap = new HashMap<String, String>();

  public FirstDistributeUtilForIT(SettleBillVO bill, StockSettleVO[] stocks) {
    this.stocks = stocks;
    // 第一次分摊和第二次分摊都进行排序，以保证分摊数据一致
    StockSettleVOUtil.sortByCodeAndBid(this.stocks);
    this.group = BSContext.getInstance().getGroupID();
    this.fees = FeeSettlePrivateUtil.findFeeSettleItem(bill);
    this.discounts = FeeSettlePrivateUtil.findDiscountSettleItem(bill);

    this.adjusts = this.findAdjustSettleItem(bill);
    // 补费用和调整发票的合同号
    List<String> bids = new ArrayList<String>();
    for (SettleBillItemVO fee : this.fees) {
      bids.add(fee.getPk_invoice_b());
    }
    for (SettleBillItemVO ajust : this.adjusts) {
      bids.add(ajust.getPk_invoice_b());
    }
    for (SettleBillItemVO discoun : this.discounts) {
      bids.add(discoun.getPk_invoice_b());
    }

    InvoiceItemVO[] itemVOs = null;
    IInvoiceSettleQueryForIT service =
        NCLocator.getInstance().lookup(IInvoiceSettleQueryForIT.class);
    try {
      itemVOs =
          service
              .queryCtCodeByInvoiceBid(bids.toArray(new String[bids.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (!ArrayUtils.isEmpty(itemVOs)) {
      for (InvoiceItemVO itemVO : itemVOs) {
        this.bidCodeMap.put(itemVO.getPk_invoice_b(), itemVO.getVctcode());
      }
    }

    // 按订单id和合同号划分入库单数组
    for (StockSettleVO vo : stocks) {
      if (!StringUtils.isEmpty(vo.getPk_order_b())) {
        this.stockMapList.put(vo.getPk_order_b(), vo);
      }
      if (!StringUtils.isEmpty(vo.getPk_order())) {
        this.stockMapList.put(vo.getPk_order(), vo);
      }
      if (!StringUtils.isEmpty(vo.getVctcode())) {
        this.stockMapList.put(vo.getVctcode(), vo);
      }
    }

    // 成本要素初始化
    String pk_fiorg = bill.getParentVO().getPk_org();
    String key = InvoiceSettleVO.PK_SRCMATERIAL;
    String[] pks =
        CirVOUtil.getDistinctFieldSet(this.fees, key).toArray(new String[0]);
    this.totalFactors = this.queryCostFactor(pk_fiorg, pks);

    // 初始化本币精度
    this.digit =
        new ScaleObjectFactory.CurrtypeScaleObject(2, 4).getDigit(stocks[0]
            .getCcurrencyid());
  }

  @Override
  public void distribute() {

    // 分摊劳务类
    this.distributeFee();

    // 分摊折扣类
    this.distributeDiscount();

    // 分摊调整
    this.distributeAdjust();
  }

  public void distributeAdjust() {

    // 调整发票分摊先放在这里，暂时不改虚类的逻辑
    if (ArrayUtils.isEmpty(this.adjusts)) {
      return;
    }
    for (SettleBillItemVO adjust : this.adjusts) {
      // 把费用发票转换成费用分摊所需要的结构
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(adjust);

      // 把入库单转换成费用分摊所需要的结构
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO(adjust);

      // 调整货物按同物料过滤
      List<FeeMnyDivideVO> list = new ArrayList<FeeMnyDivideVO>();
      for (FeeMnyDivideVO dvo : fdvos) {
        if (dvo.getMvid().equals(adjust.getPk_material())) {
          list.add(dvo);
        }
      }
      // 如果list为空，则该调整发票不进行分摊
      if (list.size() > 0) {
        FeeDivideRuleForIT t =
            new FeeDivideRuleForIT(this.group, this.totalFactors,
                list.toArray(new FeeMnyDivideVO[list.size()]));

        // 进行分摊
        FeeMnyDivideVO[] feeDivVos = t.divideForAjust(feeMnyVos);

        // 记录费用分摊结果
        this.recordAdjustResult(adjust, feeDivVos);
      }
    }
  }

  @Override
  public void distributeDiscount() {
    if (ArrayUtils.isEmpty(this.discounts)) {
      return;
    }
    for (SettleBillItemVO discount : this.discounts) {
      // 把发票转换成费用分摊所需要的结构
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(discount);

      // 把入库单转换成费用分摊所需要的结构
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO();
      FeeDivideRuleForIT t =
          new FeeDivideRuleForIT(this.group, this.totalFactors, fdvos);

      // 进行分摊
      FeeMnyDivideVO[] feeDivVos = t.divideForDiscount(feeMnyVos);

      // 记录折扣分摊结果
      this.recordDiscountResult(discount, feeDivVos);
    }
  }

  @Override
  public void distributeFee() {
    if (ArrayUtils.isEmpty(this.fees)) {
      return;
    }

    for (SettleBillItemVO fee : this.fees) {
      // 把费用发票转换成费用分摊所需要的结构
      FeeMnyVO[] feeMnyVos = this.convertToFeeMnyVO(fee);

      // 把入库单转换成费用分摊所需要的结构
      FeeMnyDivideVO[] fdvos = this.getFeeMnyDivideVO(fee);
      FeeDivideRuleForIT t =
          new FeeDivideRuleForIT(this.group, this.totalFactors, fdvos);

      // 进行分摊
      FeeMnyDivideVO[] feeDivVos = t.divide(feeMnyVos);

      // 记录费用分摊结果
      this.recordFeeResult(fee, feeDivVos);
    }
  }

  public FirstDisResultForIT[] getFirstResults() {
    return this.firstResults.toArray(new FirstDisResultForIT[0]);
  }

  public CostfactorViewVO[] getTotalFactors() {
    return this.totalFactors;
  }

  /**
   * @param fee
   * @return
   *         过滤可分摊的入库单
   */
  private StockSettleVO[] filterByITDivideScheme(SettleBillItemVO fee) {
    if (!StringUtils.isEmpty(fee.getPk_invoiceorder_b())
        && this.stockMapList.containsKey(fee.getPk_invoiceorder_b())) {
      List<StockSettleVO> list =
          this.stockMapList.get(fee.getPk_invoiceorder_b());
      return list.toArray(new StockSettleVO[list.size()]);
    }
    if (!StringUtils.isEmpty(fee.getPk_invoiceorder())
        && this.stockMapList.containsKey(fee.getPk_invoiceorder())) {
      List<StockSettleVO> list =
          this.stockMapList.get(fee.getPk_invoiceorder());
      return list.toArray(new StockSettleVO[list.size()]);
    }
    if (!StringUtils.isEmpty(this.bidCodeMap.get(fee.getPk_invoice_b()))
        && this.stockMapList.containsKey(this.bidCodeMap.get(fee
            .getPk_invoice_b()))) {
      String ctCode = this.bidCodeMap.get(fee.getPk_invoice_b());
      List<StockSettleVO> list = this.stockMapList.get(ctCode);
      return list.toArray(new StockSettleVO[list.size()]);
    }
    return this.stocks;
  }

  private SettleBillItemVO[] findAdjustSettleItem(SettleBillVO vo) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      if (EnumMatchRowType.AdjustGoods.value().equals(item.getFrowtype())) {
        items.add(item);
      }
    }
    return items.toArray(new SettleBillItemVO[0]);
  }

  private void recordAdjustResult(SettleBillItemVO adjust,
      FeeMnyDivideVO[] feeDivVos) {
    // 构造结构：<入库单行ID,分摊VO>
    Map<String, FeeMnyDivideVO> bid_dividevo_map = null;
    bid_dividevo_map = new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO vo : feeDivVos) {
      bid_dividevo_map.put(vo.getBillpk(), vo);
    }
    for (int i = 0, len = this.stocks.length; i < len; i++) {
      String bid = this.stocks[i].getPk_stockps_b();
      if (bid_dividevo_map.get(bid) == null) {
        continue;
      }
      // 增量式设置要素的分摊金额
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      // 本张入库单曾经已分摊的折扣金额
      UFDouble oldMny = MathTool.nvl(this.stocks[i].getNadjustmny());
      UFDouble newMny = MathTool.add(oldMny, addMny);
      this.stocks[i].setNadjustmny(newMny);

      FirstDisResultForIT result = new FirstDisResultForIT();
      result.setStockid(this.stocks[i].getPk_stockps());
      result.setStockbid(this.stocks[i].getPk_stockps_b());
      result.setStockbilltype(this.stocks[i].getCbilltypecode());
      result.setInvoicebid(adjust.getPk_invoice_b());
      result.setNdistributetype(2);
      result.setFactorindex(-1);
      result.setPk_material(adjust.getPk_material());
      result.setPk_srcmaterial(adjust.getPk_srcmaterial());
      result.setPk_supplier(adjust.getPk_supplier());
      result.setDismny(addMny);
      this.firstResults.add(result);
    }
  }

  private void recordDiscountResult(SettleBillItemVO discount,
      FeeMnyDivideVO[] feeDivVos) {
    // 构造结构：<入库单行ID,分摊VO>
    Map<String, FeeMnyDivideVO> bid_dividevo_map = null;
    bid_dividevo_map = new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO vo : feeDivVos) {
      bid_dividevo_map.put(vo.getBillpk(), vo);
    }
    for (int i = 0, len = this.stocks.length; i < len; i++) {
      String bid = this.stocks[i].getPk_stockps_b();
      if (bid_dividevo_map.get(bid) == null) {
        continue;
      }
      // 增量式设置要素的分摊金额
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      // 本张入库单曾经已分摊的折扣金额
      UFDouble oldMny = MathTool.nvl(this.stocks[i].getNdiscount());
      UFDouble newMny = MathTool.add(oldMny, addMny);
      this.stocks[i].setNdiscount(newMny);

      FirstDisResultForIT result = new FirstDisResultForIT();
      result.setStockid(this.stocks[i].getPk_stockps());
      result.setStockbid(this.stocks[i].getPk_stockps_b());
      result.setStockbilltype(this.stocks[i].getCbilltypecode());
      result.setInvoicebid(discount.getPk_invoice_b());
      result.setNdistributetype(1);
      result.setFactorindex(-1);
      result.setPk_material(discount.getPk_material());
      result.setPk_srcmaterial(discount.getPk_srcmaterial());
      result.setPk_supplier(discount.getPk_supplier());
      result.setDismny(addMny);
      this.firstResults.add(result);
    }
  }

  protected FeeMnyVO[] convertToFeeMnyVO(SettleBillItemVO fee) {
    // 把劳务折扣类发票转换为费用分摊所需要的解构
    if (fee == null) {
      return null;
    }
    FeeMnyVO[] feemnyvo = new FeeMnyVO[1];
    String feemrloid = fee.getPk_srcmaterial();
    UFDouble feemny = fee.getNmoney();// 费用结算金额
    feemnyvo[0] = new FeeMnyVO(feemrloid, feemny, this.digit);
    return feemnyvo;
  }

  protected CostfactorViewVO findFactorVO(SettleBillItemVO fee) {
    // 根据成本要素主键获得对应的成本要素视图VO(用于判断是否影响成本)
    if (fee == null || this.totalFactors == null) {
      return null;
    }
    String fiorg = fee.getPk_org();
    String pk_mrl = fee.getPk_srcmaterial();
    for (CostfactorViewVO view : this.totalFactors) {
      // 使用财务组织+物料OID,查找成本要素
      if (!fiorg.equals(view.getPk_org())) {
        continue;
      }
      if (!pk_mrl.equals(view.getPk_srcmaterial())) {
        continue;
      }
      return view;
    }
    String msg =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0031")/* @res "对劳务发票进行分摊时，出现找不到对应的成本要素定义！" */;
    ExceptionUtils.wrappBusinessException(msg);
    return null;
  }

  protected FeeMnyDivideVO[] getFeeMnyDivideVO() {
    if (ArrayUtils.isEmpty(this.stocks)) {
      return null;
    }
    // 如果入库单作过暂估，则费用分摊时按照费用金额/（暂估数量或者金额+未暂估部分累计已结算数量或者金额）进行分摊；
    // 如果入库单传确认应付和成本，则费用分摊时按照费用金额/确认成本的数量或者金额。
    FeeMnyDivideVO[] mnyDivides = new FeeMnyDivideVO[this.stocks.length];
    for (int i = 0, len = this.stocks.length; i < len; i++) {
      StockSettleVO vo = this.stocks[i];
      String stockbid = vo.getPk_stockps_b();
      UFDouble disnum;
      UFDouble dismny;
      if (EnumToIAFlag.ConfirmToIA.value().equals(vo.getFdirtoiatype())) {
        // 确认过成本直接使用确认成本的数据
        disnum = vo.getNinnum();
        // wuxla v61
        // dismny = vo.getNmny();
        dismny = vo.getNcalcostmny();
      }
      else {
        // 分摊数量 = 累计结算数量－累计暂估部分结算数量+暂估数量
        disnum =
            MathTool.add(
                MathTool.sub(vo.getNaccumsettlenum(),
                    vo.getNaccestcoststtlnum()), vo.getNestnum());
        // 分摊金额= 暂估前累计结算金额+暂估本币含税金额(本币含税金额)
        // dismny = MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestmny());
        // wuxla v61
        dismny =
            MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestcalcostmny());
      }
      String mrlvid = vo.getPk_material();
      // 红蓝对冲金额为0，取数量. 2011-11-17 孙聪、赵玉行、田锋涛一起确认
      if (MathTool.isZero(dismny)) {
        dismny = disnum;
      }
      mnyDivides[i] = new FeeMnyDivideVO(dismny, disnum, mrlvid);
      mnyDivides[i].setBillpk(stockbid);
    }
    return mnyDivides;
  }

  protected FeeMnyDivideVO[] getFeeMnyDivideVO(SettleBillItemVO fee) {
    if (ArrayUtils.isEmpty(this.stocks)) {
      return null;
    }
    // 对入库单进行过滤，找到指定条件的入库单数组作为分摊的分母
    StockSettleVO[] filterVOs = this.filterByITDivideScheme(fee);

    // 如果入库单作过暂估，则费用分摊时按照费用金额/（暂估数量或者金额+未暂估部分累计已结算数量或者金额）进行分摊；
    // 如果入库单传确认应付和成本，则费用分摊时按照费用金额/确认成本的数量或者金额。
    FeeMnyDivideVO[] mnyDivides = new FeeMnyDivideVO[filterVOs.length];
    for (int i = 0, len = filterVOs.length; i < len; i++) {
      StockSettleVO vo = filterVOs[i];
      String stockbid = vo.getPk_stockps_b();
      UFDouble disnum;
      UFDouble dismny;
      if (EnumToIAFlag.ConfirmToIA.value().equals(vo.getFdirtoiatype())) {
        // 确认过成本直接使用确认成本的数据
        disnum = vo.getNinnum();
        // wuxla v61
        // dismny = vo.getNmny();
        dismny = vo.getNcalcostmny();
      }
      else {
        // 分摊数量 = 累计结算数量－累计暂估部分结算数量+暂估数量
        disnum =
            MathTool.add(
                MathTool.sub(vo.getNaccumsettlenum(),
                    vo.getNaccestcoststtlnum()), vo.getNestnum());
        // 分摊金额= 暂估前累计结算金额+暂估本币含税金额(本币含税金额)
        // dismny = MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestmny());
        // wuxla v61
        dismny =
            MathTool.add(vo.getNaccpreeststtlmny(), vo.getNestcalcostmny());
      }
      String mrlvid = vo.getPk_material();
      // 红蓝对冲金额为0，取数量. 2011-11-17 孙聪、赵玉行、田锋涛一起确认
      if (MathTool.isZero(dismny)) {
        dismny = disnum;
      }
      mnyDivides[i] = new FeeMnyDivideVO(dismny, disnum, mrlvid);
      mnyDivides[i].setBillpk(stockbid);
    }
    return mnyDivides;
  }

  protected void recordFeeResult(SettleBillItemVO fee,
      FeeMnyDivideVO[] feeDivVos) {
    if (fee == null || ArrayUtils.isEmpty(feeDivVos)) {
      return;
    }
    CostfactorViewVO factorvo = this.findFactorVO(fee);
    if (factorvo == null) {
      return;
    }

    // 构造结构：<入库单行ID,分摊VO>
    Map<String, FeeMnyDivideVO> bid_dividevo_map =
        new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO vo : feeDivVos) {
      bid_dividevo_map.put(vo.getBillpk(), vo);
    }
    for (StockSettleVO stock : this.stocks) {
      String bid = stock.getPk_stockps_b();
      if (bid_dividevo_map.get(bid) == null) {
        continue;
      }
      // 增量式设置结算金额
      UFDouble addMny = bid_dividevo_map.get(bid).getDividedmny();
      int index = this.getFactorIndex(factorvo) - 1;// 要素中记录了顺序号
      // 增量式设置要素的分摊金额
      UFDouble oldMny = ICostfactorDiscountUtil.getNcostfactor(stock, index);
      UFDouble newMny = MathTool.add(oldMny, addMny);
      ICostfactorDiscountUtil.setNcostfactor(stock, index, newMny);

      // 记录分摊明细
      FirstDisResultForIT result = new FirstDisResultForIT();
      result.setStockbid(stock.getPk_stockps_b());
      result.setStockid(stock.getPk_stockps());
      result.setInvoicebid(fee.getPk_invoice_b());
      result.setNdistributetype(0);
      result.setFactorindex(index);
      result.setDismny(addMny);
      result.setPk_material(fee.getPk_material());
      result.setPk_srcmaterial(fee.getPk_srcmaterial());
      result.setPk_supplier(fee.getPk_supplier());
      result.setCostfactorvo(factorvo);
      this.firstResults.add(result);
    }
  }

}
