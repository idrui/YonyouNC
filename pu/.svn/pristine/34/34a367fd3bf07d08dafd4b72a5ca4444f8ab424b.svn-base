package nc.bs.pu.m23.writeback.qc.c005rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.qc.Writeback23ForC005Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>紧急放行单回写到货时，检查回写参数规则类
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 上午09:43:07
 */
public class ChkWriteParaForC005Rule implements IRule<ArriveItemVO> {

  private UFBoolean isNeedChkDiffNum;

  private Writeback23ForC005Para[] paras;

  public ChkWriteParaForC005Rule(Writeback23ForC005Para[] paras) {
    this.paras = paras;
  }

  public ChkWriteParaForC005Rule(Writeback23ForC005Para[] paras,
      UFBoolean isNeedChkDiffNum) {
    this.paras = paras;
    this.isNeedChkDiffNum = isNeedChkDiffNum;
  }

  @Override
  public void process(ArriveItemVO[] vos) {
    for (Writeback23ForC005Para para : this.paras) {
      para.validate();

      if (this.isNeedChkDiffNum == null) {
        continue;
      }
      if (this.isNeedChkDiffNum.booleanValue() && (para.getDiffNum() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0108")/*@res "回写到货单的参数中紧急放行差异数量不允许为空！"*/);
      }
    }
  }
}