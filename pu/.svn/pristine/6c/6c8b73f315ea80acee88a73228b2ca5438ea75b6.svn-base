/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午04:47:16
 */
package nc.bs.pu.est.m45.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 将自动暂估标志设置到表头
 * @scene
 * 自动暂估
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:15:38
 * @author zhangshqb
 */
public class AutoEstFlagFillRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PurchaseInEstVO vo : vos) {
      vo.getParentVO().setBautotofi(UFBoolean.TRUE);
    }
  }

}
