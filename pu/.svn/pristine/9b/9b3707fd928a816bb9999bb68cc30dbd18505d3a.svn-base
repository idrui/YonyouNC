package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.MatchValidationException;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.SameMaterialMatchRule;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>同物料匹配</b>
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
 * @time 2010-3-25 上午10:23:43
 */
public class SameMaterialMerge extends GoodsMatchMerge {
  /** 结算的特殊约束设置 */
  private int m_addedMergeCondition = this.AddedMergeCondition_Null;

  /** 已结算完成的发票LIST */
  private ArrayList<InvoiceSettleVO> m_listFinishedInvoice =
      new ArrayList<InvoiceSettleVO>();

  /** 已结算完成的库存LIST */
  private ArrayList<StockSettleVO> m_listFinishedStock =
      new ArrayList<StockSettleVO>();

  /** 未结算完成的发票LIST */
  private ArrayList<InvoiceSettleVO> m_listUnfinishedInvoice =
      new ArrayList<InvoiceSettleVO>();

  /** 未结算完成的库存LIST */
  private ArrayList<StockSettleVO> m_listUnfinishedStock =
      new ArrayList<StockSettleVO>();

  private Map<String, UFDouble> materialIncreaseNumMap =
      new HashMap<String, UFDouble>();

  /** 结算的特殊约束：符合固定条件 */
  protected final int AddedMergeCondition_ByOrder = 1;

  /** 结算的特殊约束：和上游订单结算 */
  protected final int AddedMergeCondition_ByStock = 0;

  /** 结算的特殊约束：和上游库存单据结算 */
  protected final int AddedMergeCondition_Null = -1;

  public SameMaterialMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);

    // 自动结算入库数量不可超出发票数量
    this.getSettleEnv().setStockHaveToMatchAll(true);
  }

  public SameMaterialMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // 自动结算入库数量不可超出发票数量
    this.getSettleEnv().setStockHaveToMatchAll(true);

  }

  /**
   * 红兰发票对冲合并
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRed
   * @param voBlue
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 下午04:41:05
   */
  private SettleBillItemVO[] combineRBInvoice(InvoiceSettleVO voRed,
      InvoiceSettleVO voBlue) {

    UFDouble dRedWillSettleNum =
        MathTool.sub(voRed.getNcurrentsettlenum(),
            voRed.getNcurrentaccumsettlenum());
    UFDouble dBlueWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(voBlue),
            voBlue.getNcurrentaccumsettlenum());
    // 最小数量、每个VO剩余的数量。
    UFDouble dWillSettleNum =
        MathTool.absCompareTo(dBlueWillSettleNum, dRedWillSettleNum) > 0 ? dRedWillSettleNum
            : dBlueWillSettleNum;
    dWillSettleNum = dWillSettleNum.abs();

    InvoiceSettleVO[] voaInvoice = new InvoiceSettleVO[] {
      voRed, voBlue
    };

    SettleBillItemVO[] voaItem = new SettleBillItemVO[2];
    InvoiceSettleVO voRush = null;
    for (int i = 0; i < voaInvoice.length; i++) {
      UFDouble dRealSettleNum = dWillSettleNum;
      if (voaInvoice[i].getNcurrentsettlenum().compareTo(UFDouble.ZERO_DBL) < 0) {
        dRealSettleNum = dRealSettleNum.multiply(-1.0);
      }

      // 生成ITEM
      voaItem[i] =
          this.createItemByInvoice(voaInvoice[i], dRealSettleNum,
              EnumMatchRowType.InvoiceRush);
      // 记录对冲ID
      if (voaInvoice[i].getPk_invoice_b().equals(voRed.getPk_invoice_b())) {
        voRush = voBlue;
      }
      else {
        voRush = voRed;
      }
      voaItem[i].setPk_rushinvoice(voRush.getPk_invoice());
      voaItem[i].setPk_rushinvoice_b(voRush.getPk_invoice_b());

    }

    return voaItem;

  }

  /**
   * 红兰库存对冲合并
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRed
   * @param voBlue
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 下午04:40:20
   */
  private SettleBillItemVO[] combineRBStock(StockSettleVO voRed,
      StockSettleVO voBlue) {
    UFDouble dRedWillSettleNum =
        MathTool.sub(voRed.getNcurrentsettlenum(),
            voRed.getNcurrentaccumsettlenum());
    UFDouble dBlueWillSettleNum =
        MathTool.sub(voBlue.getNcurrentsettlenum(),
            voBlue.getNcurrentaccumsettlenum());
    // 最小数量、每个VO剩余的数量。
    UFDouble dWillSettleNum =
        MathTool.absCompareTo(dBlueWillSettleNum, dRedWillSettleNum) > 0 ? dRedWillSettleNum
            : dBlueWillSettleNum;
    dWillSettleNum = dWillSettleNum.abs();

    StockSettleVO[] voaStock = new StockSettleVO[] {
      voRed, voBlue
    };
    SettleBillItemVO[] voaItem = new SettleBillItemVO[2];
    StockSettleVO voRush = null;
    for (int i = 0; i < voaStock.length; i++) {
      UFDouble dRealSettleNum = dWillSettleNum;
      if (voaStock[i].getNcurrentsettlenum().compareTo(UFDouble.ZERO_DBL) < 0) {
        dRealSettleNum = dRealSettleNum.multiply(-1.0);
      }

      // 生成ITEM
      voaItem[i] =
          this.createItemByStock(voaStock[i], dRealSettleNum,
              EnumMatchRowType.StockRush);
      // 记录对冲ID
      if (voaStock[i].getPk_stockps_b().equals(voRed.getPk_stockps_b())) {
        voRush = voBlue;
      }
      else {
        voRush = voRed;
      }
      voaItem[i].setPk_rushstock(voRush.getPk_stockps());
      voaItem[i].setPk_rushstock_b(voRush.getPk_stockps_b());
    }

    return voaItem;
  }

  /**
   * 取红兰库存对冲可匹配的兰字库存
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRed
   * @param listBlue
   * @param iStartPos
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 下午04:38:30
   */
  private InvoiceSettleVO getCanMatchInvoice(InvoiceSettleVO voRed,
      ArrayList<InvoiceSettleVO> listBlue, int iStartPos) {

    // 循环看入库单，是否能有和发票匹配的
    InvoiceSettleVO voBlue = null;
    for (int i = iStartPos; i < listBlue.size(); i++) {
      voBlue = listBlue.get(i);
      if (this.canMatch(voRed, voBlue)) {
        return voBlue;
      }
    }

    // 如果没有匹配到任何入库单，则此发票已不会再有匹配的机会，将其存至发票垃圾筐

    return null;
  }

  /**
   * 取红兰库存对冲可匹配的兰字库存
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRed
   * @param listBlue
   * @param iStartPos
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 下午04:38:30
   */
  private StockSettleVO getCanMatchStock(StockSettleVO voRed,
      ArrayList<StockSettleVO> listBlue, int iStartPos) {

    // 循环看入库单，是否能有和发票匹配的
    StockSettleVO voBlue = null;
    for (int i = iStartPos; i < listBlue.size(); i++) {
      voBlue = listBlue.get(i);
      if (this.canMatch(voRed, voBlue)) {
        return voBlue;
      }
    }

    // 如果没有匹配到任何入库单，则此发票已不会再有匹配的机会，将其存至发票垃圾筐

    return null;
  }

  /**
   * 红兰发票是否匹配
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRedInvoice
   * @param voBlueInvoice
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 上午09:15:01
   */
  @SuppressWarnings("unchecked")
  protected boolean canMatch(InvoiceSettleVO voRedInvoice,
      InvoiceSettleVO voBlueInvoice) {

    // 某一方结算完成不再结算
    if (InvoiceSettleVOUtil.isCurrentSettleFinished(voRedInvoice)
        || InvoiceSettleVOUtil.isCurrentSettleFinished(voBlueInvoice)) {
      return false;
    }

    // c) 第三步：负数的采购发票和正数的采购发票进行结算，结算时正负采购发票需要满足系统内置条件：物料相同、供应商相同、财务组织相同。
    // 结算时优先将绝对值最小的负数和正数的采购发票行进行结算。并且参与过此类结算的采购发票需要传应付。
    String[] saRule = SameMaterialMatchRule.getRBInvoiceFixedRule();
    for (int i = 0; i < saRule.length; i++) {
      Comparable redAttr =
          (Comparable) voRedInvoice.getAttributeValue(saRule[i]);
      Comparable blueAttr =
          (Comparable) voBlueInvoice.getAttributeValue(saRule[i]);
      if (redAttr == null && blueAttr != null || redAttr != null
          && redAttr.compareTo(blueAttr) != 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * 红兰入库单是否匹配
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRedStock
   * @param voBlueStock
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 上午09:14:32
   */
  @SuppressWarnings("unchecked")
  protected boolean canMatch(StockSettleVO voRedStock, StockSettleVO voBlueStock) {

    // 入库类型不一致不允许匹配
    if (!voRedStock.getCbilltypecode().equals(voBlueStock.getCbilltypecode())) {
      return false;
    }

    // 某一方结算完成不再结算
    if (StockSettleVOUtil.isCurrentSettleFinished(voRedStock)
        || StockSettleVOUtil.isCurrentSettleFinished(voBlueStock)) {
      return false;
    }

    // 红蓝采购入库单自动结算时，已传确认应付和成本的入库单不允许作红蓝入库单对冲。
    if (EnumToIAFlag.ConfirmToIA.value().equals(voRedStock.getFdirtoiatype())
        || EnumToIAFlag.ConfirmToIA.value().equals(
            voBlueStock.getFdirtoiatype())
        || EnumToAPFlag.ConfirmToAP.value()
            .equals(voRedStock.getFdirtoaptype())
        || EnumToAPFlag.ConfirmToAP.value().equals(
            voBlueStock.getFdirtoaptype())) {
      return false;
    }

    // b) 第二步：负数的采购入库单和正数的采购入库单进行结算，结算时正负入库单需要满足系统内置必须匹配条件：物料相同、财务组织相同。
    // 结算时优先将绝对值最小的负数和正数的入库单行进行结算。
    String[] saRule = SameMaterialMatchRule.getRBStockFixedRule();
    for (int i = 0; i < saRule.length; i++) {
      Comparable redAttr = (Comparable) voRedStock.getAttributeValue(saRule[i]);
      Comparable blueAttr =
          (Comparable) voBlueStock.getAttributeValue(saRule[i]);
      if (redAttr == null && blueAttr != null || redAttr != null
          && redAttr.compareTo(blueAttr) != 0) {
        return false;
      }
    }

    return true;
  }

  @Override
  protected void checkAfter() throws BusinessException {
    for (int i = 0; i < this.getDataClassify().length; i++) {
      if (this.getDataClassify()[i].getUnfinishedInvoices().size() != 0) {
        throw new BusinessException(NCLangResOnserver.getInstance().getStrByID(
            "4004060_0",
            "04004060-0219",
            null,
            new String[] {
              MaterialAccessor
                  .getDocByPk(this.getDataClassify()[i].getPk_srcmaterial())
                  .getName().toString(), this.getCommonMessage()
            })/* 物料[{0}]有不能结算完成的发票，请检查！\n {1} */);
      }
      if (this.getDataClassify()[i].getUnfinishedStockes().size() != 0) {
        throw new BusinessException(NCLangResOnserver.getInstance().getStrByID(
            "4004060_0",
            "04004060-0220",
            null,
            new String[] {
              MaterialAccessor
                  .getDocByPk(this.getDataClassify()[i].getPk_srcmaterial())
                  .getName().toString(), this.getCommonMessage()
            })/* 物料[{0}]有不能结算完成的库存单据，请检查！\n {1} */);
      }
    }
  }

  @Override
  protected void checkBefore() throws BusinessException {
    for (int i = 0; i < this.getDataClassify().length; i++) {
      DataClassify curData = this.getDataClassify()[i];

      // 是否能对冲或结算
      boolean canInvoiceRush =
          !ListUtil.isEmpty(curData.getPlusInvoices())
              && !ListUtil.isEmpty(curData.getMinusInvoices());
      boolean canStockRush =
          !ListUtil.isEmpty(curData.getPlusStockes())
              && !ListUtil.isEmpty(curData.getMinusStockes());
      boolean canSettle =
          !ListUtil.isEmpty(curData.getOrigInvoices())
              && !ListUtil.isEmpty(curData.getOrigStockes());
      if (!canInvoiceRush && !canStockRush && !canSettle) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004060_0", "04004060-0111")/*
                                                      * @res
                                                      * "存在异物料或入库（发票）不能对冲！不能进行同物料结算！"
                                                      */);
      }

      // 库存单据的正负值、发票的正负值
      // WYF 是否需要按单据类型分开
      UFDouble dAccumInvoicePlusNum = UFDouble.ZERO_DBL;
      UFDouble dAccumInvoiceMinusNum = UFDouble.ZERO_DBL;
      {
        UFDouble dTemp = null;
        InvoiceSettleVO voInvoice = null;
        // 正发票
        for (int j = 0; j < curData.getPlusInvoices().size(); j++) {
          voInvoice = curData.getPlusInvoices().get(j);
          // --------check begin
          dTemp = InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice);

          // a) 发票###的本次结算数量为零(本次结算数量-合理损耗数量)。不能进行本物料的手工结算！
          if (dTemp.compareTo(UFDouble.ZERO_DBL) == 0) {
            throw new BusinessException(NCLangResOnserver.getInstance()
                .getStrByID("4004060_0", "04004060-0221", null, new String[] {
                  voInvoice.getVbillcode()
                })/* 发票[{0}]本次结算数量为零(本次结算数量-合理损耗数量)。不能进行本物料的手工结算！ */);
          }
          dAccumInvoicePlusNum = dAccumInvoicePlusNum.add(dTemp);
        }
        // 负发票
        for (int j = 0; j < curData.getMinusInvoices().size(); j++) {
          voInvoice = curData.getMinusInvoices().get(j);
          dTemp = voInvoice.getNcurrentsettlenum();
          dAccumInvoiceMinusNum = dAccumInvoiceMinusNum.add(dTemp);
        }
      }

      UFDouble dAccumStockPlusNum = UFDouble.ZERO_DBL;
      UFDouble dAccumStockMinusNum = UFDouble.ZERO_DBL;
      // 库存单据的最大结算数量，用于做升溢的比较
      UFDouble dMaxStockPlusNum = UFDouble.ZERO_DBL;
      String increaseMaterialPk = null;
      StockSettleVO voStock = null;
      UFDouble dTemp = null;
      for (int j = 0; j < curData.getPlusStockes().size(); j++) {
        voStock = curData.getPlusStockes().get(j);
        dTemp = voStock.getNcurrentsettlenum();

        // --------check begin
        // 物料的的入库单的本次结算数量为零。不能进行本物料的手工结算！
        if (dTemp.compareTo(UFDouble.ZERO_DBL) == 0) {
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004060_0", "04004060-0222", null, new String[] {
                voStock.getVbillcode()
              })/* 入库单[{0}]本次结算数量为零，不能进行本物料的手工结算！ */);
        }
        dAccumStockPlusNum = dAccumStockPlusNum.add(dTemp);
        // 库存单据的正数的最大结算数量
        if (dTemp.compareTo(UFDouble.ZERO_DBL) > 0
            && dTemp.compareTo(dMaxStockPlusNum) > 0) {
          dMaxStockPlusNum = dTemp;
          increaseMaterialPk = voStock.getPk_srcmaterial();
        }
      }

      for (int j = 0; j < curData.getMinusStockes().size(); j++) {
        dTemp = curData.getMinusStockes().get(j).getNcurrentsettlenum();
        dAccumStockMinusNum = dAccumStockMinusNum.add(dTemp);
      }

      // 检查所勾选的采购入库单的物料是否存在没有匹配到该物料对应的采购发票，如果存在则不允许进行同物料结算，提示“存在异物料！不能进行同物料结算！”。
      // a) 发票###的本次结算数量为零(本次结算数量-合理损耗数量)。不能进行本物料的手工结算！
      // b) 如果是负数，则要求：从绝对值上，发票数量仍不能大于入库数量。
      // c) 物料###的入库单本次结算数量与发票的本次结算数量符号不同。不能进行本物料的手工结算！
      // e) 物料###的入库单本次结算数量小于发票的本次结算数量。不能进行本物料的手工结算！
      // d) 物料###的升溢数量大于入库单的最大本次结算数量。不能进行本物料的手工结算！
      // f) 物料###的入库单本次结算数量大于发票的本次结算数量。继续本物料的手工结算吗?

      // 结算顺序：
      // 1、正负发票分别分堆。
      // 2、负数的发票先和负数的发票结。
      // 3、如果负数的入库单剩下了，则入库单对冲，剩下的是正数的入库单的数量。
      // 4、如果负数的发票剩下了，则发票对冲，剩下的是正数的发票的数量。
      // 5、正数的入库单、正数的发票匹配。

      // 只有发票
      if (curData.getPlusStockes().size() == 0
          && curData.getMinusStockes().size() == 0) {
        if (dAccumInvoiceMinusNum.add(dAccumInvoicePlusNum).compareTo(
            UFDouble.ZERO_DBL) != 0) {
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4004060_0",
                  "04004060-0219",
                  null,
                  new String[] {
                    MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                        .getName().toString(), this.getCommonMessage()
                  })/* 物料[{0}]有不能结算完成的发票，请检查！\n {1} */);
        }
      }
      // 只有入库单
      if (curData.getPlusInvoices().size() == 0
          && curData.getMinusInvoices().size() == 0) {
        if (dAccumStockMinusNum.add(dAccumStockPlusNum).compareTo(
            UFDouble.ZERO_DBL) != 0) {
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4004060_0",
                  "04004060-0220",
                  null,
                  new String[] {
                    MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                        .getName().toString(), this.getCommonMessage()
                  })/* 物料[{0}]有不能结算完成的库存单据，请检查！\n {1} */);
        }
      }

      // 均有负数
      if (dAccumStockMinusNum.compareTo(UFDouble.ZERO_DBL) != 0
          && dAccumInvoiceMinusNum.compareTo(UFDouble.ZERO_DBL) != 0) {
        int signStockPlusInvoice =
            MathTool.compareTo(dAccumStockMinusNum, dAccumInvoiceMinusNum);
        if (signStockPlusInvoice > 0) {
          dAccumInvoiceMinusNum =
              MathTool.sub(dAccumInvoiceMinusNum, dAccumStockMinusNum);
          dAccumStockMinusNum = UFDouble.ZERO_DBL;
        }
        else if (signStockPlusInvoice < 0) {
          dAccumStockMinusNum =
              MathTool.sub(dAccumStockMinusNum, dAccumInvoiceMinusNum);
          dAccumInvoiceMinusNum = UFDouble.ZERO_DBL;
        }
        else {
          dAccumStockMinusNum = UFDouble.ZERO_DBL;
          dAccumInvoiceMinusNum = UFDouble.ZERO_DBL;
        }
      }

      // 3、如果负数的入库单剩下了，则入库单对冲，剩下的是正数的入库单的数量。
      if (dAccumStockMinusNum.compareTo(UFDouble.ZERO_DBL) != 0) {
        // 负数的入库单结算后，剩余数量仍比正数的大
        if (dAccumStockMinusNum.add(dAccumStockPlusNum).compareTo(
            UFDouble.ZERO_DBL) < 0) {
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4004060_0",
                  "04004060-0220",
                  null,
                  new String[] {
                    MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                        .getName().toString(), this.getCommonMessage()
                  })/* 物料[{0}]有不能结算完成的库存单据，请检查！\n {1} */);
        }
        dAccumStockPlusNum = dAccumStockPlusNum.add(dAccumStockMinusNum);

      }
      // 4、如果负数的发票剩下了，则发票对冲，剩下的是正数的发票的数量。
      else if (dAccumInvoiceMinusNum.compareTo(UFDouble.ZERO_DBL) != 0) {
        if (dAccumInvoiceMinusNum.add(dAccumInvoicePlusNum).compareTo(
            UFDouble.ZERO_DBL) < 0) {
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4004060_0",
                  "04004060-0219",
                  null,
                  new String[] {
                    MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                        .getName().toString(), this.getCommonMessage()
                  })/* 物料[{0}]有不能结算完成的发票，请检查！\n {1} */);
        }
        dAccumInvoicePlusNum = dAccumInvoicePlusNum.add(dAccumInvoiceMinusNum);
      }

      // b) 如果是负数，则要求：从绝对值上，发票数量仍不能大于入库数量。
      // throw new ValidationException("物料###：负数方向，发票的本次结算数量不能超出入库单的本次结算数量！");

      // 正向的入库单数量小于发票数量
      if (dAccumStockPlusNum.compareTo(dAccumInvoicePlusNum) < 0) {
        throw new BusinessException(NCLangResOnserver.getInstance().getStrByID(
            "4004060_0",
            "04004060-0223",
            null,
            new String[] {
              MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                  .getName().toString()
            })/* 物料[{0}]入库单本次结算数量小于发票的本次结算数量。不能进行本物料的手工结算！ */);
      }

      // d) 物料###的升溢数量大于入库单的最大本次结算数量。不能进行本物料的手工结算！
      if (!MathTool.isZero(dAccumStockPlusNum)
          && !MathTool.isZero(dAccumInvoicePlusNum)
          && dAccumStockPlusNum.sub(dAccumInvoicePlusNum).compareTo(
              dMaxStockPlusNum) >= 0) {
        throw new BusinessException(NCLangResOnserver.getInstance().getStrByID(
            "4004060_0",
            "04004060-0224",
            null,
            new String[] {
              MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                  .getName().toString()
            })/* 物料[{0}]升溢数量大等于入库单的最大本次结算数量。不能进行本物料的手工结算！ */);
      }
      // f) 物料###的入库单本次结算数量大于发票的本次结算数量。继续本物料的手工结算吗?
      if (!this.getSettleEnv().isAllowStockBeyondInvoice()
          && dAccumStockPlusNum.compareTo(dAccumInvoicePlusNum) > 0) {
        throw new MatchValidationException(NCLangResOnserver.getInstance()
            .getStrByID(
                "4004060_0",
                "04004060-0225",
                null,
                new String[] {
                  MaterialAccessor.getDocByPk(curData.getPk_srcmaterial())
                      .getName().toString()
                })/* 物料[{0}]入库单本次结算数量大于发票的本次结算数量，继续本物料的手工结算吗？ */);
      }
      // 如果有升溢数量，则记录在物料的Map中
      if (this.getSettleEnv().isAllowStockBeyondInvoice()
          && dAccumStockPlusNum.compareTo(dAccumInvoicePlusNum) > 0) {
        this.materialIncreaseNumMap.put(increaseMaterialPk,
            MathTool.sub(dAccumInvoicePlusNum, dAccumStockPlusNum));
      }
    }

  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#formSettleBillItems()
   */
  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {
    // -----------3---------------
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    ArrayList<SettleBillItemVO> listTempItemVO = null;
    for (int i = 0; i < this.getDataClassify().length; i++) {

      // 负结算
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_MinusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 红兰库存对冲
      listTempItemVO = this.mergeRBStock(this.getDataClassify()[i]);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 红兰发票对冲
      listTempItemVO = this.mergeRBInvoice(this.getDataClassify()[i]);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 正结算
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_PlusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }
      // 构造完结算关系后，重新计算出未结算完的入库单、发票
      this.getDataClassify()[i].sumupResidualBill();

    }
    // 劳务、折扣
    listTempItemVO = this.mergeFeeDiscount();
    if (listTempItemVO != null) {
      listItemVO.addAll(listTempItemVO);
    }

    return ListUtil.isEmpty(listItemVO) ? null : listItemVO
        .toArray(new SettleBillItemVO[listItemVO.size()]);
  }

  /**
   * AddedMergeCondition的GET方法
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 上午10:58:56
   */
  protected int getAddedMergeCondition() {
    return this.m_addedMergeCondition;
  }

  @Override
  protected UFDouble getIncreaseNum(String pk_srcmaterial) {
    return this.materialIncreaseNumMap.get(pk_srcmaterial);
  }

  protected ArrayList<InvoiceSettleVO> getTotalFinishedInvoices() {
    return this.m_listFinishedInvoice;
  }

  protected ArrayList<StockSettleVO> getTotalFinishedStockes() {
    return this.m_listFinishedStock;
  }

  protected ArrayList<InvoiceSettleVO> getTotalUnfinishedInvoices() {
    return this.m_listUnfinishedInvoice;
  }

  protected ArrayList<StockSettleVO> getTotalUnfinishedStockes() {
    return this.m_listUnfinishedStock;
  }

  /**
   * 发票对冲
   * 
   * @param data
   * @return
   */
  protected ArrayList<SettleBillItemVO> mergeRBInvoice(DataClassify data) {

    ArrayList<InvoiceSettleVO> paraListRed = data.getMinusInvoices();
    ArrayList<InvoiceSettleVO> paraListBlue = data.getPlusInvoices();

    if (paraListRed == null || paraListRed.size() == 0 || paraListBlue == null
        || paraListBlue.size() == 0) {
      return null;
    }

    // 兰字
    ArrayList<InvoiceSettleVO> listBlue = paraListBlue;
    // 红字：并含数量从小到大排序。
    ArrayList<InvoiceSettleVO> listRed =
        (ArrayList<InvoiceSettleVO>) this.sortByUFDoubleKey(paraListRed,
            InvoiceSettleVO.NCURRENTSETTLENUM, false);

    // 已结算完成的兰字VO
    Map<String, InvoiceSettleVO> finishedBlueMap =
        new HashMap<String, InvoiceSettleVO>();
    // 已结算完成的红字VO
    Map<String, InvoiceSettleVO> finishedRedMap =
        new HashMap<String, InvoiceSettleVO>();

    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();

    // 外层循环红字，内层循环兰字，依次匹配，结算数量按从小到大排序好
    for (int i = 0; i < listRed.size(); i++) {
      InvoiceSettleVOUtil.calResidualSettleNum(listBlue);
      listBlue =
          (ArrayList<InvoiceSettleVO>) this.sortByUFDoubleKey(listBlue,
              InvoiceSettleVO.NRESIDUALSETTLENUM, true);
      InvoiceSettleVO voRed = listRed.get(i);
      for (int j = 0; j < listBlue.size(); j++) {
        InvoiceSettleVO voBlue = listBlue.get(j);
        // 兰字结算完毕，循环下一条
        if (finishedBlueMap.containsKey(voBlue.getPk_invoice_b())) {
          continue;
        }
        // 没有匹配到库存单据
        if (!this.canMatch(voRed, voBlue)) {
          continue;
        }
        // 如果红字、兰字均已是最后一张，则需要告知合并算法，因为其内部会把入库数量结算完成。
        SettleBillItemVO[] voaItem = this.combineRBInvoice(voRed, voBlue);
        listItemVO.add(voaItem[0]);
        listItemVO.add(voaItem[1]);
        if (InvoiceSettleVOUtil.isCurrentSettleFinished(voBlue)) {
          // 兰字如果结算完成
          finishedBlueMap.put(voBlue.getPk_invoice_b(), voBlue);
        }
        if (InvoiceSettleVOUtil.isCurrentSettleFinished(voRed)) {
          // 红字如果结算完成
          finishedRedMap.put(voRed.getPk_invoice_b(), voRed);
          break;// 红字完成进行下一个循环
        }
      }
    }
    if (finishedBlueMap.size() != 0) {
      // 已完成的兰字
      data.getFinishedInvoices().addAll(finishedBlueMap.values());
      // 整理未完成的兰字
      for (int i = 0; i < paraListBlue.size(); i++) {
        InvoiceSettleVO vo = paraListBlue.get(i);
        if (finishedBlueMap.containsKey(vo.getPk_invoice_b())) {
          paraListBlue.remove(i--);
        }
      }
    }
    if (finishedRedMap.size() != 0) {
      // 已完成的红字
      data.getFinishedInvoices().addAll(finishedRedMap.values());
      // 整理未完成的红字
      for (int i = 0; i < paraListRed.size(); i++) {
        InvoiceSettleVO vo = paraListRed.get(i);
        if (finishedRedMap.containsKey(vo.getPk_invoice_b())) {
          paraListRed.remove(i--);
        }
      }
    }

    return listItemVO;
  }

  /**
   * 库存对冲
   * 
   * @param data
   * @return
   */
  protected ArrayList<SettleBillItemVO> mergeRBStock(DataClassify data) {

    ArrayList<StockSettleVO> paraListRed = data.getMinusStockes();
    ArrayList<StockSettleVO> paraListBlue = data.getPlusStockes();

    if (paraListRed == null || paraListRed.size() == 0 || paraListBlue == null
        || paraListBlue.size() == 0) {
      return null;
    }
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();

    // 红字：并含数量从小到大排序。
    StockSettleVOUtil.calResidualSettleNum(paraListRed);
    ArrayList<StockSettleVO> listRed =
        (ArrayList<StockSettleVO>) this.sortByUFDoubleKey(paraListRed,
            StockSettleVO.NRESIDUALSETTLENUM, false);
    // 兰字：并含数量从小到大排序。
    ArrayList<StockSettleVO> listBlue = paraListBlue;

    // 已结算完成的兰字VO
    Map<String, StockSettleVO> finishedBlueMap =
        new HashMap<String, StockSettleVO>();
    // 已结算完成的红字VO
    Map<String, StockSettleVO> finishedRedMap =
        new HashMap<String, StockSettleVO>();
    for (int i = 0; i < listRed.size(); i++) {
      StockSettleVO voRed = listRed.get(i);
      StockSettleVOUtil.calResidualSettleNum(listBlue);
      listBlue =
          (ArrayList<StockSettleVO>) this.sortByUFDoubleKey(listBlue,
              StockSettleVO.NRESIDUALSETTLENUM, true);
      for (int j = 0; j < listBlue.size(); j++) {
        StockSettleVO voBlue = listBlue.get(j);
        if (finishedBlueMap.containsKey(voBlue.getPk_stockps_b())) {
          continue;
        }
        if (!this.canMatch(voRed, voBlue)) {
          continue;
        }
        // 在combineRBStock()过程中会更新红字、更新兰字的累计结算信息。
        // 如果红字、兰字均已是最后一张，则需要告知合并算法，因为其内部会把入库数量结算完成。
        SettleBillItemVO[] voaItem = this.combineRBStock(voRed, voBlue);
        listItemVO.add(voaItem[0]);
        listItemVO.add(voaItem[1]);

        if (StockSettleVOUtil.isCurrentSettleFinished(voBlue)) {

          // 兰字如果结算完成
          finishedBlueMap.put(voBlue.getPk_stockps_b(), voBlue);
        }
        if (StockSettleVOUtil.isCurrentSettleFinished(voRed)) {
          // 红字如果结算完成,结束兰字循环进入下一个红字循环
          finishedRedMap.put(voRed.getPk_stockps_b(), voRed);
          break;
        }
      }
    }

    if (finishedBlueMap.size() != 0) {
      data.getFinishedStockes().addAll(finishedBlueMap.values());
      // 整理未完成的兰字
      for (int i = 0; i < paraListBlue.size(); i++) {
        StockSettleVO vo = paraListBlue.get(i);
        if (finishedBlueMap.containsKey(vo.getPk_stockps_b())) {
          paraListBlue.remove(i--);
        }
      }
    }
    if (finishedRedMap.size() != 0) {
      data.getFinishedStockes().addAll(finishedRedMap.values());
      // 整理未完成的红字
      for (int i = 0; i < paraListRed.size(); i++) {
        StockSettleVO vo = paraListRed.get(i);
        if (finishedRedMap.containsKey(vo.getPk_stockps_b())) {
          paraListRed.remove(i--);
        }
      }
    }

    return listItemVO;
  }

  /**
   * 对数据进行初步整理
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @author wangyf
   * @time 2009-7-1 下午03:06:51
   */
  @Override
  protected void packOrigData() throws BusinessException {
    // 1 相同存货的入库单、发票归类
    // -----------1---------------
    HashMap<String, ArrayList<StockSettleVO>> hmapStockVO = null;
    if (this.getStockVOs() != null) {
      hmapStockVO =
          CirVOUtil.getFieldVOList(this.getStockVOs(),
              StockSettleVO.PK_SRCMATERIAL);
    }
    HashMap<String, ArrayList<InvoiceSettleVO>> hmapInvoiceVO = null;
    if (this.getInvoiceVOs() != null) {
      hmapInvoiceVO =
          CirVOUtil.getFieldVOList(this.getInvoiceVOs(),
              InvoiceSettleVO.PK_SRCMATERIAL);
    }

    // -----------2---------------
    ArrayList<StockSettleVO> listStock = null;
    ArrayList<InvoiceSettleVO> listInvoice = null;
    // 所有的物料 ID
    Iterator<String> iterAllMaterial = null;
    {
      HashSet<String> hsetMaterial = new HashSet<String>();
      if (hmapInvoiceVO != null) {
        hsetMaterial.addAll(hmapInvoiceVO.keySet());
      }
      if (hmapStockVO != null) {
        hsetMaterial.addAll(hmapStockVO.keySet());
      }
      iterAllMaterial = hsetMaterial.iterator();
    }
    ArrayList<DataClassify> listData = new ArrayList<DataClassify>();
    String sPk_Material = null;
    while (iterAllMaterial.hasNext()) {
      sPk_Material = iterAllMaterial.next();
      // 得到待结算数据
      listStock = hmapStockVO == null ? null : hmapStockVO.get(sPk_Material);
      listInvoice =
          hmapInvoiceVO == null ? null : hmapInvoiceVO.get(sPk_Material);
      // 对待结算数据进行分类：正负分别归类
      DataClassify data = new DataClassify(listInvoice, listStock);
      data.classifyPlusMinus();
      listData.add(data);
    }

    this.setDataClassify(listData.toArray(new DataClassify[listData.size()]));
  }

  protected void setAddedMergeCondition(int specialMatchCondition) {
    this.m_addedMergeCondition = specialMatchCondition;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#splitSettleBills(nc.vo.pu.m27.entity.SettleBillItemVO[])
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // 分单
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }
    return this.splitBill(voaOrgItem);
  }
}
