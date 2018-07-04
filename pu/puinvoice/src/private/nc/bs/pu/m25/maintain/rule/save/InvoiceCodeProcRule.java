/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 单据号处理:调用公共代码处理
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:20:48
 * @author zhangshqb
 */
public class InvoiceCodeProcRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (null == orgVos) {// 新增保存
      this.handleInsertVO(vos);
    }
    else {// 修改保存
      this.handleUpdateVO(vos, orgVos);
    }
  }

  private void handleInsertVO(InvoiceVO[] vos) {
    PUBillCodeUtils.getInvoiceCode().createBillCode(vos);
  }

  private void handleUpdateVO(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    PUBillCodeUtils.getInvoiceCode().upadteBillCode(vos, orgVos);
  }

}
