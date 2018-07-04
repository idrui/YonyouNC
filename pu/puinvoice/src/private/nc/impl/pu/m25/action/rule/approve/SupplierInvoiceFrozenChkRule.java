/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午03:32:57
 */
package nc.impl.pu.m25.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.finance.SupFinanceVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 供应商开票冻结检查
 * @scene
 * 审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午2:41:38
 * @author zhangshqb
 */
public class SupplierInvoiceFrozenChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      boolean negInvoice = true;
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (0 < MathTool.compareTo(item.getNorigmny(), UFDouble.ZERO_DBL)) {
          negInvoice = false;
          break;
        }
      }
      // 负发票不受供应商开票冻结影响
      if (negInvoice) {
        continue;
      }
      String pk_supplier = vo.getParentVO().getPk_supplier();
      String pk_org = vo.getParentVO().getPk_org();
      SupFinanceVO[] sfVos = null;
      sfVos = SupplierPubService.getSupFinanceVO(new String[] {
        pk_supplier
      }, pk_org, new String[] {
        SupFinanceVO.MAKEOUTFREEZEFLAG
      });
      if (sfVos == null || sfVos.length == 0) {
        continue;
      }
      if (ValueUtils.getBoolean(sfVos[0].getMakeoutfreezeflag())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0028", null,
                new String[] {
                  vo.getParentVO().getVbillcode()
                })/*
                   * @res
                   * "单据{0}的供应商已经开票冻结，不能再审批！"
                   */);
      }
    }
  }

}
