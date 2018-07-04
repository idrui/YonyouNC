package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;

/**
 * @description
 *              价格结算单表体非空检查
 * @scene
 *        价格结算单新增保存、更新保存
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:21:02
 * @author luojw
 */
public class BodyEmptyRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    new ItemNotNullCheckRule().checkItem(vos);
  }

}
