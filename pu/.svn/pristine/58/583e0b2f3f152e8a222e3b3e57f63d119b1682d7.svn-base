package nc.bs.pu.m23.writeback.qc.c003rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

public class ChkWriteParaForC003Rule implements IRule<ArriveBbVO> {

  private boolean isApprove;

  public ChkWriteParaForC003Rule(boolean isApprove) {
    this.isApprove = isApprove;
  }

  @Override
  public void process(ArriveBbVO[] paras) {
    if (ArrayUtils.isEmpty(paras)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0098")/*@res "回写到货单质检结果时，参数数组不允许为空！"*/);
    }
    for (ArriveBbVO para : paras) {
      if (para.getPk_arriveorder() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0099")/*@res "回写到货单时，参数中到货单主键不允许为空！"*/);
      }
      if (para.getPk_arriveorder_b() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0100")/*@res "回写到货单时，参数中到货单子表主键不允许为空！"*/);
      }

      if (this.isApprove && (para.getBcanstore() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0101")/*@res "回写到货单时，参数中是否可入库标识不允许为空！"*/);
      }
      if (this.isApprove && (para.getBeligible() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0102")/*@res "回写到货单时，参数中是否合格品标识不允许为空！"*/);
      }
      if (this.isApprove && (para.getNnum() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0103")/*@res "回写到货单时，参数中主数量不允许为空！"*/);
      }
    }
  }
}