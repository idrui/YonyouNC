/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-25 上午11:01:49
 */
package nc.ui.pu.est.rule.feediv;

import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pu.est.model.EstUIContext;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.rule.feedivide.FeeDivideRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>前台对用户输入的费用进行分摊
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-25 上午11:01:49
 */
public abstract class AbstractUIFeeDivide {
  private CostfactorViewVO[] factors;

  private String pk_group;

  protected String pk_user;

  public void feeDiv(BillManageModel model, Object vos) {
    if (null == vos) {
      return;
    }
    if (ArrayUtils.isEmpty(model.getSelectedOperaDatas())) {
      return;
    }
    EstUIContext context = (EstUIContext) model.getContext();
    this.pk_group = context.getPk_group();
    this.factors = context.getFactors();
    this.pk_user = context.getPk_loginUser();
    Object[] modelDatas = model.getSelectedOperaDatas();
    AggregatedValueObject[] datas =
        (AggregatedValueObject[]) EstVOUtil.getCloneEstData(modelDatas);
    CircularlyAccessibleValueObject[] feeVos =
        (CircularlyAccessibleValueObject[]) vos;
    this.divToSelectDatas(datas, feeVos);
    this.synModelData(model, datas);
  }

  private void clearDividedMny(FeeMnyDivideVO[] feeDivVos) {
    for (FeeMnyDivideVO vo : feeDivVos) {
      vo.setDividedmny(null);
      vo.setTotalMny(null);
      vo.setTotalOrigMny(null);
      vo.setTotalOrigTaxMny(null);
      vo.setTotalTaxMny(null);
      vo.setTotalTax(null);
      vo.setTotalOrigTax(null);
      vo.setTotalCalcostMny(null);
      vo.setTotalCaltaxMny(null);
      vo.setTotalNoSubTaxMny(null);
    }
  }

  private void divToSelectDatas(AggregatedValueObject[] datas,
      CircularlyAccessibleValueObject[] feeVos) {
    FeeMnyDivideVO[] feeDivVos = this.getFeeMnyDivideVOs(datas);
    for (CircularlyAccessibleValueObject feeVo : feeVos) {
      if (!this.isCanDiv(feeVo)) {
        continue;
      }
      this.checkEstimatedFee(datas, feeVo);
      FeeMnyVO fmvo = this.getFeeMnyVO(feeVo);
      FeeMnyDivideVO[] filterFDVos = this.filterFMDVos(feeDivVos, fmvo);
      if (ArrayUtils.isEmpty(filterFDVos)) {
        continue;
      }
      this.clearDividedMny(filterFDVos);
      FeeDivideRule rule =
          new FeeDivideRule(this.pk_group, this.factors, filterFDVos);
      feeDivVos = rule.divide(new FeeMnyVO[] {
        fmvo
      });
      this.updateSelectDatas(datas, feeDivVos, feeVo);
    }
  }

  /** 过滤掉符合不同的分摊依据 **/
  private FeeMnyDivideVO[] filterFMDVos(FeeMnyDivideVO[] feeDivVos,
      FeeMnyVO fmvo) {
    List<FeeMnyDivideVO> newVos = new ArrayList<FeeMnyDivideVO>();
    UFDouble feemny = fmvo.getMny();
    for (FeeMnyDivideVO divVo : feeDivVos) {
      boolean isNumPositive =  UFDouble.ZERO_DBL.compareTo(divVo.getBillnum()) < 0;
      boolean isMnyPositive =  UFDouble.ZERO_DBL.compareTo(feemny) < 0;
      // 如果数量大于0，费用项金额小于0，则过滤
      if (isNumPositive && !isMnyPositive) {
        continue;
      }
      newVos.add(divVo);
    }
    if (newVos.size() == feeDivVos.length) {
      return feeDivVos;
    }
    return newVos.toArray(new FeeMnyDivideVO[newVos.size()]);
  }

  private FeeMnyDivideVO[] getFeeMnyDivideVOs(AggregatedValueObject[] datas) {
    FeeMnyDivideVO[] feeDivVos = new FeeMnyDivideVO[datas.length];
    for (int i = 0; i < feeDivVos.length; i++) {
      feeDivVos[i] = this.getFeeMnyDivideVO(datas[i]);
    }
    return feeDivVos;
  }

  private void synModelData(BillManageModel model, AggregatedValueObject[] datas) {
    for (AggregatedValueObject data : datas) {
      int i = model.findBusinessData(data);
      if (0 > i) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4004060_0", "04004060-0197")/* 界面数据发生严重错误，请重新查询再操作! */);
      }
      model.getData().set(i, data);
    }
  }

  /** 检查某个费用项是否已经暂估，这里不保险（后台还要检查） **/
  protected abstract void checkEstimatedFee(AggregatedValueObject[] datas,
      CircularlyAccessibleValueObject feeVo);

  /** 根据货物暂估数据，生成分摊VO **/
  protected abstract FeeMnyDivideVO getFeeMnyDivideVO(AggregatedValueObject data);

  /** 根据费用项，生成待分摊费用VO **/
  protected abstract FeeMnyVO getFeeMnyVO(CircularlyAccessibleValueObject feeVo);

  /**
   * 判断一个费用项是否可被分摊，即金额是否为零
   * 
   * @param feeVo
   *          费用VO
   **/
  protected boolean isCanDiv(CircularlyAccessibleValueObject feeVo) {
    return true;
  }

  /** 根据分摊结果，更新选择的货物暂估数据 **/
  protected abstract void updateSelectDatas(AggregatedValueObject[] datas,
      FeeMnyDivideVO[] divVos, CircularlyAccessibleValueObject feeVo);

}
