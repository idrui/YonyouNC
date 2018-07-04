/**
 *
 */
package nc.impl.pu.m25.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * �������:�Ѿ�������㣬���������Ѿ���Ӧ������������δ���Զ���Ϣ��Ӧ����������
 * @scene
 * ����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:50:02
 * @author zhangshqb
 */
public class SettledCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        UFDouble accsettlemny = MathTool.nvl(item.getNaccumsettmny());
        if (!MathTool.isZero(accsettlemny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0033")/*@res "�ɹ���Ʊ�Ѿ�������㣬����ȡ��������"*/);
        }
      }
    }
  }

}