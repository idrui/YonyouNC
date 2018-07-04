package nc.pubimpl.pu.m25.it;

import nc.bs.pu.m25.maintain.InvoiceSaveBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.it.ITServices;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubimpl.pu.m25.it.rule.InvoiceAutoSettleRuleForIT;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualInfoFillRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualVOCheckRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualVORelaCalcRule;
import nc.pubitf.pu.m25.it.IVirtualInvoiceMaintainForIT;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

public class VirtualInvoiceMaintainForITImpl implements
    IVirtualInvoiceMaintainForIT {

  @Override
  public InvoiceVO[] genByPurchsIn4IT(PurchaseInVO[] vos)
      throws BusinessException {
    try {
      // 累计开票数量置空
      for (PurchaseInVO vo : vos) {
        for (PurchaseInBodyVO item : vo.getBodys()) {
          item.setNsignnum(UFDouble.ZERO_DBL);
        }
      }
      return this.genInvoice(vos, ICBillType.PurchaseIn);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void addGenRule(AroundProcesser<InvoiceVO> processer,
      PurchaseInVO[] stockVos) {
    processer.addBeforeFinalRule(new VirtualVOCheckRule());// 检查生成的虚拟发票VO结构
    processer.addBeforeFinalRule(new VirtualInfoFillRule(stockVos));// 虚拟发票必要信息填充
    processer.addBeforeFinalRule(new VirtualVORelaCalcRule());// 以金额联动计算
    processer.addAfterFinalRule(new InvoiceAutoSettleRuleForIT());// 虚拟发票进行结算
    // 由结算来调用结算完后自动传应付，这里不用添加规则
  }

  private void fillTempVirInfoToStockVO(AbstractBill[] srcVos) {
    for (AbstractBill bill : srcVos) {
      bill.getParent().setAttributeValue(InvoiceHeaderVO.BVIRTUAL,
          UFBoolean.TRUE);
    }
  }

  private InvoiceVO[] genInvoice(PurchaseInVO[] srcVos, IBillType bt) {
    AroundProcesser<InvoiceVO> processer =
        new AroundProcesser<InvoiceVO>(InvoicePluginPoint.GEN_VIRTUAL_IT);
    this.addGenRule(processer, srcVos);
    // 调用VO对照生成发票数据
    InvoiceVO[] invoices = this.getInvcsByVoExchg(srcVos, bt);
    // 出库来源为进口入库无发票结算时生成发票 将发票归属置为进口发票
    this.processInvoiceForITin(srcVos, bt, invoices);
    processer.before(invoices);
    // 需要前台确认环境变量，但虚拟发票，一定不会超容差。
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    invoices = new InvoiceSaveBP(env).save(invoices, null, null);
    processer.after(invoices);
    return invoices;
  }

  private InvoiceVO[] getInvcsByVoExchg(PurchaseInVO[] srcVos, IBillType srcBt) {
    // 放置一个临时标志到上游VO中，用于屏蔽掉后期交换类中的处理
    this.fillTempVirInfoToStockVO(srcVos);
    // 使用下面的方法，运行VO交换，并从流程中找目的交易类型
    InvoiceVO[] invcs =
        PfServiceScmUtil.exeVOChangeByBizFlow(srcBt.getCode(),
            POBillType.Invoice.getCode(), srcVos);
    return invcs;
  }

  /**
   * mengjian
   * 1、出库来源为进口入库无发票结算时生成发票 将发票归属置为进口发票
   * 2、处理进口明细相关属性
   * 
   * @param srcVos
   * @param bt
   * @param invoices
   */
  private void processInvoiceForITin(PurchaseInVO[] srcVos, IBillType bt,
      InvoiceVO[] invoices) {

    if (bt.isEqual(ICBillType.PurchaseIn.getCode())) {
      if (invoices == null) {
        return;
      }
      for (InvoiceVO vo : invoices) {
        InvoiceHeaderVO headvo = (InvoiceHeaderVO) vo.getParent();
        headvo.setFinvoicetype(InvoicePuImportEnum.IMPORTINVOICE.toInteger());
      }
      ITServices.dealAttributeAfterVOChange(srcVos, invoices);
    }
  }

}
