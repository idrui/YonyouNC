package nc.bs.pu.m23.fa;

import nc.bs.pu.m23.fa.rule.DealCanStoreNumForFARule;
import nc.bs.pu.m23.fa.rule.FilterByICRule;
import nc.bs.pu.m23.fa.rule.FilterByMateialRule;
import nc.bs.pu.m23.fa.rule.FilterBySelectedRule;
import nc.bs.pu.m23.fa.rule.UpdateBTransAssetRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.fa.FaToScmService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 生成转固单
 * 
 * @since 6.0
 * @version 2010-12-23 上午10:58:36
 * @author wuxla
 */

public class TransAssetBP {

  public ArriveVO[] transAsset(ArriveVO[] vos, ArriveVO[] fullVOs) {

    if (!SysInitGroupQuery.isFAEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0040")/*
                                                                   * @res
                                                                   * "固定资产模块未启用,无法生成资产卡片!"
                                                                   */);
    }

    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveBPPlugInPoint.TransAsset);

    this.addBeforeRule(processer, vos);
    this.addAfterRule(processer);
    ArriveVO[] newFullVOs = processer.before(fullVOs);
    if (ArrayUtils.isEmpty(newFullVOs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0043")/*
                                                                   * @res
                                                                   * "无符合条件可转固定资产的到货单行记录"
                                                                   */);
    }

    FaToScmService.insertFromArrivalGoods(newFullVOs);
    ArriveVO[] returnVOs = processer.after(newFullVOs);
    return returnVOs;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    processer.addAfterRule(new UpdateBTransAssetRule(UFBoolean.TRUE));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer, ArriveVO[] vos) {
    processer.addBeforeRule(new FilterBySelectedRule(vos));
    processer.addBeforeRule(new FilterByMateialRule());
    processer.addBeforeRule(new FilterByICRule());
    processer.addBeforeRule(new DealCanStoreNumForFARule());
  }
}
