package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              价格结算单单据号回退处理:调用公共代码处理
 * @scene
 *        价格结算单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:18:46
 * @author luojw
 */
public class ReturnBillCodeRule implements ICompareRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos, PricestlVO[] orgVos) {
    if (null != orgVos) {
      PUBillCodeUtils.getPricestlCode().returnBillCode(orgVos);
    }

  }

}
