package nc.bs.pu.est.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * @description
 *              暂估设置记成本单价
 * @scene
 *        暂估的BP操作
 * @param 无
 * @since 6.3
 * @version 2014-10-23 上午9:43:55
 * @author zhangshqb
 */
public class EstCalCostMnyPriceRule<E extends AggregatedValueObject> implements
    IRule<E> {

  @Override
  public void process(E[] vos) {
    ScaleUtils scu = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (E vo : vos) {
      CircularlyAccessibleValueObject headvo = vo.getParentVO();
      UFDouble nestcalcostmny =
          (UFDouble) headvo.getAttributeValue(GoodsEstVO.NESTCALCOSTMNY);
      UFDouble nestmny =
          (UFDouble) headvo.getAttributeValue(GoodsEstVO.NESTMNY);
      if (MathTool.compareTo(nestcalcostmny, nestmny) == 0) {
        headvo.setAttributeValue(GoodsEstVO.NESTCALCOSTPRICE,
            headvo.getAttributeValue(GoodsEstVO.NESTPRICE));
      }
      else {
        UFDouble nestnum =
            (UFDouble) headvo.getAttributeValue(GoodsEstVO.NESTNUM);
        headvo
            .setAttributeValue(GoodsEstVO.NESTCALCOSTPRICE, scu
                .adjustSoPuPriceScale(nestcalcostmny.div(nestnum), String
                    .valueOf(headvo
                        .getAttributeValue(GoodsEstVO.CORIGCURRENCYID))));
      }
    }
  }
}
