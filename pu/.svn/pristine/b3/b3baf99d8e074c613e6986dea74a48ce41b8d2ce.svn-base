/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 上午08:41:17
 */
package nc.bs.pu.m4202.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消签字时检查是否暂估过
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-9 上午08:41:17
 */
public class EstimatedCheckRule implements IRule<VmiFIVO> {

  @Override
  public void process(VmiFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (VmiFIVO vo : vos) {
      VmiFIHeaderVO head = vo.getParentVO();
      UFDouble estnum = head.getNestnum();
      if (0 != MathTool.compareTo(estnum, UFDouble.ZERO_DBL)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0084")/*@res "消耗汇总已经暂估过成本或应付，不能取消审批!"*/);
      }
    }

  }
}