package nc.bs.pu.costfactor.maintain;

import nc.bs.pu.costfactor.maintain.rule.BodyEmptyRule;
import nc.bs.pu.costfactor.maintain.rule.CheckMaterialRule;
import nc.bs.pu.costfactor.maintain.rule.FeeShowOrderSetRule;
import nc.bs.pu.costfactor.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * �ɱ�Ҫ�ض��屣��BP
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɱ�Ҫ�ض��屣��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����01:32:14
 */
public class UpdateBP {

  /**
   * ���������������ɱ�Ҫ�ض��屣�档
   * <p>
   * <b>����˵��</b>
   *
   * @param bills ��Ҫ����ĳɱ�Ҫ�ض���
   * @param originBills �޸�ǰ�ĳɱ�Ҫ�ض���
   * @return �����ĳɱ�Ҫ�ض���
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����01:35:22
   */
  public CostfactorVO[] update(CostfactorVO[] bills, CostfactorVO[] originBills) {
    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(BPPlugInPoint.UpdateBP);

    // ����ִ��ǰҵ�����
    this.addRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0059")/*@res "�޸ı���ǰִ��ҵ�����"*/);

    TimeLog.logStart();
    BillUpdate<CostfactorVO> bo = new BillUpdate<CostfactorVO>();
    CostfactorVO[] vos = bo.update(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0060")/*@res "�����޸ĵ��ݵ����ݿ�"*/);

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0061")/*@res "�޸ı����ִ��ҵ�����"*/);

    return vos;
  }

  private void addRule(AroundProcesser<CostfactorVO> processer) {
    // ���ü�����
    processer.addBeforeRule(new BodyEmptyRule());
    // �����������ϵ��ظ�
    processer.addBeforeRule(new CheckMaterialRule());
    // ���÷������Ĭ����ʾֵ���ݹ��Ǵ�������ֵ��
    processer.addAfterFinalRule(new FeeShowOrderSetRule());

  }
}