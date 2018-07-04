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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-3-29 ����03:07:46
 */
public class FeeAllot {

  protected static final int AllotUse_DifferentMaterial = 1;

  /** ���ۿ۷�̯���������Ϸ�̯ */
  protected static final int AllotUse_Discount = 0;

  private SettleEnvironment m_settleEnv = null;

  private ICostfactorDiscount[] m_voaAllotObject = null;

  private FeeDiscountSettleVO[] m_voaDiscount = null;

  private FeeDiscountSettleVO[] m_voaFee = null;

  /**
   * FeeAllot �Ĺ�����
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

    // ��ʼ�����㻷��
    settleEnv.setLoginDate(AppBsContext.getInstance().getBusiDate());

  }

  /**
   * ��̯
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaMaterial
   * @param voaFee
   * @param voaDiscount
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-11 ����01:45:52
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
          // �õ��������ڵĳɱ�Ҫ�أ����صĳɱ�Ҫ�ش�0��ʼ
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

      // �����������ɱ�Ҫ��
      for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
        if (daSumFactor[i] == null) {
          continue;
        }
        UFDouble dFactorDenominator = UFDouble.ZERO_DBL;
        UFDouble[] daFactorNumerator =
            new UFDouble[this.getAllotObjectVOs().length];
        // ���ӡ���ĸ
        for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
          // ������
          if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.NUM.value())) {
            daFactorNumerator[j] = this.getAllotObjectVOs()[j].getNallotnum();
          }
          // �����
          else if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getFapportionmode().equals(ApportionMode.MONEY.value())) {
            daFactorNumerator[j] = this.getAllotObjectVOs()[j].getNallotmoney();
          }
          // ������
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
          // �����
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
              })/* �ɱ�Ҫ�� {0} �ķ�̯�����ܺ�Ϊ0���޷���̯�� */);
        }

        // ����ǰ��Ϊֹ�ĳɱ�Ҫ���ܽ����ӵĺϼƣ������ж��Ƿ����һ�У�
        UFDouble dUsedFactor = UFDouble.ZERO_DBL;
        UFDouble dSumNumerator = UFDouble.ZERO_DBL;
        // ��̯
        for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
          // �ɱ�Ҫ�ط�����ֵʱ����
          if (MathTool.compareTo(daFactorNumerator[j], UFDouble.ZERO_DBL) == 0) {
            continue;
          }

          dSumNumerator = dSumNumerator.add(daFactorNumerator[j]);
          // -----���·�Ʊ�ĳɱ�Ҫ�ء������ܽ��
          // ���һ���ɷ�̯����ʱ������ǰ����+�ۼƷ��ӣ���ĸ����
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
          // �ɱ�Ҫ�ؽ���ɱ�ʱ�Ÿ��½�����
          if (this.getSettleEnv().getCostFactorVOs()[i].getParentVO()
              .getBentercost().booleanValue()) {
            this.getAllotObjectVOs()[j]
                .addtoCurrenttotalsettlemoney(ICostfactorDiscountUtil
                    .getNcostfactor(this.getAllotObjectVOs()[j], i));
          }
          // ��¼��ʹ�õĳɱ�Ҫ��
          dUsedFactor =
              dUsedFactor.add(ICostfactorDiscountUtil.getNcostfactor(
                  this.getAllotObjectVOs()[j], i));
        }
      }
    }

    // �����������ۿ�
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
   * ��̯�ۿۻ�������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param allotUse AllotDiscount��AllotDifferentMaterial
   * @param allotType ApportionMode.NUM��ApportionMode.MONEY
   * @param vosInvoice
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-29 ����03:48:59
   */
  protected void discountAndDifferentMaterialAllot(int allotUse,
      ApportionMode allotType, InvoiceSettleVO[] vosInvoice)
      throws BusinessException {

    if (this.getAllotObjectVOs() == null || vosInvoice == null) {
      return;
    }

    // �����������ۿ�
    // ������ܷ�̯����SUMֵ
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
    // ���ӡ���ĸ
    for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
      // �ۿ�
      daNumerator[j] =
          bAllotNum ? this.getAllotObjectVOs()[j].getNallotnum() : this
              .getAllotObjectVOs()[j].getNallotmoney();
      dDenominator = dDenominator.add(daNumerator[j]);
    }
    if (dDenominator.compareTo(UFDouble.ZERO_DBL) == 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0143")/* @res "�ۿ۵ķ�̯�����ܺ�Ϊ0���޷���̯��" */);
    }
    // ����ǰ��Ϊֹ���ܽ����ӵĺϼƣ������ж��Ƿ����һ�У�
    UFDouble dUsedMoney = UFDouble.ZERO_DBL;
    UFDouble dSumNumerator = UFDouble.ZERO_DBL;
    UFDouble dTemp = null;
    for (int j = 0; j < this.getAllotObjectVOs().length; j++) {
      // -----���·�Ʊ���ۿ�(�򱾴η�Ʊ������)�������ܽ��
      // ���һ���ɷ�̯����ʱ������ǰ����+�ۼƷ��ӣ���ĸ����
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
