package nc.bs.pu.m27.feesettle.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.M4APUServices;
import nc.itf.pu.reference.ic.M4AWriteParaForFeeSettle;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 
 * @description
 * 回写入库单的累计费用结算次数，涉及到的单据：
 * 其他入库单
 * @scene
 * 取消费用结算,费用结算
 * @param
 * bdosettle 是否费用结算，TRUE:费用结算,FALSE:取消费用结算
 *
 * @since 6.3
 * @version 2014-10-22 下午4:01:33
 * @author zhangshqb
 */
public class WriteStockSettleNumRule implements IRule<SettleBillVO> {

  // TRUE:费用结算,FALSE:取消费用结算
  private boolean bdosettle;

  public WriteStockSettleNumRule(boolean bdosettle) {
    this.bdosettle = bdosettle;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // 其他入
    List<SettleBillItemVO> m4AItemList = new ArrayList<SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO voItem : vo.getChildrenVO()) {
        String stockType = voItem.getVstockbilltype();
        if (ICBillType.GeneralIn.getCode().equals(stockType)) {
          m4AItemList.add(voItem);
        }
      }
    }
    this.writebackM4A(m4AItemList);// 其他入
  }

  private void writebackM4A(List<SettleBillItemVO> m4aItemList) {
    if (m4aItemList == null || m4aItemList.size() == 0) {
      return;
    }
    // 构造参数
    M4AWriteParaForFeeSettle[] paras =
        new M4AWriteParaForFeeSettle[m4aItemList.size()];
    for (int i = 0, len = m4aItemList.size(); i < len; i++) {
      paras[i] = new M4AWriteParaForFeeSettle();
      paras[i].setHid(m4aItemList.get(i).getPk_stock());
      paras[i].setBid(m4aItemList.get(i).getPk_stock_b());
      if (this.bdosettle) {
        paras[i].setNfeesettlecount(Integer.valueOf(1));
      }
      else {
        paras[i].setNfeesettlecount(Integer.valueOf(-1));
      }
    }
    M4APUServices.rewriteNaccumfeesettlecount(paras);
  }
}
