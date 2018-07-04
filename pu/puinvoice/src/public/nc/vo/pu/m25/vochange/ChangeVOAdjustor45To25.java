package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceOrgVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */

public class ChangeVOAdjustor45To25 extends InvoiceChangeVOAdjustor {

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 交换前处理
    PurchaseInVO[] sourceVOs =
        (PurchaseInVO[]) ArrayUtil.convertArrayType(srcVOs);
    for (PurchaseInVO vo : sourceVOs) {
      // 对于明确的推式保存及拉单，IC已经补充了可开票数量
      // 这里再做补充是考虑采购结算时有自己调用vo交换的情况
      for (PurchaseInBodyVO item : vo.getBodys()) {
        if (MathTool.isZero(item.getNinvoicenum())) {
          item.setNinvoicenum(MathTool.sub(item.getNnum(), item.getNsignnum()));
        }
      }
    }
    PurchaseInVO[] sourceVOss = this.filterFlargessRow(sourceVOs);
    if (sourceVOss == null || sourceVOss.length < 1) {
      ExceptionUtils.marsh(new BusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0134")/*
                                                                   * 全部赠品行的单据不支持一推到底流程
                                                                   * ！请检查！
                                                                   */));
      return null;
    }
    return sourceVOss;
  }

  /**
   * 过滤表体赠品行。若单据表体全为赠品行，则不处理。
   * 
   * @param signvos
   * @return
   */
  private PurchaseInVO[] filterFlargessRow(PurchaseInVO[] signvos) {
    if (signvos == null || signvos.length == 0) {
      return signvos;
    }
    List<PurchaseInVO> accBills = new ArrayList<PurchaseInVO>();
    for (PurchaseInVO bill : signvos) {

      PurchaseInBodyVO[] bodys = bill.getBodys();
      List<PurchaseInBodyVO> accBodys = new ArrayList<PurchaseInBodyVO>();
      for (PurchaseInBodyVO body : bodys) {
        if (UFBoolean.FALSE.equals(body.getFlargess())) {
          accBodys.add(body);
        }
      }
      if (accBodys.size() > 0) {
        if (accBodys.size() < bodys.length) {
          bill.setChildrenVO(accBodys.toArray(new PurchaseInBodyVO[accBodys
              .size()]));
        }
        accBills.add(bill);
      }
    }

    return accBills.toArray(new PurchaseInVO[accBills.size()]);
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
