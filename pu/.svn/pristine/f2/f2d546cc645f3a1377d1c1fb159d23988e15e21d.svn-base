package nc.vo.pu.m27.merge.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.merge.BillItemCalData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * 匹配时处理合理损耗数量<br>
 * 发票与入库单匹配时，处理合理损耗，包括最后一次倒挤<br>
 * 也会处理升溢数量<br>
 * V5X逻辑：对于正入库单，出现升溢时，会将升溢数量放到最大可结算数量的入库单的最后一次结算行上<br>
 * 更新InvoiceSettleVO的累计合理损耗结算数量，累计结算金额（合理损耗部分的）
 * 
 * @since 6.0
 * @version 2011-5-24 下午05:50:54
 * @author zhaoyha
 */
public class MatchMergeReasonWasteNumRule {

  private SettleEnvironment env;

  private InvoiceSettleVO invcStlVo;

  private BillItemCalData stlItemCalData;

  private ScaleUtils su = new ScaleUtils(InvocationInfoProxy.getInstance()
      .getGroupId());

  /**
   * 生成合理损耗结算处理工具
   * 
   * @param env 结算信息
   * @param invcStlVo 发票结算VO
   * @param stlItemCalData 生成的结算表体数据
   */
  public MatchMergeReasonWasteNumRule(SettleEnvironment env,
      InvoiceSettleVO invcStlVo, BillItemCalData stlItemCalData) {
    super();
    this.env = env;
    this.invcStlVo = invcStlVo;
    this.stlItemCalData = stlItemCalData;
  }

  /**
   * 计算合理损耗的相关信息（金额、数量、单价）
   * 
   * @param realInvcStlNum 本次发票实际结算数量
   * @param realStlNum 本次实现结算数量（入库单结算数量，也即结算单行上的数量）
   * @param inCreaseNum 升溢的数量
   * @return
   */
  public BillItemCalData calReasonWasteNum(UFDouble realInvcStlNum,
      UFDouble realStlNum, UFDouble inCreaseNum) {
    // 无合理损耗，无升溢，则不需要处理，退货的发票（按V5X规则）也先不处理合理损耗
    if (MathTool.isZero(this.invcStlVo.getNreasonwastenum())
        && MathTool.isZero(inCreaseNum)
        || MathTool.lessThan(this.invcStlVo.getNcansettlenum(),
            UFDouble.ZERO_DBL)) {
      return this.stlItemCalData;
    }
    // 只处理升溢
    if (MathTool.isZero(this.invcStlVo.getNreasonwastenum())
        && !MathTool.isZero(inCreaseNum)) {
      this.calWhenLastTime(realStlNum, inCreaseNum);
      return this.stlItemCalData;
    }
    if (MathTool
        .add(this.invcStlVo.getNcurrentaccumsettlenum(), realInvcStlNum)
        .compareTo(InvoiceSettleVOUtil.getCurrentRealSettleNum(this.invcStlVo)) == 0) {
      this.calWhenLastTime(realStlNum, inCreaseNum); // 发票是最后一次结算
    }
    else {
      this.calInMatching(realInvcStlNum, realStlNum);// 匹配中进行合理损耗的计算
    }
    return this.stlItemCalData;
  }

  private void calInMatching(UFDouble realInvcStlNum, UFDouble realStlNum) {
    UFDouble divReasonNum = this.getDivReasonWasteNum(realInvcStlNum);
    UFDouble divReasonMny = this.getDivReasonWasteMny(divReasonNum);
    this.stlItemCalData.setNReasonWasteNum(divReasonNum);
    // 更新发票的累计结算合理损耗数量，累计结算金额等信息
    // (不更新金额，只更新数量，累计结算金额由调用者处理)
    this.updateInvcStlInfo(divReasonNum, null);
    // 如果合理损耗进成本
    if (this.isWasteEnterCost()) {
      // 使用合理损耗金额重新设置结算信息（金额， 单价）
      this.reCalStlItemDataInfo(realStlNum, divReasonMny);
      return;
    }
    // 如果合理损耗不进成本
    this.stlItemCalData.setNReasonWasteMny(divReasonMny);
    this.stlItemCalData.setNReasonWastePrice(this.invcStlVo.getNprice());
    // 更新发票的累计结算合理损耗数量，累计结算金额等信息
    // (不更新数量，只更新金额，不进成本时调用者未处理合理损耗金额)
    this.updateInvcStlInfo(null, divReasonMny);
  }

  private void calWhenLastTime(UFDouble realStlNum, UFDouble inCreaseNum) {
    UFDouble divReasonNum =
        MathTool.sub(this.invcStlVo.getNreasonwastenum(),
            this.invcStlVo.getNcurrentaccreasonwastenum());
    // 算上升溢的数量
    UFDouble divReasonIncreaseNum = MathTool.add(divReasonNum, inCreaseNum);
    this.stlItemCalData.setNReasonWasteNum(divReasonIncreaseNum);
    UFDouble divReasonMny = this.getDivReasonWasteMny(divReasonIncreaseNum);
    // 更新发票的累计结算合理损耗数量，累计结算金额等信息
    // (不更新金额，只更新数量，累计结算金额由调用者处理)
    this.updateInvcStlInfo(divReasonNum, null);
    if (this.isWasteEnterCost()) {
      // 使用合理损耗金额重新设置结算信息－只重算一下结算及货物结算单价
      // by zhaoyha at 2011.11.15根据王印芬意见（还有孙聪、马会川、皮之兵）在场
      // 修改为与V57不一样，这里合理损耗进成本，也反算一下单价
      this.reCalStlItemDataInfo(realStlNum, null);
      return;
    }
    // 如果合理损耗不进成本
    this.stlItemCalData.setNReasonWasteMny(divReasonMny);
    this.stlItemCalData.setNReasonWastePrice(this.invcStlVo.getNprice());
    // 最后一次结算的本次结算金额，调用此类的方法中已经处理（包括了合理损耗金额）
    // 从最后一次倒挤的结算金额中减去合理损耗金额
    this.reCalStlItemDataInfo(realStlNum, MathTool.oppose(divReasonMny));
    // 更新发票的累计结算合理损耗数量，累计结算金额等信息
    // (不更新数量，只更新金额，不进成本时调用者未处理合理损耗金额)
    this.updateInvcStlInfo(null, divReasonMny);
  }

  private UFDouble getDivReasonWasteMny(UFDouble divReasonNum) {
    // 合理损耗金额＝合理损耗数量*发票单价
    UFDouble divReasonMny =
        ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
            divReasonNum.multiply(this.invcStlVo.getNprice()),
            this.env.getOrgCurr());
    return divReasonMny;
  }

  private UFDouble getDivReasonWasteNum(UFDouble realInvcStlNum) {
    UFDouble totalRealNum =
        InvoiceSettleVOUtil.getCurrentRealSettleNum(this.invcStlVo);
    // 分摊到的合理损耗数量为：（结算数量/发票总实际可结算数量）*发票合理损耗数量
    UFDouble divReasonNum =
        ScaleUtils.getScaleUtilAtBS().adjustNumScale(
            realInvcStlNum.div(totalRealNum, UFDouble.DEFAULT_POWER).multiply(
                this.invcStlVo.getNreasonwastenum()),
            this.invcStlVo.getCunitid());
    return divReasonNum;
  }

  private boolean isWasteEnterCost() {
    return UFBoolean.TRUE.equals(PUSysParamUtil.getPO75(this.invcStlVo
        .getPk_org()));
  }

  private void reCalStlItemDataInfo(UFDouble realStlNum, UFDouble divReasonMny) {
    // 2013-5-22 无法结算，提示。
    // 业务上，这时不应该有入库数量的，因为都已经损耗掉了。

    if (MathTool.isZero(realStlNum)) {
      return;
    }
    UFDouble goodsMny = this.stlItemCalData.getNgoodsmoney();
    UFDouble mny = this.stlItemCalData.getNmoney();
    goodsMny = MathTool.add(goodsMny, divReasonMny);
    mny = MathTool.add(mny, divReasonMny);
    this.stlItemCalData.setNgoodsmoney(goodsMny);
    this.stlItemCalData.setNmoney(mny);
    UFDouble goodsPrice =
        this.su.adjustSoPuPriceScale(
            goodsMny.div(realStlNum, UFDouble.DEFAULT_POWER),
            this.invcStlVo.getCcurrencyid());
    UFDouble price =
        this.su.adjustSoPuPriceScale(
            mny.div(realStlNum, UFDouble.DEFAULT_POWER),
            this.invcStlVo.getCorigcurrencyid());
    this.stlItemCalData.setNgoodsprice(goodsPrice);
    this.stlItemCalData.setNprice(price);
  }

  private void updateInvcStlInfo(UFDouble divReasonNum, UFDouble divReasonMny) {
    // 更新累计结算合理损耗数量
    this.invcStlVo.setNcurrentaccreasonwastenum(MathTool.add(divReasonNum,
        this.invcStlVo.getNcurrentaccreasonwastenum()));
    // 更新发票总的累计结算金额（原设计是包括折扣、费用等）
    this.invcStlVo.setNcurrentaccumtotalsettlemny(MathTool.add(
        this.invcStlVo.getNcurrentaccumtotalsettlemny(), divReasonMny));
    // 更新发票总的累计结算金额（纯货物结算金额）
    this.invcStlVo.setNcurrentaccuminvoicesettlemny(MathTool.add(
        this.invcStlVo.getNcurrentaccuminvoicesettlemny(), divReasonMny));
  }

}
