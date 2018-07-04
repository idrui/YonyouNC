package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用结算时后台工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-20 下午02:31:21
 */
public class FeeSettlePrivateUtil {

  /**
   * 方法功能描述：从费用结算单VO数组抽出入库单、费用发票对应的物料VID(查询费用结算明细、查询费用暂估明细时使用)
   * <p>
   * <b>参数说明</b>
   * 
   * @param feevos
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-20 下午02:28:00
   */
  public static FeeSettleQueryPara[] buildFeeSettleQueryPara(
      List<SettleBillVO> feevos) {
    if (feevos == null || feevos.size() == 0) {
      return null;
    }
    FeeSettleQueryPara[] paras = new FeeSettleQueryPara[feevos.size()];
    for (int len = feevos.size() - 1; len >= 0; len--) {
      paras[len] = new FeeSettleQueryPara();
      for (SettleBillItemVO item : feevos.get(len).getChildrenVO()) {
        if (EnumMatchRowType.Fee.value().equals(item.getFrowtype())) {
          // 物料VID
          paras[len].addFeemrlid(item.getPk_srcmaterial());
        }
        else {
          // 如果结算单表体存在费用结算之入库单行，则认为是费用结算
          paras[len].addStockbid(item.getPk_stock_b());
        }
      }
    }
    return paras;
  }

  /**
   * 方法功能描述：根据查询参数构造临时表，ID1=入库单行主键,ID2=费用类物料VID
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryParas
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-20 下午02:34:00
   */
  public static String buldTempTable(FeeSettleQueryPara[] queryParas) {
    if (ArrayUtils.isEmpty(queryParas)) {
      return null;
    }
    List<String> stockbids = new ArrayList<String>();
    List<String> feemrlids = new ArrayList<String>();
    for (FeeSettleQueryPara para : queryParas) {
      // 需要查询的费用发票对应物料ID
      List<String> feemrlidList = para.getFeemrlidList();
      // 入库单行主键
      List<String> stockbidList = para.getStockbidList();
      for (String stockbid : stockbidList) {
        for (String feemrlid : feemrlidList) {
          stockbids.add(stockbid);
          feemrlids.add(feemrlid);
        }
      }
    }
    // 创建临时表
    String[] id1 = stockbids.toArray(new String[0]);
    String[] id2 = feemrlids.toArray(new String[0]);
    String tempTname = new TempTableDefine().get(id1, id2);
    if (StringUtils.isEmpty(tempTname)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0067")/*
                                                                   * @res
                                                                   * "生成临时表失败"
                                                                   */);
    }
    return tempTname;
  }

  public static SettleBillItemVO[] findDiscountSettleItem(SettleBillVO vo) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      // 如果结算单表体存在劳务行，则认为是费用结算
      if (EnumMatchRowType.Discount.value().equals(item.getFrowtype())) {
        items.add(item);
      }
    }
    return items.toArray(new SettleBillItemVO[0]);
  }

  public static SettleBillItemVO[] findFeeSettleItem(SettleBillVO vo) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      // 如果结算单表体存在劳务行，则认为是费用结算
      if (EnumMatchRowType.Fee.value().equals(item.getFrowtype())) {
        items.add(item);
      }
    }
    return items.toArray(new SettleBillItemVO[0]);
  }

  /**
   * 方法功能描述：从结算单数组中查找费用结算的对应VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   *         费用结算的对应VO数组
   * @since 6.0
   * @author hanbin
   * @time 2010-8-20 下午02:17:45
   */
  public static List<SettleBillVO> findNeedFeeSettleVO(SettleBillVO[] vos) {
    List<SettleBillVO> feevos = new ArrayList<SettleBillVO>();
    if (ArrayUtils.isEmpty(vos)) {
      return feevos;
    }
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // 如果结算单表体存在劳务行，则认为是费用结算
        if (EnumMatchRowType.Fee.value().equals(item.getFrowtype())) {
          feevos.add(vo);
          break;
        }
        // 如果结算单表体存在折扣行，则认为是费用结算
        if (EnumMatchRowType.Discount.value().equals(item.getFrowtype())) {
          feevos.add(vo);
          break;
        }
      }
    }
    return feevos;
  }

  public static SettleBillItemVO[] findStockSettleItem(SettleBillVO vo) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      String stockType = item.getVstockbilltype();
      if (StringUtils.isNotEmpty(stockType)) {
        items.add(item);
      }
    }
    return items.toArray(new SettleBillItemVO[0]);
  }
}
