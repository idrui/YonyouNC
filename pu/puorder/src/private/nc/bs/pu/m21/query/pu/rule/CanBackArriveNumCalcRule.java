package nc.bs.pu.m21.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              <ul>
 *              <li>���˻�����
 *              <li>����������������(����)+�ۼ��˻�������������+�ۼ��˿�������������
 *              <li>��������(�ۼƵ�������-�ۼ��������-�˻�����)*(-1)
 *              </ul>
 * @scene
 *        �˻���ת����ѯ
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:34:11
 * @author luojw
 */
public class CanBackArriveNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (OrderVO vo : vos) {
      this.caculate(vo);
    }

  }

  private void caculate(OrderVO vo) {
    UFBoolean brefwhenreturn = vo.getHVO().getBrefwhenreturn();
    for (OrderItemVO item : vo.getBVO()) {
      if (MathTool.compareTo(item.getNnum(), UFDouble.ZERO_DBL) < 0) {
        // ���������˻�����������(����)+�ۼ��˻�������������+�ۼ��˿�������������
        UFDouble totalnum = item.getNnum();
        UFDouble backarrvnum = item.getNbackarrvnum();
        UFDouble backstorenum = item.getNbackstorenum();
        UFDouble num = MathTool.add(totalnum, backarrvnum);
        num = MathTool.add(num, backstorenum);
        item.setNcanarrivenum(num);
      }
      else if (UFBoolean.FALSE.equals(brefwhenreturn)) {
        // ��������(�ۼƵ�������-�ۼ��������-�ۼ��˻�����)*(-1)
        UFDouble arrvnum = item.getNaccumarrvnum();
        UFDouble backarrvnum = item.getNbackarrvnum();
        UFDouble accumstorenum = item.getNaccumstorenum();
        UFDouble num = MathTool.sub(arrvnum, backarrvnum);
        num = MathTool.sub(num, accumstorenum);
        num = MathTool.oppose(num);
        item.setNcanarrivenum(num);
      }
      else {
        // ��������(�ۼƵ�������-�ۼ��������)*(-1)
        UFDouble arrvnum = item.getNaccumarrvnum();
        UFDouble accumstorenum = item.getNaccumstorenum();
        UFDouble num = MathTool.sub(arrvnum, accumstorenum);
        num = MathTool.oppose(num);
        item.setNcanarrivenum(num);
      }
    }
  }

}
