/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 上午09:23:59
 */
package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单根据寻源算法设置默认采购组织
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:05:28
 * @author yanxm5
 */
public class SetPurchaseorgRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setPurchaseorg(vos);
  }

  private void setPurchaseorg(PraybillVO[] vos) {

    for (PraybillVO vo : vos) {
      new SetPurchaseorg().setPurchaseorg(new BillHelper<PraybillVO>(vo));
    }
  }
}
