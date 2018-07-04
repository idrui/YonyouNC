package nc.vo.pu.m20.rule.margin;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.margin.IPuMarginProcess;
import nc.vo.pu.margin.IPuMarginProcessFactory;
import nc.vo.pub.AggregatedValueObject;

/**
 * @description
 *              请购单尾差处理器
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param String srcBilltype 来源单据类型
 *          AggregatedValueObject[] srcBillVos 来源单据VO数组
 * @since 6.3
 * @version 2014-10-21 下午2:12:33
 * @author yanxm5
 */
public class PrayBillMarginRule implements IRule<PraybillVO> {
  private String srcBilltype;

  private AggregatedValueObject[] srcBillVos;

  /**
   * 构造请购单尾差处理规则
   * 
   * @param srcBilltype 来源单据类型
   * @param srcBillVos 来源单据VO数组，可为null
   */
  public PrayBillMarginRule(String srcBilltype,
      AggregatedValueObject[] srcBillVos) {
    super();
    this.srcBilltype = srcBilltype;
    this.srcBillVos = srcBillVos;
  }

  @Override
  public void process(PraybillVO[] vos) {
    IPuMarginProcessFactory factory =
        new PrayBillMarginProcessFactory(this.srcBilltype, vos, this.srcBillVos);
    List<IPuMarginProcess> processList = factory.createMarginProcess();
    for (IPuMarginProcess process : processList) {
      process.process();
    }
  }

}
