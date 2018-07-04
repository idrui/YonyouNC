/**
 *
 */
package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * ��Ʊ״̬���:���ⷢƱ����ɾ����(������BP��Ӧ�÷ŵ�),ֻ������״̬(����������������)�Ĳ���ɾ��
 * @scene
 * ɾ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:08:33
 * @author zhangshqb
 */
public class InvoiceDelStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0029")/*@res "��ͷ����Ϊ��\r\n"*/);
        continue;
      }
      if (!ApproveFlowUtil.isCanDel(vo)) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0056")/*@res "��Ʊ��ǰ״̬�²���ɾ��\r\n"*/);
      }
    }
    if (0 < buffer.length()) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }
}