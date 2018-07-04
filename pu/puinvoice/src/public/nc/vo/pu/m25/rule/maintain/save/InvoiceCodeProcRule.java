package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *            采购发票保存时，对单据号的处理（调用公共代码处理）
 * @scene
 *      采购发票保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2010-1-26 下午06:03:51
 * @author xiebo
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
