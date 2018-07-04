/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午01:45:02
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RPDownFlowOrCloseCheck;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划有后续单据或相关订单行已关闭不能删除
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午01:45:02
 */
public class RPDeleteRule implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public RPDeleteRule(OrderVO orderVO) {
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

    Object[] delVOs = vos[0].getDelObjs();
    if (ArrayUtils.isEmpty(delVOs)) {
      return;
    }
    OrderReceivePlanVO[] rpVOs = ArrayUtil.convertArrayType(delVOs);

    RPDownFlowOrCloseCheck check = new RPDownFlowOrCloseCheck();
    String result = check.downFlowOrCloseCheck(rpVOs, this.orderVO);

    if (!StringUtil.isEmptyWithTrim(result)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0290", null, new String[]{result})/*{0}不能删除*/);
    }
  }

}
