package nc.pubimpl.pu.m25.pu.settle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m25.maintain.InvoiceDeleteBP;
import nc.bs.pu.m25.maintain.InvoiceSaveBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualCancelSendAPRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualDelClearInfoRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualInfoFillRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualVOCheckRule;
import nc.pubimpl.pu.m25.pu.settle.rule.VirtualVORelaCalcRule;
import nc.pubitf.pu.m25.pu.settle.IVirtualInvoiceMaintain;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.m47.entity.SubcontInBodyVO;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.InvoiceAutoSettleRule;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购无发票结算虚拟发票的维护
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 上午10:52:13
 */
public class VirtualInvoiceMaintainImpl implements IVirtualInvoiceMaintain {

  @Override
  public void delete(String[] ids) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(ids)) {
        return;
      }
      Set<String> ssSet = new HashSet<String>(Arrays.asList(ids));
      String[] IDs = ssSet.toArray(new String[ssSet.size()]);
      InvoiceVO[] vos = new BillQuery<InvoiceVO>(InvoiceVO.class).query(IDs);
      AroundProcesser<InvoiceVO> processer =
          new AroundProcesser<InvoiceVO>(InvoicePluginPoint.DEL_VIRTUAL);
      this.addDeleteRule(processer);
      processer.before(vos);
      // 需要前台确认环境变量，但虚拟发票，一定不会超容差。
      InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
      new InvoiceDeleteBP(env).delete(vos, null, null);
      processer.after(vos);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public InvoiceVO[] genByInitEst(InitialEstVO[] vos) throws BusinessException {
    try {
      // 累计开票数量置空
      for (InitialEstVO vo : vos) {
        for (InitialEstItemVO item : vo.getItems()) {
          item.setNaccinvoicenum(UFDouble.ZERO_DBL);
        }
      }
      return this.genInvoice(vos, POBillType.InitEstimate);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] genByPurchsIn(PurchaseInVO[] vos) throws BusinessException {
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

  @Override
  public InvoiceVO[] genBySubcontIn(SubcontInVO[] vos) throws BusinessException {
    try {
      // 累计开票数量置空
      for (SubcontInVO vo : vos) {
        for (SubcontInBodyVO item : vo.getBodys()) {
          item.setNsignnum(UFDouble.ZERO_DBL);
        }
      }
      return this.genInvoice(vos, ICBillType.SubContinIn);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void addDeleteRule(AroundProcesser<InvoiceVO> processer) {
    // 取消传应付-前-final
    processer.addBeforeFinalRule(new VirtualCancelSendAPRule());
    // 清除审批信息-前
    processer.addBeforeRule(new VirtualDelClearInfoRule());
  }

  private void addGenRule(AroundProcesser<InvoiceVO> processer, IBill[] stockVos) {
    processer.addBeforeFinalRule(new VirtualVOCheckRule());// 检查生成的虚拟发票VO结构
    processer.addBeforeFinalRule(new VirtualInfoFillRule(stockVos));// 虚拟发票必要信息填充
    processer.addBeforeFinalRule(new VirtualVORelaCalcRule());// 以金额联动计算
    processer.addAfterFinalRule(new InvoiceAutoSettleRule());// 虚拟发票进行结算
    // 由结算来调用结算完后自动传应付，这里不用添加规则
  }

  private void fillTempVirInfoToStockVO(AbstractBill[] srcVos) {
    for (AbstractBill bill : srcVos) {
      bill.getParent().setAttributeValue(InvoiceHeaderVO.BVIRTUAL,
          UFBoolean.TRUE);
    }
  }

  private InvoiceVO[] genInvoice(AbstractBill[] srcVos, IBillType bt) {
    AroundProcesser<InvoiceVO> processer =
        new AroundProcesser<InvoiceVO>(InvoicePluginPoint.GEN_VIRTUAL);
    this.addGenRule(processer, srcVos);
    // 调用VO对照生成发票数据
    InvoiceVO[] invoices = this.getInvcsByVoExchg(srcVos, bt);
    this.setFinvoiceType(invoices);
    processer.before(invoices);
    // 需要前台确认环境变量。由于无发票结算允许前台修改数量、金额，所以可能超容差了
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    // 用户交互信息， nc.impl.pu.m27.SettleMatchImpl.withoutInvoiceMatch设置
    SettleEnvironment withoutInvoiceMatchEnv =
        (SettleEnvironment) BSContext.getInstance().getSession(
            "withoutInvoiceMatchEnv");
    if (withoutInvoiceMatchEnv.isInvoicePriceOverOder()) {
      env.setOverPOPrice(InvoiceUserAnswerType.YES);
    }
    invoices = new InvoiceSaveBP(env).save(invoices, null, null);
    processer.after(invoices);
    return invoices;
  }

  private InvoiceVO[] getInvcsByVoExchg(AbstractBill[] srcVos, IBillType srcBt) {
    // 放置一个临时标志到上游VO中，用于屏蔽掉后期交换类中的处理
    this.fillTempVirInfoToStockVO(srcVos);
    // 使用下面的方法，运行VO交换，并从流程中找目的交易类型
    InvoiceVO[] invcs =
        PfServiceScmUtil.exeVOChangeByBizFlow(srcBt.getCode(),
            POBillType.Invoice.getCode(), srcVos);
    return invcs;
  }

  private void setFinvoiceType(InvoiceVO[] invoices) {
    if (invoices != null) {
      for (InvoiceVO invoiceVO : invoices) {
        invoiceVO.getParentVO().setFinvoicetype(
            InvoicePuImportEnum.PUINVOICE.toInteger());
      }
    }
  }
}
