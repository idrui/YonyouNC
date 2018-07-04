package nc.bs.pu.m4203;

import nc.bs.pu.m27.AbstractStockFIDuplicateBP;
import nc.bs.pu.m27.rule.ContranctInfoRedundanceRule;
import nc.bs.pu.m27.rule.FillRowAffectCostFlagRule;
import nc.bs.pu.m27.rule.StockFIDuplicateCheckRule;
import nc.bs.pu.m4203.rule.EstToIAFlagSetRule;
import nc.bs.pu.m4203.rule.SubcontFillDtocostapdate;
import nc.bs.pu.m4203.rule.SubcontFillInfoRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 委托加工入结算财务副本生成BP
 * 
 * @since 6.0
 * @version 2011-1-20 下午01:59:38
 * @author zhaoyha
 */
public class SubcontinFIDupBP extends
    AbstractStockFIDuplicateBP<SubcontInVO, SubcontinFIVO> {

  @Override
  protected void addRule(AroundProcesser<SubcontinFIVO> prcr) {

    prcr.addBeforeRule(new SubcontFillInfoRule());
    prcr.addBeforeRule(new SubcontFillDtocostapdate());// 设置暂估日期，库存签字生成该副本，签字时间就是暂估日期
    prcr.addBeforeFinalRule(new FillRowAffectCostFlagRule<SubcontinFIVO>());// 设置是否影响成本的标志
    prcr.addBeforeFinalRule(new EstToIAFlagSetRule());// 设置传成本标志
    prcr.addBeforeFinalRule(new StockFIDuplicateCheckRule<SubcontinFIVO>());// 数据检查，放在影响成本标志之后
    prcr.addBeforeRule(new ContranctInfoRedundanceRule<SubcontinFIVO>(
        SubcontinFIItemVO.VORDERTRANTYPECODE, SCBillType.Order));
  }

  @Override
  protected IBillType getFIBilltype() {
    return POBillType.SubContractFinance;
  }

  @Override
  protected IBillType getStockBilltype() {
    return ICBillType.SubContinIn;
  }

}
