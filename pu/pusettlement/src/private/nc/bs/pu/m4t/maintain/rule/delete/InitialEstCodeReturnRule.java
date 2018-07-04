package nc.bs.pu.m4t.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *���ݺŻ��˴������ù������봦��
 * @scene
 * �ڳ��ݹ���ɾ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-9-8 ����10:39:56
 * @author wuxla
 */
public class InitialEstCodeReturnRule implements ICompareRule<InitialEstVO> {
  @Override
  public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
    if (null != originVOs) {
      PUBillCodeUtils.getInitialEstCode().returnBillCode(originVOs);
    }
  }

}
