package nc.itf.pu.reference.it;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.it.est.importin.IImportInServiceForPu;
import nc.pubitf.it.ic.IITInvoiceServiceForIC;
import nc.pubitf.it.pu.IITInvoiceMaintainAppForpu;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>进出口引用层
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-9-30 上午10:53:52
 */

public class ITServices {

  public static InvoiceVO[] approve(InvoiceVO[] vos, AbstractCompiler2 script,
      InvoiceUIToBSEnv[] envs) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return null;
    }
    try {
      IITInvoiceMaintainAppForpu service =
          NCLocator.getInstance().lookup(IITInvoiceMaintainAppForpu.class);
      return service.approve(vos, script, envs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 进口入库->进口发票 数据交换后处理
   * 填充进口发票.行的进口明细单主键/进口明细单行主键/进口明细单号/进口合同号
   * [pk_order_b][pk_order][vordercode][vctcode]
   * 
   * @param purchaseinVOs 进口入库
   * @param invoices 进口发票
   * @throws BusinessException
   */
  public static void dealAttributeAfterVOChange(PurchaseInVO[] purchaseinVOs,
      InvoiceVO[] invoices) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return;
    }
    // 调用进口接口处理 vo交换后需要处理的字段（进口明细相关属性)
    IITInvoiceServiceForIC serviceIT =
        NCLocator.getInstance().lookup(IITInvoiceServiceForIC.class);
    try {
      serviceIT.dealAttributeAfterVOChange(purchaseinVOs, invoices);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 进口入库->进口入库副本 数据交换后处理
   * 填充采购入库财务.行的进口明细单主键/进口明细单行主键/进口明细单号/进口合同号
   * [pk_order_b][pk_order][vordercode][vctcode]
   * 
   * @param purchaseinVOs 进口入库
   * @param purchInFIVOs 进口入库副本
   * @throws BusinessException
   */
  public static void dealAttributeAfterVOChange(PurchaseInVO[] purchaseinVOs,
      PurchaseinFIVO[] purchInFIVOs) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return;
    }
    IImportInServiceForPu service =
        NCLocator.getInstance().lookup(IImportInServiceForPu.class);
    try {
      service.dealAttributeAfterVOChange(purchaseinVOs, purchInFIVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static void delete(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return;
    }
    try {
      IITInvoiceMaintainAppForpu service =
          NCLocator.getInstance().lookup(IITInvoiceMaintainAppForpu.class);
      service.delete(vos, envs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static InvoiceVO[] save(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return null;
    }
    try {
      IITInvoiceMaintainAppForpu service =
          NCLocator.getInstance().lookup(IITInvoiceMaintainAppForpu.class);
      return service.saveITInvoice(vos, envs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static InvoiceVO[] unapprove(InvoiceVO[] vos, AbstractCompiler2 script) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return null;
    }
    try {
      IITInvoiceMaintainAppForpu service =
          NCLocator.getInstance().lookup(IITInvoiceMaintainAppForpu.class);
      return service.unapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
