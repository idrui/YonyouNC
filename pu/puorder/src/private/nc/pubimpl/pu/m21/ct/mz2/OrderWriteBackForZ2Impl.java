/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 ����02:06:05
 */
package nc.pubimpl.pu.m21.ct.mz2;

import nc.pubimpl.pu.m21.action.ct.mz2.OrderWriteBackForZ2Action;
import nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackForZ2;
import nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackParaForZ2;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͬ��дʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 ����02:06:05
 */
public class OrderWriteBackForZ2Impl implements IOrderWriteBackForZ2 {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackForZ2#writeBackForZ2(nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackParaForZ2[])
   */
  @Override
  public void writeBackForZ2(IOrderWriteBackParaForZ2[] wbVos)
      throws BusinessException {
    try {
      new OrderWriteBackForZ2Action().writeback(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
