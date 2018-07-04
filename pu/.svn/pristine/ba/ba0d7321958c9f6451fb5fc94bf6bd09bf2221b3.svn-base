package nc.vo.pu.m27.merge;

import nc.bs.pubapp.AppBsContext;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.enumeration.ApportionMode;
import nc.vo.pu.costfactor.utils.CostFactorVOUtil;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
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
 * @time 2010-3-29 下午03:07:46
 */
public class FeeAllot {

  protected static final int AllotUse_DifferentMaterial = 1;

  /** 是折扣分摊还是异物料分摊 */
  protected static final int AllotUse_Discount = 0;

  private SettleEnvironment m_settleEnv = null;

  private ICostfactorDiscount[] m_voaAllotObject = null;

  private FeeDiscountSettleVO[] m_voaDiscount = null;

  private FeeDiscountSettleVO[] m_voaFee = null;

  /**
   * FeeAllot 的构造子
   * 
   * @param voaAllotObject
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   */
  public FeeAllot(ICostfactorDiscount[] voaAllotObject,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      SettleEnvironment settleEnv) {

    this.m_voaAllotObject = voaAllotObject;
    this.m_voaFee = voaFee;
    this.m_voaDiscount = voaDiscount;
    this.m_settleEnv = settleEnv;

    // 初始化结算环境
    settleEnv.setLoginDate(AppBsContext.getInstance().getBusiDate());

  }

  /**
   * 分摊
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaMaterial
   * @param voaFee
   * @param voaDiscount
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-11 下午01:45:52
   */
  public void allot() throws BusinessException {

    if (this.getAllotObjectVOs() == null || this.getFeeVOs() == null
        && this.getDiscountVOs() == null
        || this.getSettleEnv().getCostFactorVOs() == null) {
      return;
    }

    {
      UFDouble[] daSumFactor = new UFDouble[CostfactorVO.MAX_NUM];
      if (this.getFeeVOs() != null) {
        int sequence = -1;
        for (int i = 0; i < this.getFeeVOs().length; i++) {
          // 得到物料所在的成本要素，返回的成本要素从0开始
          sequence =
              CostFactorVOUtil.getSequence(this.getSettleEnv()
                  .getCostFactorVOs(), this.getFeeVOs()[i].getPk_srcmaterial());
          if (sequence == -1) {
            continue;
          }
          daSumFactor[sequence] =
              MathTool.nvl(daSumFactor[sequence]).add(
                  this.getFeeVOs()[i].getNcurrentotalsettlemny());
        }
      }

      // －－－－－成本要素
      for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
        if (daSumFactor[i] == null) {
          continue;
        }
        UFDouble dFactorDenominator = UFDouble.ZERO_DBL;
        UFDouble[] daFactorNumerator =
            new UFDouble[this.getAllotObjectVOs().length];
        // 分子、分母
        for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
          // 按数量
          if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.NUM.value())) {
            daFactorNumerator[j] = this.getAllotObjectVOs()[j].getNallotnum();
          }
          // 按金额
          else if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.MONEY.value())) {
            daFactorNumerator[j] = this.getAllotObjectVOs()[j].getNallotmoney();
          }
          // 按重量
          else if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.WEIGHT.value())) {
            daFactorNumerator[j] =
                this.getSettleEnv().getUnitWeight(
                    this.getAllotObjectVOs()[j].getPk_srcmaterial());
            daFactorNumerator[j] =
                daFactorNumerator[j] == null ? UFDouble.ZERO_DBL
                    : daFactorNumerator[j];
            daFactorNumerator[j] =
                this.getAllotObjectVOs()[j]
                    .getNallotnum()
                    .multiply(daFactorNumerator[j])
                    .setScale(this.getSettleEnv().getUnitWeightDigit(),
                        UFDouble.ROUND_HALF_UP);
          }
          // 按体积
          else if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.VOLUME.value())) {
            daFactorNumerator[j] =
                this.getSettleEnv().getUnitVolume(
                    this.getAllotObjectVOs()[j].getPk_srcmaterial());
            daFactorNumerator[j] =
                this.getAllotObjectVOs()[j]
                    .getNallotnum()
                    .multiply(MathTool.nvl(daFactorNumerator[j]))
                    .setScale(this.getSettleEnv().getUnitVolumneDigit(),
                        UFDouble.ROUND_HALF_UP);
          }
          dFactorDenominator = dFactorDenominator.add(daFactorNumerator[j]);

        }

        if (dFactorDenominator.compareTo(UFDouble.ZERO_DBL) == 0) {
          int num = i + 1;
          throw new BusinessException(NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004060_0", "04004060-0229", null, new String[] {
                String.valueOf(num)
              })/* 成本要素 {0} 的分摊依据总和为0，无法分摊！ */);
        }

        // 至当前行为止的成本要素总金额、分子的合计（用来判断是否最后一行）
        UFDouble dUsedFactor = UFDouble.ZERO_DBL;
        UFDouble dSumNumerator = UFDouble.ZERO_DBL;
        // 分摊
        for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
          // 成本要素分子有值时才算
          if (MathTool.compareTo(daFactorNumerator[j], UFDouble.ZERO_DBL) == 0) {
            continue;
          }

          dSumNumerator = dSumNumerator.add(daFactorNumerator[j]);
          // -----更新发票的成本要素、结算总金额
          // 最后一个可分摊的行时：按当前分子+累计分子＝分母计算
          if (dSumNumerator.compareTo(dFactorDenominator) == 0) {
            ICostfactorDiscountUtil.setNcostfactor(this.getAllotObjectVOs()[j],
                i, daSumFactor[i].sub(dUsedFactor));
          }
          else {
            ICostfactorDiscountUtil.setNcostfactor(
                this.getAllotObjectVOs()[j],
                i,
                ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                    daFactorNumerator[j].multiply(daSumFactor[i]).div(
                        dFactorDenominator), this.getSettleEnv().getOrgCurr()));
          }
          // 成本要素进入成本时才更新结算金额
          if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getBentercost().booleanValue()) {
            this.getAllotObjectVOs()[j]
                .addtoCurrenttotalsettlemoney(ICostfactorDiscountUtil
                    .getNcostfactor(this.getAllotObjectVOs()[j], i));
          }
          // 记录已使用的成本要素
          dUsedFactor =
              dUsedFactor.add(ICostfactorDiscountUtil.getNcostfactor(
                  this.getAllotObjectVOs()[j], i));
        }
      }
    }

    // －－－－－折扣
    this.discountAndDifferentMaterialAllot(FeeAllot.AllotUse_Discount,
        ApportionMode.MONEY, this.getDiscountVOs());

    return;
  }

  private ICostfactorDiscount[] getAllotObjectVOs() {
    return this.m_voaAllotObject;
  }

  private FeeDiscountSettleVO[] getDiscountVOs() {
    return this.m_voaDiscount;
  }

  private FeeDiscountSettleVO[] getFeeVOs() {
    return this.m_voaFee;
  }

  private SettleEnvironment getSettleEnv() {
    return this.m_settleEnv;
  }

  /**
   * 分摊折扣或异物料
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param allotUse AllotDiscount、AllotDifferentMaterial
   * @param allotType ApportionMode.NUM、ApportionMode.MONEY
   * @param vosInvoice
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-29 下午03:48:59
   */
  protected void discountAndDifferentMaterialAllot(int allotUse,
      ApportionMode allotType, InvoiceSettleVO[] vosInvoice)
      throws BusinessException {

    if (this.getAllotObjectVOs() == null || vosInvoice == null) {
      return;
    }

    // －－－－－折扣
    // 先算出总分摊金额的SUM值
    UFDouble dSumMoney = UFDouble.ZERO_DBL;
    for (int i = 0; i < vosInvoice.length; i++) {
      dSumMoney = dSumMoney.add(vosInvoice[i].getNcurrentotalsettlemny());
    }
    UFDouble dDenominator = UFDouble.ZERO_DBL;
    UFDouble[] daNumerator = new UFDouble[this.getAllotObjectVOs().length];
    boolean bAllotNum =
        ApportionMode.NUM.value().equals(allotType) ? true : false;
    if (allotUse == FeeAllot.AllotUse_Discount) {
      bAllotNum = false;
    }
    // 分子、分母
    for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
      // 折扣
      daNumerator[j] =
          bAllotNum ? this.getAllotObjectVOs()[j].getNallotnum() : this
              .getAllotObjectVOs()[j].getNallotmoney();
      dDenominator = dDenominator.add(daNumerator[j]);
    }
    if (dDenominator.compareTo(UFDouble.ZERO_DBL) == 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0143")/* @res "折扣的分摊依据总和为0，无法分摊！" */);
    }
    // 至当前行为止的总金额、分子的合计（用来判断是否最后一行）
    UFDouble dUsedMoney = UFDouble.ZERO_DBL;
    UFDouble dSumNumerator = UFDouble.ZERO_DBL;
    UFDouble dTemp = null;
    for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
      // -----更新发票的折扣(或本次发票结算金额)、结算总金额
      // 最后一个可分摊的行时：按当前分子+累计分子＝分母计算
      dSumNumerator = dSumNumerator.add(daNumerator[j]);
      if (dSumNumerator.compareTo(dDenominator) == 0) {
        dTemp = dSumMoney.sub(dUsedMoney);
      }
      else {
        dTemp =
            ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                daNumerator[j].multiply(dSumMoney).div(dDenominator),
                this.getSettleEnv().getOrgCurr());
      }

      if (allotUse == FeeAllot.AllotUse_Discount) {
        this.getAllotObjectVOs()[j].setNdiscount(dTemp);
      }
      else if (allotUse == FeeAllot.AllotUse_DifferentMaterial) {
        ((StockSettleVO) this.getAllotObjectVOs()[j])
            .setNcurrentinvoicesettlemny(dTemp);
      }
      this.getAllotObjectVOs()[j].addtoCurrenttotalsettlemoney(dTemp);

      dUsedMoney = dUsedMoney.add(dTemp);
    }

    return;
  }
}
