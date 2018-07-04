/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-18 上午09:12:31
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.bs.pu.m21.rp.QueryRPByPkorderBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RPNaccumrpnumCheck;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>累计到货计划数量不能大于订单数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-18 上午09:12:31
 */
public class RPNaccumrpnumRule implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public RPNaccumrpnumRule(OrderVO orderVO) {
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

    QueryRPByPkorderBP bp = new QueryRPByPkorderBP();
    OrderReceivePlanVO[] rpVOs = bp.getAllRPVOs(this.orderVO.getPrimaryKey());
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    RPNaccumrpnumCheck check = new RPNaccumrpnumCheck();
    check.checkNaccumrpnum(rpVOs, this.orderVO);
  }

}
