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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票状态检查:虚拟发票不能修改,审批通过的发票不能修改(在不支持修订的情况下)</li>
 * </ul>
 * <p>
 * </p>
 *
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午06:01:01
 */
public class InvoiceUpdateStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer errorBuffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0024")/*@res "表头不能为空"*/);
        continue;
      }
      if (headerVO.getBvirtual().booleanValue()) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0025")/*@res "虚拟发票不能修改"*/);
      }
      if (POEnumBillStatus.APPROVE.toInt() == headerVO.getFbillstatus()
          .intValue()) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0026")/*@res "审批通过的发票不能修改"*/);
      }
    }
    if (errorBuffer.length() > 0) {
      // 抛出错误信息
      ExceptionUtils.wrappBusinessException(errorBuffer.toString());
    }
  }

}