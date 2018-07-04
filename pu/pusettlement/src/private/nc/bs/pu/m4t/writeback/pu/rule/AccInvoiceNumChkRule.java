/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����10:44:39
 */
package nc.bs.pu.m4t.writeback.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ۼƿ�Ʊ������飺
 * <li>if ������ < �ۼƿ�Ʊ���� then �׳��쳣(�ۼƿ�Ʊ�������ܳ��ڳ��ݹ����ɿ�Ʊ����)
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 ����10:44:39
 */
public class AccInvoiceNumChkRule implements IRule<InitialEstViewVO> {

  /**
   * ���෽����д
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstViewVO[] views) {
    for (InitialEstViewVO view : views) {
      UFDouble nnum = view.getNnum();
      UFDouble naccinvoicenum = view.getNaccinvoicenum();
      if (MathTool.isDiffSign(nnum, naccinvoicenum)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0091")/*@res "�����ۼƿ�Ʊ�������ڳ��ݹ�������������һ�� ���У����飡"*/);
      }
      if (MathTool.compareTo(MathTool.abs(nnum), MathTool.abs(naccinvoicenum)) < 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0092")/*@res "�ۼƿ�Ʊ�������ܳ��ڳ��ݹ����ɿ�Ʊ����"*/);
      }
    }
  }

}