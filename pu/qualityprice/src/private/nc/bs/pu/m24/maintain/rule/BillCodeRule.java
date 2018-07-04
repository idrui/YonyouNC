package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              价格结算单单据号处理规则
 * @scene
 *        价格结算单新增保存
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:23:35
 * @author luojw
 */
public class BillCodeRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    PUBillCodeUtils.getPricestlCode().createBillCode(vos);
  }

}
