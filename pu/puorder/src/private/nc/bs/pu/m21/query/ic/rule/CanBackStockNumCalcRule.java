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
 *              ������˿�����
 * @scene
 *        �˿ⵥת����ѯ
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:35:38
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
    // �Ƿ��˻�
    boolean breturn = vo.getHVO().getBreturn().booleanValue();
    UFBoolean brefwhenreturn = vo.getHVO().getBrefwhenreturn();
    for (OrderItemVO item : vo.getBVO()) {
      // ����������������+�˻�����+�˿�����
      if (breturn) {
        UFDouble totalnum = item.getNnum();
        UFDouble storenum = item.getNbackstorenum();
        UFDouble arrvnum = item.getNbackarrvnum();
        UFDouble num = MathTool.add(totalnum, storenum);
        num = MathTool.add(num, arrvnum);
        item.setNcaninnum(num);
      }
      // ��������(�ۼ��������-�ۼ��˿�����)*(-1)
      else if (UFBoolean.FALSE.equals(brefwhenreturn)) {
        UFDouble totalnum = item.getNaccumstorenum();
        UFDouble backnum = item.getNbackstorenum();
        UFDouble num = MathTool.sub(totalnum, backnum);
        num = MathTool.oppose(num);
        item.setNcaninnum(num);
      }
      else {
        // ��������(�ۼ��������)*(-1)
        UFDouble nnum = MathTool.oppose(item.getNaccumstorenum());
        item.setNcaninnum(nnum);
      }
    }
  }

}
