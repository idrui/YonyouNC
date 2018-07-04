/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午03:23:57
 */
package nc.impl.pu.m21;

import nc.impl.pu.m21.onway.bp.ConfirmDeleteBp;
import nc.impl.pu.m21.onway.bp.ConfirmUpdateBp;
import nc.impl.pu.m21.onway.bp.OnwayDeleteBpMy;
import nc.impl.pu.m21.onway.bp.OnwayUpdateBpMy;
import nc.impl.pu.m21.onway.bp.OutputDeleteBp;
import nc.impl.pu.m21.onway.bp.OutputUpdateBp;
import nc.itf.pu.m21.IStatusMaintain;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单状态维护
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-1 下午03:23:57
 */
public class StatusMaintainImpl implements IStatusMaintain {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#deleteForOutput(nc.vo.pu.m21.entity.OrderVO[],
   *      int)
   */
  @Override
  public void deleteForConfirm(OrderVO[] newVOs) throws BusinessException {
    try {
      ConfirmDeleteBp deleteBp = new ConfirmDeleteBp();
      deleteBp.deleteConfirm(newVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#deleteForOutput(nc.vo.pu.m21.entity.OrderVO[],
   *      int)
   */
  @Override
  public void deleteForOutput(OrderVO[] newVOs) throws BusinessException {
    try {
      OutputDeleteBp updateBp = new OutputDeleteBp();
      updateBp.deletOutput(newVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#deleteOnway(nc.vo.pu.m21.entity.OrderOnwayVO[])
   */
  @Override
  public void deleteOnway(OrderOnwayVO[] newVOs, int status)
      throws BusinessException {

    try {
      OnwayDeleteBpMy onwayBp = new OnwayDeleteBpMy();
      onwayBp.deleteOnway(newVOs, status);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#updateForConfirm(nc.vo.pu.m21.entity.OrderVO[],
   *      int)
   */
  @Override
  public OrderVO[] updateForConfirm(OrderVO[] newVOs) throws BusinessException {
    try {
      // BillTransferTool<OrderVO> transferTool =
      // new BillTransferTool<OrderVO>(newVOs);
      // BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
      // OrderVO[] oldVOs = transferTool.getOriginBills();
      // billUpdate.update(newVOs, oldVOs);
      ConfirmUpdateBp updateBp = new ConfirmUpdateBp();
      updateBp.updateConfirm(newVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#update(nc.vo.pu.m21.entity.OrderVO[])
   */
  @Override
  public OrderVO[] updateForOutput(OrderVO[] newVOs) throws BusinessException {
    try {
      // BillTransferTool<OrderVO> transferTool =
      // new BillTransferTool<OrderVO>(newVOs);
      // BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
      // OrderVO[] oldVOs = transferTool.getOriginBills();
      // billUpdate.update(newVOs, oldVOs);
      OutputUpdateBp updateBp = new OutputUpdateBp();
      updateBp.updateOutput(newVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IStatusMaintain#updateOnway(nc.vo.pu.m21.entity.OrderOnwayVO[])
   */
  @Override
  public StatusOnWayItemVO[] updateOnway(OrderOnwayVO[] newVOs, int status)
      throws BusinessException {
    try {
      OnwayUpdateBpMy onwayBp = new OnwayUpdateBpMy();
      StatusOnWayItemVO[] returnVO = onwayBp.updateOnway(newVOs, status);

      return returnVO;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
