package nc.bs.pu.m23.writeback.qc.c005rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.qc.Writeback23ForC005Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������е���д����ʱ������д����������
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 ����09:43:07
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
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0108")/*@res "��д�������Ĳ����н������в�������������Ϊ�գ�"*/);
      }
    }
  }
}