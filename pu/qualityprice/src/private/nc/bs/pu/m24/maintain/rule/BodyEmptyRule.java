package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              �۸���㵥����ǿռ��
 * @scene
 *        �۸���㵥�������桢���±���
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:21:02
 * @author luojw
 */
public class BodyEmptyRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
