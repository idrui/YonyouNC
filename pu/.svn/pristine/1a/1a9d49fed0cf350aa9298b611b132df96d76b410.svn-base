package nc.vo.pu.m23.rule.margin;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.margin.IPuMarginProcess;
import nc.vo.pu.margin.IPuMarginProcessFactory;
import nc.vo.pub.AggregatedValueObject;

/**
 * 到货单尾差处理器。
 * 
 * @since 6.0
 * @version 2012-4-24 上午10:29:32
 * @author lixyp
 */
public class ArriveMarginRule implements IRule<ArriveVO> {

  private String srcBilltype;

  private AggregatedValueObject[] srcBillVos;

  /**
   * 构造到货单尾差处理规则
   * 
   * @param srcBilltype 来源单据类型
   * @param srcBillVos 来源单据VO数组，可为null
   */
  public ArriveMarginRule(String srcBilltype, AggregatedValueObject[] srcBillVos) {
    super();
    this.srcBilltype = srcBilltype;
    this.srcBillVos = srcBillVos;
  }

  @Override
  public void process(ArriveVO[] vos) {
    IPuMarginProcessFactory factory =
        new ArriveMarginProcessFactory(this.srcBilltype, vos, this.srcBillVos);
    List<IPuMarginProcess> processList = factory.createMarginProcess();
    for (IPuMarginProcess process : processList) {
      process.process();
    }
  }
}
