package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderRevise;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-15 ����11:05:56
 */
public class OrderReviseModelService implements IAppModelService {

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    return;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext)
   */
  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    IOrderRevise reviseService =
        NCLocator.getInstance().lookup(IOrderRevise.class);
    if (object.getClass().isArray()) {
      return reviseService.reviseSave((OrderVO[]) object, null);
    }

    return reviseService.reviseSave(new OrderVO[] {
      (OrderVO) object
    }, null);
  }

}
