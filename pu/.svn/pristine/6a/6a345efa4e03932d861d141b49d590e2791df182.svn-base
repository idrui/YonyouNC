/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午03:05:36
 */
package nc.bs.pu.m20.maintain.rule.insert;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              根据请购单新增单据号
 * @scene
 *        请购单新增
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:52:55
 * @author yanxm5
 */
public class BillCodeRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    PUBillCodeUtils.getPraybillCode().createBillCode(vos);
  }

}
