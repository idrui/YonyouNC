package nc.bs.pu.m23.fa;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m23.fa.rule.FilterBySelectedRule;
import nc.bs.pu.m23.fa.rule.UpdateBTransAssetRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.fa.FaToScmService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 删除转固单
 * 
 * @since 6.0
 * @version 2010-12-23 上午10:59:13
 * @author wuxla
 */

public class DeleteTransAssetBP {
  public ArriveVO[] deleteTransAsset(ArriveVO[] vos, ArriveVO[] fullVOs) {
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveBPPlugInPoint.DeleteTransAsset);

    this.addBeforeRule(processer, vos);
    this.addAfterRule(processer);
    ArriveVO[] newFullVOs = processer.before(fullVOs);
    if (ArrayUtils.isEmpty(newFullVOs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0022")/*
                                                                   * @res
                                                                   * "所选到货单行没有生成转固单，不能删除"
                                                                   */);
    }
    // 资产返回未删除转固单的到货单行pk
    List<String> FAcantDeleteBodyPKs =
        FaToScmService.deleteFromArrivalGoods(newFullVOs);

    if (!CollectionUtils.isEmpty(FAcantDeleteBodyPKs)) {
      List<ArriveVO> newVOList = new ArrayList<ArriveVO>();
      List<ArriveItemVO> newList = new ArrayList<ArriveItemVO>();
      StringBuilder rows = new StringBuilder();
      for (ArriveVO vo : newFullVOs) {
        for (ArriveItemVO itemVO : vo.getBVO()) {
          String pk_arrive_b = itemVO.getPk_arriveorder_b();
          if (FAcantDeleteBodyPKs.contains(pk_arrive_b)) {
            newList.add(itemVO);
            rows.append("[" + itemVO.getCrowno() + "]");
          }
        }

        if (newList.size() > 0) {
          ArriveItemVO[] newItemVOs =
              newList.toArray(new ArriveItemVO[newList.size()]);
          vo.setBVO(newItemVOs);
          newVOList.add(vo);
        }
      }
      if (rows.length() != 0) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004040_0", "04004040-0212", null, new String[] {
              rows.toString()
            })/* 第{0}行生成的转固单已经非自由态，不允许删除！ */);
      }
      if (newVOList.size() > 0) {
        newFullVOs = newVOList.toArray(new ArriveVO[newVOList.size()]);
      }
      else {
        newFullVOs = null;
      }
    }

    processer.after(newFullVOs);

    return newFullVOs;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    processer.addAfterRule(new UpdateBTransAssetRule(UFBoolean.FALSE));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer, ArriveVO[] vos) {
    processer.addBeforeRule(new FilterBySelectedRule(vos));
  }
}
