package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              �۸���㵥���ݺŴ������
 * @scene
 *        �۸���㵥��������
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:23:35
 * @author luojw
 */
public class BillCodeRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    PUBillCodeUtils.getPricestlCode().createBillCode(vos);
  }

}
