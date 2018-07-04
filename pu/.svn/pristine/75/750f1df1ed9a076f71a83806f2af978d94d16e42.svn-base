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
 *            采购发票的弃审,是否驱动了采购发票取消付应付
 * @scene
 *      采购发票的弃审
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-4-28 下午04:37:14
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
                                                                     * "发票已经传应付不能取消审批，请取消传应付后重试！"
                                                                     */);
      }
    }
  }

}
