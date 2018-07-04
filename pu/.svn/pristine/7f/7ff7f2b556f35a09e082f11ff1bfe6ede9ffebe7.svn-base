package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.merge.BillItemCalData;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.merge.rule.MatchMergeReasonWasteNumRule;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1：货物结算之超类
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-25 下午03:25:16
 */
public abstract class GoodsMatchMerge extends MatchMerge {

  /**
   * <p>
   * <b>按物料分好的数据结构：</b>
   * <ul>
   * <li>功能条目1
   * <li>功能条目2
   * <li>...
   * </ul>
   * <p>
   * <b>变更历史（可选）：</b>
   * <p>
   * XXX版本增加XXX的支持。
   * <p>
   * <p>
   * 
   * @version 本版本号
   * @since 上一版本号
   * @author wangyf
   * @time 2010-3-15 下午03:38:37
   */
  protected class DataClassify {

    /** 已结算完成的发票LIST */
    private ArrayList<InvoiceSettleVO> m_listFinishedInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** 已结算完成的库存LIST */
    private ArrayList<StockSettleVO> m_listFinishedStock =
        new ArrayList<StockSettleVO>();

    /** 整理完的负发票 */
    private ArrayList<InvoiceSettleVO> m_listMinusInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** 整理完的负库存 */
    private ArrayList<StockSettleVO> m_listMinusStock =
        new ArrayList<StockSettleVO>();

    /** 原始的发票LIST */
    private ArrayList<InvoiceSettleVO> m_listOrigInvoice = null;

    /** 原始的库存LIST */
    private ArrayList<StockSettleVO> m_listOrigStock = null;

    /** 整理完的正发票 */
    private ArrayList<InvoiceSettleVO> m_listPlusInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** 整理完的正库存 */
    private ArrayList<StockSettleVO> m_listPlusStock =
        new ArrayList<StockSettleVO>();

    /** 未结算完成的发票LIST */
    private ArrayList<InvoiceSettleVO> m_listUnfinishedInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** 未结算完成的库存LIST */
    private ArrayList<StockSettleVO> m_listUnfinishedStock =
        new ArrayList<StockSettleVO>();

    /** 物料ID */
    private String m_Pk_srcMaterial = null;

    /**
     * DataClassify 的构造子
     * 
     * @param listInvoice
     * @param listStock
     */
    DataClassify(ArrayList<InvoiceSettleVO> listInvoice,
        ArrayList<StockSettleVO> listStock) {
      this.m_listOrigInvoice = listInvoice;
      this.m_listOrigStock = listStock;
    }

    /**
     * DataClassify 的构造子
     * 
     * @param listInvoice
     * @param listStock
     */
    DataClassify(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock) {

      if (GoodsMatchMerge.this.getInvoiceVOs() != null) {
        // 对待结算数据进行分类：正负分别归类
        ArrayList<InvoiceSettleVO> listInvoice =
            new ArrayList<InvoiceSettleVO>();
        for (InvoiceSettleVO invoiceVO : voaInvoice) {
          listInvoice.add(invoiceVO);
        }
        this.m_listOrigInvoice = listInvoice;
      }

      if (GoodsMatchMerge.this.getStockVOs() != null) {
        ArrayList<StockSettleVO> listStock = new ArrayList<StockSettleVO>();
        for (StockSettleVO stockVO : voaStock) {
          listStock.add(stockVO);
        }
        this.m_listOrigStock = listStock;
      }

    }

    /**
     * 整理数据
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * <p>
     * 
     * @author wangyf
     * @time 2010-2-4 下午04:29:55
     */
    protected void classifyPlusMinus() {
      // 按正负分类
      if (this.m_listOrigInvoice != null) {
        this.m_Pk_srcMaterial =
            this.m_listOrigInvoice.get(0).getPk_srcmaterial();
        InvoiceSettleVO voInvoice = null;
        for (int i = 0; i < this.m_listOrigInvoice.size(); i++) {
          voInvoice = this.m_listOrigInvoice.get(i);
          UFDouble currentSettleNum = voInvoice.getNcurrentsettlenum();
          currentSettleNum = MathTool.nvl(currentSettleNum);
          if (currentSettleNum.compareTo(UFDouble.ZERO_DBL) > 0) {
            this.m_listPlusInvoice.add(voInvoice);
          }
          else {
            this.m_listMinusInvoice.add(voInvoice);
          }
        }
      }

      if (this.m_listOrigStock != null) {
        this.m_Pk_srcMaterial = this.m_listOrigStock.get(0).getPk_srcmaterial();
        StockSettleVO voStock = null;
        for (int i = 0; i < this.m_listOrigStock.size(); i++) {
          voStock = this.m_listOrigStock.get(i);
          UFDouble currentSettleNum = voStock.getNcurrentsettlenum();
          currentSettleNum = MathTool.nvl(currentSettleNum);
          if (currentSettleNum.compareTo(UFDouble.ZERO_DBL) > 0) {
            this.m_listPlusStock.add(voStock);
          }
          else {
            this.m_listMinusStock.add(voStock);
          }
        }
      }
    }

    protected ArrayList<InvoiceSettleVO> getFinishedInvoices() {
      return this.m_listFinishedInvoice;
    }

    protected ArrayList<StockSettleVO> getFinishedStockes() {
      return this.m_listFinishedStock;
    }

    protected ArrayList<InvoiceSettleVO> getMinusInvoices() {
      return this.m_listMinusInvoice;
    }

    protected ArrayList<StockSettleVO> getMinusStockes() {
      return this.m_listMinusStock;
    }

    protected ArrayList<InvoiceSettleVO> getOrigInvoices() {
      return this.m_listOrigInvoice;
    }

    protected ArrayList<StockSettleVO> getOrigStockes() {
      return this.m_listOrigStock;
    }

    protected String getPk_srcmaterial() {
      return this.m_Pk_srcMaterial;
    }

    protected ArrayList<InvoiceSettleVO> getPlusInvoices() {
      return this.m_listPlusInvoice;
    }

    protected ArrayList<StockSettleVO> getPlusStockes() {
      return this.m_listPlusStock;
    }

    protected ArrayList<InvoiceSettleVO> getUnfinishedInvoices() {
      return this.m_listUnfinishedInvoice;
    }

    protected ArrayList<StockSettleVO> getUnfinishedStockes() {
      return this.m_listUnfinishedStock;
    }

    /**
     * 剩余的单据
     * <p>
     * <b>使用示例:</b>
     * <p>
     * <b>参数说明</b>
     * <p>
     * 
     * @author wangyf
     * @time 2010-3-8 上午09:33:10
     */
    protected void sumupResidualBill() {
      // ---------发票
      if (this.getMinusInvoices() != null) {
        this.m_listUnfinishedInvoice.addAll(this.getMinusInvoices());
      }
      if (this.getPlusInvoices() != null) {
        this.m_listUnfinishedInvoice.addAll(this.getPlusInvoices());
      }

      // ---------库存单据
      if (this.getMinusStockes() != null) {
        this.m_listUnfinishedStock.addAll(this.getMinusStockes());
      }
      if (this.getPlusStockes() != null) {
        this.m_listUnfinishedStock.addAll(this.getPlusStockes());
      }
    }
  }

  protected static final int CombineType_MinusInvoiceStock = 1;

  /** 入库单、发票的合并类型：正正还是负负 */
  protected static final int CombineType_PlusInvoiceStock = 0;

  /** 按不同物料分成的集合 */
  private DataClassify[] m_dataClassify = null;

  public GoodsMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
  }

  public GoodsMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);
  }

  /**
   * 把入库单、发票合并为结算单表体
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice 发票VO
   * @param voStock 库存VO
   * @param bStockLastTimeAndHaveMatchAll 是否最后库存单据且库存单据必须匹配完成，此单据只对正数单据有效
   * @return <p>
   * @author wangyf
   * @time 2009-7-3 上午11:47:52
   */
  private SettleBillItemVO createItemByInvoiceStock(InvoiceSettleVO voInvoice,
      StockSettleVO voStock, boolean bStockLastTimeAndHaveMatchAll) {

    UFDouble dInvoiceWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice),
            voInvoice.getNcurrentaccumsettlenum());
    UFDouble dStockWillSettleNum =
        MathTool.sub(voStock.getNcurrentsettlenum(),
            voStock.getNcurrentaccumsettlenum());
    // 最小数量、每个VO剩余的数量。如果发票已是最后一个，则取入库单数量为结算数量
    UFDouble dWillSettleNum = null;
    UFDouble increaseNum = null;
    if (bStockLastTimeAndHaveMatchAll) {
      dWillSettleNum = dStockWillSettleNum;
      increaseNum = MathTool.sub(dInvoiceWillSettleNum, dStockWillSettleNum);// 得到升溢的数量
    }
    else {
      dWillSettleNum =
          MathTool.absCompareTo(dStockWillSettleNum, dInvoiceWillSettleNum) > 0 ? dInvoiceWillSettleNum
              : dStockWillSettleNum;
    }
    // 发票的真正结算数量：库存10，发票2，但此时库存全部结算完，则此值＝2；库存2，发票10，此值＝2。
    UFDouble dActualInvoiceSettleNum =
        MathTool.absCompareTo(dWillSettleNum, dInvoiceWillSettleNum) < 0 ? dWillSettleNum
            : dInvoiceWillSettleNum;

    // 计算表体最需要计算的部分
    BillItemCalData calData =
        this.calItemData(voInvoice, dWillSettleNum, dActualInvoiceSettleNum,
            increaseNum);

    // --------------- 更新已结算的发票、库存VO
    // 更新发票：累计结算数量、总金额、发票金额
    // 本次结算的发票数量可能少于真正结算的数量，因为库存全部结算了。
    voInvoice.setNcurrentaccumsettlenum(MathTool.add(
        voInvoice.getNcurrentaccumsettlenum(), dActualInvoiceSettleNum));
    voInvoice.setNcurrentaccumtotalsettlemny(MathTool.add(
        voInvoice.getNcurrentaccumtotalsettlemny(), calData.getNmoney()));
    voInvoice.setNcurrentaccuminvoicesettlemny(MathTool.add(
        voInvoice.getNcurrentaccuminvoicesettlemny(), calData.getNgoodsmoney())
        .sub(MathTool.nvl(calData.getNdiscount())));
    // 更新库存：累计结算数量
    voStock.setNcurrentaccumsettlenum(MathTool.add(
        voStock.getNcurrentaccumsettlenum(), dWillSettleNum));

    // --------------- 形成结算单VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem
        .setFrowtype((Integer) EnumMatchRowType.StockInvoiceMatch.value());
    // 取自计算结果
    voBillItem.setNsettlenum(dWillSettleNum);
    // 如果没有升溢或损溢，则直接取发票单价，否则＝金额/数量
    // 如果金额与未结算金额相同，而且单价非空，非零时按单价折算
    voBillItem.setNprice(calData.getNprice());
    voBillItem.setNmoney(calData.getNmoney());
    voBillItem.setNgoodsmoney(calData.getNgoodsmoney());
    voBillItem.setNgoodsprice(calData.getNgoodsprice());
    // 货物金额、成本要素、折扣
    ICostfactorDiscountUtil.setNcostfactor(voBillItem, calData.getNaFactor());
    voBillItem.setNdiscount(calData.getNdiscount());

    // 取自发票
    voBillItem.setPk_group(voInvoice.getPk_group());
    voBillItem.setPk_org(voInvoice.getPk_org());
    voBillItem.setVinvoicecode(voInvoice.getVbillcode());
    voBillItem.setPk_invoice(voInvoice.getPk_invoice());
    voBillItem.setPk_invoice_b(voInvoice.getPk_invoice_b());
    voBillItem.setVinvoicetrantype(voInvoice.getCtrantypeid());
    // 合理损耗数量
    voBillItem.setNreasonalwastnum(calData.getNReasonWasteNum());
    // 合理损耗单价为发票单价,合理损耗金额=合理损耗数量*合理损耗单价
    voBillItem.setNreasonalwastprice(calData.getNReasonWastePrice());
    voBillItem.setNreasonalwastmny(calData.getNReasonWasteMny());
    voBillItem.setPk_invoiceorder(voInvoice.getPk_order());
    voBillItem.setPk_invoiceorder_b(voInvoice.getPk_order_b());
    // mengjian by 20141203记录发票日期
    voBillItem.setInvoicebilldate(voInvoice.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 记录进口合同号
    voBillItem.setVitctcode(voStock.getVctcode());

    // 取自库存
    voBillItem.setPk_costregion(voStock.getPk_costregion());
    voBillItem.setPk_material(voStock.getPk_material());
    voBillItem.setPk_srcmaterial(voStock.getPk_srcmaterial());
    voBillItem.setVstockcode(voStock.getVbillcode());
    voBillItem.setVstockbilltype(voStock.getCbilltypecode());
    voBillItem.setVstocktrantype(voStock.getCtrantypeid());
    voBillItem.setPk_stock(voStock.getPk_stockps());
    voBillItem.setPk_stock_b(voStock.getPk_stockps_b());
    voBillItem.setPk_arrstockorg(voStock.getPk_org());
    voBillItem.setPk_arrstockorg_v(voStock.getPk_org_v());
//    voBillItem.setPk_supplier(voStock.getPk_supplier());
    //合并通版补丁，取发票供应商NCdp205389141
    voBillItem.setPk_supplier(voInvoice.getPk_supplier());
    voBillItem.setPk_stockorder(voStock.getPk_order());
    voBillItem.setPk_stockorder_b(voStock.getPk_order_b());
    // 部门、人员
    voBillItem.setPk_dept(voStock.getPk_dept());
    voBillItem.setPk_dept_v(voStock.getPk_dept_v());
    voBillItem.setPk_psndoc(voStock.getPk_psndoc());
    // mengjian by 20141203记录入库日期
    voBillItem.setStockbilldate(voStock.getDbilldate());
    return voBillItem;
  }

  /**
   * 根据财务组织匹配成本域
   * 
   * @param pk_fiorg
   * @return
   */
  private String getCostregionByFiOrg(String pk_fiorg) {
    Map<String, String> fiCostMap =
        CostRegionPubService.getCostRegionMapByFinanceOrgIDS(new String[] {
          pk_fiorg
        });
    return fiCostMap.get(pk_fiorg);

  }

  /**
   * 根据库存组织匹配成本域
   * 
   * @param pk_stockorg
   * @return
   */
  private String getCostregionByStockOrg(String pk_stockorg) {
    Map<String, String> costregionMap =
        CostRegionPubService.getCostRegionMapByStockOrgIDS(new String[] {
          pk_stockorg
        });
    return costregionMap.get(pk_stockorg);
  }

  /**
   * 根据库存组织+仓库匹配成本域
   * 
   * @param pk_stockorg
   * @param pk_stordoc
   * @return
   */
  private String getCostregionByStockOrgAndStordoc(String pk_stockorg,
      String pk_stordoc) {
    Map<String, String> costregionMap =
        CostRegionPubService.queryCostRegionIDSByStockOrgVOSAndStordocVOS(
            new String[] {
              pk_stockorg
            }, new String[] {
              pk_stordoc
            });
    String key = pk_stockorg + "|" + pk_stordoc;
    return costregionMap.get(key);

  }

  // 进行上述的多点判断是考虑到此方法在二重循环内部执行，可能有效率问题

  /**
   * 直运无入库的成本域查询
   * 
   * @param pk_fiOrg
   * @param pk_stockorg
   * @param stockdoc
   * @return
   */
  private String getCostRegionForDTrans(String pk_fiOrg, String pk_stockorg,
      String pk_stordoc) {
    // TODO tianft 查找成本域的过程可提取到公共类
    String costRegion = null;
    // 1.先按 库存组织+仓库 匹配
    if (StringUtils.isNotEmpty(pk_stockorg)
        && StringUtils.isNotEmpty(pk_stordoc)) {
      costRegion =
          this.getCostregionByStockOrgAndStordoc(pk_stockorg, pk_stordoc);
    }
    // 2.按 库存组织 匹配
    if (StringUtils.isEmpty(costRegion) && StringUtils.isNotEmpty(pk_stockorg)) {
      costRegion = this.getCostregionByStockOrg(pk_stockorg);
    }
    // 1.按财务组织 匹配
    if (StringUtils.isEmpty(costRegion)) {
      costRegion = this.getCostregionByFiOrg(pk_fiOrg);
    }

    return costRegion;

  }

  private boolean isLastMatch(List<InvoiceSettleVO> invcLst,
      List<StockSettleVO> stockLst, Integer invcIndx, Integer stockInt) {
    // 如果发票和入库单都已经是最后一张，则一定是最后一次结算
    if (invcIndx.intValue() == invcLst.size() - 1
        && stockInt.intValue() == stockLst.size() - 1) {
      return true;
    }
    InvoiceSettleVO invcVo = invcLst.get(invcIndx.intValue());
    // 如果没有升溢，直接返回false，不需要处理
    if (MathTool.isZero(this.getIncreaseNum(invcVo.getPk_srcmaterial()))) {
      return false;
    }
    StockSettleVO stockVo = stockLst.get(stockInt.intValue());
    UFDouble invcWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(invcVo),
            invcVo.getNcurrentaccumsettlenum());
    UFDouble StockWillSettleNum =
        MathTool.sub(stockVo.getNcurrentsettlenum(),
            stockVo.getNcurrentaccumsettlenum());
    if (MathTool.equals(invcWillSettleNum, StockWillSettleNum)) {
      return true; // 如果入库未结算数量与发票未结算数量相等，认为是最后一次结算吧，也没有影响
    }
    else if (MathTool.lessThan(StockWillSettleNum, invcWillSettleNum)) {
      return false;// 如果入库可结算数量小于发票可结算数量，则一定不是最后一次
    }
    // 如果入库单可结算数量大于发票可结算数量，就看一下还有没有和这张库单匹配的发票
    // 如果没有，这就是最后一次，如有就不是
    for (int i = invcIndx.intValue() + 1; i < invcLst.size(); ++i) {
      if (this.canMatch(invcLst.get(i), stockVo)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 计算发票表体最需要计算的部分
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkCostRegion 成本域
   * @param voInvoice 发票
   * @param dWillSettleNum 待结算数量
   * @param dActualInvoiceSettleNum 真正的发票结算数量
   * @return <p>
   * @author wangyf
   * @time 2010-2-24 下午03:40:38
   */
  protected BillItemCalData calItemData(InvoiceSettleVO voInvoice,
      UFDouble dWillSettleNum, UFDouble dActualInvoiceSettleNum,
      UFDouble inCreaseNum) {
    // 发票已结算完成，则结算数量取入库数量、结算金额取发票未结算金额，结算单价＝本次结算金额/本次结算数量得到；
    // 其他情况下：
    // ---如果进行过费用分摊 则结算单价＝本次结算金额/本次结算数量得到；否则＝发票单价
    UFDouble dWillSettlePrice = null;
    UFDouble dWillSettleMoney = null;
    UFDouble[] daWillSettleFactor = new UFDouble[CostfactorVO.MAX_NUM];
    UFDouble dWillSettleDiscount = null;
    UFDouble dWillSettleInvoiceMoney = null;
    // 发票要结算完成
    if (MathTool.add(voInvoice.getNcurrentaccumsettlenum(),
        dActualInvoiceSettleNum).compareTo(
        InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)) == 0) {
      // 待结算金额
      dWillSettleMoney =
          MathTool.sub(voInvoice.getNcurrentotalsettlemny(),
              voInvoice.getNcurrentaccumtotalsettlemny());
      // 如果发票进行过分摊、或结算数量<>发票数量，则单价需要重新
      if (InvoiceSettleVOUtil.isAllot(voInvoice)
          || dWillSettleNum.compareTo(dActualInvoiceSettleNum) != 0) {
        dWillSettlePrice = dWillSettleMoney.div(dWillSettleNum);
      }
      else {
        dWillSettlePrice = voInvoice.getNprice();
      }
      dWillSettlePrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(dWillSettlePrice,
              voInvoice.getCcurrencyid());
      // 待结算的成本要素
      UFDouble dFactorPrice = null;
      UFDouble dAccumSettledFactor = null;

      for (int i = 0; i < daWillSettleFactor.length; i++) {
        if (MathTool.compareTo(
            ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1),
            UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        // 成本要素单价=成本要素/发票要结算的数量
        dFactorPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1).div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                voInvoice.getCcurrencyid());

        // 已结算的成本要素金额
        try {
          dAccumSettledFactor =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  voInvoice.getNcurrentaccumsettlenum().multiply(dFactorPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
        // 本次是最后一次的成本要素金额
        daWillSettleFactor[i] =
            MathTool.sub(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1),
                dAccumSettledFactor);
      }
      // 折扣
      if (MathTool.compareTo(voInvoice.getNdiscount(), UFDouble.ZERO_DBL) != 0) {
        // 折扣单价
        UFDouble dPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                voInvoice.getNdiscount().div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // 本次是最后一次的折扣
        try {
          dWillSettleDiscount =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  dActualInvoiceSettleNum.multiply(dPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }

      // 发票金额
      dWillSettleInvoiceMoney =
          MathTool.sub(voInvoice.getNcurrentinvoicesettlemny(),
              voInvoice.getNcurrentaccuminvoicesettlemny());
    }
    // 发票未结算完成
    else {
      // 单价
      if (InvoiceSettleVOUtil.isAllot(voInvoice)) {
        dWillSettlePrice =
            voInvoice.getNcurrentotalsettlemny().div(
                InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice));
      }
      else {
        dWillSettlePrice = voInvoice.getNprice();
      }
      dWillSettlePrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(dWillSettlePrice,
              this.getSettleEnv().getOrgCurr());
      // 金额
      dWillSettleMoney =
          ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
              dActualInvoiceSettleNum.multiply(dWillSettlePrice),
              this.getSettleEnv().getOrgCurr());
      // 待结算的成本要素
      UFDouble dFactorPrice = null;
      for (int i = 0; i < daWillSettleFactor.length; i++) {
        if (ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1) == null) {
          continue;
        }
        // 成本要素单价=成本要素/发票要结算的数量
        dFactorPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1).div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // 当次要结算的成本要素金额
        daWillSettleFactor[i] = dActualInvoiceSettleNum.multiply(dFactorPrice);
      }
      // 折扣
      if (voInvoice.getNdiscount() != null) {
        // 折扣单价
        UFDouble dPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                voInvoice.getNdiscount().div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // 折扣=数量*折扣单价
        try {
          dWillSettleDiscount =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  dActualInvoiceSettleNum.multiply(dPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
      // 发票金额
      try {
        dWillSettleInvoiceMoney =
            ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                dActualInvoiceSettleNum.multiply(voInvoice.getNprice()),
                this.getSettleEnv().getOrgCurr());
      }
      catch (Exception ex) {
        ExceptionUtils.wrappException(ex);
      }
    }

    BillItemCalData calData = new BillItemCalData();
    calData.setNprice(dWillSettlePrice);
    calData.setNmoney(dWillSettleMoney);
    calData.setNgoodsmoney(MathTool.add(dWillSettleInvoiceMoney,
        dWillSettleDiscount));
    // 如果没有折扣，则货物单价＝单价；否则再算
    if (MathTool.compareTo(dWillSettleDiscount, UFDouble.ZERO_DBL) == 0) {
      calData.setNgoodsprice(calData.getNprice());
    }
    else {
      calData.setNgoodsprice(ScaleUtils.getScaleUtilAtBS()
          .adjustSoPuPriceScale(calData.getNgoodsmoney().div(dWillSettleNum),
              this.getSettleEnv().getOrgCurr()));
    }
    // 发票金额、成本要素、折扣
    calData.setNaFactor(daWillSettleFactor);
    calData.setNdiscount(dWillSettleDiscount);

    // 对合理损耗，升溢进行处理
    MatchMergeReasonWasteNumRule reasonRule =
        new MatchMergeReasonWasteNumRule(this.getSettleEnv(), voInvoice,
            calData);
    calData =
        reasonRule.calReasonWasteNum(dActualInvoiceSettleNum, dWillSettleNum,
            inCreaseNum);
    return calData;

  }

  /**
   * 检查入库单和发票能否匹配
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voStock 入库单
   * @param voInvoice 发票
   * @return <p>
   * @author wangyf
   * @time 2010-1-20 上午10:49:51
   */
  protected boolean canMatch(InvoiceSettleVO voInvoice, StockSettleVO voStock) {

    // 某一方结算完成不再结算
    if (InvoiceSettleVOUtil.isCurrentSettleFinished(voInvoice)
        || StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
      return false;
    }

    // 自动结算、异物料结算、同物料结算共用合并算法
    // ----采购入库单已作过暂估应付或者已确认应付，则不允许该采购入库单与已传应付的采购发票进行结算；
    // ----采购入库单已确认应付，则不允许与币种与采购入库单币种不同的采购发票进行结算。

    // 订单分状态关闭影响货票结算相关规则：
    // 26. 参照订单或者非自制的采购入库单生成的采购发票不允许与其他采购订单的入库单进行结算；
    // 27. 自制的采购发票或者参照自制的采购入库单生成的采购发票可以与任意的采购入库单进行结算。

    // 发票是订单的发票，则只能匹配此订单的入库单
    if (!PubAppTool.isEqual(voInvoice.getPk_order_b(), null)
        && !PubAppTool.isEqual(voInvoice.getPk_order_b(),
            voStock.getPk_order_b())) {
      return false;
    }

    // 采购入库单已确认应付，则不允许与币种与采购入库单币种不同的采购发票进行结算。
    if (EnumToAPFlag.ConfirmToAP.value().equals(voStock.getFdirtoaptype())
        && !PubAppTool.isEqual(voStock.getCorigcurrencyid(),
            voInvoice.getCorigcurrencyid())) {
      return false;
    }

    // 采购入库单已作过暂估应付或者已确认应付，则不允许该采购入库单与已传应付的采购发票进行结算；
    if (!EnumToAPFlag.NotToAP.value().equals(voStock.getFdirtoaptype())
        && ValueUtils.getBoolean(voInvoice.getBapflag())) {
      return false;
    }

    return true;
  }

  /**
   * 纯由发票生成ITEM
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   * @param dWillSettleNum
   * @param rowflag 行标志
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 下午05:01:37
   */
  protected SettleBillItemVO createItemByInvoice(InvoiceSettleVO voInvoice,
      UFDouble dWillSettleNum, EnumMatchRowType rowflag) {

    // 计算表体最需要计算的部分
    BillItemCalData calData =
        this.calItemData(voInvoice, dWillSettleNum, dWillSettleNum, null);

    // --------------- 更新已结算的发票、库存VO
    // 更新发票：累计结算数量、总金额、发票金额
    // 本次结算的发票数量可能少于真正结算的数量，因为库存全部结算了。
    voInvoice.setNcurrentaccumsettlenum(MathTool.add(
        voInvoice.getNcurrentaccumsettlenum(), dWillSettleNum));
    voInvoice.setNcurrentaccumtotalsettlemny(MathTool.add(
        voInvoice.getNcurrentaccumtotalsettlemny(), calData.getNmoney()));
    voInvoice.setNcurrentaccuminvoicesettlemny(MathTool.add(voInvoice
        .getNcurrentaccuminvoicesettlemny(),
        calData.getNgoodsmoney().sub(MathTool.nvl(calData.getNdiscount()))));

    // --------------- 形成结算单VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem.setFrowtype((Integer) rowflag.value());
    // 取自计算结果
    voBillItem.setNsettlenum(dWillSettleNum);
    // 如果没有升溢或损溢，则直接取发票单价，否则＝金额/数量
    // 如果金额与未结算金额相同，而且单价非空，非零时按单价折算
    voBillItem.setNprice(calData.getNprice());
    voBillItem.setNmoney(calData.getNmoney());
    voBillItem.setNgoodsmoney(calData.getNgoodsmoney());
    voBillItem.setNgoodsprice(calData.getNgoodsprice());
    // 货物金额、成本要素、折扣
    ICostfactorDiscountUtil.setNcostfactor(voBillItem, calData.getNaFactor());
    voBillItem.setNdiscount(calData.getNdiscount());
    // 合理损耗数量
    voBillItem.setNreasonalwastnum(calData.getNReasonWasteNum());
    // 合理损耗单价为发票单价,合理损耗金额=合理损耗数量*合理损耗单价
    voBillItem.setNreasonalwastprice(calData.getNReasonWastePrice());
    voBillItem.setNreasonalwastmny(calData.getNReasonWasteMny());

    // 取自发票
    voBillItem.setPk_group(voInvoice.getPk_group());
    voBillItem.setPk_org(voInvoice.getPk_org());
    voBillItem.setVinvoicecode(voInvoice.getVbillcode());
    voBillItem.setPk_invoice(voInvoice.getPk_invoice());
    voBillItem.setPk_invoice_b(voInvoice.getPk_invoice_b());
    voBillItem.setVinvoicetrantype(voInvoice.getCtrantypeid());
    // 直运无入库的成本域获取
    if (EnumMatchRowType.InvoiceDTransPO.equals(rowflag)) {
      String costregion =
          this.getCostRegionForDTrans(voInvoice.getPk_org(),
              voInvoice.getPk_stockorg(), voInvoice.getPk_stordoc());
      if (StringUtils.isEmpty(costregion)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0110")/*
                                                                     * @res
                                                                     * "直运无入库结算，根据发票的[库存组织，仓库，财务组织]未匹配到成本域！发票号："
                                                                     */
            + voInvoice.getVbillcode());
      }
      voBillItem.setPk_costregion(costregion);
      // 直运无入库时的仓库赋值
      voBillItem.setPk_stordoc(voInvoice.getPk_stordoc());
    }
    else {
      voBillItem.setPk_costregion(voInvoice.getPk_costregion());
    }
    voBillItem.setPk_material(voInvoice.getPk_material());
    voBillItem.setPk_srcmaterial(voInvoice.getPk_srcmaterial());
    voBillItem.setPk_arrstockorg(voInvoice.getPk_stockorg());
    voBillItem.setPk_arrstockorg_v(voInvoice.getPk_stockorg_v());
    voBillItem.setPk_supplier(voInvoice.getPk_supplier());
    voBillItem.setPk_dept(voInvoice.getPk_dept());
    voBillItem.setPk_dept_v(voInvoice.getPk_dept_v());
    voBillItem.setPk_psndoc(voInvoice.getPk_bizpsn());
    voBillItem.setPk_invoiceorder(voInvoice.getPk_order());
    voBillItem.setPk_invoiceorder_b(voInvoice.getPk_order_b());

    // mengjian by 20141203记录发票日期
    voBillItem.setInvoicebilldate(voInvoice.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 记录进口合同号
    voBillItem.setVitctcode(voInvoice.getVctcode());
    return voBillItem;
  }

  protected DataClassify[] getDataClassify() {
    return this.m_dataClassify;
  }

  /**
   * 根据入库单行ID，则到升溢数量<br>
   * 对于正入库单，出现升溢时，会将升溢数量放到最大可结算数量的入库单的最后一次结算行上
   * 
   * @param pk_srcmaterial
   * @return
   */
  protected UFDouble getIncreaseNum(String pk_srcmaterial) {
    return null;
  }

  /**
   * 发票入库单结算
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param paraListInvoice
   * @param paraListStock
   * @param numType 正还是负结算
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 上午10:25:00
   */
  protected ArrayList<SettleBillItemVO> mergeInvoiceStock(DataClassify data,
      int numType) {

    ArrayList<InvoiceSettleVO> paraListInvoice =
        GoodsMatchMerge.CombineType_PlusInvoiceStock == numType ? data
            .getPlusInvoices() : data.getMinusInvoices();
    ArrayList<StockSettleVO> paraListStock =
        GoodsMatchMerge.CombineType_PlusInvoiceStock == numType ? data
            .getPlusStockes() : data.getMinusStockes();

    if (paraListInvoice == null || paraListInvoice.size() == 0
        || paraListStock == null || paraListStock.size() == 0) {
      return null;
    }

    boolean bPlusSettle =
        numType == GoodsMatchMerge.CombineType_PlusInvoiceStock;

    // 发票：并含剩余结算数量从小到大排序。
    InvoiceSettleVOUtil.calResidualSettleNum(paraListInvoice);
    ArrayList<InvoiceSettleVO> listInvoice =
        (ArrayList<InvoiceSettleVO>) this.sortByUFDoubleKey(paraListInvoice,
            InvoiceSettleVO.NRESIDUALSETTLENUM, bPlusSettle);
    ArrayList<StockSettleVO> listStock = paraListStock;

    StockSettleVO voStock = null;
    InvoiceSettleVO voInvoice = null;
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    // 存放临时的结算单
    SettleBillItemVO voTempItem = null;
    HashMap<Integer, Integer> hmapStockFinishIndex =
        new HashMap<Integer, Integer>();

    for (int i = 0; i < listInvoice.size(); i++) {
      // 如果所有的入库单都结算完成了，则不需再循环
      if (hmapStockFinishIndex.size() == listStock.size()) {
        break;
      }
      voInvoice = listInvoice.get(i);

      // ------------1、剩余结算数量从小到大排序
      StockSettleVOUtil.calResidualSettleNum(listStock);
      listStock =
          (ArrayList<StockSettleVO>) this.sortByUFDoubleKey(listStock,
              StockSettleVO.NRESIDUALSETTLENUM, bPlusSettle);

      // 每次都从库存单据的头循环
      for (int j = 0; j < listStock.size(); j++) {
        voStock = listStock.get(j);

        // 如果库存单据已结算完，则过滤掉
        if (StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
          continue;
        }

        // ------------2、匹配库存单据
        // 没有匹配到库存单据
        if (!this.canMatch(voInvoice, voStock)) {
          continue;
        }

        // ------------3、形成结算单行
        if (bPlusSettle) {
          voTempItem =
              this.createItemByInvoiceStock(voInvoice,
                  voStock,
                  // 如果库存单据、发票均已是最后一个且库存单据必须匹配完成
                  this.getSettleEnv().isStockHaveToMatchAll()
                      && this.isLastMatch(listInvoice, listStock,
                          Integer.valueOf(i), Integer.valueOf(j)) ? true
                      : false);
        }
        else {
          voTempItem = this.createItemByInvoiceStock(voInvoice, voStock, false);
        }
        listItemVO.add(voTempItem);

        // ------------4、合并后如果再无剩余，则将其放至已结算完成的列表
        if (StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
          hmapStockFinishIndex.put(Integer.valueOf(j), null);
        }
        if (InvoiceSettleVOUtil.isCurrentSettleFinished(voInvoice)) {
          // 发票如果已结算完成，则需进行下一张发票
          break;
        }
      }
    }

    // 重新置换参数
    paraListInvoice.clear();
    for (int i = 0; i < listInvoice.size(); i++) {
      if (InvoiceSettleVOUtil.isCurrentSettleFinished(listInvoice.get(i))) {
        data.getFinishedInvoices().add(listInvoice.get(i));
      }
      else {
        paraListInvoice.add(listInvoice.get(i));
      }
    }
    paraListStock.clear();
    for (int i = 0; i < listStock.size(); i++) {
      if (StockSettleVOUtil.isCurrentSettleFinished(listStock.get(i))) {
        data.getFinishedStockes().add(listStock.get(i));
      }
      else {
        paraListStock.add(listStock.get(i));
      }
    }

    return listItemVO.size() == 0 ? null : listItemVO;
  }

  /**
   * DataClassify的SET
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param dataClassify <p>
   * @author wangyf
   * @time 2010-3-26 上午10:01:09
   */
  protected void setDataClassify(DataClassify[] dataClassify) {
    this.m_dataClassify = dataClassify;
  }
}
