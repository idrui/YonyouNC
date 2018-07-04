package nc.bs.pu.m23.maintain.rule.insert;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 新增保存时，单据号规则
 * @scene
 * 到货单新增保存时
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-26 下午02:45:59
 * @author hanbin
 */

public class InsertBillCodeRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    PUBillCodeUtils.getArriveCode().createBillCode(vos);
  }

}
