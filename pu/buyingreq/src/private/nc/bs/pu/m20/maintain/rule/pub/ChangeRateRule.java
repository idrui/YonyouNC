/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 下午02:13:41
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置换算率规则
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:09:17
 * @author yanxm5
 */
public class ChangeRateRule implements IRule<PraybillVO> {

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
    // TODO 删掉
    // for (PraybillVO vo : vos) {
    // new ChangeRateUtil()
    // .setChgRateAndFixedFlag(new BillHelper<PraybillVO>(vo));
    // }
  }
}
