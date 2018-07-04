package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              �۸���㵥���ݺ�Ψһ�Լ��
 * @scene
 *        �۸���㵥�������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:12:15
 * @author yanxm5
 */
public class BillCodeCheckUniqueRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    PUBillCodeUtils.getPricestlCode().checkUnique(vos);
  }

}
