package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.UnitAndChangeRate;
import nc.vo.pu.pub.util.BillHelper;

/**
 * @since 6.1
 * @time 2011-12-22 ����10:38:50
 * @author lixyp
 */

public class NumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < rows.length; ++i) {
        rows[i] = i;
      }
      // ��Ҫ���û����ʣ��������ȷ���������ʹ����ˡ�
      // ���ñ��۵�λ�����ۻ����ʡ���λ��������
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      UnitAndChangeRate rate = new UnitAndChangeRate(helper);
      rate.setChangeRateNotChangeQt(rows);
      rate.setQtChangeRate(rows);
      NumValueSetter setter = new NumValueSetter(helper);
      setter.setNastnum(rows);
    }
  }
}
