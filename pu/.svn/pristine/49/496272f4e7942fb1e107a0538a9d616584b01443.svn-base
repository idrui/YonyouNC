/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午02:36:12
 */
package nc.impl.pu.m25;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m25.action.InvoiceApproveAction;
import nc.impl.pu.m25.action.InvoiceSendApproveAction;
import nc.impl.pu.m25.action.InvoiceUnApproveAction;
import nc.impl.pu.m25.action.InvoiceUnSendApproveAction;
import nc.itf.pu.m25.IInvoiceApprove;
import nc.itf.pu.reference.it.ITServices;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票审批操作实现组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-26 下午02:36:12
 */
public class InvoiceApproveImpl implements IInvoiceApprove {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IInvoiceApprove#approve(nc.vo.pu.m25.entity.InvoiceVO[])
   */
  @Override
  public InvoiceVO[] approve(InvoiceVO[] vos, AbstractCompiler2 script,
      InvoiceUIToBSEnv[] envs) throws BusinessException {
    try {
      // add by liangchen1 NC631需求，区分普通采购与进出口采购
      if (InvoiceVOUtil.isPuInvoice(vos)) {
        return new InvoiceApproveAction().approve(vos, script, envs);
      }
      else {
        return ITServices.approve(vos, script, envs);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] sendapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new InvoiceSendApproveAction().sendapprove(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IInvoiceApprove#unapprove(nc.vo.pu.m25.entity.InvoiceVO[])
   */
  @Override
  public InvoiceVO[] unapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      // add by liangchen1 NC631需求，区分普通采购与进出口采购
      if (InvoiceVOUtil.isPuInvoice(vos)) {
        return new InvoiceUnApproveAction().unapprove(vos, script);
      }
      else {
        return ITServices.unapprove(vos, script);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IInvoiceApprove#unSendapprove(nc.vo.pu.m25.entity.InvoiceVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public InvoiceVO[] unSendapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new InvoiceUnSendApproveAction().unSendApprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
