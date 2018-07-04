/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 下午06:29:49
 */
package nc.vo.pu.m25.rule.ap;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 可取消传应付的发票状态检查
 * @scene
 * 取消传应付
 * @param
 * ismanual 是否手工取消确认应付
 *
 * @since 6.3
 * @version 2014-10-22 下午2:58:15
 * @author zhangshqb
 */
public class CancelSendAPStateChkRule implements IRule<InvoiceVO> {

  private boolean ismanual;

  public CancelSendAPStateChkRule(boolean ismanual) {
    this.ismanual = ismanual;
  }

  @Override
  public void process(InvoiceVO[] vos) {
    if (!this.ismanual) {
      return;// 取消审批后配置的取消传应付，则不检查
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      int state = vo.getParentVO().getFbillstatus().intValue();
      if (POEnumBillStatus.APPROVE.toInt() != state) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0", "04004050-0108", null, new String[]{vo.getParentVO().getVbillcode()})/*[{0}]未审批通过的发票不可取消确认应付！*/);
      }
      UFBoolean sendflag = vo.getParentVO().getBapflag();
      if (!ValueUtils.getBoolean(sendflag)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0", "04004050-0109", null, new String[]{vo.getParentVO().getVbillcode()})/*[{0}]未确认过应付不可取消确认!*/);
      }
      if (UFBoolean.TRUE.equals(vo.getParentVO().getBvirtual())) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0", "04004050-0110", null, new String[]{vo.getParentVO().getVbillcode()})/*[{0}]虚拟发票不可取消确认应付！*/);
      }
    }
  }

}
