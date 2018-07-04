package nc.bs.pu.m27.settlebill.rule;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * 结算时回写库存结算财务副本结算信息-顶层类
 * 
 * @since 6.0
 * @version 2011-1-22 下午07:17:12
 * @author zhyhang
 */
public abstract class AbstractSettleUpdateRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> maplist = this.getSettleMap(vos);
    if (maplist.size() == 0) {// 费用结算有可能为空
      return;
    }
    Set<String> bidSet = maplist.keySet();
    this.updateSettleInfo(bidSet.toArray(new String[bidSet.size()]), maplist);
  }

  /**
   * 返回结算单上的库存单据BID字段名称
   * 
   * @return
   */
  protected abstract String getStockBIDField();

  /**
   * 累计所有成本要素(包括进成本和不进成本的)和折扣的金额
   * 
   * @param settleitem
   * @return
   */
  protected UFDouble getTotalFeemny(SettleBillItemVO settleitem) {
    UFDouble fee = settleitem.getNdiscount();
    for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
      fee =
          MathTool.add(fee, ICostfactorDiscountUtil.getNcostfactor(settleitem,
              i));
    }
    return fee;
  }

  /**
   * 更新库存财务单据结算信息
   * 
   * @param stockBIDs 库存财务单据BID数组
   * @param sbidStlMap 库存财务BID到结算明细列表的MAP
   */
  protected abstract void updateSettleInfo(String[] stockBIDs,
      MapList<String, SettleBillItemVO> sbidStlMap);

  private MapList<String, SettleBillItemVO> getSettleMap(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> maplist =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        String bid = (String) item.getAttributeValue(this.getStockBIDField());
        // 待回写的表体,货物结算、货物费用同时结算、纯费用结算
        if (StringUtils.isNotBlank(bid)) {
          maplist.put(bid, item);
        }
      }
    }
    return maplist;
  }

}
