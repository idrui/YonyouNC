package nc.ui.pu.m24.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m24.IPricestlMaintain;
import nc.itf.pubapp.pub.smart.IBillMaintainService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 ����07:54:20
 */
public class PricestlModelService implements IAppModelService {

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    if (object.getClass().isArray()) {
      this.service().delete((PricestlVO[]) object);
    }
    else {
      this.service().delete(new PricestlVO[] {
        (PricestlVO) object
      });
    }

  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelservice()#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    return NCLocator.getInstance().lookup(IBillMaintainService.class)
        .insert((IBill) object);
  }

  /**
   * ���෽����д
   * 
   * @see
   *      nc.ui.uif2.model.IAppModelservice()#queryByDataVisibilitySetting(nc.vo
   *      .
   *      uif2.LoginContext)
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

    return NCLocator.getInstance().lookup(IBillMaintainService.class)
        .update((PricestlVO) object);

  }

  private IPricestlMaintain service() {
    return NCLocator.getInstance().lookup(IPricestlMaintain.class);
  }

}
