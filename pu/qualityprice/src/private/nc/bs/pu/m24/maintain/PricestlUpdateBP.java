package nc.bs.pu.m24.maintain;

import nc.bs.pu.m24.maintain.rewrite.Write45WhenUpdateRule;
import nc.bs.pu.m24.maintain.rule.BillCodeCheckUniqueRule;
import nc.bs.pu.m24.maintain.rule.BillCodeUpdateRule;
import nc.bs.pu.m24.maintain.rule.BodyEmptyRule;
import nc.bs.pu.m24.maintain.rule.FillInsertPricestlVos;
import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单修改保存BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:36:02
 */
public class PricestlUpdateBP {

  /**
   * 方法功能描述：价格结算单修改保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param updateVos 需要更新的价格结算单
   * @param originalVos 旧价格结算单
   * @param isRevise 是否是修订
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-23 下午02:20:16
   */
  public PricestlVO[] update(PricestlVO[] updateVos, PricestlVO[] originalVos) {
    CompareAroundProcesser<PricestlVO> processer =
        new CompareAroundProcesser<PricestlVO>(PricestlPluginPoint.UPDATE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(updateVos, originalVos);
    BillUpdate<PricestlVO> bo = new BillUpdate<PricestlVO>();
    PricestlVO[] retVos = bo.update(updateVos, originalVos);
    processer.after(retVos, originalVos);

    return retVos;
  }

  private void addAfterRule(CompareAroundProcesser<PricestlVO> processer) {
    // 单据号唯一性检查
    processer.addAfterRule(new BillCodeCheckUniqueRule());
    // 回写上游
    processer.addAfterRule(new Write45WhenUpdateRule());

    processer.addAfterRule(new SetUpdateAuditInfoRule<PricestlVO>());

  }

  private void addBeforeRule(CompareAroundProcesser<PricestlVO> processer) {

    // 单据号处理
    processer.addBeforeRule(new BillCodeUpdateRule());
    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 补全数据
    processer.addBeforeRule(new FillInsertPricestlVos());
    // 审批不通过可修改，修改后的状态、审批人、审批日期等的设置
    processer.addBeforeFinalRule(new NoPassUpdateRule<PricestlVO>());
    processer.addBeforeRule(new UserDefSaveRule<PricestlVO>(new Class[] {
      PricestlHeaderVO.class, PricestlItemVO.class
    }));
    // // 非空项检查
    // processer.addBeforeFinalRule(new NotNullRule());
    // // 长度检查
    // processer.addBeforeFinalRule(new ChkLenRule());
  }
}
