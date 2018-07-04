/**
 * $�ļ�˵��$
 *
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 ����01:32:14
 */
package nc.bs.pu.costfactor.maintain;

import nc.bs.pu.costfactor.maintain.rule.CheckEmpty;
import nc.bs.pu.costfactor.maintain.rule.CheckMaterialRule;
import nc.bs.pu.costfactor.maintain.rule.ConcurrCtrlRule;
import nc.bs.pu.costfactor.maintain.rule.FeeShowOrderSetRule;
import nc.bs.pu.costfactor.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ɱ�Ҫ�ض�����������BP
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɱ�Ҫ�ض�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����01:32:14
 */
public class InsertBP {

  /**
   * ���������������ɱ�Ҫ�ض����������档
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills �ɱ�Ҫ�ض���
   * @return �����ĳɱ�Ҫ�ض���
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����01:35:22
   */
  public CostfactorVO[] insert(CostfactorVO[] bills) {
    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(BPPlugInPoint.InsertBP);

    // ����ִ��ǰҵ�����
    this.addRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0056")/* @res "����ǰִ��ҵ�����" */);

    TimeLog.logStart();
    BillInsert<CostfactorVO> bo = new BillInsert<CostfactorVO>();
    CostfactorVO[] vos = bo.insert(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0057")/* @res "���浥�ݵ����ݿ�" */);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0058")/* @res "�����ִ��ҵ�����" */);

    return vos;
  }

  private void addRule(AroundProcesser<CostfactorVO> processer) {
    // �ɱ�Ҫ�ض�����ǰ����
    // processer.addBeforeRule(new BodyEmptyRule());
    // �ɱ�Ҫ�غ�̨�ǿ�У��
    processer.addBeforeRule(new CheckEmpty());
    // ��������
    processer.addBeforeFinalRule(new ConcurrCtrlRule());
    // �����������ϵ��ظ�
    processer.addBeforeRule(new CheckMaterialRule());
    // ���÷������Ĭ����ʾֵ���ݹ��Ǵ�������ֵ��
    processer.addAfterFinalRule(new FeeShowOrderSetRule());

  }
}
