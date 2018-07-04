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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单发布到电子商务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-9 上午10:43:03
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
    // 没有添加规则
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 发布到电子商务检查
    processer.addBeforeRule(new CheckPublishRule());
    // 发布到电子商务
    processer.addBeforeRule(new PublishToEcRule());

  }
}
