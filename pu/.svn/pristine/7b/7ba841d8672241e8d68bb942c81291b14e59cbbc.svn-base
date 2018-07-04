package nc.vo.pu.m20.rule;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.calculate.PuSimpleCalCondition;
import nc.vo.pu.pub.calculate.PuSimpleCalculateDataSet;
import nc.vo.pu.pub.calculate.PuSimpleCalculator;
import nc.vo.pu.pub.calculate.PuSimpleRelationItems;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * @since 6.0
 * @version 2011-6-30 上午10:11:03
 * @author wuxla
 */

public class RelationCalculate {

  public void calculate(PraybillVO praybillVO, String itemKey) {
    //
    ScaleUtils scale = new ScaleUtils(praybillVO.getHVO().getPk_group());
    BillHelper<PraybillVO> bill = new BillHelper<PraybillVO>(praybillVO);

    for (int row = 0; row < praybillVO.getBVO().length; row++) {
      PuSimpleCalculator calculator =
          new PuSimpleCalculator(new PuSimpleCalculateDataSet(bill, row,
              new PuSimpleRelationItems()), scale);
      PuSimpleCalCondition condition = new PuSimpleCalCondition();
      // vo计算用固定换算率
      condition.setIsfixedChangeRate(true);
      calculator.calculate(condition, itemKey);
    }
  }

}
