package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-16 下午06:20:13
 * @author wuxla
 */

public class CoopNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      List<Integer> rowList = new ArrayList<Integer>();
      int length = itemVOs.length;
      for (int i = 0; i < length; ++i) {
        if (MathTool
            .compareTo(itemVOs[i].getNnum(), itemVOs[i].getNsourcenum()) != 0) {
          rowList.add(Integer.valueOf(i));
        }
      }
      // 如果再计算会有误差，只有主数量不一致时才计算。
      if (rowList.size() > 0) {
        int[] rows =
            ArrayUtils
                .toPrimitive(rowList.toArray(new Integer[rowList.size()]));
        NumValueSetter setter = new NumValueSetter(new BillHelper<OrderVO>(vo));
        setter.setNastnum(rows);
      }
      RelationCalculate cal = new RelationCalculate();
      cal.calculate(vo, OrderItemVO.NORIGTAXMNY, true, true);
    }
  }

}
