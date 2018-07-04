package nc.impl.pu.m20.action;

import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m20.rule.CheckUnSendApproveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ջز���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-12 ����11:13:17
 */
public class PraybillUnSendApproveAction {
  /**
   * ���������������ջء�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����11:01:49
   */
  public PraybillVO[] unSendapprove(PraybillVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PraybillVO> util =
        new PfParameterUtil<PraybillVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PraybillVO[] originBills = util.getOrginBills();
    PraybillVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(PraybillPluginPoint.UNSENDAPPROVE);
    this.addBeforeRule(processer);

    // ǰ����
    processer.before(clientBills);

    // ����ƽ̨�ű���������
    if (null != script) {
      try {
        script.procRecallFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // ��VO�־û������ݿ���
    BillUpdate<PraybillVO> update = new BillUpdate<PraybillVO>();
    PraybillVO[] returnVos = update.update(clientBills, originBills);

    // �����
    processer.after(returnVos);
    return returnVos;
  }

  /**
   * �����������������ӹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����11:01:34
   */
  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    processer.addBeforeFinalRule(new CheckUnSendApproveRule());// �Ƿ�����ջ�

  }
}
