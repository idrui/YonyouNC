/**
 *
 */
package nc.vo.pu.m25.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *	��Ʊ״̬���,��Ʊ״̬��飬�����Ƿ�����̬�������־=N����ǰ��̨�����ⷢƱ������ˣ�
 * @scene
 * ����
 * @param
 * 
 *
 * @since 6.3
 * @version 2014-10-22 ����2:39:27
 * @author zhangshqb
 */
public class ApproveStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      buffer.append(this.checkStatus(vo));
    }
    if (buffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * ���������Ƿ�����ԭ����˰������0�ļ�¼ ����״̬�ҷ�Ʊ��ԭ�ҽ��>0ʱ�������
   *
   * @author xiebo
   * @time 2010-1-27 ����11:49:23
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkNorigmnys(InvoiceItemVO[] itemVOs) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceItemVO itemVO : itemVOs) {
      if (MathTool.compareTo(itemVO.getNorigmny(), UFDouble.ZERO_DBL) > 0) {
        return buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0050")/*@res "����״̬��������Ʊ����������"*/);
      }
    }
    return buffer;
  }

  /**
   * ��ⷢƱ״̬
   *
   * @author xiebo
   * @time 2010-1-27 ����11:59:36
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkStatus(InvoiceVO vo) {
    StringBuffer buffer = new StringBuffer();
    InvoiceHeaderVO headerVO = vo.getParentVO();
    if (null == headerVO) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0029")/*@res "��ͷ����Ϊ��\r\n"*/);
    }
    else {
      if (POEnumBillStatus.APPROVE.toInt() == headerVO.getFbillstatus()
          .intValue()) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0051")/*@res "��Ʊ�Ѿ�����������������"*/);
      }
      if (ValueUtils.getBoolean(headerVO.getBvirtual())) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0052")/*@res "���ⷢƱ�����ֹ�����\r\n"*/);
      }
      if (headerVO.getBfrozen() != null && headerVO.getBfrozen().booleanValue()) {
        buffer.append(this.checkNorigmnys(vo.getChildrenVO()));
      }
    }
    return buffer;
  }

}