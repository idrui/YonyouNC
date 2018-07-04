/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-1 下午02:10:29
 */
package nc.ui.pu.m25.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IInvoiceMaintain;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-7-1 下午02:10:29
 */
public class InvoiceModelServcie implements IAppModelService {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    if (object.getClass().isArray()) {
      this.getService().delete((InvoiceVO[]) object, null);
    }
    else {
      this.getService().delete(new InvoiceVO[] {
        (InvoiceVO) object
      }, null);
    }

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.getService().save((InvoiceVO[]) object, null);
    }
    return this.getService().save(new InvoiceVO[] {
      (InvoiceVO) object
    }, null);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext)
   */
  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.getService().save((InvoiceVO[]) object, null);
    }
    return this.getService().save(new InvoiceVO[] {
      (InvoiceVO) object
    }, null);
  }

  private IInvoiceMaintain getService() {
    return NCLocator.getInstance().lookup(IInvoiceMaintain.class);
  }

}
