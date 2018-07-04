/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午02:06:09
 */
package nc.pubimpl.pu.m21.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.pubitf.pu.m21.so.m30.ICoopOrderPushSaveFor30;

import nc.impl.pubapp.env.BSContext;

import nc.pubimpl.pu.m21.action.so.m30.CoopOrderPushSaveFor30Action;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单生成协同采购订单实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午02:06:09
 */
public class CoopOrderPushSaveFor30Impl implements ICoopOrderPushSaveFor30 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m21.so.m30.ICoopOrderPushSaveFor30#push30To21Coop(nc.vo.so.msaleorder.entity.SaleOrderVO[])
   */
  @Override
  public void coopOrderPushSaveFor30(SaleOrderVO[] srcVOs,
      Map<String, Object> result) throws BusinessException {
    try {
      BSContext.getInstance().setSession("AskOverPresentStockCheck",
          result.get("AskOverPresentStockCheck"));
      new CoopOrderPushSaveFor30Action().coopOrderPushSaveFor30(srcVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
