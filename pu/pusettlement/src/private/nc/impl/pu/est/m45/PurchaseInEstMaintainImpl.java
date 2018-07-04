/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-21 上午09:02:52
 */
package nc.impl.pu.est.m45;

import nc.impl.pu.est.m45.action.PurchsInCancelEstAction;
import nc.impl.pu.est.m45.action.PurchsInFeeEstAction;
import nc.impl.pu.est.m45.action.PurchsInGoodsEstAction;
import nc.itf.pu.est.m45.IPurchaseInEstMaintain;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单暂估实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-21 上午09:02:52
 */
public class PurchaseInEstMaintainImpl implements IPurchaseInEstMaintain {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.est.m45.IPurchaseInEstMaintain#puchaseInUnEst(nc.vo.pu.est.entity.m45.PurchaseInEstVO[])
   */
  @Override
  public void puchaseInUnEst(PurchaseInEstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException {
    try {
      PurchsInCancelEstAction action = new PurchsInCancelEstAction();
      if (!onlyCancelFee.booleanValue()) {
        action.unEstimate(vos);
      }
      else {
        action.unFeeEstimate(vos);
      }
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.est.m45.IPurchaseInEstMaintain#purchaseInEst(nc.vo.pu.est.entity.m45.PurchaseInEstVO[])
   */
  @Override
  public void purchaseInEst(PurchaseInEstVO[] vos) throws BusinessException {
    try {
      new PurchsInGoodsEstAction().estimate(vos);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void purchaseInFeeEst(PurchaseInEstVO[] vos) throws BusinessException {
    try {
      new PurchsInFeeEstAction().estimate(vos);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
  }

}
