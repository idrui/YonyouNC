package nc.bs.pu.m23.fa;

import nc.bs.pu.m23.fa.rule.CanStoreNumRule;
import nc.bs.pu.m23.fa.rule.FilterByMaterTurnOverRule;
import nc.bs.pu.m23.fa.rule.FilterBySelectedRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 周转材直领查询BP
 *
 * @since 6.0
 * @version 2010-12-27 下午07:33:31
 * @author wuxla
 */

public class QueryArriveFor4A60BP {
  public ArriveVO[] queryArriveFor4A60(ArriveVO[] vos) {
    BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(vos);
    ArriveVO[] fullVOs = tool.getClientFullInfoBill();

    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveBPPlugInPoint.MaterialAssign);
    this.addBeforeRule(processer, vos);
    fullVOs = processer.before(fullVOs);
    if (ArrayUtils.isEmpty(fullVOs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0080")/*@res "无可直领的周转材料到货单行记录"*/);
    }
    return fullVOs;
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer, ArriveVO[] vos) {
    processer.addBeforeRule(new FilterBySelectedRule(vos));
    processer.addBeforeRule(new FilterByMaterTurnOverRule());
    processer.addBeforeRule(new CanStoreNumRule());
  }
}