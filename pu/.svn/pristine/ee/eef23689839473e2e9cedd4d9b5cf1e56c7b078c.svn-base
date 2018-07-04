/**
 * 
 */
package nc.vo.pu.m25.rule.maintain.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *		单据号回退处理:调用公共代码处理
 * @scene
 * 		单据号回退处理:调用公共代码处理
 * @param
 * 
 * @since 6.5
 * @version 2015-11-9 下午4:16:40
 * @author wandl
 */
public class InvoiceCodeReturnRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (null != orgVos) {
      PUBillCodeUtils.getInvoiceCode().returnBillCode(orgVos);
    }

  }

}
