package nc.bs.pu.m24.maintain;

import nc.bs.pu.m24.maintain.rewrite.Write45WhenDeleteRule;
import nc.bs.pu.m24.maintain.rule.BillStatusRule;
import nc.bs.pu.m24.maintain.rule.ReturnBillCodeRule;
import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥ɾ��BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 ����02:34:51
 */
public class PricestlDeleteBP {

  public void delete(PricestlVO[] vos) {

    CompareAroundProcesser<PricestlVO> processer =
        new CompareAroundProcesser<PricestlVO>(PricestlPluginPoint.DELETE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(vos, vos);
    BillDelete<PricestlVO> bo = new BillDelete<PricestlVO>();
    bo.delete(vos);

    processer.after(vos, vos);

  }

  private void addAfterRule(CompareAroundProcesser<PricestlVO> processer) {

    // ��д����
    processer.addAfterRule(new Write45WhenDeleteRule());

    // �˻ص��ݺ�
    processer.addAfterRule(new ReturnBillCodeRule());

  }

  private void addBeforeRule(CompareAroundProcesser<PricestlVO> processer) {
    // ����״̬���
    processer.addBeforeFinalRule(new BillStatusRule());

  }
}
