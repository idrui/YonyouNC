/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 ����01:58:32
 */
package nc.bs.pu.m25.ap.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.ref.InvoiceUapRefer;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ��鷢Ʊ��Ӧ���ű��Ƿ������˴�Ӧ������
 * @scene
 * ��Ӧ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:40:34
 * @author zhangshqb
 */
public class SendAPBusitypeCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_busitype = vos[0].getParentVO().getPk_busitype();
    if (!InvoiceUapRefer.hasSendAPDriveAction(pk_busitype, BSContext
        .getInstance().getUserID())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0018")/*
                                                                   * @res
                                                                   * "��Ʊ��Ӧ�����δ��������Ӧ��������������ܴ�Ӧ����"
                                                                   */);
    }
  }

}
