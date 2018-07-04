/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 ����02:30:21
 */
package nc.pubimpl.pu.m21.mm.m55a2;

import nc.pubimpl.pu.m21.action.mm.m55a2.OrderPushSaveFor55A2Action;
import nc.pubitf.pu.m21.mm.m55a2.IOrderPushSaveFor55A2;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������-����������ʽ����ɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 ����02:30:21
 */
public class OrderPushSaveFor55A2Impl implements IOrderPushSaveFor55A2 {

  @Override
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException {
    try {
      return new OrderPushSaveFor55A2Action().pushSave(orderVOs,
          SourceReturnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
