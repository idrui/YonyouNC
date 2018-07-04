package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m4t.entity.InitialEstVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * �ڳ��ݹ������ȼ�����
 * @scene
 * �ڳ��ݹ�������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-31 ����10:01:59
 * @author guoyk
 */

public class InitialEstScaleCheckRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(vos[0].getHeader().getPk_group(), vos);
    new InitialEstScaleRule().setScaleForCheck(scaleChecker);
  }

}
