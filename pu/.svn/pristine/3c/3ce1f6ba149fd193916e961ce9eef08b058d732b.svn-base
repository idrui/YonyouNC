package nc.vo.pu.m21.rule.margin;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.margin.IPuMarginProcess;
import nc.vo.pu.margin.IPuMarginProcessFactory;
import nc.vo.pub.AggregatedValueObject;

/**
 * 采购订单尾差处理器
 * 
 * @since 6.0
 * @version 2012-4-20 下午3:15:07
 * @author zhaoyha
 */
public class OrderMarginRule implements IRule<OrderVO> {
  private String srcBilltype;

  private AggregatedValueObject[] srcBillVos;

  /**
   * 构造采购订单尾差处理规则
   * 
   * @param srcBilltype 来源单据类型
   * @param srcBillVos 来源单据VO数组，可为null
   */
  public OrderMarginRule(String srcBilltype, AggregatedValueObject[] srcBillVos) {
    super();
    this.srcBilltype = srcBilltype;
    this.srcBillVos = srcBillVos;
  }

  @Override
  public void process(OrderVO[] vos) {
    IPuMarginProcessFactory factory =
        new OrderMarginProcessFactory(this.srcBilltype, vos, this.srcBillVos);
    List<IPuMarginProcess> processList = factory.createMarginProcess();
    for (IPuMarginProcess process : processList) {
      process.process();
    }

  }

}
