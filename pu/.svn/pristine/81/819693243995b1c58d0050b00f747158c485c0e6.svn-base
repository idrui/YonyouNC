package nc.bs.pu.m4202;

import nc.bs.pu.m27.AbstractStockFIDuplicateBP;
import nc.bs.pu.m27.rule.StockFIDuplicateCheckRule;
import nc.bs.pu.m4202.rule.VMIFillRowAffectCostFlagRule;
import nc.bs.pu.m4202.rule.VMINormalPurFlagRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ic.m50.entity.VmiSumVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 生成消耗汇总的副本BP
 * 
 * @since 6.0
 * @version 2011-1-20 下午02:06:26
 * @author zhaoyha
 */
public class VMIFIDupBP extends AbstractStockFIDuplicateBP<VmiSumVO, VmiFIVO> {

  @Override
  protected void addRule(AroundProcesser<VmiFIVO> prcr) {
    prcr.addBeforeFinalRule(new VMINormalPurFlagRule());
    prcr.addBeforeFinalRule(new VMIFillRowAffectCostFlagRule());// 设置是否影响成本的标志
    prcr.addBeforeFinalRule(new StockFIDuplicateCheckRule<VmiFIVO>());// 数据检查，放在影响成本标志之后
  }

  @Override
  protected IBillType getFIBilltype() {
    return POBillType.VoiConsumeFinance;
  }

  @Override
  protected IBillType getStockBilltype() {
    return ICBillType.VmiSum;
  }

}
