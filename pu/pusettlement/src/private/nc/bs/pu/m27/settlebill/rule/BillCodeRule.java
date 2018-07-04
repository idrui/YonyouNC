package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ���㵥���ݺŵ����ɹ���
 * @scene
 * ���ý���,������ϱ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:12:01
 * @author zhangshqb
 */
public class BillCodeRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    PUBillCodeUtils.getSettleBillCode().createBillCode(vos);
  }
}
