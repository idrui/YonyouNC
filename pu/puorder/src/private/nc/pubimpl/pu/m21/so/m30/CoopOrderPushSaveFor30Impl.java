/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����02:06:09
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������Эͬ�ɹ�����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����02:06:09
 */
public class CoopOrderPushSaveFor30Impl implements ICoopOrderPushSaveFor30 {

  /**
   * ���෽����д
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
