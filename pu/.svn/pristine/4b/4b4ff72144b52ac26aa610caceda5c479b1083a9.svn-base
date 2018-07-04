package nc.bs.pu.m20.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              请购单单据号更新处理
 * @scene
 *        请购单修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:42:11
 * @author yanxm5
 */
public class BillCodeUpdateRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray, PraybillVO[] originVOArray) {
    PUBillCodeUtils.getPraybillCode().upadteBillCode(voArray, originVOArray);
  }
}
