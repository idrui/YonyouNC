package nc.bs.pu.m21.query.ic.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              计算可退库数量
 * @scene
 *        退库单转单查询
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:35:38
 * @author luojw
 */
public class CanBackStockNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (OrderVO vo : vos) {
      this.calculate(vo);
    }
  }

  private void calculate(OrderVO vo) {
    // 是否退货
    boolean breturn = vo.getHVO().getBreturn().booleanValue();
    UFBoolean brefwhenreturn = vo.getHVO().getBrefwhenreturn();
    for (OrderItemVO item : vo.getBVO()) {
      // 负订单：订单数量+退货数量+退库数量
      if (breturn) {
        UFDouble totalnum = item.getNnum();
        UFDouble storenum = item.getNbackstorenum();
        UFDouble arrvnum = item.getNbackarrvnum();
        UFDouble num = MathTool.add(totalnum, storenum);
        num = MathTool.add(num, arrvnum);
        item.setNcaninnum(num);
      }
      // 正订单：(累计入库数量-累计退库数量)*(-1)
      else if (UFBoolean.FALSE.equals(brefwhenreturn)) {
        UFDouble totalnum = item.getNaccumstorenum();
        UFDouble backnum = item.getNbackstorenum();
        UFDouble num = MathTool.sub(totalnum, backnum);
        num = MathTool.oppose(num);
        item.setNcaninnum(num);
      }
      else {
        // 正订单：(累计入库数量)*(-1)
        UFDouble nnum = MathTool.oppose(item.getNaccumstorenum());
        item.setNcaninnum(nnum);
      }
    }
  }

}
