package nc.vo.pu.pub.rule.busilog;

import nc.bs.scmpub.util.SCMBusinessLogUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @description
 *              请购单写业务日志公共规则
 * @scene
 *        请购单和采购订单和到货单审批、弃审
 * @param String interfacePath 接口路径
 *          String operationCode 操作编码
 * @since 6.3
 * @version 2014-10-21 上午8:53:44
 * @author yanxm5
 */

public class WriteOperateLogRule<E extends AbstractBill> implements IRule<E> {

  private String interfacePath;

  private String operationCode;

  /**
   * @param actionCode
   */
  public WriteOperateLogRule(String interfacePath, String operationCode) {
    this.interfacePath = interfacePath;
    this.operationCode = operationCode;
  }

  @Override
  public void process(E[] vos) {
    // 记录无明细日志
    this.logWithNoDetail(vos);
  }

  /**
   * 记录无明细日志
   * 
   * @param vos
   */
  private void logWithNoDetail(E[] vos) {
    // 记录日志,
    SCMBusinessLogUtil<E> logUtil =
        new SCMBusinessLogUtil<E>(vos, null, this.interfacePath,
            this.operationCode);
    logUtil.process();

  }
}
