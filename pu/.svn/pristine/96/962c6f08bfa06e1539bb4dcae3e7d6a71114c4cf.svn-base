/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 上午11:02:27
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.SetOrdertrantype;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单补全订单类型规则
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:07:22
 * @author yanxm5
 */
public class SetOrdertrantypeRule implements IRule<PraybillVO> {

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
    this.setOrdertrantype(vos);
  }

  /**
   * 补全订单类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @since 6.1
   * @author lixyp
   * @time 2011-12-28 上午11:10:11
   */
  public void setOrdertrantype(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      new SetOrdertrantype().setOrdertrantype(new BillHelper<PraybillVO>(vo));
    }
  }
}
