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
 * 检查下游:已经参与结算，不能弃审；已经传应付，且流程中未配自动消息传应，不能弃审
 * @scene
 * 弃审
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:50:02
 * @author zhangshqb
 */
public class SettledCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        UFDouble accsettlemny = MathTool.nvl(item.getNaccumsettmny());
        if (!MathTool.isZero(accsettlemny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0033")/*@res "采购发票已经参与结算，不能取消审批！"*/);
        }
      }
    }
  }

}