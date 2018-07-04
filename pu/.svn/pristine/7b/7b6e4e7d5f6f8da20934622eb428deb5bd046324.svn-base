package nc.vo.pu.m25.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceOrgVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * vo交换处理，包括交换前和交换后的处理
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */

public class ChangeVOAdjustor4TTo25 extends InvoiceChangeVOAdjustor {

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 交换前处理
    InitialEstVO[] sourceVOs =
        (InitialEstVO[]) ArrayUtil.convertArrayType(srcVOs);
    for (InitialEstVO vo : sourceVOs) {
      // 这里再做补充是考虑采购结算时有自己调用vo交换的情况
      for (InitialEstItemVO item : vo.getItems()) {
        if (MathTool.isZero(item.getNcaninvoicenum())) {
          item.setNcaninvoicenum(MathTool.sub(item.getNnum(),
              item.getNaccinvoicenum()));
        }
      }
    }
    return sourceVOs;
  }

  @Override
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(retvos);
    InvoiceSupplierVatCodeFillRule supvatrule =
        new InvoiceSupplierVatCodeFillRule(bills);
    InvoiceOrgVatCodeFillRule orgvatrule = new InvoiceOrgVatCodeFillRule(bills);
    supvatrule.prepare();
    orgvatrule.prepare();
    supvatrule.process();
    orgvatrule.process();
  }
}
