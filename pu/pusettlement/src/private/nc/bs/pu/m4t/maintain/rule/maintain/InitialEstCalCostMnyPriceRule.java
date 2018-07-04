package nc.bs.pu.m4t.maintain.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * @description
 *              计算计成本单价：
 *              如果本币无税金额=计成本金额，取主本币无税单价
 *              如果本币无税金额！=计成本金额，计成本金额/主数量
 * @scene
 *        期初暂估单保存
 * @param 无
 * @since 6.3
 * @version 2014-10-31 上午10:09:55
 * @author wuxla
 */

public class InitialEstCalCostMnyPriceRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    ScaleUtils scu = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (InitialEstVO vo : vos) {
      for (InitialEstItemVO itemVO : vo.getItems()) {
        if (itemVO.getStatus() == VOStatus.UNCHANGED
            || itemVO.getStatus() == VOStatus.DELETED) {
          continue;
        }
        UFDouble ncalcostmny = itemVO.getNcalcostmny();
        UFDouble nmny = itemVO.getNmny();
        if (MathTool.compareTo(ncalcostmny, nmny) == 0) {
          itemVO.setNestcalcostprice(itemVO.getNprice());
        }
        else {
          UFDouble nnum = itemVO.getNnum();
          itemVO.setNestcalcostprice(scu.adjustSoPuPriceScale(
              ncalcostmny.div(nnum), vo.getHeader().getCorigcurrencyid()));
        }
      }
    }
  }

}
