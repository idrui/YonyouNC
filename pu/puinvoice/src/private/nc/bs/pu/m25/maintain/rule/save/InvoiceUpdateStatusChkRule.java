/**
 *
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ״̬���:���ⷢƱ�����޸�,����ͨ���ķ�Ʊ�����޸�(�ڲ�֧���޶��������)</li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 ����06:01:01
 */
public class InvoiceUpdateStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer errorBuffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0024")/*@res "��ͷ����Ϊ��"*/);
        continue;
      }
      if (headerVO.getBvirtual().booleanValue()) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0025")/*@res "���ⷢƱ�����޸�"*/);
      }
      if (POEnumBillStatus.APPROVE.toInt() == headerVO.getFbillstatus()
          .intValue()) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0026")/*@res "����ͨ���ķ�Ʊ�����޸�"*/);
      }
    }
    if (errorBuffer.length() > 0) {
      // �׳�������Ϣ
      ExceptionUtils.wrappBusinessException(errorBuffer.toString());
    }
  }

}