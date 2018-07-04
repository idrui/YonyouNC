/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-25 下午02:07:08
 */
package nc.ui.pu.est.rule.feediv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.ui.ml.NCLangRes;
import nc.vo.bd.accessor.IBDData;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pu.est.util.FeeEstRelaItems;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对前台用户输入的费用数据进行分摊
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-25 下午02:07:08
 */
public class EstFeeUIDivider extends AbstractUIFeeDivide {

  private static class LastRowCalObject {
    private FeeMnyDivideVO divVo;

    private FeeEstVO fee;

    public LastRowCalObject(FeeMnyDivideVO divVo, FeeEstVO fee) {
      this.divVo = divVo;
      this.fee = fee;
    }

    public FeeMnyDivideVO getDivVo() {
      return this.divVo;
    }

    public FeeEstVO getFee() {
      return this.fee;
    }

  }

  private void fillTotalValue(FeeEstVO fee, FeeMnyVO feeMnyVO) {
    // wuxla V61
    feeMnyVO.setEstCalcostmny(fee.getNcalcostmny());
    feeMnyVO.setEstMny(fee.getNestmny());// 本币无税金额
    feeMnyVO.setEstTax(fee.getNesttaxmny());// 本币税额
    feeMnyVO.setEstTaxmny(fee.getNesttotalmny());// 本币价税合计
    feeMnyVO.setOrigEstMny(fee.getNestomny());// 原币无税金额
    feeMnyVO.setEstNoSubTaxmny(fee.getNnosubtax());// 不可抵扣税额
  }

  /**
   * 方法功能描述：生成新的费用暂估VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param divfee
   *          用户分摊窗口中录入的费用行
   * @param estVO
   *          分摊的暂估VO
   * @param divVo
   *          分摊数据VO
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-25 下午03:54:06
   */
  private FeeEstVO genNewFeeVO(FeeEstVO divfee, EstVO estVO,
      FeeMnyDivideVO divVo) {
    FeeEstVO newFee;
    newFee = (FeeEstVO) divfee.clone();
    EstVODefualtValueUtil.setFeeEstDefValue(newFee, estVO.getParentVO(),
        divfee.getPk_estpsn());
    newFee.setNestototalmny(divVo.getDividedmny());
    FeeEstVO[] oldFees = estVO.getChildrenVO();

    if (ArrayUtils.isEmpty(oldFees)) {
      if (estVO instanceof PurchaseInEstVO) {
        estVO.setChildrenVO(new PurchaseinFIFeeVO[] {
          (PurchaseinFIFeeVO) newFee
        });
      }
      else if (estVO instanceof VmiEstVO) {
        estVO.setChildrenVO(new VmiFIFeeVO[] {
          (VmiFIFeeVO) newFee
        });
      }
      else {
        estVO.setChildrenVO(new FeeEstVO[] {
          newFee
        });
      }
    }
    else {
      List<FeeEstVO> oldFeeList =
          new ArrayList<FeeEstVO>(Arrays.asList(oldFees));
      oldFeeList.add(newFee);

      if (estVO instanceof PurchaseInEstVO) {
        estVO.setChildrenVO(oldFeeList.toArray(new PurchaseinFIFeeVO[oldFeeList
            .size()]));
      }
      else if (estVO instanceof VmiEstVO) {
        estVO
            .setChildrenVO(oldFeeList.toArray(new VmiFIFeeVO[oldFeeList.size()]));
      }
      else {
        estVO
            .setChildrenVO(oldFeeList.toArray(new FeeEstVO[oldFeeList.size()]));
      }

    }
    return newFee;
  }

  /**
   * 处理倒挤
   * 
   * @param divVo 已经分摊的vo
   * @param estVOs 全部数据
   * @param oldfee 当前费用vo
   */
  private void processLastRow(FeeMnyDivideVO divVo, EstVO[] estVOs,
      FeeEstVO feevo) {
    String pk_stockp_b = divVo.getBillpk();
    String pk_srcfeematerial = feevo.getPk_srcfeematerial();
    UFDouble tax = UFDouble.ZERO_DBL;// 本币税额
    UFDouble ncalcostmny = UFDouble.ZERO_DBL; // 记成本金额
    UFDouble nmny = UFDouble.ZERO_DBL; // 本币无税金额
    UFDouble noirgmny = UFDouble.ZERO_DBL;// 原币无税金额
    UFDouble ntaxmny = UFDouble.ZERO_DBL; // 本币价税合计
    UFDouble nnosubTaxMny = UFDouble.ZERO_DBL; // 不可抵扣税额
    for (EstVO estvo : estVOs) {
      if (pk_stockp_b.equals(estvo.getParentVO().getPk_stockps_b())) {
        continue;
      }
      for (FeeEstVO fee : estvo.getChildrenVO()) {
        if (!fee.getPk_srcfeematerial().equals(pk_srcfeematerial)) {
          continue;
        }
        tax = MathTool.add(tax, fee.getNesttaxmny());
        ncalcostmny = MathTool.add(ncalcostmny, fee.getNcalcostmny());
        nmny = MathTool.add(nmny, fee.getNestmny());
        noirgmny = MathTool.add(noirgmny, fee.getNestomny());
        ntaxmny = MathTool.add(ntaxmny, fee.getNesttotalmny());
        nnosubTaxMny = MathTool.add(nnosubTaxMny, fee.getNnosubtax());
      }
    }
    feevo.setNestomny(MathTool.sub(divVo.getTotalOrigMny(), noirgmny));// 原币无税金额
    feevo.setNesttaxmny(MathTool.sub(divVo.getTotalTax(), tax));// 本币税额
    // wuxla v61
    feevo.setNestmny(MathTool.sub(divVo.getTotalMny(), nmny));// 本币无税金额
    feevo.setNesttotalmny(MathTool.sub(divVo.getTotalTaxMny(), ntaxmny));// 本币价税合计
    // wuxla v61 计税金额
    feevo.setNcalcostmny(MathTool.sub(divVo.getTotalCalcostMny(), ncalcostmny));
    feevo.setNnosubtax(MathTool.sub(divVo.getTotalNoSubTaxMny(), nnosubTaxMny));// 不可抵扣税额
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide#checkEstimatedFee(nc.vo.pub.AggregatedValueObject[],
   *      nc.vo.pub.CircularlyAccessibleValueObject)
   */
  @Override
  protected void checkEstimatedFee(AggregatedValueObject[] datas,
      CircularlyAccessibleValueObject feeVo) {
    FeeEstVO fee = (FeeEstVO) feeVo;
    for (AggregatedValueObject data : datas) {
      String pk_srcmaterial = fee.getPk_srcfeematerial();
      FeeEstVO[] oldfees = (FeeEstVO[]) data.getChildrenVO();
      if (ArrayUtils.isEmpty(oldfees)) {
        continue;
      }
      for (FeeEstVO oldfee : oldfees) {
        String pk_fee = oldfee.getPk_stockps_fee();
        if (StringUtil.isEmptyWithTrim(pk_fee)) {
          continue;
        }
        String vo_srcmaterial = oldfee.getPk_srcfeematerial();
        if (!pk_srcmaterial.equals(vo_srcmaterial)) {
          continue;
        }
        GoodsEstVO head = (GoodsEstVO) data.getParentVO();
        String billcode = head.getVbillcode();
        String rowno = head.getCrowno();
        IBDData[] bddata = MaterialAccessor.getDocbyPks(new String[] {
          pk_srcmaterial
        });
        MultiLangText langText = bddata[0].getName();
        String msg =
            NCLangRes.getInstance().getStrByID(
                "4004060_0",
                "04004060-0198",
                null,
                new String[] {
                  billcode, rowno,
                  langText.getText(langText.getCurrLangIndex())
                })/* 单据号:{0}的第{1}行对费用项{2}已经暂估，不能再进行费用分摊！ */;
        ExceptionUtils.wrappBusinessException(msg);
      }
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide#getFeeMnyDivideVO(nc.vo.pub.AggregatedValueObject)
   */
  @Override
  protected FeeMnyDivideVO getFeeMnyDivideVO(AggregatedValueObject data) {
    EstVO vo = (EstVO) data;
    String pk_material = vo.getParentVO().getPk_material();
    UFDouble settlmny = vo.getParentVO().getNaccpreeststtlmny();// 暂估前结算金额
    UFDouble estmny = vo.getParentVO().getNestmny();// 暂估金额
    UFDouble mny = MathTool.add(estmny, settlmny);
    UFDouble num = vo.getParentVO().getNinnum();
    // 红蓝对冲金额为0，取数量. 2011-11-17 孙聪、赵玉行、田锋涛一起确认
    if (MathTool.isZero(mny)) {
      mny = num;
    }
    FeeMnyDivideVO diviedVO = new FeeMnyDivideVO(mny, num, pk_material);
    diviedVO.setBillpk(vo.getParentVO().getPk_stockps_b());
    diviedVO.setBillhpk(vo.getParentVO().getPk_stockps());

    return diviedVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide#getFeeMnyVO(nc.vo.pub.CircularlyAccessibleValueObject)
   */
  @Override
  protected FeeMnyVO getFeeMnyVO(CircularlyAccessibleValueObject feeVo) {
    FeeEstVO fee = (FeeEstVO) feeVo;
    String moid = fee.getPk_srcfeematerial();
    UFDouble mny = fee.getNestototalmny();
    String pk_curr = fee.getPk_estcurrency();
    int digit = CurrencyInfo.getDigit(pk_curr);

    FeeMnyVO feeMnyVO = new FeeMnyVO(moid, mny, digit);
    this.fillTotalValue(fee, feeMnyVO);
    return feeMnyVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide#isCanDiv(nc.vo.pub.CircularlyAccessibleValueObject)
   */
  @Override
  protected boolean isCanDiv(CircularlyAccessibleValueObject feeVo) {
    FeeEstVO fee = (FeeEstVO) feeVo;
    boolean flag = super.isCanDiv(feeVo);
    flag &= 0 != MathTool.compareTo(UFDouble.ZERO_DBL, fee.getNestototalmny());
    return flag;
  }

  @Override
  protected void updateSelectDatas(AggregatedValueObject[] datas,
      FeeMnyDivideVO[] divVos, CircularlyAccessibleValueObject feeVo) {
    FeeEstVO divfee = (FeeEstVO) feeVo;
    Map<String, FeeMnyDivideVO> diviedVOMap =
        new HashMap<String, FeeMnyDivideVO>();
    for (FeeMnyDivideVO divivo : divVos) {
      diviedVOMap.put(divivo.getBillpk(), divivo);
    }
    EstVO[] estVOs = (EstVO[]) ArrayUtil.convertArrayType(datas);
    List<LastRowCalObject> calObjectList = new ArrayList<LastRowCalObject>();
    for (int i = 0; i < datas.length; i++) {
      EstVO estVO = (EstVO) datas[i];
      FeeMnyDivideVO divVo =
          diviedVOMap.get(estVO.getParentVO().getPk_stockps_b());
      if (divVo == null) {
        continue;
      }
      EstRelationCalcUtil calcUtil =
          new EstRelationCalcUtil(estVO.getParentVO().getPk_group(),
              new FeeEstRelaItems());
      FeeEstVO[] oldFees = estVO.getChildrenVO();
      // estVO.getParentVO().getAttributeValue( GoodsEstVO.NFEEMNY);
      // estVO.getParentVO().getAttributeValue( GoodsEstVO.NFEETAXMNY);
      boolean isUpdate = false;

      if (!ArrayUtils.isEmpty(oldFees)) {
        for (FeeEstVO oldfee : oldFees) {
          String ofeeoid = oldfee.getPk_srcfeematerial();
          String feeoid = divfee.getPk_srcfeematerial();
          if (feeoid.equals(ofeeoid)) {
            oldfee.setNestototalmny(divVo.getDividedmny());
            oldfee.setNesttaxrate(divfee.getNesttaxrate());
            oldfee.setPk_supplier(divfee.getPk_supplier());
            oldfee.setPk_estcurrency(divfee.getPk_estcurrency());
            oldfee.setNestexchgrate(divfee.getNestexchgrate());
            // wuxla v61
            oldfee.setCsendcountryid(divfee.getCsendcountryid());
            oldfee.setCrececountryid(divfee.getCrececountryid());
            oldfee.setCtaxcountryid(divfee.getCtaxcountryid());
            oldfee.setFbuysellflag(divfee.getFbuysellflag());
            oldfee.setBtriatradeflag(divfee.getBtriatradeflag());
            oldfee.setBopptaxflag(divfee.getBopptaxflag());
            oldfee.setCtaxcodeid(divfee.getCtaxcodeid());
            oldfee.setNnosubtaxrate(divfee.getNnosubtaxrate());
            oldfee.setNnosubtax(divfee.getNnosubtax());
            oldfee.setFtaxtypeflag(divfee.getFtaxtypeflag());
            calcUtil.calcVO(oldfee, FeeEstVO.NESTOTOTALMNY,
                PricePriority.TAXPRICE_PRIOR_TO_PRICE);// 需求确认含税优先
            // 加入倒挤处理
            if (divVo.isLastRow()) {
              calObjectList.add(new LastRowCalObject(divVo, oldfee));
            }
            isUpdate = true;
            break;
          }
        }
      }
      if (!isUpdate) {
        FeeEstVO newFee = this.genNewFeeVO(divfee, estVO, divVo);
        calcUtil.calcVO(newFee, FeeEstVO.NESTOTOTALMNY,
            PricePriority.TAXPRICE_PRIOR_TO_PRICE);
      }
    }
    // 倒挤处理
    if (calObjectList.size() > 0) {
      for (LastRowCalObject calObject : calObjectList) {
        this.processLastRow(calObject.getDivVo(), estVOs, calObject.getFee());
      }
    }
  }

}
