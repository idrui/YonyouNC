/**
 *
 */
package nc.vo.pu.m25.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * ��Ʊ״̬���,������������̬
 * @scene
 * �ջ�
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:52:48
 * @author zhangshqb
 */
public class UnSendApproveStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      buffer.append(this.checkHeaderVO(vo.getParentVO()));
    }
    if (buffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  private StringBuffer checkHeaderVO(InvoiceHeaderVO headerVO) {
    StringBuffer buffer = new StringBuffer();
    // ���ݹ�������2011.6.25
    // ������״̬��������Ϊ�յĵ��ݿ����ջ�
    if (POEnumBillStatus.APPROVING.toInt() != headerVO.getFbillstatus()
        .intValue()
        || StringUtils.isNotBlank(headerVO.getApprover())) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0055")/*@res "ֻ��������δ���������ķ�Ʊ�����ջأ�"*/);
    }
    return buffer;
  }

}