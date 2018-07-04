package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              �۸���㵥���ݺŸ��´���
 * @scene
 *        �۸���㵥���±���
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:38:55
 * @author luojw
 */
public class BillCodeUpdateRule implements ICompareRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] voArray, PricestlVO[] originVOArray) {
    PUBillCodeUtils.getPricestlCode().upadteBillCode(voArray, originVOArray);
  }
}
