package nc.vo.pu.pub.rule.pf;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfScriptClassAdaptor;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 在一个动作驱动下一个动作时，可能会根据规则过滤VO
 * 将过滤好的VO重新放到流程的参数之中，以便驱动下一个动作时是使用最新的上游VO
 * 还负责，如果上游VO过滤后，已经为null，则使流程平台不再驱动后续动作
 * 采购订单审批－会使用－因它可能配置驱动驱动入库单的动作
 * 采购发票审批－会使用－因它可能配置驱动传应付
 * 采购发票传应付－会使用－因它可能配置驱动应付单保存
 * @scene
 * 审批,传应付
 * @param
 * pfVo 平台流程VO
 *
 * @since 6.3
 * @version 2014-10-22 下午2:53:06
 * @author zhangshqb
 */
public class UpdatePflowVORule<E extends AbstractBill> implements IRule<E> {
  private PfParameterVO pfVo = null;

  public UpdatePflowVORule(PfParameterVO pfVo) {
    this.pfVo = pfVo;
  }

  @Override
  public void process(E[] vos) {
    PfScriptClassAdaptor adaptor = new PfScriptClassAdaptor(this.pfVo);
    if (ArrayUtils.isEmpty(vos)) {
      adaptor.setVo(null);
      adaptor.setVos(null);
      // 使其不能再驱动后续动作(为适应VO流程平台所作处理，如果传空VO过去也不报错，则
      // 这里也去掉)
      if (null != this.pfVo) {
        this.pfVo.m_billType = "";
      }
    }
    else {
      adaptor.setVo(vos[0]);
      adaptor.setVos(vos);
    }
  }

}
