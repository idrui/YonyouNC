package nc.bs.pu.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.md.model.impl.MDEnum;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.costfactor.enumeration.ApportionMode;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.AbstractByValueGen;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.ByMnyGen;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.ByNumGen;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>进出口费用、调整发票分摊
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-19 下午01:12:53
 */
public class FeeDivideRuleForIT {
  private CostfactorViewVO[] cfviews;

  private FeeMnyDivideVO[] divideVos;

  private String pk_group;

  /**
   * FeeDivideRule 的构造子
   * 
   * @param pk_group 集团
   * @param cfviews 成本要素视图
   * @param divideVos 分摊依据及分摊结果
   */
  public FeeDivideRuleForIT(String pk_group, CostfactorViewVO[] cfviews,
      FeeMnyDivideVO[] divideVos) {
    this.pk_group = pk_group;
    this.cfviews = cfviews;
    this.divideVos = divideVos;
  }

  /**
   * 方法功能描述：开始对给定的费用进行分摊。
   * <p>
   * <b>参数说明</b>
   * 
   * @param feeMnyVos 要分摊的费用VO
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-25 上午09:54:22
   */
  public FeeMnyDivideVO[] divide(FeeMnyVO[] feeMnyVos) {
    Map<String, ArrayList<CostfactorViewVO>> cmap = null;
    if (!ArrayUtils.isEmpty(this.cfviews)) {
      cmap =
          CirVOUtil.getFieldVOList(this.cfviews,
              CostfactorItemVO.PK_SRCMATERIAL);
    }
    for (FeeMnyVO fmvo : feeMnyVos) {
      this.genByValue(fmvo.getFeeoid(), cmap); // 生成每个VO的分摊依据值
      this.divideOneFee(fmvo); // 开始分摊
    }
    return this.divideVos;
  }

  /**
   * 调整发票费用分摊
   * 
   * @param feeMnyVos
   * @return
   */

  public FeeMnyDivideVO[] divideForAjust(FeeMnyVO[] feeMnyVos) {
    for (FeeMnyVO fmvo : feeMnyVos) {
      // 生成每个VO的分摊依据值
      new ByNumGen(this.divideVos, this.pk_group).genByValue();
      this.divideOneFee(fmvo); // 开始分摊
    }
    return this.divideVos;
  }

  /**
   * 方法功能描述：专门提供对折扣类发票进行分摊（按照金额进行分摊）
   * <p>
   * <b>参数说明</b>
   * 
   * @param feeMnyVos
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 下午04:38:33
   */
  public FeeMnyDivideVO[] divideForDiscount(FeeMnyVO[] feeMnyVos) {
    for (FeeMnyVO fmvo : feeMnyVos) {
      // 生成每个VO的分摊依据值
      new ByMnyGen(this.divideVos, this.pk_group).genByValue();
      this.divideOneFee(fmvo); // 开始分摊
    }
    return this.divideVos;
  }

  private void divideOneFee(FeeMnyVO fmvo) {
    UFDouble feeMny = fmvo.getMny();
    if (0 == MathTool.compareTo(feeMny, UFDouble.ZERO_DBL)) {
      return;
    }
    /**** 得到总分摊依据数值 ****/
    UFDouble allByVal = UFDouble.ZERO_DBL;
    for (FeeMnyDivideVO divVo : this.divideVos) {
      allByVal = MathTool.add(allByVal, divVo.getByvalue());
    }
    if (0 == MathTool.compareTo(UFDouble.ZERO_DBL, allByVal)) {
      return;// 可能的情况是物料标准体积单位的精度和物料上的单位体积、数量精度不一致
    }
    /**** 进行分摊 ****/
    UFDouble dividedMny = UFDouble.ZERO_DBL;// 本次已分摊金额
    for (int i = 0; i < this.divideVos.length; ++i) {
      FeeMnyDivideVO divVo = this.divideVos[i];
      UFDouble divVal = UFDouble.ZERO_DBL;
      if (i == this.divideVos.length - 1) {
        divVal = MathTool.sub(feeMny, dividedMny);
        divVo.setLastRow(true);// 需要倒挤
      }
      else {
        UFDouble byVal = divVo.getByvalue();
        // 比率先取最大精度，否则会有问题
        UFDouble ratio = byVal.div(allByVal, UFDouble.DEFAULT_POWER);
        divVal = ratio.multiply(feeMny, fmvo.getDigit());
        dividedMny = MathTool.add(dividedMny, divVal);
      }
      divVo.setDividedmny(MathTool.add(divVal, divVo.getDividedmny()));
      // 补充合计相关的信息，后续倒挤用
      this.fillTotalValue(fmvo, divVo);

    }
  }

  /**
   * 补充合计相关的信息，后续倒挤用
   * 
   * @param fmvo
   * @param feeMny
   * @param divVo
   */
  private void fillTotalValue(FeeMnyVO fmvo, FeeMnyDivideVO divVo) {
    // wuxla V61 记成本金额、计税金额都需要倒挤
    divVo.setTotalCalcostMny(MathTool.add(fmvo.getEstCalcostmny(),
        divVo.getTotalCalcostMny()));
    divVo.setTotalCaltaxMny(MathTool.add(fmvo.getEstCaltaxmny(),
        divVo.getTotalCaltaxMny()));
    divVo.setTotalMny(MathTool.add(fmvo.getEstMny(), divVo.getTotalMny()));// 总本币无税金额
    divVo.setTotalTaxMny(MathTool.add(fmvo.getEstTaxmny(),
        divVo.getTotalTaxMny()));// 总本币价税合计
    divVo.setTotalOrigMny(MathTool.add(fmvo.getOrigEstMny(),
        divVo.getTotalOrigMny()));// 总原币无税金额
    divVo.setTotalOrigTaxMny(MathTool.add(fmvo.getMny(),
        divVo.getTotalOrigTaxMny()));// 总原币价税合计
    // wuxla V61去掉
    // divVo.setTotalOrigTax(MathTool.add(fmvo.getOrigEstTax(),
    // divVo.getTotalOrigTax()));// 总原币税额
    divVo.setTotalTax(MathTool.add(fmvo.getEstTax(), divVo.getTotalTax()));// 总本币税额
    divVo.setTotalNoSubTaxMny(MathTool.add(fmvo.getEstNoSubTaxmny(),
        divVo.getTotalNoSubTaxMny()));// 不可抵扣税额
  }

  private void genByValue(String pk_srcmaterial,
      Map<String, ArrayList<CostfactorViewVO>> cmap) {
    if (MapUtils.isEmpty(cmap)) {
      return;
    }
    List<CostfactorViewVO> cvlist = cmap.get(pk_srcmaterial);
    if (ListUtil.isEmpty(cvlist)) {
      return;
    }
    Integer byModeint = cvlist.get(0).getFapportionmode();
    ApportionMode byMode = MDEnum.valueOf(ApportionMode.class, byModeint);
    AbstractByValueGen bvGen =
        DivideByValueFactory.getByValueGen(this.pk_group, this.divideVos,
            byMode);
    // 分摊数值计算
    bvGen.genByValue();
  }
}
