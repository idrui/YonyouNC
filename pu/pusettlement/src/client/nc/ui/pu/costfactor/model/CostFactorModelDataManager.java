/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-12 ����01:38:00
 */
package nc.ui.pu.costfactor.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.model.IAppModelDataManager;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pubapp.pattern.log.Log;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-12 ����01:38:00
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
   * ���෽����д
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
      // ��־�쳣
      Log.info(e);
      // ���淶�׳��쳣
      throw new BusinessRuntimeException(e.getMessage());
    }

  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
  }

  /**
   * @param service
   *          Ҫ���õ� service
   */
  public void setService(IAppModelService service) {
    this.service = service;
  }

}
