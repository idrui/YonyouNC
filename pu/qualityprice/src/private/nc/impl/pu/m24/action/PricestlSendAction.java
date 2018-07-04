package nc.impl.pu.m24.action;

import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m24.action.rule.SendAppoveVOValidateRule;
import nc.impl.pu.m24.action.rule.SendApproveFlowCheckRule;
import nc.impl.pu.m24.action.rule.SendApproveStatusChangeRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;

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
 * @time 2010-1-13 ����11:14:26
 */
public class PricestlSendAction {

  /**
   * ���������������۸���㵥�������Ӧ��Action
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-13 ����11:14:57
   */
  public PricestlVO[] sendapprove(PricestlVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PricestlVO> util =
        new PfParameterUtil<PricestlVO>(script.getPfParameterVO(), vos);
    PricestlVO[] originBills = util.getOrginBills();
    PricestlVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<PricestlVO> processor =
        new AroundProcesser<PricestlVO>(PricestlPluginPoint.SEND_APPROVE);
    // ��ӹ���
    this.addRule(processor);
    // ִ�г־û�ǰ����
    processor.before(clientBills);
    // ���ݳ־û�
    PricestlVO[] returnVos =
        new BillUpdate<PricestlVO>().update(clientBills, originBills);
    // ִ�г־û������
    processor.after(returnVos);

    return returnVos;
  }

  /**
   * ������������������ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����01:23:06
   */
  private void addRule(AroundProcesser<PricestlVO> processer) {
    // ��ӳ־û�ǰ����
    processer.addBeforeFinalRule(new SendApproveFlowCheckRule());
    // ����״̬������
    processer.addBeforeFinalRule(new SendAppoveVOValidateRule());
    // ����״̬�޸�
    processer.addBeforeFinalRule(new SendApproveStatusChangeRule());

  }
}
