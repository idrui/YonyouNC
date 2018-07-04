/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 单据号唯一性校验
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:34:58
 * @author zhangshqb
 */

public class InvoiceCodeUniqueCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    // 单据号唯一性校验
    PUBillCodeUtils.getInvoiceCode().checkUnique(vos);
  }

}
