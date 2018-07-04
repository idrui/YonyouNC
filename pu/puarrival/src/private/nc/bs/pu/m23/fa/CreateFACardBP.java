package nc.bs.pu.m23.fa;

import nc.bs.pu.m23.fa.rule.ChkCanCrtFARule;
import nc.bs.pu.m23.fa.rule.FilterBySelectedRule;
import nc.bs.pu.m23.fa.rule.InvokeCrtFAServiceRule;
import nc.bs.pu.m23.fa.rule.SendMsgForFARule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.bdlayer.msg.ScmBuziMsgResEnum;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ɵ������������ʲ���Ƭ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����10:52:12
 */
public class CreateFACardBP {

  public ArriveVO[] createFACard(ArriveVO[] aggVO, ArriveVO[] originBills) {
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveBPPlugInPoint.CreateFACardBP);
    this.addBeforeRule(processer, aggVO);
    this.addAfterRule(processer);

    ArriveVO[] newFullVOs = processer.before(originBills);
    processer.after(newFullVOs);
    return newFullVOs;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    // ��������������
    processer.addAfterRule(new ArriveATPUpdateRule(false));
    // ����ҵ����Ϣ
    processer.addAfterRule(new SendMsgForFARule(
        ScmBuziMsgResEnum.ARRIVAL_GENEQUIP.code()));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer, ArriveVO[] vos) {
    processer.addBeforeRule(new FilterBySelectedRule(vos));
    // ���������ǰ����
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // ����Ƿ�������ʲ���Ƭ
    processer.addBeforeRule(new ChkCanCrtFARule());
    // TODO ���ݼ����������Ƿ������飩�ĺϸ������������ɿ�Ƭ������
    // �����ʲ�����
    processer.addBeforeRule(new InvokeCrtFAServiceRule());
  }
}
