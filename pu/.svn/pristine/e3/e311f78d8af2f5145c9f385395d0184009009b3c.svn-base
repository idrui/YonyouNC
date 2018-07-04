package nc.bs.pu.m27.feesettle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.feesettle.util.M45FeeSettleToPCIAUtil;
import nc.bs.pu.m27.feesettle.util.M4AFeeSettleToPCIAUtil;
import nc.bs.pu.m27.feesettle.util.M4TFeeSettleToPCIAUtil;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            费用结算时，传利润中心存货
 * @scene
 *      费用结算
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-10-26 上午12:03:15
 * @author mengjian
 */
public class FeeSettleToPCIABP implements IRule<SettleBillVO> {

  private boolean bneedQuery = false;

  private FeeSettleDataContext datactx;

  public FeeSettleToPCIABP() {
    this.bneedQuery = true;
    this.datactx = new FeeSettleDataContext();
  }

  public FeeSettleToPCIABP(FeeSettleDataContext datactx) {
    this.datactx = datactx;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // 费用结算相关的结算单
    List<SettleBillVO> myvos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (myvos == null || myvos.size() == 0) {
      return;
    }
    SettleBillVO[] feevos = myvos.toArray(new SettleBillVO[myvos.size()]);

    if (this.bneedQuery) {
      // 准备结算费用分摊明细、费用暂估数据
      new PrepareFeeSettleDataRule(false, true, this.datactx).process(feevos);
      // 查询结算的库存信息数据
      if (null == this.datactx.getStockSettleVOMap()) {
        StockSettleVO[] stocks = this.queryStockStlVO(vos);
        this.datactx.setStockSettleVOMap(CirVOUtil.createKeyVOMap(stocks));
      }
    }
    if (this.datactx == null) {
      return;
    }
    for (SettleBillVO vo : feevos) {
      // 采购入、委外入、其他入、期初暂估入对应的结算单行
      // 使用MAP，去除入库单重复的行（一行入库单与多行发票结算）
      Map<String, SettleBillItemVO> m45ItemList =
          new HashMap<String, SettleBillItemVO>();
      Map<String, SettleBillItemVO> m4AItemList =
          new HashMap<String, SettleBillItemVO>();
      Map<String, SettleBillItemVO> m4TItemList =
          new HashMap<String, SettleBillItemVO>();
      for (SettleBillItemVO voItem : vo.getChildrenVO()) {
        if (!SettleBillItemVOUtil.isSettleToPCIA(voItem, null)) {
          continue;
        }
        String stockType = voItem.getVstockbilltype();

        if (ICBillType.PurchaseIn.getCode().equals(stockType)) {
          m45ItemList.put(voItem.getPk_stock_b(), voItem);
        }
        else if (ICBillType.GeneralIn.getCode().equals(stockType)) {
          m4AItemList.put(voItem.getPk_stock_b(), voItem);
        }
        else if (POBillType.InitEstimate.getCode().equals(stockType)) {
          m4TItemList.put(voItem.getPk_stock_b(), voItem);
        }
      }
      // 采购入、其他入、期初暂估入，分别传存货核算
      SettleBillHeaderVO head = vo.getParentVO();
      new M45FeeSettleToPCIAUtil(head, m45ItemList.values())
          .toPCIA(this.datactx);
      new M4AFeeSettleToPCIAUtil(head, m4AItemList.values())
          .toPCIA(this.datactx);
      new M4TFeeSettleToPCIAUtil(head, m4TItemList.values())
          .toPCIA(this.datactx);
    }

  }

  private StockSettleVO[] queryStockStlVO(SettleBillVO[] vos) {
    StockInfoUtil siUtil = new StockInfoUtil(vos);
    List<StockSettleVO> ssVoLst = new ArrayList<StockSettleVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isBlank(item.getPk_stock_b())) {
          continue;
        }
        ssVoLst.add(siUtil.getStockSettleVO(item.getPk_stock_b()));
      }
    }
    if (ssVoLst.size() == 0) {
      return new StockSettleVO[0];
    }
    StockSettleVO[] stlVOs = new StockSettleVO[ssVoLst.size()];
    for (int i = 0; i < ssVoLst.size(); i++) {
      stlVOs[i] = ssVoLst.get(i);
    }
    return stlVOs;
  }
}
