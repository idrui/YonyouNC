/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午08:16:28
 */
package nc.itf.pu.reference.so;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.pu.m21.ISaleOrderFor21;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.pubitf.so.m30arrange.po.m20.IRewrite30For20;
import nc.pubitf.so.m30arrange.po.m21.IRewrite30For21;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-25 下午08:16:28
 */
public class M30SOServices {

  /**
   * 根据表体id查询销售订单结算财务组织
   * 
   * @param bids
   * @return
   */
  public static Map<String, String> getFinanceorgByBids(String[] bids) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return null;
    }
    VOQuery<SaleOrderBVO> query =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CSETTLEORGID
        });
    SaleOrderBVO[] vos = query.query(bids);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    Map<String, String> map = new HashMap<String, String>();
    for (SaleOrderBVO vo : vos) {
      map.put(vo.getCsaleorderbid(), vo.getCsettleorgid());
    }
    return map;
  }

  /**
   * 销量查询
   * 
   * @param cmaterialvid 物料VID数组
   * @param queryDate 查询时间
   * @param queryDay 查询天数
   * @param pk_group 集团
   * @return
   */
  public static Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialvid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return new HashMap<String, UFDouble>();
    }
    ISaleOrderFor21 service =
        NCLocator.getInstance().lookup(ISaleOrderFor21.class);
    return service.getSaleOrderNumber(cmaterialvid, queryDate, queryDay,
        pk_group, pk_org);
  }

  /**
   * 销售参数SO23，是否含税优先
   * 
   * @return
   */
  public static boolean isTaxPriorBySO23Para() {
    String pk_group = AppContext.getInstance().getPkGroup();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }

  /**
   * 方法功能描述：生成协同销售订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-26 下午01:38:29
   */
  public static void push21To30(OrderVO[] vos) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }
    ISaleOrderFor21 service =
        NCLocator.getInstance().lookup(ISaleOrderFor21.class);
    try {
      service.push21To30(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static Map<String, String> queryCoop21Bids(String[] bids) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return new HashMap<String, String>();
    }
    ISaleOrderFor21 service =
        NCLocator.getInstance().lookup(ISaleOrderFor21.class);
    try {
      return service.queryCoop21Bids(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 查询销售交易类型 是否直运
   * 
   * @param ctrantypeid
   * @return
   */
  public static UFBoolean queryIsDirectPO(String ctrantypeid) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0056")/*
                                                                   * @res
                                                                   * "销售模块未启用，请检查！"
                                                                   */);
    }
    Map<String, UFBoolean> tranMap =
        M30TranTypeUtil.getInstance().queryIsDirectPO(new String[] {
          ctrantypeid
        });
    if (null != tranMap.get(ctrantypeid)) {
      return tranMap.get(ctrantypeid);
    }
    return UFBoolean.FALSE;
  }

  public static Map<String, UFBoolean> querySOTrantypeDirectAttrs(
      String[] ctrantypeids) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0056")/*
                                                                   * @res
                                                                   * "销售模块未启用，请检查！"
                                                                   */);
    }
    return M30TranTypeUtil.getInstance().queryIsDirectPO(ctrantypeids);
  }

  public static void renovate30For21Delete(String[] ids) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }
    nc.pubitf.so.m30.pu.m21.IRewrite30For21 service =
        NCLocator.getInstance().lookup(
            nc.pubitf.so.m30.pu.m21.IRewrite30For21.class);
    try {
      service.renovate30For21Delete(ids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static void rewriteBillCode(Map<String, String> codeMap) {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }
    nc.pubitf.so.m30.pu.m21.IRewrite30For21 service =
        NCLocator.getInstance().lookup(
            nc.pubitf.so.m30.pu.m21.IRewrite30For21.class);
    try {
      service.rewriteBillCode(codeMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：请购单回写销售订单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-3 下午01:22:17
   */
  public static void writeback30For20(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }
    IRewrite30For20 service =
        NCLocator.getInstance().lookup(IRewrite30For20.class);
    service.rewrite30ArrangeNumFor20(paras);
  }

  /**
   * 方法功能描述：采购订单回写销售订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-30 上午10:10:30
   */
  public static void writeback30For21(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }
    IRewrite30For21 service =
        NCLocator.getInstance().lookup(IRewrite30For21.class);
    service.rewrite30ArrangeNumFor21(paras);
  }
}
