package nc.vo.pu.m27.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 检查入库单已经进行过某项费用结算，并且正要与此项已经传应付的费用发票结算
 *
 * @since 6.0
 * @version 2011-5-5 下午03:13:50
 * @author zhaoyha
 */
public class FeeEstApCheckRule {
  private FeeDiscountSettleVO[] feeVos;

  private StockSettleVO[] ssVos;

  /**
   *
   * @param ssVos 入库单结算VO
   * @param feeVos 费用发票VO
   */
  public FeeEstApCheckRule(StockSettleVO[] ssVos, FeeDiscountSettleVO[] feeVos) {
    super();
    this.ssVos = ssVos;
    this.feeVos = feeVos;
  }

  public void check() {

    if (ArrayUtils.isEmpty(this.feeVos)) {
      return;
    }
    MapList<String, String> stockFeeEstApMap = null;
    try {
      stockFeeEstApMap =
          NCLocator.getInstance().lookup(ISettleMatch.class)
              .getEstAPFeeMaterial(this.ssVos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == stockFeeEstApMap || stockFeeEstApMap.size() == 0) {
      return;
    }
    List<String> estStockLst = new ArrayList<String>();
    for (Entry<String, List<String>> entry : stockFeeEstApMap.entrySet()) {
      Set<String> feeMarSet = new HashSet<String>(entry.getValue());
      for (FeeDiscountSettleVO fee : this.feeVos) {
        if (UFBoolean.TRUE.equals(fee.getBapflag())
            && feeMarSet.contains(fee.getPk_material())) {
          estStockLst.add(entry.getKey());
          break;
        }
      }
    }
    this.throwEstApExcep(estStockLst);

  }

  private void throwEstApExcep(List<String> estStockLst) {
    if (estStockLst.size() == 0) {
      return;
    }
    Set<String> billcodeSet = new HashSet<String>();
    Map<String, StockSettleVO> ssVoMap = CirVOUtil.createKeyVOMap(this.ssVos);
    StringBuilder sb = new StringBuilder();
    sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0156")/*@res "以下入库单存在费用暂估应付，不允许与已传应付的费用发票进行结算："*/);
    for (String pk_stockps_b : estStockLst) {
      String billcode = ssVoMap.get(pk_stockps_b).getVbillcode();
      if (billcodeSet.contains(billcode)) {
        continue;
      }
      billcodeSet.add(billcode);
      sb.append("[").append(billcode).append("]");
    }
    ExceptionUtils.wrappBusinessException(sb.toString());
  }

}