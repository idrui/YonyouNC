package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              价格结算单单据号唯一性检查
 * @scene
 *        价格结算单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:12:15
 * @author yanxm5
 */
public class BillCodeCheckUniqueRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    PUBillCodeUtils.getPricestlCode().checkUnique(vos);
  }

}
