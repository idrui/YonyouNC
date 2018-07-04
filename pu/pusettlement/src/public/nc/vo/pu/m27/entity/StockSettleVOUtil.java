package nc.vo.pu.m27.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>库存结算VO工具类</b>
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
 * @time 2010-1-28 上午09:47:25
 */
public class StockSettleVOUtil {
  /**
   * 查询结算入库单后，计算未结算数量，本币未结算金额（可结算金额或叫暂估未冲销金额）
   * 
   * @param stockSettVOs
   */
  public static void calcStockCanSettle(StockSettleVO[] stockSettVOs) {
    if (ArrayUtils.isEmpty(stockSettVOs)) {
      return;
    }
    ScaleUtils utils = new ScaleUtils(stockSettVOs[0].getPk_group());
    for (int i = 0; i < stockSettVOs.length; i++) {
      // 未结算数量 = 入库数量 - 累计计算数量
      stockSettVOs[i].setNcansettlenum(MathTool.sub(
          stockSettVOs[i].getNinnum(), stockSettVOs[i].getNaccumsettlenum()));
      // 未结算金额(可结算金额或叫暂估/确认未冲销金额) = 暂估/确认金额 - 累计结算金额
      // stockSettVOs[i]
      // .setNcansettlemny(MathTool.sub(stockSettVOs[i].getNestmny(),
      // stockSettVOs[i].getNaccestcostwashmny()));
      // wuxla V61改为记成本金额
      stockSettVOs[i].setNcansettlemny(MathTool.sub(
          stockSettVOs[i].getNestcalcostmny(),
          stockSettVOs[i].getNaccestcostwashmny()));
      if (EnumToIAFlag.ConfirmToIA.toInteger().equals(
          stockSettVOs[i].getFdirtoiatype())) {
        // wuxla v61 记成本金额
        stockSettVOs[i].setNcansettlemny(MathTool.sub(
            stockSettVOs[i].getNcalcostmny(),
            stockSettVOs[i].getNacctocostadjstmny()));
        // stockSettVOs[i]
        // .setNcansettlemny(MathTool.sub(stockSettVOs[i].getNmny(),
        // stockSettVOs[i].getNacctocostadjstmny()));
      }
      // 本次结算数量 = 未结算数量
      stockSettVOs[i].setNcurrentsettlenum(stockSettVOs[i].getNcansettlenum());
      // 结算平均价
      UFDouble price =
          MathTool.isZero(stockSettVOs[i].getNaccgoodssettlemny())
              || MathTool.isZero(stockSettVOs[i].getNaccumsettlenum()) ? null
              : stockSettVOs[i].getNaccgoodssettlemny().div(
                  stockSettVOs[i].getNaccumsettlenum());

      stockSettVOs[i].setNavgsettleprice(utils.adjustSoPuPriceScale(price,
          stockSettVOs[i].getCorigcurrencyid()));
    }
  }

  /**
   * 计算本次结算还剩下多少未结算，会填充发票的Nresidualsettlenum
   * <p>
   * <b>参数说明</b>
   * 
   * @param listStock <p>
   * @author wangyf
   * @time 2010-3-23 下午02:24:49
   */
  public static void calResidualSettleNum(ArrayList<StockSettleVO> listStock) {

    if (ListUtil.isEmpty(listStock)) {
      return;
    }

    for (StockSettleVO settleVO : listStock) {
      settleVO
          .setNresidualsettlenum(MathTool.sub(settleVO.getNcurrentsettlenum(),
              settleVO.getNcurrentaccumsettlenum()));
    }
  }

  /**
   * 手工结算，从界面的VO转至库存结算VO
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaMatch
   * @return <p>
   * @author wangyf
   * @time 2010-1-28 上午09:46:14
   */
  public static StockSettleVO[] convertFromMatchMaterialVO(
      MatchMaterialVO[] voaMatch, EnumSettleType stype) {

    if (voaMatch == null || voaMatch.length == 0) {
      return null;
    }

    ArrayList<StockSettleVO> listVO = new ArrayList<StockSettleVO>();
    int iLen = voaMatch.length;
    for (int i = 0; i < iLen; i++) {
      if (ValueUtils.getBoolean(voaMatch[i].getBstock())) {
        // 得到原始的VO
        StockSettleVO voDest =
            (StockSettleVO) voaMatch[i].getStockSettleVO().clone();

        // 加入从界面得到的数据
        // 本次入库结算数量
        voDest.setNcurrentsettlenum(voaMatch[i].getNcurstocksettlenum());

        // 本次发票结算金额（异物料结算、无发票结算用）
        if (EnumSettleType.WITHOUT_INVOICE == stype) {
          // 无发票结算时记录本次结算金额
          voDest.setNcurrentinvoicesettlemny(voaMatch[i].getNcurseetlemny());
        }
        else {
          voDest.setNcurrentinvoicesettlemny(voaMatch[i]
              .getNcurinvoicesettlemny());
        }

        // 记录界面的分摊结果
        voDest.setNdiscount(voaMatch[i].getNdiscount());
        voDest.setNcostfactor1(voaMatch[i].getNcostfactor1());
        voDest.setNcostfactor2(voaMatch[i].getNcostfactor2());
        voDest.setNcostfactor3(voaMatch[i].getNcostfactor3());
        voDest.setNcostfactor4(voaMatch[i].getNcostfactor4());
        voDest.setNcostfactor5(voaMatch[i].getNcostfactor5());
        voDest.setNcostfactor6(voaMatch[i].getNcostfactor6());
        voDest.setNcostfactor7(voaMatch[i].getNcostfactor7());
        voDest.setNcostfactor8(voaMatch[i].getNcostfactor8());
        // add by liangchen1 调整货物
        voDest.setNadjustmny(voaMatch[i].getNadjustmny());

        // 结算单价
        if (voaMatch[i].getNprice() != null) {
          voDest.setNavgsettleprice(voaMatch[i].getNprice());
        }
        listVO.add(voDest);
      }
    }

    if (listVO.size() == 0) {
      return null;
    }

    return listVO.toArray(new StockSettleVO[listVO.size()]);

  }

  /**
   * 当前结算数量是否已完成
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voStock
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 上午10:29:19
   */
  public static boolean isCurrentSettleFinished(StockSettleVO voStock) {

    return MathTool.compareTo(voStock.getNcurrentsettlenum(),
        voStock.getNcurrentaccumsettlenum()) == 0;
  }

  /**
   * 按单据号和bid排序结算vo
   * 
   * @param vos
   */
  public static void sortByCodeAndBid(StockSettleVO[] vos) {
    if (null == vos) {
      return;
    }
    Arrays.sort(vos, new Comparator<StockSettleVO>() {
      @Override
      public int compare(StockSettleVO o1, StockSettleVO o2) {
        int codeCompare = o1.getVbillcode().compareTo(o2.getVbillcode());
        if (codeCompare == 0) {
          return o1.getPk_stockps_b().compareTo(o2.getPk_stockps_b());
        }
        return codeCompare;
      }
    });
  }

  public static void synchStockOfMatchMaterial(MatchMaterialVO[] uiMatchVos,
      MatchMaterialVO[] modelMatchVos, EnumSettleType stype) {
    if (ArrayUtils.isEmpty(uiMatchVos) || ArrayUtils.isEmpty(modelMatchVos)) {
      return;
    }

    Map<String, MatchMaterialVO> matchVoMap =
        new HashMap<String, MatchMaterialVO>();
    for (MatchMaterialVO modelMatchVo : modelMatchVos) {
      if (ValueUtils.getBoolean(modelMatchVo.getBstock())) {
        matchVoMap.put(modelMatchVo.getPk_billbid(), modelMatchVo);
      }
    }

    for (MatchMaterialVO uiMatchVo : uiMatchVos) {
      if (!ValueUtils.getBoolean(uiMatchVo.getBstock())) {
        continue;
      }
      // 得到模型中的VO
      MatchMaterialVO modelMatchVo = matchVoMap.get(uiMatchVo.getPk_billbid());
      StockSettleVO stock = modelMatchVo.getStockSettleVO();

      // 加入从界面得到的数据
      // 本次入库结算数量
      stock.setNcurrentsettlenum(uiMatchVo.getNcurstocksettlenum());

      // 本次发票结算金额（异物料结算、无发票结算用）
      if (EnumSettleType.WITHOUT_INVOICE == stype) {
        // 无发票结算时记录本次结算金额
        stock.setNcurrentinvoicesettlemny(uiMatchVo.getNcurseetlemny());
      }
      else {
        stock.setNcurrentinvoicesettlemny(uiMatchVo.getNcurinvoicesettlemny());
      }

      // 记录界面的分摊结果
      stock.setNdiscount(uiMatchVo.getNdiscount());
      stock.setNcostfactor1(uiMatchVo.getNcostfactor1());
      stock.setNcostfactor2(uiMatchVo.getNcostfactor2());
      stock.setNcostfactor3(uiMatchVo.getNcostfactor3());
      stock.setNcostfactor4(uiMatchVo.getNcostfactor4());
      stock.setNcostfactor5(uiMatchVo.getNcostfactor5());
      stock.setNcostfactor6(uiMatchVo.getNcostfactor6());
      stock.setNcostfactor7(uiMatchVo.getNcostfactor7());
      stock.setNcostfactor8(uiMatchVo.getNcostfactor8());
      // add by liangchen1 调整货物
      stock.setNadjustmny(uiMatchVo.getNadjustmny());

      // 结算单价
      if (uiMatchVo.getNprice() != null) {
        stock.setNavgsettleprice(uiMatchVo.getNprice());
      }
    }
  }

}
