/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 下午03:04:31
 */
package nc.bs.pu.est.rule.fee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.rule.feedivide.FeeDivideRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用分摊公共算法
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-29 下午03:04:31
 */
public class EstFeeBSDIVRule<T extends FeeEstDistVO> {
  /** 每个货物行对应的分摊依据VO **/
  private Map<String, FeeMnyDivideVO[]> bidFMDivVOMap;

  /** 货物暂估PK到VO的Map **/
  private Map<String, GoodsEstVO> estMap;

  /** 成本要素信息 **/
  private CostfactorViewVO[] factors;

  private Class<T> fdClazz;

  /** 所有的费用暂估行 **/
  private FeeEstVO[] fees;

  /** 结算信息主键到VO的Map **/
  private Map<String, SettleBillItemVO> settleBIDMap;

  private String settleStockKey;

  /** 财务PK到结算信息的Map **/
  private MapList<String, SettleBillItemVO> stockSettleMap;

  /**
   * FeeBSDIVRule 的构造子
   * 
   * @param fdClazz
   * @param settleStockKey
   */
  public EstFeeBSDIVRule(Class<T> fdClazz, String settleStockKey) {
    this.fdClazz = fdClazz;
    this.settleStockKey = settleStockKey;
  }

  public T[] process(EstVO[] estVos, SettleBillItemVO[] settleItems) {
    // 准备费用分摊的数据
    this.prepareDatas(estVos, settleItems);
    // 进行拆分
    List<T> fdVos = new ArrayList<T>();
    for (FeeEstVO fee : this.fees) {
      String pk_stockps_b = fee.getPk_stockps_b();
      FeeMnyDivideVO[] fmdvos = this.bidFMDivVOMap.get(pk_stockps_b);
      if (ArrayUtils.isEmpty(fmdvos)) {
        continue;
      }
      // 清除分摊数据
      this.clearDivideMny(fmdvos);
      // 生成费用分摊VO
      FeeMnyVO fmvo = this.getFeeMnyVO(fee);
      String pk_group = BSContext.getInstance().getGroupID();
      FeeDivideRule rule = new FeeDivideRule(pk_group, this.factors, fmdvos);
      fmdvos = rule.divide(new FeeMnyVO[] {
        fmvo
      });
      this.getFeeEstDistVOs(fdVos, fee.getPk_stockps_fee(), fmdvos);
    }
    if (0 == fdVos.size()) {
      return null;
    }
    return new ListToArrayTool<T>().convertToArray(fdVos);
  }

  private void clearDivideMny(FeeMnyDivideVO[] fmdvos) {
    for (FeeMnyDivideVO fmdvo : fmdvos) {
      fmdvo.setDividedmny(UFDouble.ZERO_DBL);
    }
  }

  /** 根据货物暂估和费用暂估信息，生成分摊依据VO **/
  private FeeMnyDivideVO[] genFeeMnyDivVos(String pk_stockps_b) {
    List<FeeMnyDivideVO> divVOList = new ArrayList<FeeMnyDivideVO>();
    // 由暂估数据生成
    GoodsEstVO esth = this.estMap.get(pk_stockps_b);
    if (null == esth) {
      return null;
    }
    String pk_material = esth.getPk_material();
    if (0 != MathTool.compareTo(UFDouble.ZERO_DBL, esth.getNestnum())) {
      // UFDouble mny = esth.getNestmny();
      // wuxla v61
      UFDouble mny = esth.getNestcalcostmny();
      UFDouble num = esth.getNestnum();
      FeeMnyDivideVO divVo = new FeeMnyDivideVO(mny, num, pk_material);
      divVo.setBillpk(pk_stockps_b);
      divVOList.add(divVo);
    }
    // 由结算数据生成
    if (null == this.stockSettleMap
        || !this.stockSettleMap.containsKey(pk_stockps_b)) {
      return divVOList.toArray(new FeeMnyDivideVO[divVOList.size()]);
    }
    for (SettleBillItemVO sitem : this.stockSettleMap.get(pk_stockps_b)) {
      UFDouble mny = sitem.getNgoodsmoney();
      UFDouble num = sitem.getNsettlenum();
      // 红蓝对冲金额为0，取数量. 2011-11-17 孙聪、赵玉行、田锋涛一起确认
      if (MathTool.isZero(mny)) {
        mny = num;
      }
      FeeMnyDivideVO divVo = new FeeMnyDivideVO(mny, num, pk_material);
      divVo.setBillpk(sitem.getPk_settlebill_b());
      divVOList.add(divVo);
    }
    return divVOList.toArray(new FeeMnyDivideVO[divVOList.size()]);
  }

  /** 由费用分摊算法处理后的VO，生成费用分摊VO **/
  private void getFeeEstDistVOs(List<T> fdVos, String pk_fee,
      FeeMnyDivideVO[] fmdvos) {
    for (FeeMnyDivideVO fmdvo : fmdvos) {
      T fdVo = this.getOneFeeEstDistVO(pk_fee, fmdvo);
      if (null == fdVo) {
        continue;
      }
      fdVos.add(fdVo);
    }
  }

  private FeeMnyVO getFeeMnyVO(FeeEstVO fee) {
    String pk_curr = fee.getPk_localcurrency();
    int digit = CurrencyInfo.getDigit(pk_curr);
    // UFDouble mny = fee.getNestmny();
    // wuxla v61
    UFDouble mny = fee.getNcalcostmny();
    String moid = fee.getPk_srcfeematerial();
    return new FeeMnyVO(moid, mny, digit);
  }

  /** 根据货物暂估信息，生成分摊依据VO **/
  private Map<String, FeeMnyDivideVO[]> getFMDivVOMap(EstVO[] estVos) {
    Map<String, FeeMnyDivideVO[]> map = new HashMap<String, FeeMnyDivideVO[]>();
    for (EstVO estVo : estVos) {
      String pk_stockps_b = estVo.getParentVO().getPk_stockps_b();
      FeeMnyDivideVO[] fmvos = this.genFeeMnyDivVos(pk_stockps_b);
      map.put(pk_stockps_b, fmvos);
    }
    return map;
  }

  /** 由费用分摊算法处理后的VO，生成费用分摊VO **/
  private T getOneFeeEstDistVO(String pk_fee, FeeMnyDivideVO vo) {
    UFDouble divMny = vo.getDividedmny();
    if (0 == MathTool.compareTo(UFDouble.ZERO_DBL, divMny)) {
      return null;
    }
    T fdvo = Constructor.construct(this.fdClazz);
    String billpk = vo.getBillpk();
    String pk_bybill;
    String pk_byitem;
    String byBillType;
    // 从来源于暂估的分摊
    if (this.estMap.containsKey(billpk)) {
      GoodsEstVO estVo = this.estMap.get(billpk);
      pk_bybill = estVo.getPk_stockps();
      pk_byitem = estVo.getPk_stockps_b();
      byBillType = estVo.getBillType();
      // 来源于结算
    }
    else {
      SettleBillItemVO sitem = this.settleBIDMap.get(billpk);
      pk_bybill = sitem.getPk_settlebill();
      pk_byitem = sitem.getPk_settlebill_b();
      byBillType = POBillType.SettleBill.getCode();
    }
    fdvo.setNdistbymny(vo.getBillmny());
    fdvo.setNdistbynum(vo.getBillnum());
    fdvo.setPk_stockps_fee(pk_fee);
    fdvo.setPk_distbybill(pk_bybill);
    fdvo.setPk_distbybill_b(pk_byitem);
    fdvo.setPk_billtype(byBillType);
    fdvo.setNdistmny(divMny);
    return fdvo;
  }

  /** 得到货物暂估行到结算信息的Map **/
  private MapList<String, SettleBillItemVO> getStockSettleMap(String stockKey,
      SettleBillItemVO[] settleitems) {
    if (ArrayUtils.isEmpty(settleitems)) {
      return null;
    }
    MapList<String, SettleBillItemVO> map =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillItemVO sitem : settleitems) {
      map.put((String) sitem.getAttributeValue(stockKey), sitem);
    }
    return map;
  }

  /** 准备费用分摊的数据 **/
  private void prepareDatas(EstVO[] estVos, SettleBillItemVO[] settleItems) {
    // 费用VO
    this.fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(estVos);
    // 成本要素
    String pk_fiorg = this.fees[0].getPk_financeorg();
    Set<String> moids =
        CirVOUtil.getDistinctFieldSet(this.fees, FeeEstVO.PK_SRCFEEMATERIAL);
    this.factors =
        EstVOUtil.getCostFactor(pk_fiorg,
            moids.toArray(new String[moids.size()]));
    if (ArrayUtils.isEmpty(this.factors) || moids.size() != this.factors.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0062")/*
                                                                   * @res
                                                                   * "成本要素已被修改，请重新查询，再做暂估操作!"
                                                                   */);
    }
    // 暂估信息
    this.estMap = AggVOUtil.createHeadMap(estVos);
    // 结算信息
    this.stockSettleMap =
        this.getStockSettleMap(this.settleStockKey, settleItems);
    this.settleBIDMap =
        ArrayUtils.isEmpty(settleItems) ? null : CirVOUtil
            .createKeyVOMap(settleItems);
    // 每个货物暂估行的分摊VO
    this.bidFMDivVOMap = this.getFMDivVOMap(estVos);
  }

}
