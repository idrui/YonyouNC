package nc.vo.pu.m25.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            采购发票取消审批时，如果已参与结算或传应付，不能弃审
 * @scene
 *      采购发票取消审批
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-1-26 下午05:05:31
 * @author xiebo
 */
public class SettledCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        UFDouble accsettlemny = MathTool.nvl(item.getNaccumsettmny());
        if (!MathTool.isZero(accsettlemny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004050_0", "04004050-0033")/*
                                                                       * @res
                                                                       * "采购发票已经参与结算，不能取消审批！"
                                                                       */);
        }
      }
    }
  }

}
