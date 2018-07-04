/**
 * 
 */
package nc.bs.pu.m25.ap.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 更新发票传应付信息
 * @scene
 * 传应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:41:39
 * @author zhangshqb
 */
public class InvcAPInfoUpdateRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setBapflag(UFBoolean.TRUE);
    }
  }
}
