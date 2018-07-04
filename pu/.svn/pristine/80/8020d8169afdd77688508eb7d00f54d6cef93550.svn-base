/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 下午02:44:18
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 * 确定业务流程
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:26:43
 * @author zhangshqb
 */
public class ConfirmInvoiceBiztypeRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    // if(null==vos || orgVos!=null) return;//新增保存才匹配，修改保存不匹配
    PfServiceScmUtil.setBusiType(vos, POBillType.Invoice.getCode());

  }

}
