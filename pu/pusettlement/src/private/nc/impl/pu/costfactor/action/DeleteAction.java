package nc.impl.pu.costfactor.action;

import nc.bs.pu.costfactor.maintain.DeleteBP;
import nc.impl.pu.costfactor.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ɱ�Ҫ�ض���ɾ������
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɱ�Ҫ�ض���ɾ��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����03:07:40
 */

public class DeleteAction {
  /**
   * ���������������ɱ�Ҫ�ض���ɾ��������ڷ�����
   * <p>
   * <b>����˵��</b>
   *
   * @param bills
   */
  public void delete(CostfactorVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<CostfactorVO> transferTool =
        new BillTransferTool<CostfactorVO>(bills);
    CostfactorVO[] clientBills = transferTool.getClientFullInfoBill();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0093")/*@res "��ȫǰ̨VO�����������ʱ���"*/);

    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(ActionPlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0094")/*@res "����ɾ��BPǰִ��ҵ�����"*/);

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0095")/*@res "����ɾ��BP������ɾ��"*/);

    TimeLog.logStart();
    processer.after(clientBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0096")/*@res "����ɾ��BP��ִ��ҵ�����"*/);

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0097")/*@res "ҵ����־"*/);
  }
}