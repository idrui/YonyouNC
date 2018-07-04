package nc.vo.pu.m25.rule.unapprove;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.ref.InvoiceUapRefer;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �ɹ���Ʊ������,�Ƿ������˲ɹ���Ʊȡ����Ӧ��
 * @scene
 *      �ɹ���Ʊ������
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-4-28 ����04:37:14
 * @author zhaoyha
 */
public class CancelSendAPDriveChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      if (!ValueUtils.getBoolean(vo.getParentVO().getBapflag())) {
        continue;
      }
      String pk_busitype = vo.getParentVO().getPk_busitype();
      if (!InvoiceUapRefer.hasCancelSendAPDriveAction(pk_busitype, BSContext
          .getInstance().getUserID())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0032")/*
                                                                     * @res
                                                                     * "��Ʊ�Ѿ���Ӧ������ȡ����������ȡ����Ӧ�������ԣ�"
                                                                     */);
      }
    }
  }

}
