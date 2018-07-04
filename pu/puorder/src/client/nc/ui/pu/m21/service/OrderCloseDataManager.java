/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午04:41:52
 */
package nc.ui.pu.m21.service;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.pubapp.uif2app.model.IRefreshable;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

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
 * @author chenlla
 * @time 2010-4-8 下午04:41:52
 */
public class OrderCloseDataManager implements IAppModelDataManagerEx,
    IRefreshable {

  private String sqlWhere;

  protected BillManageModel model = null;

  protected IQueryService service = null;

  public BillManageModel getModel() {
    return this.model;
  }

  public IQueryService getService() {
    return this.service;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelDataManager#initModel()
   */
  @Override
  public void initModel() {
    this.getModel().initModel(null);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelDataManagerEx#initModelBySqlWhere(java.lang.String)
   */
  @Override
  public void initModelBySqlWhere(String whereSql) {
    this.sqlWhere = whereSql;
    try {
      this.model.initModel(this.service.queryByWhereSql(whereSql));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelDataManagerEx#refresh()
   */
  @Override
  public void refresh() {
    if (this.sqlWhere != null) {
      this.initModelBySqlWhere(this.sqlWhere);
    }
  }

  @Override
  public boolean refreshable() {
    return !StringUtils.isBlank(this.sqlWhere);
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setService(IQueryService service) {
    this.service = service;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelDataManagerEx#setShowSealDataFlag(boolean)
   */
  @Override
  public void setShowSealDataFlag(boolean showSealDataFlag) {
    //
  }

}
