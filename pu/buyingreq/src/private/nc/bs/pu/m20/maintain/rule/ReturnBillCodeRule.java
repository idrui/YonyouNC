/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-26 上午09:42:34
 */
package nc.bs.pu.m20.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              请购单单据号回退处理:调用公共代码处理
 * @scene
 *        请购单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:21:01
 * @author yanxm5
 */
public class ReturnBillCodeRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] orgVos) {
    if (null != orgVos) {
      PUBillCodeUtils.getPraybillCode().returnBillCode(orgVos);
    }

  }

}
