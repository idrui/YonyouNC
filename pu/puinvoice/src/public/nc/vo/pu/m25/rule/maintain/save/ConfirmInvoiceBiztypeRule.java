/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 下午02:44:18
 */
package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 *            采购发票保存时，设置对应的业务流程
 * @scene
 *      采购发票保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-1-29 下午02:44:18
 * @author zhaoyha
 */
public class ConfirmInvoiceBiztypeRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    // if(null==vos || orgVos!=null) return;//新增保存才匹配，修改保存不匹配
    PfServiceScmUtil.setBusiType(vos, POBillType.Invoice.getCode());

  }

}
