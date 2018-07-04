package nc.bs.pu.m23.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 修改保存时，单据号规则
 * @scene
 * 到货单修改保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-26 下午02:45:59
 * @author hanbin
 */

public class UpdateBillCodeRule implements ICompareRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVOs) {
    PUBillCodeUtils.getArriveCode().upadteBillCode(vos, originVOs);
  }

}
