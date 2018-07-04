/**
 *
 */
package nc.impl.pu.m25.action.rule.maitain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-27 上午11:10:35
 */
public class InvoiceDelClassChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0029")/*@res "表头不能为空\r\n"*/);
        continue;
      }
      if (headerVO.getBvirtual().booleanValue()) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0030")/*@res "虚拟发票不能删除\r\n"*/);
      }
    }
    ExceptionUtils.wrappBusinessException(buffer.toString());
  }

}