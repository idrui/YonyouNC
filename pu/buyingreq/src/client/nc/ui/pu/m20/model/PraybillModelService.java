package nc.ui.pu.m20.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午04:00:05
 */
public class PraybillModelService implements IAppModelService {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    if (object.getClass().isArray()) {
      this.service().delete((PraybillVO[]) object);
    }
    else {
      this.service().delete(new PraybillVO[] {
        (PraybillVO) object
      });
    }

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelservice()#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.service().insert((PraybillVO[]) object);
    }

    return this.service().insert(new PraybillVO[] {
      (PraybillVO) object
    });

  }

  /**
   * 父类方法重写
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
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.service().update((PraybillVO[]) object);
    }

    return this.service().update(new PraybillVO[] {
      (PraybillVO) object
    });

  }

  private IPraybillMaintain service() {
    return NCLocator.getInstance().lookup(IPraybillMaintain.class);
  }

}
