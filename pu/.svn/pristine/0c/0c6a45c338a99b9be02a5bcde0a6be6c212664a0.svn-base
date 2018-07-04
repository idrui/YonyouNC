/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-12 上午10:44:27
 */
package nc.ui.pu.costfactor.model;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.itf.pu.costfactor.ICostFactorManageService;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

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
 * @time 2009-5-12 上午10:44:27
 */
public class CostFactorModelService implements IAppModelService {

  private Class<? extends ICostFactorQueryService> qryService;

  private Class<? extends ICostFactorManageService> service;

  private IValidationService validationService = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    if (object instanceof CostfactorVO) {
      NCLocator.getInstance().lookup(this.service)
          .delete((CostfactorVO) object);
    }
    else if (object instanceof CostfactorVO[]) {
      NCLocator.getInstance().lookup(this.service)
          .delete((CostfactorVO[]) object);
    }
  }

  public Class<? extends ICostFactorQueryService> getQryService() {
    return this.qryService;
  }

  public Class<? extends ICostFactorManageService> getService() {
    return this.service;
  }

  public IValidationService getValidationService() {
    return this.validationService;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    this.validate(object);
    this.filterMaterialNullItem(object);
    if (object instanceof CostfactorVO) {
      return NCLocator.getInstance().lookup(this.service)
          .insert((CostfactorVO) object);
    }
    else if (object instanceof CostfactorVO[]) {
      return NCLocator.getInstance().lookup(this.service)
          .insert((CostfactorVO[]) object);
    }
    else {
      return null;
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext)
   */
  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    // 组织权限
    String[] permissionOrgs = context.getFuncInfo().getFuncPermissionPkorgs();
    return NCLocator.getInstance().lookup(this.qryService)
        .queryAllCostFactor(context, permissionOrgs);
  }

  public void setQryService(Class<? extends ICostFactorQueryService> qryService) {
    this.qryService = qryService;
  }

  public void setService(Class<? extends ICostFactorManageService> service) {
    this.service = service;
  }

  public void setValidationService(IValidationService validationService) {
    this.validationService = validationService;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    this.validate(object);
    this.filterMaterialNullItem(object);
    if (object instanceof CostfactorVO) {
      return NCLocator.getInstance().lookup(this.service)
          .update((CostfactorVO) object);
    }
    else if (object instanceof CostfactorVO[]) {
      return NCLocator.getInstance().lookup(this.service)
          .update((CostfactorVO[]) object);
    }
    else {
      return null;
    }
  }

  private void filterMaterialNullItem(Object object) {
    if (object instanceof CostfactorVO) {
      this.filterMaterialNullItemVO(new CostfactorVO[] {
        (CostfactorVO) object
      });
    }
    else if (object instanceof CostfactorVO[]) {
      this.filterMaterialNullItemVO((CostfactorVO[]) object);
    }
  }

  /**
   * 过滤掉表体物料为空的行
   * 
   * @param vos
   * @return
   */
  private void filterMaterialNullItemVO(CostfactorVO[] vos) {
    for (CostfactorVO vo : vos) {
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      List<CostfactorItemVO> filterItemVOs = new ArrayList<CostfactorItemVO>();

      for (CostfactorItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isEmpty(item.getPk_srcmaterial())) {
          continue;
        }
        filterItemVOs.add(item);
      }
      if (filterItemVOs.size() == 0) {
        vo.setChildrenVO(null);
      }
      else {
        vo.setChildrenVO(filterItemVOs
            .toArray(new CostfactorItemVO[filterItemVOs.size()]));
      }
    }
  }

  protected void validate(Object value) {
    if (this.validationService != null) {
      try {
        this.validationService.validate(value);
      }
      catch (ValidationException e) {
        throw new BusinessExceptionAdapter(e);
      }
    }

  }
}
