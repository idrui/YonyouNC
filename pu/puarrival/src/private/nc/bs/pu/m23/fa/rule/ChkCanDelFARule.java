package nc.bs.pu.m23.fa.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 检查是否可以删除资产卡片
 * @scene
 * 删除资产卡片
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-27 下午4:22:36
 * @author guoyk
 */
public class ChkCanDelFARule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    // 检查资产信息管理是否启用
    this.chkAIMModuleEnable();

    for (ArriveVO aggVO : voArray) {
      this.chkIsFromSC(aggVO);
    }
  }

  private void chkAIMModuleEnable() {
    boolean isEnable = false;
    isEnable = SysInitGroupQuery.isAIMEnabled();
    if (!isEnable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0183")/*
                                                                   * @res
                                                                   * "资产信息管理模块未启用,无法取消生成设备卡片!"
                                                                   */);
    }
  }

  private void chkIsFromSC(ArriveVO aggVO) {
    boolean isFromSC = ArrivePublicUtil.isArriveFromSC(aggVO);
    if (isFromSC) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0067")/*
                                                                   * @res
                                                                   * "不支持来源为委外的到货单生成设备卡片！"
                                                                   */);
    }
  }
}
