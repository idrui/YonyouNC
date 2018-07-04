package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              价格结算单单据号更新处理
 * @scene
 *        价格结算单更新保存
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:38:55
 * @author luojw
 */
public class BillCodeUpdateRule implements ICompareRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] voArray, PricestlVO[] originVOArray) {
    PUBillCodeUtils.getPricestlCode().upadteBillCode(voArray, originVOArray);
  }
}
