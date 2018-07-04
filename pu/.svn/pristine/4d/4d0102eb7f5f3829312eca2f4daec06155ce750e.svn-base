/**
 * 
 */
package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *            采购发票单据号唯一性校验
 * @scene
 *      采购发票保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2011-1-21 下午02:25:43
 * @author 田锋涛
 */
public class InvoiceCodeUniqueCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    // 单据号唯一性校验
    PUBillCodeUtils.getInvoiceCode().checkUnique(vos);
  }

}
