/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-26 下午02:44:31
 */
package nc.itf.pu.reference.ct;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.ct.purdaily.ICtPayPlanQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ct.purdaily.ICtQueryForOrder;
import nc.pubitf.ct.purdaily.IOrderToCtRefWhereSql;
import nc.pubitf.ct.purdaily.IOrderToCtRelating;
import nc.pubitf.ct.purdaily.puorder.IReWriteZ2For21;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合同
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-26 下午02:44:31
 */
public class Z2CTServices {
  /**
   * 检查参数
   * 
   * @param pk_org
   * @return
   */
  public static boolean checkPO88Para(String pk_org) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return true;
    }
    try {

      ICtPayPlanQuery service =
          NCLocator.getInstance().lookup(ICtPayPlanQuery.class);
      return service.checkPO88Para(pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  /**
   * 方法功能描述：合同号参照
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   * @param pk_supplier
   * @param pk_material
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 下午02:40:28
   */
  public static String getRefWherePart(String pk_org, String pk_supplier,
      String pk_material, String corigcurrencyid, String dbilldate)
      throws BusinessException {
    IOrderToCtRefWhereSql purefct =
        NCLocator.getInstance().lookup(IOrderToCtRefWhereSql.class);
    return purefct.getWherePart(pk_org, pk_supplier, pk_material,
        corigcurrencyid, dbilldate, UFBoolean.FALSE);
  }

  /**
   * 方法功能描述：根据合同bid，返回交易类型及控制方式
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-3 上午10:08:55
   */
  public static Map<String, CtBusinessVO> queryCtBusinessByPks(String[] pks)
      throws BusinessException {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return new HashMap<String, CtBusinessVO>();
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    return service.queryCtBusinessByPks(pks);
  }

  /**
   * 为集采控制提供的查询，合同是总括订单的，需要找总括订单关联的合同。
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param pks
   * @return
   */
  public static Map<String, CtPubillViewVO> queryCtInfoForCenpuByBid(
      String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryCtInfoForCenpuByBid(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, CtPuVO> queryOrgTypeByPks(String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryOrgTypeByPks(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, CtPubillViewVO> queryViewVOsByBPks(String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryViewVOsByBPks(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  // /**
  // * 方法功能描述：通过合同主键查订单控制方式，用于合同控制检查
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param value
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-5-30 上午10:07:59
  // */
  // public static Integer queryCtltypebypk(String value) throws
  // BusinessException {
  // if (!SysInitGroupQuery.isCTEnabled()) {
  // return -1;
  // }
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.queryCtltypebypk(value);
  // }

  //
  // /**
  // * 方法功能描述：物料控制方式
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param value
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 上午11:23:57
  // */
  // public static Integer queryMaterial(String value, String pk_group)
  // throws BusinessException {
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.queryMaterial(value, pk_group);
  // }

  // /**
  // * 方法功能描述：根据合同表体主键得到对应原币含税单价，用于合同控制检查
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param pks
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-5-30 上午10:01:48
  // */
  // public static Map<String, UFDouble> queryNoriTaxPriceByPks(String[] pks)
  // throws BusinessException {
  // ICtQueryForOrder service =
  // NCLocator.getInstance().lookup(ICtQueryForOrder.class);
  // return service.queryNoriTaxPriceByPks(pks);
  // }

  // /**
  // * 方法功能描述：根据表体主键数组查询合同表体
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param ids
  // * @return <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 下午04:24:53
  // */
  // public static CtPuBVO[] queryPurdailyBVO(String[] ids)
  // throws BusinessException {
  // IPurdailyMaintain service =
  // NCLocator.getInstance().lookup(IPurdailyMaintain.class);
  // return service.queryPurdailyBVO(ids);
  // }

  // /**
  // * 方法功能描述：通过合同主键 返回交易类型VO的MAP
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param ids
  // * @return <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 下午04:40:37
  // */
  // public static Map<String, BusinessSetVO> querySetVOsByPk(String[] ids)
  // throws BusinessException {
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.querySetVOsByPk(ids);
  // }

  /**
   * 方法功能描述：采购订单关联合同服务
   * <p>
   * <b>参数说明</b>
   * 
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 下午02:37:42
   */
  public static CtPubillViewVO[] relatingCT(CtRelatingVO[] queryVOs)
      throws BusinessException {
    IOrderToCtRelating service =
        NCLocator.getInstance().lookup(IOrderToCtRelating.class);
    return service.queryForRelating(queryVOs, UFBoolean.FALSE);
  }

  /**
   * 方法功能描述：采购订单回写采购合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param backVO
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-30 上午10:01:21
   */
  public static void rewriteBackZ2(CtWriteBackForOrderVO[] backVO)
      throws BusinessException {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }
    IReWriteZ2For21 service =
        NCLocator.getInstance().lookup(IReWriteZ2For21.class);
    service.rewriteBack(backVO);
  }
}
