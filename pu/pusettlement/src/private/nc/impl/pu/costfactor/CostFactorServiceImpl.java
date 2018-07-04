/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-13 下午01:30:17
 */
package nc.impl.pu.costfactor;

import nc.impl.pu.costfactor.action.CostFactorQueryAction;
import nc.impl.pu.costfactor.action.DeleteAction;
import nc.impl.pu.costfactor.action.InsertAction;
import nc.impl.pu.costfactor.action.UpdateAction;
import nc.itf.pu.costfactor.ICostFactorManageService;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.costfactor.utils.CostFactorVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义单据增删改维护操作实现
 * <li>成本要素定义单据查询操作实现
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
 * @time 2009-5-13 下午01:30:17
 */
public class CostFactorServiceImpl implements ICostFactorManageService,
    ICostFactorQueryService {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.costfactor.ICostFactorQueryService#beReferenced(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean beReferenced(String pk_group, String pk_org,
      String pk_srcmaterial) throws BusinessException {
    try {
      CostFactorQueryAction action = new CostFactorQueryAction();
      return action.beReferenced(pk_group, pk_org, pk_srcmaterial);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return false;
  }

  @Override
  public void delete(CostfactorVO newVo) throws BusinessException {
    try {
      this.delete(new CostfactorVO[] {
        newVo
      });
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void delete(CostfactorVO[] newVos) throws BusinessException {
    try {
      new DeleteAction().delete(newVos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public CostfactorVO insert(CostfactorVO newVo) throws BusinessException {
    try {
      CostfactorVO[] returnVos = this.insert(new CostfactorVO[] {
        newVo
      });
      if (returnVos != null && returnVos.length > 0) {
        return returnVos[0];
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public CostfactorVO[] insert(CostfactorVO[] newVos) throws BusinessException {
    CostfactorVO[] bills = null;
    try {
      InsertAction action = new InsertAction();
      bills = action.insert(newVos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public CostfactorVO[] queryAllCostFactor(LoginContext context)
      throws BusinessException {
    return this.queryAllCostFactor(context, null);
  }

  @Override
  public CostfactorVO[] queryAllCostFactor(LoginContext context,
      String[] permissonOrgs) throws BusinessException {
    CostfactorVO[] bills = null;
    try {
      CostFactorQueryAction action = new CostFactorQueryAction();
      bills = action.query(context, permissonOrgs);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    // wuxla 不设置
    // if (!ArrayUtils.isEmpty(bills)) {
    // this.setTaxrate(bills);// 设置费用项默认税率
    // }
    return bills;
  }

  @Override
  public CostfactorVO[] queryCostfacotorIn(String pk_org)
      throws BusinessException {
    try {
      SqlBuilder where = new SqlBuilder();
      where.append(CostfactorHeaderVO.PK_ORG, pk_org);
      where.append(" and dr=0 ");
      CostfactorVO[] vos = new CostFactorQueryAction().querty(where.toString());
      return CostFactorVOUtil.filterVOs(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public CostfactorVO[] queryCostfacotorInNofilter(String pk_org)
      throws BusinessException {
    try {
      SqlBuilder where = new SqlBuilder();
      where.append(CostfactorHeaderVO.PK_ORG, pk_org);
      where.append(" and dr=0 ");
      CostfactorVO[] vos = new CostFactorQueryAction().querty(where.toString());
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public CostfactorViewVO[] queryEstCostItems(String pk_org)
      throws BusinessException {
    CostfactorViewVO[] vos = null;
    try {
      CostFactorQueryAction action = new CostFactorQueryAction();
      vos = action.queryEstItems(pk_org);
      // wuxla
      // if (!ArrayUtils.isEmpty(vos)) {
      // this.setTaxrate(vos);// 设置费用项默认税率
      // }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return vos;
  }

  @Override
  public CostfactorVO update(CostfactorVO newVo) throws BusinessException {
    try {
      CostfactorVO[] returnVos = this.update(new CostfactorVO[] {
        newVo
      });
      if (returnVos != null && returnVos.length > 0) {
        return returnVos[0];
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public CostfactorVO[] update(CostfactorVO[] newVos) throws BusinessException {
    CostfactorVO[] bills = null;
    try {
      UpdateAction action = new UpdateAction();
      bills = action.update(newVos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  // wuxla 去掉，不设置税率
  // private void setTaxrate(CostfactorItemVO[] items) {
  // Set<String> mpk =
  // CirVOUtil.getDistinctFieldSet(items, CostfactorItemVO.PK_MATERIAL);
  // try {
  // Map<String, UFDouble> trMap =
  // MaterialTaxRateService
  // .queryTaxRate(mpk.toArray(new String[mpk.size()]));
  // for (CostfactorItemVO item : items) {
  // item.setNmartaxrate(trMap.get(item.getPk_material()));
  // }
  // }
  // catch (Exception e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }

  // private void setTaxrate(CostfactorViewVO[] views) {
  // CostfactorItemVO[] items = new CostfactorItemVO[views.length];
  // for (int i = 0; i < items.length; i++) {
  // items[i] = (CostfactorItemVO) views[i].getVO(CostfactorItemVO.class);
  // }
  // // this.setTaxrate(items);
  // }

  // private void setTaxrate(CostfactorVO[] vos) {
  // BillHelper bh = new BillHelper(vos);
  // List<ISuperVO> ilist =
  // bh.getItemIndex().get(new CostfactorItemVO().getMetaData());
  // if (CollectionUtils.isEmpty(ilist)) {
  // return;
  // }
  // this.setTaxrate((CostfactorItemVO[]) new ListToArrayTool<ISuperVO>()
  // .convertToArray(ilist));
  // }

}
