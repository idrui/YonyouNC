package nc.pubimpl.pu.it.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ڵ�����Ʊ��̯
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-28 ����01:12:53
 */
public class MatchMergeFeeDiscountAdjustRule {
  private SettleEnvironment settleEnv = null;

  private StockSettleVO[] stockStlVos = null;

  /**
   * ����ƥ������ۿ۴���
   * 
   * @param settleEnv ���ν��㻷����Ϣ
   * @param stockStlVos ���ο�����VO����
   */
  public MatchMergeFeeDiscountAdjustRule(SettleEnvironment settleEnv,
      StockSettleVO[] stockStlVos) {
    this.settleEnv = settleEnv;
    this.stockStlVos = stockStlVos;
  }

  public void process(SettleBillItemVO[] items) {
    // ������㵥�еķ��ú��ۿ���Ϣ
    this.recordFeeDiscountAdjust(items);
  }

  private void reCalcFeeDiscountAdjust(SettleBillItemVO item, String pk_ccurr) {
    CostfactorViewVO[] cfviews = this.settleEnv.getCostFactorViews();
    UFDouble sumMny = SettlePublicUtil.getSumCostfactor(item, cfviews);
    sumMny =
        MathTool.add(sumMny,
            MathTool.add(item.getNdiscount(), item.getNadjustmny()));
    // ��Ϊ��δ���з��û��ۿ۷�̯���������
    if (MathTool.isZero(sumMny)) {
      return;
    }
    sumMny = MathTool.add(sumMny, item.getNgoodsmoney());
    // ���������
    item.setNmoney(sumMny);
    // ������㵥��
    UFDouble price =
        MathTool.isZero(item.getNsettlenum()) ? null : new ScaleUtils(
            item.getPk_group()).adjustSoPuPriceScale(
            sumMny.div(item.getNsettlenum(), UFDouble.DEFAULT_POWER), pk_ccurr);
    item.setNprice(MathTool.isZero(price) ? null : price);
  }

  private UFDouble recordAdjust(SettleBillItemVO item, UFDouble totalStlNum,
      UFDouble totalAdjust, UFDouble accAdjustDiv, String orgCurr,
      boolean isLast) {
    UFDouble divDist = null;
    if (isLast) {
      // ���һ�ε���
      divDist = MathTool.sub(totalAdjust, accAdjustDiv);
    }
    else {
      UFDouble stlNum = item.getNsettlenum();
      divDist =
          stlNum.div(totalStlNum, UFDouble.DEFAULT_POWER).multiply(totalAdjust);
      divDist =
          new ScaleUtils(item.getPk_group()).adjustMnyScale(divDist, orgCurr);
    }
    item.setNadjustmny(MathTool.isZero(divDist) ? null : divDist);
    return MathTool.add(accAdjustDiv, divDist);
  }

  private UFDouble recordDiscount(SettleBillItemVO item, UFDouble totalStlNum,
      UFDouble totalDiscount, UFDouble accDiscountDiv, String orgCurr,
      boolean isLast) {
    UFDouble divDist = null;
    if (isLast) {
      // ���һ�ε���
      divDist = MathTool.sub(totalDiscount, accDiscountDiv);
    }
    else {
      UFDouble stlNum = item.getNsettlenum();
      divDist =
          stlNum.div(totalStlNum, UFDouble.DEFAULT_POWER).multiply(
              totalDiscount);
      divDist =
          new ScaleUtils(item.getPk_group()).adjustMnyScale(divDist, orgCurr);
    }
    item.setNdiscount(MathTool.isZero(divDist) ? null : divDist);
    return MathTool.add(accDiscountDiv, divDist);
  }

  private void recordFee(SettleBillItemVO item, StockSettleVO ssVo,
      UFDouble[] accFeeDiv, UFDouble totalStlNum, String orgCurr, boolean isLast) {
    for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
      UFDouble totalCF =
          MathTool.nvl(ICostfactorDiscountUtil.getNcostfactor(ssVo, i));
      UFDouble cf = null;
      if (isLast) {
        cf = MathTool.sub(totalCF, accFeeDiv[i]);
      }
      else {
        UFDouble stlNum = item.getNsettlenum();
        cf = stlNum.div(totalStlNum, UFDouble.DEFAULT_POWER).multiply(totalCF);
        cf = new ScaleUtils(item.getPk_group()).adjustMnyScale(cf, orgCurr);
        accFeeDiv[i] = MathTool.add(accFeeDiv[i], cf);
      }
      ICostfactorDiscountUtil.setNcostfactor(item, i,
          MathTool.isZero(cf) ? null : cf);
    }
  }

  private void recordFeeDiscountAdjust(
      MapList<String, SettleBillItemVO> sbItemMapLst,
      Map<String, UFDouble> totalStlNumMap) {
    String orgCurr = this.settleEnv.getOrgCurr();
    if (ArrayUtils.isEmpty(this.stockStlVos)) {
      return;
    }
    for (StockSettleVO ssVo : this.stockStlVos) {
      UFDouble totalStlNum =
          MathTool.nvl(totalStlNumMap.get(ssVo.getPk_stockps_b()));
      UFDouble totalDiscount = MathTool.nvl(ssVo.getNdiscount());
      UFDouble accDiscountDiv = null;
      UFDouble totalAdjust = MathTool.nvl(ssVo.getNadjustmny());
      UFDouble accAdjustDiv = null;
      UFDouble[] accFeeDiv = new UFDouble[CostfactorVO.MAX_NUM];
      List<SettleBillItemVO> sbItemLst =
          sbItemMapLst.get(ssVo.getPk_stockps_b());
      if (CollectionUtils.isEmpty(sbItemLst)) {
        continue;
      }
      for (int i = 0; i < sbItemLst.size(); ++i) {
        SettleBillItemVO item = sbItemLst.get(i);
        if (MathTool.isZero(totalStlNum)) {
          // ���û�н�������,��Ϊ���ý���,ֻ�����ܼƽ������
          this.reCalcFeeDiscountAdjust(item, ssVo.getCcurrencyid());
          continue;
        }
        if (i == sbItemLst.size() - 1) {
          // ���һ�ε���
          this.recordDiscount(item, totalStlNum, totalDiscount, accDiscountDiv,
              orgCurr, true);
          this.recordFee(item, ssVo, accFeeDiv, totalStlNum, orgCurr, true);
          this.recordAdjust(item, totalStlNum, totalAdjust, accAdjustDiv,
              orgCurr, true);
        }
        else {
          accDiscountDiv =
              this.recordDiscount(item, totalStlNum, totalDiscount,
                  accDiscountDiv, orgCurr, false);
          this.recordFee(item, ssVo, accFeeDiv, totalStlNum, orgCurr, false);
          accAdjustDiv =
              this.recordAdjust(item, totalStlNum, totalAdjust, accAdjustDiv,
                  orgCurr, false);
        }
        // ���¼���
        this.reCalcFeeDiscountAdjust(item, ssVo.getCcurrencyid());
      }
    }
  }

  /**
   * ��¼���ú��ۿ۵���Ϣ,�����¼�����㵥��
   */
  private void recordFeeDiscountAdjust(SettleBillItemVO[] voaItem) {
    MapList<String, SettleBillItemVO> sbItemMapLst =
        new MapList<String, SettleBillItemVO>();
    Map<String, UFDouble> totalStlNumMap = new HashMap<String, UFDouble>();
    for (SettleBillItemVO item : voaItem) {
      String pk_stock_b = item.getPk_stock_b();
      if (StringUtils.isBlank(pk_stock_b)) {
        continue;
      }
      UFDouble stlNum = totalStlNumMap.get(pk_stock_b);
      // ��¼�ۼƽ�������
      totalStlNumMap
          .put(pk_stock_b, MathTool.add(stlNum, item.getNsettlenum()));
      sbItemMapLst.put(pk_stock_b, item);
    }
    // ��ǰ̨��̯���ĳɱ�Ҫ�غ��ۿ۽�һ����̯�����㵥����(��ͬһ��ⵥ�����γɶ�����㵥��)
    // ��̯��ʽ:����ÿһ�����㵥�н�������ռ��ⵥ���ν��������ı�����̯,���һ�е���
    this.recordFeeDiscountAdjust(sbItemMapLst, totalStlNumMap);
  }

}
