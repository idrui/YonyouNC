/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午02:52:16
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.rule.RPCastunitidCheck;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查辅计量管理但未确认辅单位的物料
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午02:52:16
 */
public class RPCastunitidRule implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public RPCastunitidRule(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = OrderReceivePlanUtils.getAddAndUpVOs(vos[0]);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    RPCastunitidCheck check = new RPCastunitidCheck();
    check.castunitidCheck(rpVOs, this.orderVO);
  }

}
