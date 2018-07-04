package nc.vo.pu.m27.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m27.entity.SettleBillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ���㵥���澫�ȼ��
 * @scene
 * ������ϱ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:20:45
 * @author zhangshqb
 */
public class SettleBillScaleCheckRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ����У�鴦��
    BillVOScaleCheckProcessor checker =
        new BillVOScaleCheckProcessor(vos[0].getParentVO().getPk_group(), vos);
    new SettleBillScaleRule().setBodyScale(checker);

  }

}
