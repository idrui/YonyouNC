/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-12 下午01:38:00
 */
package nc.ui.pu.costfactor.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.model.IAppModelDataManager;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pubapp.pattern.log.Log;

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
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-12 下午01:38:00
 */
public class CostFactorModelDataManager implements IAppModelDataManager {
  private BillManageModel model;

  private IAppModelService service;

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @return service
   */
  public IAppModelService getService() {
    return this.service;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelDataManager#initModel()
   */
  @Override
  public void initModel() {
    try {
      this.model.initModel(this.service.queryByDataVisibilitySetting(this.model
          .getContext()));
    }
    catch (Exception e) {
      // 日志异常
      Log.info(e);
      // 按规范抛出异常
      throw new BusinessRuntimeException(e.getMessage());
    }

  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
  }

  /**
   * @param service
   *          要设置的 service
   */
  public void setService(IAppModelService service) {
    this.service = service;
  }

}
