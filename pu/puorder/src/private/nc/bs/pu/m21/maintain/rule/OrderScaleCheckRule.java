package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrderScaleRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ�������������У����
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����11:23:46
 * @author luojw
 */
public class OrderScaleCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getHVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    // �������BillVOScaleCheckProcessor��process������Ҳ���ߵ�У�鷽��
    new OrderScaleRule().setScaleForCheck(scaleChecker);

  }

}
