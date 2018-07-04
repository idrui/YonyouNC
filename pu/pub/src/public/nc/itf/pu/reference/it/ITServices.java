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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������ò�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-9-30 ����10:53:52
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
   * �������->���ڷ�Ʊ ���ݽ�������
   * �����ڷ�Ʊ.�еĽ�����ϸ������/������ϸ��������/������ϸ����/���ں�ͬ��
   * [pk_order_b][pk_order][vordercode][vctcode]
   * 
   * @param purchaseinVOs �������
   * @param invoices ���ڷ�Ʊ
   * @throws BusinessException
   */
  public static void dealAttributeAfterVOChange(PurchaseInVO[] purchaseinVOs,
      InvoiceVO[] invoices) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return;
    }
    // ���ý��ڽӿڴ��� vo��������Ҫ������ֶΣ�������ϸ�������)
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
   * �������->������⸱�� ���ݽ�������
   * ���ɹ�������.�еĽ�����ϸ������/������ϸ��������/������ϸ����/���ں�ͬ��
   * [pk_order_b][pk_order][vordercode][vctcode]
   * 
   * @param purchaseinVOs �������
   * @param purchInFIVOs ������⸱��
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
