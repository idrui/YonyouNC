package nc.bs.pu.m4201.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * 入库单记成本单价
 * 在入库单签字推副本时写入
 * 
 * @since 6.0
 * @version 2012-3-13 下午06:28:17
 * @author wuxla
 */
public class CalCostPriceRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    ScaleUtils su = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (PurchaseinFIVO vo : vos) {
      for (PurchaseinFIItemVO childvo : vo.getChildrenVO()) {
        UFDouble ncalcostmny = MathTool.nvl(childvo.getNcalcostmny());
        UFDouble nmny = childvo.getNmny();
        if (MathTool.compareTo(ncalcostmny, nmny) == 0) {
          childvo.setNcalcostprice(childvo.getNprice());
        }
        else {
          UFDouble ncalcostprice =
              su.adjustSoPuPriceScale(ncalcostmny.div(childvo.getNinnum()),
                  childvo.getCorigcurrencyid());
          childvo.setNcalcostprice(ncalcostprice);
        }
        childvo.setStatus(VOStatus.UPDATED);
      }
    }
  }

}
