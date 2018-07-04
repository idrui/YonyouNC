package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;

/**
 * 
 * @description
 * ������ⵥ�Գ�ʱ�Ļس��ݹ�Ӧ��
 * @scene
 * ������ϱ�����㵥
 * @param
 * util ������ⵥ�Գ�ʱ�Ļس��ݹ�Ӧ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:19:03
 * @author zhangshqb
 */
public class BackWashEstAPForRBStockRush implements IRule<SettleBillVO> {
  private StockInfoUtil stockInfo;

  public BackWashEstAPForRBStockRush(StockInfoUtil util) {
    this.stockInfo = util;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    for (SettleBillVO vo : vos) {
      this.backWashEstAP(vo);
    }
  }

  private void backWashEstAP(List<SettleBillItemVO> rushStockItems) {
    AdjuestVO[] adjVos = this.createAdjuestVO(rushStockItems);
    if (adjVos.length == 0) {
      return;
    }
    ArapServicesForPUUtil.washEstPayable(adjVos);
  }

  private void backWashEstAP(SettleBillVO vo) {
    SettleBillItemVO[] items = vo.getChildrenVO();
    List<SettleBillItemVO> rushStockItems = new ArrayList<SettleBillItemVO>();
    for (SettleBillItemVO item : items) {
      if (EnumMatchRowType.StockRush.toInteger().equals(item.getFrowtype())) {
        rushStockItems.add(item);
      }
    }
    if (rushStockItems.size() == 0) {
      return;
    }

    // �����ݹ�Ӧ���س�
    this.backWashEstAP(rushStockItems);
  }

  private AdjuestVO[] createAdjuestVO(List<SettleBillItemVO> rushStockItems) {
    List<AdjuestVO> vos = new ArrayList<AdjuestVO>();
    for (SettleBillItemVO item : rushStockItems) {
      String pk_stock_b = item.getPk_stock_b();
      StockSettleVO settleVo = this.stockInfo.getStockSettleVO(pk_stock_b);
      if (!EnumToAPFlag.EstimateToAP.toInteger().equals(
          settleVo.getFdirtoaptype())) {
        continue;
      }

      AdjuestVO adjVo = new AdjuestVO();
      adjVo.setTop_itemid(pk_stock_b);
      adjVo.setEst_top_itemid(pk_stock_b);
      adjVo.setQuantity(item.getNsettlenum());

      adjVo.setClbh(item.getPk_settlebill_b());
      vos.add(adjVo);
    }
    return vos.toArray(new AdjuestVO[vos.size()]);
  }

}
