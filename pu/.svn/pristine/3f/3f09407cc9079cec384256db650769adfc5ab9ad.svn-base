/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 上午10:53:52
 */
package nc.impl.pu.m25;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m25.maintain.InvoiceFreezeBP;
import nc.bs.pu.m25.maintain.InvoiceUnFreezeBP;
import nc.impl.pu.m25.action.InvoiceDeleteAction;
import nc.impl.pu.m25.action.InvoiceInsertAction;
import nc.impl.pu.m25.action.InvoiceUpdateAction;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m25.IInvoiceMaintain;
import nc.itf.pu.reference.it.ITServices;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票维护操作实现组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 上午10:53:52
 */
public class InvoiceMaintainImpl implements IInvoiceMaintain {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IInvoiceMaintain#delete(nc.vo.pu.m25.entity.InvoiceVO[])
   */
  @Override
  public void delete(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException {
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    try {
      if (InvoiceVOUtil.isPuInvoice(vos)) {
        new InvoiceDeleteAction().delete(vos, envs);
      }
      else {
        ITServices.delete(vos, envs);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public InvoiceVO[] freeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException {
    try {
      return new InvoiceFreezeBP().freeze(clientVos, origVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] freezeByLightVOs(InvoiceVO[] vos) throws BusinessException {
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    InvoiceVO[] clientVos = tool.getClientFullInfoBill();
    InvoiceVO[] retVos =
        NCLocator.getInstance().lookup(IInvoiceMaintain.class)
            .freeze(clientVos, tool.getOriginBills());
    return tool.getBillForToClient(retVos);
  }

  @Override
  public InvoiceVO[] insert(InvoiceVO[] insertVos, InvoiceUIToBSEnv env)
      throws BusinessException {
    try {
      return new InvoiceInsertAction().insert(insertVos, env);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m25.IInvoiceMaintain#save(nc.vo.pu.m25.entity.InvoiceVO[])
   */
  @Override
  public InvoiceVO[] save(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException {
    try {
      // add by liangchen1 NC631需求，区分普通采购与进出口采购
      if (InvoiceVOUtil.isPuInvoice(vos)) {
        InvoiceUIToBSEnv env =
            ArrayUtils.isEmpty(envs) ? new InvoiceUIToBSEnv() : envs[0];
        InvoiceVO[] insertVos = (InvoiceVO[]) AggVOUtil.pickupInsertVO(vos);
        InvoiceVO[] updateVos = this.pickupUpdateVO(vos);
        if (!ArrayUtils.isEmpty(insertVos)) {
          return NCLocator.getInstance().lookup(IInvoiceMaintain.class)
              .insert(insertVos, env);
        }
        if (!ArrayUtils.isEmpty(updateVos)) {
          return NCLocator.getInstance().lookup(IInvoiceMaintain.class)
              .update(updateVos, env);
        }
      }
      else {
        return ITServices.save(vos, envs);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] unFreeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException {
    try {
      return new InvoiceUnFreezeBP().unFreeze(clientVos, origVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] unFreezeByLightVOs(InvoiceVO[] vos)
      throws BusinessException {
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    InvoiceVO[] clientVos = tool.getClientFullInfoBill();
    InvoiceVO[] retVos =
        NCLocator.getInstance().lookup(IInvoiceMaintain.class)
            .unFreeze(clientVos, tool.getOriginBills());
    return tool.getBillForToClient(retVos);
  }

  @Override
  public InvoiceVO[] update(InvoiceVO[] updateVos, InvoiceUIToBSEnv env)
      throws BusinessException {
    try {
      return new InvoiceUpdateAction().update(updateVos, env);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private InvoiceVO[] pickupUpdateVO(InvoiceVO[] vos) {
    List<InvoiceVO> newVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      if (StringUtil.isEmptyWithTrim(vo.getParentVO().getPk_invoice())) {
        continue;
      }
      newVos.add(vo);
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<InvoiceVO>().convertToArray(newVos);
  }

}
