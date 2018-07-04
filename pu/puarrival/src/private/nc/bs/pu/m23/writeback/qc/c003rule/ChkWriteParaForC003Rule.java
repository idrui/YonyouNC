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
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0098")/*@res "��д�������ʼ���ʱ���������鲻����Ϊ�գ�"*/);
    }
    for (ArriveBbVO para : paras) {
      if (para.getPk_arriveorder() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0099")/*@res "��д������ʱ�������е���������������Ϊ�գ�"*/);
      }
      if (para.getPk_arriveorder_b() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0100")/*@res "��д������ʱ�������е������ӱ�����������Ϊ�գ�"*/);
      }

      if (this.isApprove && (para.getBcanstore() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0101")/*@res "��д������ʱ���������Ƿ������ʶ������Ϊ�գ�"*/);
      }
      if (this.isApprove && (para.getBeligible() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0102")/*@res "��д������ʱ���������Ƿ�ϸ�Ʒ��ʶ������Ϊ�գ�"*/);
      }
      if (this.isApprove && (para.getNnum() == null)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0103")/*@res "��д������ʱ��������������������Ϊ�գ�"*/);
      }
    }
  }
}