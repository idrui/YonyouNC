package nc.impl.pu.m24.action;

import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m24.action.rule.UnApproveVOValidateRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥�������Ӧ��Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-30 ����11:14:26
 */
public class PricestlUnApproveAction {
  /**
   * ���������������۸���㵥�������Ӧ��Action
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-30 ����11:14:57
   */
  public PricestlVO[] unapprove(PricestlVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PricestlVO> util =
        new PfParameterUtil<PricestlVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PricestlVO[] originBills = util.getOrginBills();
    PricestlVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<PricestlVO> processer =
        new AroundProcesser<PricestlVO>(PricestlPluginPoint.UNAPPROVE);
    this.addBeforeRule(processer);
    processer.before(clientBills);
    // ����ƽ̨�ű���������
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    // ��VO�־û������ݿ���
    BillUpdate<PricestlVO> update = new BillUpdate<PricestlVO>();
    PricestlVO[] returnVos = update.update(clientBills, originBills);
    processer.after(returnVos);

    return returnVos;
  }

  private void addBeforeRule(AroundProcesser<PricestlVO> processer) {
    processer.addBeforeRule(new UnApproveVOValidateRule()); // ���VO״̬���
  }
}
