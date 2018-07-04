package nc.vo.pu.m27.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pubapp.AppBsContext;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m27.merge.rule.MatchMergeFeeDiscountRule;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.MatchRule;
import nc.vo.pu.m27.rule.MatchRuleFactory;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>匹配的超类：</b>
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
 * @time 2010-3-25 上午10:22:27
 */
public abstract class MatchMerge {

  private SettleEnvironment m_settleEnv = null;

  /** 调整发票 **/
  private FeeDiscountSettleVO[] m_voaAdjustInvc = null;

  private FeeDiscountSettleVO[] m_voaDiscount = null;

  private FeeDiscountSettleVO[] m_voaFee = null;

  private InvoiceSettleVO[] m_voaInvoice = null;

  private StockSettleVO[] m_voaStock = null;

  /**
   * 进口使用
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos 进口调整发票
   * @param settleEnv
   */
  public MatchMerge(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      FeeDiscountSettleVO[] adjustInvcVos, final SettleEnvironment settleEnv) {
    // 初始化及检查
    this.init(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos,
        settleEnv);
    // 初始化结算环境
    settleEnv.setLoginDate(AppBsContext.getInstance().getBusiDate());
  }

  /**
   * MergeArithmetic 的构造子
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   */
  public MatchMerge(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      final SettleEnvironment settleEnv) {
    // 初始化及检查
    this.init(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);
    // 初始化结算环境
    settleEnv.setLoginDate(AppBsContext.getInstance().getBusiDate());
  }

  public FeeDiscountSettleVO[] getAdjustInvcVos() {
    return this.m_voaAdjustInvc;
  }

  /**
   * 通用的提示信息
   * 
   * @return
   */
  public String getCommonMessage() {
    StringBuilder msg = new StringBuilder();
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0144")/* @res "结算成功必须满足的条件：\n " */);
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0145")/*
                                      * @res
                                      * " 1、  入库单已暂估应付、或确认应付，必须和未传应付的发票结算，且不可以进行异物料结算。\n "
                                      */);
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0146")/* @res " 2、  入库单已确认应付，必须和相同币种的发票结算。  \n " */);
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0147")/* @res " 3、  入库单已确认应付和成本，不可以做红蓝入库单对冲。 \n" */);
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0148")/* @res " 4、  源于采购订单的发票，必须和同源订单的采购入库单结算。" */);
    return msg.toString();
  }

  public FeeDiscountSettleVO[] getDiscountVOs() {
    return this.m_voaDiscount;
  }

  public FeeDiscountSettleVO[] getFeeVOs() {
    return this.m_voaFee;
  }

  public InvoiceSettleVO[] getInvoiceVOs() {
    return this.m_voaInvoice;
  }

  public SettleEnvironment getSettleEnv() {
    return this.m_settleEnv;
  }

  public StockSettleVO[] getStockVOs() {
    return this.m_voaStock;
  }

  /**
   * 模板方法，各种结算均走同一个过程
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @author wangyf
   * @time 2009-7-1 下午03:06:51
   */
  public final SettleBillVO[] merge() throws BusinessException {

    // 1、对数初始据进行整理打包
    this.packOrigData();

    // 2、合并前的检查
    this.checkBefore();

    // 3、把入库单、发票合并成结算单表体
    SettleBillItemVO[] voaItem = this.formSettleBillItems();

    // 4、过滤一些不可结算的数据
    voaItem = this.filterSettleItems(voaItem);

    // 5、一些特殊数据的记录
    this.recordOtherSettleInfo(voaItem);

    // 6、合并后的检查
    this.checkAfter();

    // 7、分单、构造完整的结算单
    return this.splitSettleBills(voaItem);
  }

  /**
   * 完成结算单的分单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaOrgItem 结算单表体
   * @return <p>
   * @author wangyf
   * @time 2009-7-7 上午11:46:02
   */
  public SettleBillVO[] splitBill(SettleBillItemVO[] voaOrgItem) {

    if (voaOrgItem == null) {
      return null;
    }
    // 1)
    // 自动结算或者同物料结算，只有货物发票，无费用发票进行费用分摊，则以货物发票进行分单；如果既有货物发票又有费用发票参与结算，则本次结算生成一张结算单
    // （无分单）。
    // 2) 异物料结算：一次结算生成一张结算单，无分单。
    // 3) 无发票结算：一次结算生成一张结算单，无分单。
    // 4) 费用结算：一次结算生成一张结算单，无分单。

    // 9. 消耗汇总结算后生成的采购结算单分单规则：按照货物发票＋费用分摊进行分单，具体说明如下：
    // a)
    // 消耗汇总结算，只有货物发票，无费用发票进行费用分摊，则以货物发票进行分单；如果既有货物发票又有费用发票参与结算，则本次结算生成一张结算单（无分单
    // ）。
    // b) 费用结算：一次结算生成一张结算单，无分单。

    SettleBillItemVO[][] voaResultItem = new SettleBillItemVO[1][];
    voaResultItem[0] = voaOrgItem;

    int iLen = voaResultItem.length;
    SettleBillVO[] voaBill = new SettleBillVO[iLen];
    for (int i = 0; i < iLen; i++) {

      SettleBillHeaderVO voHeader = new SettleBillHeaderVO();
      voHeader.setPk_group(voaResultItem[i][0].getPk_group());
      voHeader.setPk_org(voaResultItem[i][0].getPk_org());
      voHeader.setPk_org_v(voaResultItem[i][0].getPk_org_v());
      voHeader.setBillmaker(this.getSettleEnv().getLoginOperator());
      voHeader.setCreator(this.getSettleEnv().getLoginOperator());
      voHeader.setDbilldate(this.getSettleEnv().getLoginDate());
      voHeader.setDmakedate(this.getSettleEnv().getLoginDate());
      voaBill[i] = new SettleBillVO();
      voaBill[i].setParentVO(voHeader);
      voaBill[i].setChildrenVO(voaResultItem[i]);
    }
    SettleBillVO[] splitVos = null;
    SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
    split.appendKey(SettleBillItemVO.PK_INVOICE);
    for (SettleBillVO bill : voaBill) {
      SettleBillVO[] aryBills = new SettleBillVO[] {
        bill
      };
      // 看看是否能进行分单
      if (this.isCanSplit(bill)) {
        splitVos =
            (SettleBillVO[]) ArrayUtils.addAll(splitVos, split.split(aryBills));
      }
      else {
        splitVos = (SettleBillVO[]) ArrayUtils.addAll(aryBills, splitVos);
      }
    }
    // 设置结算类型
    this.setFsettletypes(splitVos);
    return splitVos;
  }

  private SettleBillItemVO[] filterSettleItems(SettleBillItemVO[] voaItem) {

    if (ArrayUtils.isEmpty(voaItem)) {
      return voaItem;
    }
    // 无入库单时，不可形成费用结算单行（此种情况只有在自动结算时过滤掉未分摊的费用行）
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();

    if (CollectionUtils.isEmpty(CirVOUtil.getDistinctFieldSet(voaItem,
        SettleBillItemVO.PK_STOCK_B))) {
      for (SettleBillItemVO item : voaItem) {
        // 过滤费用行
        if (EnumMatchRowType.Discount.toInteger().equals(item.getFrowtype())
            || EnumMatchRowType.Fee.toInteger().equals(item.getFrowtype())
            || EnumMatchRowType.AdjustGoods.toInteger().equals(
                item.getFrowtype())) {
          continue;
        }
        items.add(item);
      }
      if (items.size() == 0) {
        return null;
      }
      return items.toArray(new SettleBillItemVO[items.size()]);
    }
    return voaItem;
  }

  private void init(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      FeeDiscountSettleVO[] adjustInvcVos, SettleEnvironment settleEnv) {
    MatchRule matchRule =
        new MatchRuleFactory().getRule(settleEnv.getSettleType());
    // 过滤
    MatchMergeData mmData =
        new MatchMergeData(voaStock, voaInvoice, voaFee, voaDiscount,
            adjustInvcVos);
    if (null != matchRule) {
      matchRule.filter(mmData, settleEnv);
    }
    this.m_voaInvoice = mmData.getGoodsInvcVos();
    this.m_voaStock = mmData.getStockVos();
    this.m_voaFee = mmData.getFeeInvcVos();
    this.m_voaDiscount = mmData.getDiscountInvcVos();
    this.m_settleEnv = settleEnv;
    this.m_voaAdjustInvc = mmData.getAdjustInvcVos();
    // 检查
    try {
      if (null != matchRule) {
        matchRule.check(mmData, settleEnv);
      }
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void init(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      final SettleEnvironment settleEnv) {
    MatchRule matchRule =
        new MatchRuleFactory().getRule(settleEnv.getSettleType());
    // 过滤
    MatchMergeData mmData =
        new MatchMergeData(voaStock, voaInvoice, voaFee, voaDiscount);
    if (null != matchRule) {
      matchRule.filter(mmData, settleEnv);
    }
    this.m_voaInvoice = mmData.getGoodsInvcVos();
    this.m_voaStock = mmData.getStockVos();
    this.m_voaFee = mmData.getFeeInvcVos();
    this.m_voaDiscount = mmData.getDiscountInvcVos();
    this.m_settleEnv = settleEnv;
    // 检查
    try {
      if (null != matchRule) {
        matchRule.check(mmData, settleEnv);
      }
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 记录表体的冲销暂估金额、相应的确认成本金额、相应的确认应付原币价税合计(采购发票传应付做调差使用)
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 上午11:03:22
   */
  private void recordEstAndConfirmMoney(SettleBillItemVO[] vosItem) {
    if (ArrayUtils.isEmpty(vosItem)) {
      return;
    }

    /**
     * 找到结算单中同一个库存单据的所有行。 如果某一行是库存单据的最后一行，则要全部冲完；否则只按单价冲
     * 同一库存单据行的结算数量：10、20、30，入库数量为60。则10、20按单价冲，30按剩余金额冲
     */
    // 所有入库单的map
    Map<String, StockSettleVO> mapStocks = new HashMap<String, StockSettleVO>();
    StockSettleVO[] stockVOs = this.getStockVOs();
    if (ArrayUtils.isEmpty(stockVOs)) {
      return;
    }
    for (StockSettleVO stock : stockVOs) {
      mapStocks.put(stock.getPk_stockps_b(), stock);
    }

    // 找到结算单中同一个库存单据的所有行
    MapList<String, SettleBillItemVO> mapSettleBillItems =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillItemVO voItem : vosItem) {
      // 只有有入库单的货物结算的才需要写
      if (!(EnumMatchRowType.StockInvoiceMatch.value().equals(
          voItem.getFrowtype())
          || EnumMatchRowType.StockInDiffMatch.value().equals(
              voItem.getFrowtype()) || EnumMatchRowType.StockRush.value()
          .equals(voItem.getFrowtype()))) {
        continue;
      }
      // 只有进行过暂估或确认才写，注：如果暂估未传成本也参与回冲,即用暂估数量判断
      StockSettleVO voStock = mapStocks.get(voItem.getPk_stock_b());
      if (EnumToIAFlag.EstimateToIA.value().equals(voStock.getFdirtoiatype())
          || !MathTool.isZero(voStock.getNestnum())// 其实只用暂估数量判断即可，不用标志
          || EnumToIAFlag.ConfirmToIA.value().equals(voStock.getFdirtoiatype())
          || EnumToAPFlag.ConfirmToAP.value().equals(voStock.getFdirtoaptype())) {
        mapSettleBillItems.put(voItem.getPk_stock_b(), voItem);
      }
    }

    if (mapSettleBillItems.size() == 0) {
      return;
    }

    // 对每一个库存单据的ID进行处理
    for (Entry<String, List<SettleBillItemVO>> entry : mapSettleBillItems
        .entrySet()) {
      String stockBid = entry.getKey();
      List<SettleBillItemVO> listItem = entry.getValue();

      StockSettleVO voStock = mapStocks.get(stockBid);
      // 累计结算数量
      UFDouble accumSettleNum =
          ValueUtils.getUFDouble(voStock.getNaccumsettlenum());

      for (int i = 0; i < listItem.size(); i++) {
        SettleBillItemVO voItem = listItem.get(i);
        accumSettleNum = MathTool.add(accumSettleNum, voItem.getNsettlenum());
        // 这里一定要初始化为空，和零要区分开；零代表有暂估回冲，而空则无暂估回冲
        // 后来，结算单表体加了一个是否回冲暂估的标志，不再依靠null
        UFDouble dEstMoney = null;
        UFDouble dConfirmMoney = null;
        UFDouble dConfirmApMny = null;
        try {
          // 库存单据已结算完成且当前已是最后一行，则按剩余金额冲。
          // 2012-08-11，tianft：处理费用时会将累计结算数量赋值用于后续分摊，此处的accumSettleNum可能会翻倍
          // 这时需要特殊比较一下，比较入库数量和累计结算数量如果相等，再加上后面2个比较条件进行判断是不是最后一次
          if ((MathTool.compareTo(voStock.getNinnum(), accumSettleNum) == 0 || MathTool
              .compareTo(voStock.getNinnum(), voStock.getNaccumsettlenum()) == 0)
              && StockSettleVOUtil.isCurrentSettleFinished(voStock)
              && i == listItem.size() - 1) {
            if (EnumToIAFlag.EstimateToIA.value().equals(
                voStock.getFdirtoiatype())
                || !MathTool.isZero(voStock.getNestnum())) {
              // dEstMoney =
              // MathTool.sub(voStock.getNestmny(),
              // voStock.getNaccestcostwashmny());

              // wuxla v61
              dEstMoney =
                  MathTool.sub(voStock.getNestcalcostmny(),
                      voStock.getNaccestcostwashmny());
            }
            else if (EnumToIAFlag.ConfirmToIA.value().equals(
                voStock.getFdirtoiatype())) {
              // wuxla v61
              dConfirmMoney =
                  MathTool.sub(voStock.getNcalcostmny(),
                      voStock.getNacctocostadjstmny());
            }
            if (EnumToAPFlag.ConfirmToAP.value().equals(
                voStock.getFdirtoaptype())) {
              dConfirmApMny =
                  MathTool.sub(voStock.getNorigtaxmny(),
                      voStock.getNacctoapadjstotmny());
            }
          }
          // 按单价冲
          else {
            if (EnumToIAFlag.EstimateToIA.value().equals(
                voStock.getFdirtoiatype())
                || !MathTool.isZero(voStock.getNestnum())) {
              // wuxla v61
              // dEstMoney =
              // ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
              // MathTool.nvl(voItem.getNsettlenum()).multiply(
              // MathTool.nvl(voStock.getNestprice())),
              // this.getSettleEnv().getOrgCurr());
              dEstMoney =
                  ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                      MathTool.nvl(voItem.getNsettlenum()).multiply(
                          MathTool.nvl(voStock.getNestcalcostprice())),
                      this.getSettleEnv().getOrgCurr());
            }
            else if (EnumToIAFlag.ConfirmToIA.value().equals(
                voStock.getFdirtoiatype())) {
              // wuxla v61 使用记成本单价
              dConfirmMoney =
                  ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                      MathTool.nvl(voItem.getNsettlenum()).multiply(
                          MathTool.nvl(voStock.getNcalcostprice())),
                      this.getSettleEnv().getOrgCurr());
            }
            if (EnumToAPFlag.ConfirmToAP.value().equals(
                voStock.getFdirtoaptype())) {
              dConfirmApMny =
                  ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                      MathTool.nvl(voItem.getNsettlenum()).multiply(
                          MathTool.nvl(voStock.getNorigtaxnetprice())),
                      voStock.getCorigcurrencyid());
            }
          }

          voItem.setNclashestmoney(dEstMoney);
          voStock.setNaccestcostwashmny(MathTool.add(
              voStock.getNaccestcostwashmny(), dEstMoney));

          voItem.setNoppoconfmmoney(dConfirmMoney);
          voStock.setNacctocostadjstmny(MathTool.add(
              voStock.getNacctocostadjstmny(), dConfirmMoney));

          voItem.setNoppoconfmapmny(dConfirmApMny);
          voStock.setNacctoapadjstotmny(MathTool.add(
              voStock.getNacctoapadjstotmny(), dConfirmApMny));

          if (EnumToIAFlag.EstimateToIA.value().equals(
              voStock.getFdirtoiatype())
              || !MathTool.isZero(voStock.getNestnum())
              || EnumToIAFlag.ConfirmToIA.value().equals(
                  voStock.getFdirtoiatype())) {
            voItem.setBwashest(UFBoolean.TRUE);
          }
          else {
            voItem.setBwashest(UFBoolean.FALSE);
          }
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
    }
  }

  /**
   * 货物单价、货物金额
   * <p>
   * <b>参数说明</b>
   * 
   * @param vosItem <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 下午02:48:03
   */
  private void recordGoofsInfo(SettleBillItemVO[] vosItem) {
    if (ArrayUtils.isEmpty(vosItem)) {
      return;
    }

    for (SettleBillItemVO voItem : vosItem) {
      // 只有货物结算的才需要写
      if (EnumMatchRowType.StockInvoiceMatch.value().equals(
          voItem.getFrowtype())
          || EnumMatchRowType.StockInDiffMatch.value().equals(
              voItem.getFrowtype())
          || EnumMatchRowType.StockRush.value().equals(voItem.getFrowtype())
          || EnumMatchRowType.InvoiceRush.value().equals(voItem.getFrowtype())
          || EnumMatchRowType.InvoiceDTransPO.value().equals(
              voItem.getFrowtype())) {
        // WYF 判断是否需要在此处做

      }
    }
  }

  /**
   * 记录其他的结算信息
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vosItem <p>
   * @author wangyf
   * @time 2010-4-6 下午02:16:45
   */
  private void recordOtherSettleInfo(SettleBillItemVO[] vosItem) {

    if (vosItem == null) {
      return;
    }

    // 记录库存单据ID
    this.recordStock(vosItem);

    // 记录暂估金额、相应确认金额信息
    this.recordEstAndConfirmMoney(vosItem);

    // 货物单价、货物金额
    this.recordGoofsInfo(vosItem);

    // 费用及折扣,并重算结算金额及单价
    this.recordFeeDiscountInfo(vosItem, this.m_voaStock);

  }

  /**
   * 记录库存单据信息
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaItem <p>
   * @author wangyf
   * @time 2010-4-6 下午02:16:45
   */
  private void recordStock(SettleBillItemVO[] vosItem) {

    if (ArrayUtils.isEmpty(vosItem)) {
      return;
    }

    for (SettleBillItemVO voItem : vosItem) {
      if (ICBillType.PurchaseIn.getCode().equals(voItem.getVstockbilltype())) {
        voItem.setPk_purchasein(voItem.getPk_stock());
        voItem.setPk_purchasein_b(voItem.getPk_stock_b());
      }
      else if (ICBillType.GeneralIn.getCode()
          .equals(voItem.getVstockbilltype())) {
        voItem.setPk_generalin(voItem.getPk_stock());
        voItem.setPk_generalin_b(voItem.getPk_stock_b());
      }
      else if (ICBillType.VmiSum.getCode().equals(voItem.getVstockbilltype())) {
        voItem.setPk_voiconsume(voItem.getPk_stock());
        voItem.setPk_voiconsume_b(voItem.getPk_stock_b());
      }
      else if (ICBillType.TransIn.getCode().equals(voItem.getVstockbilltype())) {
        voItem.setPk_transin(voItem.getPk_stock());
        voItem.setPk_transin_b(voItem.getPk_stock_b());
      }
      else if (ICBillType.SubContinIn.getCode().equals(
          voItem.getVstockbilltype())) {
        voItem.setPk_subcontract(voItem.getPk_stock());
        voItem.setPk_subcontract_b(voItem.getPk_stock_b());
      }
      else if (POBillType.InitEstimate.getCode().equals(
          voItem.getVstockbilltype())) {
        voItem.setPk_initialest(voItem.getPk_stock());
        voItem.setPk_initialest_b(voItem.getPk_stock_b());
      }
    }
  }

  /**
   * 匹配完成之后进行的检查
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-12 下午04:03:01
   */
  protected abstract void checkAfter() throws BusinessException;

  /**
   * 原始数据的检查
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-12 下午01:48:56
   */
  protected abstract void checkBefore() throws BusinessException;

  /**
   * 劳务、折扣生成ITEM
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   * @param dWillSettleMoney
   * @param rowflag 行标志
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 下午05:01:37
   */
  protected SettleBillItemVO createItemByFeeDiscount(
      FeeDiscountSettleVO voInvoice, EnumMatchRowType rowflag) {

    UFDouble dWillSettleMny = voInvoice.getNcurrentinvoicesettlemny();
    // --------------- 更新已结算的发票
    // 更新发票：累计总金额、发票金额
    voInvoice.setNcurrentaccumtotalsettlemny(dWillSettleMny);
    voInvoice.setNcurrentaccuminvoicesettlemny(dWillSettleMny);

    // --------------- 形成结算单VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem.setFrowtype((Integer) rowflag.value());
    // 取自计算结果
    voBillItem.setNsettlenum(null);
    // 如果没有升溢或损溢，则直接取发票单价，否则＝金额/数量
    // 如果金额与未结算金额相同，而且单价非空，非零时按单价折算
    voBillItem.setNprice(null);
    voBillItem.setNmoney(dWillSettleMny);

    // 取自发票
    voBillItem.setPk_group(voInvoice.getPk_group());
    voBillItem.setPk_org(voInvoice.getPk_org());
    voBillItem.setVinvoicecode(voInvoice.getVbillcode());
    voBillItem.setPk_invoice(voInvoice.getPk_invoice());
    voBillItem.setPk_invoice_b(voInvoice.getPk_invoice_b());
    voBillItem.setVinvoicetrantype(voInvoice.getCtrantypeid());
    // voBillItem.setPk_costregion(voInvoice.getPk_costregion());
    voBillItem.setPk_material(voInvoice.getPk_material());
    voBillItem.setPk_srcmaterial(voInvoice.getPk_srcmaterial());
    // voBillItem.setPk_arrstockorg(voInvoice.getPk_stockorg());
    // voBillItem.setPk_arrstockorg_v(voInvoice.getPk_stockorg_v());
    voBillItem.setPk_supplier(voInvoice.getPk_supplier());
    voBillItem.setPk_dept(voInvoice.getPk_dept());
    voBillItem.setPk_dept_v(voInvoice.getPk_dept_v());
    voBillItem.setPk_psndoc(voInvoice.getPk_bizpsn());

    // mengjian by 20141203记录发票日期
    voBillItem.setInvoicebilldate(voInvoice.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 记录进口合同号
    voBillItem.setVitctcode(voInvoice.getVctcode());
    return voBillItem;
  }

  /**
   * 纯由库存单据生成ITEM
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voStock
   * @param dWillSettleNum
   * @param rowflag 行标志
   * @return <p>
   * @author wangyf
   * @time 2010-3-25 上午11:18:58
   */
  protected SettleBillItemVO createItemByStock(StockSettleVO voStock,
      UFDouble dWillSettleNum, EnumMatchRowType rowflag) {
    // --------------- 更新已结算的库存VO
    // 更新库存
    voStock.setNcurrentaccumsettlenum(MathTool.add(
        voStock.getNcurrentaccumsettlenum(), dWillSettleNum));
    // --------------- 形成结算单VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem.setFrowtype((Integer) rowflag.value());
    // 取自计算结果
    voBillItem.setNsettlenum(dWillSettleNum);

    // wuxla v61
    // voBillItem.setNprice(voStock.getNestprice());
    // if (dWillSettleNum != null && voStock.getNestprice() != null) {
    // voBillItem.setNmoney(ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
    // dWillSettleNum.multiply(voStock.getNestprice()),
    // this.getSettleEnv().getOrgCurr()));
    // }
    voBillItem.setNprice(voStock.getNestcalcostprice());
    if (dWillSettleNum != null && voStock.getNestcalcostprice() != null) {
      voBillItem.setNmoney(ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
          dWillSettleNum.multiply(voStock.getNestcalcostprice()),
          this.getSettleEnv().getOrgCurr()));
    }
    voBillItem.setNgoodsmoney(voBillItem.getNmoney());
    voBillItem.setNgoodsprice(voBillItem.getNprice());

    // 取自库存
    voBillItem.setPk_group(voStock.getPk_group());
    voBillItem.setPk_org(voStock.getPk_financeorg());
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
    voBillItem.setPk_supplier(voStock.getPk_supplier());
    // TODO 需确定费用结算取费用发票的采购部门、业务员，还是入库的
    voBillItem.setPk_dept(voStock.getPk_dept());
    voBillItem.setPk_dept_v(voStock.getPk_dept_v());
    voBillItem.setPk_psndoc(voStock.getPk_psndoc());
    voBillItem.setPk_stockorder(voStock.getPk_order());
    voBillItem.setPk_stockorder_b(voStock.getPk_order_b());

    // 记录界面的分摊结果
    voBillItem.setNdiscount(voStock.getNdiscount());
    voBillItem.setNcostfactor1(voStock.getNcostfactor1());
    voBillItem.setNcostfactor2(voStock.getNcostfactor2());
    voBillItem.setNcostfactor3(voStock.getNcostfactor3());
    voBillItem.setNcostfactor4(voStock.getNcostfactor4());
    voBillItem.setNcostfactor5(voStock.getNcostfactor5());
    voBillItem.setNcostfactor6(voStock.getNcostfactor6());
    voBillItem.setNcostfactor7(voStock.getNcostfactor7());
    voBillItem.setNcostfactor8(voStock.getNcostfactor8());
    // add by liangchen1 调整货物
    voBillItem.setNadjustmny(voStock.getNadjustmny());

    // mengjian by 20141203记录入库日期
    voBillItem.setStockbilldate(voStock.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 记录进口合同号
    voBillItem.setVitctcode(voStock.getVctcode());
    return voBillItem;
  }

  /**
   * 形成结算单，是具体的合并
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-4 上午10:39:02
   */
  protected abstract SettleBillItemVO[] formSettleBillItems()
      throws BusinessException;

  /**
   * 判断一个结算单是否能分单
   * 
   * @param vo
   * @return
   */
  protected boolean isCanSplit(SettleBillVO vo) {
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      Integer rowtype = item.getFrowtype();
      // 已经有费用分摊，或自动结算有对冲数据，或异物料，或无发票结算（子类处理吧），则不能分单
      if (ICostfactorDiscountUtil.isAllot(item)
          || EnumMatchRowType.InvoiceRush.toInteger().equals(rowtype)
          || EnumMatchRowType.StockRush.toInteger().equals(rowtype)
          || EnumMatchRowType.InvoiceInDiffMatch.toInteger().equals(rowtype)
          || EnumMatchRowType.StockInDiffMatch.toInteger().equals(rowtype)
          || EnumMatchRowType.Fee.toInteger().equals(rowtype)
          || EnumMatchRowType.AdjustGoods.toInteger().equals(rowtype)
          || EnumMatchRowType.Discount.toInteger().equals(rowtype)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 所有的劳务、折扣行生成ITEM
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-25 下午01:45:40
   */
  protected ArrayList<SettleBillItemVO> mergeFeeDiscount() {

    ArrayList<SettleBillItemVO> listItem = new ArrayList<SettleBillItemVO>();
    if (this.getFeeVOs() != null) {
      for (FeeDiscountSettleVO voInvoice : this.getFeeVOs()) {
        listItem.add(this.createItemByFeeDiscount(voInvoice,
            EnumMatchRowType.Fee));
      }
    }

    if (this.getDiscountVOs() != null) {
      for (FeeDiscountSettleVO voInvoice : this.getDiscountVOs()) {
        listItem.add(this.createItemByFeeDiscount(voInvoice,
            EnumMatchRowType.Discount));
      }
    }

    // 进口调整货物发票
    if (this.getAdjustInvcVos() != null) {
      for (FeeDiscountSettleVO voInvoice : this.getAdjustInvcVos()) {
        listItem.add(this.createItemByFeeDiscount(voInvoice,
            EnumMatchRowType.AdjustGoods));
      }
    }
    return ListUtil.isEmpty(listItem) ? null : listItem;
  }

  /**
   * 对初始数据进行整理、打包
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-4 上午10:49:18
   */
  protected abstract void packOrigData() throws BusinessException;

  /**
   * mengjian 进口结算时规则需要单独处理，所以将此方法放开
   * 
   * @param voaItem
   * @param m_voaStock2
   */
  protected void recordFeeDiscountInfo(SettleBillItemVO[] voaItem,
      StockSettleVO[] m_voaStock2) {
    new MatchMergeFeeDiscountRule(this.getSettleEnv(), m_voaStock2)
        .process(voaItem);
  }

  protected void setAdjustInvcVos(FeeDiscountSettleVO[] voaAdjustInvc) {
    this.m_voaAdjustInvc = voaAdjustInvc;
  }

  protected void setDiscountVOs(FeeDiscountSettleVO[] vos) {
    this.m_voaDiscount = vos;
  }

  protected void setFeeVOs(FeeDiscountSettleVO[] vos) {
    this.m_voaFee = vos;
  }

  /**
   * //设置结算类型 0：采购 ；1：进口
   * 
   * @param splitVos
   */
  protected void setFsettletypes(SettleBillVO[] splitVos) {
    for (SettleBillVO bill : splitVos) {
      // 设置结算类型 0：采购 ；1：进口
      bill.getParentVO().setFsettletype(EnumSettleOrderType.PU.toInteger());
    }
  }

  protected void setInvoiceVOs(InvoiceSettleVO[] vos) {
    this.m_voaInvoice = vos;
  }

  protected void setStockVOs(StockSettleVO[] vos) {
    this.m_voaStock = vos;
  }

  /**
   * 对某KEY，按升序或降序进行排序。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param <T>
   * @param listVO VO数组
   * @param sOrderKey 排序的KEY
   * @param bAscend 升序为TRUE，否则为FALSE。
   * @return 不是对原有的LIST修改，而是返回一个新的LIST。
   *         <p>
   * @author wangyf
   * @time 2010-2-4 上午10:50:46
   */
  protected <T extends CircularlyAccessibleValueObject> List<T> sortByUFDoubleKey(
      final List<T> listVO, final String sOrderKey, final boolean bAscend) {
    List<T> sortLst = new ArrayList<T>(listVO);
    Comparator<T> comp = new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {
        UFDouble value1 = (UFDouble) o1.getAttributeValue(sOrderKey);
        UFDouble value2 = (UFDouble) o2.getAttributeValue(sOrderKey);
        // if (bAscend) {
        // return MathTool.compareTo(value1, value2);
        // }
        // return MathTool.compareTo(value2, value1);
        // TODO 赵玉行 UFDouble的compareTo有效率问题，这里临时处理转为double
        double double_value1 = null == value1 ? 0f : value1.doubleValue();
        double double_value2 = null == value2 ? 0f : value2.doubleValue();
        int retValue = 0;
        if (double_value1 > double_value2) {
          retValue = 1;
        }
        else if (double_value1 < double_value2) {
          retValue = -1;
        }
        if (bAscend) {
          return retValue;
        }
        return -retValue;
      }
    };
    // 调用公共算法排序
    Collections.sort(sortLst, comp);
    return sortLst;
  }

  /**
   * 分单
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-4 上午10:51:08
   */
  protected abstract SettleBillVO[] splitSettleBills(
      SettleBillItemVO[] voaOrgItem) throws BusinessException;
}
