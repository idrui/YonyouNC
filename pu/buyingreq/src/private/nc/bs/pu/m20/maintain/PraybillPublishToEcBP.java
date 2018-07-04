package nc.bs.pu.m20.maintain;

import nc.bs.pu.m20.maintain.rule.pub.BodyEmptyRule;
import nc.bs.pu.m20.maintain.rule.publish.CheckPublishRule;
import nc.bs.pu.m20.maintain.rule.publish.PublishToEcRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-9 ����10:43:03
 */
public class PraybillPublishToEcBP {

  public PraybillVO[] publishToEc(PraybillVO[] updateVos,
      PraybillVO[] originalVos) {
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(
            PraybillPluginPoint.PUBLISH_TO_EC);
    this.addBeforeRule(processer);
    this.addAfterRule();

    PraybillVO[] upVos = updateVos;
    processer.before(upVos, originalVos);
    BillUpdate<PraybillVO> bo = new BillUpdate<PraybillVO>();
    upVos = bo.update(upVos, originalVos);
    processer.after(upVos, originalVos);

    return upVos;
  }

  /**
   * @param processer
   */
  private void addAfterRule() {
    // û����ӹ���
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // ����ǿռ��
    processer.addBeforeRule(new BodyEmptyRule());
    // ����������������
    processer.addBeforeRule(new CheckPublishRule());
    // ��������������
    processer.addBeforeRule(new PublishToEcRule());

  }
}
